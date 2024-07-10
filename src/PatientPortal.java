/****************************************************************************************
[Contributors]:
  - John Bostater
  
  - Tristan Andrade

  - Austin Mayhew

  - Ryan Clark

  - Juan Rosas Jr.

 	
[Description]:
  This file contains the relevant code for creating the Patient Portal.

  Upon a successful login by the user, the main page of the portal will be displayed

  From this Portal the Patient/User can use the following functionality


[Functionality]: 

  [Change Patient Information]
  
  [View Previous Visits]

  [Messages]
  
  [Logout]
  
	
[Aspect Ratio/Dimension of Graphical User Interface]: 

  - [4:3]	-->   [1024 x 768]	--	{Width x Height}

	
[Files Created/Used within the program for storing/loading data]:	  
  - PatientAccounts.txt 	
      {Contains: FirstName, LastName, DOB, Insurance Info, Contact Info}
    
  - VisitSummary.txt
      {Used for saving the patients data: [Insurance], [Contact], [etc.]}
	  
  - PatientVisits
      {Saved Patient Visit Information??}
	  
  - Messages.txt
      {Used for communication between parties}
        [New chats are added linearly (by nextLine();)]
***************************************************************************************/


//Relevant Java & JavaFX Libraries
//-----------------------------------------------
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//File I/O
import java.io.*;	//FileWriter for file writing
import java.util.*;	//Scanner for file reading
//-----------------------------------------------


public class PatientPortal{
    //Methods:		
    //		[Change Patient Information]	[View Previous Visits]	[Messages]	[Logout]

    //Patient's data (Will be loaded upon the 'Login' button being hit in the Patient "Login/New Account Page" {loaded from Welcome Page})
    //--------------------------------------------------        
      //Use Patient Credentials as a "key" for finding 
      //the specified patient's info within .txt files
        private String patientCredentials;

      //Full Name
        private String fullName = "";  //Ex; "John Smith"

      //Date Of Birth
        private String dateOfBirth;

      //Stage for displaying the relevant Scene(s)
        private Stage primeStage;
      
      //Load Welcome Scene & all of its functionality
        private Scene welcomePage;

      //String that holds the last message sent by the user
        String messageSent = "";
      //[Used for deleting last message sent]

      //Patient Information
        private String phoneNumber = "";
        private String insuranceInfo = "";
        private String pharmacyInfo = "";
    //--------------------------------------------------


    //Constructor
    //-----------------------------------------------------------------------------------------
      public PatientPortal(String patientCreds, Stage primaryStage, Scene welcomePg){
        //Take apart the userCredentials String using delimeters
        //Update the Patient's data with the following broken up data

        //Welcome Page that was created in Portals.java
          welcomePage = welcomePg;

        //Set the primary stage for displaying Scenes
          primeStage = primaryStage;

        //Set the patients credentials
          patientCredentials = patientCreds;


        //Break apart the patient's credentials to set the private data: fullName & dateOfBirth
        //Get the patients Full Name {first & last}
        
          //Delimeter used to break up the text
            int delimeter = patientCreds.indexOf(",");

          //First Name
            fullName += patientCreds.substring(0, delimeter);

          //Place Holder for the edited string
            String placeHolder = patientCreds.substring(delimeter + 1, patientCreds.length());

          //Last Name
            fullName += " " + placeHolder.substring(0, placeHolder.indexOf(","));

          //Set the string to null to help Garbage collector
            placeHolder = null;
      }
    //-----------------------------------------------------------------------------------------


