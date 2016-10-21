package acmeindustries.boondoggletd.view;

import android.graphics.Canvas;

/**
 * a view for purchasing units to attack the enemy
 */

public class RecruitRenderer implements Renderer {
    public void render(Canvas c) {
        // if this isn't here sometimes tries to write to a non-existent canvas throwing a null error
        if(c == null){
            return;
        }

    }
}
