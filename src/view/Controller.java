package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
	@FXML
	ListView<String> songList;
	private ObservableList<String> obsList;
	
	public void start() {
	 obsList = FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App");
	 songList.setItems(obsList); 
	}

}
