package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.view.BuildRenderer;
import acmeindustries.boondoggletd.view.TowerBuildRenderer;

import static acmeindustries.boondoggletd.model.Player.GameMode.BATTLEGROUND;
import static acmeindustries.boondoggletd.model.Player.GameMode.BUILDING;
import static acmeindustries.boondoggletd.model.Player.GameMode.SELECTING_TOWER;

/**
 * class for controlling the build view
 */

public class BuildController {

    private Player player;
    private Battleground bg;
    private BuildRenderer buildRenderer;
    private TowerBuildRenderer towerBuildRenderer;
    private float width;
    private float height;
    private int currentX, currentY;

    public BuildController(Player p, Battleground bg,float width,float height){
        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.buildRenderer = new BuildRenderer(bg, p);
        this.towerBuildRenderer = new TowerBuildRenderer(bg, p);
        this.currentX = 0;
        this.currentY = 0;

    }

    public void press(float x, float y){
        //System.out.printf("pressed at %f, %f - gridx: %d, gridy: %d\n", x, y, (int)((x/width)*8), (int)((y/height)*6));


        if(player.gm == BUILDING) {
            currentX = (int)((x/width)*6);
            currentY = (int)((y/height)*5);

            if (bg.checkPlayerGridAvailable(currentX,currentY) == true) {
                // open screen for tower type with gold values, then if you can afford call below
                player.gm = SELECTING_TOWER;

                /*bg.addPlayerTower((int) ((x / width) * 6), (int) ((y / height) * 5), 2, 1);
                player.setGold(player.getGold() - 10);*/
            } else if ((x / width) * 6 < 1 && (y / height) * 5 > 4) {
                player.gm = BATTLEGROUND;
            }
        }else { // if in tower selection
            player.gm = BUILDING;
        }

    }


    public void update(float delta){}

    public void render(Canvas canvas){
        if(player.gm == BUILDING) {
            this.buildRenderer.render(canvas);
        }else {
            this.towerBuildRenderer.render(canvas);
        }
    }
}
