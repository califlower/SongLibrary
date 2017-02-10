package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Controller;

public class SongLib extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException 
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
		getClass().getResource("/view/SongGUI.fxml"));
		AnchorPane root = (AnchorPane)loader.load(); 
		
		Controller Controller =
				 loader.getController();
				 Controller.start(); 
        
        primaryStage.setTitle("Song Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        //unitTest.testAll();

	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
