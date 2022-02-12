package clientes;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import reservas.Reservas;

public class Clientes {
	/**
	 * Agencia de Viajes - Clase Clientes
	 * 
	 * @author Robert G
	 * 
	 */
	Collection<Reservas> reservas;
	private String DNI;
	private String Nombre;
	private String Apellidos;
	private int Telefono;

	public Clientes(Collection<Reservas> reservas, String dNI, String nombre, String apellidos, int telefono) {
		super();
		this.reservas = reservas;
		DNI = dNI;
		Nombre = nombre;
		Apellidos = apellidos;
		Telefono = telefono;
	}

	public Collection<Reservas> getReservas() {
		return reservas;
	}

	public void setReservas(Collection<Reservas> reservas) {
		this.reservas = reservas;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public int getTelefono() {
		return Telefono;
	}

	public void setTelefono(int telefono) {
		Telefono = telefono;
	}

	// Método para validar si un DNI es correcto o no
	public static boolean valido(String nif) {

		boolean correcto = false;
		// ("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		// ("^[0-9]{8}[T|R|W|A|G|M|Y|F|P|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E]$");
		// Expresiones regulares
		Matcher matcher = pattern.matcher(nif);

		if (matcher.matches()) {

			String letra = matcher.group(2);// letra group 2 es la parte letra

			String letras = "TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke";

			int index = Integer.parseInt(matcher.group(1));// index group 1 es la parte numérica

			index = index % 23;// Hallamos el resto de dividir index entre 23

			String reference = letras.substring(index, index + 1);

			if (reference.equalsIgnoreCase(letra)) {// Para admitir tanto mays como minus
				correcto = true;
			} else {// System.out.println("Letra del dni incorrecta");
				JOptionPane.showMessageDialog(null, "La letra del DNI es incorrecta");
				correcto = false;
			}
		} else {// System.out.println("Formato incorrecto");
			JOptionPane.showMessageDialog(null, "El formato es incorrecto");
			correcto = false;
		}
		return correcto;
	}// Fin método validar dni

	// Método para impedir la entrada de datos String en int a traves de interface valida
	public static boolean valido2(String ed) {

		@SuppressWarnings("unused") // Para que entero no me de aviso de que no lo he usado todavía
		int entero = 0;
		boolean error = true;
		try {
			entero = Integer.parseInt(ed);
		} catch (NumberFormatException e1) {
			error = false;
			JOptionPane.showMessageDialog(null, "Error en el formato del número, intentelo de nuevo");
			// System.out.println("Error en el formato del número, intentelo de nuevo");
		} catch (Exception e1) {
			error = false;
			JOptionPane.showMessageDialog(null, "Error en el formato del número, intentelo de nuevo");
			// System.out.println("Error en el formato del número, intentelo de nuevo");
		}
		return error;
	}// Fin de método valido
}