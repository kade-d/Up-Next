package bsu.edu.cs222.UIComponents;

import bsu.edu.cs222.FileIO.FileIO;
import bsu.edu.cs222.FileIO.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class ScoreboardController {

    @FXML
    private ListView<String> scoreboard;

    public void initialize() {
        FileIO fileIO = new FileIO();
        String filePath = fileIO.findXMLPath();
        ArrayList<Game> games = fileIO.readXML(filePath);
        populateListView(games);
    }

    private void populateListView(ArrayList<Game> games) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Game game : games) {
            observableList.add("Score: " + game.getScore());
        }
        scoreboard.setItems(observableList);
    }
}
