/**
 * Created by anmu on 08/09/2016.
 */
public class Player {
    private String name;
    private OceanGrid oceanGrid;
    private TargetGrid targetGrid;

    public Player(){
        this("John Doe");
    }

    public Player(String name){
        this.name = name;
        this.oceanGrid = new OceanGrid();
        this.targetGrid = new TargetGrid();
    }

    public OceanGrid getOceanGrid() {
        return oceanGrid;
    }

    public TargetGrid getTargetGrid() {
        return targetGrid;
    }

    public String getName(){
        return this.name;
    }
}
