package com.juanfel.yaca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by juanfel on 24-04-17.
 * Obtiene los datos de receta de la base de datos
 */

public class RecipeDataSource {
    DatabaseHelper dbHelper;
    String[] columnNames = new String[]{ dbHelper.Recipe_Id, dbHelper.Recipe_Name, dbHelper.Recipe_Steps_Json, dbHelper.Recipe_Timestamp};
    public RecipeDataSource(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public void insertRecipe(Recipe recipe){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.Recipe_Name, recipe.getNombre());

        cv.put(dbHelper.Recipe_Steps_Json, recipe.getSteps());

        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar c = Calendar.getInstance();
        Log.d("YACA", df.format(c.getTime()));
        cv.put(dbHelper.Recipe_Timestamp, df.format(c.getTime()));


        db.insert(dbHelper.Recipe_Table, null, cv);
        db.close();
    }
    public List<Recipe> readRecipes(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbHelper.Recipe_Table,
                columnNames,
                null,null,null,null,null);
        cursor.moveToFirst();
        List<Recipe> recipes = new ArrayList<>();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(dbHelper.Recipe_Id));
            String name = cursor.getString(cursor.getColumnIndex(dbHelper.Recipe_Name));
            String steps = cursor.getString(cursor.getColumnIndex(dbHelper.Recipe_Steps_Json));
            String timestamp = cursor.getString(cursor.getColumnIndex(dbHelper.Recipe_Timestamp));

            Recipe recipe = new Recipe(id,name, steps, timestamp);
            recipes.add(recipe);
            cursor.moveToNext();
        }
        return recipes;
    }

    public void deleteRecipe(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.Recipe_Table, dbHelper.Recipe_Id + " = ?", new String[]{id});
    }
}
