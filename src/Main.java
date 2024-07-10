/*********************************************************************************************************
[Contributors]:
  - John Bostater
    
  - Tristan Andrade

  - Austin Mayhew

  - Ryan Clark

  - Juan Rosas Jr.


[Description]:
  Driver File or Class/Object of the entire program "ArizonaMedical"
  It makes use of the other object that contain the data and methods of their respective file names.


[Library/JavaFX Info]:
  
  [External JARS/Libraries used]
    - JavaSE-22.0.1 {JRE System Library}
    - JavaFX22	    {java-sdk-22.0.1}			

  [Class path]
    - JavaSDK

      
[Etc]:
  //Note the commands below are written in Unix/Linux OS, hence the "/"
  //  For Windows users, use "\" for file path to Java SDK's lib

  + [Run Program from Objects created in 'bin' {Easiest way to run program!}]

    //Run the executable
      java --module-path "/path/to/javafx-sdk-22.0.1/lib" --add-modules javafx.controls,javafx.fxml Main


  + [Running Program in Terminal, compiling from .java files in 'src']

    //Compile/Build Object
      javac --module-path "/path/to/javafx-sdk-22.0.1/lib" --add-modules javafx.controls,javafx.fxml Main.java

    //Run the executable
      java --module-path "/path/to/javafx-sdk-22.0.1/lib" --add-modules javafx.controls,javafx.fxml Main


  + Virtual Machine Argument(s) [For running in EclipseIDE]:
  
    --module-path /path/to/javafx-sdk-22/lib --add-modules javafx.controls,javafx.fxml
    
  [NOTE] Change VM argument(s) via: 'Run Configurations' under 'Run as' in Eclipse
*********************************************************************************************************/


//Relevant JavaFX imports for our GUI
//------------------------------------
import javafx.application.Application;
import javafx.stage.Stage;
//------------------------------------


//Driver of program: "Arizona Medical"
public class Main extends Application{
	//Driver for our 'Main' Class/Object
  public static void main(String[] args) {
      launch(args);
  }

  //Sets up the GUI, along with the event handling & functionality of the program
  public void start(Stage primaryStage) {
    //Program Loop: 
    //-------------------------------------------------------------------------------------------------------
      //Title of the program
        primaryStage.setTitle("Arizona Medical");
    
      //Load the Welcome Page of the program and continue from there...
        //We pass primaryStage into the Portal Object to be used within the Object 
        //for seemless page transitions as well as displaying our GUI
        Portals driver = new Portals(primaryStage);
        
      //Call upon the public method to run the program
        driver.runProgram();      
    //-------------------------------------------------------------------------------------------------------
  }
}