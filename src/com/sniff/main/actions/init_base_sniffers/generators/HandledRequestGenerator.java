package com.sniff.main.actions.init_base_sniffers.generators;

import com.sniff.main.actions.init_base_sniffers.SniffInitCreationController;
import com.sniff.main.actions.init_base_sniffers.templates.Templates;
import com.sniff.main.base.SniffBaseInitGenerator;

/**
 * Created by ivan on 9.12.15.
 */
public class HandledRequestGenerator extends SniffBaseInitGenerator {

    public HandledRequestGenerator(String projectPath, String aPackage, String modelPath) {
        super(projectPath, aPackage, modelPath);
    }

    @Override
    public String generate() {
        return Templates.sHandledRequestBody.replace(SniffInitCreationController.cPackage, mPackage);
    }
}
