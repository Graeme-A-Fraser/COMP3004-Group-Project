package acmeindustries.boondoggletd.controller;

import java.util.Iterator;
import java.util.List;

import acmeindustries.boondoggletd.model.Bullet;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Tower;

/**
 * Created by eric on 16/11/16.
 */

public class TowerController {

    private List<Tower> towers;
    private List<Creep> creeps;
    private List<Bullet> bullets;
    private int gridX, gridY;
    private boolean player;

    public TowerController(List<Tower> towers, List<Creep> creeps, List<Bullet> bullets, int gridX, int gridY, boolean player){
        this.towers = towers;
        this.creeps = creeps;
        this.bullets = bullets;
        this.gridX = gridX;
        this.gridY = gridY;
        this.player = player;
    }

    public void update(){
        Iterator<Tower> itTower = towers.iterator();
        while(itTower.hasNext()){
            Tower t = itTower.next();
            // set target to null and find closest one
            // this is inefficient as every update every tower finds a new target
            t.setTarget(null);
            if(!t.isLoaded()) {
                t.setCurrentLoading(t.getCurrentLoading() - 1);
                if (t.getCurrentLoading() <= 0) {
                    t.setLoaded(true);
                    System.out.println("LOADED");
                }
            }
            for (Creep creep :
                    creeps) {
                // stop targeting towers that are in base
                if(player && creep.getX() < 2) continue;
                if(!player && creep.getX() > 7f) continue;
                // if im adding 0.5f its to center the location of the bullet (so it comes from middle of tower
                if(Math.sqrt(Math.pow(creep.getY() - (gridY + t.getY() +0.5f),2)
                        + Math.pow((creep.getX()-(gridX + 0.5f + t.getX())),2))
                        < t.getRange()){
                    t.setTarget(creep);
                    break;
                }
            }
            // fire at it
            if(t.getTarget() != null && t.isLoaded()){
                bullets.add(new Bullet(t.getTarget(),t.getX()+0.5f+gridX,
                        t.getY()+0.5f+gridY,t.getDamage(), t.getDamageType()));
                t.reset();
            }
        }
    }

}
