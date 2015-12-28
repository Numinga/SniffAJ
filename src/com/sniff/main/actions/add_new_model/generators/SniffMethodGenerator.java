package com.sniff.main.actions.add_new_model.generators;

import com.sniff.main.actions.add_new_model.SniffModelCreationController;
import com.sniff.main.actions.add_new_model.templates.Templates;
import com.sniff.main.base.SniffBaseModelGenerator;

/**
 * Created by ivan on 7.12.15.
 */
public class SniffMethodGenerator extends SniffBaseModelGenerator{

    private String mMethodName, mReturnValueType;

    public SniffMethodGenerator(String modelName, String aPackage, String methodName, String returnType) {
        super(modelName, aPackage);
        mMethodName = methodName;
        mReturnValueType = returnType;
    }

    public String getReturnValueType() {
        return mReturnValueType;
    }

    public String getMethodName() {
        return mMethodName;
    }

    public String getListenerDeclareString() {
        return Templates.mMethodListenerDeclare.replace(SniffModelCreationController.cName, mMethodName)
                .replace(SniffModelCreationController.cNameL, mMethodName.toLowerCase())
                .replace(SniffModelCreationController.cNameH, mMethodName.toUpperCase());
    }

    public String getListenerSettingString() {
        return Templates.mMethodListenerSet.replace(SniffModelCreationController.cName, mMethodName)
                .replace(SniffModelCreationController.cNameH, mMethodName.toUpperCase())
                .replace(SniffModelCreationController.cModelName, mModelName);
    }

    public String getListenerInitString() {
        return Templates.mMethodListenerInit.replace(SniffModelCreationController.cName, mMethodName);
    }

    public String getMethodStaticString() {
        return Templates.mMethodStatic.replace(SniffModelCreationController.cName, mMethodName);
    }

    public String getMethodBodyString() {
        return Templates.mMethodBody.replace(SniffModelCreationController.cName, mMethodName)
                .replace(SniffModelCreationController.cType, mReturnValueType);
    }

    public String getMethodNotifyString() {
        return Templates.mMethodNotify.replace(SniffModelCreationController.cName, mMethodName)
                .replace(SniffModelCreationController.cType, mReturnValueType);
    }

    public String getMethodImportString(){
        return Templates.mImportBody.replace(SniffModelCreationController.cName, mMethodName)
                .replace(SniffModelCreationController.cType, mReturnValueType)
                .replace(SniffModelCreationController.cNameL, mModelName.toLowerCase())
                .replace(SniffModelCreationController.cFunctionFolder, mMethodName.toLowerCase());
    }
}
