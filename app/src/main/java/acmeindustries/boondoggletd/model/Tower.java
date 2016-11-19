package acmeindustries.boondoggletd.model;

/**
 * tower class - more abstract as it generally wont be used
 */

public class Tower {

    private float TPS;
    private float x;
    private float y;
    private float rotation;
    private int cost;
    private float damage;
    private float speed; // projectile speed
    private float range;
    private Creep target;
    private int loadTime;
    private int currentLoading;
    private boolean loaded;

    public Tower(float TPS, float x, float y, float damage, float speed, int cost){
        this.TPS = TPS;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.speed = speed;
        this.rotation =0;
        this.range = 2f;
        this.target = null;
        this.loadTime = (int)TPS;
        this.currentLoading = 0;
        this.loaded = true;
        this.cost = cost;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRange() {
        return range;
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

    public float getDamage() {
        return damage;
    }

    public float getSpeed() {
        return speed;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
