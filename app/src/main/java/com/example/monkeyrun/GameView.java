package com.example.monkeyrun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintSet;

import com.google.androidgamesdk.GameActivity;

public class GameView extends SurfaceView implements Runnable{
    private final Object swipeListener;
    //12:00 into video

    private Thread thread;
    private Boolean isPlaying;
    private int screenX, screenY;
    private float screenRatioX, screenRatioY;
    private Paint paint;
    private Background background1, background2;
    private Aiai aiai;
    GestureDetector gestureDetector;


    public GameView(Context context, int screenX, int screenY) {
        super(context);
        SurfaceView surfaceView = this;
        gestureDetector = new GestureDetector(this, swipeListener);
        surfaceView.setOnTouchListener(touchListener);
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
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    };

    class swipeListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent event) {
            Log.d("SAUER","HELD DOWN");

            // don't return false here or else none of the other
            // gestures will work
            return true;
        }
    }
}

