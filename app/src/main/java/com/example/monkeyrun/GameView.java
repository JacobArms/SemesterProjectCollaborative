package com.example.monkeyrun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.Timer;
import java.util.TimerTask;
import android.content.res.Resources;

public class GameView extends SurfaceView implements Runnable{
    private Thread thread;
    Timer timer;
    private Boolean isPlaying;
    private int screenX, screenY;
    private float screenRatioX, screenRatioY;
    private Paint paint;
    private Background background1, background2;
    private Obstacle ob1,ob2,ob3,ob4,ob5;
    private Aiai aiai;
    private Baby baby;
    private GonGon gonGon;
    private Character character;
    private boolean hitOnce1 = true,hitOnce2 = true,hitOnce3 = true,hitOnce4 = true,hitOnce5 = true;
    static boolean hitBarrel;
    private int diff = OpeningScreenActivity.diffNum;
    private int charPos = 3;
    private boolean right = false, left = false;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    private double objectOneX, objectTwoX, objectThreeX, objectFourX, objectFiveX;
    SurfaceView gameView = this;
    private int score = 0;
    int counter = 0;

    int x , y;
    float speed = diff * 10;
    double one = speed*(Math.random()+1);
    double two = speed*(Math.random()+1);
    double three = speed*(Math.random()+1);
    double four = speed*(Math.random()+1);
    double five = speed*(Math.random()+1);
//    Rect obstacleRect = new Rect((int)ob1.x,ob1.y,(int)(ob1.x+ob1.object.getWidth()),ob1.object.getHeight());
//    Rect monkeyRect = new Rect(aiai.x,aiai.y,aiai.x+aiai.aiai1.getWidth(),aiai.aiai1.getHeight());
//    Resource res;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX=screenX;
        this.screenY=screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f/ screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;
        background1= new Background(screenX, screenY, getResources());
        background2= new Background(screenX, screenY, getResources());
        background2.x = screenX;
        objectOneX = 0;
        objectTwoX = screenX/5;
        objectThreeX = screenX/5*2;
        objectFourX = screenX/5*3;
        objectFiveX = screenX/5*4;
        hitBarrel = false;
        ob1= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),1);
        ob2= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),2);
        ob3= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),3);
        ob4= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),4);
        ob5= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),5);



//        ob2.addPaddingLeftForBitmap(ob2.object, 500);
        //Draws the different characters for the different difficulties
            aiai = new Aiai(screenX, screenY, getResources());
            gonGon = new GonGon(screenX, screenY, getResources());
            baby = new Baby(screenX, screenY, getResources());


            aiai = new Aiai(screenX, screenY, getResources());
            gonGon = new GonGon(screenX, screenY, getResources());
            baby = new Baby(screenX, screenY, getResources());


        background2.y = screenX;
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.BLACK);
    }

    public GameView(Context context) {
        super(context);
    }
    
    
    @Override
    //While the game is playing is true this will run to update what is happening while a user is playing
    public void run() {
        while(isPlaying){
            update();
            draw();
            try{
                moveObject();
            }catch(Exception e){

            }
            sleep();
        }
    }

    //update position of drawables
    private void update(){

        background1.y += screenRatioY;
        background2.y += screenRatioY;
        ob1.y += one;
        ob2.y += two;
        ob3.y += three;
        ob4.y += four;
        ob5.y += five;

        if(ob1.getY() >= screenY) {
            Log.println(Log.ASSERT, "ARMS", "OBJECT CHANGED");
            ob1= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),1);
            ob1.setY(0-ob3.height);
            hitOnce1 = true;
        }
        if(ob2.getY() >= screenY) {
            Log.println(Log.ASSERT, "ARMS", "OBJECT CHANGED");
            ob2= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),2);
            ob2.setY(0-ob3.height);
            hitOnce2 = true;
        }
        if(ob3.getY() >= screenY) {
            Log.println(Log.ASSERT, "ARMS", "OBJECT CHANGED");
            ob3= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),3);
            ob3.setY(0-ob3.height);
            hitOnce3 = true;
        }
        if(ob4.getY() >= screenY) {
            Log.println(Log.ASSERT, "ARMS", "OBJECT CHANGED");
            ob4= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),4);
            ob4.setY(0-ob3.height);
            hitOnce4 = true;
        }
        if(ob5.getY() >= screenY) {
            Log.println(Log.ASSERT, "ARMS", "OBJECT CHANGED");
            ob5= new Obstacle(screenX, screenY, getResources(), (int)Math.floor(Math.random()*3+1),5);
            ob5.setY(0-ob3.height);
            hitOnce5 = true;
        }

        if(background1.y >= screenY){
            background1.y = -screenY;
        }
        if(background2.y >= screenY){
            background2.y = background2.y -screenY*2 ;
        }
