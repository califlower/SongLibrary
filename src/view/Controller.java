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
	ListView<Song> songList;
	private ObservableList<Song> obsList;
	
	public void start() {
	 obsList = FXCollections.observableArrayList (Library.getList());
	 songList.setItems(obsList);
	 songList.getSelectionModel().select(0);
	}
	
    public void addSong(ActionEvent event) {
       Song s = new Song("test", "test", "test");
       Library.addSong(s);
       obsList = FXCollections.observableArrayList(Library.getList());
       System.out.println("Yerr");
    }

}
