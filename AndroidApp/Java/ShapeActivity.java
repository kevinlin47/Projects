package nivek.learnnumbersmore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShapeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        Button toTriangle = (Button) findViewById(R.id.triangleButton);
        toTriangle.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intentTriangle = new Intent(ShapeActivity.this, TriangleActivity.class);
                startActivity(intentTriangle);
            }
        });

        Button toSquare = (Button) findViewById(R.id.squareButton);
        toSquare.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intentSquare = new Intent(ShapeActivity.this, SquareActivity.class);
                startActivity(intentSquare);
            }
        });

        Button toRectangle = (Button) findViewById(R.id.rectangleButton);
        toRectangle.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intentRectangle = new Intent(ShapeActivity.this, RectangleActivity.class);
                startActivity(intentRectangle);
            }
        });

        Button toCircle = (Button) findViewById(R.id.circleButton);
        toCircle.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intentCircle = new Intent(ShapeActivity.this, CircleActivity.class);
                startActivity(intentCircle);
            }
        });

        Button toHexagon = (Button) findViewById(R.id.hexagonButton);
        toHexagon.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intentHexagon = new Intent(ShapeActivity.this, HexagonActivity.class);
                startActivity(intentHexagon);
            }
        });

        Button toPentagon = (Button) findViewById(R.id.pentagonButton);
        toPentagon.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intentPentagon = new Intent(ShapeActivity.this, PentagonActivity.class);
                startActivity(intentPentagon);
            }
        });

    }


}
