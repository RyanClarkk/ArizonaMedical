/****************************************************************************************
[Contributors]:
  - John Bostater
  
  - Tristan Andrade

  - Austin Mayhew

  - Ryan Clark

  - Juan Rosas Jr.

 	
[Description]:
  This Object/Class contains all of the functionality for the Nurse
  This includes creating new visit forms and sending messages.


[Functionality]:

  [New Visit Form]

  [Messages]

  [Logout]s
	
[Aspect Ratio/Dimension of Graphical User Interface]: 

  - [4:3]	-->   [1024 x 768]	--	{Width x Height}
	 
	
[Files Created/Used within the program for storing/loading data]:	  
  - <Patient's Name>VisitSummarys.txt
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


public class NursePortal{
  //Private Data & Variables
  //------------------------------------------
    //We use protected so the sub-class [Doctor] can gain access/have the same data

    //Stage used to display the Scenes/Methods
      protected Stage primeStage;

    //Welcome Page (Used upon logout)
      protected Scene welcomePage;

    //Since the Doctor portal extends the NursePortal we will need to use this ID to differentiate them
      protected String staffId;

    //String that holds the current patient's credentials, that the Nurse is viewing
      private String currentPatientCreds;

    //Notification Text
      protected Label notificationLbl;

    //Notification Flag
      private boolean isNotified;

    //Visit Form Saved Flag
      private boolean formIsSaved;
      //Flag to make sure the Nurse can only submit one physical at a time
      //Set this flag to false everytime the dropDown menu is activated/a new patient is loaded

    //String that holds the Last Message sent
      private String messageSent;
  //------------------------------------------


  //Constructor
  //------------------------------------------------------------------------------
    public NursePortal(Stage primaryStage, Scene welcomeScene){
      //Set the private variables...
        primeStage = primaryStage;
        welcomePage = welcomeScene;
        staffId = "Nurse";
        currentPatientCreds = "";
        isNotified = false;
        formIsSaved = false;
    }
  //------------------------------------------------------------------------------


  //[New Visit Form]      [Messages]      [Logout]

  //Methods
  //------------------------------------------------------------------------------
    //Run the Main Portal page for the Nurse Portal & all of its functionality
    public void displayPortal(){
      //Create the scene: Buttons, Action-Event, Alignment, Scene...

      //Labels/Headers
      //===========================================================================
        //Welcome
          Label header0 = new Label("Welcome!");
	  		  	//Set the style & font of the label
  	  			  header0.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: white;");

        //What would you like to do today??
          Label header1 = new Label("What would you like to do today?");
            //Set Font & size of text
              header1.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");
      //===========================================================================


      //Buttons
      //=================================================================================================================
        //New Visit Form
          Button newVisitButton = new Button("New Visit Form");
            //newVisitButton = new Button("New Visit Form");
            //Set the Dimensions of the Button
              newVisitButton.setPrefSize(235, 50);
              newVisitButton.setMinSize(235, 50);
              newVisitButton.setMaxSize(235, 50);
            //Set the Font size of the text
              newVisitButton.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        //Messages
          Button messageButton = new Button("Messages");
            //Set the Dimensions of the Button
              messageButton.setPrefSize(150, 50);
              messageButton.setMinSize(150, 50);
              messageButton.setMaxSize(150, 50);
            //Set the Font size of the text
              messageButton.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        //Logout
          Button logoutButton = new Button("Logout");
            //Set the Dimensions of the Button
              logoutButton.setPrefSize(125, 50);
              logoutButton.setMinSize(125, 50);
              logoutButton.setMaxSize(125, 50);
            //Set the Font size of the text
              logoutButton.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
      //=================================================================================================================


      //Action-Event Handling
      //===========================================================================================
        //New Visit Form      [This will save to:  "PatientSummary.txt"  <-- rename this .txt file]
          newVisitButton.setOnAction(e -> {
            //Call upon the method to load the scene & display it
              primeStage.setScene(newVisitForm());
              primeStage.show();
          });


        //Messages
          messageButton.setOnAction(e -> {
            //Load the messages page & display it [Doctor Messages will be exactly the same]
              primeStage.setScene(messagePage());
              primeStage.show();
          });


        //Logout
          logoutButton.setOnAction(e -> {
            //Load the welcome page and display it    [Side Note: deallocate items??]
              primeStage.setScene(welcomePage);
              primeStage.show();
          });
      //===========================================================================================

      //Alignment
      //===========================================================================================
        //Vertically align the Buttons/Functionality of the Nurse Portal
          VBox vertical0 = new VBox(20, header0, header1, newVisitButton, messageButton, logoutButton);
            //Alignment
              vertical0.setAlignment(Pos.CENTER);

        //Horizontally align the Vertical alignment of buttons and labels/texts
          HBox horizontal0 = new HBox(vertical0);
            //Alignment
              horizontal0.setAlignment(Pos.CENTER);
            //Set the background color of the scene
              horizontal0.setStyle("-fx-background-color: #3A3A3A;");
      //===========================================================================================


      //Build the Scene
        Scene mainLayout = new Scene(horizontal0, 1024, 768);

      //Display the Scene
        primeStage.setScene(mainLayout);
        primeStage.show();
    }


    //New Visit Form
    protected Scene newVisitForm(){
    
      //Labels
      //====================================================================
        //Vitals
          Label vitalsLbl = new Label("Vitals:");
            //Set the Dimensions & Font style
              vitalsLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 28px; -fx-text-fill: white;");
  
          //Weight
            Label weightLbl = new Label("Weight:\t\t\t ");
              //Set the dimensions & font weight
                weightLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 18px");

          //Height
            Label heightLbl = new Label("Height:\t\t\t ");
              //Set the dimensions & font weight
                heightLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 18px");

          //Feet/Inches
            Label feetInchLbl = new Label("<ft>\'<in>\"");
              //Set the dimensions & font weight
                feetInchLbl.setStyle("-fx-font-size: 18px");

          //Body Temperature
            Label bodyTempLbl = new Label("Body Temperature:");
              //Set the dimensions & font weight
                bodyTempLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 18px");


          //Blood Pressure
            Label bloodPressureLbl = new Label("Blood Pressure:\t ");
              //Set the dimensions & font weight
                bloodPressureLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 18px");
              

          //Farenheit Label
            Label farenheitLbl = new Label("F°");
            //Set the style of the text box
              farenheitLbl.setStyle("-fx-font-size: 18px;");


          //Age Notification
            Label ageNoteLbl = new Label("*Patient vitals available for ages over 12.");
            //Set the Style of the note
              ageNoteLbl.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");


        //Pounds (lbs)
          Label lblsLbl = new Label("lbs");
            //Set the style of the text box
              lblsLbl.setStyle("-fx-font-size: 18px;");


        //Patient's Previous History
          Label previousHistoryLbl = new Label("Patient's Previous History:");
            //Set the dimensions & font weight
              previousHistoryLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 28px; -fx-text-fill: white;");
              
      
        //Nurse's Notes
          Label nurseNotesLbl = new Label("Nurse's Notes:");
            //Set the dimension & font style
              nurseNotesLbl.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");


        //Bring the patientCredentials VBox method over here1!
           //Patient Credentials:
          Label patientCredsLbl = new Label("Patient Credentials:");
            //Set the dimension & Style
              patientCredsLbl.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");

        //Select Patient  [Displayed next to or above the dropdown menu]
          Label patientSelectLbl = new Label("Select Patient:");
            //Set the dimension & style
              patientSelectLbl.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        //First Name
          Label firstNameLbl = new Label("First Name:");
            //Set the dimension & style
              firstNameLbl.setStyle("-fx-font-size: 20px;");

        //Last Name
          Label lastNameLbl = new Label("Last Name:");
            //Set the dimension & style
              lastNameLbl.setStyle("-fx-font-size: 20px;");

        //Date Of Birth
          Label dobLbl = new Label("Date of Birth:");
            //Set the dimension & style
              dobLbl.setStyle("-fx-font-size: 20px;");

        //(MM / DD / YYYY)
          Label dobAlignLbl = new Label("(MM/DD/YYYY)");
            //Set the dimension & style
              dobAlignLbl.setStyle("-fx-font-size: 18px;");


        //Exam Date
          Label examDateLbl = new Label("Visit Date:");
            //Set the font & style of the label
              examDateLbl.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: white;");
      //====================================================================


      //Text Boxes
      //====================================================================
        //Patient Credentials TExt Boxes
        //First Name Text Box
          TextArea firstNameTxt = new TextArea();
            //Set the dimensions
              firstNameTxt.setPrefSize(125, 30);
              firstNameTxt.setMinSize(125, 30);
              firstNameTxt.setMaxSize(125, 30);
            //Set the style of the text box
              firstNameTxt.setStyle("-fx-font-size: 14px;");

        //Last Name Text Box
          TextArea lastNameTxt = new TextArea();
            //Set the dimensions
              lastNameTxt.setPrefSize(125, 30);
              lastNameTxt.setMinSize(125, 30);
              lastNameTxt.setMaxSize(125, 30);
            //Set the style of the text box
              lastNameTxt.setStyle("-fx-font-size: 14px;");

        //Date of Birth Text Box
          TextArea dobTxt = new TextArea();
            //Set the dimensions
              dobTxt.setPrefSize(100, 30);
              dobTxt.setMinSize(100, 30);
              dobTxt.setMaxSize(100, 30);
            //Set the style of the text box
              dobTxt.setStyle("-fx-font-size: 14px;");
   
        //Weight
          TextArea weightTxt = new TextArea();
            //Set the dimension & style
              weightTxt.setPrefSize(45, 30);
              weightTxt.setMinSize(45, 30);
              weightTxt.setMaxSize(45, 30);
            //Set the style of the text box
              weightTxt.setStyle("-fx-font-size: 14px;");


        //Height
          TextArea heightTxt = new TextArea();
            //Set the dimension & style
              heightTxt.setPrefSize(55, 30);
              heightTxt.setMinSize(55, 30);
              heightTxt.setMaxSize(55, 30);
            //Set the style of the text box
              heightTxt.setStyle("-fx-font-size: 14px;");
    

        //Body Temperature
          TextArea bodyTempTxt = new TextArea();
            //Set the dimension & style
              bodyTempTxt.setPrefSize(50, 30);
              bodyTempTxt.setMinSize(50, 30);
              bodyTempTxt.setMaxSize(50, 30);
            //Set the style of the text box
              bodyTempTxt.setStyle("-fx-font-size: 14px;");


        //Blood Pressure:
          TextArea bloodPressureTxt = new TextArea();
            //Set the dimension & style
              bloodPressureTxt.setPrefSize(100, 30);
              bloodPressureTxt.setMinSize(100, 30);
              bloodPressureTxt.setMaxSize(100, 30);
            //Set the style of the text box
              bloodPressureTxt.setStyle("-fx-font-size: 14px;");


        //Nurse's Notes:
          TextArea nursesNotesTxt = new TextArea("<Known Allergies & Other Health Concerns>");
            //Set the dimension & style
              nursesNotesTxt.setPrefSize(500, 300);
              nursesNotesTxt.setMinSize(500, 300);
              nursesNotesTxt.setMaxSize(500, 300);
            //Set the style of the text box
              nursesNotesTxt.setStyle("-fx-font-size: 16px;");
            //Clear the text box when the user clicks on it to enter new text!
              nursesNotesTxt.setOnMouseClicked(event-> {
                //Clear the text
                nursesNotesTxt.clear();
              });
            //Enable text wrapping
              nursesNotesTxt.setWrapText(true);


        //Patient's Previous History
          TextArea previousMedHistTxt = new TextArea("<Previously Prescribed Medications>\n<Immunization History>\n<Previous Health Issues>");
            //Set the dimension & style
              previousMedHistTxt.setPrefSize(500, 150);
              previousMedHistTxt.setMinSize(500, 150);
              previousMedHistTxt.setMaxSize(500, 150);
            //Set the style of the text box
              previousMedHistTxt.setStyle("-fx-font-size: 16px;");
            //Clear the text box when the user clicks on it to enter new text!
              previousMedHistTxt.setOnMouseClicked(event-> {
                //Clear the text
                previousMedHistTxt.clear();
              });
            //Set text wrapping
              previousMedHistTxt.setWrapText(true);


        //Date Text Box
          TextArea dateTxt = new TextArea("00/00/0000");
            //Set the dimension & style
              dateTxt.setPrefSize(110, 35);
              dateTxt.setMinSize(110, 35);
              dateTxt.setMaxSize(110, 35);
            //Set the style of the text box
              dateTxt.setStyle("-fx-font-size: 16px;");
            //Clear the text box when the user clicks on it to enter new text!
              dateTxt.setOnMouseClicked(event-> {
                //Clear the text
                dateTxt.clear();
              });
      //====================================================================


      //Buttons
      //====================================================================
        //Submit For Physical {Nurse}
          Button submitPhysical = new Button("Submit for Physical");
            //Button size
              submitPhysical.setPrefSize(200, 40);
              submitPhysical.setMinSize(200, 40);
              submitPhysical.setMaxSize(200, 40);
            //Set the style of the Button
              submitPhysical.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");


        //Conduct Exam {Doctor}
          Button conductExam = new Button("Conduct Exam");
            //Button size
              conductExam.setPrefSize(150, 40);
              conductExam.setMinSize(150, 40);
              conductExam.setMaxSize(150, 40);
            //Set the style of the Button
              conductExam.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

          
        //Exit  {Both}
          Button goBack = new Button("Exit");
            //Button size
              goBack.setPrefSize(75, 40);
              goBack.setMinSize(75, 40);
              goBack.setMaxSize(75, 40);
            //Set the style of the Button
              goBack.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
      //====================================================================


      //DropDown Menu
      //===========================================================================
        //Create the dropdown menu
          ComboBox<String> dropDown = new ComboBox<>();
            //Set dimension of the dropdown menu??
              dropDown.setPrefSize(250, 30);
              dropDown.setMinSize(250, 30);
              dropDown.setMaxSize(250, 30);
            //Set the style
              dropDown.setStyle("-fx-font-size: 14px;");


        //Collect all of the patient credentials and place them into the combo box
          try{
            //Open 'PatientAccounts.txt' for reading!!
              Scanner fileReader = new Scanner(new File("PatientAccounts.txt"));

            //Read all of the file's contents
              while(fileReader.hasNextLine()){
                //Place the file being read into a String
                  String line = fileReader.nextLine();

                //Collect all of the patient credentials via this if statement
                if( !line.contains("\t") 
                      && !line.contains("\n") 
                      && !line.contains("Patient Accounts:")
                      && !line.isEmpty()
                ){
                  //Add the patient(s) credentials to the Drop Down menu
                    dropDown.getItems().add(line);
                }
              }
          }
          catch(IOException e){
            //Do nothing...
          }
      //===========================================================================


      //Alignments
      //====================================================================
        //VBox that stores the Buttons/Functionality of the Visit Form Page
          VBox buttonContainer;

        //If-statement that checks for which type of user is using the program & then
        //The buttons will change depending on what is chosen
          if(this.staffId == "Nurse"){
            //[Submit for Physical] &&  [Exit]
              buttonContainer = new VBox(10, submitPhysical, goBack);
            //Set the alignment of the buttons
              buttonContainer.setAlignment(Pos.CENTER);
          }
          //Else, Doctor is using Program   [Doctor's: 'Patient Visit Form' Method]
          else{ //{DOCTOR}
            //[Conduct Exam]  &&  [Exit]
              buttonContainer = new VBox(10, conductExam, goBack);
            //Set the alignment of the buttons
              buttonContainer.setAlignment(Pos.CENTER);
          }

        //Weight Horizontal Alignments
          HBox horizontal0 = new HBox(5, weightTxt, lblsLbl);
    
        //Height Horizontal Alignments
          HBox horizontal1 = new HBox(5, heightTxt, feetInchLbl);
          
        //Body Temperature Horizontal Alignments
          HBox horizontal2 = new HBox(5, bodyTempTxt, farenheitLbl);
            
        //Blood Pressure Horizontal Alignments
          HBox horizontal3 = new HBox(5, bloodPressureTxt, bloodPressureLbl);


        //Vertical alignment for the vitals entry box
          VBox vitalsBox = new VBox(5, weightLbl, horizontal0, heightLbl, horizontal1, bodyTempLbl, horizontal2, bloodPressureLbl, horizontal3);
            //Set the dimension of the vitals box
              vitalsBox.setPrefSize(225, 300);
              vitalsBox.setMinSize(225, 300);
              vitalsBox.setMaxSize(225, 300);
            //Set the background color of the vitals box
              vitalsBox.setStyle("-fx-background-color: lightblue; -fx-border-radius: 10; -fx-background-radius: 10;");
            //Set the alignment
              vitalsBox.setPadding(new Insets(30));
              vitalsBox.setAlignment(Pos.CENTER);
            

        //Vertical Alignments for the Visit Date
          VBox visitDateBox = new VBox(dateTxt);
            //Set the size
              visitDateBox.setPrefSize(125, 45);
              visitDateBox.setMinSize(125, 45);
              visitDateBox.setMaxSize(125, 45);
            //Set alignment
              visitDateBox.setAlignment(Pos.CENTER);
            //Set the background color
              visitDateBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");


        //Vertical Alignments for the Nurse's Notes
          VBox nurseNotesBox = new VBox(nursesNotesTxt);
            //Set the size
              nurseNotesBox.setPrefSize(525, 325);
              nurseNotesBox.setMinSize(525, 325);
              nurseNotesBox.setMaxSize(525, 325);

            //Set alignment
              nurseNotesBox.setAlignment(Pos.CENTER);
            //Set the background color
              nurseNotesBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");


        //Vertical Alignments for the Patient's Previous History Notes
          VBox patientHistoryBox = new VBox(previousMedHistTxt);
            //Set the size
              patientHistoryBox.setPrefSize(525, 175);
              patientHistoryBox.setMinSize(525, 175);
              patientHistoryBox.setMaxSize(525, 175);
            //Set the padding
              //patientHistoryBox.setpadding(new Insets(30));

            //Set alignment
              patientHistoryBox.setAlignment(Pos.CENTER);
            //Set the background color
              patientHistoryBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");

        
        //Patient Credentials Alignment
        //Horizontal Boxes
          //First Name
            HBox horizontalX = new HBox(5, firstNameLbl, firstNameTxt);

          //Last Name
            HBox horizontalY = new HBox(5, lastNameLbl, lastNameTxt);

          //Date of Birth
            HBox horizontalZ = new HBox(5, dobLbl, dobTxt);


        //DropDown 
          VBox dropDownBox = new VBox(5, patientSelectLbl, dropDown);
            //Set the alignment of the VBox
              dropDownBox.setAlignment(Pos.CENTER);
        

        //Credentials Section 
          VBox credSection = new VBox(5, dropDownBox, horizontalX, horizontalY, horizontalZ);
            //Set the dimensions, style, & color
              credSection.setPrefSize(300, 200);
              credSection.setMinSize(300, 200);
              credSection.setMaxSize(300, 200);
            //Set the alignment of the VBox
              credSection.setAlignment(Pos.CENTER);
            //Set the background color of the VBox
              credSection.setStyle("-fx-background-color: lightblue; -fx-border-radius: 10; -fx-background-radius: 10;");
            //Set the padding of the VBox
              credSection.setPadding(new Insets(30));


        //Encapsulating VBox that we will return
          VBox patientCredentialsBox = new VBox(10, patientCredsLbl, credSection);
            //Set the alignment of the VBox
              patientCredentialsBox.setAlignment(Pos.CENTER);


        //Vitals Encapsulating Vertical Alignment
          VBox vitalsAlignBox = new VBox(5, vitalsLbl, vitalsBox, ageNoteLbl);
            //Set Alignment
              vitalsAlignBox.setAlignment(Pos.CENTER);

        //Patient Credentials (laoded via method, 
        //this method also updates the priv data holding the current/visiting patient's info)
          VBox vertical0 = new VBox(10, patientCredentialsBox, examDateLbl, visitDateBox, vitalsAlignBox);
            //Set the alignment of the Encapsulating box
              vertical0.setAlignment(Pos.CENTER);

        //Notification Container
          HBox notificationContainer = new HBox();
            //Set the alignment of the text
              notificationContainer.setAlignment(Pos.CENTER);

        //Vertical alignment for the nurseNotesBox & patientHistoryBox
          VBox vertical1 = new VBox(5, nurseNotesLbl, nurseNotesBox, previousHistoryLbl, patientHistoryBox, notificationContainer, buttonContainer);
            //Set the alginment of vertical1
              vertical1.setAlignment(Pos.CENTER);


        //Final Vertical Alignment
          HBox finalLayout = new HBox(20, vertical0, vertical1);
            //Set the final adjustments??
              finalLayout.setAlignment(Pos.CENTER);
            //Set the background color of the gui
              finalLayout.setStyle("-fx-background-color: #3A3A3A;");
      //====================================================================


      //Action-Event Handling
      //====================================================================
        //DropDown Menu Selection 
          dropDown.setOnAction(event -> {
            //Remove any previous notfications
              notificationContainer.getChildren().remove(this.notificationLbl);


            //Remove any previous text from the text boxes
              dateTxt.clear();
              weightTxt.clear();
              heightTxt.clear();
              bodyTempTxt.clear();
              bloodPressureTxt.clear();
              nursesNotesTxt.clear();
              previousMedHistTxt.clear();


            //Set the "Save Vist Form" Flag to false
              formIsSaved = false;
            //[i.e. user can now Save new patient Exam]


            //Save the Selected patient's name to the current patient priv data string
              this.currentPatientCreds = dropDown.getValue();

            //Load the patients data into the relevant TextBoxes
              String fullName = dropDown.getValue().substring(0, dropDown.getValue().indexOf("/")-3).replace(",", " ");


            //First Name Text Box Set
              firstNameTxt.setText(fullName.substring(0, fullName.indexOf(" ")));

            //First Name Text Box Set
              lastNameTxt.setText(fullName.substring(fullName.indexOf(" "), fullName.length()));

            //Date of Birth Text Box Set
              dobTxt.setText(this.currentPatientCreds.substring(this.currentPatientCreds.indexOf("/")-2, this.currentPatientCreds.length()));
          
   
            //Load any previously written data into the Text Boxes
            //Open the file for reading to load all potential data
            try{
              //Update the fullName string before proceeding
                fullName = fullName.replaceAll(" ", "");

              //VisitSummary.txt file
                File visitSummaryFile = new File(fullName + "VisitSummarys.txt");

              //See if the file exists
                if(visitSummaryFile.exists()){
                  //File Exists!, now load all of the data from the previous visit into the text boxes!
                  
                  //File Reader
                    Scanner fileReader = new Scanner(visitSummaryFile);

                  //Relevant Flag for data collection
                    boolean collectData = false;

                  //Date Collection [Doctor]
                    boolean dateCollect = false;

                  //Vitals Collection [Doctor]
                    boolean vitalsCollect = false;

                  //Nurse's Notes Collection [Doctor]
                    boolean nursesNotesCollect = false;

                  //Previous History Notes  [Nurse]
                    boolean prevHistCollect = false;

                  //Load the number of the Last exam to be added to the .txt file 
                    short lastExamNum = getTotalExams();

                  //Counter for # of "[Exam #" found
                    short counter = 0;


                  //Nurse's Notes
                    String nursesNotesStr = "<Known Allergies & Other Health Concerns>";

                  //Previous History
                    String prevHistStr = "<Previously Prescribed Medications>\n<Immunization History>\n<Previous Health Issues>";


                  //Read text file line for line
                    while(fileReader.hasNextLine()){
                      //String that holds the line being read
                        String line = fileReader.nextLine();


                      //Increment the counter upon every Exam #
                        if(line.contains("[Exam #")){
                          //Increment
                            counter++;
                        }


                      //Last Exam found!, load the data into the .txt files
                        if(counter == lastExamNum){
                          //Update the flag!
                            collectData = true;
                        }


                      //Flag Activation 
                      //---------------------------------------------------------------------------
                        //Date collection [Doctor]
                          if(line.contains("[Date]:") && collectData && this.staffId == "Doctor"){
                            //Move the line forward!
                             // line = fileReader.nextLine();

                            //Activate the flag to start collecting the Vitals
                              dateCollect = true;
                          }

                        //Vitals Flag [Doctor]
                          if(line.contains("[Vitals]:") && collectData && this.staffId == "Doctor"){
                            //Move the line forward!
                              line = fileReader.nextLine();

                            //Activate the flag to start collecting the Vitals
                              vitalsCollect = true;
                          }

                        //Nurse's Notes Flag [Doctor]
                          if(line.contains("[Nurse's Notes]:") && collectData && this.staffId == "Doctor"){
                            //Turn off any proceeding flags
                              vitalsCollect = false;
                            
                            //Empty the default text from String(s)
                              nursesNotesStr = "";
                            
                            //Move the line forward!
                              line = fileReader.nextLine();

                            //Activate the flag to start collecting the Nurse's Notes
                              nursesNotesCollect = true;
                          }

                        //Previous History Flag [Both]
                          if(line.contains("[History]:") && collectData){
                            //Turn off any preceeding flags
                              nursesNotesCollect = false;

                            //Empty the default text from String(s)
                              prevHistStr = "";

                            //Move the line forward!
                              line = fileReader.nextLine();

                            //Activate the flag to start collecting the Previous Exam History
                              prevHistCollect = true;
                          }
                      //---------------------------------------------------------------------------


                      //Data Collection {Flag response}
                      //---------------------------------------------------------------------------
                        //Collection
                        //Date Flag
                          if(dateCollect){
                            //Set the text in the date string                         
                              dateTxt.setText(line.substring(line.indexOf(":")+1, line.length()));
                            
                            //Break the date collection
                              dateCollect = false;
                          }


                        //Vitals Flag [Doctor]
                          if(vitalsCollect){
                            //Statement to break the vitals collection!!!
                              if(line.contains("[Nurse's Notes]:")){
                                //Break the vitals collection
                                  vitalsCollect = false;
                              }
                            //Else, collect the data
                              else{
                                //Collect the Weight & place it into its proper .txt
                                  if(line.contains("[Weight]: ")){
                                    //Collect the weight (anything between ":" & "l")
                                  
                                    //If the weight is NOT empty, update the string
                                      if(!line.replaceAll(" ", "").isEmpty()){
                                        //Collect the weight via a substring
                                          weightTxt.setText(line.substring(line.indexOf(":")+1, line.indexOf("l")-1));
                                      }
                                  }


                                //Collect the Height & place it into its proper .txt
                                  if(line.contains("[Height]: ")){
                                    //If the height is NOT empty, update the string
                                      if(!line.replaceAll(" ", "").isEmpty()){
                                        //Collect the height via a substring
                                          heightTxt.setText(line.substring(line.indexOf(":")+1, line.indexOf("<")-1));
                                      }
                                  }


                                //Collect the Body Temp & place it into its proper .txt
                                  if(line.contains("[Body Temperature]: ")){
                                    //If the body temp is NOT empty, update the string
                                      if(!line.replaceAll(" ", "").isEmpty()){
                                        //Collect the body temp via a substring
                                          bodyTempTxt.setText(line.substring(line.indexOf(":")+1, line.indexOf("F")-1));
                                      }
                                  }


                                //Collect the Blood Pressure & place it into its proper .txt
                                  if(line.contains("[Blood Pressure]: ")){
                                    //If the blood pressure is NOT empty, update the string
                                      if(!line.replaceAll(" ", "").isEmpty()){
                                        //Collect the blood pressure via a substring
                                          bloodPressureTxt.setText(line.substring(line.indexOf(":")+1, line.length()));
                                      }
                                  }
                            }
                          }

                        //Nurse's Notes Flag [Doctor]
                          if(nursesNotesCollect){
                            //Statement to break the data collection!!!
                              if(line.contains("[History]:")){
                                //Break the data collection
                                  nursesNotesCollect = false;
                              }
                            //Else, collect the data
                              else{
                                //Collect the line
                                  nursesNotesStr += line + "\n";
                              }
                          }

                        //History Notes Collection [Both]
                          if(prevHistCollect){
                            //Statement to break the data collection!!!
                              if(line.contains("[Exam #")){
                                //Break the data collection
                                  prevHistCollect = false;
                              }
                            //Else, collect the data
                              else{
                                //Collect the line
                                  prevHistStr += line + "\n";
                              }
                          }                
                    }
                    //---------------------------------------------------------------------------

                  //Add the Text box strings to their respective TextBoxes            
                  //Nurse's Notes
                    nursesNotesTxt.setText(nursesNotesStr.trim());
                      //Trim any whitespace or \t

                  //Previous History
                    previousMedHistTxt.setText(prevHistStr.trim());
                      //Trim any whitespace or \t

                  //Close the file reader
                    fileReader.close();
                }
              }
              //Else, do nothing...
            catch(IOException k){
              //Do Nothing
            }
          });


        //Submit For Physical {Nurse}
          submitPhysical.setOnAction(e -> {
            //Submit the physical...
            //This will open the <PatientName>VisitSummarys.txt and add the new exam notes into the
            //.txt file     [Search for patient {if they don't exist append a new entry}
            //               Look for the exam dates and ]

            //Remove any previous notfications
              notificationContainer.getChildren().remove(this.notificationLbl);


            //If the user has NOT selected a patient via the dropdown menu inform them via a notification
              if(dropDown.getValue() == null){
                //Display the notification to the user that they have to use the dropdown box
                  this.notificationLbl = new Label("*Select a Patient via the Drop Down Menu.");
                    //Set the font & size
                      this.notificationLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
                //Add the notification to the container
                  notificationContainer.getChildren().add(this.notificationLbl);

                //Don't continue
                  return;
              }


            //If any of the Text Boxes are empty or incorrect throw an error & skip below!!
            if(     weightTxt.getText().isEmpty() 
                ||  heightTxt.getText().isEmpty() 
                ||  bodyTempTxt.getText().isEmpty()
                ||  bloodPressureTxt.getText().isEmpty()
                ||  dateTxt.getText().isEmpty()
                ||  this.formIsSaved
            ){
              //If in regard to form being saved already display notification and break here
                if(this.formIsSaved){
                  //Remove any old notifications
                    notificationContainer.getChildren().remove(this.notificationLbl);

                  //Build & Display the notification
                    this.notificationLbl = new Label("*Visit Data has already been saved.");
                  
                  //Display the notification
                    notificationContainer.getChildren().add(this.notificationLbl);

                  //Break here
                    return;
                }


              //Update the notification
                this.notificationLbl = new Label("*Required Input is Incorrect or Missing");
                  //Set the style & font
                    notificationLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
                
                //Add the new notification to the container
                  notificationContainer.getChildren().add(this.notificationLbl);

              //Break the action-event!!
                return;  
            }


            //Open <Patient Name>VisitSummarys.txt
            try{
              //Open the patients correlating "<Patient Name>VisitSummarys.txt"
              //We will be appending All of the information entered in the "New Visit Form"
              //To the .txt file if it already exists

              //Break apart the this.currentPatientCreds to get the fullName 
                String fullName = this.currentPatientCreds.substring(0, this.currentPatientCreds.indexOf("/") - 3);


              //Open File for Writing (If the .txt does not exist it will be created)
                FileWriter fileWriter = new FileWriter(fullName.replaceAll(",", "") + "VisitSummarys.txt", true);
                  //'true' is so we can append text to the file


              //Collect all of the information from the text boxes/areas 
              //then write it to the .txt file via appending
                String physicalExam = "[Exam #" + (getTotalExams()+1) + "]:"
                  + "\n"
                  + "[Date]: " +  dateTxt.getText()
                  + "\n\n" 
                  + "[Vitals]:" 
                  + "\n"
                  + "\t[Weight]: " + weightTxt.getText().trim() + " lbs"
                  + "\n"
                  + "\t[Height]: " + heightTxt.getText().trim() + " <ft>'<in>\""
                  + "\n"
                  + "\t[Body Temperature]: " + bodyTempTxt.getText().trim() + " F°"
                  + "\n"
                  + "\t[Blood Pressure]: " + bloodPressureTxt.getText().trim()
                  + "\n"
                  + "\n[Nurse's Notes]: " 
                  + "\n" + nursesNotesTxt.getText()
                  + "\n"
                  + "\n[History]: " 
                  + "\n" + previousMedHistTxt.getText()
                  + "\n\n\n";
                

              //Append the Visit Form to the Patient's current visit Summary
                fileWriter.append(physicalExam);

              //Update flag that the form has been saved (we cannot save more than one Visit at a time)
                this.formIsSaved = true;

  
              //Set up the notification label
                this.notificationLbl = new Label("*Exam data has been saved.");
              //Set the font & size
                notificationLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
              //Add the new notficiation to the notification box
                notificationContainer.getChildren().add(this.notificationLbl);


              //Close the file Writer
                fileWriter.close(); 
            }
            catch(IOException n){
              //Do nothing...
            }
          });


        //Conduct Exam {Doctor}
          conductExam.setOnAction(e -> {
            //Remove any previous notfications
              notificationContainer.getChildren().remove(this.notificationLbl);

            //If the user has NOT selected a patient via the dropdown menu inform them via a notification
              if(dropDown.getValue() == null){
                //Display the notification to the user that they have to use the dropdown box
                  this.notificationLbl = new Label("*Select a Patient via the Drop Down Menu.");
                    //Set the font & size
                      notificationLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
                //Add the notification to the container
                  notificationContainer.getChildren().add(this.notificationLbl);

                //Don't continue
                  return;
              }
    
            //Else, display a notification to the user!!

            //Call upon the conductExam Method
              primeStage.setScene(conductExam(this.currentPatientCreds));
              primeStage.show();
          });


        //Exit  {Both}
          goBack.setOnAction(e -> {
            //Reupdate any flags before leaving
              this.isNotified = false;
              this.formIsSaved = false;

            //Call upon the Display portal method [Same for both]
              displayPortal();
          });
      //====================================================================


      //Build the Scene
        Scene mainLayout = new Scene(finalLayout, 1024,768);
      //Return the Scene
        return mainLayout;
    }


  //Collect the total number of exam dates for a selected patient
    private short getTotalExams(){
      //Open the Patient's total exam's file if it exists

      //Open file for reading & collecting the total number of examinations
      try{
        //Get the patients full Name
          String fullName = this.currentPatientCreds.substring(0, this.currentPatientCreds.indexOf("/")-3).replaceAll(",", "");

        //Make sure the file isn't being created to read from
          if(fullName.isEmpty()){
            //Break
              return 0;
          }


        //Open <Patient Name>VisitSummary.txt for reading
          File visitSummaryFile = new File(fullName.trim() + "VisitSummarys.txt");

        //Holds count of the number of exam dates
          short totalExams = 0;

    
        //If the file exists, start collecting
          if(visitSummaryFile.exists()){
            //File Reader
              Scanner fileReader = new Scanner(visitSummaryFile);

           //Start reading
            while(fileReader.hasNextLine()){
              //String for holding the line being read
                String line = fileReader.nextLine();

              //Exam encountered, increment the count
                if(line.contains("[Exam #")){
                  //Update count
                    totalExams++;
                }
            }

            //Close the file reader
              fileReader.close();

            //Return the total number of exams
              return totalExams;
          }
        //File does not exist or this is the first entry
          else{
            //Return total number (0)
              return totalExams;
          }

      }
      catch(IOException a){
        //Do nothing
          return -1;
      }
    }
  //-----------------------------------------------------------------------------------


  //[DoctorPortal Methods]  //return Null in Nurse Portal
  //-----------------------------------------------------------------------------------
  //Empty method that will be Overrided by the DoctorPortal!!
    protected Scene conductExam(String patientCredentials){
      //Return null, as the Nurse will NOT use this method
        return null;
    }
  //-----------------------------------------------------------------------------------


  //This method will display/load the Scene for sending & reading messages
  //
  //[This method looks like the patient portal but there is a dropdown menu for 
  //                                  selecting which patients messages .txt file you want to open]
    protected Scene messagePage(){
      //Labels, Buttons, Text Box, Action-Event Handling, Scene

      //Labels
      //=====================================================================================================
        //Message Board
          Label messageBrdLbl = new Label("Message Board:");
            //Set the Font & size of the text
              messageBrdLbl.setStyle("-fx-font-size: 34px; -fx-font-weight: bold; -fx-text-fill: white;");
            //NEW!!
            //Set padding
             // messageBrdLbl.setPadding(new Insets(0,0,20,0));

        //Message
          Label messageLbl = new Label("Message:");
            //Set the size of the text
              messageLbl.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        //Inbox
          Label inboxLbl = new Label("Inbox:");
            //Set the size of the text
              inboxLbl.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");


        //Select Patient [Drop Down Menu]
          Label selectPatientLbl = new Label("Select Patient:");
            //Set the Font and other features
              selectPatientLbl.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");
      
        //Patient Contact Information
          //Have the Phone Number Displayed in a text box next to this label!!!
          Label contactInfoLbl = new Label("Patient Contact Information:");
            //Set the Font and other features
              contactInfoLbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");     
      //=====================================================================================================


      //Buttons
      //=======================================================================
        //Send Message
          Button sendMessage = new Button("Send Message");
            //Set the dimensions of the Button
              sendMessage.setPrefSize(150, 40);
              sendMessage.setMinSize(150, 40);
              sendMessage.setMaxSize(150, 40);
            //Set the Font size of the text
              sendMessage.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");


        //Delete Message
          Button deleteMessage = new Button("Delete Message");
          //^^This will simply remove the lines of the .txt file after & including "Patient Name:"
            //Set the dimensions of the Button
              deleteMessage.setPrefSize(175, 40);
              deleteMessage.setMinSize(175, 40);
              deleteMessage.setMaxSize(175, 40);
            //Set the Font size of the text
              deleteMessage.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");


        //Exit
          Button goBack = new Button("Exit");
            //Set the dimensions of the Button
              goBack.setPrefSize(100, 40);
              goBack.setMinSize(100, 40);
              goBack.setMaxSize(100, 40);
            //Set the Font size of the text
              goBack.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
      //=======================================================================


      //Text Boxes
      //=======================================================================
        //Message Text Box  {Anything written here will be posted to the board}
          TextArea messageTxt = new TextArea();
            //Set the dimensions of the text box
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
            //Set the dimensions of the text box
              inboxTxt.setPrefSize(600, 485);
              inboxTxt.setMinSize(600, 485);
              inboxTxt.setMaxSize(600, 485);
            //Set the Font size of the text
              inboxTxt.setStyle("-fx-font-size: 18px;");
            //Set text wrapping 
              inboxTxt.setWrapText(true);                
              //[Note]: ^^ any text that exceeeds the width will fall to a new line
            //Make the text uneditable
              inboxTxt.setEditable(false);


        //Phone Number Text Box [Will be next to contactInfoLbl]
          TextArea phoneNumTxt = new TextArea("000-000-0000");
            //Set the dimensions of the the text box
              phoneNumTxt.setPrefSize(130, 35);
              phoneNumTxt.setMinSize(130, 35);
              phoneNumTxt.setMaxSize(130, 35);
            //Set the font size of the text within
              phoneNumTxt.setStyle("-fx-font-size: 16px;");
            //Set the text box so it CANNOT be deleted or manipluated
              phoneNumTxt.setEditable(false);

            //Place this text below the inboxTxt
      //=======================================================================


      //DropDown Menu && HashMap
      //=============================================================================
        //Hash Map to be used for mapping Patient Credentials to their Specified <fullName>Messages.txt
          HashMap<String, String> messagesMap = new HashMap<>();

        //Create a new Dropdown menu for selecting patients
          ComboBox<String> dropDown = new ComboBox<>();
            //Set the dimension & font size of the DropDown Menu
              dropDown.setPrefSize(250,35);
              dropDown.setMinSize(250,35);
              dropDown.setMaxSize(250,35);
            //Set the font size
              dropDown.setStyle("-fx-font-size: 16px;");

     
        //Read the patient names from "Patient Accounts.txt"
        try{
          //File to read from
            File patientAccountsFile = new File("PatientAccounts.txt");

          //File reader
            Scanner fileReader = new Scanner(patientAccountsFile);

          //Read file & collect data
            while(fileReader.hasNextLine()){
              //Place the line read into a String variable for manip
                String line = fileReader.nextLine();

              //If the line does NOT contain a "\t" throw dont collect the line??
                if(!line.contains("\t") && !line.contains("Patient Accounts:") && !line.isEmpty()){ 
                  //[Idea] use the fullName instead to be "cleaner"??
                  
                  //Add the Patient's Credentials to the HashMap (key) & their Messages (value)
                    messagesMap.put(line, getMessages(line));

                  //Add the patient's Credentials to the Dropdown menu
                    dropDown.getItems().add(line);
                }
            }

          //Close file reader
            fileReader.close();
        }
        catch(IOException p1){
          //Do nothing
        }
      //=============================================================================


      //Action-Event Handling
      //=============================================================================
        //DropDown menu, selecting a Patient
          dropDown.setOnAction(event -> {
            //Clear the text boxes of any previous data/text
              messageTxt.clear();
              inboxTxt.clear();

            //Get the value selected (Patient Credentials)  
            //Use the selected credentials to load the patient's messages via the HashMap
            
            //Set the text of the inbox to that of the patient selected via the dropdown menu
              inboxTxt.setText(messagesMap.get(dropDown.getValue()));

            //Start file Reading to collect the Patients Contact Information (Phone Number)
              try{
                //File Reader for PatientAccounts.txt
                  Scanner fileReader = new Scanner(new File("PatientAccounts.txt"));

                //Flag used for data collection 
                  boolean patientFound = false;

                //Start reading the file looking for the Patient's contact information
                  while(fileReader.hasNext()){
                    //Save the line read into a String to be manipulated
                      String line = fileReader.nextLine();                    

                    //Activate flag for data collection (Patient has been found)
                      if(line.contains(dropDown.getValue())){
                        //Activate the data collection flag
                          patientFound = true;
                      }

                    //Collect the Line containing the Patient's contact information
                      if(line.contains("[Phone Number]:") && patientFound){
                        //Place the phone number into the Text Box!!
                          phoneNumTxt.setText(line.substring(line.indexOf("-")-3, line.length()));
                    
                        //Break the loop
                          break;
                      }
                  }
              }
              catch(IOException e3){
                //Do nothing...
              }
          });


        //Send Message
          //This will open the "Messages.txt" file for reading & writing
          sendMessage.setOnAction(e -> {
            //If the Staff user has NOT selected a user from the DropDown menu (they CANNOT add the message)
              if(dropDown.getValue() == null){
                //Display notification to user
                  inboxTxt.setText("*Please Select a Patient via the Drop Down menu above to view and send messages.");
              
                //Stop the user here
                  return;
              }


            //Get the patient's fullname (used for sending message to correct .txt)
              String fullName = dropDown.getValue().substring(0, dropDown.getValue().indexOf("/")-3).replaceAll(",", "");


            //Add/Append the message written in "Message: " to the Inbox/Current Conversation              
            //Fill the ComboBox with all of the Visit Dates via: PatientSummary.txt
              try{
                //Open: <DropDown.getValue()>PatientInfo.txt
                  File patientInbox = new File(fullName + "Messages.txt");    

                //Open the File Writer for adding new messages to the file
                  FileWriter fileWriter = new FileWriter(patientInbox, true);
                    //[we set 'true' so that the fileWriter can append to the file]

                //If the File exists append the new message to the end
                  if(patientInbox.exists() && !messageTxt.getText().isEmpty()){      
                    //Save the message sent to the global variable [so it can be used in deletion]
                    //Empty the global variable used for sending messages
                      this.messageSent = "[" + this.staffId + "]: " + messageTxt.getText() + "\n\n";
              
                    //Append the new message to the "<FullName>Message.txt"
                      fileWriter.append(this.messageSent);
              
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
              inboxTxt.setText(getMessages(dropDown.getValue()));
          
            //*/
          
          });


        //Delete Message
          deleteMessage.setOnAction(e -> {
            //Count the total number of messages sent by the user {i.e. count everytime we see: "[fullName]:"}

            //If the Staff user has NOT selected a user from the DropDown menu (they CANNOT add the message)
              if(dropDown.getValue() == null){
                //Display notification to user
                  messageTxt.setText("*Please Select a Patient via the Drop Down menu above to view and send messages.");
              
                //Stop the user here
                  return;
              }


            //If the last message sent is empty catch error
              if(this.messageSent == "" 
                  || this.messageSent == "*Please Select a Patient via the Drop Down menu above to view and send messages."
              ){
                //Stop code from progressing 
                  System.out.println("HeRE!");
                //Display notification to user
                  messageTxt.setText("*Can only delete the last message sent.");
              
                //Stop the user here
                  return;
              }


            //Get the patient's fullname (used for sending message to correct .txt)
              String fullName = dropDown.getValue().substring(0, dropDown.getValue().indexOf("/")-3).replaceAll(",", "");


          //Open the .txt file to delete the last message sent
            try{
              //Get the messages via our method...
                
                //If the message inbox is not empty...
                if(getMessages(dropDown.getValue()) != "<Inbox Empty>"){
                  //Write this into the <fullName>Messagest.txt
                    String editedStr = getMessages(dropDown.getValue()).replace(this.messageSent, "");
                  
                  //Edit the <fullName>Messages.txt to have the new inbox displayed!
                    File editedFile = new File(fullName.replaceAll(" ", "") + "Messages.txt");

                  //Open a file writer for writing the new text into the .txt file
                    FileWriter fileWriter0 = new FileWriter(fullName.replaceAll(" ", "") + "Messages.txt");

                  //If the string we made is not empty, update the .txt file
                    if(this.messageSent != ("[" + fullName + "]: \n\n")){
                      //Replace all of the text in the Messages.txt with the latest message deleted
                        fileWriter0.write(editedStr);
      
                      //Place new text into inbox being displayed
                        inboxTxt.setText(editedStr);

                      //Close the file writer
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
        //*/

        //Exit
          goBack.setOnAction(e-> {
            //Call upon displayPortal()
              displayPortal();
          });
      //=============================================================================


      //Alignments
      //==========================================================================================
        //Select Patient:
          VBox selectPatientSection = new VBox(selectPatientLbl, dropDown);
            //Set the alignment of the box??
              selectPatientSection.setAlignment(Pos.CENTER);
            //Set the padding!
              selectPatientSection.setPadding(new Insets(-25,0,0,0));


        //Horizontal Aligment of the Label & DropDown Menu
          HBox horizontal0 = new HBox(150, messageBrdLbl, selectPatientSection);
            //Set the padding
              horizontal0.setPadding(new Insets(0,0,0,50));


        //Message:
          VBox messageSection = new VBox(5, messageLbl, messageTxt);

        //Patient Phone Number Horizontal Alignment
          HBox horizontal1 = new HBox(5, contactInfoLbl, phoneNumTxt);
          
        //Inbox:
          VBox inboxSection = new VBox(5, inboxLbl, inboxTxt, horizontal1);


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
          VBox finAlign = new VBox(10, horizontal0, messageBoard, buttonContainer);
            //Set the alignment of the VBox
              finAlign.setAlignment(Pos.CENTER);
            //Set the background color of the entire page to gray
              finAlign.setStyle("-fx-background-color: #3A3A3A;");
      //==========================================================================================


      //Build the Scene
        Scene mainLayout = new Scene(finAlign, 1024, 768);
    
      //Return the Scene
        return mainLayout;
    }


//Getters & Setters
//-------------------------------------------------------------------------------------------------------
  //Returns all of the text within: <fullName>Messages.txt
    private String getMessages(String patientsCreds){
      //Get the user's Full Name via their credentials (used for <fullName>Message.txt)
        String fullName = patientsCreds.substring(0, patientsCreds.indexOf("/")-3).replaceAll(",", "");

      //Open the  File for reading and read every line and set the inboxTxt to it!!
        try{
          //Collect the Lines into a String variable
            String messageStr = "";

          //Open the file & if it exists post the text to the messageBoard
            File inboxFile = new File(fullName + "Messages.txt");

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
  //------------------------------------------------------------------------------
}