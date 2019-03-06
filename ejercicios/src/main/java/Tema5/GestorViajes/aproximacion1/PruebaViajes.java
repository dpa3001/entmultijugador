package Tema5.GestorViajes.aproximacion1;

import Tema5.GestorViajes.aproximacion1.GestorViajes;
import Tema5.GestorViajes.aproximacion1.Viaje;

public class PruebaViajes {

	public static void main(String[] args) {
		GestorViajes gestor = new GestorViajes();

		gestor.insertarViaje(new Viaje("Madrid", "Barcelona", 1));
		gestor.insertarViaje(new Viaje("Madrid", "París", 2));
		gestor.insertarViaje(new Viaje("Barcelona", "Castellón", 1));
		gestor.insertarViaje(new Viaje("Madrid", "New York", 6));
		gestor.insertarViaje(new Viaje("París", "Barcelona", 1));

		// Presentamos por pantalla las diferentes consultas
		System.out.println("Viajes con origen Madrid: " 
				+ gestor.getViajesOrigen("Madrid"));
		
		System.out.println("Viajes con destino Madrid: " 
				+ gestor.getViajesDestino("Barcelona"));
		
		System.out.println("Ciudaddes: " 
				+ gestor.getCiudades());
		
		System.out.println("Viajes: "
				+ gestor.getViajes());

	}

}
