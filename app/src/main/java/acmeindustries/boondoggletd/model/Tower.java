package acmeindustries.boondoggletd.model;

/**
 * tower class - more abstract as it generally wont be used
 */

public class Tower {

    private float x;
    private float y;
    private float rotation;
    private int cost;
    private float damage;
    private float speed; // projectile speed
    private float range; // projectile speed
    private Creep target;
    private int loadTime;
    private int currentLoading;
    private boolean loaded;

    public Tower(float x, float y, int cost, float damage, float speed){
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.damage = damage;
        this.speed = speed;
        this.rotation =0;
        this.range = 2f;
        this.target = null;
        this.loadTime = 24;
        this.currentLoading = 0;
        this.loaded = true;
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

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public Creep getTarget() {
        return target;
    }

    public void setTarget(Creep target) {
        this.target = target;
    }

    public int getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(int loadTime) {
        this.loadTime = loadTime;
    }

    public int getCurrentLoading() {
        return currentLoading;
    }

    public void setCurrentLoading(int currentLoading) {
        this.currentLoading = currentLoading;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
