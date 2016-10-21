package acmeindustries.boondoggletd.model;

/**
 * Created by Eric on 10/16/2016.
 */

public class Castle {
    private float hp;
    private float damage;
    private float x;
    private float y;

    public Castle(float x, float y){
        this.x = x;
        this.y = y;
        this.hp = 40;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
