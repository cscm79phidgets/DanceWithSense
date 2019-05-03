package com.example.danceapp;
import java.lang.Math;
public class Estimation {
    public static double percentage=0;
    public static double result = 0;
    public static float a=0;
    public static void NormalDistribution(float mean,float stdev,float value){
        a = -((value-mean)*(value-mean))/(2*stdev*stdev);
        result = ((1/(Math.sqrt(2*Math.PI)*stdev))*Math.exp(a));
        percentage = percentage+result;
    }
    public static double getPercentage(){
        return percentage;
    }
    public static void setPercentage(double a){
        percentage = a;
    }
    public static double getResult(){
        return  result;
    }
    public static float getA(){
        return a;
    }
}
