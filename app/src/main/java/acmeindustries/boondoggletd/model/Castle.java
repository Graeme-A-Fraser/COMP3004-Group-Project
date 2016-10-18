package acmeindustries.boondoggletd.model;

/**
 * Created by Eric on 10/16/2016.
 */

public class Castle {
    private int hp;
    private int damage;
    private int x;
    private int y;

    public Castle(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
