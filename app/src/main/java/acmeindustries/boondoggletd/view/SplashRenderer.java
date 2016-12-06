package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import acmeindustries.boondoggletd.controller.SplashController;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.util.CustomColours;

/**
 * Created by ericm on 2016-12-06.
 */

public class SplashRenderer {

    public SplashRenderer(){}

    public void render(Canvas c, Player p) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        c.drawColor(CustomColours.light);
        paint.setColor(CustomColours.dark);
        paint.setTextSize(c.getHeight()/5);
        paint.setTextAlign(Paint.Align.CENTER);
        c.drawText("Boondoggle TD", c.getWidth()/2, c.getHeight()/2, paint);
        paint.setTextSize(c.getHeight()/20);
        if(p.gm == Player.GameMode.SPLASH_INTRO) {
            c.drawText("Tap to begin!", c.getWidth() / 2, c.getHeight() / 4*3, paint);
        }else if(p.gm == Player.GameMode.SPLASH_WIN){
            c.drawText("You win!", c.getWidth() / 2, c.getHeight() / 4*3, paint);
        }else if(p.gm == Player.GameMode.SPLASH_LOSE){
            c.drawText("You lose..", c.getWidth() / 2, c.getHeight() / 4*3, paint);
        }
        paint.setTextAlign(Paint.Align.LEFT);
    }
}
