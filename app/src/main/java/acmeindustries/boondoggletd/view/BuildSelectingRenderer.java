package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;
import acmeindustries.boondoggletd.util.CustomColours;

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

    public void render(Canvas c, int selection, Tower[] towers) {

        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int TILEWIDTH = c.getWidth()/6;
        int TILEHEIGHT = c.getHeight()/5;
        paint.setStrokeWidth(3);

        //bg
        c.drawColor(CustomColours.light);
        paint.setTextSize(c.getHeight()/20);
        //btn1
        paint.setColor(CustomColours.yellow2);
        c.drawRect(0, c.getHeight()/5*4, c.getWidth()/4,c.getHeight(), paint);
        paint.setColor(CustomColours.dark2);
        c.drawText("Buy", 10, c.getHeight()/40*37, paint);
        //btn2
        paint.setColor(CustomColours.yellow2);
        c.drawRect(c.getWidth()/4, c.getHeight()/5*4, c.getWidth()/2,c.getHeight(), paint);
        paint.setColor(CustomColours.dark2);
        c.drawText("Sell", 10 + c.getWidth()/4, c.getHeight()/40*37, paint);
        c.drawLine(c.getWidth()/4,c.getHeight()/5*4, c.getWidth()/4, c.getHeight(), paint);
        // info
        paint.setTextSize(c.getHeight()/30);
        paint.setColor(CustomColours.yellow2);
        c.drawRect(c.getWidth()/2, c.getHeight()/5*4, c.getWidth(),c.getHeight(), paint);
        paint.setColor(CustomColours.dark2);
        c.drawText(String.format("Gold: %d", player.getGold()), 10+c.getWidth()/2, c.getHeight()/40*35, paint);
        c.drawText(String.format("Lives: %d", player.getHp()), 10+c.getWidth()/2, c.getHeight()/40*37, paint);
        c.drawText(String.format("Enemy Lives: %d",bg.getEnemy().getHp()), 10+c.getWidth()/2, c.getHeight()/40*39, paint);
        // HIGHLIGHT
        paint.setColor(CustomColours.blue2);
        paint.setAlpha(100);
        c.drawRect(c.getWidth()/2*(selection%2), c.getHeight()/5*((int)(selection/2)), c.getWidth()/2 + c.getWidth()*((int)(selection%2)),c.getHeight()/5 + c.getHeight()/5*((int)(selection/2)), paint);

        //Grid
        paint.setColor(Color.BLACK);
        c.drawLine(TILEWIDTH*6/2,0, TILEWIDTH*6/2,c.getHeight(), paint);
        paint.setTextSize(c.getHeight()/30);
        for (int i = 0; i < 8; i+=2) {
            c.drawLine(0,TILEHEIGHT+TILEHEIGHT*i/2, TILEWIDTH*6,TILEHEIGHT + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("%s", towers[i]), 10, TILEHEIGHT/12*4 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Cost: $%d", towers[i].getCost()), 10, TILEHEIGHT/12*7 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Damage: %.0f", towers[i].getDamage()), 10, TILEHEIGHT/12*9 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("%s", towers[i].toString()), 10+c.getWidth()/2, TILEHEIGHT/12*4 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Cost:  $%d", towers[i+1].getCost()), 10+c.getWidth()/2, TILEHEIGHT/12*7 + TILEHEIGHT*i/2, paint);
            c.drawText(String.format("Damage: %.0f", towers[i+1].getDamage()), 10+c.getWidth()/2, TILEHEIGHT/12*9 + TILEHEIGHT*i/2, paint);
        }
        paint.setColor(Color.BLACK);
        //line for right side
        c.drawRect(TILEWIDTH*10,0,TILEWIDTH*11,TILEHEIGHT*10, paint);
    }
}
