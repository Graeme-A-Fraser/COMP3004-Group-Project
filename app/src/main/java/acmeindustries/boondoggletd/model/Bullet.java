package acmeindustries.boondoggletd.model;

import java.util.WeakHashMap;

/**
 * Created by Eric on 10/18/2016.
 */

public class Bullet {
    private Creep target;
    private float x;
    private float y;
    private float damage;
    private float speed;

    public Bullet(Creep target, float x, float y, float damage, float speed) {
        this.target = target;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.speed = speed;
    }

    public Creep getTarget() {
        return target;
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

    public float getDamage() {
        return damage;
    }

    public float getSpeed() {
        return speed;
    }

}
