package com.example.demo.controller;

import com.example.demo.model.Folder;
import com.example.demo.model.User;
import com.example.demo.service.FileFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;

@RestController
@RequestMapping(value = {"/filesystem"})
public class FileSystemController {
    static volatile WeakHashMap<String,String> locks = new WeakHashMap<>();

    @Autowired
    public volatile FileFolderService flService;

    @RequestMapping(name = "getfolder",method = RequestMethod.GET)
    public Folder get(){

           return flService.get("jack","initialFolder");
    }

    @RequestMapping(name="createfolder",method = RequestMethod.POST)
    public void create(@RequestParam String uid,String resource){
        try{
;
            resource = resource==null?"":resource;

            System.out.println("before lock::"+Thread.currentThread().getId()+", uid::"+uid);

            synchronized (getSyncObjectForId(uid)){
                System.out.println("in lock::"+Thread.currentThread().getId()+", uid::"+uid);
                Thread.sleep(5000);
                flService.create(uid,resource);
                System.out.println("leave lock::"+Thread.currentThread().getId()+", uid::"+uid);
            }

        }catch(Exception ex){
            System.out.println(ex);
        }


        System.out.println("in lock::"+Thread.currentThread().getId()+", uid::"+uid);
      //  Thread.sleep(5000);
        flService.create(uid,resource);
        System.out.println("leave lock::"+Thread.currentThread().getId()+", uid::"+uid);

    }
    static Object getSyncObjectForId(String _uid) {
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
