package com.juanfel.yaca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by juanfel on 24-04-17.
 * Administra la base de datos
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBName = "YACA.db";
    public static final int Version = 1;

    //Campos para Receta
    public static final String Recipe_Table = "RECIPE";
    public static final String Recipe_Id = "id";
    public static final String Recipe_Name = "name";
    public static final String Recipe_Steps_Json = "steps";
    public static final String Recipe_Timestamp = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DBName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Object[] recipeArgs = new Object[]{Recipe_Table, Recipe_Id, Recipe_Name, Recipe_Steps_Json, Recipe_Timestamp };
        db.execSQL(String.format("CREATE TABLE %s ( %s text PRIMARY KEY, %s text, %s text, %s text)",
                Recipe_Table, Recipe_Id, Recipe_Name, Recipe_Steps_Json, Recipe_Timestamp));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Recipe_Table);
        onCreate(db);

    }
}
