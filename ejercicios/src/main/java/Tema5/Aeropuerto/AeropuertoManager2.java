package Tema5.Aeropuerto;

import java.util.HashMap;
import java.util.Map;
import Tema5.Aeropuerto.Aeropuerto;

public class AeropuertoManager2 {

	
	public static void main(String[] args) {
	
		Map<String,Aeropuerto> aeropuertos = new HashMap<>();
		addAeropuerto(aeropuertos, new Aeropuerto("Barajas","Madrid",10));
		addAeropuerto(aeropuertos, new Aeropuerto("El Prat","Barcelona",8));
		addAeropuerto(aeropuertos, new Aeropuerto("Fantasma","Castellón",5));
		
		System.out.println(aeropuertos.get("Barajas"));
	}

	/**
	 * Con este método evitamos duplicados a la hora de insertar la clave
	 * @param aeropuertos
	 * @param e
	 */
	public static void addAeropuerto(Map<String, Aeropuerto> aeropuertos, Aeropuerto e) {
		aeropuertos.put(e.getNombre(), e);
	}
}
