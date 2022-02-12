package medios;

import java.util.Collection;

import principal.Viajes;

public class MediosTransporte {
	/**
	 * Agencia de Viajes - Clase MediosTransporte
	 * 
	 * @author Robert G
	 * 
	 */
	Collection<Viajes> via;
	private String CodMedio;
	private String NombreMedio;

	public MediosTransporte(Collection<Viajes> via, String codMedio, String nombreMedio) {
		super();
		this.via = via;
		CodMedio = codMedio;
		NombreMedio = nombreMedio;
	}

	public Collection<Viajes> getVia() {
		return via;
	}

	public void setVia(Collection<Viajes> via) {
		this.via = via;
	}

	public String getCodMedio() {
		return CodMedio;
	}

	public void setCodMedio(String codMedio) {
		CodMedio = codMedio;
	}

	public String getNombreMedio() {
		return NombreMedio;
	}

	public void setNombreMedio(String nombreMedio) {
		NombreMedio = nombreMedio;
	}
}