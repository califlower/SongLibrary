package view;

import application.Library;
import application.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
	@FXML
	ListView<String> songList;
	private ObservableList<String> obsList;
	
	public void start() {
	 obsList = FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App"); // replace with getList() later
	 songList.setItems(obsList);
	 songList.getSelectionModel().select(0);
	}
	
    public void addSong(ActionEvent event) {
       Song s = new Song("test");
       
    }

}
