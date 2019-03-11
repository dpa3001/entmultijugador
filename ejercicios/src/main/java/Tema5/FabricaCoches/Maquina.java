package Tema5.FabricaCoches;

public class Maquina {

	private Almacen almacen;
	private int tipoPieza;

	public Maquina(int tipoPieza, Almacen almacen) {
		this.tipoPieza = tipoPieza;
		this.almacen = almacen;
	}

	public void iniciar() {
		new Thread(() -> fabricar()).start();
	}

	public void fabricar() {
		try {
			while (true) {
				double pieza = fabricarPieza();
				System.out.println("Pieza de tipo " + tipoPieza + " fabricada: " + pieza);
				this.almacen.almacenarPieza(tipoPieza, pieza);
			}
		} catch (InterruptedException e) {
			return;
		}
	}

	public double fabricarPieza() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		return Math.random();
	}

}
