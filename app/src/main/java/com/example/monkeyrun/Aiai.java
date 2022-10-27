package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ResourceBundle;

public class Aiai {

    int x, y, width, height, frameNum = 0;
    Bitmap aiai1, aiai2, aiai3, aiai4, aiai5, aiai6;

    Aiai (int screenX, Resources res){
        aiai1 = BitmapFactory.decodeResource(res, R.drawable.aia_f_one);
        aiai2 = BitmapFactory.decodeResource(res, R.drawable.aia_f_two);
        aiai3 = BitmapFactory.decodeResource(res, R.drawable.aia_f_three);
        aiai4 = BitmapFactory.decodeResource(res, R.drawable.aia_f_four);
        aiai5 = BitmapFactory.decodeResource(res, R.drawable.aia_f_five);
        aiai6 = BitmapFactory.decodeResource(res, R.drawable.aia_f_six);

        width = aiai1.getWidth();
        height = aiai1.getWidth();

        aiai1 = Bitmap.createScaledBitmap(aiai1, width, height, false);
        aiai2 = Bitmap.createScaledBitmap(aiai2, width, height, false);
        aiai3 = Bitmap.createScaledBitmap(aiai3, width, height, false);
        aiai4 = Bitmap.createScaledBitmap(aiai4, width, height, false);
        aiai5 = Bitmap.createScaledBitmap(aiai5, width, height, false);
        aiai6 = Bitmap.createScaledBitmap(aiai6, width, height, false);

        x = screenX/2;
    }

    Bitmap getFrame (){
        int frameNow = (frameNum%6) + 1;
        frameNum++;
        if(frameNow==1){
            return aiai1;
        }else if(frameNow==2){
            return aiai2;
        }else if(frameNow==3){
            return aiai3;
        }else if(frameNow==4){
            return aiai4;
        }else if(frameNow==5){
            return aiai5;
        }else{
            return aiai6;
        }
    }

}
