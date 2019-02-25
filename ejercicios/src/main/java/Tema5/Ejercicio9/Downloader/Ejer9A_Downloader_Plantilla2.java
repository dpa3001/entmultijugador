package Tema5.Ejercicio9.Downloader;



/**
 * A partir del ejercicio 9 del tema 2 empezamos a modificar:
 * 1 - Eliminamos la librería SimpleConcurrent y vemos los errores de código que 
 * 		aparecen y los arreglamos todos.
 * 1.1 - Modificamos sleepRandom por un método sleep de Java
 * 1.2 - Generamos dos métodos para pintar por pantalla println y print
 * 1.3 - Eliminamos enterMutex y exitMutex
 * 1.4 - Arreglamos el método main
 * 
 * * 2 - Generamos la estructura (plantilla) con Threads
 * @author DPA
 *
 */
public class Ejer9A_Downloader_Plantilla2 {

	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;

	private static volatile int[] fichero = new int[N_FRAGMENTOS];

	// Add the attributes you need
	private static volatile int sigFragDescarga;
	//private static volatile boolean hayFragmentos; --> Ya no es necesaria

	private static int descargaDatos(int numFragmento) {
		//sleepRandom(1000);
		try {
			Thread.sleep((long)Math.random()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return numFragmento * 2;
	}

	private static void mostrarFichero() {
		println("--------------------------------------------------");
		print("File = [");
		for (int i = 0; i < N_FRAGMENTOS; i++) {
			print(fichero[i] + ",");
		}
		println("]");
	}

	//Creamos dos métodos propios privados (println y print)
	private static void println(String mensaje) {
		System.out.println(mensaje);
	}
	
	private static void print(String mensaje) {
		System.out.println(mensaje);
	}

	public static void downloader() {

		while (true) {
			//Eliminamos los mutex
			//enterMutex();

			//La declaramos fuera del bloque sincronizado para 
			// que sea accesible
			int frag;
			
			//Generamos una sección sincronizada 
			//¡¡OBSERVAR EL ERROR QUE DA!!
			synchronized (this) {
				// Ya no hay más fragmentos libres
				if (sigFragDescarga == N_FRAGMENTOS) {
					//exitMutex();
					break;// Esta sentencia nos saca del bloque sincronizado
				}

				frag = sigFragDescarga;

				sigFragDescarga++;
			}
			
			//exitMutex();

			//frag debemos de sacarla fuera
			int datos = descargaDatos(frag);

			// El acceso a un array es siempre de forma ATÓMICA
			fichero[frag] = datos;
		}
	}

	public static void main(String[] args) {

		//createThreads(N_HILOS, "downloader");

		//startThreadsAndWait();
		
		for (int i = 0; i < N_HILOS; i++) {
			new Thread(()->downloader()).start();
		}

		// Ahora queremos mostrar el fichero cada vez que 
		// se descargue un fichero
		
		 mostrarFichero();
	}
}
