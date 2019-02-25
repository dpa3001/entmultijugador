package Tema5.Aeropuerto;

import java.util.HashMap;
import java.util.Map;
import Tema5.Aeropuerto.Aeropuerto;

public class AeropuertoManager {

	
	public static void main(String[] args) {
	
		Map<String,Aeropuerto> aeropuertos = new HashMap<>();
		aeropuertos.put("Barajas", new Aeropuerto("Barajas","Madrid",10));
		aeropuertos.put("El Prat", new Aeropuerto("El Prat","Barcelona",8));
		aeropuertos.put("Fantasma", new Aeropuerto("Fantasma","Castellón",5));
	}
}
