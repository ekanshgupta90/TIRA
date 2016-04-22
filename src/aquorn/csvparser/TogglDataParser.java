package aquorn.csvparser;

import aquorn.dto.TogglDto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
        String[] split = line.split("\\\"", -1);
        List<String> actualList = new ArrayList<>();
        boolean inQuotes = false;
        for (int i = 0; i < split.length; i++) {
            if (!inQuotes) {
                String[] str = split[i].split(",");
                for (int j = 0; j < str.length; j++) {
                   actualList.add(str[j]);
                }
                inQuotes = true;
            } else {
                actualList.add("\"" + split[i] + "\"");
                inQuotes = false;
            }
        }
        
        togglDto.setUser(actualList.get(0));
        togglDto.setDescription(actualList.get(5));
        togglDto.setStartDate(actualList.get(7));
        togglDto.setStartTime(actualList.get(8));
        togglDto.setEndDate(actualList.get(9));
        togglDto.setEndTime(actualList.get(10));
        togglDto.setDuration(actualList.get(11));
        togglDto.setTags(actualList.get(12));
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
            System.out.println("####Checking for Toggl file exist at " + fileName);
            if (!file.exists()) {
                System.err.println("Error: Toggl file not found!");
                return new ArrayList<>();
            }
            System.out.println("####Reading data from the Toggl file");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String line = "";
            System.out.println("####Extracting data from the Toggl data");
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
            System.out.println("####Toggl data extraction complete!");
        } catch (Exception e) {
            LOGS.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        
        return csvOutput;
    }
    
    public static void main(String args[]) {
        List<TogglDto> list = TogglDataParser.extractDataFromCSV("data/toggl.csv");
        int i = 1;
        for (TogglDto dto : list) {
            System.out.println((i++) + ". " + dto.print());
        }
    }
}
