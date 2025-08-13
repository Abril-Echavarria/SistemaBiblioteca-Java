Sistema de biblioteca en Java con gestión de libros, usuarios y préstamos, se ejecuta en la consola y los archivos se guardan cuando se cierran.

Para ejecutarlo:
    javac -d out src/main/java/com/abril/*.java
    java -cp out com.abril.Main

Opciones:
0. Cierra el programa, guardando los cambios hechos.
1. Agrega un libro, se escribe su titulo, autor, fecha de lanzamiento e ISBN
2. Prestar un libro, se necesita el nombre del libro y el dni de un usuario, su fecha de cuando se presta y la fecha de devolución.
3. Crear un usuarios, se crea con su nombre, dni y email, sirve para pedir prestado un libro.
4. Devolver un libro, se debe tener su dni y titulo del libro.
5. Listar libros, muestra todos los libros que estan registrados.
6. Busca su libro usando su nombre.
7. Busca todos los libros del autor.