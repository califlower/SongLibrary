package view;

import application.Library;
import application.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {
	// List View
	@FXML
	ListView<Song> songList;
	private ObservableList<Song> obsList;
	// Song Detail Labels
	@FXML
	private Label songLabel;
	@FXML
	private Label artistLabel;
	@FXML
	private Label albumLabel;
	@FXML
	private Label yearLabel;
	// Add a New Song Text Fields
	@FXML
	private TextField songField;
	@FXML
	private TextField artistField;
	@FXML
	private TextField albumField;
	@FXML
	private TextField yearField;
	
	public void start() {
	 obsList = FXCollections.observableArrayList(Library.getList());
	 songList.setItems(obsList);
	 songList.getSelectionModel().select(0); // Selecting first song as default
	 
	 // Setting default song details
	 songLabel.setText(songList.getSelectionModel().getSelectedItem().getName());
	 artistLabel.setText(songList.getSelectionModel().getSelectedItem().getArtist());
	 albumLabel.setText(songList.getSelectionModel().getSelectedItem().getAlbum());
	 yearLabel.setText(songList.getSelectionModel().getSelectedItem().getYear());
	 
	 // Listens for song selection event, changes Song Detail accordingly
	 songList.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> songDetail());
	 
	}
	
	private void songDetail(){
		 songLabel.setText(songList.getSelectionModel().getSelectedItem().getName());
		 artistLabel.setText(songList.getSelectionModel().getSelectedItem().getArtist());
		 albumLabel.setText(songList.getSelectionModel().getSelectedItem().getAlbum());
		 yearLabel.setText(songList.getSelectionModel().getSelectedItem().getYear());
	}
	
    public void addSong(ActionEvent event){
    	Song s;
    	if(songField.getText().isEmpty() || artistField.getText().isEmpty()){
    		// Prompt user to enter a song name and/or artist name
    		 Alert invalidInput = new Alert(AlertType.INFORMATION); 
    		 //invalidInput.initOwner(maingStage); FIND OUT IF THIS IS NECESSARY
    		 invalidInput.setTitle("Invalid Input");
    		 invalidInput.setHeaderText("Unable to add song to the Song Library");
    		 invalidInput.setContentText("Please make sure to enter the Song Name and Artist Name");
    		 invalidInput.showAndWait();
    		return;
    	}
    	// Song add choices
    	if(albumField.getText().isEmpty() && yearField.getText().isEmpty()){
    		s = new Song(songField.getText(), artistField.getText());
    	}else if(!albumField.getText().isEmpty() && yearField.getText().isEmpty()){
    		s = new Song(songField.getText(), artistField.getText(), albumField.getText(), 0); 
    	}else if(albumField.getText().isEmpty() && !yearField.getText().isEmpty()){
    		s = new Song(songField.getText(), artistField.getText(), albumField.getText(), 1); 
    	}else{
    		s = new Song(songField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
    	}
    	
    	/* ADD A WAY TO CHECK FOR AN ATTEMPT TO ADD DUPLICATE ENTRIES; MAYBE TURN LIBRARY.ADDSONG TO CHECK AND RETURN A -1 IF FAILED
    	 * CHECK RETURN VALUE OF LIBRARY.ADDSONG AND HANDLE WITH A PROMPT TO THE USER
    	 */
    	
    	Library.addSong(s); // Adds song to library text file
    	obsList.add(s); // Adds song to observable list whilst application is currently running
    }
    
    public void removeSong(ActionEvent event){
    	
    }
    

}
