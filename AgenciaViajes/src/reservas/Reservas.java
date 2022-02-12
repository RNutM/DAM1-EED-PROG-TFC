package reservas;

import clientes.Clientes;
import principal.Viajes;

public class Reservas {
	/**
	 * Agencia de Viajes - Clase Reservas
	 * 
	 * @author Robert G
	 * 
	 */
	Clientes cli;
	Viajes via;
	private int NumeroPersonas;
	private String CodReserva;
	private float PrecioTotal;

	public Reservas(Clientes cli, Viajes via, int numeroPersonas, String codReserva, float precioTotal) {
		super();
		this.cli = cli;
		this.via = via;
		NumeroPersonas = numeroPersonas;
		CodReserva = codReserva;
		PrecioTotal = precioTotal;
	}

	public Clientes getCli() {
		return cli;
	}

	public void setCli(Clientes cli) {
		this.cli = cli;
	}

	public Viajes getVia() {
		return via;
	}

	public void setVia(Viajes via) {
		this.via = via;
	}

	public int getNumeroPersonas() {
		return NumeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		NumeroPersonas = numeroPersonas;
	}

	public String getCodReserva() {
		return CodReserva;
	}

	public void setCodReserva(String codReserva) {
		CodReserva = codReserva;
	}

	public float getPrecioTotal() {
		return PrecioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		PrecioTotal = precioTotal;
	}
}