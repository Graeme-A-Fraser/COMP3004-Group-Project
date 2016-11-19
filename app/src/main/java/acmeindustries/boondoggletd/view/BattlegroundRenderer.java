package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Bullet;
import acmeindustries.boondoggletd.model.Creep;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.model.Tower;

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
        TILEWIDTH = c.getWidth()/10;
        TILEHEIGHT = c.getHeight()/10;

        //bg
        c.drawRGB(100,100,100);

        /* drawing player grid
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        for (int i = 0; i <= 6; i++) {
            c.drawLine(bg.getX() + TILEWIDTH*bg.getPlayerGridX()+ TILEWIDTH*i,bg.getY() + TILEHEIGHT*bg.getPlayerGridY(),
                    bg.getX()+TILEWIDTH*bg.getPlayerGridX()+TILEWIDTH*i,bg.getY()+TILEHEIGHT*4 + TILEHEIGHT*bg.getPlayerGridY(),paint);
        }
        for (int i = 0; i <= 4; i++) {
            c.drawLine(bg.getX()+ TILEWIDTH*bg.getPlayerGridX(),bg.getY() + TILEHEIGHT*i + TILEHEIGHT*bg.getPlayerGridY(),
                    bg.getX() +TILEWIDTH*bg.getPlayerGridX()+ TILEWIDTH*6,bg.getY() + TILEHEIGHT*i + TILEHEIGHT*bg.getPlayerGridY(),paint);
        }
        // drawing enemy grid
        paint.setColor(Color.CYAN);
        for (int i = 0; i <= 6; i++) {
            c.drawLine(bg.getX() + TILEWIDTH*bg.getEnemyGridX()+ TILEWIDTH*i,bg.getY() + TILEHEIGHT*bg.getEnemyGridY(),
                    bg.getX()+TILEWIDTH*bg.getEnemyGridX()+TILEWIDTH*i,bg.getY()+TILEHEIGHT*4 + TILEHEIGHT*bg.getEnemyGridY(),paint);
        }
        for (int i = 0; i <= 4; i++) {
            c.drawLine(bg.getX()+ TILEWIDTH*bg.getEnemyGridX(),bg.getY() + TILEHEIGHT*i + TILEHEIGHT*bg.getEnemyGridY(),
                    bg.getX() +TILEWIDTH*bg.getEnemyGridX()+ TILEWIDTH*6,bg.getY() + TILEHEIGHT*i + TILEHEIGHT*bg.getEnemyGridY(),paint);
        }
        */



        // player towers
        for (Tower t:bg.getPlayerTowers()
             ) {
            paint.setColor(Color.rgb(255,203,5));
            c.drawRect(t.getX()* TILEWIDTH + TILEWIDTH*bg.getPlayerGridX(), t.getY()*TILEHEIGHT + TILEHEIGHT*bg.getPlayerGridY(),
                    t.getX()* TILEWIDTH + TILEWIDTH*bg.getPlayerGridX() + TILEWIDTH, t.getY()* TILEHEIGHT+TILEHEIGHT*bg.getPlayerGridY() + TILEHEIGHT, paint);
            paint.setColor(Color.GREEN);
            c.drawCircle(t.getX()* TILEWIDTH + TILEWIDTH*bg.getPlayerGridX()+TILEWIDTH/2, t.getY()*TILEHEIGHT + TILEHEIGHT*bg.getPlayerGridY()+TILEHEIGHT/2,
                    TILEHEIGHT/6, paint);
        }
        // enemy towers
        paint.setColor(Color.rgb(255,203,5));
        for (Tower t:bg.getEnemyTowers()
                ) {
            c.drawRect(t.getX()* TILEWIDTH + TILEWIDTH*bg.getEnemyGridX(), t.getY()*TILEHEIGHT + TILEHEIGHT*bg.getEnemyGridY(),
                    t.getX()* TILEWIDTH + TILEWIDTH*bg.getEnemyGridX() + TILEWIDTH, t.getY()* TILEHEIGHT+TILEHEIGHT*bg.getEnemyGridY() + TILEHEIGHT, paint);
        }

        //player castle
        paint.setColor(Color.GREEN);
        c.drawRect(bg.getPlayerCastle().getX()*TILEWIDTH, bg.getPlayerCastle().getY()*TILEHEIGHT,
                bg.getPlayerCastle().getX()*TILEWIDTH + TILEWIDTH, bg.getPlayerCastle().getY()*TILEHEIGHT+TILEHEIGHT, paint);

        //enemy castle
        paint.setColor(Color.RED);
        c.drawRect(bg.getEnemyCastle().getX()*TILEWIDTH, bg.getEnemyCastle().getY()*TILEHEIGHT,
                bg.getEnemyCastle().getX()*TILEWIDTH + TILEWIDTH, bg.getEnemyCastle().getY()*TILEHEIGHT+TILEHEIGHT, paint);
        // line between bases
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        c.drawLine(bg.getPlayerCastle().getX()*TILEWIDTH, bg.getPlayerCastle().getY()*TILEHEIGHT, (bg.getEnemyCastle().getX()+2)*TILEWIDTH, bg.getPlayerCastle().getY()*TILEHEIGHT, paint);

        //enemy creeps
        for (Creep creep:bg.getEnemyCreeps()
                ) {
            paint.setColor(Color.GREEN);
            c.drawRect(creep.getX()*TILEWIDTH - TILEWIDTH/6,creep.getY()*TILEHEIGHT - TILEHEIGHT/4-14, creep.getX()*TILEWIDTH - TILEWIDTH/6 + TILEWIDTH*(creep.getHp()/creep.getMaxHP())/3, creep.getY()*TILEHEIGHT - TILEHEIGHT/4-8, paint);
            paint.setColor(Color.RED);
            c.drawCircle(creep.getX()*TILEWIDTH, creep.getY()*TILEHEIGHT,
                    creep.getRadius()*TILEHEIGHT, paint);
        }
        //player creeps
        paint.setColor(Color.GREEN);
        for (Creep creep:bg.getPlayerCreeps()
                ) {
            c.drawRect(creep.getX()*TILEWIDTH - TILEWIDTH/6,creep.getY()*TILEHEIGHT - TILEHEIGHT/4-14, creep.getX()*TILEWIDTH - TILEWIDTH/6 + TILEWIDTH*(creep.getHp()/creep.getMaxHP())/3, creep.getY()*TILEHEIGHT - TILEHEIGHT/4-8, paint);
            c.drawCircle(creep.getX()*TILEWIDTH, creep.getY()*TILEHEIGHT,
                    creep.getRadius()*TILEHEIGHT, paint);
        }

        //bullets
        paint.setColor(Color.MAGENTA);
        for (Bullet b:bg.getBullets()
                ) {
            c.drawCircle(b.getX()*TILEWIDTH,b.getY()*TILEHEIGHT,
                    0.1f*TILEHEIGHT, paint);
        }
        //action bar/gui TODO: maybe seperate this into its own class?


        paint.setTextSize(c.getHeight()/20);

        if(bg.spawning || !bg.getEnemyCreeps().isEmpty()) {
            paint.setColor(Color.rgb(200, 255, 255));
            c.drawRect(0, c.getHeight() / 10 * 8, c.getWidth() / 10 * 6, c.getHeight(), paint);
            paint.setColor(Color.BLACK);
            c.drawText("Wave Started", c.getWidth() / 10 * 2, c.getHeight() / 40 * 37, paint);
        }else {
            //btn1
            paint.setColor(Color.rgb(200, 255, 255));
            c.drawRect(0, c.getHeight() / 10 * 8, c.getWidth() / 10 * 2, c.getHeight(), paint);
            paint.setColor(Color.BLACK);
            c.drawText("Build", 0, c.getHeight() / 40 * 37, paint);

            //btn2
            paint.setColor(Color.rgb(255, 200, 255));
            c.drawRect(c.getWidth() / 10 * 2, c.getHeight() / 10 * 8, c.getWidth() / 10 * 4, c.getHeight(), paint);
            paint.setColor(Color.BLACK);
            c.drawText("Recruit", c.getWidth() / 10 * 2, c.getHeight() / 40 * 37, paint);

            //btn3
            paint.setColor(Color.rgb(255, 255, 200));
            c.drawRect(c.getWidth() / 10 * 4, c.getHeight() / 10 * 8, c.getWidth() / 10 * 6, c.getHeight(), paint);
            paint.setColor(Color.BLACK);
            c.drawText("Start Wave", c.getWidth() / 10 * 4, c.getHeight() / 40 * 37, paint);
        }
        // info text?
        paint.setColor(Color.BLACK);
        c.drawRect(c.getWidth()/10*6, c.getHeight()/10*8, c.getWidth(),c.getHeight(), paint);
        paint.setColor(Color.YELLOW);
        c.drawText(String.format("Gold: %d", player.getGold()), c.getWidth()/10*6, c.getHeight()/20*18, paint);
        c.drawText(String.format("Lives: %d", player.getHp()), c.getWidth()/10*6, c.getHeight()/20*19, paint);

        //line for top of ui
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        c.drawLine(0,TILEHEIGHT*8, TILEWIDTH*10,TILEHEIGHT*8, paint);
        //line for right side
        c.drawRect(TILEWIDTH*10,0,TILEWIDTH*11,TILEHEIGHT*10, paint);
    }
}
