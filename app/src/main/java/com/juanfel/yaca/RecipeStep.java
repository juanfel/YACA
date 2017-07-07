package com.juanfel.yaca;


/**
 * Created by juanfel on 24-05-17.
 * Controla las variables que están involucradas en cada paso.
 */

public class RecipeStep {
    int step_time; //En segundos
    Boolean has_timer;
    int coffee_percent;

    public RecipeStep(Boolean has_timer, int step_time,int coffee_percent){
        if(has_timer){
            this.has_timer = has_timer;
            this.step_time = step_time;
        }
        this.coffee_percent = coffee_percent;
    }
}
