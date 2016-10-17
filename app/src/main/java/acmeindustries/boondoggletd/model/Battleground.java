package acmeindustries.boondoggletd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 10/16/2016.
 */

public class Battleground {

    public static final int WIDTH = 5;
    public static final int HEIGHT = 4;

    private int x;
    private int y;
    private int scale;

    private Object[][] playerGrid;
    private List<Tower> towers;

    public Battleground(){

        // set x, y to 0 and scale
        // TODO: implement this into the battleground renderer (use these values to translate and transform)
        this.x = 0;
        this.y = 0;
        this.scale = 1;

        // init the player grid
        playerGrid = new Object[HEIGHT][WIDTH];
        towers = new ArrayList<Tower>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                playerGrid[j][i] = null;
            }
        }
        //fill in 2 spots with towers
        playerGrid[1][1] = new Tower(1, 1);
        playerGrid[3][1] = new Tower(1, 3);
        towers.add((Tower)playerGrid[1][1]);
        towers.add((Tower)playerGrid[3][1]);
    }

    public Object[][] getPlayerGrid() {
        return playerGrid;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
