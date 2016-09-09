import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by anmu on 07/09/2016.
 */
public class GameLogic {

    private Scanner reader;
    private int currentX;
    private int currentY;
    private Orientation currentOrientation;


    protected GameLogic(InputStream keypresses) {
        reader = new Scanner(keypresses);
    }

    protected GameLogic(Scanner reader){this.reader = reader;}

    public void placeShipAfterUserInput(Player currentPlayer, int currentShipLength) {
        boolean placeShipSuccess = false;

        while (!placeShipSuccess) {
            try {
                getCoordinates();
                getOrientation();
                if (currentPlayer.getOceanGrid().withinGrid(this.currentX, this.currentY, currentShipLength, this.currentOrientation) &&
                        !currentPlayer.getOceanGrid().collidesWithShip(this.currentX, this.currentY, currentShipLength, this.currentOrientation)) {
                    currentPlayer.getOceanGrid().setShip(this.currentX, this.currentY, currentShipLength, this.currentOrientation);
                    placeShipSuccess = true;
                }
            } catch (InputMismatchException ime) {
                //clear line if invalid input
                reader.nextLine();
                System.out.println("Invalid input!");
            }
        }
    }

    public GridState shootAfterUserInput(Player attacker, Player defender) {
        GridState shootResult = GridState.EMPTY;
        boolean shootSuccess = false;

        while (!shootSuccess) {
            try {
                getCoordinates();
                if (attacker.getOceanGrid().withinGrid(this.currentX, this.currentY)) {
                    GridState state = defender.getOceanGrid().getGrid()[this.currentX][this.currentY];
                    switch (state) {
                        case EMPTY:
                            attacker.getTargetGrid().setShot(this.currentX, this.currentY, GridState.MISS);
                            defender.getOceanGrid().setOpponentsShot(this.currentX, this.currentY, GridState.MISS);
                            shootResult = GridState.MISS;
                            shootSuccess = true;
                            break;
                        case SHIP:
                            attacker.getTargetGrid().setShot(this.currentX, this.currentY, GridState.HIT);
                            defender.getOceanGrid().setOpponentsShot(this.currentX, this.currentY, GridState.HIT);
                            shootResult = GridState.HIT;
                            shootSuccess = true;
                            break;
                        default:
                            System.out.println("Previously shot! Shoot again!");
                            break;
                    }
                }
            } catch (InputMismatchException ime) {
                //clear line if invalid input
                reader.nextLine();
                System.out.println("Invalid input!");
            }
        }
        return shootResult;
    }

    public void getCoordinates() {
        System.out.println("Enter X coordinate: ");
        this.currentX = reader.nextInt();
        reader.nextLine(); //get rid of \n
        System.out.println("Enter Y coordinate: ");
        this.currentY = reader.nextInt();
        reader.nextLine(); //get rid of \n
    }

    public void getOrientation() {
        System.out.println("Enter '1' for vertical or '2' for horizontal orientation");
        int orientationInt = reader.nextInt();
        if (orientationInt==1) {
            this.currentOrientation = Orientation.VERTICAL;
            System.out.println("Vertical orientation selected");
        }
        else if (orientationInt==2){
            this.currentOrientation = Orientation.HORIZONTAL;
            System.out.println("Horizontal orientation selected");
        }
        else{
            throw new InputMismatchException();
        }
    }
}
