package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class GonGon{
    int x, y, width, height, frameNum = 0;
    Bitmap gongon1, gongon2, gongon3, gongon4, gongon5, gongon6, gongon7;

    GonGon (int screenX, int screenY, Resources res){
        gongon1 = BitmapFactory.decodeResource(res, R.drawable.gon_run1);
        gongon2 = BitmapFactory.decodeResource(res, R.drawable.gon_run2);
        gongon3 = BitmapFactory.decodeResource(res, R.drawable.gon_run3);
        gongon4 = BitmapFactory.decodeResource(res, R.drawable.gon_run4);
        gongon5 = BitmapFactory.decodeResource(res, R.drawable.gon_run5);
        gongon6 = BitmapFactory.decodeResource(res, R.drawable.gon_run6);
        gongon7 = BitmapFactory.decodeResource(res, R.drawable.gon_run7);

        width = gongon1.getWidth()+35;
        height = gongon1.getWidth()+35;

        gongon1 = Bitmap.createScaledBitmap(gongon1, width, height, false);
        gongon2 = Bitmap.createScaledBitmap(gongon2, width, height, false);
        gongon3 = Bitmap.createScaledBitmap(gongon3, width, height, false);
        gongon4 = Bitmap.createScaledBitmap(gongon4, width, height, false);
        gongon5 = Bitmap.createScaledBitmap(gongon5, width, height, false);
        gongon6 = Bitmap.createScaledBitmap(gongon6, width, height, false);
        gongon7 = Bitmap.createScaledBitmap(gongon7, width, height, false);

        x = screenX/2 - gongon1.getWidth()/2;
        y = screenY - gongon1.getHeight()*2 - 100;
    }

    Bitmap getFrame (){
        int frameNow = (frameNum%7) + 1;
        frameNum++;
        if(frameNow==1){
            return gongon1;
        }else if(frameNow==2){
            return gongon2;
        }else if(frameNow==3){
            return gongon3;
        }else if(frameNow==4){
            return gongon4;
        }else if(frameNow==5){
            return gongon5;
        }else if (frameNow==6){
            return gongon6;
        }else {
            return gongon7;
        }
    }

    Rect getCollisionShape(){
        return new Rect(x,y,x + width,y + height);
    }
}
