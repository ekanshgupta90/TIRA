package aquorn.xmlparser;

import aquorn.constants.BaseConstants;
import aquorn.dto.JIRADto;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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
     * The method checks for the validity of the url. 
     * @param url
     * @param username
     * @param password
     * @return A JIRA identifiable string.
     */
    private static String convertToJIRAURL(String url, String username, String password) {
        
        String[] urlSplit = url.split("\\?");
        try {
            if (urlSplit[0] == null || urlSplit[1] == null) {
                throw new MalformedURLException();
            }
        } catch (MalformedURLException e) {
            System.err.println("Error:" + e);
            throw new RuntimeException("The url dont seem right!");
        } 
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(urlSplit[0]);
        urlBuilder.append("?");
        urlBuilder.append("os_username=");
        urlBuilder.append(username);
        urlBuilder.append("&os_password=");
        urlBuilder.append(password);
        urlBuilder.append("&");
        urlBuilder.append(urlSplit[1]);
        url = urlBuilder.toString();
        return url;
    }
    /**
     * Reads JIRA RSS feed, one can be created using issue search.
     * @param url the url of the feed.
     * @param username the user who can view the feed.
     * @param password the password for the user.
     * @return xml document.
     */
    private static Document readRssFeed(String url) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document document;
            
            
            
            document = builder.parse(new URL(url).openStream());
            document.getDocumentElement().normalize();
            return document;
        } catch (Exception e) {
            System.err.println("Error" + e);
            throw new RuntimeException("The url did not work as excepted.", e.getCause());
        }
    }
    
    /**
     * Reading an xml file and converting it to a document.
     * @param fileName filename with path for the file to be read.
     * @return Document object for the xml.
     */
    @Deprecated
    private static Document readFileData(String fileName) {
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
     * The methods extracts data from an RSS feed item.
     * @param element which is item tag in the RSS.
     * @return JIRADDto of the required things.
     */
    private static JIRADto getDataFromItemsForRSS(Element element) {
        JIRADto jiraDto = new JIRADto();
        if (element.getElementsByTagName(BaseConstants.JIRA_KEY).getLength() > 0) {
            jiraDto.setKey(element.getElementsByTagName(BaseConstants.JIRA_KEY).item(0).getTextContent());
        }
        if (element.getElementsByTagName(BaseConstants.JIRA_ITEM_DUE_DATE).getLength() > 0) {
            jiraDto.setDueDate(element.getElementsByTagName(BaseConstants.JIRA_ITEM_DUE_DATE).item(0).getTextContent());
        }
        if (element.getElementsByTagName(BaseConstants.JIRA_UPDATED_DATE).getLength() > 0) {
            jiraDto.setUpdated(element.getElementsByTagName(BaseConstants.JIRA_UPDATED_DATE).item(0).getTextContent());
        }
        if (element.getElementsByTagName(BaseConstants.JIRA_CREATED_DATE).getLength() > 0) {
            jiraDto.setCreated(element.getElementsByTagName(BaseConstants.JIRA_CREATED_DATE).item(0).getTextContent());
        }
        if (element.getElementsByTagName(BaseConstants.JIRA_DESC).getLength() > 0) {
            jiraDto.setDescription(element.getElementsByTagName(BaseConstants.JIRA_DESC).item(0).getTextContent());
        }
        if (element.getElementsByTagName(BaseConstants.JIRA_ASSIGNEE).getLength() > 0) {
            jiraDto.setAssignee(element.getElementsByTagName(BaseConstants.JIRA_ASSIGNEE).item(0).getTextContent());
        }
        if (element.getElementsByTagName(BaseConstants.JIRA_STATUS).getLength() > 0) {
            jiraDto.setStatus(element.getElementsByTagName(BaseConstants.JIRA_STATUS).item(0).getTextContent());
        }
        if (element.getElementsByTagName(BaseConstants.JIRA_TIME_ESTIMATE).getLength() > 0) {
            jiraDto.setEstimatedTime(element.getElementsByTagName(BaseConstants.JIRA_TIME_ESTIMATE).item(0).getTextContent());
        }      
        return jiraDto;
    }
    
    /**
     * Extracting information from each issue individually.
     * @param element xml element to extract information from.
     * @return JIRADto object to be put in the Map.
     */
    @Deprecated
    private static JIRADto getDataFromElement(Element element) {
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
    @Deprecated
    public static Map<String, JIRADto> extractDataFromXML(String fileName) {
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
                            xmlOutput.put(jiraDto.getKey().toLowerCase(), jiraDto);
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
    
    /**
     * The method is user to extract data from a JIRA RSS feed.
     * @param url RSS feed url.
     * @param username valid username for the feed.
     * @param password valid password for the user.
     * @return Map of key as string and JIRADto with the rest of the information.
     */
    public static Map<String, JIRADto> extractDataFromRSSFeed(String url, String username, String password) {
        Map<String, JIRADto> xmlOutput = new HashMap<>(101);
        System.out.println("####Calling JIRA URL at - " + url);
        url = convertToJIRAURL(url, username, password);
        JIRADto jiraDto = new JIRADto();
        Document document = readRssFeed(url);
        System.out.println("####Extracting JIRA XML Data from RSS feed");
        if (document.hasChildNodes()) {
            NodeList itemList = document.getElementsByTagName("item");
            for (int i = 0; i < itemList.getLength(); i++) {
                if (itemList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    jiraDto = new JIRADto();
                    Element element = (Element) itemList.item(i);
                    jiraDto = getDataFromItemsForRSS(element);
                    xmlOutput.put(jiraDto.getKey().toLowerCase(), jiraDto);
                }
            }
        }
        System.out.println("####JIRA Data extraction complete!");
        return xmlOutput;
    }
}
