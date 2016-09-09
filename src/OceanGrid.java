import java.util.Calendar;

/**
 * Created by anmu on 07/09/2016.
 */
public class OceanGrid extends Grid {

    public void setShip(int x, int y, int length, Orientation orientation) {
        switch (orientation) {

            case HORIZONTAL:
                for (int i = y; i < y + length; i++) {
                    grid[x][i] = GridState.SHIP;
                }
                break;

            default:
                for (int i = x; i < x + length; i++) {
                    grid[i][y] = GridState.SHIP;
                }
                break;
        }
        System.out.println("Ship has been placed at " + x + "/" + y);


    }


    public void setOpponentsShot(int x, int y, GridState gridState) {
        grid[x][y] = gridState;
    }

    public boolean anyShipsLeft() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == GridState.SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collidesWithShip(int x, int y, int length, Orientation orientation) {
        switch (orientation) {
            case HORIZONTAL:
                for (int i = y; i < y + length; i++) {

                    if (grid[x][i] == GridState.SHIP) {
                        System.out.println("Ship collides with another");
                        return true;

                    }
                }
                return false;
            default:
                for (int i = x; i < x + length; i++) {

                    if (grid[i][y] == GridState.SHIP) {
                        System.out.println("Ship collides with another");
                        return true;

                    }
                }
                return false;
        }
    }
}
