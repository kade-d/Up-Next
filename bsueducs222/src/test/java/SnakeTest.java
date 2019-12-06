import bsu.edu.cs222.Controller;
import bsu.edu.cs222.Games.Snake.SnakeGameState;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SnakeTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    @Test
    public void snakeStartsInCorrectPosition() {
        Controller controller = new Controller();
        SnakeGameState snake = new SnakeGameState(controller, 25, 25);
        ArrayList<Integer> snakePositions = snake.snake;
        assertEquals(0, (int) snakePositions.get(0));
        assertEquals(1, (int) snakePositions.get(1));
        assertEquals(2, (int) snakePositions.get(2));
    }
}
