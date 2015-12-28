package com.sniff.main.actions.add_new_model;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.sniff.main.actions.add_new_model.generators.SniffMethodGenerator;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;

/**
 * Created by ivan on 7.12.15.
 */
public class AddNewModelAction extends AnAction {

    private String mName, mPackage;
    private ArrayList<SniffMethodGenerator> mMethods;

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        InputValidator iv = new InputValidator() {
            @Override
            public boolean checkInput(String s) {
                return true;
            }

            @Override
            public boolean canClose(String s) {
                return true;
            }
        };

        String args = Messages.showMultilineInputDialog(project, "Specify required values. Supports multiple loading functions, place them one in one row", "Add new model", mInitialText, new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {

            }

            @Override
            public int getIconWidth() {
                return 0;
            }

            @Override
            public int getIconHeight() {
                return 0;
            }
        }, iv);
        parseArguments(args);
        SniffModelCreationController generator = new SniffModelCreationController(project.getBaseDir().getPath(), mPackage, mName, mMethods);
        generator.create();
    }
    
    private void parseArguments(String argument){
            String [] args = argument.split("\\n");
            mMethods = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if(args[i].contains("model_name")){
                    String [] nameItems = args[i].split("=");
                    mName = nameItems[1];
                } else if (args[i].contains("package_name")){
                    String [] packageItems = args[i].split("=");
                    mPackage = packageItems[1];
                } else if (args[i].contains("function")){
                    String [] functionItems = args[i].split("=");
                    String [] functionArgs = functionItems[1].split("&");
                    SniffMethodGenerator descriptor = new SniffMethodGenerator(mName, mPackage, functionArgs[0], functionArgs[1]);
                    mMethods.add(descriptor);
                }
        }
    }

    private static final String mInitialText = "model_name=model_name\n" +
            "package_name=cz.anywhere.name\n" +
            "function=name&return_value_type\n";
}
