package com.example.monkeyrun;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
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
    ListPopupWindow listPopupWindow;

    final ImageView AiAi = findViewById(R.id.AiAi);
    final TextView title = findViewById(R.id.Title);
    final TextView Highscore = findViewById(R.id.Highscore);
    final ImageView BlackCircle = findViewById(R.id.BlackCircle);
    final TextView PlayButton = findViewById(R.id.playButton);
    final ImageView settingsButton = findViewById(R.id.settingsButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);




        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Moves AiAi up the screen to disappear before the game starts
                AiAi.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                //Moves the title up the screen to disappear before the game starts
                title.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                //Moves the high score up the screen to disappear before the game starts
                Highscore.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                //Moves the black circle up the screen to disappear before the game starts
                BlackCircle.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                //Moves the play button up the screen to disappear before the game starts
                PlayButton.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                //Moves the settings button up the screen to disappear before the game starts
                settingsButton.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(OpeningScreenActivity.this, GameActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 800);

            }
        });

//        setUpListpopupWindow();
//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                listPopupWindow.show();
//            }
//        });



        //below code will switch to the game screen but needs proper timer so it doesnt overlap animation
//        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view) {
//                startActivity(new Intent(OpeningScreenActivity.this, GameActivity.class));
//            }
//        });
    }
//    public void setUpListpopupWindow() {
//        listPopupWindow = new ListPopupWindow(OpeningScreenActivity.this);
//        listPopupWindow.setAnchorView(settingsButton);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        listPopupWindow.setWidth(metrics.widthPixels); //sets width as per the screen.
//        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
//        listPopupWindow.setModal(true);
//
//        View filterLayout = getLayoutInflater().inflate(R.layout.custom_layout_settings, null);
//
//        listPopupWindow.setPromptView(filterLayout);
//    }


}