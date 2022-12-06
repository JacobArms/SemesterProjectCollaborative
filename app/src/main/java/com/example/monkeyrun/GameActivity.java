package com.example.monkeyrun;

import static com.example.monkeyrun.GameView.hitBarrel;
import static com.example.monkeyrun.GameView.isHitBarrel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.GameManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class GameActivity extends AppCompatActivity {

    //    private final Bundle bundle = getIntent().getExtras();
//    int diff = bundle.getInt("difficulty");
    private GameView gameView;
    private boolean hitBarrel = GameView.isHitBarrel();
    private Aiai aiai;
    private Context context;
    private GameManager gameManager;
    private GestureDetector.SimpleOnGestureListener gestureListener;
    private Obstacle ob1, ob2, ob3, ob4, ob5;
    private int score;
    private OpeningScreenActivity openingScreenActivity;
//    private static boolean hitBarrel = false;
//    private ImageView monkeyAiai;
//jacob messed up

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);




//        if(hitBarrel){
//            Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
//            startActivity(intent);

//        }
//        Rect obstacleRect = new Rect((int)ob1.x,ob1.y,(int)(ob1.x+ob1.object.getWidth()),ob1.object.getHeight());
//        Rect monkeyRect = new Rect(aiai.x,aiai.y,aiai.x+aiai.aiai1.getWidth(),aiai.aiai1.getHeight());
//
//        if(obstacleRect.intersect(monkeyRect) && ob1.getType() == 1){
//            Log.println(Log.ASSERT, "ARMS", "COLLIDED");
//            Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
//            startActivity(intent);
//        }

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
//        monkeyAiai.setOnTouchListener(new OnSwipeTouchListener(GameActivity.this) {
//            public void onSwipeTop() {
//                Toast.makeText(GameActivity.this, "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                Toast.makeText(GameActivity.this, "right", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                Toast.makeText(GameActivity.this, "left", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(GameActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//            }
//
//        });


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
    //    public void onSwipe(View view){
//        view.setOnClickListener(new SwipeListener(context));
//    }
}