package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.SeekBar;

public class customSettings extends AppCompatActivity {
    SeekBar musicSeekbar,soundSeekbar;
    OpeningScreenActivity openingScreenActivity;
    AudioManager audioManager = openingScreenActivity.audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_settings);
        //Seekbar adjusting sound
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        //Sets the max volume for both the music and sound
        musicSeekbar.setMax(maxVolume);
        soundSeekbar.setMax(maxVolume);


        soundSeekbar.setProgress(currentVolume);

        //Changes the sound and music of the game
        musicSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                musicSeekbar.setProgress(currentVolume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }