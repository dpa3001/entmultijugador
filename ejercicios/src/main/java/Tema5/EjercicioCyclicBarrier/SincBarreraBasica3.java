package Tema5.EjercicioCyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Por ahora nos olvidamos del guión
 * Vamos a implementar la sincronización
 * condicional. Hasta que no se pinten las
 * tres Aes no se pinten las letras Bs
 * 
 * Para eso utilizamos la sincronizacion
 * de barrera
 */
public class SincBarreraBasica3 {

	/**
	 * Método que va a pintar
	 * las letras 'A'x3 + '-' + 'B'x3
	 */
	private void proceso() {
		CyclicBarrier cb = new CyclicBarrier(3);
		
		System.out.println("A");
		
		cb.await();
		
		System.out.println("B");
	}
	
	/**
	 * Método que tiene que generar
	 * tres hilos
	 */
	public void exec() {
		for (int i = 0; i < 3; i++) {
			new Thread(()->proceso()).start();
		}
	}
	
	/**
	 * Método principal que va a llamar
	 * al método exec
	 * @param args
	 */
	public static void main(String[] args) {
		new SincBarreraBasica3().exec();

	}

}
