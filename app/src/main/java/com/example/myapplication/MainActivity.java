package com.example.myapplication;

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
    private Button button;
    private int reclen = 10000;
    private int duration = 1000;
    private String action;
    private SensorManager sm;
    private Sensor ac,gp;
    private Database database = new Database();
    private int tableNumber = 0;
    private Context context;
    private float ac1,ac2,ac3,gp1,gp2,gp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.tv);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        ac = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gp = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        context = MainActivity.this;
        database.myDBOpenHelper = new MyDBOpenHelper(context,"database.db",null,1);
        database.sqLiteDatabase = database.myDBOpenHelper.getWritableDatabase();
        /**
        Click button to start
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reclen = 0;
                while(database.CheckTable("data"+tableNumber)){
                    ++tableNumber;
                }
                database.CreateTable("data"+tableNumber);
                handler.postDelayed(runnable,1);
            }
        });
        /**
         * Create accelerator sensor
         */
        SensorEventListener acSensorListener = new SensorEventListener(){
            public void onSensorChanged(SensorEvent event) {
                ac1 = event.values[0];
                ac2 = event.values[1];
                ac3 = event.values[2];
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        /**
         *  Create gyroscope sensor listener
         */
        SensorEventListener gpSensorListener = new SensorEventListener(){
            public void onSensorChanged(SensorEvent event) {
                gp1 = event.values[0];
                gp2 = event.values[1];
                gp3 = event.values[2];
                tv.setText("ac1"+ac1+"ac2"+ac2+"ac3"+ac3+"gp1"+gp1+"gp2"+gp2+"gp3"+gp3+"");
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sm.registerListener(acSensorListener,ac,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(gpSensorListener,gp,SensorManager.SENSOR_DELAY_NORMAL);
//        tv.setText(gp.toString());
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(reclen<duration){
                ContentValues contentValues = new ContentValues();
                contentValues.put("a",ac1);
                contentValues.put("b",ac2);
                contentValues.put("c",ac3);
                contentValues.put("d",gp1);
                contentValues.put("e",gp2);
                contentValues.put("f",gp3);
                database.InsertData("data"+tableNumber,contentValues);
            }
//            for(int i=0;i<duration;++i){
//                float[] mean = database.Find(action+"mean",i);
//                float[] stdev = database.Find(action+"stdev",i);
//                float[] value = database.Find("data"+tableNumber,i);
//                Estimation.NormalDistribution(mean[0],stdev[0],value[0]);
//                Estimation.NormalDistribution(mean[1],stdev[1],value[1]);
//                Estimation.NormalDistribution(mean[2],stdev[2],value[2]);
//                Estimation.NormalDistribution(mean[3],stdev[3],value[3]);
//                Estimation.NormalDistribution(mean[4],stdev[4],value[4]);
//                Estimation.NormalDistribution(mean[5],stdev[5],value[5]);
//                tv.setText(""+Estimation.percentage/6000);
//            }
            reclen++;
            handler.postDelayed(this,1);
        }
    };

}
