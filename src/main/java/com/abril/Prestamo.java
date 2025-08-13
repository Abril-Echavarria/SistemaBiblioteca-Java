package com.abril;

import java.io.Serializable;

public class Prestamo implements Serializable {
    private Usuario usuario;
    private Libro libro;
    private String fechaDePrestamo;
    private String fechaDeDevo;

    public Prestamo(Usuario usua, Libro li, String fp, String fd){
        usuario = usua;
        libro = li;
        fechaDePrestamo = fp;
        fechaDeDevo = fd;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public Libro getLibro(){
        return this.libro;
    }

    public String getFechaPrestamo(){
        return this.fechaDePrestamo;
    }

    public String getFechaDevolucion(){
        return this.fechaDeDevo;
    }
}
