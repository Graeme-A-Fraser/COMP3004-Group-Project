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

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
