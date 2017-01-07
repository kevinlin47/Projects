package nivek.learnnumbersmore;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.media.MediaPlayer;

public class DogActivity extends AppCompatActivity {

    MediaPlayer dogSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
    }

    @Override
    public void onResume()
    {
        super.onResume();



        dogSound=MediaPlayer.create(this,R.raw.woof);
        dogSound.setVolume(500,500);
        dogSound.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_dog);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dogSound.start();
            }

        });
    }
}
