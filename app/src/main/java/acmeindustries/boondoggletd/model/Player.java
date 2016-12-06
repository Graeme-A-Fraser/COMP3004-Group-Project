package acmeindustries.boondoggletd.model;

/**
 * this is a class that doesn't get seen but contains the players resources
 */

public class Player {

    public enum GameMode {
        BATTLEGROUND, RECRUITING, BUILDING_PLACING, BUILDING_SELECTING, SELLING
    }

    private int gold;
    private int hp;
    private int creepCount;
    public GameMode gm;


    public Player(){
        this.gm = GameMode.BATTLEGROUND;
        this.gold = 1000;
        this.hp = 40;
        this.creepCount = 0;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getCreepCount(){
       return creepCount;
    }

    public void setCreepCount(int creepCount) {
        this.creepCount = creepCount;
    }
}
