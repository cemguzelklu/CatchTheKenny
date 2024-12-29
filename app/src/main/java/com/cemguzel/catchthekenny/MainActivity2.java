package com.cemguzel.catchthekenny;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {


    TextView timeText;
    TextView scoreText;
    TextView textView3;
    int score;
    int delay;
    ImageView imageView0;
    ImageView  imageView1;
    ImageView  imageView2;
    ImageView  imageView3;
    ImageView  imageView4;
    ImageView  imageView5;
    ImageView  imageView6;
    ImageView  imageView7;
    ImageView  imageView8;
    ImageView  imageView9;
    ImageView  imageView10;
    ImageView  imageView11;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.levelText), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        delay = getIntent().getIntExtra("newDelay", 1500);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        score=0;
        textView3=findViewById(R.id.textView3);
        imageView0=findViewById(R.id.imageView0);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);

        imageArray=new ImageView[] {imageView0,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11};
        textView3.setText("LEVEL2");
        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                timeText.setText("Game Over");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                if(score>30){
                    AlertDialog.Builder alert2=new AlertDialog.Builder(MainActivity2.this);
                    alert2.setTitle("LEVEL2 TAMAMLANDI");
                    alert2.setMessage("Level 3'e geç");
                    alert2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            delay -= 200;
                            if (delay < 300) {
                                delay = 300;
                            }
                            Log.d("Intent", "MainActivity3'e geçiş yapılıyor...");
                            Toast.makeText(MainActivity2.this, "MainActivity3'e geçiş yapılıyor...", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                            intent.putExtra("newDelay", delay);
                            startActivity(intent);

                        }
                    });alert2.show();
                }else {
                    AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity2.this);
                    alert.setTitle("Game Over");
                    alert.setMessage("Try Again?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity2.this,"Game Over",Toast.LENGTH_LONG).show();
                        }
                    });
                    alert.show();
                }

            }
        }.start();



    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score: "+score);

    }
    public void hideImages(){
        handler=new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(12);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,delay);
            }

        };
        handler.post(runnable);


    }

}