import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by anmu on 08/09/2016.
 */
public class GameFlowController {

    private Scanner reader;
    private GameLogic gameLogic;
    private Player player1;
    private Player player2;


    public GameFlowController() {
        this(System.in);
    }

    protected GameFlowController(InputStream keypresses) {
        reader = new Scanner(keypresses);
        gameLogic = new GameLogic(reader);
        newGame();
    }

    public void newGame() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    public void prepareForBattle(int numberOfShips){
        if (numberOfShips < 1 || numberOfShips > 5) {
            throw new IllegalArgumentException("Invalid number of ships.");
        }

        printBeginScreen();
        printGrids(player1);
        for (int i = numberOfShips; i > 0; i--) {
            System.out.println("Player 1 please place your ship of length \"" + i + "\"");
            gameLogic.placeShipAfterUserInput(player1, i);
            clearScreen();
            printGrids(player1);
        }
        waitForNextPlayer(player2);

        printBeginScreen();
        printGrids(player2);
        for (int i = numberOfShips; i > 0; i--) {
            System.out.println("Player 2 please place your ship of length \"" + i + "\"");
            gameLogic.placeShipAfterUserInput(player2, i);
            clearScreen();
            printGrids(player2);
        }
        waitForNextPlayer(player1);
    }

    public void battleCommences() {
        GridState shotResult = GridState.EMPTY;
        while (true) {

            printGrids(player1);

            System.out.println("Player 1 take your shot:");
            shotResult = gameLogic.shootAfterUserInput(player1, player2);
            printShotResult(shotResult);
            printGrids(player1);
            if (!player2.getOceanGrid().anyShipsLeft()) {
                printGameResult(player1);
                break;
            }
            waitForNextPlayer(player2);



            printGrids(player2);
            System.out.println("Player 2 take your shot:");
            shotResult = gameLogic.shootAfterUserInput(player2, player1);
            printShotResult(shotResult);
            printGrids(player2);
            if (!player1.getOceanGrid().anyShipsLeft()) {
                printGameResult(player2);
                break;
            }
            waitForNextPlayer(player1);
        }
    }

    private void printShotResult(GridState gridState){
        switch (gridState){
            case HIT:
                clearScreen();
                System.out.println("==========================\n\tYOU HIT!\n==========================");
                break;
            default:
                clearScreen();
                System.out.println("==========================\n\tYOU MISSED!\n==========================");
                break;
        }
    }

    private void printGrids(Player player){
        player.getTargetGrid().printGrid();
        player.getOceanGrid().printGrid();
    }

    private void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private void waitForNextPlayer(Player nextPlayer){
        System.out.println("==========================");
        System.out.println("Press any key for "+nextPlayer.getName()+"'s turn.");
        reader.nextLine();
        clearScreen();
    }


    public static void main(String[] args) {
        GameFlowController gameFlowController = new GameFlowController();
        gameFlowController.prepareForBattle(2);
        gameFlowController.battleCommences();
    }

    private void printBeginScreen(){
        System.out.println(
                "             |    |    |\n" +
                        "            )_)  )_)  )_)\n" +
                        "           )___))___))___)\\\n" +
                        "          )____)____)_____)\\\\\n" +
                        "        _____|____|____|____\\\\\\__\n" +
                        "-------\\      ---------          /---------\n" +
                        "       ^^^^^ ^^^^^^^^^^^^^^^^^^^^^\n" +
                        "   ^^^^      ^^^^     ^^^    ^^\n" +
                        "        ^^^^      ^^^");

        System.out.println("==========================\n\tPREPARE FOR BATTLE !\n==========================");
    }

    private void printGameResult(Player winner){
        System.out.println("GAME OVER\n"+winner.getName()+" wins!");
    }
}
