package Tema5.FabricaCoches;

public class Robot {

	private Almacen almacen;
	private int numPiezas;
	private int numRobot;

	public Robot(int numRobot, int numPiezas, Almacen almacen) {
		this.numRobot = numRobot;
		this.numPiezas = numPiezas;
		this.almacen = almacen;
	}

	public void iniciar() {
		new Thread(() -> fabricar()).start();
	}

	public void fabricar() {
		try {
			while (true) {
				for (int i = 0; i < numPiezas; i++) {

					double pieza = almacen.recogerPieza(i);
					montarPieza(i, pieza);
				}
				System.out.println("[ROBOT " + numRobot + "] ha montado coche.");
			}
		} catch (InterruptedException e) {
			return;
		}
	}

	public void montarPieza(int tipoPieza, double pieza) {
		try {
			Thread.sleep((long) (Math.random() * 200));
		} catch (InterruptedException e) {
		}
		System.out.println("Pieza de tipo " + tipoPieza + " montada");
	}

}
