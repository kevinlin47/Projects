package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;

public class TriangleActivity extends AppCompatActivity {

    MediaPlayer nameTriangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        nameTriangle = MediaPlayer.create(this,R.raw.triangle);
        nameTriangle.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_triangle);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                nameTriangle.start();
            }

        });

    }
}
