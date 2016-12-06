package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.view.SplashRenderer;

/**
 * Created by ericm on 2016-12-06.
 */

public class SplashController {

    private Player player;
    private SplashRenderer splashRenderer;

    public SplashController(Player p){
        this.player = p;
        splashRenderer = new SplashRenderer();
    }

    public boolean press(){
        if(player.gm == Player.GameMode.SPLASH_LOSE || player.gm == Player.GameMode.SPLASH_WIN){
            return true;
        }
        player.gm = Player.GameMode.BATTLEGROUND;
        return false;
    }

    public void render(Canvas c){
        splashRenderer.render(c,player);
    }

}
