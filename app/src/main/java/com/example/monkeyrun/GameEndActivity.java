package com.example.monkeyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameEndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        OpeningScreenActivity diffValue = new OpeningScreenActivity();
        int diffNumber = diffValue.getDiffNum();
        ImageView img = (ImageView) findViewById(R.id.diffCharacter);


        if(diffNumber == 1){
            img.setImageResource(R.drawable.baby_monkey_end_screen);
        }else if(diffNumber == 2){
            img.setImageResource(R.drawable.aiaicheer);
        }else if(diffNumber == 3){
            img.setImageResource(R.drawable.gon_gon_end_screen);
        }
    }
}