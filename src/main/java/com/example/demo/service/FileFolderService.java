package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Service
public class FileFolderService {

    private static final String root = "."+File.separator+"usrFldr";

    public File userRootFolder;

    public FileFolderService() {
        File root = new File(FileFolderService.root);
        if (!root.exists()) root.mkdir();
    }

    //resource: file or folder
    public List<String> getRootList(String uid) {
        userRootFolder = this.createUserRootFolder(uid);
        return Arrays.asList(userRootFolder.list());
    }
    public List<String> get(String uid,String resource){
        String rootPath = this.createUserRootFolder(uid).getPath();
        String resourcePath = rootPath.concat(File.separator).concat(resource);
        File fl = new File(resourcePath);

        if(fl.exists()){
            return Arrays.asList(fl.list());
        }

    return  null;
    }

    public Boolean create(String uid, String resource) throws IOException {

        resource = resource.trim();
        if(resource.length()>0){
            String rootFolderPath = createUserRootFolder(uid).getCanonicalPath();
            File fl = new File(String.format("%s"+File.separator+"%s", rootFolderPath, resource));

            if (!fl.exists()){ // stupid check if a file or folder
                if(resource.contains(".")) {
                    Files.createFile(fl.toPath());
                    return true;
                }
                else
                    return  fl.mkdir();
            }
        }
        return false;
    }

    public boolean delete(String uid,String resource){
        String rootFolderPath = createUserRootFolder(uid).getPath();
        File fl = new File(String.format("%s"+File.separator+"%s", rootFolderPath, resource));
        if(fl.exists()){
            return fl.delete();
        }
        return false;
    }

    public File createUserRootFolder(String uid) {
        File fl = new File(String.format("%s"+File.separator+"%s", root, uid));
        if (!fl.exists()) {
            fl.mkdir();
        }
        return fl;
    }

    public File[] getRootFiles(String uid) {
        return createUserRootFolder(uid).listFiles();
    }

    public boolean fileFolderExists(String uid) {
        return new File(String.format("%s"+File.separator+"%s", root, uid)).exists();
    }

}
