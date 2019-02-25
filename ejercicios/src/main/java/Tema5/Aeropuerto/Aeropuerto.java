package Tema5.Aeropuerto;

public class Aeropuerto {

	private String nombre;
	private String ciudad;
	private int numPistas;
	
	public Aeropuerto(String nombre, String ciudad, int numPistas) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.numPistas = numPistas;
	}
	@Override
	public String toString() {
		return "Aeropuerto [nombre=" + nombre + ", ciudad=" + ciudad + ", numPistas=" + numPistas + "]";
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getNumPistas() {
		return numPistas;
	}
	public void setNumPistas(int numPistas) {
		this.numPistas = numPistas;
	}
}
