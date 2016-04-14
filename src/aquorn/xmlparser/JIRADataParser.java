package aquorn.xmlparser;

import aquorn.constants.BaseConstants;
import aquorn.dto.JIRADto;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This file reads data from a JIRA xml and gets the issue data.
 * Methods - extractDataFromXML
 * @author ekansh_gupta
 */
public class JIRADataParser {
    
    /**
     * static variable storing the class name.
     */
    private static final String CLASSNAME = JIRADataParser.class.getName();
    
    /**
     * Java Util logger to log all the exceptions.
     */
    private static final Logger LOGS = Logger.getLogger(CLASSNAME);
    
    /**
     * Reading an xml file and converting it to a document.
     * @param fileName filename with path for the file to be read.
     * @return Document object for the xml.
     */
    private Document readFileData(String fileName) {
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            return builder.parse(xmlFile);
        } catch (Exception e) {
            LOGS.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    /**
     * Extracting information from each issue individually.
     * @param element xml element to extract information from.
     * @return JIRADto object to be put in the Map.
     */
    private JIRADto getDataFromElement(Element element) {
        JIRADto jiraDto = new JIRADto();
        if (element.hasAttribute(BaseConstants.JIRA_KEY)) {
            jiraDto.setKey(element.getAttribute(BaseConstants.JIRA_KEY));
        }
        if (element.hasAttribute(BaseConstants.JIRA_DUE_DATE)) {
            jiraDto.setDueDate(element.getAttribute(BaseConstants.JIRA_DUE_DATE));
        }
        if (element.hasAttribute(BaseConstants.JIRA_UPDATED_DATE)) {
            jiraDto.setUpdated(element.getAttribute(BaseConstants.JIRA_UPDATED_DATE));
        }
        if (element.hasAttribute(BaseConstants.JIRA_CREATED_DATE)) {
            jiraDto.setCreated(element.getAttribute(BaseConstants.JIRA_CREATED_DATE));
        }
        if (element.hasAttribute(BaseConstants.JIRA_DESC)) {
            jiraDto.setDescription(element.getAttribute(BaseConstants.JIRA_DESC));
        }
        if (element.hasAttribute(BaseConstants.JIRA_ASSIGNEE)) {
            jiraDto.setAssignee(element.getAttribute(BaseConstants.JIRA_ASSIGNEE));
        }
        if (element.hasAttribute(BaseConstants.JIRA_STATUS)) {
            jiraDto.setStatus(element.getAttribute(BaseConstants.JIRA_STATUS));
        }
        if (element.hasAttribute(BaseConstants.JIRA_TIME_ESTIMATE)) {
            jiraDto.setEstimatedTime(element.getAttribute(BaseConstants.JIRA_TIME_ESTIMATE));
        }
        return jiraDto;
    }
    
    /**
     * The method extracts information from a JIRA xml to be be mapped
     * to toggl data.
     * @param fileName filename with path for the file to be read.
     * @return Map for issueId and JIRADto.
     */
    public Map<String, JIRADto> extractDataFromXML(String fileName) {
        Map<String, JIRADto> xmlOutput = new HashMap<>(101);
        try {
            Document document = readFileData(fileName);
            document.getDocumentElement().normalize();
            
            NodeList nodeList = document.getElementsByTagName("Issue");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.hasAttribute(BaseConstants.JIRA_DUE_DATE)) {
                        JIRADto jiraDto = getDataFromElement(element);
                        if (jiraDto.getKey() != null) {
                            xmlOutput.put(jiraDto.getKey(), jiraDto);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGS.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return xmlOutput;
    }
    
    //TODO Remove the main from this file.
    public static void main(String args[]) {
        JIRADataParser dataParser = new JIRADataParser();
        Map<String, JIRADto> map = dataParser.extractDataFromXML("data/entities.xml");
        map.values().stream().forEach((value) -> {
            System.out.println(value.toString());
        });
    }
}
