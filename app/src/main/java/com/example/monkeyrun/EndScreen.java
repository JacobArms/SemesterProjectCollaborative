package com.example.monkeyrun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class EndScreen {
    int x = 0,y = 0;
    Bitmap endscreen;

    public EndScreen (int screenX, int screenY, Resources res ){
        endscreen = BitmapFactory.decodeResource(res, R.drawable.endscreenbackground);
        endscreen = Bitmap.createScaledBitmap(endscreen, screenX, screenY, false);
    }
}
