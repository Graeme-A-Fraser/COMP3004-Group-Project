package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Tower;

/**
 * this class should only show your side of the battlefield, making it very easy to build towers at certain locations
 */

public class BuildRenderer implements Renderer{

    private Battleground bg;

    public BuildRenderer(Battleground bg){
        this.bg = bg;
    }

    @Override
    public void render(Canvas c) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        Paint paint = new Paint();
        int TILEWIDTH = c.getWidth()/8;
        int TILEHEIGHT = c.getHeight()/6;

        //bg
        c.drawRGB(100,100,100);

        // drawing player grid
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        for (int i = 0; i <= 6; i++) {
            c.drawLine(bg.getX() + TILEWIDTH*i,bg.getY(),
                    bg.getX()+TILEWIDTH*i,bg.getY()+TILEHEIGHT*4,paint);
        }
        for (int i = 0; i <= 4; i++) {
            c.drawLine(bg.getX(),bg.getY() + TILEHEIGHT*i,
                    bg.getX()+ TILEWIDTH*6,bg.getY() + TILEHEIGHT*i,paint);
        }

        paint.setColor(Color.rgb(255,203,5));
        for (Tower t:bg.getPlayerTowers()
                ) {
            c.drawRect(t.getX()* TILEWIDTH, t.getY()*TILEHEIGHT,
                    t.getX()* TILEWIDTH + TILEWIDTH, t.getY()* TILEHEIGHT+ TILEHEIGHT, paint);
        }

        //action bar/gui TODO: maybe seperate this into its own class?

        paint.setTextSize(c.getHeight()/20);

        //btn1
        paint.setColor(Color.rgb(200,255,255));
        c.drawRect(0, c.getHeight()/6*4, c.getWidth()/8,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Confirm", 0, c.getHeight(), paint);

        //btn2
        paint.setColor(Color.rgb(255,200,255));
        c.drawRect(c.getWidth()/8, c.getHeight()/6*4, c.getWidth()/3,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Cancel", c.getWidth()/6, c.getHeight(), paint);

        //btn3
        paint.setColor(Color.rgb(255,255,200));
        c.drawRect(c.getWidth()/8*2, c.getHeight()/6*4, c.getWidth()/6*3,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Upgrade", c.getWidth()/6*2, c.getHeight(), paint);
        // info text?
        paint.setColor(Color.rgb(200,200,200));
        c.drawRect(c.getWidth()/2, c.getHeight()/6*4, c.getWidth(),c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("TEMPORARY BACK BUTTON", c.getWidth()/2, c.getHeight(), paint);
    }
}
