package com.sniff.main.actions.add_new_model.generators;

import com.sniff.main.actions.add_new_model.SniffModelCreationController;
import com.sniff.main.actions.add_new_model.templates.Templates;
import com.sniff.main.base.SniffBaseModelGenerator;

import java.util.ArrayList;

/**
 * Created by ivan on 7.12.15.
 */
public class SniffModelGenerator extends SniffBaseModelGenerator {

    private ArrayList<SniffMethodGenerator> mMethods;

    public SniffModelGenerator(String modelName, String aPackage, ArrayList<SniffMethodGenerator> methods) {
        super(modelName, aPackage);
        mMethods = methods;
    }

    @Override
    public String generate() {
        return Templates.mModelBody.replace(cListenersDeclare, getListenersDeclare())
                .replace(cListenersInit, getListenersInit())
                .replace(cListenersSet, getListenersSet())
                .replace(cMethodStatic, getMethodStatic())
                .replace(cMethodBody, getMethodBody())
                .replace(cMethodNotify, getMethodNotify())
                .replace(cMethodImport, getMethodImport())
                .replace(SniffModelCreationController.cPathDoted, mPackage)
                .replace(SniffModelCreationController.cNameL, mModelName.toLowerCase())
                .replace(SniffModelCreationController.cNameH, mModelName.toUpperCase())
                .replace(SniffModelCreationController.cName, mModelName);
    }

    private String getListenersDeclare(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mMethods.size(); i++) {
            result.append(mMethods.get(i).getListenerDeclareString());
        }
        return  result.toString();
    }

    private String getListenersInit(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mMethods.size(); i++) {
            result.append(mMethods.get(i).getListenerInitString());
        }
        return  result.toString();
    }

    private String getListenersSet(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mMethods.size(); i++) {
            result.append(mMethods.get(i).getListenerSettingString());
        }
        return  result.toString();
    }

    private String getMethodStatic(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mMethods.size(); i++) {
            result.append(mMethods.get(i).getMethodStaticString());
        }
        return  result.toString();
    }

    private String getMethodBody(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mMethods.size(); i++) {
            result.append(mMethods.get(i).getMethodBodyString());
        }
        return  result.toString();
    }

    private String getMethodNotify(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mMethods.size(); i++) {
            result.append(mMethods.get(i).getMethodNotifyString());
        }
        return  result.toString();
    }

    private String getMethodImport(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mMethods.size(); i++) {
            result.append(mMethods.get(i).getMethodImportString());
        }
        return  result.toString();
    }

    private static final String cListenersDeclare = "#c:listeners.declare#";
    private static final String cListenersSet = "#c:listeners.set#";
    private static final String cListenersInit = "#c:listeners.init#";
    private static final String cMethodStatic = "#c:method.static#";
    private static final String cMethodBody = "#c.method.body#";
    private static final String cMethodNotify = "#c.method.notify#";
    private static final String cMethodImport = "#c:import#";
}
