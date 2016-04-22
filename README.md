# TIRA
The project is an attempt to combine Toggl and JIRA data to draw earn value charts.

#Running TIRA

Command-line run

Goto to the dist folder in the root directory. 

cd <path>/dist

Use the following java command

java -jar "JIRA URL" "Toggl CSV" "your username" "your password"

Example - 

java -jar TIRA.jar "https://aquorn.atlassian.net/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=&tempMax=1000" "../data/Toggl.csv" "username" "password"

#SETUP
This is a netbeans project. This has been created in netbean to make it easy to implement swings UI.
Import the code and open as existing project in Netbeans with Java 8.

#Folders

data - contains data files for testing

src - contains source code

dist - contains the latest build JAR

#Packages
Aquorn.Constants - All the constant values repeatedly used in the project. Like JIRA XML keys and Toggl column headers.

Aquorn.CSVParser - Has files to extract CSV data for Toggl.

Aquorn.DataStore - Will be used for writing data to files. This data can be csv created from the combined data and the configurations stored for the UI.

Aquorn.DTO - Contains data transfer objects.

Aquorn.XMLParser - Has files to extract XML data from JIRA.
