package acmeindustries.boondoggletd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import acmeindustries.boondoggletd.util.AStar;
import acmeindustries.boondoggletd.util.Cell;


public class Battleground {

    public static final int GRIDWIDTH = 10;
    public static final int GRIDHEIGHT = 4;
    public static final int TPS = 24;

    private int x;
    private int y;
    private int roundNumber;

    private Player enemy;

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

    public boolean spawning;

    private List<Bullet> bullets;

    private static float playerPath[][]; //player path
    private static float enemyPath[][]; //enemy path
    private AStar enemyPathfinder;
    private AStar playerPathfinder;

    public Battleground(){

        this.x = 0;
        this.y = 0;
        playerGridX = 0;
        playerGridY = 4;
        enemyGridX = 0;
        enemyGridY = 0;
        // init enemy
        enemy = new Player();

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

        //create creep list
        enemyCreeps = new ArrayList<Creep>();
        playerCreeps = new ArrayList<Creep>();

        bullets = new ArrayList<Bullet>();

        // player and enemy pathfinders
        enemyPathfinder = new AStar(GRIDWIDTH,GRIDHEIGHT,9,0,1,0,playerGrid);
        playerPathfinder = new AStar(GRIDWIDTH,GRIDHEIGHT,0,3,8,3,enemyGrid);
        enemyPath = enemyPathfinder.getFinalPath();
        playerPath = playerPathfinder.getFinalPath();

        roundNumber = 0;
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
    public void addPlayerTower(Tower t){
        playerGrid[(int)t.getY()][(int)t.getX()] = t;
        playerTowers.add(t);
    }

    public void removePlayerTower(Tower t){
        playerGrid[(int)t.getY()][(int)t.getX()] = null;
        playerTowers.remove(t);
    }

    public Tower createPlayerTower(int gridX, int gridY, float damage, float speed, int cost, Tower.DamageType type){
        return new Tower(TPS, gridX,gridY,damage,speed,cost, type);
    }

    public Tower getPlayerTower(int x, int y){
        return (Tower)playerGrid[y][x];
    }

    public void addEnemyTower(int gridX, int gridY, float damage, float speed, Tower.DamageType type){
        enemyGrid[gridY][gridX] = new Tower(TPS, gridX,gridY,damage,speed, 0, type);
        enemyTowers.add((Tower)enemyGrid[gridY][gridX]);
    }

    public Tower getEnemyTower(int x, int y){
        return (Tower)enemyGrid[y][x];
    }

    //getters and setters

    public void addEnemyCreep(Creep c){
        enemyCreeps.add(c);
    }

    public void addPlayerCreep(Creep c){
        playerCreeps.add(c);
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
        if(!enemyPathfinder.findPath() || !playerPathfinder.findPath()) return false;
        enemyPath = enemyPathfinder.getFinalPath();
        playerPath = playerPathfinder.getFinalPath();
        return true;
    }

    public float[][] getPlayerPath(){
        return playerPath;
    }
    public float[][] getEnemyPath(){
        return enemyPath;
    }

    public Player getEnemy(){
        return enemy;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
