"# obligatorio_programacion2" 

Proceso de Carga de Datos:
Comenzamos separando las tuplas del archivo en campos, para lo cual decidimos implementar un funcion que identificara a 
los campos como todo lo que este entre "" y enviendolos una vez apareciera una "," luego de una ". Nos encontramos con 
varios problemas al hacerlo, entre esos, tuvimos que implementar que si hay dos " juntas (ejemplo: "" XXX "") no 
significa que sean dos campos.
Además, nos vimos bajo la necesidad de agregar unas comillas al final del campo del id de spotify, pues fue la unica
forma que encontramos para que el archivo nos mostrase lo que queriamos.

Una vez que tuvimos los campos separados, comenzamos creando el pais y, si ya no esta ingresado, se ingresa al Hash de
paises. Luego tomamos el campo correspondiente a la snapshotdate y lo convertimos a un objeto de tipo LocalDate.
Para el capo de artistas hicimos un proceso parecido a un lector de csv comun, obteniendo todos los artistas.

Como ya disponemos de los datos elementales comenzamos a ingresar datos de interes, como que tantas ocurrencias tiene un
artista en un pais, en un dia en particular. 
Contuinuamos con la creacion de las canciones, en donde se registran las apariciones de cada cancion. Para finalmente 
crear la cancien e ingresarla en los tops 50.

Decidimos que el proceso de carga fuera lo mas pesado, para que una vez cargado todo se pudieran hacer multiples 
consultas sin mayores dificultades. 

Memoria RAM consumida: 1525 MB
Tiempo promnedio de ejecucion: 5 minutos y 50 segundos

------------------------------------------------------------------------------------------------------------------------
Proceso de Consulta de Datos:

1) Comenzando por countriesTopTen:
Primeramente checkeamos que todos los datos esten bien. Buscamos en nuestro Hash de tops y obtenemos el Heap,
correspondiente a nuestros datos. Para desplegarlos vamos sacando los elementos del heap utilizando detele. Ademas,
los guardamos en una lista para luego volverlos a insertar y no perder los datos obtenidos.
Memoria RAM consumida: 1525 MB
Tiempo promnedio de ejecucion: 5 minutos y 50 segundos


2) top5OfAllTops
Para esta funcion utilizamos el hash que creamos de las apariciones en los tops. Recorremos segun la fecha y obtenemos 
el total.

3) e
