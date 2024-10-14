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
        ApplicationContext appContext = AppUtil.getApplicationContext();
        WorkflowUserManager workflowUserManager = (WorkflowUserManager) appContext.getBean("workflowUserManager");
        WorkflowManager workflowManager = (WorkflowManager) WorkflowUtil.getApplicationContext().getBean("workflowManager");
        
        String appId = null;
        if (variableKey.contains("[") && variableKey.contains("]")) {
            appId = variableKey.substring(variableKey.indexOf("[") + 1, variableKey.indexOf("]"));
        }
        
        if (variableKey.contains("runningCount")) {
            return String.valueOf(
                    workflowManager.getAssignmentSize(
                            appId != null ? appId : null, 
                            null, 
                            null
                    )
            );
        } else if (variableKey.contains("completedCount")) {
            return String.valueOf(
                    workflowManager.getCompletedProcessSize(
                            appId != null ? appId : null, 
                            null, 
                            null, 
                            null, 
                            null, 
                            workflowUserManager.getCurrentUsername()
                    )
            );
        }
        
        return null;
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
        return "8.0.1";
    }

    @Override
    public String getDescription() {
        return "Retrieves the assignment count for the current logged in user, either by total count or filtered by app ID.";
    }

    @Override
    public String getLabel() {
        return getName();
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
        syntax.add("loggedInUser.runningCount[APP_ID]");
        syntax.add("loggedInUser.completedCount");
        syntax.add("loggedInUser.completedCount[APP_ID]");
        return syntax;
    }
}
