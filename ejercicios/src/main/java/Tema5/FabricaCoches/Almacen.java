package Tema5.FabricaCoches;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Almacen {

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
