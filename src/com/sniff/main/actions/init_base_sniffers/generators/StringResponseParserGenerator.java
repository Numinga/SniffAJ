package com.sniff.main.actions.init_base_sniffers.generators;

import com.sniff.main.actions.init_base_sniffers.SniffInitCreationController;
import com.sniff.main.actions.init_base_sniffers.templates.Templates;
import com.sniff.main.base.SniffBaseInitGenerator;

/**
 * Created by ivan on 9.12.15.
 */
public class StringResponseParserGenerator  extends SniffBaseInitGenerator {

    public StringResponseParserGenerator(String projectPath, String aPackage, String modelPath) {
        super(projectPath, aPackage, modelPath);
    }

    @Override
    public String generate() {
        return Templates.sStringResponseParserBode.replace(SniffInitCreationController.cPackage, mPackage);
    }
}
