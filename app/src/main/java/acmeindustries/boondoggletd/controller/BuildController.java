package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Notification;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;
import acmeindustries.boondoggletd.view.BuildPlacingRenderer;
import acmeindustries.boondoggletd.view.BuildSelectingRenderer;

import static acmeindustries.boondoggletd.model.Player.GameMode.BATTLEGROUND;
import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING_PLACING;
import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING_SELECTING;
import static acmeindustries.boondoggletd.model.Player.GameMode.SELLING;

/**
 * class for controlling the build view
 */

public class BuildController {

    private Player player;
    private Battleground bg;
    private BuildPlacingRenderer buildPlacingRenderer;
    private BuildSelectingRenderer buildSelectingRenderer;
    private float width;
    private float height;
    private int currentX, currentY;
    private int towerSelection;
    private ArrayDeque<Tower> towers;
    private Notification notification;

    // TODO: REVAMP THIS HOW TOWER TYPES SHOULD BE IMPLEMENTED
    private float[][] towerTypes = {
            //cost, damage, speed
            {20, 1,0.25f},
            {30, 2,0.25f},
            {50, 5,0.25f},
            {75, 10,0.25f},
            {150,25,0.25f},
            {250,60,0.25f},
            {400,100,0.25f},
            {500,200,0.25f}
    };

    public BuildController(Player p, Battleground bg, Notification n, float width,float height){
        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.buildPlacingRenderer = new BuildPlacingRenderer(bg, p);
        this.buildSelectingRenderer = new BuildSelectingRenderer(bg, p);
        this.currentX = 0;
        this.currentY = 0;
        this.towerSelection = 0;
        this.towers = new ArrayDeque<Tower>();
        this.notification = n;
    }

    public void press(float x, float y){
        //System.out.printf("pressed at %f, %f - gridx: %d, gridy: %d\n", x, y, (int)((x/width)*8), (int)((y/height)*6));
        // TODO: CLEAN THIS UP AND IMPLEMENT UNDO
        currentX = (int)((x/width)*8)+2;
        currentY = (int)((y/height)*5);
        // if you click on the start / end nodes
        if(player.gm == BUILDING_PLACING) {
            if(currentY>=4){
                currentX = (int)((x/width)*10);
                if(currentX < 2) {
                    // confirm
                    // empty temporary stack
                    while(!towers.isEmpty()){
                        towers.pop();
                    }
                    player.gm = BATTLEGROUND;
                }else if(currentX < 4){
                    // undo
                    if(!towers.isEmpty()){
                        Tower t = towers.pop();
                        player.setGold(player.getGold() + t.getCost());
                        bg.removePlayerTower(t);
                    } else {
                        notification.newNotification("Nothing to undo.");
                    }
                }else if(currentX < 6){
                    // select tower
                    while(!towers.isEmpty()){
                        Tower t = towers.pop();
                        player.setGold(player.getGold() + t.getCost());
                        bg.removePlayerTower(t);
                    }
                    player.gm = BUILDING_SELECTING;
                }
            }else
            if (player.getGold() >= towerTypes[towerSelection][0] && bg.checkPlayerGridAvailable(currentX, currentY)) {
                // create tower and add to temporary stack
                if(currentX == 9 && currentY == 0){
                    notification.newNotification("Unable to build on enemy spawn.");
                    return;
                };
                Tower t = bg.createPlayerTower(currentX,currentY,towerTypes[towerSelection][1], towerTypes[towerSelection][2], (int)towerTypes[towerSelection][0]);
                towers.push(t);
                bg.addPlayerTower(t);
                player.setGold(player.getGold() - t.getCost());
                // if no path is able to be made, undo changes
                if(!bg.createPath()){
                    notification.newNotification("Cannot place in path of creeps.");
                    towers.pop();
                    bg.removePlayerTower(t);
                    player.setGold(player.getGold() + t.getCost());
                }
            }
        }else if(player.gm == BUILDING_SELECTING){
            currentX = (int)((x/width)*10);
            if(currentY>=4){
                if(currentX < 2) {
                    player.gm = BUILDING_PLACING;
                }else {
                    player.gm = SELLING;
                }
            }else{
                this.towerSelection = currentY*2 + (currentX/5);
            }
        }else if(player.gm == SELLING){
            if(currentY>=4){
                currentX = (int)((x/width)*10);
                if(currentX < 2) {
                    // confirm
                    // empty temporary stack
                    while(!towers.isEmpty()){
                        towers.pop();
                    }
                    player.gm = BATTLEGROUND;
                }else if(currentX < 4){
                    // undo
                    if(!towers.isEmpty()){
                        Tower t = towers.pop();
                        player.setGold(player.getGold() - t.getCost()/2);
                        bg.addPlayerTower(t);
                    } else {
                        notification.newNotification("Nothing to undo.");
                    }
                }else if(currentX < 6){
                    // select tower
                    while(!towers.isEmpty()){
                        Tower t = towers.pop();
                        player.setGold(player.getGold() - t.getCost()/2);
                        bg.addPlayerTower(t);
                    }
                    player.gm = BUILDING_SELECTING;
                }
            }else
            if (currentX > 0 && currentY >= 0 && currentX < 10 && currentY < 5) {
                Tower t = bg.getPlayerTower(currentX,currentY);
                if(t == null){
                    notification.newNotification("Tower not found at selected location.");
                } else {
                    towers.push(t);
                    bg.removePlayerTower(t);
                    player.setGold(player.getGold() + t.getCost()/2);
                }
            }
        }

    }


    public void update(){}

    public void render(Canvas canvas){
        if(player.gm == BUILDING_PLACING || player.gm == SELLING) {
            this.buildPlacingRenderer.render(canvas, this.towerTypes);
        }else if(player.gm == BUILDING_SELECTING){
            this.buildSelectingRenderer.render(canvas, this.towerSelection, this.towerTypes);
        }
    }
}
