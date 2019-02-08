# Entornos Multijugador
Este repositorio lo vamos a utilizar para subir código perteneciente a la asignatura de Entornos Multijugador.

## Ejercicio 9. Downloader
Se quiere implementar una aplicación para descargar ficheros. La aplicación debe tener la capacidad de descargar un único fichero, pero debe ser capaz de descargar varios fragmentos del fichero de manera concurrente para aprovechar de forma más eficiente la red.
Para simplificar la aplicación, consideramos que un fichero se representa en memoria como un array de enteros. Internamente, la aplicación dispone de una serie de procesos que van descargando los diferentes fragmentos del fichero (posiciones del array). Los procesos están ejecutando tres acciones: primero se determina el siguiente fragmento a descargar, a continuación se descarga ese fragmento, y por último se guarda el fragmento descargado en el array que representa el fichero. La solución se puede implementar con espera activa o con semáforos.
