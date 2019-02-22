package Tema5.EjercicioCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Creamos un método guión y colocamos su código
 * dentro de proceso.
 *  
 * RECUERDA: Revisa uno de los constructores
 * de CyclicBarrier. Ahí está la respuesta
 */
public class SincBarreraBasica6 {

	CyclicBarrier cb = new CyclicBarrier(3,()->guion());
	
	/**
	 * Generamos un método guión para pintarlo
	 */
	private void guion() {
		System.out.println("-");
	}
	
	/**
	 * Método que va a pintar
	 * las letras 'A'x3 + '-' + 'B'x3
	 */
	private void proceso() {
		
		System.out.println("A");
		
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
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
		new SincBarreraBasica6().exec();

	}

}
