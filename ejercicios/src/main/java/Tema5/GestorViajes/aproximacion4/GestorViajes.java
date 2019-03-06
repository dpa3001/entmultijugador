package Tema5.GestorViajes.aproximacion4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		
		//¿ES NORMAL REPLICAR CÓDIGO?...NO ES LÓGICO
		List<Viaje> o = origenes.get(viaje.getOrigen());
		if (o == null) {
			o = new ArrayList<>();
			origenes.put(viaje.getOrigen(), o);
		}
		o.add(viaje);
		
		List<Viaje> d = destinos.get(viaje.getDestino());
		if (d == null) {
			d = new ArrayList<>();
			destinos.put(viaje.getDestino(), d);
		}
		d.add(viaje);
	}

	@Override
	public Collection<Viaje> getViajesOrigen(String ciudad) {
		List<Viaje> viajes = origenes.get(ciudad);
		if(viajes == null) {
			return Collections.emptyList();
		}else {
			return viajes;
		}
	}

	@Override
	public Collection<Viaje> getViajesDestino(String ciudad) {
		List<Viaje> viajes = destinos.get(ciudad);
		if(viajes == null) {
			return Collections.emptyList();
		}else {
			return viajes;
		}
	}

	@Override
	public Collection<Viaje> getViajes() {
		return viajes;
	}

	@Override
	public Collection<String> getCiudades() {
		return ciudades;
		//return Collections.unmodifiableCollection(ciudades);
		// Tiene penalización de rendimiento
	}

}
