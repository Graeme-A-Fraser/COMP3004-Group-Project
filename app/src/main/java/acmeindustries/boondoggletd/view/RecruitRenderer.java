package acmeindustries.boondoggletd.view;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Player;

/**
 * a view for purchasing units to attack the enemy
 */

public class RecruitRenderer {

    private Battleground bg;
    private Player player;

    public RecruitRenderer(Battleground bg, Player p){
        this.bg = bg;
        this.player = p;
    }

    public void render(Canvas c, int selection, Creep[] creeps, int stackSize) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int TILEWIDTH = c.getWidth()/6;
        int TILEHEIGHT = c.getHeight()/5;

        //bg
        c.drawRGB(255,255,255);


        //btn1
        paint.setTextSize(c.getHeight()/30);
        paint.setColor(Color.rgb(177, 189, 29));
        c.drawRect(0, c.getHeight()/5*4, c.getWidth()/4,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Confirm", 0, c.getHeight()/40*37, paint);
        //btn2
        paint.setColor(Color.rgb(73, 130, 133));
        c.drawRect(c.getWidth()/4, c.getHeight()/5*4, c.getWidth()/2,c.getHeight(), paint);
        paint.setColor(Color.BLACK);
        c.drawText("Undo", c.getWidth()/4, c.getHeight()/40*37, paint);
        // HIGHLIGHT
        paint.setColor(Color.argb(100,0,255,255));
        c.drawRect(c.getWidth()/2*(selection%2), c.getHeight()/5*((int)(selection/2)), c.getWidth()/2 + c.getWidth()*((int)(selection%2)),c.getHeight()/5 + c.getHeight()/5*((int)(selection/2)), paint);

        //Grid
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        c.drawLine(TILEWIDTH*6/2,0, TILEWIDTH*6/2,TILEHEIGHT*4, paint);
        paint.setTextSize(c.getHeight()/30);
        for (int i = 0; i < 8; i+=2) {
            c.drawLine(0,TILEHEIGHT+TILEHEIGHT*i/2, TILEWIDTH*6,TILEHEIGHT + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Creep %d", 1+i), 0, TILEHEIGHT/12*4 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Cost: $%d", creeps[i].getCost()), 0, TILEHEIGHT/12*7 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Speed: %f",creeps[i].getSpeed()), 0, TILEHEIGHT/12*9 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Creep %d", 2+i), c.getWidth()/2, TILEHEIGHT/12*4 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Cost:  $%d", creeps[i+1].getCost()), c.getWidth()/2, TILEHEIGHT/12*7 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Speed: %f", creeps[i+1].getSpeed()), c.getWidth()/2, TILEHEIGHT/12*9 + TILEHEIGHT*i/2, paint);
        }
        paint.setColor(Color.BLACK);
        c.drawRect(c.getWidth()/2, c.getHeight()/5*4, c.getWidth(),c.getHeight(), paint);
        paint.setColor(Color.YELLOW);
        c.drawText(String.format("Gold: %d", player.getGold()), c.getWidth()/2, c.getHeight()/20*18, paint);
        c.drawText(String.format("Lives: %d", player.getHp()), c.getWidth()/2, c.getHeight()/20*19, paint);
        for(int i = 0; i < stackSize; i++){
            c.drawRect(c.getWidth()/4*3 + i*c.getWidth()/20,c.getHeight()/5*4,c.getWidth()/4*3 + (i+1)*c.getWidth()/20, c.getHeight(),paint);
        }
        //line for right side
        c.drawRect(TILEWIDTH*10,0,TILEWIDTH*11,TILEHEIGHT*10, paint);
    }
}
