package com.example.monkeyrun;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

public class Obstacle {
    int x = 0;
    int y = 0, width, height;
    Bitmap object;
    private int type;
    int obstaclePos;



    public Obstacle (int screenX, int screenY, Resources res, int type, int obstaclePos){
        //The (int)Math.floor(Math.random()*6), decides whether or not the object is a banana, barrel, or sideways barrel
        this.obstaclePos=obstaclePos;
        Log.println(Log.ASSERT, "OBSTACLE", "" + obstaclePos);



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

    Rect getCollisionShape(){
        return new Rect(x,y,x + width,y + height);
    }

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

    public void setObstaclePos(int obstaclePos) {
        this.obstaclePos = obstaclePos;
    }

    public int getObstaclePos() {
        return obstaclePos;
    }
}
