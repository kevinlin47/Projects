package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;

public class PentagonActivity extends AppCompatActivity {

    MediaPlayer namePentagon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pentagon);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        namePentagon = MediaPlayer.create(this,R.raw.pentagon);
        namePentagon.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_pentagon);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                namePentagon.start();
            }

        });
    }
}
