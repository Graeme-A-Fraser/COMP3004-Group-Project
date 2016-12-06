package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;
import acmeindustries.boondoggletd.util.CustomColours;

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

    public void render(Canvas c) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int TILEWIDTH = c.getWidth()/8;
        int TILEHEIGHT = c.getHeight()/5;

        //bg
        c.drawColor(CustomColours.light);
        //towers
        for (Tower t:bg.getPlayerTowers()
                ) {
            paint.setColor(CustomColours.dark);
            c.drawRect(t.getX()* TILEWIDTH - (2*TILEWIDTH), t.getY()*TILEHEIGHT,
                    t.getX()* TILEWIDTH + TILEWIDTH - (2*TILEWIDTH), t.getY()* TILEHEIGHT+ TILEHEIGHT, paint);
            paint.setColor(t.getColor());
            c.drawCircle(TILEWIDTH/2 + t.getX()* TILEWIDTH - (2*TILEWIDTH), TILEHEIGHT/2 + t.getY()*TILEHEIGHT,
                    TILEWIDTH/3, paint);
        }

        // START & END
        paint.setColor(CustomColours.red);
        c.drawRect(7* TILEWIDTH, 0*TILEHEIGHT,
                7* TILEWIDTH + TILEWIDTH, 0* TILEHEIGHT+ TILEHEIGHT, paint);

        // drawing player grid
        paint.setColor(CustomColours.dark);
        paint.setStrokeWidth(3);
        for (int i = 0; i <= 10; i++) {
            c.drawLine(bg.getX() + TILEWIDTH*i,bg.getY(),
                    bg.getX()+TILEWIDTH*i,bg.getY()+TILEHEIGHT*4,paint);
        }
        for (int i = 0; i <= 4; i++) {
            c.drawLine(bg.getX(),bg.getY() + TILEHEIGHT*i,
                    bg.getX()+ TILEWIDTH*10,bg.getY() + TILEHEIGHT*i,paint);
        }


        //action bar/gui TODO: maybe seperate this into its own class?

        paint.setTextSize(c.getHeight()/20);

        //btn1
        paint.setColor(CustomColours.yellow2);
        c.drawRect(0, c.getHeight() / 10 * 8, c.getWidth() / 10 * 2, c.getHeight(), paint);
        paint.setColor(CustomColours.dark2);
        c.drawText("Confirm", 10, c.getHeight() / 40 * 37, paint);

        //btn2
        paint.setColor(CustomColours.yellow2);
        c.drawRect(c.getWidth() / 10 * 2, c.getHeight() / 10 * 8, c.getWidth() / 10 * 4, c.getHeight(), paint);
        paint.setColor(CustomColours.dark2);
        c.drawText("Undo",10 + c.getWidth() / 10 * 2, c.getHeight() / 40 * 37, paint);
        c.drawLine(c.getWidth()/10*2,c.getHeight()/10*8, c.getWidth()/10*2, c.getHeight(), paint);

        //btn3
        paint.setColor(CustomColours.yellow2);
        c.drawRect(c.getWidth() / 10 * 4, c.getHeight() / 10 * 8, c.getWidth() / 10 * 6, c.getHeight(), paint);
        paint.setColor(CustomColours.dark2);
        c.drawText("Back", 10 + c.getWidth() / 10 * 4, c.getHeight() / 40 * 37, paint);
        c.drawLine(c.getWidth()/10*4,c.getHeight()/10*8, c.getWidth()/10*4, c.getHeight(), paint);
        // info text?
        paint.setTextSize(c.getHeight()/30);
        paint.setColor(CustomColours.yellow2);
        c.drawRect(c.getWidth()/10*6, c.getHeight()/10*8, c.getWidth(),c.getHeight(), paint);
        paint.setColor(CustomColours.dark2);
        c.drawText(String.format("Gold: %d", player.getGold()), 10+c.getWidth()/10*6, c.getHeight()/40*35, paint);
        c.drawText(String.format("Lives: %d", player.getHp()), 10+c.getWidth()/10*6, c.getHeight()/40*37, paint);
        c.drawText(String.format("Enemy Lives: %d",bg.getEnemy().getHp()), 10+c.getWidth()/10*6, c.getHeight()/40*39, paint);
        c.drawLine(c.getWidth()/10*6,c.getHeight()/10*8, c.getWidth()/10*6, c.getHeight(), paint);
        //line for top of ui
        paint.setColor(CustomColours.dark2);
        paint.setStrokeWidth(3);
        c.drawLine(0,c.getHeight()/10*8, c.getWidth(),c.getHeight()/10*8, paint);
        //line for right side
        paint.setColor(Color.BLACK);
        c.drawRect(TILEWIDTH*8,0,TILEWIDTH*9,TILEHEIGHT*10, paint);
    }
}
