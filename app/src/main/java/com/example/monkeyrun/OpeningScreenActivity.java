package com.example.monkeyrun;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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

    Timer timer;
    ListPopupWindow listPopupWindow;
    MediaPlayer mp;
    MediaPlayer mpGame;
    boolean wasPlaying = false;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        //Creates the background music for the game
        mp = MediaPlayer.create(this,R.raw.monkey_run_lobby_music);
        mpGame = MediaPlayer.create(this,R.raw.monkey_run_game_music);
        mp.start();
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //Movement of the objects off of screen when the "play" button is hit
        final ImageView AiAi = findViewById(R.id.AiAi);
        final TextView title = findViewById(R.id.Title);
        final TextView Highscore = findViewById(R.id.Highscore);
        final ImageView BlackCircle = findViewById(R.id.BlackCircle);
        final TextView PlayButton = findViewById(R.id.playButton);
        final ImageView settingsButton = findViewById(R.id.settingsButton);

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
                        mp.stop();
                        mpGame.start();
                    }
                }, 800);

            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListPopupWindow(v);
            }
        });


    }
    private void showListPopupWindow(View anchorView) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setWidth(600);
        List<String> sampleData = new ArrayList<>();
        sampleData.add("");


        listPopupWindow.setAnchorView(anchorView);
        ListPopUpWindowAdapter listPopupWindowAdapter = new ListPopUpWindowAdapter(this, sampleData, new ListPopUpWindowAdapter.OnClickDeleteButtonListener() {
            @Override
            public void onClickExitButton(int position) {
                Toast.makeText(OpeningScreenActivity.this, "Settings Saved", Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.setAdapter(listPopupWindowAdapter);
        listPopupWindow.show();
    }
    public static void changeVolume(AudioManager audioManager, int progress){
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }
}