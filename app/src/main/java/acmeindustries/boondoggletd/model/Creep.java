package acmeindustries.boondoggletd.model;

/**
 * represents creep
 */

public class Creep {
    private float x;
    private float y;
    private float speed;
    private float hp;
    private float maxHP;
    private float damage;
    private float targetX;
    private float targetY;
    private float radius;
    private int goldValue;

    public Creep(float x, float y, float hp, float damage, float targetX, float targetY){

        this.x = x;
        this.y = y;
        this.hp = hp;
        this.maxHP = hp;
        this.damage = damage;
        this.targetX = targetX;
        this.targetY = targetY;
        this.radius = 0.25f;
        this.speed = 0.05f;
        this.goldValue = 10;
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

    public float getSpeed() {
        return speed;
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

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public float getRadius() {
        return radius;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public int getGoldValue() {
        return goldValue;
    }

}
