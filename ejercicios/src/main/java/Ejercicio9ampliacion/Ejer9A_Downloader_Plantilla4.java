package Ejercicio9ampliacion;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer9A_Downloader_Plantilla4 {

	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;

	private static volatile int[] fichero = new int[N_FRAGMENTOS];

	// Add the attributes you need
	private static volatile int sigFragDescarga;
	//private static volatile boolean hayFragmentos; --> Ya no es necesaria

	private static int descargaDatos(int numFragmento) {
		sleepRandom(1000);
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

	public static void downloader() {

		/* Escribimos el pseudo-código */

		/*
		 * Bien hay que conseguir que no nos toquen las variables compartidas mientras
		 * yo la esté utilizando, pero que al proceso le dejen descargar los ficheros
		 * concurrentemente.
		 */
		/*
		 * NOTA1: CUIDADO CON LA VARIABLE hayFragmentos --> cuando yo la miré había
		 * fragmentos, pero me han podido tocar la variable
		 */

		// Eliminamos este código y lo transformamos por un bucle
		// hayFragmentos = sigFragDescarga < N_FRAGMENTOS;

		while (true) {
			enterMutex();

			// Ya no hay más fragmentos libres
			if (sigFragDescarga == N_FRAGMENTOS) {
				exitMutex();
				break;// No se lo digáis a los profesores de primero.
			}

			int frag = sigFragDescarga;

			sigFragDescarga++;

			exitMutex();

			int datos = descargaDatos(frag);

			// El acceso a un array es siempre de forma ATÓMICA
			fichero[frag] = datos;
		}

		// ¿ESTÁ BIEN ESTE CÓDIGO?***
	}

	public static void main(String[] args) {

		createThreads(N_HILOS, "downloader");

		startThreadsAndWait();

		mostrarFichero();
	}

	/*
	 * * NO, PORQUE TE ESTÁN TOCANDO LA VARIABLE. El contandor puede irse
	 * modificando.
	 */

	/*
	 * ** Hasta que un proceso no termine su descarga "sigFragDescarga", Nadie puede
	 * entrar a descargar
	 * 
	 * Este programa está bien escrito, no va a tener condiciones de sincronización
	 * o de carrera, perooooo es muy poco eficiente y no utiliza de forma adecuada
	 * los recursos.
	 * 
	 * NO PERMITE QUE DOS PROCESOS DESCARGUEN A LA MISMA VEZ. Tenemos 3 hilos que no
	 * trabajan concurrentemente
	 */

	/*
	 * *** Hemos apuntado en la variable local frag lo que vale la variable
	 * compartida. Aumentamos el valor de la variable compartida y de esta forma
	 * todo el mundo se entera que yo me he quedado con este valor y el siguiente
	 * disponible es el siguiente. Puedo liberar la exclusión mutua, porque a partir
	 * de ahora voy a trabajar con la variable local.
	 * 
	 * 
	 */

}
