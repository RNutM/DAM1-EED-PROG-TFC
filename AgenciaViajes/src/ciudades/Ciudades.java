package ciudades;

import java.util.*;

import hoteles.Hoteles;
import principal.Viajes;

public class Ciudades {
	/**
	 * Agencia de Viajes - Clase Ciudades
	 * 
	 * @author Robert G
	 * 
	 */
	Collection<Hoteles> hot;
	Collection<Viajes> via;
	private String CodCiudad;
	private String NombreCiudad;
	private String País;

	public Ciudades(Collection<Hoteles> hot, Collection<Viajes> via, String codCiudad, String nombreCiudad,
			String país) {
		super();
		this.hot = hot;
		this.via = via;
		CodCiudad = codCiudad;
		NombreCiudad = nombreCiudad;
		País = país;
	}

	public Ciudades() {
		super();
	}

	public Collection<Hoteles> getHot() {
		return hot;
	}

	public void setHot(Collection<Hoteles> hot) {
		this.hot = hot;
	}

	public Collection<Viajes> getVia() {
		return via;
	}

	public void setVia(Collection<Viajes> via) {
		this.via = via;
	}

	public String getCodCiudad() {
		return CodCiudad;
	}

	public void setCodCiudad(String codCiudad) {
		CodCiudad = codCiudad;
	}

	public String getNombreCiudad() {
		return NombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		NombreCiudad = nombreCiudad;
	}

	public String getPaís() {
		return País;
	}

	public void setPaís(String país) {
		País = país;
	}
}