package medios;

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

public class AltaMedio extends JFrame {
	/**
	 * Agencia de Viajes - Clase AltaMedio
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static AltaMedio frAlMe;
	private JTextField textCodigo;
	private JTextField textNombre;

	/**
	 * Create the frame.
	 */
	public AltaMedio() {
		setTitle("Alta de medios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAceptarYGuardar = new JButton("Aceptar y Guardar");
		btnAceptarYGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {// Aviso al usuario si quiere continuar con la inserción
					int msj = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?");
					if (msj == JOptionPane.YES_OPTION) {// Si la opción es SI...

						// Hago el INSERT en la BDD
						AccesoDatos.insert(
								AccesoDatos.ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "agencia", "agencia"),
								"INSERT INTO mediostransporte(COD_MEDIO,NOMBRE_MEDIO) VALUES('" + textCodigo.getText()
										+ "','" + textNombre.getText() + "')");

						JOptionPane.showMessageDialog(null, "Medio añadido correctamente");
						textCodigo.setText(null);
						textNombre.setText(null);
					}
					if (msj == JOptionPane.NO_OPTION) {// Si la opción es NO...

						Icon unIcono = null;// Creo una variable de tipo icono
						int si = JOptionPane.showOptionDialog// Creo mi propia ventana de mensaje personalizado
						(null, "¿Quieres que borre todos los campos?", "Pulsa SI, NO o Cancelar",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, unIcono,
								new Object[] { "SI", "NO", "Cancelar" }, "SI");

						if (JOptionPane.OK_OPTION == si) {// Borro todos los campos
							textCodigo.setText(null);
							textNombre.setText(null);
							btnAceptarYGuardar.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "Esta bien no borro nada");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptarYGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptarYGuardar.setEnabled(false);
		btnAceptarYGuardar.setBounds(43, 211, 135, 25);
		contentPane.add(btnAceptarYGuardar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Borro todos los campos al entrar
				textNombre.setText(null);
				frAlMe.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(251, 212, 135, 25);
		contentPane.add(btnVolver);

		textCodigo = new JTextField();
		textCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try {
					InicioAG.rset = AccesoDatos.ConsultaBD// Con esta consulta pongo el código máximo existente +1
					("SELECT MAX(COD_MEDIO)+1 FROM MEDIOSTRANSPORTE");
					while (InicioAG.rset.next()) {// Cojo el codigo y lo pongo en el textField
						textCodigo.setText(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textCodigo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(148, 61, 215, 20);
		contentPane.add(textCodigo);

		textNombre = new JTextField();
		textNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int num = 0;
				String nommed = textNombre.getText();

				if (nommed.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacio");

				} else {
					nommed = Character.toUpperCase(nommed.charAt(0)) + nommed.substring(1, nommed.length());
					textNombre.setText(nommed);// La muestro en mayuscula ya
					btnAceptarYGuardar.setEnabled(true);// Activo el boton Aceptar
				}

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD// Compruebo que exista o no el nombre
					("SELECT count(*) FROM MEDIOSTRANSPORTE WHERE (NOMBRE_MEDIO) LIKE '" + nommed + "'");
					while (InicioAG.rset.next()) {
						num = InicioAG.rset.getInt(1);
						if (num > 0) {// Si num es mayor que 0

							JOptionPane.showMessageDialog(null, "El nombre ya existe, por favor introduce otro");
							textNombre.setText(null);
							btnAceptarYGuardar.setEnabled(false);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textNombre.setColumns(10);
		textNombre.setBounds(148, 93, 215, 20);
		contentPane.add(textNombre);

		JLabel lblFormularioDeAlta = new JLabel("Formulario de alta de medios");
		lblFormularioDeAlta.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeAlta.setBounds(98, 11, 250, 25);
		contentPane.add(lblFormularioDeAlta);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigo.setBounds(42, 61, 100, 20);
		contentPane.add(lblCodigo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(42, 93, 100, 20);
		contentPane.add(lblNombre);
	}
}
