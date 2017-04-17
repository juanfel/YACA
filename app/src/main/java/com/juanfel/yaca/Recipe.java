package com.juanfel.yaca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanfel on 17-04-17.
 * Modelo de dominio para una receta.
 */

public class Recipe {
    String nombre;
    Integer id;
    public final List<String> children = new ArrayList<String>();

    public Recipe(Integer id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
}
