package acmeindustries.boondoggletd.controller;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Player;

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
            spawnTimer = 24;
        }


        // creep code
        for (Creep c :
                bg.getEnemyCreeps()) {
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
        }
        for (Creep c :
                bg.getPlayerCreeps()) {
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
        }
    }

    public void press(float x, float y){


    }

}
