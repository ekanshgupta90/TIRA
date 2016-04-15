package aquorn.processor;

import aquorn.csvparser.TogglDataParser;
import aquorn.dto.CombinedDto;
import aquorn.dto.JIRADto;
import aquorn.dto.TogglDto;
import aquorn.xmlparser.JIRADataParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class uses data extracted from Toggl and JIRA to combine into a 
 * single data object.
 * Methods - integrateData.
 * @author ekansh_gupta
 */
public class DataIntegrator {
    /**
     * 
     */
    private static final String CLASSNAME = DataIntegrator.class.getName();
    /**
     * 
     */
    private static final Logger LOGS = Logger.getLogger(CLASSNAME);
    
    /**
     * Method is used to merge the data from JIRA and Toggl.
     * @param jiraMap
     * @param togglMap 
     * @return List of combinedDTO from the merged data.
     */
    private static List<CombinedDto> mergeData(Map<String,JIRADto> jiraMap, List<TogglDto> togglList) {
        final String METHODNAME = "mergeData";
        
        List<CombinedDto> outputList = new LinkedList<>();
        
        for (TogglDto toggl : togglList) {
            CombinedDto dto = new CombinedDto(toggl);
            String description = dto.getDescription();
            for (String key : jiraMap.keySet()) {
                if (description.contains(key)) {
                    JIRADto jIRADto = jiraMap.get(key);
                    
                    dto.setKey(key);
                    dto.setStatus(jIRADto.getStatus());
                    dto.setAllocatedTime(jIRADto.getEstimatedTime());
                    dto.setDueDate(jIRADto.getDueDate());
                    dto.setIsCompleted(false);
                }
            }
            outputList.add(dto);
        }
        
        return null;
    }
    
    /**
     * 
     * @param jiraFileName filename with path for JIRA XML.
     * @param togglFileName filename with path for Toggl CSV.
     * @return List of CombinedDto with object containing info for all the data.
     */
    public static List<CombinedDto> integrateData(String jiraFileName, String togglFileName) {
        final String METHODNAME = "integrateData";
        
        JIRADataParser jiraParser = new JIRADataParser();
        TogglDataParser togglParser = new TogglDataParser();
        
        Map<String, JIRADto> jiraOutputMap = new HashMap<>(101);
        List<TogglDto> togglOutputList = new LinkedList<>();
        
        LOGS.log(Level.FINE, CLASSNAME + ":" + METHODNAME, "Trying to extract data from JIRA XML.");
        try {
            jiraOutputMap = jiraParser.extractDataFromXML(jiraFileName);
        } catch (RuntimeException e) {
            LOGS.log(Level.SEVERE, CLASSNAME + ":" + METHODNAME, "Failed to extract data from JIRA XML.");
            throw new RuntimeException(e.getMessage());
        }
        
        LOGS.log(Level.FINE, CLASSNAME + ":" + METHODNAME, "Trying to extract data from Toggl CSV.");
        try {
            togglOutputList = togglParser.extractDataFromCSV(togglFileName);
        } catch (RuntimeException e) {
            LOGS.log(Level.SEVERE, CLASSNAME + ":" + METHODNAME, "Failed to extract data from Toggl CSV.");
            throw new RuntimeException(e.getMessage());
        }
        
        Collections.sort(togglOutputList, new TogglDateWiseSort());
        
        return mergeData(jiraOutputMap, togglOutputList); 
    }
}
