import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anmu on 08/09/2016.
 */
public class OceanGridTest {
    @Test
    public void collidesWithShipHorizontalTrue() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,0,2,Orientation.HORIZONTAL);
        assertTrue(oceanGrid.collidesWithShip(0,0,2,Orientation.HORIZONTAL));
    }

    @Test
    public void collidesWithShipVerticalTrue() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,0,2,Orientation.HORIZONTAL);
        assertTrue(oceanGrid.collidesWithShip(0,0,2,Orientation.VERTICAL));
    }

    @Test
    public void collidesWithShipHorizontalFalse() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,2,2,Orientation.HORIZONTAL);
        assertFalse(oceanGrid.collidesWithShip(0,0,2,Orientation.HORIZONTAL));
    }

    @Test
    public void collidesWithShipVerticalFalse() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,2,2,Orientation.HORIZONTAL);
        assertFalse(oceanGrid.collidesWithShip(0,0,2,Orientation.VERTICAL));
    }

    @Test
    public void setShipZeroLength() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,0,0,Orientation.HORIZONTAL);
        assertEquals(GridState.EMPTY,oceanGrid.grid[0][0]);
    }

    @Test
    public void setShipLength1Horizontal() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,0,1,Orientation.HORIZONTAL);
        assertEquals(GridState.SHIP,oceanGrid.grid[0][0]);
    }

    @Test
    public void setShipLength1Vertical() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,0,1,Orientation.VERTICAL);
        assertEquals(GridState.SHIP,oceanGrid.grid[0][0]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setShipLengthTooLong() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,0,100,Orientation.HORIZONTAL);
    }

    @Test
    public void setShipLength5() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(0,0,2,Orientation.HORIZONTAL);
        assertEquals(GridState.SHIP,oceanGrid.grid[0][0]);
        assertEquals(GridState.SHIP,oceanGrid.grid[0][1]);
    }

    @Test
    public void anyShipsLeftOneLeft() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(9,1,1,Orientation.HORIZONTAL);
        assertTrue(oceanGrid.anyShipsLeft());
    }

    @Test
    public void anyShipsLeftNoneLeft() throws Exception {
        OceanGrid oceanGrid = new OceanGrid();
        oceanGrid.setShip(1,1,1,Orientation.HORIZONTAL);
        oceanGrid.setOpponentsShot(1,1,GridState.HIT);
        assertFalse(oceanGrid.anyShipsLeft());
    }

}