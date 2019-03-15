package Tema5.FabricaRobots;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Ejercicio16 {

	private final static int NUM_TIPOS_PIEZAS = 4;
	private final static int NUM_ROBOTS = 5;
	private final static int MAX_PIEZAS = 10;

	private void exec() {

		Almacen almacen = new Almacen(NUM_TIPOS_PIEZAS, 
				MAX_PIEZAS);

		for (int i = 0; i < NUM_TIPOS_PIEZAS; i++) {
			new Maquina(i, almacen).iniciar();
		}

		for (int i = 0; i < NUM_ROBOTS; i++) {
			new Robot(i, NUM_TIPOS_PIEZAS, almacen).iniciar();
		}
	}

	public static void main(String[] args) {
		new Ejercicio16().exec();
	}
}

class Robot {

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

class Maquina {

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

class Almacen {

	private List<BlockingQueue<Double>> datos;

	public Almacen(int numTiposPiezas, int maxPiezas) {
		this.datos = new ArrayList<>();
		for (int i = 0; i < numTiposPiezas; i++) {
			this.datos.add(new ArrayBlockingQueue<>(maxPiezas));
		}
	}

	public double recogerPieza(int tipo) throws InterruptedException {
		return this.datos.get(tipo).take();
	}

	public void almacenarPieza(int tipo, double pieza) throws InterruptedException {
		this.datos.get(tipo).put(pieza);
	}

}
