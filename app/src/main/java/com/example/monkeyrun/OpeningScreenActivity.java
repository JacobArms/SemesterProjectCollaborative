package com.example.monkeyrun;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.Timer;
import java.util.TimerTask;

public class OpeningScreenActivity extends AppCompatActivity {

//    public void imageClick(View view){
//        //Grabs the image of AiAi
//        ImageView imageView = (ImageView) findViewById(R.id.AiAi);
//
//        //Sets the xFrom, xTo, yFrom, and yTo
//        TranslateAnimation animation = new TranslateAnimation(0.0f,0.0f, 0.0f, -1481.0f);
//        animation.setDuration(1000);    //animation duration, impacts the speed of the animation as well
//        animation.setRepeatCount(0);    //Sets the repeat count
//        animation.setRepeatMode(0);     //Repeat animation from (left to right, right to left)
//        imageView.startAnimation(animation);    //Starts the animation of the image on the page
//        while(imageView.getY()<=-1481) {
//            imageView.setY(-1480);
//        }
//    }
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        final ImageView AiAi = findViewById(R.id.AiAi);
        final TextView title = findViewById(R.id.Title);
        final TextView Highscore = findViewById(R.id.Highscore);
        final ImageView BlackCircle = findViewById(R.id.BlackCircle);
        final TextView PlayButton = findViewById(R.id.playButton);
        
        
        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AiAi.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                title.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                Highscore.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                BlackCircle.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));
                PlayButton.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));


            }
        });
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(OpeningScreenActivity.this, InGameActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
        
        
        
        
    }

}