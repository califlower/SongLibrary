package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SongLib extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException 
	{
		Parent root = FXMLLoader.load(getClass().getResource("SongGUI.fxml"));
        
        primaryStage.setTitle("Song Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();



        ListView<String> list = (ListView) root.lookup("#songList");
        
        ObservableList<String> items =FXCollections.observableArrayList (
        	    "Single", "Double", "Suite", "Family App");
        	list.setItems(items);

		list.getSelectionModel().select(0);

	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
