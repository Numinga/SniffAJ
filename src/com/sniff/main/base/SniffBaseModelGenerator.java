package com.sniff.main.base;

import com.sniff.main.actions.add_new_model.generators.SniffMethodGenerator;

import java.util.ArrayList;

/**
 * Created by ivan on 7.12.15.
 */
public class SniffBaseModelGenerator {
    public String mModelName, mPackage;

    public SniffBaseModelGenerator(String modelName, String aPackage) {
        mModelName = modelName;
        mPackage = aPackage;
    }

    public String getModelName() {
        return mModelName;
    }

    public void setModelName(String modelName) {
        mModelName = modelName;
    }

    public String getPackage() {
        return mPackage;
    }

    public void setPackage(String aPackage) {
        mPackage = aPackage;
    }

    public String generate(){
        return "";
    }
}
