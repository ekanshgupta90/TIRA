/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquorn.ui;

import aquorn.dto.CombinedDto;
import aquorn.filegenerator.ExportToCSV;
import aquorn.processor.DataIntegrator;
import java.util.List;

/**
 *
 * @author ekansh_gupta
 */
public class TIRAExecute {
    
    /**
     * This method is used to run the application in Command line.
     * @param args 4/5 inputs all strings.
     */
    public static void main(String args[]) {
        if (args.length >= 4) {
            String jiraURL = args[0];
            String togglPath = args[1];
            String username = args[2];
            String password = args[3];
            String outputPath = "";
            if (args.length == 5 && args[5] != null) {
                outputPath = args[5];
            } 
            System.out.println("Starting TIRA Toggl and JIRA data merge process");
            List<CombinedDto> data = DataIntegrator.integrateDataFromJIRARss(jiraURL, togglPath, username, password);
            ExportToCSV.exportCombinedData(data, outputPath);
            System.out.println("All Done! Exiting TIRA");
        } else {
            System.err.println("This run needs 4 or 5 input arguments \nJIRA RSS URL, "
                    + "\nToggl CSV name, "
                    + "\nJIRA username, "
                    + "\nJIRA password, "
                    + "\n[optional] Output Path!");
        }
        
        
        
    }
}
