package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import acmeindustries.boondoggletd.model.Notification;

/**
 * Created by Eric on 11/19/2016.
 */

public class NotificationRenderer  implements Renderer{
    private Notification notification;

    public NotificationRenderer(Notification n){
        this.notification = n;
    }

    public void render(Canvas c){
        // stops errors from writing on null canvas
        if( c==null || !notification.isActive()) return;

        Paint paint = new Paint();
        paint.setTextSize(c.getHeight()/20);

        paint.setColor(Color.rgb(240,240,240));
        c.drawRect(5, 5, c.getWidth() - 10,c.getHeight()/5 - 10, paint);
        paint.setColor(Color.BLACK);
        c.drawText(notification.getText(), 10, c.getHeight()/10, paint);
        paint.setTextSize(c.getHeight()/30);
        c.drawText("Tap to close.", 10, (c.getHeight()/10)+(c.getHeight()/20), paint);

        /*
        paint.setStrokeWidth(5);
        c.drawLine(0,0,0,0, paint);
        */
    }

}
