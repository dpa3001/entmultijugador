package Tema5.EjercicioCyclicBarrier;
/**
 * Construimos la estructura 
 * para la creación de 3 hilos
 * 
 * Pintamos las letras A y B
 * @author DPA
 *
 */
public class SincBarreraBasica2 {

	/**
	 * Método que va a pintar
	 * las letras 'A'x3 + '-' + 'B'x3
	 */
	private void proceso() {
		System.out.println("A");
		
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
		new SincBarreraBasica2().exec();

	}

}
