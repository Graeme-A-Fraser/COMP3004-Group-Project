package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.model.Player;
import acmeindustries.boondoggletd.view.BattlegroundRenderer;
import acmeindustries.boondoggletd.view.BuildRenderer;
import acmeindustries.boondoggletd.view.RecruitRenderer;

import static acmeindustries.boondoggletd.model.Player.GameMode.*;

public class GameEngine {

    private Player player; //keeps track of stuff like mode, gold, hp

    private BattlegroundRenderer battlegroundRenderer;
    private BuildRenderer buildRenderer;
    private RecruitRenderer recruitRenderer;

    private BuildController buildController;

    private Battleground bg;
    private float width;
    private float height;

    public GameEngine(float w, float h){
        this.width = w;
        this.height = h;
        this.init();
    }

    private void init() {
        bg = new Battleground();

        player = new Player(BATTLEGROUND);

        // views
        battlegroundRenderer = new BattlegroundRenderer(bg);
        buildRenderer = new BuildRenderer(bg);
        recruitRenderer = new RecruitRenderer();

        // additional controllers for breaking out smaller tasks
        buildController = new BuildController(player, bg, width, height);

    }

    public void press(float x, float y){
        //System.out.printf("pressed at %f, %f - gridx: %d, gridy: %d\n", x, y, (int)((x/width)*10), (int)((y/height)*10));

        /* creating towers in bg mode...
        if(bg.addPlayerTower((int)((x/width)*10)-bg.getPlayerGridX(), (int)((y/height)*10)-bg.getPlayerGridY()) == true){
            System.out.println("Tower created!");
        }else{
            System.out.println("Problem creating tower.");
        }
        */
        switch(player.gm){
            case BATTLEGROUND:
                // bg contro
                if((x/width)*10<2 && (y/height)*10>8){
                    player.gm = BUILDING;
                }
                break;
            case BUILDING:
                // build controller?
                buildController.press(x,y);
                break;
            case RECRUITING:
                // recruit controller?
                break;
        }
    }

    public void release(float x, float y){
        System.out.printf("released at %f, %f\n", x, y);
    }

    public void update(float delta){
    }

    public void render(Canvas canvas){
        switch(player.gm){
            case BATTLEGROUND:
                battlegroundRenderer.render(canvas);
                break;
            case BUILDING:
                buildRenderer.render(canvas);
                break;
            case RECRUITING:
                recruitRenderer.render(canvas);
                break;
        }
    }
}
