package org.joget.marketplace;

import java.util.ArrayList;
import java.util.Collection;
import org.joget.apps.app.model.DefaultHashVariablePlugin;
import org.joget.apps.app.service.AppUtil;
import org.joget.workflow.model.service.WorkflowManager;
import org.joget.workflow.model.service.WorkflowUserManager;
import org.joget.workflow.util.WorkflowUtil;
import org.springframework.context.ApplicationContext;

public class UserAssignmentCountHashVariable extends DefaultHashVariablePlugin {

    @Override
    public String processHashVariable(String variableKey) {
        int count = 0;
        ApplicationContext appContext = AppUtil.getApplicationContext();
        WorkflowUserManager workflowUserManager = (WorkflowUserManager) appContext.getBean("workflowUserManager");
        String username = workflowUserManager.getCurrentUsername();
        WorkflowManager workflowManager = (WorkflowManager) WorkflowUtil.getApplicationContext().getBean("workflowManager");
        if ("runningCount".equalsIgnoreCase(variableKey)) {
            count = workflowManager.getAssignmentSize(null, null, null);
        } else if ("completedCount".equalsIgnoreCase(variableKey)) {
            count = workflowManager.getCompletedProcessSize(null, null, null, null, null, username);
        }
        return String.valueOf(count);
    }

    @Override
    public String getPrefix() {
        return "loggedInUser";
    }

    @Override
    public String getName() {
        return "User Assignment Count Hash Variable";
    }

    @Override
    public String getVersion() {
        return "8.0.0";
    }

    @Override
    public String getDescription() {
        return "User Assignment Count Hash Variable";
    }

    @Override
    public String getLabel() {
        return "User Assignment Count Hash Variable";
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }

    @Override
    public String getPropertyOptions() {
        return "";
    }

    @Override
    public Collection<String> availableSyntax() {
        Collection<String> syntax = new ArrayList<>();
        syntax.add("loggedInUser.runningCount");
        syntax.add("loggedInUser.completedCount");
        return syntax;
    }
}
