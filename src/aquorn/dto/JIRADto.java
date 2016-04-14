package aquorn.dto;

import aquorn.constants.BaseConstants;

/**
 * This Data transfer object will store the 
 * data extracted from the JIRA xml.
 * @author ekansh_gupta
 */
public class JIRADto {
    /**
     * Default construction for the class.
     */
    public JIRADto() {
        this.key = null;
    }
    /**
     * Key for an issue.
     */
    private String key;
    /**
     * due date for an issue.
     */
    private String dueDate;
    /**
     * created date for an issue.
     */
    private String created;
    /**
     * updated date for an issue.
     */
    private String updated;
    /**
     * estimated time for an issue.
     */
    private String estimatedTime;
    /**
     * status for an issue.
     */
    private String status;
    /**
     * description for an issue.
     */
    private String description;
    /**
     * Person assigned for an issue.
     */
    private String assignee;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the dueDate
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the created
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return the updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     * @return the estimatedTime
     */
    public String getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * @param estimatedTime the estimatedTime to set
     */
    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    
    /**
     * Method to return a combined string.
     * @return a string.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(BaseConstants.JIRA_KEY + ":");
        builder.append(this.key + "\t");
        builder.append(BaseConstants.JIRA_ASSIGNEE + ":");
        builder.append(this.assignee + "\t");
        builder.append(BaseConstants.JIRA_CREATED_DATE + ":");
        builder.append(this.created + "\t");
        builder.append(BaseConstants.JIRA_UPDATED_DATE + ":");
        builder.append(this.updated + "\t");
        builder.append(BaseConstants.JIRA_DUE_DATE + ":");
        builder.append(this.dueDate + "\t");
        builder.append(BaseConstants.JIRA_STATUS + ":");
        builder.append(this.status + "\t");
        builder.append(BaseConstants.JIRA_TIME_ESTIMATE + ":");
        builder.append(this.estimatedTime + "\t");
        builder.append(BaseConstants.JIRA_DESC + ":");
        builder.append(this.description + "\t");
        return builder.toString();
    }
}
