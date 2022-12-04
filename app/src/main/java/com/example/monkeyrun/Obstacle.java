package com.example.monkeyrun;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class Obstacle {
    double x = 0;
    int y = 0, width, height;
    Bitmap object;
    private int type;

    public Obstacle (double screenX, int screenY, Resources res ){
//        The (int)Math.floor(Math.random()*6), decides whether or not the object is a banana, barrel, or sideways barrel
        type = (int)Math.floor(Math.random()*3+1);
        if (type == 1){
    public Obstacle (double screenX, int screenY, Resources res, int type ){
        //The (int)Math.floor(Math.random()*6), decides whether or not the object is a banana, barrel, or sideways barrel


        if (type == 1) {
            object = BitmapFactory.decodeResource(res, R.drawable.normal_barrel);
        }else if(type == 2){
            object = BitmapFactory.decodeResource(res, R.drawable.sideways_barrel);
        }else if(type == 3){
            object = BitmapFactory.decodeResource(res, R.drawable.banana);
        }

        object = Bitmap.createScaledBitmap(object, (int)screenX/5, (int)screenX/5, false);
        x = screenX/2 - object.getWidth()/2;

        width = object.getWidth();
        height = object.getHeight();
    }

//    public Bitmap addPaddingLeftForBitmap(Bitmap bitmap, int paddingLeft) {
//        Bitmap outputBitmap = Bitmap.createBitmap(bitmap.getWidth() + paddingLeft, bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(outputBitmap);
//        canvas.drawBitmap(bitmap, paddingLeft, 0, null);
//        return outputBitmap;
//    }

    public double getX() {
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
