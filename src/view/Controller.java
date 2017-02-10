package view;

import application.Library;
import application.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

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
	
	public void start()
	{

		obsList = FXCollections.observableArrayList(Library.getList());
        songList.setItems(obsList);


        if (!obsList.isEmpty())
        {
            songList.getSelectionModel().select(0); // Selecting first song as default

            // Setting default song details
            songLabel.setText(songList.getSelectionModel().getSelectedItem().getName());
            artistLabel.setText(songList.getSelectionModel().getSelectedItem().getArtist());
            albumLabel.setText(songList.getSelectionModel().getSelectedItem().getAlbum());
            yearLabel.setText(songList.getSelectionModel().getSelectedItem().getYear());
        }

         // Listens for song selection event, changes Song Detail accordingly
        songList.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> songDetail());


        songList.setCellFactory(lv -> new ListCell<Song>()
        {
            public void updateItem(Song i, boolean e)
            {
                super.updateItem(i, e);
                if (e)
                    setText(null);
                else
                {
                    setText(i.getName());
                }
            }
        });
	 
	}
	
	private void songDetail()
	{
		 songLabel.setText(songList.getSelectionModel().getSelectedItem().getName());
		 artistLabel.setText(songList.getSelectionModel().getSelectedItem().getArtist());
		 albumLabel.setText(songList.getSelectionModel().getSelectedItem().getAlbum());
		 yearLabel.setText(songList.getSelectionModel().getSelectedItem().getYear());
	}
	
    public void addSong(ActionEvent event)
    {
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
    	else
        {
            s = new Song(songField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
        }


    	
    	/* ADD A WAY TO CHECK FOR AN ATTEMPT TO ADD DUPLICATE ENTRIES; MAYBE TURN LIBRARY.ADDSONG TO CHECK AND RETURN A -1 IF FAILED
    	 * CHECK RETURN VALUE OF LIBRARY.ADDSONG AND HANDLE WITH A PROMPT TO THE USER
    	 */
    	
    	Library.addSong(s); // Adds song to library text file
    	obsList.add(s); // Adds song to observable list whilst application is currently running
    }
    
    public void removeSong(ActionEvent event)
    {
        Library.removeSong(songList.getSelectionModel().getSelectedIndex());
        obsList.remove(songList.getSelectionModel().getSelectedIndex());
    }
    

}
