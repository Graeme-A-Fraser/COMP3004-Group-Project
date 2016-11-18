package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import java.util.Iterator;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Bullet;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.view.BattlegroundRenderer;

import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING_SELECTING;

public class BattlegroundController {

    private Player player;
    private Battleground bg;
    private Spawner spawner;
    private TowerController playerTC;
    private TowerController enemyTC;
    // renderer
    private BattlegroundRenderer battlegroundRenderer;
    // width values of screen
    private float width, height;
    public BattlegroundController(Player p, Battleground bg,float width,float height){
        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.battlegroundRenderer = new BattlegroundRenderer(bg, player);
        this.spawner = new Spawner(this.bg);
        this.playerTC = new TowerController(this.bg.getPlayerTowers(), this.bg.getEnemyCreeps(),
                this.bg.getBullets(), this.bg.getPlayerGridX(), this.bg.getPlayerGridY());
        this.enemyTC = new TowerController(this.bg.getEnemyTowers(), this.bg.getPlayerCreeps(),
                this.bg.getBullets(), this.bg.getEnemyGridX(), this.bg.getEnemyGridY());

        // testing
        bg.addEnemyTower(1,1,2,0.25f);
        bg.addEnemyTower(2,1,2,0.25f);

    }

    public void update(){

        // spawner
        spawner.update();
        // towers
        playerTC.update();
        enemyTC.update();

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
            if(Math.abs(xdist+ydist) < 0.25f){
                b.getTarget().setHp(b.getTarget().getHp()-b.getDamage());
                itBullet.remove();
            }
        }

        // creep code - implement path finding etc here
        // have to use iterators or else we get errors when deleting
        Iterator<Creep> it = bg.getEnemyCreeps().iterator();
        while(it.hasNext()){
            Creep c = it.next();
            c.move();
            if(!c.isAlive()){
                it.remove();
            }
            if(c.getHp() <= 0){
                player.setGold(player.getGold()+c.getGoldValue());
                it.remove();
            }
        }
        it = bg.getPlayerCreeps().iterator();
        while(it.hasNext()){
            Creep c = it.next();
            c.move();
            if(!c.isAlive()){
                it.remove();
            }
            if(c.getHp() <= 0){
                it.remove();
            }
        }

    }

    public void press(float x, float y){
        bg.createPath();
        if((x/width)*10<2 && (y/height)*10>8){
            player.gm = BUILDING_SELECTING;
        }
        if((x/width)*10>=4 && (x/width)*10<6 && (y/height)*10>8){
            spawner.startRound();
        }
    }

    public void render(Canvas canvas){
        this.battlegroundRenderer.render(canvas);
    }

}
