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
        ArrayList<Game> sortedGames = sortGamesByScore(games);
        populateListView(sortedGames);
    }

    private void populateListView(ArrayList<Game> gamesSorted) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Game game : gamesSorted) {
            observableList.add("User: " + game.getUsername() + "*** Score: " + game.getScore());
        }
        scoreboard.setItems(observableList);
    }

    private ArrayList<Game> sortGamesByScore(ArrayList<Game> games) {
        for (int i = 0; i < games.size() - 1; i++) {
            if (Float.parseFloat(games.get(i).getScore()) < Float.parseFloat(games.get(i + 1).getScore())) {
                Game tempGame = games.get(i);
                games.set(i, games.get(i + 1));
                games.set(i + 1, tempGame);
            }
        }
        return games;
    }
}
