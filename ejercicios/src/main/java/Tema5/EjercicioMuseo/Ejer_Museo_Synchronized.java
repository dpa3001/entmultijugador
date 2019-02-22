package Tema5.EjercicioMuseo;

public class Ejer_Museo_Synchronized {

	private volatile int personas = 0;

	public void persona() {

		while (true) {

			boolean regalo = false;
			synchronized (this) {

				if (personas == 0) {
					regalo = true;
				}
				personas++;
				System.out.println("hola, somos " + personas);
			}

			if (regalo) {
				System.out.println("Tengo regalo");
			} else {
				System.out.println("No tengo regalo");
			}

			System.out.println("qué bonito!");
			System.out.println("alucinante!");

			synchronized (this) {

				personas--;
				System.out.println("adiós a los " + personas);
			}

			System.out.println("paseo");
		}
	}

	public void exec() {
		for (int i = 0; i < 3; i++) {
			new Thread(() -> persona()).start();
		}
	}

	public static void main(String[] args) {
		new Ejer_Museo_Synchronized().exec();
	}
	
	/*
	 * El código queda mucho más compacto con synchronized, 
	 * pero a cambio perdemos la versatilidad que nos ofrece
	 * el cerrojo, puesto que no se pueden realizar ciertas
	 * operaciones de grado fino tales como: tiempo de espera,
	 * justicia, etc.
	 * 
	 */
}
