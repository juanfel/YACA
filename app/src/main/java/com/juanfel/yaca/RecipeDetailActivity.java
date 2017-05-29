package com.juanfel.yaca;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class RecipeDetailActivity extends AppCompatActivity {

    int time_to_end = 130;
    Boolean isTimerStarted = false;
    CountDownTimer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        final NumberPicker numberPicker = (NumberPicker)findViewById(R.id.timer_seconds);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(300);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                time_to_end = newVal;
            }
        });



        final Button botonCronometro = (Button)findViewById(R.id.timer_button);
        botonCronometro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(isTimerStarted){
                    botonCronometro.setText("Comenzar");
                    timer.cancel();
                    isTimerStarted = false;
                }
                else{
                    if(timer != null){
                        timer.cancel();
                    }
                    timer = new CountDownTimer(time_to_end*1000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            numberPicker.setValue((int)millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
                            isTimerStarted = false;
                        }
                    }.start();
                    botonCronometro.setText("Pausar");
                    isTimerStarted = true;
                }

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
}
