package clientes;

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

public class ModCliente extends JFrame {
	/**
	 * Agencia de Viajes - Clase ModCliente
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static ModCliente frMoCl;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textTelefono;

	/**
	 * Create the frame.
	 */
	public ModCliente() {
		setTitle("Modificaci�n de clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFormularioDeModificacin = new JLabel("Formulario de modificaci\u00F3n de clientes");
		lblFormularioDeModificacin.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeModificacin.setBounds(58, 11, 310, 25);
		contentPane.add(lblFormularioDeModificacin);

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(44, 61, 100, 20);
		contentPane.add(lblDNI);

		textDNI = new JTextField();
		textDNI.setToolTipText("Introduzca un DNI y comprobar\u00E9 si existe en nuestra Base de datos");
		textDNI.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				int num = 0;
				String dni = textDNI.getText();
				if (dni.isEmpty()) {// Compruebo que el campo esta vac�o y aviso
					// JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else if (Clientes.valido(dni)) {

					try {
						dni = dni.substring(0, 9).toUpperCase();// Paso el ultimo digito del dni a mayuscula que es la
																// letra
						textDNI.setText(dni);// La muestro en mayuscula ya
						InicioAG.rset = AccesoDatos.ConsultaBD// Consulto si el codigo esta o no en la base de datos
						("SELECT count(*) FROM CLIENTES WHERE (DNI) LIKE '" + dni + "'");

						while (InicioAG.rset.next())
							num = InicioAG.rset.getInt(1);
						// System.out.println(num);//Compruebo en consola si hay 1 o 0

						if (num > 0) {// Si num es mayor que 0

							JOptionPane.showMessageDialog(null, "El DNI existe");
							InicioAG.rset = AccesoDatos
									.ConsultaBD("SELECT * FROM CLIENTES WHERE (DNI) LIKE '" + dni + "'");
							// Activo los campos
							textNombre.setEditable(true);
							textApellidos.setEditable(true);
							textTelefono.setEditable(true);
							while (InicioAG.rset.next()) {
								// Relleno los campos
								textDNI.setText(InicioAG.rset.getString(1));
								textNombre.setText(InicioAG.rset.getString(2));
								textApellidos.setText(InicioAG.rset.getString(3));
								textTelefono.setText(InicioAG.rset.getString(4));
							}
						} else {
							JOptionPane.showMessageDialog(null, "El DNI no Existe");
							textDNI.setText(null);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textDNI.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textDNI.setColumns(10);
		textDNI.setBounds(150, 61, 215, 20);
		contentPane.add(textDNI);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(44, 93, 100, 20);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textNombre.setColumns(10);
		textNombre.setBounds(150, 93, 215, 20);
		contentPane.add(textNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(44, 125, 100, 20);
		contentPane.add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setEditable(false);
		textApellidos.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textApellidos.setColumns(10);
		textApellidos.setBounds(150, 126, 215, 20);
		contentPane.add(textApellidos);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefono.setBounds(44, 157, 100, 20);
		contentPane.add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setEditable(false);
		textTelefono.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textTelefono.setColumns(10);
		textTelefono.setBounds(150, 157, 215, 20);
		contentPane.add(textTelefono);

		JButton btnAceptarYModificar = new JButton("Aceptar y Modificar");
		btnAceptarYModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dni = textDNI.getText();
				if (dni.isEmpty()) {// Compruebo que el campo esta vac�o y aviso
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else {

					try {// Aviso al usuario si quiere continuar con la modificaci�n
						int msj = JOptionPane.showConfirmDialog(null, "�Esta seguro de continuar?");
						if (msj == JOptionPane.YES_OPTION) {// Si la opci�n es SI...
							// Hago el UPDATE en la BDD
							AccesoDatos.update(
									AccesoDatos.ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "agencia", "agencia"),
									"UPDATE clientes SET DNI = '" + textDNI.getText() + "'" + "," + "nombre_cli= '"
											+ textNombre.getText() + "'" + "," + "apellidos= '"
											+ textApellidos.getText() + "'" + "," + "telefono = "
											+ textTelefono.getText() + "WHERE DNI LIKE '" + textDNI.getText() + "'");

							JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
							// Desactivo los campos
							textNombre.setEditable(false);
							textApellidos.setEditable(false);
							textTelefono.setEditable(false);
							// Borro los campos
							textDNI.setText(null);
							textNombre.setText(null);
							textApellidos.setText(null);
							textTelefono.setText(null);
						}
						if (msj == JOptionPane.NO_OPTION) {// Si la opci�n es NO...

							Icon unIcono = null;// Creo una variable de tipo icono
							int si = JOptionPane.showOptionDialog// Creo mi propia ventana de mensaje personalizado
							(null, "�Quieres que borre todos los campos?", "Pulsa SI, NO o Cancelar",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, unIcono,
									new Object[] { "SI", "NO", "Cancelar" }, "SI");

							if (JOptionPane.OK_OPTION == si) {// Borro todos los campos
								// Desactivo los campos
								textNombre.setEditable(false);
								textApellidos.setEditable(false);
								textTelefono.setEditable(false);
								// Borro los campos
								textDNI.setText(null);
								textNombre.setText(null);
								textApellidos.setText(null);
								textTelefono.setText(null);
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
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frMoCl.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(253, 212, 135, 25);
		contentPane.add(btnVolver);
	}
}
