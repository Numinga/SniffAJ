package com.sniff.main.actions.init_base_sniffers;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.impl.Message;

/**
 * Created by ivan on 7.12.15.
 */
public class InitBaseClassesAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        String arg = Messages.showInputDialog(project, "Enter project package: ", "Initializing base classes", null);
        SniffInitCreationController generator = new SniffInitCreationController(arg, project.getBaseDir().getPath());
        generator.create();
    }
}
