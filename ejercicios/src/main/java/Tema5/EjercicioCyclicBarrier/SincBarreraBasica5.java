package Tema5.EjercicioCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ¿Cuántos objetos CyclicBarrier hay en nuestro programa?
 * La respuesta es MUCHOS...eso implica que no se van a sincronizar 
 * correctamente. Por lo tanto, sacamos del método proceso la variable
 * cb - CyclicBarrier para que sea única y compartida por todos los hilos
 * 
 * ¿ESTÁ BIEN ESTE CÓDIGO?...PROBAR EL MAIN
 * 
 * RECUERDA: Falta pintar entre las Aes y las Bes un guión
 */
public class SincBarreraBasica5 {

	CyclicBarrier cb = new CyclicBarrier(3);
	
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
		new SincBarreraBasica5().exec();

	}

}
