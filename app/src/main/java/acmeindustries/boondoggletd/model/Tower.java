package acmeindustries.boondoggletd.model;

/**
 * tower class - more abstract as it generally wont be used
 */

public class Tower {

    private float x;
    private float y;
    private float rotation;
    private float damage;
    private float speed; // projectile speed

    public Tower(float x, float y, float rotation, float damage, float speed){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.damage = damage;
        this.speed = speed;
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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
