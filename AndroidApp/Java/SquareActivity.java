package nivek.learnnumbersmore;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;

public class SquareActivity extends AppCompatActivity {

    MediaPlayer nameSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        nameSquare = MediaPlayer.create(this,R.raw.square);
        nameSquare.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_square);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                nameSquare.start();
            }

        });


    }
}
