package acmeindustries.boondoggletd.controller;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;

import static acmeindustries.boondoggletd.model.Player.GameMode.BATTLEGROUND;

/**
 * class for controlling the build view
 */

public class BuildController {

    private Player player;
    private Battleground bg;
    private float width;
    private float height;

    public BuildController(Player p, Battleground bg,float width,float height){
        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
    }

    public void press(float x, float y){
        //System.out.printf("pressed at %f, %f - gridx: %d, gridy: %d\n", x, y, (int)((x/width)*8), (int)((y/height)*6));


        if(bg.addPlayerTower((int)((x/width)*6), (int)((y/height)*5)) == true){
            //System.out.println("Tower created!");
        }else if((x/width)*6>3 && (y/height)*5>4){
            player.gm = BATTLEGROUND;
        }

    }


    public void update(float delta){}
}
