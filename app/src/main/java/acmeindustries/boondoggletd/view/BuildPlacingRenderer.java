package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;

/**
 * this class should only show your side of the battlefield, making it very easy to build towers at certain locations
 */

public class BuildPlacingRenderer{

    private Battleground bg;
    private Player player;

    public BuildPlacingRenderer(Battleground bg, Player p){
        this.bg = bg;
        this.player = p;
    }

    public void render(Canvas c, float[][] towers) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        Paint paint = new Paint();
        int TILEWIDTH = c.getWidth()/6;
        int TILEHEIGHT = c.getHeight()/5;

        //bg
        c.drawRGB(100,100,100);
        //towers
        paint.setColor(Color.rgb(255,203,5));
        for (Tower t:bg.getPlayerTowers()
                ) {
            c.drawRect(t.getX()* TILEWIDTH, t.getY()*TILEHEIGHT,
                    t.getX()* TILEWIDTH + TILEWIDTH, t.getY()* TILEHEIGHT+ TILEHEIGHT, paint);
        }

        // drawing player grid
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        for (int i = 0; i <= 6; i++) {
            c.drawLine(bg.getX() + TILEWIDTH*i,bg.getY(),
                    bg.getX()+TILEWIDTH*i,bg.getY()+TILEHEIGHT*4,paint);
        }
        for (int i = 0; i <= 4; i++) {
            c.drawLine(bg.getX(),bg.getY() + TILEHEIGHT*i,
                    bg.getX()+ TILEWIDTH*6,bg.getY() + TILEHEIGHT*i,paint);
        }


        //action bar/gui TODO: maybe seperate this into its own class?

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

        //btn3
        paint.setColor(Color.rgb(255,255,200));
        c.drawRect(c.getWidth()/6*2, c.getHeight()/5*4, c.getWidth()/6*3,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Back", c.getWidth()/6*2, c.getHeight()/40*37, paint);
        // info text?
        paint.setColor(Color.BLACK);
        c.drawRect(c.getWidth()/2, c.getHeight()/5*4, c.getWidth(),c.getHeight(), paint);
        paint.setColor(Color.YELLOW);
        c.drawText(String.format("Gold: %d", player.getGold()), c.getWidth()/2, c.getHeight()/20*18, paint);
        c.drawText(String.format("Lives: %d", player.getHp()), c.getWidth()/2, c.getHeight()/20*19, paint);

        //line for top of ui
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        c.drawLine(0,TILEHEIGHT*4, TILEWIDTH*6,TILEHEIGHT*4, paint);
        //line for right side
        c.drawRect(TILEWIDTH*10,0,TILEWIDTH*11,TILEHEIGHT*10, paint);
    }
}
