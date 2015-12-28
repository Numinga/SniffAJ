package com.sniff.main.base;

/**
 * Created by ivan on 7.12.15.
 */
public class SniffBaseInitGenerator {
    public String mProjectPath, mPackage, mModelPath;

    public SniffBaseInitGenerator(String projectPath, String aPackage, String modelPath) {
        mProjectPath = projectPath;
        mPackage = aPackage;
        mModelPath = modelPath;
    }

    public String generate(){
        return "";
    }
}
