package org.joget.marketplace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.joget.apps.app.model.DefaultHashVariablePlugin;
import org.joget.apps.app.service.AppUtil;
import org.joget.commons.util.StringUtil;
import org.joget.workflow.model.service.WorkflowManager;

public class UserAssignmentCountHashVariable extends DefaultHashVariablePlugin {

    @Override
    public String processHashVariable(String variableKey) {
        WorkflowManager workflowManager = (WorkflowManager) AppUtil.getApplicationContext().getBean("workflowManager");
        
        String appId = null;
        String processDefId = null;
        String activityDefId = null;
        if (variableKey.contains("[") && variableKey.contains("]")) {
            final String queryString = variableKey.substring(variableKey.indexOf("[") + 1, variableKey.indexOf("]"));
            final Map<String, String[]> parameters = StringUtil.getUrlParams(queryString);
            
            if (parameters.containsKey("appId")) {
                appId = parameters.get("appId").length > 0 
                        ? (parameters.get("appId"))[0] 
                        : null;
            }
            if (parameters.containsKey("processDefId")) {
                processDefId = parameters.get("processDefId").length > 0 
                        ? "%" + (parameters.get("processDefId"))[0]
                        : null;
            }
            if (parameters.containsKey("activityDefId")) {
                activityDefId = parameters.get("activityDefId").length > 0 
                        ? (parameters.get("activityDefId"))[0] 
                        : null;
            }
        }
        String username = workflowManager.getWorkflowUserManager().getCurrentUsername();
        
        if (variableKey.contains("runningCount")) {
            return String.valueOf(
                    workflowManager.getAssignmentSize(
                            appId,
                            processDefId,
                            null,
                            activityDefId
                    )
            );
        } else if (variableKey.contains("completedCount")) {
            int completed = workflowManager.getCompletedProcessSize(
                appId,
                processDefId,
                null,
                null,
                null,
                username 
            );

            return String.valueOf(completed);
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
        return "8.0.3";
    }

    @Override
    public String getDescription() {
        return "Retrieves the assignment count for the current logged in user, either by total count, or filtered by app ID, process definition ID, activity definition ID.";
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
        syntax.add("loggedInUser.runningCount[appId=APP_ID&processDefId=PROCESS_DEF_ID&activityDefId=ACTIVITY_DEF_ID]");
        syntax.add("loggedInUser.completedCount");
        syntax.add("loggedInUser.completedCount[appId=APP_ID&processDefId=PROCESS_DEF_ID]");
        return syntax;
    }
}
