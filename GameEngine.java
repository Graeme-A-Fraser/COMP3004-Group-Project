package acmeindustriescarleton.comp3004;

/**
 * Basic Controller
 */

import android.os.Handler;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameEngine {
    protected GameRenderer renderer;
    protected GameModel model;

    public GameEngine(GameRenderer renderer, GameModel model){
        this.model = model;
        this.renderer = renderer;
    }


    public void getInputs(){}
    public void Update(){
        model.Update();
    }
    public void Render(){}


}
