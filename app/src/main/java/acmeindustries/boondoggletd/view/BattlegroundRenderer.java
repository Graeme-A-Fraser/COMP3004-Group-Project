package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Bullet;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;
import acmeindustries.boondoggletd.util.CustomColours;

/**
 * this class can be considered a view which shows the overall battlefield with room at the bottom for an action bar.
 */

public class BattlegroundRenderer implements Renderer{

    private Player player;
    private Battleground bg;
    private int TILEWIDTH;
    private int TILEHEIGHT;

    public BattlegroundRenderer(Battleground bg, Player player){
        this.bg = bg;
        this.player = player;
    }

    public void render(Canvas c) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        TILEWIDTH = c.getWidth()/10;
        TILEHEIGHT = c.getHeight()/10;

        //bg
        c.drawColor(CustomColours.light);

        // player towers
        for (Tower t:bg.getPlayerTowers()
             ) {
            paint.setColor(CustomColours.dark);
            c.drawRect(t.getX()* TILEWIDTH + TILEWIDTH*bg.getPlayerGridX(), t.getY()*TILEHEIGHT + TILEHEIGHT*bg.getPlayerGridY(),
                    t.getX()* TILEWIDTH + TILEWIDTH*bg.getPlayerGridX() + TILEWIDTH, t.getY()* TILEHEIGHT+TILEHEIGHT*bg.getPlayerGridY() + TILEHEIGHT, paint);
            paint.setColor(t.getColor());
            c.drawCircle(t.getX()* TILEWIDTH + TILEWIDTH*bg.getPlayerGridX()+TILEWIDTH/2, t.getY()*TILEHEIGHT + TILEHEIGHT*bg.getPlayerGridY()+TILEHEIGHT/2,
                    TILEHEIGHT/3, paint);
        }
        // enemy towers
        for (Tower t:bg.getEnemyTowers()
                ) {
            paint.setColor(CustomColours.dark);
            c.drawRect(t.getX()* TILEWIDTH + TILEWIDTH*bg.getEnemyGridX(), t.getY()*TILEHEIGHT + TILEHEIGHT*bg.getEnemyGridY(),
                    t.getX()* TILEWIDTH + TILEWIDTH*bg.getEnemyGridX() + TILEWIDTH, t.getY()* TILEHEIGHT+TILEHEIGHT*bg.getEnemyGridY() + TILEHEIGHT, paint);
            paint.setColor(t.getColor());
            c.drawCircle(t.getX()* TILEWIDTH + TILEWIDTH*bg.getEnemyGridX()+TILEWIDTH/2, t.getY()*TILEHEIGHT + TILEHEIGHT*bg.getEnemyGridY()+TILEHEIGHT/2,
                    TILEHEIGHT/3, paint);
        }

        //enemy creeps
        for (Creep creep:bg.getEnemyCreeps()
                ) {
            paint.setColor(CustomColours.green);
            c.drawRect(creep.getX()*TILEWIDTH - TILEWIDTH/6,creep.getY()*TILEHEIGHT - TILEHEIGHT/4-14, creep.getX()*TILEWIDTH - TILEWIDTH/6 + TILEWIDTH*(creep.getHp()/creep.getMaxHP())/3, creep.getY()*TILEHEIGHT - TILEHEIGHT/4-8, paint);
            paint.setColor(CustomColours.red);
            c.drawCircle(creep.getX()*TILEWIDTH, creep.getY()*TILEHEIGHT,
                    creep.getRadius()*TILEHEIGHT, paint);
        }
        //player creeps
        for (Creep creep:bg.getPlayerCreeps()
                ) {
            paint.setColor(CustomColours.green);
            c.drawRect(creep.getX()*TILEWIDTH - TILEWIDTH/6,creep.getY()*TILEHEIGHT - TILEHEIGHT/4-14, creep.getX()*TILEWIDTH - TILEWIDTH/6 + TILEWIDTH*(creep.getHp()/creep.getMaxHP())/3, creep.getY()*TILEHEIGHT - TILEHEIGHT/4-8, paint);
            paint.setColor(CustomColours.green2);
            c.drawCircle(creep.getX()*TILEWIDTH, creep.getY()*TILEHEIGHT,
                    creep.getRadius()*TILEHEIGHT, paint);
        }

