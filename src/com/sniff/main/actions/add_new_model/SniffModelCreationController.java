package com.sniff.main.actions.add_new_model;

import com.intellij.openapi.util.io.FileUtil;
import com.sniff.main.actions.add_new_model.generators.SniffListenerGenerator;
import com.sniff.main.actions.add_new_model.generators.SniffMethodGenerator;
import com.sniff.main.actions.add_new_model.generators.SniffModelGenerator;
import com.sniff.main.actions.add_new_model.generators.SniffParserGenerator;
import com.sniff.main.actions.add_new_model.generators.SniffRequestGenerator;
import com.sniff.main.base.SniffBaseCreationController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ivan on 7.12.15.
 */
public class SniffModelCreationController extends SniffBaseCreationController {
    private String mModelName, mModelPath;
    private ArrayList<SniffMethodGenerator> mRequestMethods;

    public SniffModelCreationController(String basePath, String aPackage, String modelName, ArrayList<SniffMethodGenerator> requestMethods) {
        super(aPackage, basePath);
        mModelName = modelName;
        mRequestMethods = requestMethods;
        mModelPath = mProjectPath + "/model";
    }

    public boolean create() {
        checkFolderStructure();
        createModel();
        createListener();
        createRequests();
        createParsers();
        return true;
    }

    private void createModel() {
        String path = mModelPath + "/" + mModelName + "Model.java";
        SniffModelGenerator modelGenerator = new SniffModelGenerator(mModelName, mPackage, mRequestMethods);
        String modelCode = modelGenerator.generate();
        writeToFile(path, modelCode);
    }

    private void createListener() {
        for (int i = 0; i < mRequestMethods.size(); i++) {
            SniffMethodGenerator m = mRequestMethods.get(i);
            String path = mModelPath + "/" + m.getMethodName().toLowerCase()
                    + "/" +m.getMethodName() + "LoadingListener.java";
            SniffListenerGenerator listenerGenerator = new SniffListenerGenerator(mModelName, mPackage, m);
            String listenerCode = listenerGenerator.generate();
            writeToFile(path, listenerCode);
        }
    }

    private void createRequests() {
        for (int i = 0; i < mRequestMethods.size(); i++) {
            SniffMethodGenerator m = mRequestMethods.get(i);
            String path = mModelPath + "/" + m.getMethodName().toLowerCase()
                    + "/" + m.getMethodName() + "Request.java";
            SniffRequestGenerator requestGenerator = new SniffRequestGenerator(mModelName, mPackage, m);
            String listenerCode = requestGenerator.generate();
            writeToFile(path, listenerCode);
        }
    }

    private void createParsers() {
        for (int i = 0; i < mRequestMethods.size(); i++) {
            SniffMethodGenerator m = mRequestMethods.get(i);
            String path = mModelPath + "/" + m.getMethodName().toLowerCase()
                    + "/" + m.getMethodName() + "Parser.java";
            SniffParserGenerator parserGenerator = new SniffParserGenerator(mModelName, mPackage, m);
            String listenerCode = parserGenerator.generate();
            writeToFile(path, listenerCode);
        }
    }

    private void checkFolderStructure() {
        checkOrCreateFolder(mModelPath);
        mModelPath += "/" + mModelName.toLowerCase();
        checkOrCreateFolder(mModelPath);
        for (SniffMethodGenerator m :
                mRequestMethods) {
            String path = mModelPath + "/" + m.getMethodName().toLowerCase();
            checkOrCreateFolder(path);
        }
    }

    public static final String cName = "#c:name#";
    public static final String cNameL = "#c:name.l#";
    public static final String cNameH = "#c:name.h#";
    public static final String cType = "#c:type#";
    public static final String cPathDoted = "#c:model.path#";
    public static final String cModelName = "#c:model.name#";
    public static final String cFunctionFolder = "#c:fname.l#";
}
