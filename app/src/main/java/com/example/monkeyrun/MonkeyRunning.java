package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MonkeyRunning extends AppCompatActivity {
    private Aiai aiai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey_running);
        //Grabs the image of Dr Heinz
        ImageView grass1 = (ImageView) findViewById(R.id.grass1);
        //Sets the xFrom, xTo, yFrom, and yTo
        TranslateAnimation grass1ani = new TranslateAnimation(0f,0f, -100f, 2000);
        grass1ani.setDuration(1800);    //animation duration, impacts the speed of the animation as well
        grass1ani.setRepeatCount(1000000);    //Sets the repeat count
//        animation.setRepeatMode();     //Repeat animation from (left to right, right to left)
        grass1.startAnimation(grass1ani);    //Starts the animation of the image on the page


        //Grabs the image of Dr Heinz
        ImageView grass2 = (ImageView) findViewById(R.id.grass2);
        //Sets the xFrom, xTo, yFrom, and yTo
        TranslateAnimation grass2ani = new TranslateAnimation(0f,0f, -2000f, 2000);
        grass2ani.setDuration(2000);    //animation duration, impacts the speed of the animation as well
        grass2ani.setRepeatCount(1000000);    //Sets the repeat count
//        animation.setRepeatMode();     //Repeat animation from (left to right, right to left)
        grass2.startAnimation(grass2ani);    //Starts the animation of the image on the page
    }
}