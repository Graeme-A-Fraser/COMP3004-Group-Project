package acmeindustries.boondoggletd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
This is just a shell to launch the 'activity'
 */

public class GameLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GamePanel(this));
    }
}
