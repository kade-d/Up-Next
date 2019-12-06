import bsu.edu.cs222.Games.Maze.MazeController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MazeTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void mazeAcceptsRightMovement() {
        MazeController mazeController = new MazeController();
        mazeController.keyboardBitSet.set(MazeController.KEY.RIGHT.getValue());
        mazeController.setBallVelocityFromBitSet();
        assertEquals(150.0, mazeController.circleXVelocity.get(), 0);
    }

    @Test
    public void mazeAcceptsLeftMovement() {
        MazeController mazeController = new MazeController();
        mazeController.keyboardBitSet.set(MazeController.KEY.LEFT.getValue());
        mazeController.setBallVelocityFromBitSet();
        assertEquals(-150.0, mazeController.circleXVelocity.get(), 0);
    }

    @Test
    public void mazeAcceptsUpMovement() {
        MazeController mazeController = new MazeController();
        mazeController.keyboardBitSet.set(MazeController.KEY.UP.getValue());
        mazeController.setBallVelocityFromBitSet();
        assertEquals(-150.0, mazeController.circleYVelocity.get(), 0);
    }

    @Test
    public void mazeAcceptsDownMovement() {
        MazeController mazeController = new MazeController();
        mazeController.keyboardBitSet.set(MazeController.KEY.DOWN.getValue());
        mazeController.setBallVelocityFromBitSet();
        assertEquals(150.0, mazeController.circleYVelocity.get(), 0);
    }

}