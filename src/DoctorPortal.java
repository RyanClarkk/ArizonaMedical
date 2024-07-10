/****************************************************************************************
[Contributors]:
	- John Bostater
 	  
    - Tristan Andrade

    - Austin Mayhew

    - Ryan Clark

    - Juan Rosas Jr.

 	
[Description]:
	This Class/Object extends the functionality of the NursePortal.
	We extend since both Staff [Nurse & Doctor] have similar functions
	

[Functionality]:

  [Patient Visit Form]

  [View Patient Records]

  [Messages]
  
  [Logout]
  
	
[Aspect Ratio/Dimension of Graphical User Interface]: 

	- [4:3]	-->   [1024 x 768]	--	{Width x Height}
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

//Object/Class, sub-class of NursePortal
public class DoctorPortal extends NursePortal{
	//This object contains: 
	//  [Patient Visit Form]    [View Patient Records]  [Messages]  [Logout]

	//Data of the Object/Class
	//-------------------------------------------------------------------------------------------
	  //All of the 'protected' Data from the NursePortal Class is shared with the Doctor Class
	
	  //Flag for saving form
	  	private boolean examSaved;

	  //Flag for saving prescription
		private boolean prescriptionSaved;
	//-------------------------------------------------------------------------------------------


	//Constructor (since they are all kinda the same yanno?)
	//-------------------------------------------------------------------------------------------
	  public DoctorPortal(Stage primaryStage, Scene welcomeScene){
		//Call upon the same constructor of the NursePortal (since they act similar)
		  super(primaryStage, welcomeScene);
		  this.staffId = "Doctor";

		//Set the flags
		  examSaved = false;
		  prescriptionSaved = false;
	  }
	//-------------------------------------------------------------------------------------------


	//Methods
	//-------------------------------------------------------------------------------------------
	  //We will be using the same methods to that of the NursePortal but we will override them
	  //that way we can manipulate aspect of the stuff that we want to or whatever
	  //Display portal that has the same name....
	  

	  //Display the Main Page
	  @Override
	    public void displayPortal(){
		  //Set the Buttons, Labels, Action-Event, etc.  [In the future this may be moved to the constructor?]

		  //Labels
		  //========================================================================
			//Welcome!
			  Label header0= new Label("Welcome!");
			  	//Set the style & font of the label
				  header0.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: white;");
			
			//What would you like to do today?
			  Label header1 = new Label("What would you like to do today?");
			  	//Set the style & font of the label
				  header1.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");
		  //========================================================================


		  //Buttons
		  //========================================================================
			//Patient Visit Form
			  Button patientVisitForm = new Button("Patient Visit Form");
				//Set the Dimensions of the Button
				  patientVisitForm.setPrefSize(275, 50);
				  patientVisitForm.setMinSize(275, 50);
				  patientVisitForm.setMaxSize(275, 50);
				//Set the Font size of the text
				  patientVisitForm.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

			//The action - event handling for this button will call upon the newVisitForm() method

			//View Patient Records
			  Button viewPatientRecords = new Button("View Patient Records");
				//Set the Dimensions of the Button
				  viewPatientRecords.setPrefSize(300, 50);
				  viewPatientRecords.setMinSize(300, 50);
				  viewPatientRecords.setMaxSize(300, 50);
				//Set the Font size of the text
				  viewPatientRecords.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

			//Messages
			  Button messagesButton = new Button("Messages");
				//Set the Dimensions of the Button
				  messagesButton.setPrefSize(150, 50);
				  messagesButton.setMinSize(150, 50);
				  messagesButton.setMaxSize(150, 50);
				//Set the Font size of the text
				  messagesButton.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");


			//Logout
			  Button goBack = new Button("Logout");
				//Set the Dimensions of the Button
				  goBack.setPrefSize(125, 50);
				  goBack.setMinSize(125, 50);
				  goBack.setMaxSize(125, 50);
				//Set the Font size of the text
				  goBack.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
		  //========================================================================


		  //Action-Event Handling
		  //========================================================================
			//Patient Visit Form
			  patientVisitForm.setOnAction(e -> {
				//Load the newVisitForm() method
				  this.primeStage.setScene(newVisitForm());
				
				//Display the scene
				  this.primeStage.show();
			  });


			//View Patient Records
			  viewPatientRecords.setOnAction(e -> {
				//Call upon the method
				  this.primeStage.setScene(viewPatientRecords());
				
				//Display the Scene
				  this.primeStage.show();
			  });


			//Messages
			  messagesButton.setOnAction(e -> {
				//Call upon the SuperClass messages method
				  this.primeStage.setScene(this.messagePage());

				//Display the Scene
				   this.primeStage.show();
			  });


			//Logout
			  goBack.setOnAction(e -> {
				//Load & display the welcome page
				  primeStage.setScene(this.welcomePage);

				//Display the Scene
				  primeStage.show();
			  });
		  //========================================================================


		  //Alignment
		  //========================================================================
			//Vertical alignmnet of the buttons, labels & functionality
			  VBox vertical0 = new VBox(20, header0, header1, patientVisitForm, viewPatientRecords, messagesButton, goBack);
				//Set the alignment of the VBox
				  vertical0.setAlignment(Pos.CENTER);

		  	
			//Final Horizontal Alignment
			  HBox finalHorizontal = new HBox(vertical0);
				//Set the background color & other features
				  finalHorizontal.setStyle("-fx-background-color: #3A3A3A;");
				//Set the horizontal alignment
				  finalHorizontal.setAlignment(Pos.CENTER);
		  //========================================================================


		  //Set the Scene & Display it
		
		  //Build the Scene
			Scene mainLayout = new Scene(finalHorizontal , 1024, 768);

		  //Display the Scene
		  	this.primeStage.setScene(mainLayout);
			this.primeStage.show();
		}

	
	//Patient Visit Form
	//Conduct Examination Page
	  @Override
	  protected Scene conductExam(String patientCredentials){
		//Break apart the patientCredentials String & derive a fullName from it
		  String fullName = patientCredentials.substring(0, patientCredentials.indexOf("/")-3).replaceAll(",", "");
			//^This string will be used a lot in here

		//Labels
		//===========================================================================
		  //Doctor's Exam
		  	Label doctorsExamLbl = new Label("Doctor's Exam:");
			  //Set the size & font
			  	doctorsExamLbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

		  //Prescription
			Label prescriptionLbl = new Label("Prescription:");
			  //Set the size & font
			  	prescriptionLbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

		  //Prescription Script
		  	Label prescriptionScriptLbl = new Label("Prescription Script:");
			  //Set the size & font
			  	prescriptionScriptLbl.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");	


		  //Current Patient Credentials (Maybe swtich this to "Patient Name??")
			Label currentPatientCredsLbl = new Label("Current Patient:");
			  //Set the size & font
			  	currentPatientCredsLbl.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
			  //[Note]:
			  //	Next to this text label should be a text box with the patient's credentials loaded into it!!


		  //Patient's Pharmaceutical Provider
			Label pharmacyProvLbl = new Label("Patient's Pharmaceutical Provider:");
			  //Set the size & font
			  	pharmacyProvLbl.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");


		  //Doctor's Digital Signature
		  	Label signatureLbl = new Label("Doctor Signature:");
			  //Set the size & font
			  	signatureLbl.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		//===========================================================================


		//Text Boxes
		//=====================================================================================================
		  //Physical Exam & Other Notes
			TextArea doctorsExamTxt = new TextArea("<Physical Examination & Other Notes>");
			  //Set the style of the text within the box
			  	doctorsExamTxt.setStyle("-fx-font-size: 16px;");

			  //Set the dimension & style of the text area
			  	doctorsExamTxt.setPrefSize(600, 500);
			  	doctorsExamTxt.setMinSize(600, 500);
			  	doctorsExamTxt.setMaxSize(600, 500);
			  //Clear the Text Box upon the user clicking it for data entrance
			  	doctorsExamTxt.setOnMouseClicked(event -> {
				  //Clear the default text in the Text Box
				  	doctorsExamTxt.clear();
				});


		  //Patient's Name
		  //Display the current patient's name
			TextArea patientNameTxt = new TextArea(fullName);
			  //Set the dimension & style of the text area
			  	patientNameTxt.setPrefSize(175, 50);
			  	patientNameTxt.setMinSize(175, 50);
			  	patientNameTxt.setMaxSize(175, 50);
			  //Clear the Text Box upon the user clicking it for data entrance


		  //Prescription Script
			TextArea prescriptionScriptTxt = new TextArea("<Prescription Name and Dose>");
			  //Set the dimension & style of the text area
			  	prescriptionScriptTxt.setPrefSize(250, 300);
			  	prescriptionScriptTxt.setMinSize(250, 300);
			  	prescriptionScriptTxt.setMaxSize(250, 300);
			  //Clear the Text Box upon the user clicking it for data entrance
			  	prescriptionScriptTxt.setOnMouseClicked(event -> {
				  //Clear the default text in the Text Box
				  	prescriptionScriptTxt.clear();
				});
			  //Set the font size of the Text Box
			  	prescriptionScriptTxt.setStyle("-fx-font-size: 16px;");
			

		  //Current Patient's Pharmacy (Load the text from \t\t[Insurance Provider] from "PatientAccounts".txt)
			TextArea currentProvTxt = new TextArea("<Load from .txt file>");
			  //Set the dimension & style of the text area
			  	currentProvTxt.setPrefSize(250, 35);
			  	currentProvTxt.setMinSize(250, 35);
			  	currentProvTxt.setMaxSize(250, 35);
			  //Set the font size of the Text Box
			  	currentProvTxt.setStyle("-fx-font-size: 16px;");
			  //Make the text NOT editable
			  	currentProvTxt.setEditable(false);
			

		  //Digital Signature
		  	TextArea signatureTxt = new TextArea("<Write Signature Here>");
			  //Set the dimension & style of the text area
			  	signatureTxt.setPrefSize(250, 35);
			  	signatureTxt.setMinSize(250, 35);
			  	signatureTxt.setMaxSize(250, 35);
			  //Clear the Text Box upon the user clicking it for data entrance
			  	signatureTxt.setOnMouseClicked(event -> {
				  //Clear the default text in the Text Box
				  	signatureTxt.clear();
				});
			  //Set the font size of the Text Box
			  	signatureTxt.setStyle("-fx-font-size: 16px;");


		  //NEW!!!
		  //Load the Patient's insurance provider into the respective Text Box
		  //To do this, open Patient Accounts, use the patient credentials as a flag, activate flag once patient found
		  //Collect the line: \t[Insurance Provider]:	& then break the while loop (text file reading)
		  //Close the Scanner too!!

		  //Try-catch for any File IO Errors
			try{
			  //Open "PatientAccounts.txt"
				File patientAccountsFile = new File("PatientAccounts.txt");

			  //File reader (reads line-by-line)
				Scanner fileReader = new Scanner(patientAccountsFile);
			  
			  //Flag for data collection
			  	boolean patientFound = false;

			  //String that save Pharmacy provider
				String provStr = "Provider";

			  //Read the file & collect the Pharmacy Provider
			  	while(fileReader.hasNextLine()){
				  //Place the line read into a variable for manipulation
				  	String line = fileReader.nextLine();

				  //Patient Found, Activate data collection flag
					if(line.contains(patientCredentials)){
					  //NEW!!!
					  //DEBUG!!
					  //System.out.println("Patient Found!!");

					  //Advance the line
					  	line = fileReader.nextLine();

					  //Activate the flag for data collection
						patientFound = true;
					}

				  //Pharmacy Provider found, collect information
					if(line.contains("[Pharmacy Provider]: ") && patientFound){
					  //Collect the Pharmacy Provider
					  	provStr = line.substring(21, line.length());
					}

				  //Break the reading loop [data already collected]
					if(!line.contains("\t") && patientFound){
					  //Break the reading loop!
						break;
					}

				}


			  //Update the Text Box
			  	currentProvTxt.setText(provStr.trim());

			  //Close the file reader
			  	fileReader.close();
			}
			catch(IOException w){
			  //Do nothing
			}
		//=====================================================================================================


		//Buttons
		//=================================================================================================
		  //Submit Exam
			Button submitExam = new Button("Submit Examination");
			  //Set the dimensions & style of thee button
				submitExam.setPrefSize(225, 45);  
           	    submitExam.setMaxSize(225, 45);
           	    submitExam.setMinSize(225, 45);
			  //Set the style
			  	submitExam.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");


		  //Send Prescription
		  	Button sendPrescription = new Button("Send Prescription");
			  //Set the dimensions & style of thee button
				sendPrescription.setPrefSize(200, 45);  
           	    sendPrescription.setMaxSize(200, 45);
           	    sendPrescription.setMinSize(200, 45);
			  //Set the style
			  	sendPrescription.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
	

		  //Exit
		  	Button goBack = new Button("Exit");
			  //Set the dimensions & style of thee button
				goBack.setPrefSize(75, 45);  
           	    goBack.setMaxSize(75, 45);
           	    goBack.setMinSize(75, 45);
			  //Set the style
			  	goBack.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
		//=================================================================================================


		//Alignments
		//==========================================================================
		  //Vertical aligments of Recording Exam [Update Summary] & [Exit]

		  //Horizontal alignments of [Submit Exam] & [Exit]
		  	HBox buttonContainer = new HBox(20, submitExam, goBack);
			  //Set the alignment of the buttons!!
			  	buttonContainer.setAlignment(Pos.CENTER);


		  //Doctors Exam Txt (Background color set)
		  	VBox doctorsExamBox = new VBox(doctorsExamTxt);
			  //Set the size of the Box (this way we can have a consistent formatting)
			  	doctorsExamBox.setPrefSize(625, 525);
			  	doctorsExamBox.setMinSize(625, 525);
			  	doctorsExamBox.setMaxSize(625, 525);
			  //Set the background color & dimension of the box
			  	doctorsExamBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");
			  //Set the alignment of the text box within
			  	doctorsExamBox.setAlignment(Pos.CENTER);


		  //Notification Container
		  	HBox notificationContainer = new HBox();
			  //Set the alignment of the text within the box
			  	notificationContainer.setAlignment(Pos.CENTER);


		  //Doctors Exam
			VBox vertical0 = new VBox(10, doctorsExamLbl, doctorsExamBox, notificationContainer, buttonContainer);
			  //Set the Alignment
			  	vertical0.setAlignment(Pos.CENTER);


		  //Prescription Box
			VBox prescriptionBox = new VBox(10, prescriptionScriptLbl, prescriptionScriptTxt, pharmacyProvLbl, currentProvTxt, signatureLbl, signatureTxt);
			  //Set the Alignment
			  	prescriptionBox.setAlignment(Pos.CENTER);
			  //Set the size of the Box (this way we can have a consistent formatting)
			  	prescriptionBox.setPrefSize(275, 525);
			  	prescriptionBox.setMinSize(275, 525);
			  	prescriptionBox.setMaxSize(275, 525);
			  //Set the background color & dimension of the box
			  	prescriptionBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");


		  //Prescription
		  	VBox vertical1 = new VBox(10, prescriptionLbl, prescriptionBox, sendPrescription);
			  //Set the alignment of the items within
				vertical1.setAlignment(Pos.CENTER);
		  

		  //Encapsulating HBox (Contains: vertical0 & vertical1)
			HBox finalHorizontal = new HBox(20, vertical0, vertical1);
			  //Set the alignment of the HBox
			  	finalHorizontal.setAlignment(Pos.CENTER);
			  //Set the background color of the entire Scene/Page
			  	finalHorizontal.setStyle("-fx-background-color: #3A3A3A;");
		//==========================================================================


		//Action-Event Handling
		//=================================================================================================
		  //find the patient via the dropdown menu credentials &

		  //Submit Exam
			submitExam.setOnAction(e -> {
				
			  //Catch any IO Errors in file writing
				try{
				  //File Writer
				  	FileWriter fileWriter = new FileWriter(fullName + "VisitSummarys.txt", true);

				  //If the form is not already saved, save it!
				  	if(!examSaved){
					  //Remove

					  //Add notification that the Exam has been successfully recorded
					  	//notificationContainer.getChildren().add(this.notificationLbl);

					  //Append the text to the end of the .txt file
						fileWriter.append("[Doctor's Exam]:\n" + doctorsExamTxt.getText().trim() + "\n\n");
					
					  //Set the flag
					  	examSaved = true;
					}

				  //Close the file writer
				  	fileWriter.close();
				}
				catch(IOException a){
				  //Do Nothing...
				}

			});


		  //Send Prescription
			sendPrescription.setOnAction(e -> {
			  //Append the Prescription & all the other text fields to the <fullName>VisitSummarys.txt
			
			  //Catch any errors in the file writing
				try{
				  //File Writer
					FileWriter fileWriter = new FileWriter(fullName + "VisitSummarys.txt", true);
					  //We set 'true' so the text can be appended to any existing file!	


				  //Save The prescription data only once
					if(!prescriptionSaved){
				 	  //Append the text to the end of the .txt file
						fileWriter.append("[Prescription]:" + prescriptionScriptTxt.getText().trim() 
							+ "\n[Pharmaceutical Provider]: " + currentProvTxt.getText().trim() 
							+ "\n[Signature]: " + signatureTxt.getText() 
							+ "\n\n");

					  //Set the flag
					  	prescriptionSaved = true;
					}

				  //Close the file writer
				  	fileWriter.close();
				}
				catch(IOException a0){
				  //Do Nothing...
				}
			});



		  //Exit
		    goBack.setOnAction(e -> {
			  //Call upon the "newVistForm" method 
			
			  //Set the Scene
				this.primeStage.setScene(this.newVisitForm());
			
			  //Display the Scene
			  	this.primeStage.show();
			});
		//=================================================================================================



		//Build the Conduct Exam Scene
		  Scene examScene = new Scene(finalHorizontal, 1024, 768);

		//Return the Conduct Exam Scene
		  return examScene;
	  }


	//View Patient Records
	  private Scene viewPatientRecords(){
		//Labels, Buttons, Text Boxes, Alignment, Action-Event Handling, Scene

		//2 DROPDOWN MENUS:
		//		- Select Patient		(dropDown0)
		//		- Select Visit to view	(dropDown1)


        //Labels
        //============================================================================================
		  //Select Patient
		  	Label header0 = new Label("Select Patient:");
			  //Set the size & font
			  	header0.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");

          //Select visit to view
            Label header1 = new Label("Select Visit to view:");
              //Set size of text
                header1.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");

          //Visit summary
            Label header2 = new Label("Visit Summary:");
              //Set size of text
                header2.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");
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
			//Set the padding
			  exitPage.setAlignment(Pos.CENTER);
        //=========================================================================================================



        //DropDown #1 [Select Patient]
        //==========================================================================================
		  //Drop Down for selecting a patient
			ComboBox<String> dropDown0 = new ComboBox<>();
              //Set the Dimensions of the drop down menu
              //[Width x Height]
                dropDown0.setPrefSize(300, 35);
                dropDown0.setMinSize(300, 35);
                dropDown0.setMaxSize(300, 35);
              //Set the font of the text within
                dropDown0.setStyle("-fx-font-size: 16px;");

		  //Collect all of the Patient Names and place them into the ComboBox
			try{
			  //File Reader for collecting all of the patient Names
			  	Scanner fileReader0 = new Scanner(new File("PatientAccounts.txt"));

			  //Start reading the file
			  	while(fileReader0.hasNext()){
				  //Collect the line read and place it into a String
				  	String line = fileReader0.nextLine();

				  //Place the patient credentials into the dropDown menu
				  	if(!line.contains("Patient Accounts:") && !line.contains("\t") && !line.isEmpty()){
					  //Save the Patient Credentials
					  	dropDown0.getItems().add(line);
					}
				}

			  //Close the file reader
			  	fileReader0.close();
			}
			catch(IOException h1){
			  //Do nothing
			}

		  //NOTE!!!
		  //Drop down #2 cannot operate without Drop Down #1!!
        //==========================================================================================


        //DropDown #2 [Select Visit]
        //==========================================================================================
          //Create the dropDown menu object
            ComboBox<String> dropDown1 = new ComboBox<>();
              //Set the Dimensions of the drop down menu
              //[Width x Height]
                dropDown1.setPrefSize(200, 35);
                dropDown1.setMinSize(200, 35);
                dropDown1.setMaxSize(200, 35);
              //Set the font of the text within
                dropDown1.setStyle("-fx-font-size: 16px;");
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
		  //Hash Map used for storing Visit Summarys to each visit date
		  	HashMap<Integer, String> summaryMap = new HashMap<>();

          //User select's an exam date from the dropdown menu
            dropDown0.setOnAction(event -> { 
              //Get the Date String that is currently selected by the user
              //Then strip the String 
			 
			  //Debug
				//if(dropDown1.getValue() != null){
					//Set the value to null?
					
					//dropDown1.setValue(null);

					//return;
				//}

			
			  //Clear all of the old strings from the DropDown Menu
				dropDown1.getItems().clear();
			  //Deselect the selected items in the "Select Visit" DropDown menu
			  //Set the HashMap to null (i.e. empty any previous input!)
			  	summaryMap.clear();

			  //Clear the old Summary from the text
			  	visitSummaryTxt.clear();	//Kind of redundant 



			  //Get the Patient's FullName via their credentials (to be used later)
				String fullName = dropDown0.getValue().substring(0, dropDown0.getValue().indexOf("/")-3).replaceAll(",", "");

			  //Integer that stores/uses the Date's numbers as a Key
				Integer uniqueKeyInt = null; 
			
			  //String that stores the unique key Integer stripped from: "Date: <date here>"
				String uniqueKeyStr = "";

			  //String that stores the visit summary (all text below patient being found)
				String visitSummary = ""; //These will be added to the hashMap
				//We will be able to access these in constant time via the action event-handling

			  //String array for the exam dates (this will be useful for)
				String[] examDates = new String[50];


			  //Fill the ComboBox with all of the Visit Dates via: PatientSummary.txt
				try{
				  //File that contains the Patient's Visit Summarys
				  	File visitSummarysFile = new File(fullName + "VisitSummarys.txt");

				  //See if the file exists, if it doesn't then display message and skip
				  	if(!visitSummarysFile.exists()){
					  //Display the error message
						visitSummaryTxt.setText("<This Patient has no Visits>");

					  //Return to "break"
						return;
					}


				  //Read the File with a scanner (easier to get Line by Line)
					Scanner fileReader = new Scanner(visitSummarysFile);


				  //Flag that "puts on the brakes" of the fileReader so it will
					boolean dateFound = false;
		
				  //Collect the String line by line
					String line = "";

				  //Instantiate the String array 
					for(short i = 0; i < 50; i++){
					  //Place the empty string "" into the indexed position
						examDates[i] = "";
					}

				  //Counter for getting the exam number
					short counter = 0;

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
					
					  //If the key does not exist skip
						if(uniqueKeyStr != ""){
					      //Add the unique Integer [Visit Date] & visit String [Summary] to the hashmap
							summaryMap.put(uniqueKeyInt, visitSummary);
						}
					}	

				  //Close the fileReader
					fileReader.close();
				}
				catch(IOException e2){
				  //Error Print
					System.out.println("File Not Found!!");
				}


			  //Add all of the exam Dates to the ComboBox
				for(int i = 0; i < 50; i++){
				  //Add exam dates
					if(examDates[i] != ""){
					  //Add the Exam date to the box
						dropDown1.getItems().add(examDates[i]);
					}
				  //Else, help the garbage collector
					//else{
					  //Set the indexed value to null
						examDates[i] = null;
					//}
				}
            });


		  //Load the text upon the Selection of a Visit date by the Doctor
		  	dropDown1.setOnAction(event -> {
				if(!dropDown0.getValue().isEmpty()){
				  //This gets the unique integer Id that will load the summary via the hashMap
					visitSummaryTxt.setText(summaryMap.get(Integer.parseInt(dropDown1.getValue().replaceAll("\\D", ""))));
				}
			});


          //Exit
            exitPage.setOnAction(e -> {
              //Load the Patient Portal page/scene
                displayPortal();
            });
        //=====================================================


        //Alignment
        //=================================================================
		  //Button Container
		  	HBox buttonContainer = new HBox(exitPage);

		  //Make a horzionatal space for all of the headers 
		  	HBox horizontal0 = new HBox(150, header0, header1, buttonContainer);
				
		  //Make a horizontal space for the Drop Downs & exit button
			HBox horizontal1 = new HBox(50, dropDown0, dropDown1, exitPage);


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
            VBox vertical2 = new VBox(10, horizontal0, horizontal1, header2, summaryBox);

          //Horizontally align the VBox [Final box & adjustment]
            HBox horizontal2 = new HBox(vertical2);
              //Set the background color for the whole page
                horizontal2.setStyle("-fx-background-color: #3A3A3A;");

          //Set the adjustment
            horizontal2.setAlignment(Pos.CENTER);        
        //=================================================================


        //Setup Scene
          Scene mainLayout = new Scene(horizontal2, 1024, 768);

        //Return Scene
          return mainLayout;
	  }
	//-------------------------------------------------------------------------------------------
}