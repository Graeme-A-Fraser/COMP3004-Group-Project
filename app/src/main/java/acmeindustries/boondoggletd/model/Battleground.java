package acmeindustries.boondoggletd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * this is a model of the actual game's battleground - locations in relations to a 1x1 grid
 */

public class Battleground {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final int GRIDWIDTH = 6;
    public static final int GRIDHEIGHT = 4;

    private int x;
    private int y;
    private float scale;

    // for creeps to know where to start / finish
    private int startX;
    private int startY;
    private int finishX;
    private int finishY;

    // where the grids are in relation to the rest of the battleground
    private int playerGridX;
    private int playerGridY;
    private int enemyGridX;
    private int enemyGridY;

    private Castle playerCastle;
    private Castle enemyCastle;

    private Object[][] playerGrid;
    private Object[][] enemyGrid;
    private List<Tower> playerTowers;
    private List<Tower> enemyTowers;

    private List<Creep> enemyCreeps;
    private List<Creep> playerCreeps;

    private List<Bullet> bullets;

    public Battleground(){

        // set x, y to 0 and scale
        // TODO: implement this into the battleground renderer (use these values to translate and transform)
        this.x = 0;
        this.y = 0;
        this.scale = 1;

        startX = 0;
        startY = 1;
        finishX = 10;
        finishY = 1;

        playerGridX = 2;
        playerGridY = 4;
        enemyGridX = 2;
        enemyGridY = 0;

        playerCastle = new Castle(0,4);
        enemyCastle = new Castle(8,0);

        // init the player grid
        playerGrid = new Object[GRIDHEIGHT][GRIDWIDTH];
        playerTowers = new ArrayList<Tower>();
        for (int i = 0; i <GRIDWIDTH; i++) {
            for (int j = 0; j <GRIDHEIGHT; j++) {
                playerGrid[j][i] = null;
            }
        }
        // init the enemy grid
        enemyGrid = new Object[GRIDHEIGHT][GRIDWIDTH];
        enemyTowers = new ArrayList<Tower>();
        for (int i = 0; i <GRIDWIDTH; i++) {
            for (int j = 0; j <GRIDHEIGHT; j++) {
                enemyGrid[j][i] = null;
            }
        }
        /*fill in 2 spots with towers
        playerGrid[1][1] = new Tower(1, 1, 0, 1, 1);
        enemyGrid[2][4] = new Tower(4, 2, 0, 1, 1);
        playerTowers.add((Tower)playerGrid[1][1]);
        enemyTowers.add((Tower)enemyGrid[2][4]);
        */

        //create creep list
        enemyCreeps = new ArrayList<Creep>();
        playerCreeps = new ArrayList<Creep>();

        bullets = new ArrayList<Bullet>();
    }

    public boolean checkPlayerGridAvailable(int gridX, int gridY){
        if(gridX < 0 || gridX >= GRIDWIDTH){
            return false;
        }
        if(gridY < 0 || gridY >= GRIDHEIGHT){
            return false;
        }
        return playerGrid[gridY][gridX] == null;
    }

    // can later customize this for different types etc.
    public void addPlayerTower(int gridX, int gridY){
        playerGrid[gridY][gridX] = new Tower(gridX,gridY,20,1,1);
        playerTowers.add((Tower)playerGrid[gridY][gridX]);
    }

    //getters and setters

    public void addEnemyCreep(){
        enemyCreeps.add(new Creep(enemyCastle.getX()+2,playerCastle.getY()+2,10,1,
                playerCastle.getX()+2,playerCastle.getY()+2));
    }

    public void addPlayerCreep(){
        playerCreeps.add(new Creep(playerCastle.getX(),enemyCastle.getY()+2,10,1,
                enemyCastle.getX(),enemyCastle.getY()+2));
    }

    public List<Creep> getEnemyCreeps() {
        return enemyCreeps;
    }
    public List<Creep> getPlayerCreeps() {
        return playerCreeps;
    }

    public Object[][] getPlayerGrid() {
        return playerGrid;
    }

    public List<Tower> getPlayerTowers() {
        return playerTowers;
    }
    public List<Tower> getEnemyTowers() {
        return enemyTowers;
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

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getFinishX() {
        return finishX;
    }

    public void setFinishX(int finishX) {
        this.finishX = finishX;
    }

    public int getFinishY() {
        return finishY;
    }

    public void setFinishY(int finishY) {
        this.finishY = finishY;
    }

    public int getPlayerGridX() {
        return playerGridX;
    }

    public void setPlayerGridX(int playerGridX) {
        this.playerGridX = playerGridX;
    }

    public int getPlayerGridY() {
        return playerGridY;
    }

    public void setPlayerGridY(int playerGridY) {
        this.playerGridY = playerGridY;
    }

    public int getEnemyGridX() {
        return enemyGridX;
    }

    public void setEnemyGridX(int enemyGridX) {
        this.enemyGridX = enemyGridX;
    }

    public int getEnemyGridY() {
        return enemyGridY;
    }

    public void setEnemyGridY(int enemyGridY) {
        this.enemyGridY = enemyGridY;
    }

    public Castle getPlayerCastle() {
        return playerCastle;
    }

    public Castle getEnemyCastle() {
        return enemyCastle;
    }

    public Object[][] getEnemyGrid() {
        return enemyGrid;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
