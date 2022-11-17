package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private Aiai aiai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);
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