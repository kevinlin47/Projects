package nivek.learnnumbersmore;



import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class NumberActivity extends AppCompatActivity {

    MediaPlayer oneSound;
    MediaPlayer twoSound;
    MediaPlayer threeSound;
    MediaPlayer fourSound;
    MediaPlayer fiveSound;
    MediaPlayer sixSound;
    MediaPlayer sevenSound;
    MediaPlayer eightSound;
    MediaPlayer nineSound;
    MediaPlayer tenSound;

    ImageView numberOne;
    ImageView numberTwo;
    ImageView numberThree;
    ImageView numberFour;
    ImageView numberFive;
    ImageView numberSix;
    ImageView numberSeven;
    ImageView numberEight;
    ImageView numberNine;
    ImageView numberTen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        oneSound = MediaPlayer.create(this, R.raw.one);
        twoSound = MediaPlayer.create(this, R.raw.two);
        threeSound = MediaPlayer.create(this, R.raw.three);
        fourSound = MediaPlayer.create(this, R.raw.four);
        fiveSound = MediaPlayer.create(this, R.raw.five);
        sixSound = MediaPlayer.create(this, R.raw.six);
        sevenSound = MediaPlayer.create(this, R.raw.seven);
        eightSound = MediaPlayer.create(this, R.raw.eight);
        nineSound = MediaPlayer.create(this, R.raw.nine);
        tenSound = MediaPlayer.create(this, R.raw.ten);








        Button forOne=(Button) findViewById(R.id.oneButton);
        forOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            oneSound.start();

            numberOne=(ImageView) findViewById(R.id.imageOne);
            numberOne.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    numberOne.setVisibility(View.INVISIBLE);

                    }
                }, 5000);

            }
        });

        Button forTwo=(Button) findViewById(R.id.twoButton);
        forTwo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                twoSound.start();

                numberTwo=(ImageView) findViewById(R.id.imageTwo);
                numberTwo.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberTwo.setVisibility(View.INVISIBLE);

                    }
                }, 5000);

            }
        });

        Button forThree=(Button) findViewById(R.id.threeButton);
        forThree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                threeSound.start();

                numberThree=(ImageView) findViewById(R.id.imageThree);
                numberThree.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberThree.setVisibility(View.INVISIBLE);

                    }
                }, 5000);
            }
        });

        Button forFour=(Button) findViewById(R.id.fourButton);
        forFour.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fourSound.start();

                numberFour=(ImageView) findViewById(R.id.imageFour);
                numberFour.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberFour.setVisibility(View.INVISIBLE);

                    }
                }, 5000);


            }
        });

        Button forFive=(Button) findViewById(R.id.fiveButton);
        forFive.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fiveSound.start();

                numberFive=(ImageView) findViewById(R.id.imageFive);
                numberFive.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberFive.setVisibility(View.INVISIBLE);

                    }
                }, 5000);
            }
        });

        Button forSix=(Button) findViewById(R.id.sixButton);
        forSix.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sixSound.start();

                numberSix=(ImageView) findViewById(R.id.imageSix);
                numberSix.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberSix.setVisibility(View.INVISIBLE);

                    }
                }, 5000);
            }
        });

        Button forSeven=(Button) findViewById(R.id.sevenButton);
        forSeven.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sevenSound.start();

                numberSeven=(ImageView) findViewById(R.id.imageSeven);
                numberSeven.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberSeven.setVisibility(View.INVISIBLE);

                    }
                }, 5000);
            }
        });

        Button forEight=(Button) findViewById(R.id.eightButton);
        forEight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                eightSound.start();

                numberEight=(ImageView) findViewById(R.id.imageEight);
                numberEight.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberEight.setVisibility(View.INVISIBLE);

                    }
                }, 5000);

            }
        });

        Button forNine=(Button) findViewById(R.id.nineButton);
        forNine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nineSound.start();

                numberNine=(ImageView) findViewById(R.id.imageNine);
                numberNine.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberNine.setVisibility(View.INVISIBLE);

                    }
                }, 5000);


            }
        });

        Button forTen=(Button) findViewById(R.id.tenButton);
        forTen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tenSound.start();

                numberTen=(ImageView) findViewById(R.id.imageTen);
                numberTen.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        numberTen.setVisibility(View.INVISIBLE);

                    }
                }, 5000);
            }
        });

    }



}
