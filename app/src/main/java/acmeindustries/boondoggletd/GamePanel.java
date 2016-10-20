package acmeindustries.boondoggletd;

import android.content.Context;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.FloatMath;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * this class connects the game loop to the android built in primitives like gestures, touch events, surfaces (views) etc.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    static final int ZOOM = 1;
    static final int NONE = 2;
    int mode = 0;

    private GameLoop gameLoop;
    private Thread gameThread;
    GestureDetector gestureDetector;
    Drawable background;
    Bitmap bitmap;
    Matrix matrix;
    Matrix savedMatrix;

    float currentScale;
    float maxZoom;
    float minZoom;
    float changeScale;
    float xStartScale;
    float yStartScale;
    float zoomedScale;
    boolean zoomed = false;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameLoop = new GameLoop(getHolder(), this, 1 / 24f); //THIS NEEDS TO BE A FLOAT!
        gameThread = new Thread(gameLoop);
        gestureDetector = new GestureDetector(context, new Gesture());
        matrix = new Matrix();
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
    protected void onDraw(Canvas canvas) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameLoop.touchEvent(event);
        gestureDetector.onTouchEvent(event);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case (MotionEvent.ACTION_POINTER_DOWN):
                savedMatrix.set(matrix);
                mode = ZOOM;
                break;
            case (MotionEvent.ACTION_UP):
            case (MotionEvent.ACTION_POINTER_UP):
                mode = NONE;
                break;
            case (MotionEvent.ACTION_MOVE):
                savedMatrix.set(matrix);
                if (mode == ZOOM) {

                    matrix.set(savedMatrix);
                    if (zoomed) {
                        matrix.postScale(0, 0, 0, 0);
                        zoomed = false;
                    } else if (!zoomed) {
                        matrix.postScale(100, 100, 100, 100);
                        zoomed = true;
                    }
                }

                break;
        }
        return super.onTouchEvent(event);
    }

    class Gesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            changeScale = 1;
            xStartScale = event.getX();
            yStartScale = event.getY();

            if (currentScale - maxZoom > 0) {
                zoomedScale = maxZoom;
            } else {
                zoomedScale = minZoom;
            }
            return true;
        }
    }
}
