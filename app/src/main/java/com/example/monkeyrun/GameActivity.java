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