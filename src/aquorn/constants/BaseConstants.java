/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquorn.constants;

/**
 * All the constant values that need to extracted from the xml and csv.
 * @author ekansh_gupta
 */
public class BaseConstants {
    /***************************************************************************
     * JIRA Constants - START
     **************************************************************************/
    /**
     * An issue's due date.
     */
    public static String JIRA_DUE_DATE = "duedate";
    /**
     * An issue's key.
     */
    public static String JIRA_KEY = "key";
    /**
     * An issue's status.
     */
    public static String JIRA_STATUS = "status";
    /**
     * An issue's assigned person.
     */
    public static String JIRA_ASSIGNEE = "assignee";
    /**
     * An issue's description.
     */
    public static String JIRA_DESC = "description";
    /**
     * An issue's updated date.
     */
    public static String JIRA_UPDATED_DATE = "updated";
    /**
     * An issue's created date.
     */
    public static String JIRA_CREATED_DATE = "created";
    /**
     * An issue's estimated time.
     */
    public static String JIRA_TIME_ESTIMATE = "timeoriginalestimate";
    /***************************************************************************
     * JIRA Constants - END
     **************************************************************************/
    
    /***************************************************************************
     * Toggl Constants - START
     **************************************************************************/
    /**
     * User who enters the time tracking task.
     */
    public static String TOGGL_USER = "user";
    /**
     * Description of the task.
     */
    public static String TOGGL_DESC = "description";
    /**
     * Start date for a task.
     */
    public static String TOGGL_START_DATE = "start_date";
    /**
     * End date for a task.
     */
    public static String TOGGL_END_DATE = "end_date";
    /**
     * Start time for a task.
     */
    public static String TOGGL_START_TIME = "start_time";
    /**
     * End time for a task.
     */
    public static String TOGGL_END_TIME = "end_time";
    /**
     * Total duration for a task.
     */
    public static String TOGGL_DURATION = "duration";
    /**
     * All the associated tags separated by commas.
     */
    public static String TOGGL_TAGS = "tags";
    /***************************************************************************
     * Toggl Constants - END
     **************************************************************************/
    /**
     * The file name used for exporting.
     */
    public static String OUTPUT_FILE_NAME = "Toggl_JIRA_Combine_";
    /**
     * 
     */
    public static String OUTPUT_FILE_HEADER = "WEEK,START DATE,START TIME,END DATE,END TIME,DURATION,USER,DESCRIPTION,ALLOCATED TIME,DUE DATE,JIRA ID,JIRA STATUS,TAGS";
}