    //Methods
    //------------------------------------------------------------------------------
    //Creates & Displays the Patient Portal Scene		
    //[NEW NOTE: this method will be used for every "exit" button within the other methods below]
      public void displayPortal() {
        //Debug Print
          //System.out.println("Login Successful!!");


        //Buttons
        //====================================================================================================================
          //Change Patient Information
            Button changePatientInfo = new Button("Change Patient Information");
            //Set the dimensions of the Button
           	  //[Width x Height]
           	    changePatientInfo.setPrefSize(385, 50);  
           	    changePatientInfo.setMaxSize(385, 50);
           	    changePatientInfo.setMinSize(385, 50);
              //Set the Font of the Button's text
                changePatientInfo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

          //View Previous Visits 
            Button viewPreviousVisits = new Button("View Previous Visits");
            //Set the dimensions of the Buttons
              //[Width x Height]
                viewPreviousVisits.setPrefSize(300, 50);  
                viewPreviousVisits.setMaxSize(300, 50);
                viewPreviousVisits.setMinSize(300, 50);
              //Set the Font of the Button's text
                viewPreviousVisits.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

          //Messages
            Button messageInbox = new Button("Messages");
            //Set the dimensions of the Buttons
              //[Width x Height]
                messageInbox.setPrefSize(150, 50);  
                messageInbox.setMaxSize(150, 50);
                messageInbox.setMinSize(150, 50);
              //Set the Font of the Button's text
                messageInbox.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

          //Logout	
            Button signOut = new Button("Logout");	
              //Set the dimensions of the Buttons
              //[Width x Height]
                signOut.setPrefSize(125, 50);  
                signOut.setMaxSize(125, 50);
                signOut.setMinSize(125, 50);
              //Set the Font of the Button's text
                signOut.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
        //====================================================================================================================

        //Labels/Text
        //==================================================================
          //Welcome!
            Label header0 = new Label("Welcome!");
             //Set the font of header
               header0.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: white;");

          //What would you  like to do today?
            Label text0 = new Label("What would you like to do today?");
              //Set the font of text
                text0.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");
        //==================================================================


        //Action-Event Handling
        //=============================================================================
          //Change Patient Information
            changePatientInfo.setOnAction(e -> {
              //Load the scene/method
                primeStage.setScene(changePatientInfo());
              //Display the Scene created
                primeStage.show();
            });


          //View Previous Visits
            viewPreviousVisits.setOnAction(e -> {
              //This will load a scene that has the DROPDOWN menu
              //Refer to code in [etc] for usage...
              //Load the View Previous Visits Page
                primeStage.setScene(previousVisits());

              //Display the Scene
                primeStage.show();
            });


          //Messages
            messageInbox.setOnAction(e -> {
              //This will also use the DROPDOWN menu...  [For the doctor actually]
              //Load & Display the Message(s) inbox Scene
                primeStage.setScene(messagePage());

              //Display the Scene
                primeStage.show();
            });


          //Logout
            signOut.setOnAction(e -> {
              //Set the stage to the Welcome page and run
                primeStage.setScene(welcomePage);
                primeStage.show();
            });
        //=============================================================================


        //Align the buttons, text, etc.
        //========================================================================================================
          //Alignment box for all of the text, buttons, etc.
            VBox alignBox0 = new VBox(25, header0, text0, changePatientInfo, viewPreviousVisits, messageInbox, signOut);

          //Set the alignment of the VBox for the Scene
            alignBox0.setAlignment(Pos.CENTER);
          
          //Set the background color of the page
            alignBox0.setStyle("-fx-background-color: #3A3A3A;");
        //========================================================================================================


        //Make the Scene, set the scene, and display it
        //Load the Patient Portal scene to be displayed
          Scene mainScene = new Scene(alignBox0, 1024, 768);
        
        //Set the primary/main Scene and displays it
          primeStage.setScene(mainScene);
          primeStage.show();
      }



    //Methods below will either return Scene or use primeStage to set the Scene upon button activation

