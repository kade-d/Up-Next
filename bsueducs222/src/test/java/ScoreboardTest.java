import bsu.edu.cs222.FileIO.Game;
import bsu.edu.cs222.UIComponents.ScoreboardController;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class ScoreboardTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void LeastToGreatestSortingWorks() {
        Game firstGame = new Game("firstGame", true, "999", "Test");
        Game secondGame = new Game("secondGame", true, "0", "Test");

        ArrayList<Game> games = new ArrayList<>();
        games.add(firstGame);
        games.add(secondGame);

        ScoreboardController scoreboardController = new ScoreboardController();
        ArrayList<Game> sortedGames = scoreboardController.sortGamesByScore(games);
        assertTrue(Integer.parseInt(sortedGames.get(0).getScore()) < Integer.parseInt(sortedGames.get(1).getScore()));

    }
}
