"# obligatorio_programacion2" 

Proceso de Carga de Datos:

1. Carga Masiva de Datos desde CSV
El proceso de carga de datos inicia con la lectura de un archivo CSV que contiene la información de las canciones, sus posiciones en los rankings, los artistas, entre otros atributos. Este archivo es cargado por el usuario proporcionando la ruta del archivo a través de la interfaz de usuario en la consola.

2. Fue modificada la lectura del CSV de tal manera que el programa pudiera identificar comas (,) fuera de las comillas (" ") durante la lectura del archivo CSV. Esto de tal manera para que no generase errores, porque en algunos casos en los que se daba que habían múltiples artistas en una misma canción ocurría el error de que identificara al segundo artista como si fuera el siguiente valor del elemento.


