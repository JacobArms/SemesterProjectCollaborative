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
        cheer = BitmapFactory.decodeResource(res, drawable);
        cheer = Bitmap.createScaledBitmap(cheer, 600, 800, false);
    }

}
