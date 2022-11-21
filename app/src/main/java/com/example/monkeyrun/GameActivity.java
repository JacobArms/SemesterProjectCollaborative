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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);
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