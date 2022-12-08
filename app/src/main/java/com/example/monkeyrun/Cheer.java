package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Cheer {
    int x = 0,y = 0;
    Bitmap cheer;
    public Cheer (int x, int y, Resources res, int drawable){
        float screenRatioX = 1920f / x;
        float screenRatioY = 1080f/ y;
        int width = (int)(975/screenRatioX);
        int height =(int)(449/screenRatioY);
        cheer = BitmapFactory.decodeResource(res, drawable);
        cheer = Bitmap.createScaledBitmap(cheer, width, height, false);
    }

}
