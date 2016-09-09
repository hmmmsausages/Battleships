import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anmu on 07/09/2016.
 */
public class GridTest {
    @Test
    public void withinBoardWhen1_1ThenTrue() throws Exception {
        Grid grid = new Grid();
        assertTrue(grid.withinGrid(1,1));
    }

    @Test
    public void withinBoardWhen11_1ThenFalse() throws Exception {
        Grid grid = new Grid();
        assertFalse(grid.withinGrid(11,1));
    }

    @Test
    public void withinBoardWhen1_11ThenFalse() throws Exception {
        Grid grid = new Grid();
        assertFalse(grid.withinGrid(1,11));
    }

    @Test
    public void withinBoardWithShipLengthFits() throws Exception {
        Grid grid = new Grid();
        assertTrue(grid.withinGrid(1,1,0, Orientation.HORIZONTAL));
    }

    @Test
    public void withinBoardFromZeroAndZeroLength() throws Exception {
        Grid grid = new Grid();
        assertTrue(grid.withinGrid(1,0,0,Orientation.HORIZONTAL));
    }

    @Test
    public void withinBoardWithShipLengthDoesntFit() throws Exception {
        Grid grid = new Grid();
        assertFalse(grid.withinGrid(1,9,2,Orientation.HORIZONTAL));
    }

    @Test
    public void withinBoardWithShipLengthFitsVertical() throws Exception {
        Grid grid = new Grid();
        assertTrue(grid.withinGrid(1,9,2,Orientation.VERTICAL));
    }

    @Test
    public void withinBoardWithShipLengthDoesntFitVertical() throws Exception {
        Grid grid = new Grid();
        assertFalse(grid.withinGrid(9,9,2,Orientation.VERTICAL));
    }

    @Test
    public void withinBoardNegativeValues() throws Exception {
        Grid grid = new Grid();
        assertFalse(grid.withinGrid(-1,-2));
    }


}