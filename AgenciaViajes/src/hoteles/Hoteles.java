package hoteles;

import ciudades.Ciudades;

public class Hoteles {
	/**
	 * Agencia de Viajes - Clase Hoteles
	 * 
	 * @author Robert G
	 * 
	 */
	Ciudades ciu;
	private String CodHotel;
	private String NombreHotel;
	private String Direccion;

	public Hoteles(Ciudades ciu, String codHotel, String nombreHotel, String direccion) {
		super();
		this.ciu = ciu;
		CodHotel = codHotel;
		NombreHotel = nombreHotel;
		Direccion = direccion;
	}

	public Ciudades getCiu() {
		return ciu;
	}

	public void setCiu(Ciudades ciu) {
		this.ciu = ciu;
	}

	public String getCodHotel() {
		return CodHotel;
	}

	public void setCodHotel(String codHotel) {
		CodHotel = codHotel;
	}

	public String getNombreHotel() {
		return NombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		NombreHotel = nombreHotel;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
}