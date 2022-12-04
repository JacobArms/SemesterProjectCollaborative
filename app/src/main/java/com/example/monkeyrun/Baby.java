package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Baby {
    int x, y, width, height, frameNum = 0;
    Bitmap baby1, baby2, baby3, baby4, baby5, baby6;

    Baby (int screenX, int screenY, Resources res){
        baby1 = BitmapFactory.decodeResource(res, R.drawable.baby_run1);
        baby2 = BitmapFactory.decodeResource(res, R.drawable.baby_run2);
        baby3 = BitmapFactory.decodeResource(res, R.drawable.baby_run3);
        baby4 = BitmapFactory.decodeResource(res, R.drawable.baby_run4);
        baby5 = BitmapFactory.decodeResource(res, R.drawable.baby_run5);
        baby6 = BitmapFactory.decodeResource(res, R.drawable.baby_run6);

        width = baby1.getWidth()+50;
        height = baby1.getWidth()+50;

        baby1 = Bitmap.createScaledBitmap(baby1, width, height, false);
        baby2 = Bitmap.createScaledBitmap(baby2, width, height, false);
        baby3 = Bitmap.createScaledBitmap(baby3, width, height, false);
        baby4 = Bitmap.createScaledBitmap(baby4, width, height, false);
        baby5 = Bitmap.createScaledBitmap(baby5, width, height, false);
        baby6 = Bitmap.createScaledBitmap(baby6, width, height, false);

        x = screenX/2 - baby1.getWidth()/2;
        y = screenY - baby1.getHeight()*2 - 100;
    }

    Bitmap getFrame (){
        int frameNow = (frameNum%6) + 1;
        frameNum++;
        if(frameNow==1){
            return baby1;
        }else if(frameNow==2){
            return baby2;
        }else if(frameNow==3){
            return baby3;
        }else if(frameNow==4){
            return baby4;
        }else if(frameNow==5){
            return baby5;
        }else{
            return baby6;
        }
    }

    Rect getCollisionShape(){
        return new Rect(x,y,x + width,y + height);
    }
}
