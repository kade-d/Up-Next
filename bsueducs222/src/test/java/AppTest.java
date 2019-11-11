import bsu.edu.cs222.TicTacToe;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    @Test
    public void ticTacToeCellsAreFlaggedCorrectly() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playerMove(5);
        assertEquals(ticTacToe.gameState.cells[5], 1);
    }

    @Test
    public void ticTacToeVictoryIsDetected() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playerMove(0);
        ticTacToe.playerMove(1);
        ticTacToe.playerMove(2);
        assertEquals("Player",ticTacToe.victor);
    }

    @Test
    public void ticTacToeRowsAreCorrectlyMarked() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playerMove(4);
        assertEquals(1, ticTacToe.gameState.row2[1]);
    }

    @Test
    public void ticTacToeColumnsAreCorrectlyMarked() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playerMove(4);
        assertEquals(1, ticTacToe.gameState.col2[1]);
    }

    @Test
    public void ticTacToeDiagonalsAreCorrectlyMarked() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playerMove(4);
        assertEquals(1, ticTacToe.gameState.diag1[1]);
    }
}