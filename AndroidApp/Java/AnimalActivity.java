package nivek.learnnumbersmore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        Button toDog = (Button) findViewById(R.id.dogButton);
        Button toCat = (Button) findViewById(R.id.catButton);
        Button toCow = (Button) findViewById(R.id.cowButton);
        Button toHorse = (Button) findViewById(R.id.horseButton);
        Button toPig = (Button) findViewById(R.id.pigButton);
        Button toChick = (Button) findViewById(R.id.chickenButton);

        toDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dogIntent = new Intent(AnimalActivity.this,DogActivity.class);
                startActivity(dogIntent);
            }
        });

        toCat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent catIntent = new Intent(AnimalActivity.this,CatActivity.class);
                startActivity(catIntent);
            }
        });

        toCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cowIntent = new Intent(AnimalActivity.this,CowActivity.class);
                startActivity(cowIntent);
            }
        });

        toHorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent horseIntent = new Intent(AnimalActivity.this,HorseActivity.class);
                startActivity(horseIntent);
            }
        });

        toPig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pigIntent = new Intent(AnimalActivity.this,PigActivity.class);
                startActivity(pigIntent);
            }
        });

        toChick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chickenIntent = new Intent(AnimalActivity.this,ChickenActivity.class);
                startActivity(chickenIntent);
            }
        });
    }
}
