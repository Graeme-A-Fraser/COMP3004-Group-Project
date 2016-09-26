package acmeindustriescarleton.comp3004;

/**
 * Basic Renderer/View class
 */


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;


public class GameRenderer {
    protected GameModel model;

    public GameRenderer(GameModel model){
        this.model = model;
    }

    public void render(){}

}
