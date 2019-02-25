package Tema5.Ejercicio9.Downloader;

/**
 * A partir del ejercicio 9 del tema 2 empezamos a modificar: 1 - Eliminamos la
 * librería SimpleConcurrent y vemos los errores de código que aparecen y los
 * arreglamos todos. 1.1 - Modificamos sleepRandom por un método sleep de Java
 * 1.2 - Generamos dos métodos para pintar por pantalla println y print 1.3 -
 * Eliminamos enterMutex y exitMutex 1.4 - Arreglamos el método main
 * 
 * 2 - Generamos la estructura (plantilla) con Threads 2.1 - Con la plantilla
 * habitual ya podemos eliminar todas las cláusulas static 2.2 - Al utilizar
 * métodos de sincronismo podemos eliminar el atributo volatile de las variables
 * 2.3 - Hay que generar un contador de hilos finalizados
 * 
 * 3 - Esta es la solución al ejercicio donde se descarga un único
 * fichero y el último en darse cuenta que ya ha terminado es el que 
 * pinta el fichero.
 * 
 * 4 - Lo que se pedía en este ejercicio es que la descarga del fichero
 * se repitiese constantemente. Por consiguiente, deberemos de modificar
 * este código y que utilice CyclicBarrier.
 * 
 * 4.1 - Modificamos el nombre del método downloader a downloadFile, 
 * porque este método sirve para descargar un único fichero.
 * 4.2 - Creamos un método que se llame downloadFiles
 * 4.3 - En el método exec modificamos el nombre del método al nuevo método creado "downloadFiles"
 *  
 * ¿CUÁL ES LA PROBLEMATICA DE ESTA SOLUCIÓN?
 * 
 * 
 * @author DPA
 *
 */
public class Ejer9A_Downloader_Plantilla4 {

	private final int N_FRAGMENTOS = 10;
	private final int N_HILOS = 3;

	private int[] fichero = new int[N_FRAGMENTOS];

	private int sigFragDescarga;

	private int numHilosTerminados = 0;

	private int descargaDatos(int numFragmento) {
		try {
			Thread.sleep((long) Math.random() * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return numFragmento * 2;
	}

	private void mostrarFichero() {
		println("--------------------------------------------------");
		print("File = [");
		for (int i = 0; i < N_FRAGMENTOS; i++) {
			print(fichero[i] + ",");
		}
		println("]");
	}

	private void println(String mensaje) {
		System.out.println(mensaje);
	}

	private void print(String mensaje) {
		System.out.println(mensaje);
	}

	public void downloadFile() {

		while (true) {

			int frag;

			// Generamos una sección sincronizada
			synchronized (this) {
				// Ya no hay más fragmentos libres
				if (sigFragDescarga == N_FRAGMENTOS) {
					break;// Esta sentencia nos saca del bloque sincronizado
				}

				frag = sigFragDescarga;

				sigFragDescarga++;
			}

			// frag debemos de sacarla fuera
			int datos = descargaDatos(frag);

			// El acceso a un array es siempre de forma ATÓMICA
			fichero[frag] = datos;
			
		}
		
		/* Cuando yo me he dado cuenta que no hay más fragmentos
		 * lo que hace la sección del while es salir gracias a un
		 * break
		 * 
		 * Entonces, lo que nosotros queremos es saber cuál es el
		 * último en salir del bucle y entonces mostrar el fichero
		 *  
		 */

		/* Generamos un fragmento sincronizado que cuenta el
		 * número de hilos terminados y en ese momento
		 * muestra el fichero.
		*/		
		synchronized (this) {
			numHilosTerminados++;
			
			if (numHilosTerminados == N_HILOS)
				mostrarFichero();
		}
	}

	/**
	 * Hay que descargar 10 fragmentos = N_FRAGMENTOS
	 * Cada hilo va a intentar descargar un fichero.
	 * 
	 * Cuando el hilo haya terminado de descargar el fichero, 
	 * porque no haya más fragmentos que descargar
	 * 
	 * Sin embargo, tenemos un PROBLEMA o RESTRICCIÓN
	 * como solo tenemos un array o buffer de ficheros
	 * para guardar los datos del fichero.
	 * 
	 * Un hilo no puede descargar el siguiente fichero
	 * hasta que se haya terminado de descargar el fichero
	 * anterior.
	 * 
	 */
	public void downloadFiles() {
	
		for (int i = 0; i < N_FRAGMENTOS; i++) {
			downloadFile();
		}
	}
	
	/**
	 * Modificamos el nombre del método que lanza el hilo
	 */
	public void exec() {
		for (int i = 0; i < N_HILOS; i++) {
			new Thread(() -> downloadFiles()).start();
		}
	}

	public static void main(String[] args) {

		new Ejer9A_Downloader_Plantilla4().exec();
	}

}
