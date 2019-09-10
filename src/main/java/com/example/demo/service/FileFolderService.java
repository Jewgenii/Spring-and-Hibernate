package com.example.demo.service;

import com.example.demo.model.Folder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class FileFolderService {

    private static final String root = "./usrFldr";

    public Folder folder = null;
    public File userRootFolder;

    public FileFolderService() {
        File root = new File(FileFolderService.root);
        if (!root.exists()) root.mkdir();
    }

    //resource: file or folder
    public Folder get(String uid, String resource) {

        try {
            // test log out for concurrency
            System.out.print("try access:" + uid + " " + ZonedDateTime.now().toString() + "\r\n");

            userRootFolder = this.createUserRootFolder(uid);
            folder.fileName = (ArrayList<String>) Arrays.asList(userRootFolder.list((dir, name) -> name == resource));

            Thread.sleep(3000);
        } catch (InterruptedException ex) {

        }

        return folder;
    }

    public void create(String uid, String resource) {

        String rootFolderPath = createUserRootFolder(uid).getPath();

        File fl = new File(String.format("%s/%s", rootFolderPath, resource));

        if (resource != "" && !fl.exists()) fl.mkdir();
    }

    public void delete(String uid, String resource) {

    }

    public File createUserRootFolder(String uid) {
        File fl = new File(String.format("%s/%s", root, uid));
        if (!fl.exists()) {
            fl.mkdir();
        }
        return fl;
    }

    public File[] getRootFiles(String uid) {
        return createUserRootFolder(uid).listFiles();
    }

    public boolean fileFolderExists(String uid) {
        return new File(String.format("%s/%s", root, uid)).exists();
    }

}
