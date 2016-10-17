package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Tower;

/**
 * this class is essentially the in-game renderer. should have a seperate layer for interface within still have to play around with how to go about this.
 */

public class BattlegroundRenderer implements Renderer{

    private Battleground bg;
    private int TILEWIDTH=240;
    private int TILEHEIGHT=180;

    public BattlegroundRenderer(Battleground bg){
        this.bg = bg;
    }

    @Override
    public void render(Canvas c) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }
        Paint paint = new Paint();

        //bg
        c.drawRGB(251,79,79);

        // really inefficient way of drawing grid
        paint.setStrokeWidth(5);
        c.drawLine(0,0,TILEWIDTH*5,0,paint);
        c.drawLine(0,TILEHEIGHT,TILEWIDTH*5,TILEHEIGHT,paint);
        c.drawLine(0,TILEHEIGHT*2,TILEWIDTH*5,TILEHEIGHT*2,paint);
        c.drawLine(0,TILEHEIGHT*3,TILEWIDTH*5,TILEHEIGHT*3,paint);
        c.drawLine(0,TILEHEIGHT*4,TILEWIDTH*5,TILEHEIGHT*4,paint);
        c.drawLine(0,0,0,0,paint);
        c.drawLine(TILEWIDTH,0,TILEWIDTH,TILEHEIGHT*4,paint);
        c.drawLine(TILEWIDTH*2,0,TILEWIDTH*2,TILEHEIGHT*4,paint);
        c.drawLine(TILEWIDTH*3,0,TILEWIDTH*3,TILEHEIGHT*4,paint);
        c.drawLine(TILEWIDTH*4,0,TILEWIDTH*4,TILEHEIGHT*4,paint);
        c.drawLine(TILEWIDTH*5,0,TILEWIDTH*5,TILEHEIGHT*4,paint);

        //towers
        paint.setColor(Color.rgb(255,203,5));
        for (Tower t:bg.getTowers()
             ) {
            c.drawRect(t.getX()* TILEWIDTH, t.getY()*TILEHEIGHT, t.getX()* TILEWIDTH + TILEWIDTH, t.getY()* TILEHEIGHT+TILEHEIGHT, paint);
        }
    }
}
