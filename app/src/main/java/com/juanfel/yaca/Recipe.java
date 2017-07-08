package com.juanfel.yaca;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by juanfel on 17-04-17.
 * Modelo de dominio para una receta.
 */

public class Recipe implements Serializable{
    private String nombre;
    private Integer id;
    private String steps;
    private String timestamp;

    public final List<String> header_list = new ArrayList<String>();

    public Recipe(Integer id, String nombre, String steps, String timestamp){
        this.setId(id);
        this.setNombre(nombre);
        this.steps = steps;
        Gson gson = new Gson();
        RecipeStep stepObject = gson.fromJson(steps, RecipeStep.class);
        if(this.header_list != null){
            this.header_list.add("ID: " + this.getId() + "\n");
            this.header_list.add("Timestamp:" + timestamp + "\n");
            this.header_list.add("Ratio: 1:" + stepObject.coffee_percent);
            this.header_list.add("Tiempo total:" + stepObject.step_time + " segundos");
        }
        this.timestamp = timestamp;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
