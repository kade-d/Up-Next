package bsu.edu.cs222;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import bsu.edu.cs222.TicTacToe;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
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
}
