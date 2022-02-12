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

public class ModCiudad extends JFrame {
	/**
	 * Agencia de Viajes - Clase ModCiudad
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static ModCiudad frMoCiu;
	private JTextField textCodigo;
	private JTextField textNombre;

	/**
	 * Create the frame.
	 */
	public ModCiudad() {
		setTitle("Modificación de ciudades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textCodigo = new JTextField();
		textCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(148, 61, 215, 20);
		contentPane.add(textCodigo);

		JLabel lblFormularioDeModificacion = new JLabel("Formulario de modificaci\u00F3n de ciudades");
		lblFormularioDeModificacion.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeModificacion.setBounds(57, 11, 320, 25);
		contentPane.add(lblFormularioDeModificacion);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigo.setBounds(42, 61, 100, 20);
		contentPane.add(lblCodigo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(42, 93, 100, 20);
		contentPane.add(lblNombre);

		JComboBox<Object> comboBoxPais = new JComboBox<Object>();
		comboBoxPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxPais.setEnabled(false);
		comboBoxPais.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Espa\u00F1a", "Francia", "Italia", "Reino Unido" }));
		comboBoxPais.setSelectedItem(null);// Con esta linea ocultamos las opciones
		comboBoxPais.setBounds(148, 124, 215, 22);
		contentPane.add(comboBoxPais);

		textNombre = new JTextField();
		textNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int num = 0;
				String nom = textNombre.getText();
				if (nom.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else {

					try {
						nom = Character.toUpperCase(nom.charAt(0)) + nom.substring(1, nom.length());
						textNombre.setText(nom);// La muestro en mayuscula ya
						InicioAG.rset = AccesoDatos.ConsultaBD// Consulto si el codigo esta o no en la base de datos
						("SELECT count(*) FROM CIUDADES WHERE (NOMBRE_CIUDAD) LIKE '" + nom + "'");

						while (InicioAG.rset.next())
							num = InicioAG.rset.getInt(1);
						// System.out.println(num);//Compruebo en consola si hay 1 o 0

						if (num > 0) {// Si num es mayor que 0

							JOptionPane.showMessageDialog(null, "La ciudad existe");
							InicioAG.rset = AccesoDatos
									.ConsultaBD("SELECT * FROM CIUDADES WHERE (NOMBRE_CIUDAD) LIKE '" + nom + "'");

							comboBoxPais.setEnabled(true);
							while (InicioAG.rset.next()) {// Cojo el codigo y relleno los textField
								// textPais.setEditable(true);
								textCodigo.setText(InicioAG.rset.getString(1));
								textNombre.setText(InicioAG.rset.getString(2));
								comboBoxPais.setSelectedItem(InicioAG.rset.getString(3));
							}

						} else {

							JOptionPane.showMessageDialog(null, "La ciudad no Existe");
							textNombre.setText(null);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
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

		JButton btnAceptarYModificar = new JButton("Aceptar y Modificar");
		btnAceptarYModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = textCodigo.getText();
				if (cod.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else {

					try {// Aviso al usuario si quiere continuar con la modificación
						int msj = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?");
						if (msj == JOptionPane.YES_OPTION) {// Si la opción es SI...
							// Hago el UPDATE en la BDD
							AccesoDatos.update(
									AccesoDatos.ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "agencia", "agencia"),
									"UPDATE ciudades SET COD_CIUDAD = '" + textCodigo.getText() + "'" + ","
											+ "nombre_ciudad= '" + textNombre.getText() + "'" + "," + "pais= '"
											+ comboBoxPais.getSelectedItem() + "'" + "WHERE COD_CIUDAD LIKE '"
											+ textCodigo.getText() + "'");

							JOptionPane.showMessageDialog(null, "Ciudad modificada correctamente");
							comboBoxPais.setEnabled(false);
							textCodigo.setText(null);
							textNombre.setText(null);
							comboBoxPais.setSelectedItem(null);// Con esta linea ocultamos las opciones
						}

						if (msj == JOptionPane.NO_OPTION) {// Si la opción es NO...

							Icon unIcono = null;// Creo una variable de tipo icono
							int si = JOptionPane.showOptionDialog// Creo mi propia ventana de mensaje personalizado
							(null, "¿Quieres que borre todos los campos?", "Pulsa SI, NO o Cancelar",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, unIcono,
									new Object[] { "SI", "NO", "Cancelar" }, "SI");

							if (JOptionPane.OK_OPTION == si) {// Borro todos los campos
								comboBoxPais.setEnabled(false);
								textCodigo.setText(null);
								textNombre.setText(null);
								comboBoxPais.setSelectedItem(null);// Con esta linea ocultamos las opciones
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
		btnAceptarYModificar.setBounds(43, 211, 140, 25);
		contentPane.add(btnAceptarYModificar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frMoCiu.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(251, 212, 135, 25);
		contentPane.add(btnVolver);
	}
}
