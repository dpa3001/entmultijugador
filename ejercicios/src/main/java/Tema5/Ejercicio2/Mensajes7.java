package Tema5.Ejercicio2;

public class Mensajes7 {
	
	/*
	 * Ahora debemos de modificar en el hilo principal
	 * el Thread que está esperando a que acabe después de 
	 * 5 segundos
	 * 
	 * Además, hay que presentar por pantalla el nombre del 
	 * Thread activo
	 *
	 */

	//Generamos un método para imprimir por pantalla
	private void println(String texto) {
		System.out.println(Thread.currentThread() + ":" + texto +"\n");
	}
	
	private void mensajes() {
		//Almacenamos los mensajes en un array o buffer
		String[] mensajes = {"La vida es bella", 
				"O no...", 
				"Los pajaritos cantan", 
				"Y molestan..."};
		
		//Escribrimos cada mensaje por pantalla
		for (String mensaje : mensajes) {
			println(mensaje);
			
			/*Debe de esperarse a decir cada frase cada dos
			 * segundos
			 * 
			 * Da error de compilación --> debo de capturar la
			 * excepción try-catch
			 */
			
			try {
				/*NOTA IMPORTANTE: ahora modificamos para que le
				 * de tiempo a terminar la ejecución. Modificamos 
				 * el sleep a 500 ms y su comportamiento CAMBIA.
				 */
				Thread.sleep(500);
			} catch (InterruptedException e) {
				//Finalizo
				println("Se acabó");
				return;
			}
			
		}
	}
	
	private void exec() {
	
		//Podemos poner un nombre al paso de mensajes
		Thread t = new Thread(()->mensajes(),"MensajesThread");
		t.start();
		
		/*También da un error de compilación
		 * capturamos la excepción
		 */
		
		try {
			/* Modificamos join(5000) por un bucle con un 
			 * join de un segundo dentro de un bucle
			 */
			for (int i = 0; i < 5; i++) {
				t.join(1000);
				println("Todavía esperando...");
			}
			
			
			//Entonces
			if (t.isAlive()) {
				println("Cansado de esperar!");
				t.interrupt();
				t.join();
				println("Por fin");
			}
		} catch (InterruptedException e) {
			//No hacemos nada porque es el main del programa
		}
		
	}

	
	public static void main(String[] args) {
		new Mensajes7().exec();
	}

	

}
