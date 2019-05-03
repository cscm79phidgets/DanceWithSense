package com.example.myapplication;
import java.lang.Math;
public class Estimation {
    public static double percentage=0;
    public static void NormalDistribution(float mean,float stdev,float value){
        double result = 0;
        float a = -(value-mean)*(value-mean)/(2*stdev*stdev);
        result = ((1/(Math.sqrt(2*Math.PI)*stdev))*Math.exp(a));
        percentage = percentage+result;
    }
}
