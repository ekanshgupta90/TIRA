package aquorn.dto;

import aquorn.constants.BaseConstants;
import aquorn.utils.DateUtils;

/**
 * This data transfer class stores the combined data of Toggl and JIRA.
 * The method extends togglDto because we need to keep all the items from 
 * Toggl.
 * @author ekansh_gupta
 */
public class CombinedDto extends TogglDto {
    /**
     * The default constructor initializes the super class constructor.
     */
    public CombinedDto() {
        super();
    }
    /**
     * A constructor to initiate the values of the TogglDto with the
     * input variable.
     * @param togglDto to equate the values.
     */
    public CombinedDto(TogglDto toggl) {
        setUser(toggl.getUser());
        setDescription(toggl.getDescription());
        setStartDate(toggl.getStartDate());
        setStartTime(toggl.getStartTime());
        setEndDate(toggl.getEndDate());
        setEndTime(toggl.getEndTime());
        setDuration(toggl.getDuration());
        setTags(toggl.getTags());
        setWeek((DateUtils.getWeekNumberFromStartingDate(BaseConstants.SEM_START_DATE, toggl.getStartDate()) + 1) + "");
    }
    /**
     * Key for an issue.
     */
    private String key;
    /**
     * Is the issue completed.
     */
    private boolean isCompleted;
    /**
     * Week number of the task.
     */
    private String week;
    /**
     * Status of the issue.
     */
    private String status;
    /**
     * Time allocated in JIRA.
     */
    private String allocatedTime;
    /**
     * Stores the due date for the issue.
     */
    private String dueDate;
    
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
     * @return the isCompleted
     */
    public boolean isIsCompleted() {
        return isCompleted;
    }

    /**
     * @param isCompleted the isCompleted to set
     */
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * @return the week
     */
    public String getWeek() {
        return week;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(String week) {
        this.week = week;
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
     * @return the allocatedTime
     */
    public String getAllocatedTime() {
        return allocatedTime;
    }

    /**
     * @param allocatedTime the allocatedTime to set
     */
    public void setAllocatedTime(String allocatedTime) {
        this.allocatedTime = allocatedTime;
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
     * Overriding to string method.
     * @return String 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getWeek() + ",");
        builder.append(getStartDate() + ",");
        builder.append(getStartTime() + ",");
        builder.append(getEndDate() + ",");
        builder.append(getEndTime() + ",");
        builder.append(getDuration() + ",");
        builder.append(getUser() + ",");
        builder.append(getDescription() + ",");
        builder.append(getAllocatedTime() + ",");
        builder.append(getDueDate() + ",");
        builder.append(getKey() + ",");
        builder.append(getStatus() + ",");
        builder.append(getTags() + "\n");
        
        return builder.toString();
    }    
}
