package ciudades;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import principal.AccesoDatos;
import principal.InicioAG;

public class AltaCiudad extends JFrame {
	/**
	 * Agencia de Viajes - Clase AltaCiudad
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static AltaCiudad frAlCi;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JButton btnAceptarYGuardar;

	/**
	 * Create the frame.
	 */
	public AltaCiudad() {
		setTitle("Alta de ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFormularioDeAlta = new JLabel("Formulario de alta de ciudades");
		lblFormularioDeAlta.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeAlta.setBounds(98, 11, 250, 25);
		contentPane.add(lblFormularioDeAlta);

		JComboBox<Object> comboBoxPais = new JComboBox<Object>();
		comboBoxPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxPais.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Espa\u00F1a", "Francia", "Reino Unido", "Italia" }));
		comboBoxPais.setSelectedItem(null);// Con esta linea ocultamos las opciones
		comboBoxPais.setBounds(148, 124, 215, 20);
		contentPane.add(comboBoxPais);

		btnAceptarYGuardar = new JButton("Aceptar y Guardar");
		btnAceptarYGuardar.setEnabled(false);
		btnAceptarYGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {// Aviso al usuario si quiere continuar con la inserción
					int msj = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?");
					if (msj == JOptionPane.YES_OPTION) {// Si la opción es SI...

						// Hago el INSERT en la BDD
						AccesoDatos.insert(
								AccesoDatos.ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "agencia", "agencia"),
								"INSERT INTO ciudades(COD_CIUDAD,NOMBRE_CIUDAD,PAIS) VALUES('" + textCodigo.getText()
										+ "','" + textNombre.getText() + "','" + comboBoxPais.getSelectedItem() + "')");

						JOptionPane.showMessageDialog(null, "Ciudad añadida correctamente");
						textCodigo.setText(null);
						textNombre.setText(null);
						comboBoxPais.setSelectedItem(null);
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
							comboBoxPais.setSelectedItem(null);
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
		btnAceptarYGuardar.setBounds(43, 211, 135, 25);
		contentPane.add(btnAceptarYGuardar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Borro todos los campos al entrar
				textNombre.setText(null);
				comboBoxPais.setSelectedItem(null);
				frAlCi.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(251, 212, 135, 25);
		contentPane.add(btnVolver);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigo.setBounds(42, 61, 100, 20);
		contentPane.add(lblCodigo);

		textCodigo = new JTextField();
		textCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					InicioAG.rset = AccesoDatos.ConsultaBD// Con esta consulta pongo el código siguiente al que ya
															// existe como el último
					// o lo que es lo mismo el máximo existente +1
					("SELECT MAX(COD_CIUDAD)+1 FROM CIUDADES");
					while (InicioAG.rset.next()) {// Cojo el código y lo pongo en el textField
						textCodigo.setText(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textCodigo.setEditable(false);
		textCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCodigo.setColumns(10);
		textCodigo.setBounds(148, 61, 215, 20);
		contentPane.add(textCodigo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(42, 93, 100, 20);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int num = 0;
				String nomciu = textNombre.getText();

				if (nomciu.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacio");

				} else {
					if (comboBoxPais.getSelectedItem() == null) {

						JOptionPane.showMessageDialog(null, "Debes seleccionar un País para poder continuar");
						// Pongo la ciudad ya con la primera letra en mayuscula
						nomciu = Character.toUpperCase(nomciu.charAt(0)) + nomciu.substring(1, nomciu.length());
						textNombre.setText(nomciu);// La muestro en mayuscula ya
					} else {
						btnAceptarYGuardar.setEnabled(true);// Activo el boton Aceptar
					}
				}
				try {
					InicioAG.rset = AccesoDatos.ConsultaBD// Compruebo que exista o no la ciudad
					("SELECT count(*) FROM CIUDADES WHERE (NOMBRE_CIUDAD) LIKE '" + nomciu + "'");
					while (InicioAG.rset.next()) {
						num = InicioAG.rset.getInt(1);
						if (num > 0) {// Si num es mayor que 0
							JOptionPane.showMessageDialog(null, "La ciudad ya existe, por favor introduce otra");
							textNombre.setText(null);
							comboBoxPais.setSelectedItem(null);// Dejo en blanco de nuevo el comboBox
							btnAceptarYGuardar.setEnabled(false);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNombre.setColumns(10);
		textNombre.setBounds(148, 93, 215, 20);
		contentPane.add(textNombre);

		JLabel lblPais = new JLabel("Pa\u00EDs");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(42, 125, 100, 20);
		contentPane.add(lblPais);
	}
}
