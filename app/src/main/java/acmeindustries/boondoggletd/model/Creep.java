package acmeindustries.boondoggletd.model;

public class Creep {
    private float x,y,speed,hp,maxHP,radius, gridX, gridY, slowed, burnDmg, slowedTimer, burnTimer;
    private int goldValue;


    private float[][] path;
    private int position;

    private boolean alive;

    public Creep(float x, float y, float hp, float[][] path, float gridX, float gridY){

        this.x = x;
        this.y = y;
        this.hp = hp;
        this.maxHP = hp;
        this.path = path;
        this.radius = 0.25f;
        this.speed = 0.05f;
        this.goldValue = 10;
        this.alive = true;
        this.position = 0;
        this.gridX = gridX;
        this.gridY = gridY;
        this.slowed = 1;
        this.burnDmg= 0;
        this.slowedTimer = 0;
        this.burnTimer = 0;
    }

    public void move(){
        // move left if target is bigger etc...
        if(this.x > this.path[position][0] + gridX){
            this.x -= speed*slowed;
        }
        if(this.x < this.path[position][0] + gridX){
            this.x += speed*slowed;
        }
        if(this.y > this.path[position][1] + gridY){
            this.y -= speed*slowed;
        }
        if(this.y < this.path[position][1] + gridY){
            this.y += speed*slowed;
        }
    }

    public void update(){
        // check if on correct node, then increment position if so
        if(Math.sqrt(Math.pow(this.x - this.path[position][0] - gridX, 2) + Math.pow(this.y - this.path[position][1] - gridY, 2)) < 0.1f){
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

        // burn
        setHp(getHp() - burnDmg);

        // burn & slow timers

        if(slowedTimer> 0){
            slowedTimer--;
        }else{
            slowed = 1;
        }
        if(burnTimer > 0){
            burnTimer --;
        }else{
            burnDmg = 0;
        }


        move();
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

    public boolean isAlive() {
        return alive;
    }

    // slow speed as multiple of 1, time in seconds
    public void slowDown(float time, float speed){
        this.slowed = speed;
        this.slowedTimer = time*24; // 24 tps, hardcoded
    }

    public void burn(float time, float damage){
        this.burnDmg = damage;
        this.burnTimer = time*24;
    }

}
