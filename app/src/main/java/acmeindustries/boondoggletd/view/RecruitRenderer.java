package acmeindustries.boondoggletd.view;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;

/**
 * a view for purchasing units to attack the enemy
 */

public class RecruitRenderer implements Renderer {

    private Battleground bg;
    private Player player;

    public RecruitRenderer(Battleground bg, Player p){
        this.bg = bg;
        this.player = p;
    }

    public void render(Canvas c) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        //bg
        c.drawRGB(255,255,255);
        paint.setTextSize(c.getHeight()/20);




        //btn1
        paint.setColor(Color.rgb(177, 189, 29));
        c.drawRect(0, c.getHeight()/5*4, c.getWidth()/2,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Confirm Selection", 0, c.getHeight()/40*37, paint);
        // gold
        paint.setColor(Color.BLACK);
        c.drawRect(c.getWidth()/2, c.getHeight()/5*4, c.getWidth(),c.getHeight(), paint);
        paint.setColor(Color.YELLOW);
        c.drawText(String.format("Gold: %d", player.getGold()), c.getWidth()/2, c.getHeight()/20*18, paint);
        c.drawText(String.format("Lives: %d", player.getHp()), c.getWidth()/2, c.getHeight()/20*19, paint);
    }
}