    //Change Patient Information {Scene}
      private Scene changePatientInfo(){
        //You can exit this Scene via the Button "Exit"

        //Labels
        //======================================================================================================
          //Contact Information
            Label contactInfoLbl = new Label("Contact Information:");
              //Set font of Text
                contactInfoLbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");


          //Phone Number
            Label phoneNumLbl = new Label("Phone Number:");
              //Set font of Text
                phoneNumLbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");


          //Digit seperators for the phone number
            Label phoneDash0 = new Label("-");
              //Set the font of the Label
                phoneDash0.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
            
            Label phoneDash1 = new Label("-");
              //Set the font of the Label
                phoneDash1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");


          //Insurance Information
            Label insuranceInfoLbl = new Label("Insurance Information:");
              //Set font of Text
                insuranceInfoLbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

          //Insurance Information {Specifier text}
            Label insuranceInfoLbl0 = new Label("Insurance Provider:");
              //Set font of Text
                insuranceInfoLbl0.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");


          //Pharmaceutical Information
            Label pharmacyInfoLbl = new Label("Pharmaceutical Information:");
              //Set font of Text
                pharmacyInfoLbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

          //Pharmacy Information {Specifier text}
            Label pharmacyInfoLbl0 = new Label("Pharmacy Provider:");
              //Set font of Text
                pharmacyInfoLbl0.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        //======================================================================================================


        //Buttons
        //=============================================================================================================
          //Update Information (Saves to .txt file: PatientInfo.txt)
            Button updateInfo = new Button("Update Information");
              //Set the Dimensions & text of the button
              //[Width x Height]
    			  	  updateInfo.setPrefSize(250, 45);  
		            updateInfo.setMaxSize(250, 45);
		            updateInfo.setMinSize(250, 45);
		          //Set the Font of the Button's text
		            updateInfo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

          //Exit,   This will run the displayPortal() Method!!
            Button exitPage = new Button("Exit");
              //Set the Dimensions & text of the button
              //[Width x Height]
    			  	  exitPage.setPrefSize(75, 45);  
		            exitPage.setMaxSize(75, 45);
		            exitPage.setMinSize(75, 45);
		          //Set the Font of the Button's text
		            exitPage.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
        //=============================================================================================================


        //Text Boxes
        //==============================================================================
          //3 text boxes for the phone number...
          //Phone Number
            //First three Digits
            TextArea phoneNum0 = new TextArea();
              //Set the dimensions of the text area/box
                phoneNum0.setPrefSize(60, 40);
                phoneNum0.setMinSize(60, 40);
                phoneNum0.setMaxSize(60, 40);
              //Set the size of the text in the text box
                phoneNum0.setStyle("-fx-font-size: 18px;");
              //Clear the text box when the user clicks on it
                phoneNum0.setOnMouseClicked(event -> {
                  //Clear the Text Box
                    phoneNum0.clear();
                });

            //Second three Digits
            TextArea phoneNum1 = new TextArea();
              //Set the dimensions of the text area/box
                phoneNum1.setPrefSize(60, 40);
                phoneNum1.setMinSize(60, 40);
                phoneNum1.setMaxSize(60, 40);
              //Set the size of the text in the text box
                phoneNum1.setStyle("-fx-font-size: 18px;");
              //Clear the text box when the user clicks on it
                phoneNum1.setOnMouseClicked(event -> {
                  //Clear the Text Box
                    phoneNum1.clear();
                });

            //Final four Digits
            TextArea phoneNum2 = new TextArea();
              //Set the dimensions of the text area/box
                phoneNum2.setPrefSize(70, 40);
                phoneNum2.setMinSize(70, 40);
                phoneNum2.setMaxSize(70, 40);
              //Set the size of the text in the text box
                phoneNum2.setStyle("-fx-font-size: 18px;");
              //Clear the text box when the user clicks on it
                phoneNum2.setOnMouseClicked(event -> {
                  //Clear the Text Box
                    phoneNum2.clear();
                });


          //Insurance Provider
            TextArea insuranceTxt = new TextArea();
              //Set the dimensions of the text area/box
                insuranceTxt.setPrefSize(250, 40);
                insuranceTxt.setMinSize(250, 40);
                insuranceTxt.setMaxSize(250, 40);
              //Set the size of the text in the text box
                insuranceTxt.setStyle("-fx-font-size: 18px;");
              //Clear the text box when the user clicks on it
                insuranceTxt.setOnMouseClicked(event -> {
                  //Clear the Text Box
                    insuranceTxt.clear();
                });


          //Pharmacy Provider
            TextArea pharmacyTxt = new TextArea();
              //Set the dimensions of the text area/box
                pharmacyTxt.setPrefSize(300, 40);
                pharmacyTxt.setMinSize(300, 40);
                pharmacyTxt.setMaxSize(300, 40);
              //Set the size of the text in the text box
                pharmacyTxt.setStyle("-fx-font-size: 18px;");
              //Clear the text box when the user clicks on it
                pharmacyTxt.setOnMouseClicked(event -> {
                  //Clear the Text Box
                    pharmacyTxt.clear();
                });


          ///*
          //Open "PatientAccounts.txt" & Collect the information to be placed into the text boxes
            try{
              //Flag that allows collection of the patient's unique data
                boolean patientFound = false;

              //Open "PatientAccount.txt"
                File patientAccountFile = new File("PatientAccounts.txt");

              //File Reader
                Scanner fileReader = new Scanner(patientAccountFile);

              //Start reading the file and collect the user's [contact, insurance, pharmacy] info
                while(fileReader.hasNextLine()){
                  //String that saves each line read
                    String line = fileReader.nextLine();

                  //If the Patient is found based on their credentials...
                    if(line.contains(this.patientCredentials)){
                      //Activate the flag so we can start reading
                        patientFound = true;

                      //Move the line forward??
                        line = fileReader.nextLine();
                    }

                  //Break the file reader if the line does not contain "\t"
                    if(patientFound && !line.contains("\t")){
                      //Break the reading loop
                        break;
                    }


                  //Start collecting the patient's data & place it into the Text Boxes
                  //Phone Number Collection
                    if(line.contains("[Phone Number]: ") && patientFound){
                      //Trim the String 
                        this.phoneNumber = line.trim().substring(16, line.length() - 1);

                      //DEBUG/CHECK
                        //System.out.println("Phone Number: " + this.phoneNumber);


                      //Set up delimeters for breaking up the Phone Number String
                        int delimeter = this.phoneNumber.indexOf("-");

                      //First 3 numbers
                        phoneNum0.setText(this.phoneNumber.substring(0, delimeter));

                      //Update the trimmed string
                        String editedStr = this.phoneNumber.substring(delimeter+1, this.phoneNumber.length());
                      
                      //Update the delimeter
                        delimeter = editedStr.indexOf("-");

                      //Second 3 numbers
                        phoneNum1.setText(editedStr.substring(0, delimeter));

                      //Update the trimmed string
                        editedStr = editedStr.substring(delimeter+1, editedStr.length());
                      
                      //Last 4 numbers
                        phoneNum2.setText(editedStr.substring(0, editedStr.length()));
                    }

                  //Insurance Collection
                    if(line.contains("[Insurance Provider]: ") && patientFound){
                      //Update the private variable that stores the insurance provider
                        this.insuranceInfo = line.trim().substring(22, line.length()-1);

                      //Trim the string
                        insuranceTxt.setText(this.insuranceInfo);
                    }

                  //Pharmacy Collection
                    if(line.contains("[Pharmacy Provider]: ") && patientFound){
                      //Update the privat variable that stores the pharmacy provider
                        this.pharmacyInfo = line.trim().substring(21, line.length()-1);

                      //Trim the string and
                        pharmacyTxt.setText(pharmacyInfo);
                    }
                }

              //Close the file Reader
                fileReader.close();
            }
            catch(IOException k){
              //Do nothing??
            }
            //*/
        //==============================================================================


        //Action-Event Handling
        //==========================================================
          //Update the Information Entered into the text fields
            updateInfo.setOnAction(e -> {         
              //Open the file "PatientAccounts.txt" for writing new information
              //Pull the information for writing from the relevant text boxes
                try{
                  //Create a File object to check for files existence
                    File patientAccounts = new File("PatientAccounts.txt");

                  //Scanner for file reading
                    Scanner fileReader = new Scanner(patientAccounts);

                  //String that will collect all of the text leading up to & NOT including
                    String newText = "";

                  //Combine the phone Number text boxes into a string to be used easier
                    String totalPhoneNum = phoneNum0.getText() + "-" + phoneNum1.getText() + "-" + phoneNum2.getText();

                  //Flag for collecting data
                    boolean patientFound = false;

                  //Start reading the File
                  while(fileReader.hasNextLine()){
                    //Put the Line read into a string for us to manipulate
                      String line = fileReader.nextLine();


                    //Update the flag that we have found the patient
                      if(line.contains(this.patientCredentials)){
                        //Update the flag
                          patientFound = true;
                      }


                    //If the textbox has changed stop reading here & "skip this line" to collect all of
                    //the other text below it... [i.e if we updated "PhoneNumber" then collect all text before it skip the line a]
                    //Updated phone number 
                      if(    line.contains("[Phone Number]: " + this.phoneNumber)   
                          && this.phoneNumber != totalPhoneNum 
                          && patientFound
                      ){
                        //Update the private variable!!
                          this.phoneNumber = totalPhoneNum;

                        //Add the New Phone number into the editedText String! 
                          newText += "\t[Phone Number]: " + totalPhoneNum + "\n"; 

                        //Skip the line!!
                          line = fileReader.nextLine();
                      }
                    

                    //Update Insurance
                      if(    line.contains("[Insurance Provider]: " + this.insuranceInfo)   
                          && this.insuranceInfo != insuranceTxt.getText()
                          && patientFound
                      ){
                        //Update the private variable!!
                          this.insuranceInfo = insuranceTxt.getText();

                        //Add the New Insurance Provider into the editedText String! 
                          newText += "\t[Insurance Provider]: " + insuranceTxt.getText() + "\n"; 

                        //Skip the line!!
                          line = fileReader.nextLine();
                      }


                    //Update Pharmacy
                      if(    line.contains("[Pharmacy Provider]: " + this.pharmacyInfo)   
                          && this.pharmacyInfo != pharmacyTxt.getText()
                          && patientFound
                      ){
                        //Update the private variable!!
                          this.pharmacyInfo = pharmacyTxt.getText();

                        //Add the New Pharmacy Provider into the editedText String! 
                          newText += "\t[Pharmacy Provider]: " + pharmacyTxt.getText() + "\n"; 

                        //Skip the line!!
                          line = fileReader.nextLine();
                      }

                    //Add the Line's contents
                      newText += line + "\n";
                  }

                  //Create File Writer
                    FileWriter fileWriter = new FileWriter("PatientAccounts.txt");

                  //Write the new Contents to the file
                    fileWriter.write(newText);

                  //Close the File Writer
                    fileWriter.close();

                  //Close the file reader!!
                    fileReader.close();
                }
                //If the file DNE error will be caught
                catch(IOException x){
                  //Error Catch print
                    System.out.println("File does NOT exist!.!"); 
                }
            });


          //Exit the 'Update Information' page
            exitPage.setOnAction(e -> {
              //[For the future...] 
              //we may have to set objects to null 
              //before jumping into a new 'display portal'

              //Runs 'displayPortal()' to traverse back page
                displayPortal();
            });
        //==========================================================


        //Alignment of Buttons, Labels, etc.
        //========================================================================================
          //Buttons
            VBox buttonsSection = new VBox(20, updateInfo, exitPage);
              //Set the alignment of the HBox containing the functionality
                buttonsSection.setAlignment(Pos.CENTER);
    
      
          //Contact Info:
            //Horizontally align the text fields & labels of the Phone Number entry
              HBox horizontal1 = new HBox(5, phoneNum0, phoneDash0, phoneNum1, phoneDash1, phoneNum2);

            //Vertically align the phoneNumLbl & horizontal1
              VBox vertical0 = new VBox(5, phoneNumLbl, horizontal1);
                //Set the border of the shits??
                  vertical0.setStyle("-fx-background-radius: 10; -fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: lightblue; -fx-border-radius: 10;");  


          //Insurance Info:
            //Vertical alignment box
              VBox vertical1 = new VBox(5, insuranceInfoLbl0, insuranceTxt);
          		  //Set the background color & border of the box
                vertical1.setStyle("-fx-background-radius: 10; -fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: lightblue; -fx-border-radius: 10;");  
          

          //Pharmacy Info:
            //Vertical alignment box
              VBox vertical2 = new VBox(5, pharmacyInfoLbl0, pharmacyTxt);
                //Set the background color & border of the box
                vertical2.setStyle("-fx-background-radius: 10; -fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: lightblue; -fx-border-radius: 10;");  


          //Final adjustments to be consistent with the Phase1-specifications
            VBox column0 = new VBox(contactInfoLbl, vertical0);
            VBox column1 = new VBox(insuranceInfoLbl, vertical1);
            VBox column2 = new VBox(pharmacyInfoLbl, vertical2);


          //Align the sections: [Contact Info, Insurance Info, Pharmaceutical Info]
            HBox sectionAlignment = new HBox(20, column0, column1, column2);
              //Set the alignment of the Hbox
                sectionAlignment.setAlignment(Pos.CENTER);
      

          //Final Alignment/Main Layout
            VBox vertical3 = new VBox(50, sectionAlignment, buttonsSection);
              //Set the alignment of the Vertical box
                vertical3.setAlignment(Pos.CENTER);
              //Set the background color of the page to dark gray
                vertical3.setStyle("-fx-background-color: #3A3A3A;");
        //========================================================================================


        //Build the Scene
          Scene mainLayout = new Scene(vertical3, 1024, 768);

        //Return the Scene
          return mainLayout;
      }
    

