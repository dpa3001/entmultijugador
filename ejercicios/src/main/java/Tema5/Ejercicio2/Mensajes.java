package Tema5.Ejercicio2;

/**
 * Este código es incorrecto a propósito
 * @author DPA
 *
 */
public class Mensajes {

	private void mensajes() {
		
	}
	
	private void exec() {
	
		Thread t = new Thread(()->mensajes());
		t.start();
		t.join();
		
	}

	
	public static void main(String[] args) {
		new Mensajes().exec();
	}

	

}
