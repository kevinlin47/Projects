package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.media.MediaPlayer;

public class CowActivity extends AppCompatActivity {

    MediaPlayer cowSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        cowSound=MediaPlayer.create(this,R.raw.moo);
        cowSound.setVolume(100,100);
        cowSound.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_cow);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                cowSound.start();
            }

        });
    }
}
