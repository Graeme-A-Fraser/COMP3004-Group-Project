package acmeindustries.boondoggletd.model;

/**
 * this is a class that doesn't get seen but contains the players resources
 */

public class Player {

    public enum GameMode {
        BATTLEGROUND, BUILDING, RECRUITING
    }

    private float gold;
    private Castle c;
    public GameMode gm;


    public Player(GameMode gm){
        this.gm = gm;
    }
}
