package aquorn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * This class is used to convert dates and times.
 * @author ekansh_gupta
 */
public class DateUtils {
    /**
     * The date format to be used for inserting data to csv output.
     */
    private static final String DATEFORMAT = "yyyy-MM-dd";
    /**
     * The date format to be used for extracting data from Toggl.
     */
    private static final String TOGGL_DATEFORMAT = "yyyy-MM-dd";
    /**
     * The date time format to be used for inserting data to csv output.
     */
    private static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * The date time format to be used for inserting data to csv output.
     */
    private static final String DATETIMEFORMAT4FILE = "yyyy-MM-dd HH-mm-ss";
    /**
     * The time format to be used for inserting data to csv output.
     */
    private static final String TIMEFORMAT = "HH:mm:ss";
    /**
     * Inputs a date string and outputs long.
     * @param dateStr
     * @return long
     */
    public static Long convertDateFromStringToLong(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
            Date date = formatter.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Error in parsing date.");
        }
    }
    
    /**
     * Inputs a date and time string and output long.
     * @param dateStr
     * @return long
     */
    public static Long convertDateTimeFromStringToLong(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATETIMEFORMAT);
            Date date = formatter.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Error in parsing date.");
        }
    }
    
    /**
     * Inputs a date and output String.
     * @param date
     * @return String
     */
    public static String convertDateFromDateToString(Date date) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Inputs a date time and output String.
     * @param date
     * @param ForFileName
     * @return String
     */
    public static String convertDateTimeFromDateToString(Date date, boolean forFileName) {
        try {
            SimpleDateFormat formatter;
            if (forFileName) {
                formatter = new SimpleDateFormat(DATETIMEFORMAT4FILE);
            } else {
                formatter = new SimpleDateFormat(DATETIMEFORMAT);
            }
            return formatter.format(date);
        } catch (Exception e) {
            throw new RuntimeException("Error in parsing date.");
        }
    }
    
    /**
     * Inputs a date string and output a date.
     * @param dateStr
     * @return date
     */
    public static Date convertDateFromStringToDate(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
            Date date = formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException("Error in parsing date.");
        }
    }
    
    /**
     * Inputs 2 dates as strings (only date without time) and returns the 
     * number of weeks between them.
     * @param startDate the date from which you are starting.
     * @param targetDate to the date which you want to find.
     * @return int as number of weeks between them.
     */
    public static int getWeekNumberFromStartingDate(String startDate, String targetDate) {
        try {
            Date start = DateUtils.convertDateFromStringToDate(startDate);
            Date end = DateUtils.convertDateFromStringToDate(targetDate);
            LocalDate lStart = new LocalDate(start.getTime());
            LocalDate lEnd = new LocalDate(end.getTime());
            int days = Days.daysBetween(lStart, lEnd).getDays();
            if (days < 0) {
                return 0;
            }
            return days / 7;
        } catch (Exception e) {
            throw new DateTimeException(e.toString());
        }
    }
}
