package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;

/**
 * Created by Eric on 10/20/2016.
 */

public class BuildSelectingRenderer {

    private Battleground bg;
    private Player player;

    public BuildSelectingRenderer(Battleground bg, Player player){
        this.bg = bg;
        this.player = player;
    }

    public void render(Canvas c, int selection) {

        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        Paint paint = new Paint();
        int TILEWIDTH = c.getWidth()/6;
        int TILEHEIGHT = c.getHeight()/5;

        //bg
        c.drawRGB(255,255,255);
        paint.setTextSize(c.getHeight()/20);




        //btn1
        paint.setColor(Color.rgb(200,200,200));
        c.drawRect(0, c.getHeight()/5*4, c.getWidth()/2,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Continue to Placement", 0, c.getHeight()/40*37, paint);
        // info text?
        paint.setColor(Color.BLACK);
        c.drawRect(c.getWidth()/2, c.getHeight()/5*4, c.getWidth(),c.getHeight(), paint);
        paint.setColor(Color.YELLOW);
        c.drawText("Info about tower selection.", c.getWidth()/2, c.getHeight()/40*37, paint);

        //Grid
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        c.drawLine(TILEWIDTH*6/2,0, TILEWIDTH*6/2,TILEHEIGHT*4, paint);
        c.drawLine(0,TILEHEIGHT*1, TILEWIDTH*6,TILEHEIGHT*1, paint);
        c.drawText("Tower 1", 0, TILEHEIGHT*1, paint);
        c.drawText("Tower 2", c.getWidth()/2, TILEHEIGHT*1, paint);
        c.drawLine(0,TILEHEIGHT*2, TILEWIDTH*6,TILEHEIGHT*2, paint);
        c.drawText("Tower 3", 0, TILEHEIGHT*2, paint);
        c.drawText("Tower 4", c.getWidth()/2, TILEHEIGHT*2, paint);
        c.drawLine(0,TILEHEIGHT*3, TILEWIDTH*6,TILEHEIGHT*3, paint);
        c.drawText("Tower 5", 0, TILEHEIGHT*3, paint);
        c.drawText("Tower 6", c.getWidth()/2, TILEHEIGHT*3, paint);
        c.drawLine(0,TILEHEIGHT*4, TILEWIDTH*6,TILEHEIGHT*4, paint);
        c.drawText("Tower 7", 0, TILEHEIGHT*4, paint);
        c.drawText("Tower 8", c.getWidth()/2, TILEHEIGHT*4, paint);

        // HIGHLIGHT
        paint.setColor(Color.argb(100,255,255,255));
        // PLAY WITH MOD AND DIV OF SELECTION TO GET THE RIGHT VALUES
        c.drawRect(c.getWidth()*selection%2, c.getHeight()/5*((int)(selection/2)), c.getWidth()/2,c.getHeight(), paint);

        //line for right side
        c.drawRect(TILEWIDTH*10,0,TILEWIDTH*11,TILEHEIGHT*10, paint);
    }
}
