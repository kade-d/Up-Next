import bsu.edu.cs222.Games.Minesweeper.Minesweeper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MinesweeperTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void minesweeperCellsAreFlaggedCorrectly() {
        Minesweeper minesweeper = new Minesweeper(9);
        minesweeper.gameState.flagCell(0);
        assertTrue(minesweeper.gameState.flaggedCells[0]);
    }
}
