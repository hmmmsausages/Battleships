/**
 * Created by anmu on 07/09/2016.
 */
public class TargetGrid extends Grid {

    public void setShot(int x, int y, GridState gridState) {
        grid[x][y] = gridState;
        System.out.println("Shot has been fired at " + x + "/" + y);
    }
}
