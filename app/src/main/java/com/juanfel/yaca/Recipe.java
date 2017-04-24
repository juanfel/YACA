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
    public final List<String> children = new ArrayList<String>();

    public Recipe(Integer id, String nombre, List<String> children){
        this.setId(id);
        this.setNombre(nombre);
        if(children != null){
            for (String s : children) {
                this.children.add(s);
            }
        }
    }

    public String getNombre() {
        return nombre;
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
