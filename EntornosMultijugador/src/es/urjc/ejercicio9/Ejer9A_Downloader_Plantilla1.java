package es.urjc.ejercicio9;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer9A_Downloader_Plantilla1 {
	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;

	private static volatile int[] fichero = new int[N_FRAGMENTOS];

	// Add the attributes you need
	private static volatile int sigFragDescarga;
	private static volatile boolean hayFragmentos;

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
		while (hayFragmentos) {

			// El primer proceso que llegue coge el primer fragmento
			// El segundo proceso que llegue coge el segundo fragmento
			// Así sucesivamente...
			// El que termine antes, preguntará ¿Qué fragmento me toca?

			// --> Llevar un control de pendientes de descargas y empezamos por el ppo
			// sigFragDescarga = 0;

			/*
			 * Necesitamos informar a los demás procesos de qué fragmento me estoy ocupando
			 */
			int datos = descargaDatos(sigFragDescarga);

			fichero[sigFragDescarga] = datos;

			sigFragDescarga++;

			hayFragmentos = sigFragDescarga < N_FRAGMENTOS;

			// ¿EST�? BIEN ESTE CÓDIGO?*
		}
		// Mientras hay fragmentos que descargar...

		// Descargar los datos del siguiente fragmento

		// Almacenar los datos en el array
	}

	public static void main(String[] args) {

		createThreads(N_HILOS, "downloader");

		startThreadsAndWait();

		mostrarFichero();
	}

	// * NO, PORQUE TE EST�?N TOCANDO LA VARIABLE

}
