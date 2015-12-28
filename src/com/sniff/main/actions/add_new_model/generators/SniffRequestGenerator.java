package com.sniff.main.actions.add_new_model.generators;

import com.sniff.main.actions.add_new_model.SniffModelCreationController;
import com.sniff.main.actions.add_new_model.templates.Templates;
import com.sniff.main.base.SniffBaseModelGenerator;

/**
 * Created by ivan on 7.12.15.
 */
public class SniffRequestGenerator extends SniffBaseModelGenerator{
    private SniffMethodGenerator mMethod;

    public SniffRequestGenerator(String modelName, String aPackage, SniffMethodGenerator method) {
        super(modelName, aPackage);
        mMethod = method;
    }

    @Override
    public String generate() {
        return Templates.mRequestBody.replace(SniffModelCreationController.cName, mMethod.getMethodName())
                .replace(SniffModelCreationController.cPathDoted, mPackage)
                .replace(SniffModelCreationController.cType, mMethod.getReturnValueType())
                .replace(SniffModelCreationController.cNameL, mModelName.toLowerCase())
                .replace(SniffModelCreationController.cModelName, mModelName)
                .replace(SniffModelCreationController.cNameH, mMethod.getMethodName().toUpperCase())
                .replace(SniffModelCreationController.cFunctionFolder, mMethod.getMethodName().toLowerCase());
    }
}
