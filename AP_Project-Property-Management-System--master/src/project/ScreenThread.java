package project;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.lang.Thread;

public class ScreenThread extends Thread 
{
	public void run(String fileName) throws IOException
	{
		Parent window;
	    window = FXMLLoader.load(getClass().getResource(fileName));
	    Stage mainStage;
	    mainStage = Main.parentWindow;
	    mainStage.getScene().setRoot(window); 
	}
}
