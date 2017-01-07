package nivek.learnnumbersmore;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Button;
import android.view.View;


public class SelectionActivity extends AppCompatActivity {

    ImageView grassImage;
    MediaPlayer mySound;
    public static boolean shouldPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);






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

        Button toNumber = (Button) findViewById(R.id.numbButton);
        toNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent0 = new Intent(SelectionActivity.this, NumberActivity.class);
                shouldPlay=true;
                mySound.stop();
                startActivity(intent0);
            }
        });

        Button toShapes = (Button) findViewById(R.id.shapeButton);
        toShapes.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent1 = new Intent(SelectionActivity.this, ShapeActivity.class);
                shouldPlay=true;
                mySound.stop();
                startActivity(intent1);
            }
        });

        Button toAnimal = (Button) findViewById(R.id.aniButton);
        toAnimal.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent2 = new Intent(SelectionActivity.this, AnimalActivity.class);
                shouldPlay = true;
                mySound.stop();
                startActivity(intent2);
            }

        });
    }

}