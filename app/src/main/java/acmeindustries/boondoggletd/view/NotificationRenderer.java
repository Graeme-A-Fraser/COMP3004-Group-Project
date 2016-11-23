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
        Paint paint2 = new Paint();
        paint.setAntiAlias(true);
        paint2.setAntiAlias(true);
        paint.setTextSize(c.getHeight()/20);

        paint2.setColor(Color.rgb(240,240,240));
        paint2.setShadowLayer(5f,0f,2f,Color.rgb(0,0,0));
        c.drawRect(20, c.getHeight()/2 - c.getHeight()/7, c.getWidth() - 20,c.getHeight()/2 + c.getHeight()/7, paint2);
        paint.setColor(Color.BLACK);
        c.drawText(notification.getText(), 50, c.getHeight()/2, paint);
        paint.setTextSize(c.getHeight()/30);
        c.drawText("Tap to close.", 50, (c.getHeight()/2)+(c.getHeight()/20), paint);

        /*
        paint.setStrokeWidth(5);
        c.drawLine(0,0,0,0, paint);
        */
    }

}
