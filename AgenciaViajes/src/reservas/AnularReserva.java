package reservas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import principal.AccesoDatos;

public class AnularReserva extends JFrame {
	/**
	 * Agencia de Viajes - Clase AnularReserva
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static AnularReserva frame6;
	private JTextField textDNI;
	private JTextField textViaje;

	/**
	 * Create the frame.
	 */
	public AnularReserva() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAnulacionDeReservas = new JLabel("Anulaci\u00F3n de reservas");
		lblAnulacionDeReservas.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblAnulacionDeReservas.setBounds(133, 11, 180, 25);
		contentPane.add(lblAnulacionDeReservas);

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(42, 61, 100, 20);
		contentPane.add(lblDNI);

		textDNI = new JTextField();
		textDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textDNI.setColumns(10);
		textDNI.setBounds(148, 61, 215, 20);
		contentPane.add(textDNI);

		JLabel lblViaje = new JLabel("C\u00F3digo Viaje");
		lblViaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblViaje.setBounds(42, 93, 100, 20);
		contentPane.add(lblViaje);

		textViaje = new JTextField();
		textViaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textViaje.setColumns(10);
		textViaje.setBounds(148, 93, 215, 20);
		contentPane.add(textViaje);

		JButton btnAceptarYAnular = new JButton("Aceptar y Anular");
		btnAceptarYAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = textDNI.getText();
				String codv = textViaje.getText();

				if (dni.isEmpty() || codv.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");

				} else {

					try {// Aviso al usuario si quiere continuar con la modificación
						int msj = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?");
						if (msj == JOptionPane.YES_OPTION) {// Si la opción es SI...

							// Hago el DELETE en la BDD
							AccesoDatos.delete(
									AccesoDatos.ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "agencia", "agencia"),
									"DELETE FROM reservas WHERE DNI LIKE '" + textDNI.getText() + "' and cod_viaje = "
											+ codv);

							textDNI.setText(null);// Borro todos los campos
							textViaje.setText(null);
						}
						if (msj == JOptionPane.NO_OPTION) {// Si la opción es NO...

							Icon unIcono = null;// Creo una variable de tipo icono
							int si = JOptionPane.showOptionDialog// Creo mi propia ventana de mensaje personalizado
							(null, "¿Quieres que borre todos los campos?", "Pulsa SI, NO o Cancelar",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, unIcono,
									new Object[] { "SI", "NO", "Cancelar" }, "SI");

							if (JOptionPane.OK_OPTION == si) {// Borro todos los campos
								textDNI.setText(null);
								textViaje.setText(null);

							} else {
								JOptionPane.showMessageDialog(null, "Esta bien no borro nada");
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAceptarYAnular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptarYAnular.setBounds(42, 240, 135, 25);
		contentPane.add(btnAceptarYAnular);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame6.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(250, 240, 135, 25);
		contentPane.add(btnVolver);
	}
}
