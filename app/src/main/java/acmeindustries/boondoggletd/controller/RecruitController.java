package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.view.RecruitRenderer;

/**
 * Created by ericm on 2016-10-21.
 */

public class RecruitController {

    private Player player;
    private Battleground bg;
    private RecruitRenderer recruitRenderer;
    private float width;
    private float height;

    public RecruitController(Player p, Battleground bg, float width, float height){

        this.player = p;
        this.bg = bg;
        this.width = width;
        this.height = height;

    }

    public void press(float x, float y){
        float currentX = (int)((x/width)*6);
        float currentY = (int)((y/height)*5);

    }

    public void update(){}

    public void render(Canvas canvas){
        this.recruitRenderer.render(canvas);
    }


}