    //View Previous Visits {Scene}
      private Scene previousVisits(){
        //This will have a dropwn down menu and only one button for exiting

        //Labels
        //============================================================================================
          //Select visit to view
            Label header0 = new Label("Select Visit to view:");
              //Set size of text
                header0.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");

          //Visit summary
            Label header1 = new Label("Visit Summary:");
              //Set size of text
                header1.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");
        //============================================================================================


        //Buttons
        //=========================================================================================================
          Button exitPage = new Button("Exit");
            //Set the Dimensions and text of the button
              exitPage.setPrefSize(75, 45);
              exitPage.setMinSize(75, 45);
              exitPage.setMaxSize(75, 45);
            //Set the font & size
              exitPage.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
        //=========================================================================================================


        //It works!!
          //uniqueKeyInt = Integer.parseInt(uniqueKeyStr);
         // System.out.println("Unique Key Integer: " + uniqueKeyInt);

        //DropDown Menu 
        //==========================================================================================
          //Create the dropDown menu object
            ComboBox<String> dropSelect = new ComboBox<>();
              //Set the Dimensions of the drop down menu
              //[Width x Height]
                dropSelect.setPrefSize(200, 35);
                dropSelect.setMinSize(200, 35);
                dropSelect.setMaxSize(200, 35);
              //Set the font of the text within
                dropSelect.setStyle("-fx-font-size: 16px;");

          /*
            REWRITE THIS CODE TO NOT HAVE THE STRING ARRAY (examDates) AND OTHER THINGS FOR ADDING VALUES
            YOU CAN JUST DO IT DIRECTLY IN THE WHILE LOOP'S IF-BRANCH
          */


          //String array that contains all of the exam dates
            String[] examDates = new String[10];   //We could either use the default
            //Max amount of exams per Patient is 10

          //Do a hashmap to map the visit summary(s) {Strings} to a key {Visit Date as integer}
            HashMap<Integer, String> summaryMap = new HashMap<>();

          //Integer that stores/uses the Date's numbers as a Key
            Integer uniqueKeyInt = null; 
        
          //String that stores the unique key Integer stripped from: "Date: <date here>"
            String uniqueKeyStr = "";

          //String that stores the visit summary (all text below patient being found)
            String visitSummary = ""; //These will be added to the hashMap
          //We will be able to access these in constant time via the action event-handling


          //Instantiate the string array
            for(short i = 0; i < 10; i++){
              //New stuff
                examDates[i] = "";
            }

          //Fill the ComboBox with all of the Visit Dates via: PatientSummary.txt
            try{
              //Open: PatientInfo.txt
              //Read the File with a scanner (easier to get Line by Line)
                Scanner fileReader = new Scanner(new File(this.fullName.replaceAll(" ", "") + "VisitSummarys.txt"));

              //Flag that "puts on the brakes" of the fileReader so it will
                boolean dateFound = false;
              
              //Counter for: examDate[counter] = <Exam Date String> 
                short counter = 0;

              //Collect the String line by line
                String line = "";


             //Read the file Line-by-Line and compare the strings for a match
               while(fileReader.hasNextLine()) {
                 //Gather and store the line being read
                   line = fileReader.nextLine(); //Starts at the first line of text in .txt file


                 //Collect all of the exam dates
                   if(!dateFound && line.contains("[Date]:")){  
                      //Add the exam # 
                       visitSummary = "[Exam #" + (counter+1) + "]:\n";

                      //Set the flag to true (corresponding date found)
                        dateFound = true;
                     
                      //Instantiate the visitSummary String 
                        visitSummary += "[Date]: " + line.substring(8, line.length()).trim() + "\n";

                      //Collect the exam date
                        examDates[counter] += line.substring(8, line.length());

                      //Strip the exam Date string for the numbers to be mapped into hashmap
                        uniqueKeyStr = line.replaceAll("\\D", "");  
                        //Using regex to replace all non digits with ""

                      //Add the stripped number to the Integer/Key
                        uniqueKeyInt = Integer.parseInt(uniqueKeyStr);
          
                      //increment counter
                        counter++;
                    }
                 //Collect the visit summary or break the reading loop at next patient's data
                   else{
                    //If the patient is found start collecting their data!!
                      if(dateFound && !line.contains("[Exam #")){
                        //Collect the data!
                          visitSummary += line + "\n";
                      }
                      else if(dateFound && line.contains("[Exam #")){
                        //Set the flag to false (new Exam encountered)
                          dateFound = false;
                      }
                   }

                 //Add the unique Integer [Visit Date] & visit String [Summary] to the hashmap
                   summaryMap.put(uniqueKeyInt, visitSummary);
                }

              //Close the fileReader
                fileReader.close();
            }
            catch(IOException e){
              //Error Print
                System.out.println("File Not Found!!");
            }

          //Add all of the exam Dates to the ComboBox
            for(int i = 0; i < 10; i++){
              //Add exam dates
                if(examDates[i] != ""){
                  //Add the Exam date to the box
                    dropSelect.getItems().add(examDates[i]);
                }
              //Else, help the garbage collector
                else{
                  //Set the empty slots to null
                    examDates[i] = null;
                }
            }
        //==========================================================================================


        //Text Boxes
        //=================================================================================
          //This text box will display the visit summary of the visit date selected
            TextArea visitSummaryTxt = new TextArea("<Select a Visit to be Displayed>");

          //Set the textBox size and text style??
            visitSummaryTxt.setStyle("-fx-font-weight: 18px");
            //[Width x Height]
              visitSummaryTxt.setPrefSize(800, 550);  
              visitSummaryTxt.setMaxSize(800, 550);
              visitSummaryTxt.setMinSize(800, 550);
            //Set the Font of the Button's text
              visitSummaryTxt.setStyle("-fx-font-size: 18px;");
        //=================================================================================


        //Action-Event Handling
        //=====================================================
          //Add action event handling for changes in the drop down selector!
          //This will access the hash map via stripping the strings inserted into the examDates[i]
          //STRIP THE STRINGS IN examDates[i] TO GET THE UNIQUE CORRESPONDING ID FOR THE HASHMAP
          //PLACE THE STRING INTO THE visitSummart (text box)
            
          //User select's an exam date from the dropdown menu
            dropSelect.setOnAction(event -> { 
              //Get the Date String that is currently selected by the user
              //Then strip the String 
              //This gets the unique integer Id that will load the summary via the hashMap
                visitSummaryTxt.setText(summaryMap.get(Integer.parseInt(dropSelect.getValue().replaceAll("\\D", ""))));
              //Do it all in one line of code!!

            });


          //Exit
            exitPage.setOnAction(e -> {
              //Load the Patient Portal page/scene
                displayPortal();
            });
        //=====================================================


        //Alignment
        //=================================================================
          //So far this alignment is concurrent with that of Phase1

          //Horizontally align the Dropdown select & Exit button
            HBox functContainer = new HBox(525, dropSelect, exitPage);

          //Vertically align the Header 0 Label and the button container box
            VBox vertical0 = new VBox(header0, functContainer);


          //VBox for the messageTxt (this way we can apply background color)
            VBox summaryBox = new VBox(visitSummaryTxt);
              //Set the alignment of the Visit Summary text area
                summaryBox.setAlignment(Pos.CENTER);

              //Set the background color & other features
                summaryBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");
              //Set the dimension of the summary box (slightly bigger than the Text Box)
                summaryBox.setPrefSize(825, 575);
                summaryBox.setMinSize(825, 575);
                summaryBox.setMaxSize(825, 575);

          //Vertically alignment of header and text box
            VBox vertical1 = new VBox(10, header1, summaryBox);
  
          //Final Adjustment VBox
            VBox vertical2 = new VBox(vertical0, vertical1);         
              //Set the alignment of the VBox
                vertical2.setAlignment(Pos.CENTER);

          //Set the alignment of the final box to be concurrent with the center of the page
            //vertical2.setAlignment(Pos.CENTER);

          //Horizontally align the VBox [Final box & adjustment]
            HBox horizontal0 = new HBox(vertical2);
              //Set the background color for the whole page
                horizontal0.setStyle("-fx-background-color: #3A3A3A;");

          //Set the adjustment
            horizontal0.setAlignment(Pos.CENTER);        
        //=================================================================


        //Setup Scene
          Scene mainLayout = new Scene(horizontal0, 1024, 768);

        //Return Scene
          return mainLayout;
      }
    