        //bullets
        paint.setColor(CustomColours.dark);
        for (Bullet b:bg.getBullets()
                ) {
            c.drawCircle(b.getX()*TILEWIDTH,b.getY()*TILEHEIGHT,
                    0.1f*TILEHEIGHT, paint);
        }
        //player castle
        paint.setColor(CustomColours.green2);
        c.drawRect(bg.getPlayerCastle().getX()*TILEWIDTH, bg.getPlayerCastle().getY()*TILEHEIGHT,
                bg.getPlayerCastle().getX()*TILEWIDTH + TILEWIDTH*2, bg.getPlayerCastle().getY()*TILEHEIGHT+TILEHEIGHT*4, paint);

        //enemy castle
        paint.setColor(CustomColours.red);
        c.drawRect(bg.getEnemyCastle().getX()*TILEWIDTH, bg.getEnemyCastle().getY()*TILEHEIGHT,
                bg.getEnemyCastle().getX()*TILEWIDTH + TILEWIDTH*2, bg.getEnemyCastle().getY()*TILEHEIGHT+TILEHEIGHT*4, paint);
        // line between bases
        paint.setColor(CustomColours.dark2);
        paint.setStrokeWidth(3);
        c.drawLine(bg.getPlayerCastle().getX()*TILEWIDTH, bg.getPlayerCastle().getY()*TILEHEIGHT, (bg.getEnemyCastle().getX()+2)*TILEWIDTH, bg.getPlayerCastle().getY()*TILEHEIGHT, paint);

        //action bar/gui TODO: maybe seperate this into its own class?

        paint.setTextSize(c.getHeight()/20);

        if(bg.spawning || !bg.getEnemyCreeps().isEmpty()) {
            paint.setColor(CustomColours.dark);
            c.drawRect(0, c.getHeight() / 10 * 8, c.getWidth() / 10 * 6, c.getHeight(), paint);
            paint.setColor(CustomColours.light);
            c.drawText("Wave Started", c.getWidth() / 10 * 2, c.getHeight() / 40 * 37, paint);
        }else {
            //btn1
            paint.setColor(CustomColours.yellow2);
            c.drawRect(0, c.getHeight() / 10 * 8, c.getWidth() / 10 * 2, c.getHeight(), paint);
            paint.setColor(CustomColours.dark2);
            c.drawText("Build", 10, c.getHeight() / 40 * 37, paint);

            //btn2
            paint.setColor(CustomColours.yellow2);
            c.drawRect(c.getWidth() / 10 * 2, c.getHeight() / 10 * 8, c.getWidth() / 10 * 4, c.getHeight(), paint);
            paint.setColor(CustomColours.dark2);
            c.drawText("Recruit",10 + c.getWidth() / 10 * 2, c.getHeight() / 40 * 37, paint);
            c.drawLine(c.getWidth()/10*2,c.getHeight()/10*8, c.getWidth()/10*2, c.getHeight(), paint);

            //btn3
            paint.setColor(CustomColours.yellow2);
            c.drawRect(c.getWidth() / 10 * 4, c.getHeight() / 10 * 8, c.getWidth() / 10 * 6, c.getHeight(), paint);
            paint.setColor(CustomColours.dark2);
            c.drawText("Start Wave", 10 + c.getWidth() / 10 * 4, c.getHeight() / 40 * 37, paint);
            c.drawLine(c.getWidth()/10*4,c.getHeight()/10*8, c.getWidth()/10*4, c.getHeight(), paint);
        }
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
        c.drawLine(0,TILEHEIGHT*8, TILEWIDTH*10,TILEHEIGHT*8, paint);
        //line for right side
        paint.setColor(Color.BLACK);
        c.drawRect(TILEWIDTH*10,0,TILEWIDTH*11,TILEHEIGHT*10, paint);
    }
}
