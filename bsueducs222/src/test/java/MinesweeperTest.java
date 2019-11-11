import bsu.edu.cs222.Minesweeper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MinesweeperTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void minesweeperCellsAreFlaggedCorrectly() {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.gameState.flagCell(0);
        assertTrue(minesweeper.gameState.flaggedCells[0]);
    }
}
