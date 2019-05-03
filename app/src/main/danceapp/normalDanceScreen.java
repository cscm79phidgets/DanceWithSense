package com.example.danceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import java.util.Random;

public class normalDanceScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_dance_screen);

        ImageView[] ivselection = new ImageView[5];

        final ImageView ImageView11 = findViewById(R.id.imageView11);
        ImageView11.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView12 = findViewById(R.id.imageView12);
        ImageView12.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView13 = findViewById(R.id.imageView13);
        ImageView13.setImageResource(R.drawable.ic_launcher_background);

        final ImageView ImageView14 = findViewById(R.id.imageView14);
        ImageView14.setImageResource(R.drawable.ic_launcher_background);

        Button button = (Button) findViewById(R.id.button8);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch(randomMove()){
                    case 0:
                        ImageView11.setImageResource(R.drawable.img_0);
                        break;
                    case 1:
                        ImageView11.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView11.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView11.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView11.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView12.setImageResource(R.drawable.img_0);
                        break;
                    case 1:
                        ImageView12.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView12.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView12.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView12.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView13.setImageResource(R.drawable.img_0);
                        break;
                    case 1:
                        ImageView13.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView13.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView13.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView13.setImageResource(R.drawable.img_4);
                        break;
                }
                switch(randomMove()){
                    case 0:
                        ImageView14.setImageResource(R.drawable.img_0);
                        break;
                    case 1:
                        ImageView14.setImageResource(R.drawable.img_1);
                        break;
                    case 2:
                        ImageView14.setImageResource(R.drawable.img_2);
                        break;
                    case 3:
                        ImageView14.setImageResource(R.drawable.img_3);
                        break;
                    case 4:
                        ImageView14.setImageResource(R.drawable.img_4);
                        break;
                }
            }
        });

    }
    protected int randomMove(){
        Random rand = new Random();
        int randomInt = (int) rand.nextInt(4);

        return randomInt;

    }

}

