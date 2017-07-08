package com.juanfel.yaca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.List;

public class RecipeList extends AppCompatActivity {
    RecipeListAdapter listAdapter;
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

                Intent intent = new Intent(getApplicationContext(), NewRecipeActivity.class);
                startActivity(intent);
            }
        });

        //Recupera la lista de recetas
        RecipeDataSource rds = new RecipeDataSource(getApplicationContext());
        List<Recipe> recipe_list = rds.readRecipes();

        //Creación de la lista de recetas
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.content_recipe_list);
        listAdapter = new RecipeListAdapter(this, recipe_list);
        listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Intent intent = new Intent(getApplicationContext(), RecipeDetailActivity.class);
                Recipe recipe = (Recipe)parent.getExpandableListAdapter().getGroup(groupPosition);

                Log.d("YACA recipe", recipe.getSteps());
                intent.putExtra("recipe_step", recipe.getSteps());
                startActivity(intent);
                return false;
            }
        });

        registerForContextMenu(listView);
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recipe_list_options, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_recipe_list_delete:
                Recipe recipe = (Recipe)listAdapter.getGroup(ExpandableListView.getPackedPositionGroup(info.packedPosition));
                RecipeDataSource rds = new RecipeDataSource(getApplicationContext());
                rds.deleteRecipe(Integer.toString(recipe.getId()));

                Intent intent = new Intent(getApplicationContext(), RecipeList.class);
                startActivity(intent);
                return true;
            case R.id.menu_recipe_list_modify:
                Toast toast = Toast.makeText(getApplicationContext(),"TODO", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
