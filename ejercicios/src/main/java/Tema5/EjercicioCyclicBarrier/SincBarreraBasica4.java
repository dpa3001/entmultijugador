package Tema5.EjercicioCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Manejamos lo primero la excepcion
 * del CyclicBarrier con un código 
 * try/catch
 * 
 * ¿ESTÁ BIEN ESTE CÓDIGO?...PROBAR EL MAIN
 */
public class SincBarreraBasica4 {

	/**
	 * Método que va a pintar
	 * las letras 'A'x3 + '-' + 'B'x3
	 */
	private void proceso() {
		CyclicBarrier cb = new CyclicBarrier(3);
		
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
		new SincBarreraBasica4().exec();

	}

}
