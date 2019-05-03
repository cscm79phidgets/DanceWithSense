package com.example.danceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.os.Build;
import android.content.Context;

import java.util.Random;

public class EasyDanceScreen extends AppCompatActivity {
int move0;
int move1;
int move2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_dance_screen);

        final ImageView ImageView1 = findViewById(R.id.imageView1);
        ImageView1.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView2 = findViewById(R.id.imageView2);
        ImageView2.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView3 = findViewById(R.id.imageView3);
        ImageView3.setImageResource(R.drawable.ic_launcher_background);

        Button button = (Button) findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch(randomMove()){
                    case 0:
                        ImageView1.setImageResource(R.drawable.img_0);
                        move0 = 0;
                        break;
                    case 1:
                        ImageView1.setImageResource(R.drawable.img_1);
                        move0 = 1;
                        break;
                    case 2:
                        ImageView1.setImageResource(R.drawable.img_2);
                        move0 = 2;
                        break;
                    case 3:
                        ImageView1.setImageResource(R.drawable.img_3);
                        move0 = 3;
                        break;
                    case 4:
                        ImageView1.setImageResource(R.drawable.img_4);
                        move0 = 4;
                        break;
                 }
                switch(randomMove()){
                    case 0:
                        ImageView2.setImageResource(R.drawable.img_0);
                        move1 = 0;
                        break;
                    case 1:
                        ImageView2.setImageResource(R.drawable.img_1);
                        move1 = 1;
                        break;
                    case 2:
                        ImageView2.setImageResource(R.drawable.img_2);
                        move1 = 2;
                        break;
                    case 3:
                        ImageView2.setImageResource(R.drawable.img_3);
                        move1 = 3;
                        break;
                    case 4:
                        ImageView2.setImageResource(R.drawable.img_4);
                        move1 = 4;
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView3.setImageResource(R.drawable.img_0);
                        move2 = 0;
                        break;
                    case 1:
                        ImageView3.setImageResource(R.drawable.img_1);
                        move2 = 1;
                        break;
                    case 2:
                        ImageView3.setImageResource(R.drawable.img_2);
                        move2 = 2;
                        break;
                    case 3:
                        ImageView3.setImageResource(R.drawable.img_3);
                        move2 = 3;
                    case 4:
                        ImageView3.setImageResource(R.drawable.img_4);
                        move2 = 4;
                        break;
                }
        }
         });


        final Button playButton = findViewById(R.id.buttonPlayEasy);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Viber("on", getApplicationContext());
            }

        });
    }


    protected int randomMove(){
        Random rand = new Random();
        int randomInt = (int) rand.nextInt(4);
        return randomInt;
    }

    public void Viber(String value, Context context) {
        if (value.equals("on")) {
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(300);
        }
    }

    public void changeToFinish(View view)
    {
        Intent intent = new Intent(EasyDanceScreen.this, finish.class);
        int Score = 3;
        intent.putExtra("Score", Score);
        startActivity(intent);
    }

    public void changeToError(View view)
    {
        Intent intent = new Intent(EasyDanceScreen.this, Error.class);
        startActivity(intent);
    }
}

