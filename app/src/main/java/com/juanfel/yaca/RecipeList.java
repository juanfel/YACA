package com.juanfel.yaca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.List;

public class RecipeList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Example database
                RecipeDataSource rds = new RecipeDataSource(getApplicationContext());
                Recipe recipe = new Recipe(0,"Testo", "", "");
                rds.insertRecipe(recipe);
                List<Recipe> recipes = rds.readRecipes();
                Snackbar.make(view, "Creando receta de prueba: " + recipe.getNombre(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Recupera la lista de recetas
        RecipeDataSource rds = new RecipeDataSource(getApplicationContext());
        List<Recipe> recipe_list = rds.readRecipes();

        //Creación de la lista de recetas
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.content_recipe_list);
        RecipeListAdapter listAdapter = new RecipeListAdapter(this, recipe_list);
        listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Intent intent = new Intent(getApplicationContext(), RecipeDetailActivity.class);
                startActivity(intent);
                return false;
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
