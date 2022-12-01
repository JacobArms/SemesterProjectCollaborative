package com.example.monkeyrun;

import static com.example.monkeyrun.customSettings.musicVol;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.res.AssetFileDescriptor;
import android.widget.SeekBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class OpeningScreenActivity extends AppCompatActivity{
    Dialog myDialog;
    Timer timer;
    int volume = 80;
    ListPopupWindow listPopupWindow;
    MediaPlayer mp;
    MediaPlayer mpGame;
    boolean wasPlaying = false;
    AudioManager audioManager;
    // Sets the difficulty of the game by setting the int to either 1, 2, or 3
    private int diffNum = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        //Creates the background music for the game
        mp = MediaPlayer.create(this,R.raw.monkey_run_lobby_music);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,100,100);
        mpGame = MediaPlayer.create(this,R.raw.monkey_run_game_music);
        mp.start();
        myDialog = new Dialog(this);
        //Movement of the objects off of screen when the "play" button is hit
        final ImageView AiAi = findViewById(R.id.AiAi);
        final TextView title = findViewById(R.id.Title);
        final TextView Highscore = findViewById(R.id.Highscore);
        final ImageView BlackCircle = findViewById(R.id.BlackCircle);
        final TextView PlayButton = findViewById(R.id.playButton);
        final ImageView settingsButton = findViewById(R.id.settingsButton);
        final ImageView difficultyButton = findViewById(R.id.diffButton);

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

                //Moves the difficulty select button up the screen to disappear before the game starts
                difficultyButton.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.slide_up
                ));

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(OpeningScreenActivity.this, GameActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("difficulty", diffNum);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        mp.stop();
                        mpGame.start();
                    }
                }, 800);

            }
        });

    }
    public void ShowPopup(View v) {
        Log.println(Log.ASSERT, "SAUER", "POPUP OPENED");
        SeekBar musicVol;
        ImageView txtclose;
        myDialog.setContentView(R.layout.activity_custom_settings);
        txtclose =(ImageView) myDialog.findViewById(R.id.exitButton);
        musicVol =(SeekBar) myDialog.findViewById(R.id.musicSeekbar);
        musicVol.setProgress(volume);
        musicVol.setOnSeekBarChangeListener(musicBarChange);


        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.ASSERT, "SAUER", "POPUP CLOSED");
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowDiffPopup(View v) {
        ImageView txtclose;
        Button easy;
        Button medium;
        Button hard;
        myDialog.setContentView(R.layout.custom_difficulty_settings);
        txtclose =  (ImageView) myDialog.findViewById(R.id.exitButton);
        easy = myDialog.findViewById(R.id.easyButton);
        medium = myDialog.findViewById(R.id.mediumButton);
        hard = myDialog.findViewById(R.id.hardButton);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diffNum = 1;
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diffNum = 2;
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diffNum = 3;
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    //LOOKS FOR A CHANGE ON THE SEEK BAR
    SeekBar.OnSeekBarChangeListener musicBarChange = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            Log.println(Log.ASSERT, "SAUER", "PROGRESS CHANGED" + i);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i/5,0);
            volume = i;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.println(Log.ASSERT, "SAUER", "1");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.println(Log.ASSERT, "SAUER", "2");
        }
    };

    public int getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(int diffNum) {
        this.diffNum = diffNum;
    }


}