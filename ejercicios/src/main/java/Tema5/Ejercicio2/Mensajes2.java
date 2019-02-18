package Tema5.Ejercicio2;

/**
 * El código es incorrecto a propósito
 * @author DPA
 *
 */
public class Mensajes2 {

	private void mensajes() {
		//Almacenamos los mensajes en un array o buffer
		String[] mensajes = {"La vida es bella", 
				"O no...", 
				"Los pajaritos cantan", 
				"Y molestan..."};
		
		//Escribrimos cada mensaje por pantalla
		for (String mensaje : mensajes) {
			System.out.println(mensaje);
			
			/*Debe de esperarse a decir cada frase cada dos
			 * segundos
			 */
			Thread.sleep(2000);
			
		}
	}
	
	private void exec() {
	
		Thread t = new Thread(()->mensajes());
		t.start();
		t.join(1000);
		
	}

	
	public static void main(String[] args) {
		new Mensajes2().exec();
	}

	

}
