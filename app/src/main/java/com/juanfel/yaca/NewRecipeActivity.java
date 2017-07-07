package com.juanfel.yaca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Clase que permite guardar recetas.
 */
public class NewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        final TextView titleView = (TextView)findViewById(R.id.new_recipe_title);

        final NumberPicker timer = (NumberPicker)findViewById(R.id.new_recipe_time);
        timer.setMinValue(0);

        final NumberPicker ratio = (NumberPicker)findViewById(R.id.new_recipe_ratio);
        ratio.setMinValue(0);
        ratio.setMaxValue(100); //Usualmente los ratio están entre 1:4 a 1:20, siendo el mas usual 1:16
        ratio.setValue(16);

        Button saveButton = (Button)findViewById(R.id.new_recipe_save_button);

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RecipeDataSource rds = new RecipeDataSource(getApplicationContext());

                String title = titleView.getText().toString();

                Recipe recipe = new Recipe(0,title, "", "");

                rds.insertRecipe(recipe);

                Intent intent = new Intent(getApplicationContext(), RecipeList.class);
                startActivity(intent);
            }
        });
    }

}
