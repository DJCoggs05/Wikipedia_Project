# Wikepedia Revision Parser
For this assigment we were tasked with creating an application that takes a Wikipedia title from the command line, and returns the twenty one most recent revisions in reverse chronological order. It will also tell you if any redirects were made. If there is an error, it will specify what error is being made and provide that to the user via a custom message. We were also instructed to ensure all code follows "Clean Code" principles we learned in class and from the book "Clean Code a Handbook of Agile Software Craftsmanship". Below is a general overview of how our project works.

<img width="566" alt="Screenshot 2025-02-07 at 8 55 05 AM" src="https://github.com/user-attachments/assets/6a8437d5-295e-4431-a941-849fc17fd7a7" />
<img width="532" alt="Screenshot 2025-02-07 at 8 59 57 AM" src="https://github.com/user-attachments/assets/9f93346c-7874-4cab-b631-97410e483ef0" />
<img width="681" alt="Screenshot 2025-02-07 at 8 55 30 AM" src="https://github.com/user-attachments/assets/d41f3a71-6cdc-496f-9ee1-d3f2d07ee14f" />

## Run Instructions
Command Line: To run this program please run the Main class outside of the GUI package!
GUI: To run this program please run the Main class inside of the GUI package. Make sure that you're running it through gradle or it will not run correctly. 


## Revision class
This class is a fundamental building block for the entire project. This contains two fields, a field for a username, and a field for a timestamp. This way we can further encapsulation and organization of our data.

## RevisionInputStream class
This class allows us to parse an Input Stream twice. Since we will need to parse the data more than once we will store the original input stream as a byte array. That way when we need to use it again we can easily look at the data and parse it again. 

### openInputStream method
This method allows us to easily open a ByteArrayInputStream. This allows to have a lot of flexibility to open an input stream multiple times so we can parse the data more than once. 

## RevisionParser class
The RevisionParser class is the class responsible for doing all the parsing related to the revisions. It has multiple methods to make this happen. In order to instantiate this object you must pass it an instance of the RevisionInputStream class we made earlier.

### Parse method
This method doesn't take any parameters. It will call another method called the extractRevisions method that will return a JSONArray of what we need. It will then call the convertRevisionsToList method that will return a list of objects of the Revision class, and return that value. 

### extractRevisions method
This method takes an input stream type. It will then read then extra the revisions from the input stream and return them as JSONArray.

### convertRevisionsToList method
This method will take a JSONArray. It will then loop through every item in that JSONArray and convert to a LinkedHashMap. After that it will extract the username and the timestamp and add it to a Revision object. It will then add that object to a list that will be returned. It does this process for every item in the JSONArray.

### extractRedirect method
This method takes in an input stream. It will then parse out whether the request was redirected. If there was no redirect found in the parse it will return an empty string. If there was a redirect found it will return the string such as "Redirected to {redirect found}"

## RevisionFormatter Class
This class is responsible for formatting all of our output. Each revision that needed to be printed needed to follow this exact format: "Line:  timestamp  username".

### printRevionList method
This method will take the list of revisions. It will loop through each revision and call the formatOutput method and combine that with the line number to get the desired output. 

### formatOutput method
This method will take a Revision object. It will then extract the revision timeStamp and the userName and return a string. The string will be the timestamp followed by two spaces, then the user name per the guidelines. 

## WikiConnection class
This class is responsible for pulling all of our data from Wikipedia. 

### createRequestUrl method
The method takes in a string. It will then use the URLEncoder class to encode the string. After the string is encoded it will add that title to the URL string and return it. 

### getInputStream method
This method will take in a URL string as a paraemter. It will then use the URL class to create a connection. The last thing this method will do is return the input stream of the request. 

## Menu class
This is the class that controls the running of the menu, and when all the other classes are called. It has four fields. An instance of the WikiConnection class, RevisionParser class, RevisionFormatterClass, and lastly the Scanner class. 

### runMenu method
Asks the user for a title. It will then grab the title and send it to the inputSearch method.

### inputSearch method
This will call the WikiConnection search method with will return us our input stream. It will then create an instance of the RevisionParser class and give it an instance of the RevisionInputStream class. The parameter of the RevisionInputStream constructor will be the input stream generated from the WikiConnection search method call. After that it will call the parse method and return the list of revisions. 

### inputSearchPrint method
This is a method to print our results of the inputSearch and if it was redirected.

## Main class
This class creates an instance of the menu class and calls it's runMenu method.

## Error Handling
We did our error handling by creating custom exceptions. We then threw those exceptions up to the view section to be handled there. That way we had good view model seperation between the view and the model.

# GUI Version

## GUI/Main Class
This class is the GUI alternative to our main and menu class from before. 

### start method
This method creates the first screen the user sees using JavaFX and creates a title using the createTitle method. It also creates an article label using the createArticleLabel method.

### switchScene method
This creates and switches the view to a new screen, creates the errorPopUp, and creates a title using the createTitle method. If there is an error, the errorPopUp is displayed to the user and a message is specified. The same functions from before are called to process the user's request and return the correct output. We then create an outputLabel using the createOutputLabel method. A spacer is added to ensure spacing is correct. A button is created to allow the user to return to the previous screen.

### createTitle method
This creates and customizes a title.

### createOutputLabel method
This creates and customizes an output label, and implements a scroll pane so the output does not go off screen.

### createGetRevisionsButton method
This creates and customizes the getRevisions button seen on the first screen. If this button is pressed it will attempt to switch to the second screen and process the users request the same way as in the non-GUI version.

### createArticleLabel method
This creates and customizes the Article Label.

## Authors by Alphabetical Order
Dakota Coughlin\
Nolan Meyer
