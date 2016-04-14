/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquorn.dto;

import aquorn.constants.BaseConstants;

/**
 * This data transfer object will store the data extracted from 
 * Toggl csv file.
 * @author ekansh_gupta
 */
public class TogglDto {
    /**
     * Default constructor for the class.
     * Initializes User to null, denoting that the class in empty.
     */
    public TogglDto() {
        this.user = null;
    }
    /**
     * Name of the user logging the time.
     */
    private String user;
    /**
     * description of the task performed.
     */
    private String description;
    /**
     * start date for the task.
     */
    private String startDate;
    /**
     * end date of the task.
     */
    private String endDate;
    /**
     * start time of the task.
     */
    private String startTime;
    /**
     * end time of the task.
     */
    private String endTime;
    /**
     * total duration of the task.
     */
    private String duration;
    /**
     * tags associated to the task.
     */
    private String tags;

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
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
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(BaseConstants.TOGGL_USER + ":");
        builder.append(this.user + "\t");
        builder.append(BaseConstants.TOGGL_DESC + ":");
        builder.append(this.description + "\t");
        builder.append(BaseConstants.TOGGL_START_DATE + ":");
        builder.append(this.startDate + "\t");
        builder.append(BaseConstants.TOGGL_START_TIME + ":");
        builder.append(this.startTime + "\t");
        builder.append(BaseConstants.TOGGL_END_DATE + ":");
        builder.append(this.endDate + "\t");
        builder.append(BaseConstants.TOGGL_END_TIME + ":");
        builder.append(this.endTime + "\t");
        builder.append(BaseConstants.TOGGL_DURATION + ":");
        builder.append(this.duration + "\t");       
        builder.append(BaseConstants.TOGGL_TAGS + ":");
        builder.append(this.tags + "\t");
        return builder.toString();
    }
}
