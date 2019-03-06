package Tema5.GestorViajes.aproximacion1;

import java.util.List;

public interface MiGestorViajes {

	/**
	 * Método que inserta un viaje
	 * @param viaje
	 */
	public void insertarViaje (Viaje viaje);
	
	/**
	 * Método que devuelve todos los viajes 
	 * que tienen una determinada ciudad de origen
	 * @param viaje
	 * @return Devuelve la colección o lista de viajes
	 */
	public List<Viaje> getViajesOrigen(String viaje);
	
	/**
	 * Método que devuelve todos los viajes 
	 * que tienen una determinada ciudad de destino
	 * @param viaje
	 * @return Devuelve la colección o lista de viajes
	 */
	public List<Viaje> getViajesDestino(String viaje);
	
	/**
	 * Método que devuelve todos los viajes
	 * @param viaje
	 * @return Colección o lista de viajes
	 */
	public List<Viaje> getViajes();
	
	/**
	 * Método que devuelve todas las ciuidades en las 
	 * que hay viajes
	 * @return Colección o lista de ciudades
	 */
	public List<String> getCiudades();
	
	
}
