package com.abril;

import java.io.Serializable;

public class Libro implements Serializable{
    private String nombre;
    private String autor;
    private int ano;
    private int ISBN;
    private Boolean prestado;

    public Libro(String n, String a, int an, int i){
        nombre = n;
        autor = a;
        ano = an;
        ISBN = i;
        prestado = false;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getAutor(){
        return this.autor;
    }

    public int getano(){
        return this.ano;
    }

    public int getISBN(){
        return this.ISBN;
    }

    public Boolean estaEnPrestamo(){
        return this.prestado;
    }

    public void marcarComoEnPrestamo(){
        this.prestado = true;
    }

    public void marcarComoDevuelto(){
        this.prestado = false;
    }

    @Override
    public String toString(){
        return nombre + " - " + autor + " - " + ano + " (" + ISBN + "). ";
    }
}
