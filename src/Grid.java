import java.util.Arrays;

/**
 * Created by anmu on 07/09/2016.
 */

enum GridState {
    SHIP,
    EMPTY,
    HIT,
    MISS
}

enum Orientation{
    HORIZONTAL,
    VERTICAL
}


public class Grid {
    protected GridState[][] grid;

    public Grid(){
        grid = new GridState[10][10];
        for(GridState[] row : grid){
            Arrays.fill(row, GridState.EMPTY);
        }
    }


    public boolean withinGrid(int x, int y, int length, Orientation orientation){
        if(withinGrid(x,y)){
            switch (orientation){
                case HORIZONTAL:
                    if(y+length-1<10){
                        return true;
                    }
                    break;
                default:
                    if(x+length-1<10){
                        return true;
                    }
                    break;

            }
            System.out.println("Coordinates are not within the grid.");
        }
        return false;
    }

    public boolean withinGrid(int x, int y){
        if(x<10&&y<10&&x>=0&&y>=0){
            return true;
        }
        System.out.println("Coordinates are not within the grid.");
        return false;
    }

    public GridState[][] getGrid(){
        return grid;
    }

    public void printGrid(){
        System.out.print("\t");
        for(int i=0; i<grid.length;i++){
            System.out.print(i+"\t");
        }
        System.out.println();

        for(int x=0; x<grid.length; x++){
            System.out.print(x+"\t");
            for(int y = 0; y<grid[x].length; y++){
                switch (grid[x][y]){
                    case EMPTY: System.out.print("~\t");
                        break;

                    case SHIP: System.out.print("O\t");
                        break;

                    case HIT: System.out.print("X\t");
                        break;

                    default: System.out.print("*\t");
                        break;
                }
            }
            System.out.println();
        }
    }
}
