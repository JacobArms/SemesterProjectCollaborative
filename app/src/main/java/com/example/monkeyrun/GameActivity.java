package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.GameManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private Aiai aiai;
    private Context context;
    private GameManager gameManager;
    private GestureDetector.SimpleOnGestureListener gestureListener;
    private Obstacle ob1,ob2,ob3,ob4,ob5;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);

//        if(aiai.y - aiai.height == ob3.y){
//            if(aiai.x == ob3.x){
//                if(ob3.getType() == 3){
//                    score += 10;
//                }else if(ob3.getType() == 1 || ob3.getType() == 2){
//                    Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
//                    startActivity(intent);
//                }
//            }
//        }

        //Grabs the image of Dr Heinz
        ImageView imageView = (ImageView) findViewById(R.id.drHeinz);
        //Sets the xFrom, xTo, yFrom, and yTo
        TranslateAnimation animation = new TranslateAnimation(-160.0f,160.0f, 0.0f, 0.0f);
        animation.setDuration(800);    //animation duration, impacts the speed of the animation as well
        animation.setRepeatCount(5);    //Sets the repeat count
        animation.setRepeatMode(2);     //Repeat animation from (left to right, right to left)
        imageView.startAnimation(animation);    //Starts the animation of the image on the page
    }

    @SuppressLint("ClickableViewAccessibility")
    public void surfaceCreated(SurfaceHolder holder) {
        SurfaceHolder surfaceHolder = holder;
        gameView.setOnTouchListener(new SwipeListener(context) {
            @Override
            public void onSwipeLeft() {
                Log.println(Log.ASSERT, "SAUER", "SWIPE LEFT");
            }
            public void onSwipeRight() {
                Log.println(Log.ASSERT, "SAUER", "SWIPE RIGHT");
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    public void hitObstacle(){
        Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
        startActivity(intent);
    }
//    public void onSwipe(View view){
//        view.setOnClickListener(new SwipeListener(context));
//    }
}