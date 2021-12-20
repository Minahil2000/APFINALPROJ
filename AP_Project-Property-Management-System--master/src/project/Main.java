package project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application
{

	//Inside the main() method, we can launch our application using Application.launch().
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public static Stage parentWindow;
	public void start(Stage stage) throws Exception 
	{
	    parentWindow = stage;

	    Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

	    Scene scene = new Scene(root);

	    stage.setScene(scene);
	    stage.show();
	}

}
