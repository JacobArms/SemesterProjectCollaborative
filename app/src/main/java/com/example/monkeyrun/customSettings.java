package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;

public class customSettings extends AppCompatActivity {
    SeekBar musicSeekbar,soundSeekbar;
    AudioManager audioManager;

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

        musicSeekbar.setProgress(currentVolume);
        soundSeekbar.setProgress(currentVolume);

        //Changes the sound and music of the game
        musicSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
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