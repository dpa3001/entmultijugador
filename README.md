# Entornos Multijugador
Este repositorio lo vamos a utilizar para subir código perteneciente a la asignatura de Entornos Multijugador de los temas 2 al 5 (Java).

## Tema 2 - Ejercicio 9. Downloader
Se quiere implementar una aplicación para descargar ficheros. La aplicación debe tener la capacidad de descargar un único fichero, pero debe ser capaz de descargar varios fragmentos del fichero de manera concurrente para aprovechar de forma más eficiente la red.
Para simplificar la aplicación, consideramos que un fichero se representa en memoria como un array de enteros. Internamente, la aplicación dispone de una serie de procesos que van descargando los diferentes fragmentos del fichero (posiciones del array). Los procesos están ejecutando tres acciones: primero se determina el siguiente fragmento a descargar, a continuación se descarga ese fragmento, y por último se guarda el fragmento descargado en el array que representa el fichero. La solución se puede implementar con espera activa o con semáforos.

Licencia CC - Micael Gallego. 
Modificaciones - DPA.

## Tema 5 - Ejercicio 2. Hilo de Mensajes

Se desea implementar en Java un programa con dos hilos, el hilo principal Main y un hilo de Mensajes.

1. El hilo principal:
  - Crea el hilo de mensajes
  - Espera a que el hilo de mensajes finalice
  - Si no lo hace, cada segundo imprime “Todavía esperando…”
  - Cuando ha pasado un tiempo máximo de 5 segundos, si el hilo de mensajes no ha terminado todavía, dice “Cansado de esperar!”, le interrumpe y espera a que termine
  - Al finalizar el hilo dice “Por fin!”

2. El hilo de mensajes:
  - Dice las siguientes frases cada dos segundos: "La vida es bella", "O no...", "Los pajaritos cantan", "Y molestan...”
  - Si el hilo principal le interrumpe antes de terminar, dice “Se acabó!”

3. Cada hilo debe indicar su nombre cada vez que dice algo.

Licencia CC - Micael Gallego. 
Modificaciones - DPA.

## Tema 5 - Ejercicio básico de CyclicBarrier

Se desea implementar 3 hilos que pinten primero 3 Aes, depués un guión y finalmente 3 Bes. Es imprescindible el uso de la clase CyclicBarrier de Java.

## Tema 5 - Ejercicio CountDownLatch

Se solicita modificar el ejercicio de los semáforos por la estrategia CountDownLatch

# Tema 5 - Fábrica de Coches

Este ejercicio está orientado a POO puro. La idea es generar una fábrica de construcción de coches emulando las diferentes tareas entre los robots de montaje y las máquinas de generación de piezas.

# Tema 5 - Aeropuerto

Ejercicio básico de repaso de las estructuras de datos en Java.

# Tema 5 - Gestor de Viajes

Ejercicio intermedio que emula un gestor de viajes. Dada una ciudad de origen y/o destino, el usuario podrá ver por pantalla los diferentes vuelos existentes.

# Tema 5 - FindDuplicates (Uso de Estructuras de Datos Concurrentes y Executors)

Este ejercicio útil, permite de forma recursiva obtener todos los ficheros duplicados dadas dos carpetas de nuestro sistema operativo.
