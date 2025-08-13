package com.abril;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;

public class Biblioteca implements Serializable {
    private HashMap<String, Libro> librosPorNombre;
    private HashMap<String, List<Libro>> librosPorAutor;
    private HashMap<Usuario, List<Prestamo>> prestamosActivos;
    private ArrayList<Prestamo> historialDePrestamos;
    private HashMap<Integer, Usuario> usuarios;

    public Biblioteca(){
        HashMap<String, Libro> librosPorN= new HashMap<String, Libro>();
        librosPorNombre = librosPorN;
        HashMap<String, List<Libro>> librosPorA = new HashMap<String, List<Libro>>();
        librosPorAutor = librosPorA;
        prestamosActivos = new HashMap<Usuario, List<Prestamo>>();
        historialDePrestamos = new ArrayList<Prestamo>();
        usuarios = new HashMap<Integer, Usuario>();
    }

    public void agregarUsuario(Usuario usuario){
        Integer dni = usuario.getdni();
        if(!usuarios.containsKey(dni)){
            usuarios.put(dni, usuario);
        }
    }

    public Usuario buscarUsuario(Integer dni){
        return usuarios.get(dni);
    }

    public void agregarLibro(Libro libro){
        String nombre = libro.getNombre();
        librosPorNombre.put(nombre, libro);

        String autor = libro.getAutor();
        if(librosPorAutor.containsKey(autor)){
            librosPorAutor.get(autor).add(libro);
        }else{
            List<Libro> lista = new ArrayList<Libro>();
            lista.add(libro);
            librosPorAutor.put(autor, lista);
        }
    }

    public void hacerUnPrestamo(Prestamo prestamo){
        Usuario cliente = prestamo.getUsuario();
        if(prestamosActivos.containsKey(cliente)){
            prestamosActivos.get(cliente).add(prestamo);
        }else{
            List<Prestamo> lista = new ArrayList<Prestamo>();
            lista.add(prestamo);
            prestamosActivos.put(cliente, lista);
        }
        Libro libro = prestamo.getLibro();
        libro.marcarComoEnPrestamo();
    }

    public void devolverUnLibro(Usuario cliente, Libro libro){
        List<Prestamo> lista = prestamosActivos.get(cliente);
        if (lista == null || lista.isEmpty()) return;
        Prestamo prestamo = null;
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getLibro().equals(libro)){
                prestamo = lista.get(i);
                break;
            }
        }
        if(prestamo != null){
            prestamosActivos.get(cliente).remove(prestamo);
            historialDePrestamos.add(prestamo);
            libro.marcarComoDevuelto();
        }
    }

    public Libro buscarPorNombre(String nombre){
        if(librosPorNombre.containsKey(nombre)){
            return librosPorNombre.get(nombre);
        }
        return null;
    }

    public List<Libro> buscarPorAutor(String autor){
        if(librosPorAutor.containsKey(autor)){
            return librosPorAutor.get(autor);
        }
        return null;
    }

    public void listarLibros(){
        for(Libro libro: librosPorNombre.values()){
            System.out.println(libro);
        }
    }

    public void guardarDatos() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("biblioteca.dat"));
            out.writeObject(this);
            out.close();
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
