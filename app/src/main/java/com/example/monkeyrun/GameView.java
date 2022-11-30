package com.example.monkeyrun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;

import java.util.Timer;
import java.util.TimerTask;

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
    private boolean right = false, left = false;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    private double objectOneX, objectTwoX, objectThreeX, objectFourX, objectFiveX;
    SurfaceView gameView = this;
    private int score;
    int counter = 0;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX=screenX;
        this.screenY=screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f/ screenY;
        background1= new Background(screenX, screenY, getResources());
        background2= new Background(screenX, screenY, getResources());

        ob1= new Obstacle(screenX, screenY, getResources());
        ob2= new Obstacle(screenX, screenY, getResources());
        ob3= new Obstacle(screenX, screenY, getResources());
        ob4= new Obstacle(screenX, screenY, getResources());
        ob5= new Obstacle(screenX, screenY, getResources());

        objectOneX = screenX - ob1.object.getWidth();
        objectTwoX = screenX * 0.8 - ob2.object.getWidth();
        objectThreeX = screenX * 0.6 - ob3.object.getWidth();
        objectFourX = screenX * 0.4 - ob4.object.getWidth();
        objectFiveX = screenX * 0.2 - ob5.object.getWidth();

//        ob2.addPaddingLeftForBitmap(ob2.object, 500);

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
            try{
                moveObject();
            }catch(Exception e){

            }
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
        ob5.y += speed;

        if (left)

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

            if (counter==0){
                    canvas.drawBitmap(ob1.object, (float) objectOneX, ob1.y, paint);
                    Log.println(Log.ASSERT, "ARMS", "OBJECT DRAWN");
                    counter++;

            }else if (ob1.y>screenY){
                counter=0;
            }
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y-3250, paint);
            canvas.drawBitmap(ob1.object, (float) objectOneX, ob1.y, paint);
            canvas.drawBitmap(ob2.object,(float) objectTwoX, ob2.y-500, paint);
            canvas.drawBitmap(ob3.object, (float) objectThreeX, ob3.y-1000, paint);
            canvas.drawBitmap(ob4.object, (float) objectFourX, ob4.y-1500, paint);
            canvas.drawBitmap(ob5.object, (float) objectFiveX, ob5.y-2000, paint);

            canvas.drawBitmap(aiai.getFrame(), aiai.x, aiai.y, paint);
            getHolder().unlockCanvasAndPost(canvas);

//            if (ob1.y <= -screenY) {
//                //Creates a new object for the obstacle
//                ob1.setType((int)Math.floor(Math.random()*3+1));
//                ob1.setY(screenY);
//            }
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

    public void moveObject(){
        if(ob1.y >= screenY){
            ob1.setType((int)Math.floor(Math.random()*3+1));
//            ob1.setObject(null);
//            ob1 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob1.setY(-500);
        }
        if(ob2.y-500 >= screenY){
            ob2.setType((int)Math.floor(Math.random()*3+1));
//            ob2.setObject(null);
//            ob2 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob2.setY(-500);
        }
        if(ob3.y-1000 >= screenY){
            ob3.setType((int)Math.floor(Math.random()*3+1));
//            ob3.setObject(null);
//            ob3 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob3.setY(-500);
        }
        if(ob4.y-1500 >= screenY){
            ob4.setType((int)Math.floor(Math.random()*3+1));
//            ob4.setObject(null);
//            ob4 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob4.setY(-500);
        }
        if(ob5.y-2000 >= screenY){
            ob5.setType((int)Math.floor(Math.random()*3+1));
//            ob5.setObject(null);
//            ob5 = new Obstacle((int)Math.floor(Math.random()*3+1),screenX, screenY, getResources());
            ob5.setY(-500);
        }
    }


//class swipeDetector extends GestureDetector.SimpleOnGestureListener {
//    public boolean onSwipe(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        try {
//            Log.println(Log.ASSERT, "ARMS", "POPUP CLOSED");
//            // right to left swipe
//            if (e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 100) {
//                //left
//            } else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 100) {
//                //right
//                right = true;
//            }
//        } catch (Exception e) {
//            // nothing
//        }
//        return true;
//    }
//}

//@Override
//public boolean dispatchKeyEvent(KeyEvent KEvent)
//{
//    int keyaction = KEvent.getAction();
//
//    if(keyaction == KeyEvent.ACTION_DOWN)
//    {
//        int keycode = KEvent.getKeyCode();
//        int keyunicode = KEvent.getUnicodeChar(KEvent.getMetaState() );
//        char character = (char) keyunicode;
//        Log.println(Log.ASSERT, "ARMS", "DEBUG MESSAGE KEY=" + character + " KEYCODE=" +  keycode + "");
//    }
//
//
//    return super.dispatchKeyEvent(KEvent);
//}
@Override
public boolean dispatchKeyEvent(KeyEvent event) {
    Log.i("key pressed", String.valueOf(event.getKeyCode()));
    return super.dispatchKeyEvent(event);
}

// If Swipe doesnt work, we can implement the arrow kets to stipe the monkey left and right
//    public void keyTyped(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.) {
//        }
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
}

