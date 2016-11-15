package acmeindustries.boondoggletd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * this is a model of the actual game's battleground - locations in relations to a 1x1 grid
 */

public class Battleground {

    public static final int GRIDWIDTH = 6;
    public static final int GRIDHEIGHT = 4;
    public static final int TPS = 24;

    private int x;
    private int y;

    // where the grids are in relation to the rest of the battleground
    private int playerGridX;
    private int playerGridY;
    private int enemyGridX;
    private int enemyGridY;

    private Castle playerCastle;
    private Castle enemyCastle;

    private Object[][] playerGrid; //grid of null or tower objects
    private Object[][] enemyGrid; // grid of null or tower objects
    private List<Tower> playerTowers;
    private List<Tower> enemyTowers;

    private List<Creep> enemyCreeps;
    private List<Creep> playerCreeps;

    private List<Bullet> bullets;

    private static float playerPath[][]; //player path
    private static float enemyPath[][]; //enemy path

    public Battleground(){

        this.x = 0;
        this.y = 0;
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
    public void addPlayerTower(int gridX, int gridY, float damage, float speed){
        playerGrid[gridY][gridX] = new Tower(TPS, gridX,gridY,20,damage,speed);
        playerTowers.add((Tower)playerGrid[gridY][gridX]);
    }

    //getters and setters

    public void addEnemyCreep(float damage, float health){
        enemyCreeps.add(new Creep(enemyCastle.getX()+2,playerCastle.getY()+2,health,damage
                ,new float[][]{{playerCastle.getX()+2, playerCastle.getY()+2}}));
    }

    public void addPlayerCreep(float damage, float health){
        playerCreeps.add(new Creep(playerCastle.getX(),enemyCastle.getY()+2,health,damage,
                new float[][]{{enemyCastle.getX(),enemyCastle.getY()+2}}));
    }

    public List<Creep> getEnemyCreeps() {
        return enemyCreeps;
    }
    public List<Creep> getPlayerCreeps() {
        return playerCreeps;
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

    public int getX() {
        return x;
    }

    public int getPlayerGridX() {
        return playerGridX;
    }

    public int getPlayerGridY() {
        return playerGridY;
    }

    public int getEnemyGridX() {
        return enemyGridX;
    }

    public int getEnemyGridY() {
        return enemyGridY;
    }

    public Castle getPlayerCastle() {
        return playerCastle;
    }

    public Castle getEnemyCastle() {
        return enemyCastle;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public boolean createPath(){

        return false;
    }

}
