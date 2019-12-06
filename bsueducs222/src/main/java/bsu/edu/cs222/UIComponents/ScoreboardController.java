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

    public ArrayList<Game> sortGamesByScore(ArrayList<Game> games) {
        for (int i = 0; i < games.size() - 1; i++) {
            for (int j = 0; j < games.size() - i - 1; j++) {

                if (Float.parseFloat(games.get(j).getScore()) > Float.parseFloat(games.get(j + 1).getScore())) {
                    Game tempGame = games.get(j);
                    games.set(j, games.get(j + 1));
                    games.set(j + 1, tempGame);
                }

            }
        }

        return games;
    }
}
