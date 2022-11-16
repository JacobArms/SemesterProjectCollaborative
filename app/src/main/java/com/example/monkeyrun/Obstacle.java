package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class Obstacle {
    int x = 0,y = 0;
    Bitmap object;
    int type;

    public Obstacle (int type, int screenX, int screenY, Resources res ){
        this.type = type;
        if (type == 1) {
            object = BitmapFactory.decodeResource(res, R.drawable.normal_barrel);
        }else if(type == 2){
            object = BitmapFactory.decodeResource(res, R.drawable.sideways_barrel);
        }else if(type == 3){
            object = BitmapFactory.decodeResource(res, R.drawable.banana);
        }
        object = Bitmap.createScaledBitmap(object, screenX/5, screenX/5, false);
        x = screenX/2 - object.getWidth()/2;
    }
//    public Bitmap addPaddingLeftForBitmap(Bitmap bitmap, int paddingLeft) {
//        Bitmap outputBitmap = Bitmap.createBitmap(bitmap.getWidth() + paddingLeft, bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(outputBitmap);
//        canvas.drawBitmap(bitmap, paddingLeft, 0, null);
//        return outputBitmap;
//    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getObject() {
        return object;
    }

    public void setObject(Bitmap object) {
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
