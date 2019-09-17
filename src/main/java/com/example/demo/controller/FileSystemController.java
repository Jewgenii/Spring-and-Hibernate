package com.example.demo.controller;

import com.example.demo.service.FIleLoggerService;
import com.example.demo.service.FileFolderService;
import com.example.demo.service.MyConfig;
import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/filesystem")
public class FileSystemController {
    static volatile HashMap<String,String> locks = new HashMap<>();
    @Autowired
    FIleLoggerService loger;

    @Autowired
    public volatile FileFolderService flService;

    public FileSystemController() {
      /*  GenericApplicationContext c  = new AnnotationConfigApplicationContext(MyConfig.class);
        this.loger = c.getBean(FIleLoggerService.class);*/

    }

    @GetMapping("getfolder")
    public List<String> get(String uid, String resource){

           loger.log("get:"+resource);
           System.out.println("controller thread name:"+Thread.currentThread().getName());
           return flService.get(uid,resource);
    }

    @GetMapping("getrootfolder")
    public List<String> getrootfolder(String uid){
        return flService.getRootList(uid);
    }

    @PostMapping("createfolder")
    public void create(@RequestParam String uid,String resource){
        try{
;
            System.out.println("before lock::"+Thread.currentThread().getId()+", uid::"+uid);

            synchronized (getSyncObjectForId(uid)){
                System.out.println("in lock::"+Thread.currentThread().getId()+", uid::"+uid);
                Thread.sleep(1000);
                flService.create(uid,resource);
                System.out.println("leave lock::"+Thread.currentThread().getId()+", uid::"+uid);
                locks.remove(uid);
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    @DeleteMapping
    public Boolean delete(@RequestParam String uid,String resource){
        loger.log(resource);
        return  flService.delete(uid,resource);
    }
    @PostMapping("uploadfile")
    public List<String> uploadfile(@RequestParam String uid,@RequestParam() MultipartFile[] files){
        String root = flService.createUserRootFolder(uid).getPath();
        List<String> fileNotWritten = new ArrayList<>();
        for (MultipartFile mfile:files){
            String flname = String.join(File.separator, (CharSequence) root,mfile.getOriginalFilename());
            File fl = new File(flname);
            if(fl.exists()){
                fileNotWritten.add("not written"+File.pathSeparator+flname);
            }

            try {
                fl.createNewFile();
                FileOutputStream out =  new FileOutputStream(flname);
                out.write(mfile.getInputStream().readAllBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileNotWritten;
    }
    public void test(){
        ExecutorService es = Executors.newSingleThreadExecutor();

    }
    static Object getSyncObjectForId(final String _uid) {
        synchronized (locks) {
            String lock = locks.get(_uid);
            if (lock == null) {
                lock = _uid;
                locks.put(lock, lock);
            }
            return lock;
        }
    }
}
