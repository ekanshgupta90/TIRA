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
    public static String exportCombinedData(List<CombinedDto> exportList, String path) {
        String fileName = BaseConstants.OUTPUT_FILE_NAME + DateUtils.convertDateTimeFromDateToString(new Date(), true) + ".csv";
        File file = new File(fileName);
        try {
            System.out.println("##Writing the merged data to CSV file");
            if (!file.exists()) {
                file.createNewFile();
            }
            System.out.println("##Output File Name" + fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            writer.write(BaseConstants.OUTPUT_FILE_HEADER + "\n");
            for (CombinedDto dto : exportList) {
                writer.write(dto.toString());
            }
            writer.close();
            System.out.println("##Data write complete!");
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return "";
    }
    
    public static void main (String[] args) {
        ExportToCSV export = new ExportToCSV();
        try {
            String url = "https://aquorn.atlassian.net/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=&tempMax=1000";
            List<CombinedDto> CombinedList = 
                    DataIntegrator.integrateDataFromJIRARss(url, "data/toggl.csv", "egupta", "Reg.3354");
            export.exportCombinedData(CombinedList, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
