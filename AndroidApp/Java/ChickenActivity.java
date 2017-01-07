package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.media.MediaPlayer;

public class ChickenActivity extends AppCompatActivity {

    MediaPlayer chickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        chickSound=MediaPlayer.create(this,R.raw.chickensound);
        chickSound.setVolume(100,100);
        chickSound.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_chicken);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                chickSound.start();
            }

        });
    }
}
