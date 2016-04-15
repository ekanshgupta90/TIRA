package aquorn.csvparser;

import aquorn.dto.TogglDto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class extracts data from a Toggl csv and 
 * puts it in a TogglDTO object.
 * Methods - extractDataFromCSV.
 * @author ekansh_gupta
 */
public class TogglDataParser {
    
    /**
     * name of the class.
     */
    private static String CLASSNAME = TogglDataParser.class.getName();
    /**
     * Logger for logging exceptions.
     */
    private static final Logger LOGS = Logger.getLogger(CLASSNAME);
    
    /**
     * The method reads a line and returns a equivalent TogglDto.
     * @param line with comma separated values from CSV.
     * @return a TogglDto format data.
     */
    private static TogglDto getDataFromLine(String line) {
        TogglDto togglDto = new TogglDto();
        String[] split = line.split(",", -1);
        for (int i = 0; i < split.length; i++) {
            togglDto.setUser(split[0]);
            togglDto.setDescription(split[5]);
            togglDto.setStartDate(split[7]);
            togglDto.setStartTime(split[8]);
            togglDto.setEndDate(split[9]);
            togglDto.setEndTime(split[10]);
            togglDto.setDuration(split[11]);
            togglDto.setTags(split[12]);
        }
        return togglDto;
    }
    
    /**
     * This method is used for extracting information 
     * from a Toggl csv.
     * @param fileName of the csv to be extracted.
     * @return a map with a TogglDTO.
     */
    public static List<TogglDto> extractDataFromCSV(String fileName) {
        List<TogglDto> csvOutput = new LinkedList<>();
        File file = new File(fileName);
        boolean isFirst = true;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                TogglDto togglDto = getDataFromLine(line);
                if (togglDto.getDescription() != null && 
                        !togglDto.getDescription().isEmpty()) {
                    csvOutput.add(togglDto);
                }
            }
            
            reader.close();
        } catch (Exception e) {
            LOGS.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        
        return csvOutput;
    }
}
