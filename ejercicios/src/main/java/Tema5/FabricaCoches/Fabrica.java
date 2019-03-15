package Tema5.FabricaCoches;

public class Fabrica {

	// Nos generamos las constantes del ejercicio
	private final static int NUM_TIPOS_PIEZAS = 4;
	private final static int NUM_ROBOTS = 5;
	private final static int MAX_PIEZAS = 10;

	private void exec() {

		Almacen almacen = new Almacen(NUM_TIPOS_PIEZAS, MAX_PIEZAS);

		for (int i = 0; i < NUM_TIPOS_PIEZAS; i++) {
			new Maquina(i, almacen).iniciar();
		}

		for (int i = 0; i < NUM_ROBOTS; i++) {
			new Robot(i, NUM_TIPOS_PIEZAS, almacen).iniciar();
		}
	}

	public static void main(String[] args) {
		new Fabrica().exec();
	}

}
