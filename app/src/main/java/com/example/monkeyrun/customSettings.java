package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

public class customSettings extends AppCompatActivity{

    SeekBar musicSeekbar,soundSeekbar;
    OpeningScreenActivity openingScreenActivity;
    AudioManager audioManager = openingScreenActivity.audioManager;
    MediaPlayer song = openingScreenActivity.mp;
    public static int musicVol = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        musicSeekbar = findViewById(R.id.musicSeekbar);
        soundSeekbar = findViewById(R.id.soundSeekbar);
        musicVol=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_settings);
        //Seekbar adjusting sound
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Sets the max volume for both the music and sound
        musicSeekbar.setMax(maxVolume);
        soundSeekbar.setMax(maxVolume);


        //Changes the sound and music of the game
        musicSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public SeekBar getMusicSeekbar() {
        return musicSeekbar;
    }

    public void setMusicSeekbar(SeekBar musicSeekbar) {
        this.musicSeekbar = musicSeekbar;
    }

    public SeekBar getSoundSeekbar() {
        return soundSeekbar;
    }

    public void setSoundSeekbar(SeekBar soundSeekbar) {
        this.soundSeekbar = soundSeekbar;
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