//        int type = (int)Math.floor(Math.random()*3+1);
//
//        double width = ob1.getX();
//        int height=ob1.getY();
//        Rect getCollisionShape(){
//            return new Rect(x,y,(int)(x+width),y+height);
//        }


            //Creates a new object for the obstacle


//        if(ob1.y <= screenY){
//
//        }

        if(ob1.y>=aiai.y&&hitOnce1){
            if(charPos==ob1.getObstaclePos()){
                if (ob1.getType()==3) {
                    Log.println(Log.ASSERT, "hits", "banana collected 1");
                    score+=10*diff;
                    hitOnce1 = false;
                }else{
                    Log.println(Log.ASSERT, "hits", "hit 1");
//                    ((Activity) getContext()).finish();
                    hitBarrel = true;

//                    gameView.setVisibility(gameView.GONE);
                }
            }
        }
        if(ob2.y>=aiai.y&&hitOnce2){
            if(charPos==ob2.getObstaclePos()){
                if (ob2.getType()==3) {
                    Log.println(Log.ASSERT, "hits", "banana collected 2");
                    score+=10*diff;
                    hitOnce2 = false;
                }else{
                    Log.println(Log.ASSERT, "hits", "hit 2");
                    hitBarrel = true;
                }
            }
        }
        if(ob3.y>=aiai.y&&hitOnce3){
            if(charPos==ob3.getObstaclePos()){
                if (ob3.getType()==3) {
                    Log.println(Log.ASSERT, "hits", "banana collected 3");
                    score+=10*diff;
                    hitOnce3 = false;
                }else{
                    Log.println(Log.ASSERT, "hits", "hit 3");
                    hitBarrel = true;
                }
            }
        }
        if(ob4.y>=aiai.y&&hitOnce4){
            if(charPos==ob4.getObstaclePos()){
                if (ob4.getType()==3) {
                    Log.println(Log.ASSERT, "hits", "banana collected 4");
                    score+=10*diff;
                    hitOnce4 = false;
                }else{
                    Log.println(Log.ASSERT, "hits", "hit 4");
                    hitBarrel = true;
                }
            }
        }
        if(ob5.y>=aiai.y&&hitOnce5){
            if(charPos==ob5.getObstaclePos()){
                if (ob5.getType()==3) {
                    Log.println(Log.ASSERT, "hits", "banana collected 5");
                    score+=10*diff;
                    hitOnce5 = false;
                }else{
                    Log.println(Log.ASSERT, "hits", "hit 5");
                    hitBarrel = true;
                }
            }
        }


    }
    //draws new position of drawables
    private void draw(){
        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
            canvas.drawText(score + "",  screenX / 2f, 164, paint);
//            canvas.drawBitmap(ob1.object, (float) objectOneX, ob1.y, paint);
            canvas.drawBitmap(ob1.object, (float) objectOneX, ob1.y, paint);
            canvas.drawBitmap(ob2.object,(float) objectTwoX, ob2.y, paint);
            canvas.drawBitmap(ob3.object, (float) objectThreeX, ob3.y, paint);
            canvas.drawBitmap(ob4.object, (float) objectFourX, ob4.y, paint);
            canvas.drawBitmap(ob5.object, (float) objectFiveX, ob5.y, paint);
            if (diff==2) {
                canvas.drawBitmap(aiai.getFrame(), aiai.x, aiai.y, paint);
            } else if(diff==3){
                canvas.drawBitmap(gonGon.getFrame(), gonGon.x, gonGon.y, paint);
            } else{
                canvas.drawBitmap(baby.getFrame(), baby.x, baby.y, paint);
            }



//            if(ob1.getY() >= screenY){
//                int type = (int)Math.floor(Math.random()*3+1);
//
//                if (type == 1) {
//                    ob1.setObject(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.banana));
////                    ob1.object.setWidth(200);
////                    getResizedBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.banana), (int) objectOneX, ob1.y, 100, 100);
////                    ob1.setX(screenX - ob1.object.getWidth() - 2000);
//
//                }else if(type == 2){
//                    ob1.setObject(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.normal_barrel));
////                    ob1.object.setWidth(200);
////                    ob1.setX(screenX - ob1.object.getWidth() - 1000);
//                }else if(type == 3){
//                    ob1.setObject(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sideways_barrel));
////                    ob1.object.setWidth(200);
////                    ob1.setX(screenX - ob1.object.getWidth() - 1000);
//                }
//            }


//            if(ob1.getY() >= screenY){
//                    ob1.setType(2);
////                if(type == 1){
////                    ob1.setObject(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.banana));
////                }else if(type == 2){
////                    ob1.setObject(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sideways_barrel));
////                }else if(type == 3){
////                    ob1.setObject(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.normal_barrel));
////                }
//            }



            getHolder().unlockCanvasAndPost(canvas);


        }

    }
    //waiting code (60fps)
    private void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //this allows the code to resume it function in case it gets paused
    public void resume() {
        isPlaying = true;
        thread = new Thread (this);
        thread.start();
    }

    //this pauses the game loop if needed
    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getX() < screenX/2 ){
                    Log.println(Log.ASSERT, "SAUER", "Left click");
                    if (charPos!=1) {
                        aiai.x -= screenX / 5;
                        gonGon.x -= screenX / 5;
                        baby.x -= screenX / 5;
                        charPos--;
                    }
                }else {
                    Log.println(Log.ASSERT, "SAUER", "right click");
                    if (charPos!=5) {
                        aiai.x += screenX / 5;
                        gonGon.x += screenX / 5;
                        baby.x += screenX / 5;
                        charPos++;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void moveObject(){
        if(ob1.y >= screenY){
            ob1.setType((int)Math.floor(Math.random()*3+1));
//            ob1.setObject(null);
//            ob1 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob1.setY(0-ob3.height);
            hitOnce1 = true;
        }
        if(ob2.y >= screenY){
            ob2.setType((int)Math.floor(Math.random()*3+1));
//            ob2.setObject(null);
//            ob2 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob2.setY(0-ob3.height);
            hitOnce2 = true;
        }
        if(ob3.y >= screenY){
            ob3.setType((int)Math.floor(Math.random()*3+1));
//            ob3.setObject(null);
//            ob3 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob3.setY(0-ob3.height);
            hitOnce3 = true;
        }
        if(ob4.y >= screenY){
            ob4.setType((int)Math.floor(Math.random()*3+1));
//            ob4.setObject(null);
//            ob4 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob4.setY(0-ob3.height);
            hitOnce4 = true;
        }
        if(ob5.y >= screenY){
            ob5.setType((int)Math.floor(Math.random()*3+1));
//            ob5.setObject(null);
//            ob5 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob5.setY(0-ob3.height);
            hitOnce5 = true;
        }
    }

//    public Bitmap getResizedBitmap(Bitmap bm, int x, int y, int newHeight, int newWidth){
//        int height = ob1.object.getHeight();
//        int width = ob1.object.getWidth();
//
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//
//        Bitmap resizedBitmap = Bitmap.createBitmap(bm, x, y, width, height, matrix, false);
//
//        return resizedBitmap;
//    }


    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Obstacle getOb1() {
        return ob1;
    }

    public void setOb1(Obstacle ob1) {
        this.ob1 = ob1;
    }

    public Obstacle getOb2() {
        return ob2;
    }

    public void setOb2(Obstacle ob2) {
        this.ob2 = ob2;
    }

    public Obstacle getOb3() {
        return ob3;
    }

    public void setOb3(Obstacle ob3) {
        this.ob3 = ob3;
    }

    public Obstacle getOb4() {
        return ob4;
    }

    public void setOb4(Obstacle ob4) {
        this.ob4 = ob4;
    }

    public Obstacle getOb5() {
        return ob5;
    }

    public void setOb5(Obstacle ob5) {
        this.ob5 = ob5;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isHitOnce1() {
        return hitOnce1;
    }

    public void setHitOnce1(boolean hitOnce1) {
        this.hitOnce1 = hitOnce1;
    }

    public boolean isHitOnce2() {
        return hitOnce2;
    }

    public void setHitOnce2(boolean hitOnce2) {
        this.hitOnce2 = hitOnce2;
    }

    public boolean isHitOnce3() {
        return hitOnce3;
    }

    public void setHitOnce3(boolean hitOnce3) {
        this.hitOnce3 = hitOnce3;
    }

    public boolean isHitOnce4() {
        return hitOnce4;
    }

    public void setHitOnce4(boolean hitOnce4) {
        this.hitOnce4 = hitOnce4;
    }

    public boolean isHitOnce5() {
        return hitOnce5;
    }

    public void setHitOnce5(boolean hitOnce5) {
        this.hitOnce5 = hitOnce5;
    }

    public static boolean isHitBarrel() {
        return hitBarrel;
    }

    public static void setHitBarrel(boolean hitBarrel) {
        GameView.hitBarrel = hitBarrel;
    }
}

