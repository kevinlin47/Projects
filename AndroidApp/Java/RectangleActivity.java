package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;

public class RectangleActivity extends AppCompatActivity {

    MediaPlayer nameRectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        nameRectangle = MediaPlayer.create(this,R.raw.rectangle);
        nameRectangle.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_rectangle);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                nameRectangle.start();
            }

        });
    }
}
