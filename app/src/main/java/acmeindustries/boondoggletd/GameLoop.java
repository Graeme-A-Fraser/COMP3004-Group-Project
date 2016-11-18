package acmeindustries.boondoggletd;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import acmeindustries.boondoggletd.controller.GameEngine;

/**
 * this class contains the main loop of the game, which we pass off to the 'engine' to handle at a higher level
 */

public class GameLoop implements Runnable{

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private GameEngine engine;
    private boolean running;
    private double delta;
    public static Canvas canvas;

    public GameLoop(SurfaceHolder sh, GamePanel gh, double updateRate){
        super();
        this.surfaceHolder = sh;
        this.gamePanel = gh;
        this.delta = updateRate;
    }

    // the main game loop -- taken from http://entropyinteractive.com/2011/02/game-engine-design-the-game-loop/
    public void run() {
        startup();
        double nextTime = (double)System.nanoTime() / 1000000000.0;
        double maxTimeDiff = 0.5;
        int skippedFrames = 1;
        int maxSkippedFrames = 5;
        while(running)
        {
            double currTime = (double)System.nanoTime() / 1000000000.0;
            if((currTime - nextTime) > maxTimeDiff) nextTime = currTime;
            if(currTime >= nextTime)
            {
                // assign the time for the next update
                nextTime += delta;
                // TODO: actually input a proper delta value...
                update();
                if((currTime < nextTime) || (skippedFrames > maxSkippedFrames))
                {
                    draw();
                    skippedFrames = 1;
                }
                else
                {
                    skippedFrames++;
                }
            }
            else
            {
                // calculate the time to sleep
                int sleepTime = (int)(1000.0 * (nextTime - currTime));
                // sanity check
                if(sleepTime > 0)
                {
                    // sleep until the next update
                    try
                    {
                        Thread.sleep(sleepTime);
                    }
                    catch(InterruptedException e)
                    {
                        // do nothing
                    }
                }
            }
        }
        shutdown();
    }

    public void stop(){
        running = false;
    }

    public void startup(){
        this.engine = new GameEngine(gamePanel.getWidth(), gamePanel.getHeight());
        running = true;
    };
    public void shutdown(){};
    public void update(){
        //
        engine.update();
    };
    public void draw(){
        try {
            canvas = surfaceHolder.lockCanvas();
            // draw stuff here
            engine.render(canvas);
        } finally {
            if(canvas!=null && surfaceHolder != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    };

    public void touchEvent(MotionEvent event) {
        int action = event.getAction();
        switch(action){
            // for now just press and release, can look into other gestures here https://developer.android.com/training/gestures/detector.html
            case(MotionEvent.ACTION_DOWN):
                engine.press(event.getX(), event.getY());
                break;
            case(MotionEvent.ACTION_UP):
                engine.release(event.getX(), event.getY());
                break;
        }
    }

}
