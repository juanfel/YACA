package com.juanfel.yaca;

/**
 * Created by juanfel on 24-05-17.
 * Controla las variables que están involucradas en cada paso.
 */

public class Recipe_Step {
    float step_time; //En segundos
    Boolean has_timer;
    float water_percent;
    float coffee_percent;

    public Recipe_Step(Boolean has_timer, float step_time, float water_percent, float coffee_percent){
        if(has_timer){
            this.has_timer = has_timer;
            this.step_time = step_time;
        }
        this.water_percent = water_percent;
        this.coffee_percent = coffee_percent;
    }
}
