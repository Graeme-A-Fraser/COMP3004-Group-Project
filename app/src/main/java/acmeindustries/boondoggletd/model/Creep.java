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
    private float radius;
    private int goldValue;


    private float[][] path;
    private int position;

    public boolean alive;

    public Creep(float x, float y, float hp, float damage, float[][] path){

        this.x = x;
        this.y = y;
        this.hp = hp;
        this.maxHP = hp;
        this.damage = damage;
        this.path = path;
        this.radius = 0.25f;
        this.speed = 0.05f;
        this.goldValue = 10;
        this.alive = true;
        this.position = 0;
    }

    public void move(){
        // check if on correct node, then increment position if so
        if(Math.sqrt(Math.pow(this.x - this.path[position][0], 2) + Math.pow(this.y - this.path[position][1], 2)) < 0.1f){
            this.position++;
        }
        // if at end of path die
        if(this.position >= path.length){
            this.alive = false;
        }

        // if dead dont execute below code
        if(!this.alive){
            return;
        }

        // move left if target is bigger etc...
        if(this.x > this.path[position][0]){
            this.x -= speed;
        }
        if(this.x < this.path[position][0]){
            this.x += speed;
        }
        if(this.y > this.path[position][1]){
            this.y -= speed;
        }
        if(this.y < this.path[position][1]){
            this.y += speed;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
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
