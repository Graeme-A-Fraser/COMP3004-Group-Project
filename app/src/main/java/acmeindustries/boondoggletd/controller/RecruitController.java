package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import java.util.ArrayDeque;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Notification;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.view.RecruitRenderer;

import static acmeindustries.boondoggletd.model.Player.GameMode.BATTLEGROUND;
import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING_PLACING;

/**
 * Created by ericm on 2016-10-21.
 */

public class RecruitController {

    private Player player;
    private Battleground bg;
    private Spawner spawner;
    private RecruitRenderer recruitRenderer;
    private Notification notification;
    private float width;
    private float height;
    private int creepSelection;

    private ArrayDeque<Creep> creeps;

    private float[][] path;
    private Creep[] creepTypes;

    public RecruitController(Player p, Battleground bg, Spawner s, Notification n, float width, float height){

        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.notification = n;
        this.recruitRenderer = new RecruitRenderer(bg, p);
        this.creepSelection = 0;
        this.creeps = new ArrayDeque<Creep>();
        this.spawner = s;
        bg.createPath();
        this.path = bg.getPlayerPath();
        creepTypes = new Creep[]{
                //3: hp, 4: speed, 5:gold cost, rest can be replaced when actually adding creep
                new Creep(10,1,20,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f),
                new Creep(20,1,40,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f),
                new Creep(40,1.5f,80,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f),
                new Creep(80,1.5f,160,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f),
                new Creep(160,2,320,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f),
                new Creep(320,2,640,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f),
                new Creep(640,2,1280,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f),
                new Creep(1280,3,2560,path,bg.getEnemyGridX()+0.5f,bg.getEnemyGridY()+0.5f)
        };
    }


    public void press(float x, float y){
        int currentX = (int)((x/width)*10);
        int currentY = (int)((y/height)*5);
        if(currentY>=4){
            if(currentX < 2){
                // confirm / buy all towers in stack
                player.gm = BATTLEGROUND;
                while(!creeps.isEmpty()){
                    // pop creep and add to spawner stack
                    spawner.pushPlayerCreep(creeps.pop());
                }
            }else{
                // undo, cancel tower in stack
                if(!creeps.isEmpty()){
                    Creep c = creeps.pop();
                    player.setGold(player.getGold() + c.getCost());
                    player.setCreepCount(player.getCreepCount()-1);
                }
            }
        } else {
            this.creepSelection = currentY*2 + (currentX/5);
            if(player.getCreepCount() >= 5){
                notification.newNotification("Maximum creeps purchased this round.");
            }else {
                if (player.getGold() >= creepTypes[creepSelection].getCost()) {
                    this.path = bg.getPlayerPath();
                    Creep c = new Creep(creepTypes[creepSelection]);
                    creeps.push(c);
                    player.setCreepCount(player.getCreepCount()+1);
                    player.setGold(player.getGold() - c.getCost());
                } else {
                    notification.newNotification("Don't have enough gold!");
                }
            }
        }
    }

    public void update(){}

    public void render(Canvas canvas){
        this.recruitRenderer.render(canvas, creepSelection, creepTypes);
    }
}
