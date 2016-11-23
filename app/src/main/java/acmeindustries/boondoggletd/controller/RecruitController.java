package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Battleground;
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
    private RecruitRenderer recruitRenderer;
    private Notification notification;
    private float width;
    private float height;

    public RecruitController(Player p, Battleground bg, Notification n, float width, float height){

        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.notification = n;
        this.recruitRenderer = new RecruitRenderer(bg, p);

    }

    public void press(float x, float y){
        int currentX = (int)((x/width)*10);
        int currentY = (int)((y/height)*5);
        if(currentY>=4){
            player.gm = BATTLEGROUND;
        }else{
            player.gm = BUILDING_PLACING;
        }
    }

    public void update(){}

    public void render(Canvas canvas){
        this.recruitRenderer.render(canvas);
    }


}
