package Tema5.Ejercicio2;

public class Mensajes3 {

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
			 * 
			 * Da error de compilación --> debo de capturar la
			 * excepción try-catch
			 */
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				//Finalizo
				System.out.println("Se acabó");
				return;
			}
			
		}
	}
	
	private void exec() {
	
		//Podemos poner un nombre al paso de mensajes
		Thread t = new Thread(()->mensajes(),"mensajes");
		t.start();
		
		/*También da un error de compilación
		 * capturamos la excepción
		 */
		
		try {
			t.join(1000);
		} catch (InterruptedException e) {
			//No hacemos nada porque es el main del programa
		}
		
	}

	
	public static void main(String[] args) {
		new Mensajes3().exec();
	}

	

}
