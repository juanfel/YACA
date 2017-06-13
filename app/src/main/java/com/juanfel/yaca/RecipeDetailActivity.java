package com.juanfel.yaca;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class RecipeDetailActivity extends AppCompatActivity {

    int time_to_end = 130;
    Boolean isTimerStarted = false;
    CountDownTimer timer = null;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        seekBar = (SeekBar)findViewById(R.id.timer_bar);

        final Button botonCronometro = (Button)findViewById(R.id.timer_button);
        final Chronometer chronometer = (Chronometer)findViewById(R.id.timer_seconds);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long delta = SystemClock.elapsedRealtime() - chronometer.getBase();
                if(delta >= time_to_end*1000){
                    switchTimer(botonCronometro, chronometer);
                }
            }
        });

        botonCronometro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switchTimer(botonCronometro, chronometer);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void switchTimer(Button botonCronometro, Chronometer chronometer){
        //Cambia el timer de encendido a apagado y viceversa.

        if(isTimerStarted){
            botonCronometro.setText("Comenzar");
            chronometer.stop();
            isTimerStarted = false;
        }
        else{
            botonCronometro.setText("Pausar");
            chronometer.start();
            isTimerStarted = true;
        }
    }
}
