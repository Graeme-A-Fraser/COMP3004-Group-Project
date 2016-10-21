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

public class TowerBuildRenderer implements Renderer {

    private Battleground bg;
    private Player player;

    public TowerBuildRenderer(Battleground bg, Player player){
        this.bg = bg;
        this.player = player;
    }

    @Override
    public void render(Canvas c) {

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
        paint.setColor(Color.rgb(200,255,255));
        c.drawRect(0, c.getHeight()/5*4, c.getWidth()/6,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Confirm", 0, c.getHeight()/40*37, paint);

        //btn2
        paint.setColor(Color.rgb(255,200,255));
        c.drawRect(c.getWidth()/6, c.getHeight()/5*4, c.getWidth()/3,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Cancel", c.getWidth()/6, c.getHeight()/40*37, paint);
        // info text?
        paint.setColor(Color.BLACK);
        c.drawRect(c.getWidth()/6*2, c.getHeight()/5*4, c.getWidth(),c.getHeight(), paint);
        paint.setColor(Color.YELLOW);
        c.drawText("Info about tower selection.", c.getWidth()/6*2, c.getHeight()/40*37, paint);

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
        //line for right side
        c.drawRect(TILEWIDTH*10,0,TILEWIDTH*11,TILEHEIGHT*10, paint);
    }
}
