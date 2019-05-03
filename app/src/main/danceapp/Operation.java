package com.example.danceapp;

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

public class Operation {
    public  SensorManager sm;
    public  Sensor ac;
    public  Sensor gp;
    public  float ac1,ac2,ac3,gp1,gp2,gp3;
    private Database database;
    private int format=100;
    private boolean timer=false;
    private int tableNumber = 0;
    public void recordMovement(){
        timer = true;
        while(database.checkTable("temp"+tableNumber)){
            ++tableNumber;
        }
        database.createTable("temp"+tableNumber);
//        while(timer<1000000000){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("a",ac1);
//            contentValues.put("b",ac2);
//            contentValues.put("c",ac3);
//            contentValues.put("d",gp1);
//            contentValues.put("e",gp2);
//            contentValues.put("f",gp3);
//            database.insertData("temp"+tableNumber,contentValues);
//            try {
//                Thread.sleep(1000);
//                ++timer;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            handler.postDelayed(runnable,500);
//        }
    }
    public void stopRecording(){
        timer = false;
        database.createTable("final"+tableNumber);
        int count = database.countColumn("temp"+tableNumber);
        if(count<format){
            for(int i=1;i<format;++i){
                float[] value = new float[6];
                value = database.findValue("temp"+tableNumber,i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("a",value[0]);
                contentValues.put("b",value[1]);
                contentValues.put("c",value[2]);
                contentValues.put("d",value[3]);
                contentValues.put("e",value[4]);
                contentValues.put("f",value[5]);
                database.insertData("final"+tableNumber,contentValues);
            }
        }else{
            for(int i=1;i<format;++i){
                float[] value = new float[6];
                database.findValue("temp"+tableNumber,i*format/count);
                ContentValues contentValues = new ContentValues();
                contentValues.put("a",value[0]);
                contentValues.put("b",value[1]);
                contentValues.put("c",value[2]);
                contentValues.put("d",value[3]);
                contentValues.put("e",value[4]);
                contentValues.put("f",value[5]);
                database.insertData("final"+tableNumber,contentValues);
            }
        }
    }
    public void cancelRecording(){
        timer = false;
    }
    public double estimateMovement(String movement){
        for(int i=1;i<format;++i){
                    float[] mean = database.findValue(movement+"A",i);
                    float[] stdev = database.findValue(movement+"S",i);
                    float[] value = database.findValue("final"+tableNumber,i);
                    Estimation.NormalDistribution(mean[0],stdev[0],value[0]);
                    Estimation.NormalDistribution(mean[1],stdev[1],value[1]);
                    Estimation.NormalDistribution(mean[2],stdev[2],value[2]);
                    Estimation.NormalDistribution(mean[3],stdev[3],value[3]);
                    Estimation.NormalDistribution(mean[4],stdev[4],value[4]);
                    Estimation.NormalDistribution(mean[5],stdev[5],value[5]);
                }
        double result = Estimation.getPercentage()/600;
        Estimation.setPercentage(0);
        return result;
    }
    public void initializeClass(){
        sm = MainActivity.getSm();
        ac = MainActivity.getAc();
        gp = MainActivity.getGp();
        database = MainActivity.getDatabase();
        SensorEventListener acSensorListener = new SensorEventListener(){
            public void onSensorChanged(SensorEvent event) {
                ac1 = event.values[0];
                ac2 = event.values[1];
                ac3 = event.values[2];
                if(timer) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("a",ac1);
                    contentValues.put("b",ac2);
                    contentValues.put("c",ac3);
                    contentValues.put("d",gp1);
                    contentValues.put("e",gp2);
                    contentValues.put("f",gp3);
                    database.insertData("temp"+tableNumber,contentValues);
                }
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
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sm.registerListener(acSensorListener,ac,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(gpSensorListener,gp,SensorManager.SENSOR_DELAY_NORMAL);
    }
//    Handler handler = new Handler();
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            handler.postDelayed(this,500);
//            ++timer;
//        }
//    };

}
