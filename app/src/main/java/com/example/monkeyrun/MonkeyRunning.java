package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MonkeyRunning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey_running);

        //Grabs the image of Dr Heinz
        ImageView imageView = (ImageView) findViewById(R.id.grass1);
        //Sets the xFrom, xTo, yFrom, and yTo
        TranslateAnimation animation = new TranslateAnimation(0f,0f, -160.0f, 160.0f);
        animation.setDuration(800);    //animation duration, impacts the speed of the animation as well
        animation.setRepeatCount(1000000);    //Sets the repeat count
//        animation.setRepeatMode();     //Repeat animation from (left to right, right to left)
        imageView.startAnimation(animation);    //Starts the animation of the image on the page
    }
}