package com.juanfel.yaca;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;

import com.google.gson.Gson;

public class RecipeDetailActivity extends AppCompatActivity {

    int time_to_end = 30;
    long elapsed = 0;
    Boolean isTimerStarted = false;
    SeekBar seekBar;
    RecipeStep step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        seekBar = (SeekBar)findViewById(R.id.timer_bar);

        final Button botonCronometro = (Button)findViewById(R.id.timer_button);
        final Chronometer chronometer = (Chronometer)findViewById(R.id.timer_seconds);
        final NumberPicker numberPicker = (NumberPicker)findViewById(R.id.timer_total_seconds);
        final EditText coffeeField = (EditText)findViewById(R.id.activity_recipe_detail_coffee);
        final EditText waterField= (EditText)findViewById(R.id.activity_recipe_detail_water);


        String stepsJson = getIntent().getStringExtra("recipe_step");
        Gson gson = new Gson();
        if(stepsJson != null){
            Log.d("YACA",stepsJson);
            step = gson.fromJson(stepsJson, RecipeStep.class);
        }
        else{
            step = new RecipeStep(true,10,16);
        }

        time_to_end = step.step_time;

        coffeeField.setText("16");
        waterField.setText("100");
        setWaterByRatio(coffeeField,waterField);

        coffeeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    setWaterByRatio(coffeeField,waterField);
                }
            }
        });

        waterField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    setCoffeeByRatio(coffeeField,waterField);
                }
            }
        });
        numberPicker.setMaxValue(999);
        numberPicker.setMinValue(0);
        numberPicker.setValue(step.step_time);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                time_to_end = newVal;
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long delta = SystemClock.elapsedRealtime() - chronometer.getBase();
                if(delta >= time_to_end*1000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    switchTimer(botonCronometro, chronometer);
                    makeBrewOverNotification();


                }
                else{
                    seekBar.setProgress(100*(int)delta/(time_to_end*1000));
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
            elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
            isTimerStarted = false;
        }
        else{
            botonCronometro.setText("Pausar");
            chronometer.setBase(SystemClock.elapsedRealtime() - elapsed);
            chronometer.start();
            isTimerStarted = true;
        }
    }

    /**
     * Cambia los campos de café en base al ratio usado y al agua actual.
     * @param coffeeField
     * @param waterField
     */
    public void setCoffeeByRatio(EditText coffeeField, EditText waterField){
        int water = Integer.parseInt(waterField.getText().toString());
        int coffee = water/step.coffee_percent;

        coffeeField.setText(Integer.toString(coffee));
    }

    /**
     * Cambia el campo de agua en base al ratio usado y al agua actual.
     * @param coffeeField
     * @param waterField
     */
    public void setWaterByRatio(EditText coffeeField, EditText waterField){
        int coffee = Integer.parseInt(coffeeField.getText().toString());
        int water = coffee*step.coffee_percent;

        waterField.setText(Integer.toString(water));
    }

    /**
     * Crea notificación para cuando termina el temporizador
     */
    public void makeBrewOverNotification(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Terminó la receta")
                        .setContentText("")
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        Intent notificationIntent = new Intent(this, RecipeDetailActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);
        // Sets an ID for the notification
        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}
