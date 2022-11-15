package com.example.monkeyrun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;

public class GameView extends SurfaceView implements Runnable{
    //12:00 into video

    private Thread thread;
    private Boolean isPlaying;
    private int screenX, screenY;
    private float screenRatioX, screenRatioY;
    private Paint paint;
    private Background background1, background2;
    private Obstacle ob1,ob2,ob3,ob4;
    private Aiai aiai;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    int randNum1 = screenX/(int)Math.floor(Math.random()*5+1);


    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX=screenX;
        this.screenY=screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f/ screenY;
        background1= new Background(screenX, screenY, getResources());
        background2= new Background(screenX, screenY, getResources());

        //The (int)Math.floor(Math.random()*6), decides whether or not the object is a banana, barrel, or sideways barrel
        ob1= new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
        ob2= new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
        ob3= new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
        ob4= new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());

        aiai = new Aiai(screenX, screenY, getResources());
        background2.y = screenX;
        paint = new Paint();
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
            sleep();
        }
    }

    //update position of drawables
    private void update(){
        float speed = 25;
        background1.y += speed;
        background2.y += speed;
        ob1.y += speed;
        ob2.y += speed;
        ob3.y += speed;
        ob4.y += speed;


        if(background1.y >= screenY){
            background1.y = -screenY;
        }
        if(background2.y-3250 >= screenY){
            background2.y = background2.y -screenY*2 ;
        }
//        if(ob1.y <= screenY){
//            ob1.y = ob1.y + screenY ;
//        }

    }
    //draws new position of drawables
    private void draw(){
        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y-3250, paint);
            canvas.drawBitmap(ob1.object, randNum1, ob1.y-1500, paint);
            canvas.drawBitmap(ob2.object, randNum1, ob1.y-300, paint);
            canvas.drawBitmap(ob3.object, randNum1, ob1.y-300, paint);
            canvas.drawBitmap(ob4.object, randNum1, ob1.y-300, paint);

            canvas.drawBitmap(aiai.getFrame(), aiai.x, aiai.y, paint);
            getHolder().unlockCanvasAndPost(canvas);

            if (ob1.y <= -screenY) {
                //Creates a new object for the obstacle
                ob1.setType((int)Math.floor(Math.random()*3+1));
                ob1.setY(screenY);

            }
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


    GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if(e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 100) {
                    //left
                    aiai.x-=screenX/5;
                    Log.println(Log.ASSERT, "SAUER", "LEFT");
                } else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 100) {
                    //right
                    aiai.x+=screenX/5;
                    Log.println(Log.ASSERT, "SAUER", "RIGHT");
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    };

}

