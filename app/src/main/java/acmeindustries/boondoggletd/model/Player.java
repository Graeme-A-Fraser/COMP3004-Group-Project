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
    public GameMode gm;


    public Player(GameMode gm){
        this.gm = gm;
        this.gold = 10000;
        this.hp = 40;
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
}
