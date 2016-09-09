import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by anmu on 08/09/2016.
 */
public class GameFlowControllerTest {
    @Test
    public void prepareForBattleValidNumberOfShips() throws Exception {
        GameFlowController gameFlowController = new GameFlowController(keypresses("1\n1\n1\n\n2\n2\n1\n\n"));
        gameFlowController.prepareForBattle(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void prepareForBattleInvalidNumberOfShipsLowerBound() throws Exception {
        GameFlowController gameFlowController = new GameFlowController();
        gameFlowController.prepareForBattle(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void prepareForBattleInvalidNumberOfShipsUpperBound() throws Exception {
        GameFlowController gameFlowController = new GameFlowController();
        gameFlowController.prepareForBattle(6);
    }

    @Test
    public void battleCommencesPlayer1WinsInstantly() throws Exception {
        GameFlowController gameFlowController = new GameFlowController(keypresses("1\n1\n2\n\n2\n2\n2\n\n2\n2\n\n"));
        gameFlowController.prepareForBattle(1);
        gameFlowController.battleCommences();
    }

    @Test
    public void battleCommencesPlayer2WinsInstantly() throws Exception {
        GameFlowController gameFlowController = new GameFlowController(keypresses("1\n1\n2\n\n2\n2\n2\n\n2\n1\n\n1\n1\n\n"));
        gameFlowController.prepareForBattle(1);
        gameFlowController.battleCommences();
    }


    private InputStream keypresses(String s) {
        return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
    }

    private InputStream keypresses(Integer[] i) {
        return keypresses(Arrays.toString(i).replace(", ","\n").replace("[","").replace("]",""));
    }
}