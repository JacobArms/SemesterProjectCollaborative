package com.example.monkeyrun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintSet;

import com.google.androidgamesdk.GameActivity;

public class GameView extends SurfaceView implements Runnable{
    //12:00 into video

    private Thread thread;
    private Boolean isPlaying;
    private int screenX, screenY;
    private float screenRatioX, screenRatioY;
    private Paint paint;
    private Background background1, background2;
    private Aiai aiai;
    GestureDetector gestureDetector;
//    private MotionEvent MotionEvent;
//    MotionEvent motionEvent;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        SurfaceView surfaceView = this;
        SwipeListener swipeListener = new SwipeListener(surfaceView, aiai, screenX);
        this.screenX=screenX;
        this.screenY=screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f/ screenY;
        background1= new Background(screenX, screenY, getResources());
        background2= new Background(screenX, screenY, getResources());
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

        if(background1.y >= screenY){
            background1.y = -screenY;
        }
        if(background2.y-3250 >= screenY){
            background2.y = background2.y -screenY*2 ;
        }
    }
    //draws new position of drawables
    private void draw(){
        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y-3250, paint);

            canvas.drawBitmap(aiai.getFrame(), aiai.x, aiai.y, paint);
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

//    public void swipeListener(View view, Aiai aiai, int screenX){
//        int threshold = 100;
//        int velocityThreshhold = 100;
//
//        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
//
//            public boolean onDown(MotionEvent e) {
//                return true;
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                float xDiff = e2.getX() - e1.getX();
//                float yDiff = e2.getY() - e1.getY();
//                try {
//                    if (Math.abs(xDiff) > Math.abs(yDiff)) {
//                        if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocityThreshhold) {
//                            if (xDiff > 0) {
//                                //right
//                                aiai.x += screenX/5;
//
//                            } else {
//                                //left
//                                aiai.x -= screenX/5;
//                            }
//                            return true;
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return false;
//            }
//        };
//        gestureDetector = new GestureDetector(listener);
////        public boolean onTouch(View view, MotionEvent){
////            return gestureDetector.onTouchEvent(MotionEvent);
////        }
//    }

}

