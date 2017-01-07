package nivek.learnnumbersmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.view.View;

public class HexagonActivity extends AppCompatActivity {

    MediaPlayer nameHexagon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexagon);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        nameHexagon = MediaPlayer.create(this,R.raw.hexagon);
        nameHexagon.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_hexagon);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                nameHexagon.start();
            }

        });
    }
}
