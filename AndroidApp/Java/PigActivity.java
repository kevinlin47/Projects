package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.media.MediaPlayer;

public class PigActivity extends AppCompatActivity {

    MediaPlayer pigSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        pigSound=MediaPlayer.create(this,R.raw.oink);
        pigSound.setVolume(100,100);
        pigSound.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_pig);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pigSound.start();
            }

        });
    }
}
