package aquorn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is used to convert dates and times.
 * @author ekansh_gupta
 */
public class DateUtils {
    /**
     * The date format to be used for inserting data to csv output.
     */
    private static final String DATEFORMAT = "MM-dd-yy";
    /**
     * The date time format to be used for inserting data to csv output.
     */
    private static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
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
     * @return String
     */
    public static String convertDateTimeFromDateToString(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATETIMEFORMAT);
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
        throw new UnsupportedOperationException();
    }
}
