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
 * @author DPA
 *
 */
public class Ejer9A_Downloader_Plantilla3 {

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

	public void downloader() {

		while (true) {

			int frag;

			// Generamos una sección sincronizada
			// ¡¡OBSERVAR EL ERROR QUE DA!!
			synchronized (this) {
				// Ya no hay más fragmentos libres
				if (sigFragDescarga == N_FRAGMENTOS) {
					// exitMutex();
					break;// Esta sentencia nos saca del bloque sincronizado
				}

				frag = sigFragDescarga;

				sigFragDescarga++;
			}

			// exitMutex();

			// frag debemos de sacarla fuera
			int datos = descargaDatos(frag);

			// El acceso a un array es siempre de forma ATÓMICA
			fichero[frag] = datos;
			
		}
		
		/* Cuando yo me he dado cuenta que no hay más fragmentos
		 * lo que hace la sección del while es salir gracias a un
		 * break
		 * 
		 * Entonces lo que nosotros queremos es saber cuál es el
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

	public void exec() {
		for (int i = 0; i < N_HILOS; i++) {
			new Thread(() -> downloader()).start();
		}
	}

	public static void main(String[] args) {

		new Ejer9A_Downloader_Plantilla3().exec();

		// Ahora queremos mostrar el fichero cada vez que
		// se descargue un fichero, por lo tanto esto no iría aquí

		// mostrarFichero();
	}

}
