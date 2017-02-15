package view;

import application.Library;
import application.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Collections;
import java.util.Optional;

public class Controller
{
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
    // Edit Song Text Fields
    @FXML
    private TextField editSong;
    @FXML
    private TextField editArtist;
    @FXML
    private TextField editAlbum;
    @FXML
    private TextField editYear;
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


            editSong.setText(songList.getSelectionModel().getSelectedItem().getName());
            editArtist.setText(songList.getSelectionModel().getSelectedItem().getArtist());
            editAlbum.setText(songList.getSelectionModel().getSelectedItem().getAlbum());
            editYear.setText(songList.getSelectionModel().getSelectedItem().getYear());


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
        if (!obsList.isEmpty())
        {

            Song x = songList.getSelectionModel().getSelectedItem();

            if (x == null)
                return;

            if (x.getName() != null)
            {
                songLabel.setText(x.getName());
                editSong.setText(x.getName());
            } else
            {
                songLabel.setText("");
                editSong.setText("");
            }


            if (x.getArtist() != null)
            {
                artistLabel.setText(x.getArtist());
                editArtist.setText(x.getArtist());
            } else
            {
                artistLabel.setText("");
                editArtist.setText("");
            }


            if (x.getAlbum() != null)
            {
                albumLabel.setText(x.getAlbum());
                editAlbum.setText(x.getAlbum());
            } else
            {
                albumLabel.setText("");
                editAlbum.setText("");
            }

            if (x.getYear() != null)
            {
                yearLabel.setText(x.getYear());
                editYear.setText(x.getYear());
            } else
            {
                yearLabel.setText("");
                editYear.setText("");
            }

        }
    }

    public void addSong(ActionEvent event)
    {
        Song s;

        if (songField.getText().isEmpty() || artistField.getText().isEmpty())
        {
            // Prompt user to enter a song name and/or artist name
            Alert invalidInput = new Alert(AlertType.INFORMATION);
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

        if (Library.libContains(s))
        {
            Alert invalidInput = new Alert(AlertType.INFORMATION);
            invalidInput.setTitle("Invalid Input");
            invalidInput.setHeaderText("Unable to add song to the Song Library");
            invalidInput.setContentText("Duplicate Song");
            invalidInput.showAndWait();
            return;
        }



        Library.addSong(s); // Adds song to library text file
        obsList.add(s); // Adds song to observable list whilst application is currently running

        Collections.sort(obsList);
        
        songList.getSelectionModel().select(s);
    }

    public void removeSong(ActionEvent event)
    {

        if (obsList.isEmpty())
        {
            Alert invalidInput = new Alert(AlertType.INFORMATION);
            //invalidInput.initOwner(maingStage); FIND OUT IF THIS IS NECESSARY
            invalidInput.setTitle("Can not remove");
            invalidInput.setHeaderText("Empty library");
            invalidInput.setContentText("Please make sure to have songs in the library");
            invalidInput.showAndWait();
            return;
        }


        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deletion Confirmation");
        alert.setHeaderText("Are you sure you want to remove the song?");
        alert.setContentText("Click OK or Cancel");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK)
        {
        	int remove = songList.getSelectionModel().getSelectedIndex(); // index of song to be deleted
        	if(remove != obsList.size() - 1) // select the next song if there is one and if not the previous song will be selected
        	{
        		songList.getSelectionModel().selectNext();
        	}
            Library.removeSong(remove);
            obsList.remove(remove);
            alert.close();

            if (obsList.isEmpty())
            {
                songLabel.setText("");
                artistLabel.setText("");
                albumLabel.setText("");
                yearLabel.setText("");

                editSong.setText("");
                editArtist.setText("");
                editAlbum.setText("");
                editYear.setText("");
            }
        } else
        {
            alert.close();
        }
    }

    public void editSong(ActionEvent event)
    {
        if (obsList.isEmpty())
        {
            Alert invalidInput = new Alert(AlertType.INFORMATION);
            invalidInput.setTitle("Can not Edit");
            invalidInput.setHeaderText("Empty library");
            invalidInput.setContentText("Please make sure to have songs in the library");
            invalidInput.showAndWait();
            return;
        }
        else
        {
            // Set the Song Name and Artist Name Fields if the user has left them empty
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Edit Confirmation");
            alert.setHeaderText("Are you sure you want to edit the song?");
            alert.setContentText("Click OK or Cancel");

            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() != ButtonType.OK)
                return;
        }

        Song s;

        if (editSong.getText().isEmpty() || editArtist.getText().isEmpty())
        {
            // Prompt user to enter a song name and/or artist name
            Alert invalidInput = new Alert(AlertType.INFORMATION);
            invalidInput.setTitle("Invalid Input");
            invalidInput.setHeaderText("Unable to edit song in the Song Library");
            invalidInput.setContentText("Please make sure to enter the Song Name and Artist Name");
            invalidInput.showAndWait();
            return;
        }
        else
        {
            s = new Song(editSong.getText(), editArtist.getText(), editAlbum.getText(), editYear.getText());
        }

        if (Library.libContains(s))
        {
            Alert invalidInput = new Alert(AlertType.INFORMATION);
            invalidInput.setTitle("Invalid Input");
            invalidInput.setHeaderText("Unable to edit song to the Song Library");
            invalidInput.setContentText("Duplicate Song");
            invalidInput.showAndWait();
            return;
        }

        Library.editSong(s, songList.getSelectionModel().getSelectedIndex());
        obsList.set(songList.getSelectionModel().getSelectedIndex(), s); // Adds song to observable list whilst application is currently running

        Collections.sort(obsList);


        if (obsList.size() == 1)
            songList.getSelectionModel().select(0); // Selecting first song as default


    }


}
