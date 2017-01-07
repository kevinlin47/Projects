package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.media.MediaPlayer;

public class CatActivity extends AppCompatActivity {

    MediaPlayer catSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        catSound=MediaPlayer.create(this,R.raw.meow);
        catSound.setVolume(100,100);
        catSound.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_cat);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                catSound.start();
            }

        });
    }
}
