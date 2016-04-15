/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquorn.processor;

import aquorn.dto.TogglDto;
import aquorn.utils.DateUtils;
import java.util.Comparator;

/**
 *
 * @author ekansh_gupta
 */
public class TogglDateWiseSort implements Comparator<TogglDto>{

    @Override
    public int compare(TogglDto toggl1, TogglDto toggl2) {
        String toggl1DateTime = 
                toggl1.getStartDate() + " " + toggl1.getStartTime();
        
        String toggl2DateTime = 
                toggl2.getStartDate() + " " + toggl2.getStartTime();
        if (DateUtils.convertDateTimeFromStringToLong(toggl1DateTime) > 
                DateUtils.convertDateTimeFromStringToLong(toggl2DateTime)) {
            return 1;
        } else if (DateUtils.convertDateTimeFromStringToLong(toggl1DateTime) < 
                DateUtils.convertDateTimeFromStringToLong(toggl2DateTime)) {
            return -1;
        } else {
            return 0;
        }
    }
}
