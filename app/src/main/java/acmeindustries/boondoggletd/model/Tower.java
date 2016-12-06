package acmeindustries.boondoggletd.model;

import android.graphics.Color;

import java.util.PriorityQueue;

/**
 * tower class - more abstract as it generally wont be used
 */

public class Tower {

    public enum DamageType {
        STANDARD, SLOW, BURN
    }

    private float x;
    private float y;
    private float rotation;
    private float reloadMultiplier;
    private int cost;
    private float damage;
    private float range;
    private Creep target;
    private float loadTime;
    private float currentLoading;
    private boolean loaded;
    private DamageType damageType;

    public Tower(float TPS, float x, float y, float damage, float reloadMultiplier, int cost, DamageType type){
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.rotation =0;
        this.range = 2f;
        this.target = null;
        this.loadTime = TPS*reloadMultiplier;
        this.currentLoading = 0;
        this.loaded = true;
        this.cost = cost;
        this.damageType = type;
        this.reset();
        this.reloadMultiplier = reloadMultiplier;
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

    public float getCurrentLoading() {
        return currentLoading;
    }

    public void setCurrentLoading(float currentLoading) {
        this.currentLoading = currentLoading;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void reset(){
        this.loaded = false;
        this.currentLoading = this.loadTime;
    }

    public float getDamage() {
        return damage;
    }

    public int getCost() {
        return cost;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public int getColor() {
        switch(damageType){
            case BURN:
                return Color.RED;
            case SLOW:
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }

    public float getReloadMultiplier() {
        return reloadMultiplier;
    }

    @Override
    public String toString() {
        switch(damageType){
            case STANDARD:
                return "Standard Tower";
            case BURN:
                return "Burn Tower";
            case SLOW:
                return "Slow Tower";
            default:
                return "I don't know what type this is.";
        }
    }

    public void setDamage(float damage){
        this.damage = damage;
    }
}
