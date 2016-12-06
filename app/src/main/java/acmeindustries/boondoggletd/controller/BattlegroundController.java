package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import java.util.Iterator;
import java.util.Random;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Bullet;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Notification;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;
import acmeindustries.boondoggletd.view.BattlegroundRenderer;

import static acmeindustries.boondoggletd.model.Player.GameMode.BATTLEGROUND;
import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING_SELECTING;
import static acmeindustries.boondoggletd.model.Player.GameMode.RECRUITING;

public class BattlegroundController {

    private Player player;
    private Battleground bg;
    private Notification notification;
    private Spawner spawner;
    private TowerController playerTC;
    private TowerController enemyTC;
    // renderer
    private BattlegroundRenderer battlegroundRenderer;
    // width values of screen
    private float width, height;
    public BattlegroundController(Player p, Battleground bg, Spawner s, Notification n, float width,float height){
        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.battlegroundRenderer = new BattlegroundRenderer(bg, player);
        this.spawner = s;
        this.playerTC = new TowerController(this.bg.getPlayerTowers(), this.bg.getEnemyCreeps(),
                this.bg.getBullets(), this.bg.getPlayerGridX(), this.bg.getPlayerGridY(), true);
        this.enemyTC = new TowerController(this.bg.getEnemyTowers(), this.bg.getPlayerCreeps(),
                this.bg.getBullets(), this.bg.getEnemyGridX(), this.bg.getEnemyGridY(), false);
        this.notification = n;
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
            // when bullet hits
            if(Math.abs(xdist+ydist) < 0.2f){
                if(b.getType() == Tower.DamageType.SLOW) {
                    b.getTarget().slowDown(2, 0.5f);
                }else if(b.getType() == Tower.DamageType.BURN){
                    b.getTarget().burn(2, b.getDamage()/48);
                }
                b.getTarget().setHp(b.getTarget().getHp()-b.getDamage());
                itBullet.remove();
            }
        }

        // enemy creeps
        Iterator<Creep> it = bg.getEnemyCreeps().iterator();
        while(it.hasNext()){
            Creep c = it.next();
            c.update();
            if(!c.isAlive()){
                player.setHp(player.getHp()-1);
                it.remove();
            }
            if(c.getHp() <= 0){
                player.setGold(player.getGold() + c.getCost());
                it.remove();
            }
        }
        // player creeps
        it = bg.getPlayerCreeps().iterator();
        while(it.hasNext()){
            Creep c = it.next();
            c.update();
            if(!c.isAlive()){
                bg.getEnemy().setHp(bg.getEnemy().getHp()-1);
                it.remove();
            }
            if(c.getHp() <= 0){
                it.remove();
            }
        }

    }

    public void press(float x, float y){
        if(bg.spawning || !bg.getEnemyCreeps().isEmpty() || (y/height)*5 < 4) return;
        if((x/width)*10<2){
            player.gm = BUILDING_SELECTING;
        } else if((x/width)*10<4){
            player.gm = RECRUITING;
        } else if((x/width)*10>=4){
            bg.createPath();
            bg.setRoundNumber(bg.getRoundNumber()+1);
            // TODO: enemy create stack of creeps and buy towers?
            Random rand = new Random();
            for(int i =0; i<5; i++){
                // hp from 10 - 30 + round number, speed from 0.5 - 1.5, gold 2*hp
                int hp = (rand.nextInt(3) + 1)*10 + bg.getRoundNumber();
                float speed = rand.nextFloat() + 0.5f;
                spawner.pushEnemyCreep(new Creep(hp,speed,hp*2,bg.getEnemyPath(),bg.getPlayerGridX()+0.5f,bg.getPlayerGridY()+0.5f));
            }
            // keep track of creeps purchased
            player.setCreepCount(0);
            spawner.startRound();
        }
    }

    public void render(Canvas canvas){
        this.battlegroundRenderer.render(canvas);
    }

}
