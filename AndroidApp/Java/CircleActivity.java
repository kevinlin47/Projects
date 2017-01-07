package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;


public class CircleActivity extends AppCompatActivity {

    MediaPlayer nameCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        nameCircle = MediaPlayer.create(this,R.raw.circle);
        nameCircle.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_circle);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                nameCircle.start();
            }

        });
    }
}
