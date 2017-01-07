package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.media.MediaPlayer;

public class HorseActivity extends AppCompatActivity {

    MediaPlayer horseSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horse);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        horseSound=MediaPlayer.create(this,R.raw.horse_nay);
        horseSound.setVolume(100,100);
        horseSound.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_horse);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                horseSound.start();
            }

        });
    }
}