      //Messages {Scene}
      //You can exit this method/scene via the Button "Exit"
        private Scene messagePage(){
          //Labels, Buttons, Text Box, Action-Event Handling, Scene

          //Labels
          //=======================================================================
            //Message Board
              Label messageBrdLbl = new Label("Message Board:");
                //Set the Font & size of the text
                  messageBrdLbl.setStyle("-fx-font-size: 34px; -fx-font-weight: bold; -fx-text-fill: white;");
                 

            //Message
              Label messageLbl = new Label("Message:");
                //Set the size of the text
                  messageLbl.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

            //Inbox
              Label inboxLbl = new Label("Inbox:");
                //Set the size of the text
                  inboxLbl.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
          //=======================================================================


          //Buttons
          //=================================================================================================================
            //Send Message
              Button sendMessage = new Button("Send Message");
                //Set the dimensions of the Button
                  sendMessage.setPrefSize(150, 40);
                  sendMessage.setMinSize(150, 40);
                  sendMessage.setMaxSize(150, 40);
                //Set the Font size of the text
                  sendMessage.setStyle("-fx-font-size: 20px; -fx-font-family: 'Times New Roman'; -fx-font-weight: bold;");


            //Delete Message
              Button deleteMessage = new Button("Delete Message");
              //^^This will simply remove the lines of the .txt file after & including "Patient Name:"
                //Set the dimensions of the Button
                  deleteMessage.setPrefSize(175, 40);
                  deleteMessage.setMinSize(175, 40);
                  deleteMessage.setMaxSize(175, 40);
                //Set the Font size of the text
                  deleteMessage.setStyle("-fx-font-size: 20px; -fx-font-family: 'Times New Roman'; -fx-font-weight: bold;");


            //Exit
              Button goBack = new Button("Exit");
                //Set the dimensions of the Button
                  goBack.setPrefSize(100, 40);
                  goBack.setMinSize(100, 40);
                  goBack.setMaxSize(100, 40);
                //Set the Font size of the text
                  goBack.setStyle("-fx-font-size: 20px; -fx-font-family: 'Times New Roman'; -fx-font-weight: bold;");
          //=================================================================================================================


          //Text Boxes
          //=======================================================================
            //Message Text Box  {Anything written here will be posted to the board}
              TextArea messageTxt = new TextArea();
                //Set the dimensions of the Button
                  messageTxt.setPrefSize(300, 525);
                  messageTxt.setMinSize(300, 525);
                  messageTxt.setMaxSize(300, 525);
                //Set the Font size of the text
                  messageTxt.setStyle("-fx-font-size: 18px;");
                //Set text wrapping 
                  messageTxt.setWrapText(true);                
                  //[Note]: ^^ any text that exceeeds the width will fall to a new line


            //Inbox Text Box
              TextArea inboxTxt = new TextArea();
                //Set the dimensions of the Button
                  inboxTxt.setPrefSize(600, 525);
                  inboxTxt.setMinSize(600, 525);
                  inboxTxt.setMaxSize(600, 525);
                //Set the Font size of the text
                  inboxTxt.setStyle("-fx-font-size: 18px;");
                //Set text wrapping 
                  inboxTxt.setWrapText(true);                
                  //[Note]: ^^ any text that exceeeds the width will fall to a new line
                //Make the Text inacessible for editing
                  inboxTxt.setEditable(false);


            //Set the text of the inbox to the messsages correlating to the patient
              inboxTxt.setText(getMessages());
          //=======================================================================


          //Action-Event Handling
          //=============================================================================
            //Send Message
              //This will open the "Messages.txt" file for reading & writing
              sendMessage.setOnAction(e -> {
                //Add/Append the message written in "Message: " to the Inbox/Current Conversation              
                //Fill the ComboBox with all of the Visit Dates via: PatientSummary.txt
                  try{
                    //Open: <this.fullName>PatientInfo.txt
                      File patientInbox = new File(this.fullName.replace(" ", "") + "Messages.txt");    

                    //Open the File Writer for adding new messages to the file
                      FileWriter fileWriter = new FileWriter(patientInbox, true);
                        //[we set 'true' so that the fileWriter can append to the file]

                    //If the File exists append the new message to the end
                      if(patientInbox.exists() && !messageTxt.getText().isEmpty()){      
                        //Save the message sent to the global variable [so it can be used in deletion]
                        //Empty the global variable used for sending messages
                          messageSent = "[" + this.fullName + "]: " + messageTxt.getText() + "\n\n";
                  
                        //Append the new message to the "<FullName>Message.txt"
                          fileWriter.append(messageSent);
                  
                        //Empty the text area used for sending messages
                          messageTxt.setText("");

                        //Close the file writer
                          fileWriter.close();
                      }
                    //Else, file does not exist
                      else{
                        //Create the file
                          fileWriter.write("");

                        //Close the File Reader
                          fileWriter.close();
                      }
                  }
                  catch(IOException n){
                    //File doesn't exits do nothing
                    //Might have to creat a new file???
                  }

                //if [<Patient Name>]: does NOT exist, start the first comment [If while-loop finished without finding user]

                //Update the inbox to show the new message added
                  inboxTxt.setText(getMessages());
              });


            //Delete Message
              deleteMessage.setOnAction(e -> {
                //Count the total number of messages sent by the user {i.e. count everytime we see: "[fullName]:"}

                //Then reread the file and collect all of the text and stop once we see the last instance of [fullName]:
                //Then use .write() to rewrite all of the text in the <fullName>Messages.txt NOT including the last message sent by the user

              //Open the text file to delete the message sent
                try{
                  //Get the messages via our method...
                    
                    //If the message inbox is not empty...
                    if(getMessages() != "<Inbox Empty>"){
                      //DEBUG
                       //All messages in the .txt file
                       //System.out.println("Inbox: \n" + getMessages());
      
                      //DEbug
                        //Print the last message sent/saved in the global variable
                          //System.out.println("Message sent: " + this.messageSent);

                      //Write this into the <this.fullName>Messagest.txt
                      String editedStr = getMessages().replace(this.messageSent, "");
                      
                      //Edit the <this.fullName>Messages.txt to have the new inbox displayed!
                        File editedFile = new File(this.fullName.replaceAll(" ", "") + "Messages.txt");

                      //Open a file writer for writing the new text into the .txt file
                        FileWriter fileWriter0 = new FileWriter(this.fullName.replaceAll(" ", "") + "Messages.txt");

                      //DEBUG STATEMENT
                        //System.out.println("Edited TExt: \n" + editedStr);

                      //If the string we made is not empty, update the .txt file
                        if(this.messageSent != ("[" + this.fullName + "]: \n\n")){
                          //Replace all of the text in the Messages.txt with the latest message deleted
                            fileWriter0.write(editedStr);
          
                          //Place new text into inbox being displayed
                            inboxTxt.setText(editedStr);

                          //CLOSE THE FILE WRITER OMG
                            fileWriter0.close();
                        }
                      
                    }

                  //If the inbox is empty display the relevant text
                    if(inboxTxt.getText().isEmpty()){
                      //Set the inbox text
                        inboxTxt.setText("<Inbox Empty>");
                    }
                }
                catch(IOException l){
                  //File does not exist.. either do nothing? or create it...
                }
              });


            //Exit
              goBack.setOnAction(e-> {
                //Call upon displayPortal()
                  displayPortal();
              });
          //=============================================================================


          //Alignments
          //==========================================================================================
            //Message:
              VBox messageSection = new VBox(5, messageLbl, messageTxt);
              
            //Inbox:
              VBox inboxSection = new VBox(5, inboxLbl, inboxTxt);
            
            //HBox containing the text fields & their resepective labels (background color: lightblue)
              HBox messageBoard = new HBox(10, messageSection, inboxSection); 
                //Set Style, alignment, borders, & rounded edges
                  messageBoard.setStyle("-fx-border-color: black; -fx-border-width: 1px;  -fx-background-padding: 10; -fx-background-radius: 10; -fx-background-color: lightblue;");
                  messageBoard.setAlignment(Pos.CENTER);        
                //Set the dimensions of the "Message Board"
                  messageBoard.setPrefSize(950, 600);
                  messageBoard.setMinSize(950, 600);
                  messageBoard.setMaxSize(950, 600);


            //Horizontally Align the Buttons/Functionality
              HBox buttonContainer = new HBox(30, sendMessage, deleteMessage, goBack);
                //Set the alignment of the button container
                  buttonContainer.setAlignment(Pos.CENTER);
                  

            //Vertically align all of the sections {Last VBox}
              VBox finAlign = new VBox(20, messageBrdLbl, messageBoard, buttonContainer);
                //Set the alignment of the VBox
                //Maybe unecessary???
                  finAlign.setAlignment(Pos.CENTER);
                //Set the background color of the entire page to gray
                  finAlign.setStyle("-fx-background-color: #3A3A3A;");
          //==========================================================================================


          //Build the Scene
            Scene mainLayout = new Scene(finAlign, 1024, 768);
      
          //Return the Scene
            return mainLayout;
        }
  //-------------------------------------------------------------------------------------------------------


  //Getters & Setters
  //-------------------------------------------------------------------------------------------------------
    //Returns all of the text within: <this.fullName>Messages.txt
      private String getMessages(){
        //Open the  File for reading and read every line and set the inboxTxt to it!!
          try{
            //Collect the Lines into a String variable
              String messageStr = "";

            //Open the file & if it exists post the text to the messageBoard
              File inboxFile = new File(this.fullName.replaceAll(" ", "") + "Messages.txt");

            //Scanner that will read the file
              Scanner fileReader = new Scanner(inboxFile);

            //Read all of the text in the file and place it into the inbox
              while(fileReader.hasNextLine()){
                //Add the lines to the string
                  messageStr += fileReader.nextLine() + "\n";
              }

            //Close the file reader
              fileReader.close();

            //If the Messagebox is empty set return empty notification
              if(messageStr.isEmpty()){
                //Return the notification
                  return "<Inbox Empty>";
              }

            //Return the string
              return messageStr;
        }
        catch(IOException m){
          //Do nothing...
            return "<Inbox Empty>";
        }
    }
  //-------------------------------------------------------------------------------------------------------
}