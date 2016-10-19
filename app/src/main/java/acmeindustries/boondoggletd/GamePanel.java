package acmeindustries.boondoggletd;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * this class connects the game loop to the android built in primitives like gestures, touch events, surfaces (views) etc.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private GameLoop gameLoop;
    private Thread gameThread;

    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        gameLoop = new GameLoop(getHolder(), this, 1/24f);
        gameThread = new Thread(gameLoop);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameLoop.stop();
        gameThread.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameLoop.touchEvent(event);
        return super.onTouchEvent(event);
    }

}
