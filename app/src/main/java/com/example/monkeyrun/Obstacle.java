package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Obstacle {
    int x = 0,y = 0;
    Bitmap object;

    public Obstacle (int type, int screenX, int screenY, Resources res ){
        if (type == 1) {
            object = BitmapFactory.decodeResource(res, R.drawable.normal_barrel);
        }else if(type == 2){
            object = BitmapFactory.decodeResource(res, R.drawable.sideways_barrel);
        }else if(type == 3){
            object = BitmapFactory.decodeResource(res, R.drawable.banana);
        }
        object = Bitmap.createScaledBitmap(object, screenX/(int)Math.floor(Math.random()*5+1), screenY/10, false);
    }
}
