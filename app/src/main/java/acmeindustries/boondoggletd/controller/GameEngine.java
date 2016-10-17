package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Battleground;
import acmeindustries.boondoggletd.view.BattlegroundRenderer;
import acmeindustries.boondoggletd.view.Renderer;

public class GameEngine {

    private Renderer renderer;
    private Battleground bg;

    public GameEngine(){
        this.init();
    }


    private void init() {
        // can make a seperate renderer/view for menu here-for now just jumping straight into it
        bg = new Battleground();
        renderer = new BattlegroundRenderer(bg);
    }

    public void press(float x, float y){
        System.out.printf("pressed at %f, %f\n", x, y);
    }

    public void release(float x, float y){
        System.out.printf("released at %f, %f\n", x, y);
    }

    public void update(float delta){
        // should probably get working delta
    }

    public void render(Canvas canvas){
        renderer.render(canvas);
    }
}
