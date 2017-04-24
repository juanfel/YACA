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
    public static final String Recipe_Name = "name";
    public static final String Recipe_Steps_Json = "steps";
    public static final String Recipe_Timestamp = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DBName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] recipeArgs = new String[]{Recipe_Table, Recipe_Name, Recipe_Steps_Json, Recipe_Timestamp };
        db.execSQL("CREATE TABLE ? ( ? text, ? text, ? text, ? text)",recipeArgs);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ?", new String[]{Recipe_Name});
        onCreate(db);

    }
}
