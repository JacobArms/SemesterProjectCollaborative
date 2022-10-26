package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class OpeningScreenActivity extends AppCompatActivity {

    public void imageClick(View view){
        //Grabs the image of AiAi
        ImageView imageView = (ImageView) findViewById(R.id.AiAi);
        //Sets the xFrom, xTo, yFrom, and yTo
        TranslateAnimation animation = new TranslateAnimation(-160.0f,160.0f, 0.0f, 0.0f);
        animation.setDuration(800);    //animation duration, impacts the speed of the animation as well
        animation.setRepeatCount(5);    //Sets the repeat count
        animation.setRepeatMode(2);     //Repeat animation from (left to right, right to left)
        imageView.startAnimation(animation);    //Starts the animation of the image on the page
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
    }

}