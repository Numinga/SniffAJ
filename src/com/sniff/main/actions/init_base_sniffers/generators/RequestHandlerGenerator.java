package com.sniff.main.actions.init_base_sniffers.generators;

import com.sniff.main.actions.init_base_sniffers.SniffInitCreationController;
import com.sniff.main.actions.init_base_sniffers.templates.Templates;
import com.sniff.main.base.SniffBaseInitGenerator;

/**
 * Created by ivan on 7.12.15.
 */
public class RequestHandlerGenerator  extends SniffBaseInitGenerator {

    public RequestHandlerGenerator(String projectPath, String aPackage, String modelPath) {
        super(projectPath, aPackage, modelPath);
    }

    @Override
    public String generate() {
        return Templates.sRequestHandlerBody.replace(SniffInitCreationController.cPackage, mPackage);
    }
}