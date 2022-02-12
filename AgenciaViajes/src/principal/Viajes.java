package principal;

import java.util.*;

import ciudades.Ciudades;
import medios.MediosTransporte;
import reservas.Reservas;

public class Viajes {
	/**
	 * Agencia de Viajes - Clase Viajes
	 * 
	 * @author Robert G
	 * 
	 */
	Ciudades ciu;
	MediosTransporte med;
	Collection<Reservas> reservas;
	private String Cod_Viaje;
	private int PrecioViaje;

	public Viajes(Ciudades ciu, MediosTransporte med, Collection<Reservas> reservas, String cod_Viaje,
			int precioViaje) {
		super();
		this.ciu = ciu;
		this.med = med;
		this.reservas = reservas;
		Cod_Viaje = cod_Viaje;
		PrecioViaje = precioViaje;
	}

	public Ciudades getCiu() {
		return ciu;
	}

	public void setCiu(Ciudades ciu) {
		this.ciu = ciu;
	}

	public MediosTransporte getMed() {
		return med;
	}

	public void setMed(MediosTransporte med) {
		this.med = med;
	}

	public Collection<Reservas> getReservas() {
		return reservas;
	}

	public void setReservas(Collection<Reservas> reservas) {
		this.reservas = reservas;
	}

	public String getCod_Viaje() {
		return Cod_Viaje;
	}

	public void setCod_Viaje(String cod_Viaje) {
		Cod_Viaje = cod_Viaje;
	}

	public int getPrecioViaje() {
		return PrecioViaje;
	}

	public void setPrecioViaje(int precioViaje) {
		PrecioViaje = precioViaje;
	}
}