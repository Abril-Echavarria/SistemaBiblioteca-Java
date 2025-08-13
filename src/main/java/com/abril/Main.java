package com.abril;
import java.util.Scanner;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Biblioteca biblioteca;
        try { //si existe un archivo, carga el archivo
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("biblioteca.dat"));
            biblioteca = (Biblioteca) in.readObject();
            in.close();
            System.out.println("Datos cargados correctamente.");
        } catch (Exception e) { // si no existe el archivo, creamos uno nuevo
            biblioteca = new Biblioteca();
            System.out.println("No se encontraron datos previos, creando biblioteca nueva.");
        }
        Scanner sc = new Scanner(System.in);

        int opcion;
        do { //Menu inicial
            System.out.println("=== Menú Biblioteca ===");
            System.out.println("1. Agregar libro");
            System.out.println("2. Prestar libro");
            System.out.println("3. Crear un usuario");
            System.out.println("4. Devolver libro");
            System.out.println("5. Listar libros");
            System.out.println("6. Buscar por nombre");
            System.out.println("7. Buscar por autor");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 0: //caso si se cierra el programa, se guardan los datos
                    biblioteca.guardarDatos();
                    System.out.println("Saliendo del programa...");
                    break;
                case 1: //caso si se agrega un libro nuevo
                    System.out.println("=======================");
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print( "Año de publicación: ");
                    int ano = sc.nextInt();
                    sc.nextLine();
                    System.out.print( "ISBN: ");
                    int ISBN = sc.nextInt();
                    sc.nextLine();
                    Libro libro = new Libro(titulo, autor, ano, ISBN);
                    biblioteca.agregarLibro(libro);
                    System.out.print("Libro agregado con exito.\n");
                    System.out.println("=======================");
                    break;

                case 2: //caso de pedir prestado un prestamo
                System.out.println("=======================");
                    System.out.print("Para pedir un libro necesitamos unos datos.\n");
                    System.out.print("Título: ");
                    String nombre = sc.nextLine();
                    Libro l = biblioteca.buscarPorNombre(nombre);
                    if(l == null){System.out.print("No existe este libro.\n"); System.out.println("======================="); break;}
                    if(l.estaEnPrestamo()){System.out.print("El libro esta en prestamo en este momento.\n"); System.out.println("======================="); break;}
                    System.out.print("Si ya tenes un usuarios, escribe tu dni, si no crea uno: ");
                    int d = sc.nextInt();
                    sc.nextLine();
                    Usuario usu = biblioteca.buscarUsuario(d);
                    if(usu == null){System.out.print("No existe este usuario.\n"); System.out.println("======================="); break;}
                    System.out.print("Fecha de hoy: ");
                    String fp = sc.nextLine();
                    System.out.print("Fecha de devolución: ");
                    String fd = sc.nextLine();
                    Prestamo p = new Prestamo(usu, l, fp, fd);
                    biblioteca.hacerUnPrestamo(p);
                    System.out.println("Prestamo hecho con exito.");
                    System.out.println("=======================");
                    break;

                case 3: //Crear un usuario nuevo
                System.out.println("=======================");
                    System.out.print("Para crear un usuario necesitamos unos datos.\n");
                    System.out.print("Nombre del cliente: ");
                    String cliente = sc.nextLine();
                    System.out.print("DNI: ");
                    int dni = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Email del cliente: ");
                    String email = sc.nextLine();
                    Usuario usuario = new Usuario(cliente, email, dni);
                    biblioteca.agregarUsuario(usuario);
                    System.out.print("Usuarios creado con exito.\n");
                    System.out.println("=======================");
                    break;

                case 4: //Devolver un libro
                    System.out.println("=======================");
                    System.out.print("Titulo del libro prestado:");
                    String t = sc.nextLine();
                    Libro libro2 = biblioteca.buscarPorNombre(t);
                    if(libro2 == null){System.out.print("No existe este libro.\n"); break;}
                    
                    System.out.print("Dni del cliente: ");
                    int dn = sc.nextInt();
                    sc.nextLine();
                    Usuario usuario2 = biblioteca.buscarUsuario(dn);
                    if(usuario2 == null){System.out.print("No existe este usuario.\n"); break;}
                    
                    biblioteca.devolverUnLibro(usuario2, libro2);
                    System.out.print("El libro ha sido devuelto.\n");
                    System.out.println("=======================");
                    break;
                case 5: //ver todos los libros
                    System.out.println("=======================");
                    biblioteca.listarLibros();
                    System.out.println("=======================");
                    break;

                case 6:
                    System.out.println("=======================");
                    System.out.print("Titulo del libro: ");
                    String ti = sc.nextLine();
                    Libro libro3 = biblioteca.buscarPorNombre(ti);
                    if(libro3 == null){System.out.println("Libro no encontrado."); System.out.println("======================="); break;}
                    System.out.println(libro3.toString());
                    System.out.println("=======================");
                    break;

                case 7:
                    System.out.println("=======================");
                    System.out.print("Nombre del autor: ");
                    String au = sc.nextLine();
                    List<Libro> lista3 = biblioteca.buscarPorAutor(au);
                    if(lista3 == null){System.out.println("Autor no encontrado.\n"); System.out.println("======================="); break;}
                    for (Libro lib : lista3) {
                        System.out.println(lib);
                    }
                    System.out.println("=======================");
                    break;

            }
        } while (opcion != 0);
        sc.close();
    }
}
