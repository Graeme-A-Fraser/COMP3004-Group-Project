package acmeindustries.boondoggletd.controller;

import android.graphics.Canvas;

import acmeindustries.boondoggletd.model.Notification;
import acmeindustries.boondoggletd.view.NotificationRenderer;


public class NotificationController {

    private Notification notification;
    private NotificationRenderer notificationRenderer;

    public NotificationController(Notification n){
        this.notification = n;
        this.notificationRenderer = new NotificationRenderer(notification);
    }

    public void press(float x, float y){
        notification.setStatus(false);
    }

    public void render(Canvas c){
        notificationRenderer.render(c);
    }

}
