package com.abril;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String nombre;
    private int dni;
    private String email;

    public Usuario(String n, String e, int d){
        nombre = n;
        dni = d;
        email = e;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getdni(){
        return this.dni;
    }

    public String getemail(){
        return this.email;
    }
}
