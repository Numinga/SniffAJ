package com.sniff.main.actions.init_base_sniffers;

import com.sniff.main.actions.add_new_model.generators.SniffModelGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.ApplicationExceptionGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.HandledRequestGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.HandledStringRequestGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.LoadingListenerGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.RequestHandlerGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.ResponseListenerGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.ResponseParserGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.StringResponseParserGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.UrlConfigGenerator;
import com.sniff.main.actions.init_base_sniffers.generators.VolleyUtilsGenerator;
import com.sniff.main.actions.init_base_sniffers.templates.Templates;
import com.sniff.main.base.SniffBaseCreationController;

import org.omg.CORBA.portable.ApplicationException;

import java.io.File;

/**
 * Created by ivan on 7.12.15.
 */
public class SniffInitCreationController extends SniffBaseCreationController {

    private String mInitPath, mRequestsInitPath, mCoreInitPath, mParserInitPath;


    public SniffInitCreationController(String aPackage, String basePath) {
        super(aPackage, basePath);
        mInitPath = mProjectPath + "/model";
    }

    private void checkFileStructure() {
        checkOrCreateFolder(mInitPath);
        checkOrCreateFolder(mInitPath + "/utils");
        mInitPath += "/sniff";
        checkOrCreateFolder(mInitPath);
        mRequestsInitPath = mInitPath + "/request";
        checkOrCreateFolder(mRequestsInitPath);
        mCoreInitPath = mInitPath + "/core";
        checkOrCreateFolder(mCoreInitPath);
        mParserInitPath = mInitPath + "/parser";
        checkOrCreateFolder(mParserInitPath);
    }

    private void createApplicationException() {
        String path = mCoreInitPath + "/ApplicationException.java";
        ApplicationExceptionGenerator generator = new ApplicationExceptionGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createVolleyUtils() {
        String path = mCoreInitPath + "/VolleyUtils.java";
        VolleyUtilsGenerator generator = new VolleyUtilsGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createUrlConfig() {
        String path = mProjectPath + "/model/utils/UrlConfig.java";
        UrlConfigGenerator generator = new UrlConfigGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createRequestHandler() {
        String path = mInitPath + "/RequestHandler.java";
        RequestHandlerGenerator generator = new RequestHandlerGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createHandledRequest() {
        String path = mRequestsInitPath + "/HandledRequest.java";
        HandledRequestGenerator generator = new HandledRequestGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createHandledStringRequest() {
        String path = mRequestsInitPath + "/HandledStringRequest.java";
        HandledStringRequestGenerator generator = new HandledStringRequestGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createResponseParser() {
        String path = mParserInitPath + "/ResponseParser.java";
        ResponseParserGenerator generator = new ResponseParserGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createStringResponseParser() {
        String path = mParserInitPath + "/StringResponseParser.java";
        StringResponseParserGenerator generator = new StringResponseParserGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createLoadingListener() {
        String path = mCoreInitPath + "/LoadingListener.java";
        LoadingListenerGenerator generator = new LoadingListenerGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void createResponseListener() {
        String path = mCoreInitPath + "/ResponseListener.java";
        ResponseListenerGenerator generator = new ResponseListenerGenerator(mProjectPath, mPackage, mProjectPath + "/model");
        String body = generator.generate();
        writeToFile(path, body);
    }

    private void addStringResources() {
        String content = readFile(mBasePath + "/app/src/main/res/values/strings.xml");
        if (!content.contains(Templates.sResourcesErrorsStringsInit))
            if (content != null) {
                content = content.replaceFirst("<resources>", Templates.sResourcesErrorsStringsInit);
                safeWriteToFile(mBasePath + "/app/src/main/res/values/strings.xml", "strings.xml", content);
            }
    }

    public boolean create() {
        checkFileStructure();
        createApplicationException();
        createHandledRequest();
        createHandledStringRequest();
        createResponseParser();
        createStringResponseParser();
        createRequestHandler();
        createVolleyUtils();
        createUrlConfig();
        createLoadingListener();
        createResponseListener();
        addStringResources();
        return true;
    }

    public static final String cPackage = "#c:package#";
}
