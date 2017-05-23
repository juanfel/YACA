package com.juanfel.yaca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanfel on 17-04-17.
 * Modelo de dominio para una receta.
 */

public class Recipe {
    private String nombre;
    private Integer id;
    public final List<String> header_list = new ArrayList<String>();

    public Recipe(Integer id, String nombre){
        this.setId(id);
        this.setNombre(nombre);
        if(this.header_list != null){
            this.header_list.add("ID: " + this.getId() + "\n");
        }
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
}
