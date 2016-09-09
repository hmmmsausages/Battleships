import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

/**
 * Created by anmu on 08/09/2016.
 */
public class GameLogicTest {
    @Test
    public void getOrientationVerticalSelected() throws Exception {
        GameLogic gameLogic = new GameLogic(keypresses("1\n"));
        gameLogic.getOrientation();
    }

    @Test
    public void getOrientationHorizontalSelected() throws Exception {
        GameLogic gameLogic = new GameLogic(keypresses("2\n"));
        gameLogic.getOrientation();
    }

    @Test(expected = InputMismatchException.class)
    public void getOrientationInvalidInput() throws Exception {
        GameLogic gameLogic = new GameLogic(keypresses("error\n"));
        gameLogic.getOrientation();
    }

    @Test
    public void placeShipAfterUserInputValidInput() throws Exception {
        Player player = new Player();
        GameLogic gameLogic = new GameLogic(keypresses("9\n6\n2\n"));
        gameLogic.placeShipAfterUserInput(player,1);
        assertEquals(GridState.SHIP,player.getOceanGrid().getGrid()[9][6]);
    }

    @Test
    public void placeShipAfterUserInputInvalidInputFollowedByValidInput() throws Exception {
        Player player = new Player();
        GameLogic gameLogic = new GameLogic(keypresses("91\n6\n1\n4\n5\n1\n"));
        gameLogic.placeShipAfterUserInput(player,1);
    }

    @Test
    public void shootAfterUserInputValidInputMiss() throws Exception {
        Player attacker = new Player();
        Player defender = new Player();
        GameLogic gameLogic = new GameLogic(keypresses("9\n6\n"));
        gameLogic.shootAfterUserInput(attacker, defender);
        assertEquals(GridState.MISS,attacker.getTargetGrid().getGrid()[9][6]);
    }

    @Test
    public void shootAfterUserInputValidInputHIT() throws Exception {
        Player attacker = new Player();
        Player defender = new Player();
        GameLogic gameLogic = new GameLogic(keypresses("9\n6\n2\n9\n6\n"));
        gameLogic.placeShipAfterUserInput(defender, 1);
        gameLogic.shootAfterUserInput(attacker,defender);
        assertEquals(GridState.HIT,attacker.getTargetGrid().getGrid()[9][6]);
    }

    @Test
    public void shootAfterUserInputValidInputPreviousShotThenValidShot() throws Exception {
        Player attacker = new Player();
        Player defender = new Player();
        GameLogic gameLogic = new GameLogic(keypresses("9\n6\n9\n6\n9\n5\n"));
        gameLogic.shootAfterUserInput(attacker,defender);
        gameLogic.shootAfterUserInput(attacker,defender);
    }

    @Test
    public void shootAfterUserInputInvalidInputFollowedByValidInput() throws Exception {
        Player attacker = new Player();
        Player defender = new Player();
        GameLogic gameLogic = new GameLogic(keypresses("91\n6\n4\n5\n"));
        gameLogic.shootAfterUserInput(attacker,defender);
    }

    @Test
    public void getCoordinateValidInput() throws Exception {

        GameLogic gameLogic = new GameLogic(keypresses("9\n6\n"));
        gameLogic.getCoordinates();
    }

    @Test(expected = InputMismatchException.class)
    public void getCoordinateInvalidInput() throws Exception {
        GameLogic gameLogic = new GameLogic(keypresses("a\n6\n"));
        gameLogic.getCoordinates();
    }


    private InputStream keypresses(String s) {
        return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
    }

    private InputStream keypresses(Integer[] i) {
        return keypresses(Arrays.toString(i).replace(", ","\n").replace("[","").replace("]",""));
    }

}