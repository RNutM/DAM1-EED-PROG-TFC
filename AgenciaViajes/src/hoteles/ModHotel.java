
package hoteles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import principal.InicioAG;

public class ModHotel extends JFrame {
	/**
	 * Agencia de Viajes - Clase ModHotel
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static ModHotel frMoHo;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textDir;
	private JTextField textPrecio;
	private JTextField textCodigoCiudad;
	private JTextField textCiudad;

	/**
	 * Create the frame.
	 */
	public ModHotel() {
		setTitle("Modificación de hoteles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(45, 74, 90, 20);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				int num = 0;
				String nom = textNombre.getText();
				if (nom.isEmpty()) {// Compruebo que el campo no esta vacío y aviso
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacío");

				} else {

					try {

						nom = Character.toUpperCase(nom.charAt(0)) + nom.substring(1, nom.length());
						textNombre.setText(nom);// La muestro en mayuscula ya
						InicioAG.rset = AccesoDatos.ConsultaBD// Consulto si el nombre esta o no en la base de datos
						("SELECT count(*) FROM HOTELES WHERE (nombre_hotel) LIKE '" + nom + "'");

						while (InicioAG.rset.next())
							num = InicioAG.rset.getInt(1);

						if (num > 0) {// Si num es mayor que 0

							JOptionPane.showMessageDialog(null, "El hotel existe");

							textDir.setEditable(true);
							textPrecio.setEditable(true);

							InicioAG.rset = AccesoDatos
									.ConsultaBD("SELECT * FROM HOTELES WHERE (nombre_hotel) LIKE '" + nom + "'");

							while (InicioAG.rset.next()) {// Cojo el codigo y relleno los textField

								textCodigo.setText(InicioAG.rset.getString(1));
								textNombre.setText(InicioAG.rset.getString(2));
								textDir.setText(InicioAG.rset.getString(3));
								textPrecio.setText(InicioAG.rset.getString(4));
								textCodigoCiudad.setText(InicioAG.rset.getString(5));

							}
							String codCiudad = textCodigoCiudad.getText();
							InicioAG.rset = AccesoDatos.ConsultaBD(
									"SELECT NOMBRE_CIUDAD FROM CIUDADES WHERE (cod_ciudad) LIKE '" + codCiudad + "'");

							while (InicioAG.rset.next()) {
								textCiudad.setText(InicioAG.rset.getString(1));
							}
						} else {
							JOptionPane.showMessageDialog(null, "El hotel no existe");
							textCodigo.setText(null);
							textNombre.setText(null);
							textDir.setText(null);
							textPrecio.setText(null);
							textCodigoCiudad.setText(null);
							textCiudad.setText(null);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNombre.setBounds(151, 74, 200, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblFormularioDeBaja = new JLabel("Formulario de modificar hoteles");
		lblFormularioDeBaja.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeBaja.setBounds(92, 11, 250, 25);
		contentPane.add(lblFormularioDeBaja);

		JLabel labelDir = new JLabel("Direcci\u00F3n");
		labelDir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelDir.setBounds(45, 105, 90, 20);
		contentPane.add(labelDir);

		textDir = new JTextField();
		textDir.setEditable(false);
		textDir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textDir.setColumns(10);
		textDir.setBounds(151, 105, 200, 20);
		contentPane.add(textDir);

		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelPrecio.setBounds(45, 138, 90, 20);
		contentPane.add(labelPrecio);

		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPrecio.setColumns(10);
		textPrecio.setBounds(151, 138, 100, 20);
		contentPane.add(textPrecio);

		JLabel lblCodigoCiudad = new JLabel("C\u00F3digo Ciudad");
		lblCodigoCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoCiudad.setBounds(45, 167, 90, 20);
		contentPane.add(lblCodigoCiudad);

		textCodigoCiudad = new JTextField();
		textCodigoCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCodigoCiudad.setEditable(false);
		textCodigoCiudad.setColumns(10);
		textCodigoCiudad.setBounds(151, 167, 40, 20);
		contentPane.add(textCodigoCiudad);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCiudad.setBounds(205, 169, 50, 20);
		contentPane.add(lblCiudad);

		textCiudad = new JTextField();
		textCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCiudad.setEditable(false);
		textCiudad.setColumns(10);
		textCiudad.setBounds(265, 167, 125, 20);
		contentPane.add(textCiudad);

		JLabel lblCodigoHotel = new JLabel("C\u00F3digo Hotel");
		lblCodigoHotel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoHotel.setBounds(45, 46, 90, 20);
		contentPane.add(lblCodigoHotel);

		textCodigo = new JTextField();
		textCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(151, 46, 100, 20);
		contentPane.add(textCodigo);

		JButton btnAceptarYModificar = new JButton("Aceptar y Modificar");
		btnAceptarYModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nom = textNombre.getText();
				if (nom.isEmpty()) {// Compruebo que el campo no esta vacío y aviso

					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else {

					try {// Aviso al usuario si quiere continuar con la modificación
						int msj = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?");
						if (msj == JOptionPane.YES_OPTION) {// Si la opción es SI...
							// Hago el UPDATE en la BDD
							AccesoDatos.update(
									AccesoDatos.ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "agencia", "agencia"),
									"UPDATE hoteles SET COD_HOTEL = '" + textCodigo.getText() + "'" + ","
											+ "nombre_hotel = '" + textNombre.getText() + "'" + "," + "direccion = '"
											+ textDir.getText() + "'" + "," + "precio_hotel = " + textPrecio.getText()
											+ "WHERE COD_HOTEL LIKE '" + textCodigo.getText() + "'");

							JOptionPane.showMessageDialog(null, "Hotel modificado correctamente");
							// Desactivo los campos
							textCodigo.setEditable(false);
							textNombre.setEditable(true);
							textDir.setEditable(false);
							textPrecio.setEditable(false);
							// Borro los campos
							textCodigo.setText(null);
							textNombre.setText(null);
							textDir.setText(null);
							textPrecio.setText(null);
						}
						if (msj == JOptionPane.NO_OPTION) {// Si la opción es NO...

							Icon unIcono = null;// Creo una variable de tipo icono
							int si = JOptionPane.showOptionDialog// Creo mi propia ventana de mensaje personalizado
							(null, "¿Quieres que borre todos los campos?", "Pulsa SI, NO o Cancelar",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, unIcono,
									new Object[] { "SI", "NO", "Cancelar" }, "SI");

							if (JOptionPane.OK_OPTION == si) {// Borro todos los campos
								// Desactivo los campos
								textCodigo.setEditable(false);
								textNombre.setEditable(false);
								textDir.setEditable(false);
								textPrecio.setEditable(false);
								// Borro los campos
								textCodigo.setText(null);
								textNombre.setText(null);
								textDir.setText(null);
								textPrecio.setText(null);
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
		btnAceptarYModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptarYModificar.setBounds(45, 211, 140, 25);
		contentPane.add(btnAceptarYModificar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frMoHo.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
				// Desactivo los campos al pulser volver
				textDir.setEditable(false);
				textPrecio.setEditable(false);
				// Vacío los campos al pulsar volver
				textCodigo.setText(null);
				textNombre.setText(null);
				textDir.setText(null);
				textPrecio.setText(null);
				textCodigoCiudad.setText(null);
				textCiudad.setText(null);
			}
		});
		btnVolver.setBounds(253, 212, 135, 25);
		contentPane.add(btnVolver);
	}
}
