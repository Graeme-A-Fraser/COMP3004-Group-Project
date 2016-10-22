package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.view.BuildPlacingRenderer;
import acmeindustries.boondoggletd.view.BuildSelectingRenderer;

import static acmeindustries.boondoggletd.model.Player.GameMode.BATTLEGROUND;
import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING_PLACING;
import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING_SELECTING;

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

    // consider moving these definitions to their own class so everyone can use
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

    public BuildController(Player p, Battleground bg,float width,float height){
        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.buildPlacingRenderer = new BuildPlacingRenderer(bg, p);
        this.buildSelectingRenderer = new BuildSelectingRenderer(bg, p);
        this.currentX = 0;
        this.currentY = 0;
        this.towerSelection = 0;
    }

    public void press(float x, float y){
        //System.out.printf("pressed at %f, %f - gridx: %d, gridy: %d\n", x, y, (int)((x/width)*8), (int)((y/height)*6));

        currentX = (int)((x/width)*6);
        currentY = (int)((y/height)*5);

/*        if(player.gm == BUILDING) {
            if (bg.checkPlayerGridAvailable(currentX,currentY) == true) {
                // open screen for tower type with gold values, then if you can afford call below
                player.gm = SELECTING_TOWER;

                bg.addPlayerTower((int) ((x / width) * 6), (int) ((y / height) * 5), 2, 1);
                player.setGold(player.getGold() - 10);
            } else if ((x / width) * 6 < 1 && (y / height) * 5 > 4) {
                player.gm = BATTLEGROUND;
            }
        }else { // if in tower selection
            player.gm = BUILDING;
        }
        */
        if(player.gm == BUILDING_PLACING) {
            if(currentY>=4){
                if(currentX < 1) {
                    // confirm
                    player.gm = BATTLEGROUND;
                }else if(currentX < 2){
                    // cancel
                    player.gm = BATTLEGROUND;
                }else if(currentX < 3){
                    player.gm = BUILDING_SELECTING;
                }
            }else
            if (player.getGold() >= towerTypes[towerSelection][0]) {
                player.setGold(player.getGold() - (int)towerTypes[towerSelection][0]);
                bg.addPlayerTower(currentX, currentY, towerTypes[towerSelection][1], towerTypes[towerSelection][2]);
            }
        }else if(player.gm == BUILDING_SELECTING){
            if(currentY>=4){
                player.gm = BUILDING_PLACING;
            }else{
                this.towerSelection = currentY*2 + (currentX/3);
            }
        }

    }


    public void update(float delta){}

    public void render(Canvas canvas){
        if(player.gm == BUILDING_PLACING) {
            this.buildPlacingRenderer.render(canvas, this.towerTypes);
        }else if(player.gm == BUILDING_SELECTING){
            this.buildSelectingRenderer.render(canvas, this.towerSelection, this.towerTypes);
        }
    }
}
