package com.example.monkeyrun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable{
    //12:00 into video
    private Thread thread;
    private Boolean isPlaying;
    private int screenX, screenY;
    private Paint paint;
    private Background background1, background2;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX=screenX;
        this.screenY=screenY;

        background1= new Background(screenX, screenY, getResources());
        background2= new Background(screenX, screenY, getResources());
        background2.y = screenX;
        paint = new Paint();
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
        background1.y-= 10;
        background2.y-= 10;

        if(background1.y+ background1.background.getHeight() < 0){
            background1.y = screenY;
        }
    }
    //draws new position of drawables
    private void draw(){
        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

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


    public void resume() {

        isPlaying = true;
        thread = new Thread (this);
        thread.start();
    }


    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
