import bsu.edu.cs222.Games.Simon.SimonController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimonTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    @Test
    public void simonQuestionIsLengthSix() {
        SimonController simon = new SimonController();
        for(int i = 0; i < 6; i++) {
            simon.generateOrder();
        }
        String question = simon.question;
        assertEquals(6, question.length());
    }
}
