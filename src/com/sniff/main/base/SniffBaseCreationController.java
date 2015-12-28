package com.sniff.main.base;

import com.intellij.openapi.util.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ivan on 9.12.15.
 */
public class SniffBaseCreationController {

    public String mPackage, mProjectPath, mLogString;
    public String mBasePath;

    public SniffBaseCreationController(String aPackage, String basePath){
        mPackage = aPackage;
        mLogString = "";
        mBasePath = basePath;
        setProjectPath(basePath);
    }

    private void setProjectPath(String basePath){
        mProjectPath = basePath + "/app/src/main/java/" + mPackage.replace(".", "/");
    }

    public void checkOrCreateFolder(String path){
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
    }

    public void writeToFile(String path, String body) {
        File file = new File(path);
        System.out.println(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileUtil.writeToFile(file, body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFile(String path){
        try {
            byte [] enc = Files.readAllBytes(Paths.get(path));
            return new String(enc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void safeWriteToFile(String path, String name, String body){
        File file = new File(path);
        if(file != null && file.exists()){
            File back = new File(path.replace(".xml", ".backup.xml"));
            file.renameTo(back);
            writeToFile(path, body);
            back.delete();
        }
    }
}
