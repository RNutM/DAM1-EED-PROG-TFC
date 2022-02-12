package principal;

public class NIF {
	/**
	 * Agencia de Viajes - Clase NIF
	 * 
	 * @author Robert G
	 * 
	 */
	public static String letras[] = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S",
			"Q", "V", "H", "L", "C", "K", "E" };

	// Método para obtener la letra correcta de un DNI
	public static String obtenerletra(int numero) {
		int resto = numero % 23;
		// System.out.println("La letra es: "+letras[resto]);
		return letras[resto];
	}
}