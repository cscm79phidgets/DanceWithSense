package com.example.danceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import android.content.ContentValues;

public class MainActivity extends AppCompatActivity {
	private TextView tv;
    private Button record;
    private Button stop;
    private Button cancel;
    private String action;
    public static SensorManager sm;
    public static Sensor ac,gp;
    private static Database database = new Database();
    private Operation operation = new Operation();
    private int tableNumber = 0;
    private Context context;
    private float ac1,ac2,ac3,gp1,gp2,gp3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        tv = findViewById(R.id.tv);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        ac = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gp = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        context = MainActivity.this;
        database.myDBOpenHelper = new MyDBOpenHelper(context,"database.db",null,1);
        database.sqLiteDatabase = database.myDBOpenHelper.getWritableDatabase();
        operation.initializeClass();

        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);
        cancel = findViewById(R.id.cancel);

		      record.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operation.recordMovement();
            }
        }));
        stop.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operation.stopRecording();
            }
        }));
        cancel.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operation.cancelRecording();
            }
        }));
	}	
	    public static SensorManager getSm(){
        return sm;
    }
    public static Sensor getAc(){
        return ac;
    }
    public static Sensor getGp(){
        return gp;
    }
    public static Database getDatabase(){
        return database;
    }
	
    public void changeToEasy(View view)
    {
        Intent intent = new Intent(MainActivity.this, EasyDanceScreen.class);
        startActivity(intent);
    }

    public void changeToNormal(View view)
    {
        Intent intent = new Intent(MainActivity.this, normalDanceScreen.class);
        startActivity(intent);
    }

    public void changeToHard(View view)
    {
        Intent intent = new Intent(MainActivity.this, hardDanceScreen.class);
        startActivity(intent);
    }

    public void changeToCustom(View view)
    {
        Intent intent = new Intent(MainActivity.this, customDanceScreen.class);
        startActivity(intent);
    }

    public void changeToLeaderboard(View view)
    {
        Intent intent = new Intent(MainActivity.this, leaderboard.class);
        startActivity(intent);
    }
}