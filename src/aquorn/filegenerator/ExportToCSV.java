package aquorn.filegenerator;

import aquorn.constants.BaseConstants;
import aquorn.dto.CombinedDto;
import aquorn.processor.DataIntegrator;
import aquorn.utils.DateUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

/**
 * This file converts data to csv and exports it.
 * @author ekansh_gupta
 */
public class ExportToCSV {
    /**
     * The method is used to export the data to a csv.
     * @param exportList
     * @param path
     * @return 
     */
    public String exportCombinedData(List<CombinedDto> exportList, String path) {
        String fileName = BaseConstants.OUTPUT_FILE_NAME + DateUtils.convertDateTimeFromDateToString(new Date(), true) + ".csv";
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            writer.write(BaseConstants.OUTPUT_FILE_HEADER + "\n");
            for (CombinedDto dto : exportList) {
                writer.write(dto.toString());
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return "";
    }
    
    public static void main (String[] args) {
        ExportToCSV export = new ExportToCSV();
        try {
            List<CombinedDto> CombinedList = 
                    DataIntegrator.integrateData("data/entities.xml", "data/toggl.csv");
            export.exportCombinedData(CombinedList, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
