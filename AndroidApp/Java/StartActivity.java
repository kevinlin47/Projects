package nivek.learnnumbersmore;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.media.MediaPlayer;

public class StartActivity extends AppCompatActivity {

    ImageView grassImage;
    MediaPlayer mySound;
    public static boolean shouldPlay=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }

    @Override
    public void onPause() {

        if(this.isFinishing()){  //BACK was pressed from this activity
            mySound.stop();
        }

        super.onPause(); }

    @Override
    public void onStop() {
        super.onStop();
        shouldPlay=false;
        if (!shouldPlay) {
            mySound.pause();
            mySound = null;
        }
    }


    @Override
    public void onResume()
    {
        super.onResume();
        mySound = MediaPlayer.create(this, R.raw.happymusic);
        mySound.setLooping(true);
        mySound.setVolume(100, 100);
        mySound.start();

        grassImage=(ImageView) findViewById(R.id.imageView);
        ((AnimationDrawable) grassImage.getDrawable()).start();


        Button toSelection = (Button) findViewById(R.id.button);
        toSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, SelectionActivity.class);
                shouldPlay=true;
                mySound.stop();
                startActivity(intent);
            }
        });
    }




}

