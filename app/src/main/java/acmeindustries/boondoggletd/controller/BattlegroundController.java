package acmeindustries.boondoggletd.controller;

import java.util.Iterator;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Bullet;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;

/**
 * Created by Eric on 10/18/2016.
 */

public class BattlegroundController {

    private Player player;
    private Battleground bg;
    private float width, height;

    private int spawnTimer;


    public BattlegroundController(Player p, Battleground bg,float width,float height){
        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.spawnTimer = 0;
    }

    public void update(float delta){
        spawnTimer--;
        if(spawnTimer<=0){
            bg.addEnemyCreep();
            bg.addPlayerCreep();
            spawnTimer = 72;
        }


        // creep code - implement path finding etc here
        // have to use iterators or else we get errors when deleting
        Iterator<Creep> it = bg.getEnemyCreeps().iterator();
        while(it.hasNext()){
            Creep c = it.next();
            if (c.getTargetX() < c.getX() - c.getRadius()/10f) {
                c.setX(c.getX() - c.getSpeed());
            }else if (c.getTargetX() > c.getX() + c.getRadius()/10f) {
                c.setX(c.getX() + c.getSpeed());
            }
            if (c.getTargetY() < c.getY() - c.getRadius()/10f) {
                c.setY(c.getY() - c.getSpeed());
            }else if (c.getTargetY() > c.getY() + c.getRadius()/10f) {
                c.setY(c.getY() + c.getSpeed());
            }
            if(c.getX()-c.getTargetX() >= -0.1f && c.getX()-c.getTargetX() <= 0.1f){
                it.remove();
                break;
            }
            if(c.getHp() <= 0){
                it.remove();
            }
        }
        it = bg.getPlayerCreeps().iterator();
        while(it.hasNext()){
            Creep c = it.next();
            if (c.getTargetX() < c.getX() - c.getRadius()/10f) {
                c.setX(c.getX() - c.getSpeed());
            }else if (c.getTargetX() > c.getX() + c.getRadius()/10f) {
                c.setX(c.getX() + c.getSpeed());
            }
            if (c.getTargetY() < c.getY() - c.getRadius()/10f) {
                c.setY(c.getY() - c.getSpeed());
            }else if (c.getTargetY() > c.getY() + c.getRadius()/10f) {
                c.setY(c.getY() + c.getSpeed());
            }
            if(c.getX()-c.getTargetX() >= -0.1f && c.getX()-c.getTargetX() <= 0.1f){
                it.remove();
            }
            if(c.getHp() <= 0){
                it.remove();
            }
        }

        // tower code
        //player
        Iterator<Tower> itTower = bg.getPlayerTowers().iterator();
        while(itTower.hasNext()){
            Tower t = itTower.next();
            // set target to null and find closest one
            t.setTarget(null);
            for (Creep creep :
                    bg.getEnemyCreeps()) {
                // if im adding 0.5f its to center the location of the bullet (so it comes from middle of tower
                if(Math.sqrt(Math.pow(creep.getY() - (bg.getPlayerGridY() + t.getY() +0.5f),2)
                        + Math.pow((creep.getX()-(bg.getPlayerGridX() + 0.5f + t.getX())),2))
                        < t.getRange()){
                    t.setTarget(creep);
                    break;
                }
            }
            if(t.getTarget() != null){
                // fire at it
                if(t.isLoaded()){
                    bg.getBullets().add(new Bullet(t.getTarget(),t.getX()+0.5f+bg.getPlayerGridX(),
                            t.getY()+0.5f+bg.getPlayerGridY(),1,0.25f));
                    t.setLoaded(false);
                }
            }
            if(!t.isLoaded()){
                t.setCurrentLoading(t.getCurrentLoading()-1);
                if(t.getCurrentLoading()<=0){
                    t.setLoaded(true);
                    t.setCurrentLoading(t.getLoadTime());
                }
            }
        }

        // bullet code
        Iterator<Bullet> itBullet = bg.getBullets().iterator();
        while(itBullet.hasNext()){
            Bullet b = itBullet.next();
            if(b.getTarget().getHp()<=0){
                itBullet.remove();
                break;
            }
            float xdist = b.getTarget().getX() - b.getX();
            float ydist = b.getTarget().getY() - b.getY();
            float angle = (float)Math.atan2(ydist, xdist);
            b.setX(b.getX() + (float)Math.cos(angle)*b.getSpeed());
            b.setY(b.getY() + (float)Math.sin(angle)*b.getSpeed());
            if(xdist + ydist <= 0.1f && xdist+ydist>=-0.1f){
                b.getTarget().setHp(b.getTarget().getHp()-b.getDamage());
                itBullet.remove();
            }
        }

    }

    public void press(float x, float y){


    }

}
