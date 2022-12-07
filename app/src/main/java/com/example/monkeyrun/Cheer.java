package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Cheer {
    int x = 0,y = 0;
    Bitmap cheer;

    public Cheer (int x, int y, Resources res, int drawable){
        cheer = BitmapFactory.decodeResource(res, drawable);
        cheer = Bitmap.createScaledBitmap(cheer, x, y, false);
    }

}
