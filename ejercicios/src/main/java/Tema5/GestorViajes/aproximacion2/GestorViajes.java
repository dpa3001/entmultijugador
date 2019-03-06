package Tema5.GestorViajes.aproximacion2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestorViajes implements MiGestorViajes{
	
	private List<Viaje> viajes = new ArrayList<>();
	private Set<String> ciudades = new HashSet<>();
	private Map<String,List<Viaje>> origenes = new HashMap<>();
	private Map<String,List<Viaje>> destinos = new HashMap<>();

	@Override
	public void insertarViaje(Viaje viaje) {
		/*Habrá que añadir un viaje a todas las estructuras de datos*/
		viajes.add(viaje);
		
		ciudades.add(viaje.getOrigen());
		ciudades.add(viaje.getDestino());
		
		//¿QUÉ OCURRE CON LOS MAPAS?
		origenes.put(viaje.getOrigen(), viaje);
		
	}

	@Override
	public List<Viaje> getViajesOrigen(String viaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Viaje> getViajesDestino(String viaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Viaje> getViajes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCiudades() {
		// TODO Auto-generated method stub
		return null;
	}

}
