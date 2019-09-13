package com.example.demo.controller;

import com.example.demo.service.FIleLoggerService;
import com.example.demo.service.FileFolderService;
import com.example.demo.service.MyConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.WeakHashMap;

@RestController
@RequestMapping("/filesystem")
public class FileSystemController {
    static volatile WeakHashMap<String,String> locks = new WeakHashMap<>();
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
