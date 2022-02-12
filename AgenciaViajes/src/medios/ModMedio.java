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

public class ModMedio extends JFrame {
	/**
	 * Agencia de Viajes - Clase ModMedio
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static ModMedio frMoMe;
	private JTextField textCodigo;
	private JTextField textNombre;

	/**
	 * Create the frame.
	 */
	public ModMedio() {
		setTitle("Modificación de medios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFormularioDeModificacion = new JLabel("Formulario de modificaci\u00F3n de medios");
		lblFormularioDeModificacion.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeModificacion.setBounds(72, 11, 305, 25);
		contentPane.add(lblFormularioDeModificacion);

		textCodigo = new JTextField();
		textCodigo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(148, 61, 215, 20);
		contentPane.add(textCodigo);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigo.setBounds(42, 61, 100, 20);
		contentPane.add(lblCodigo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(42, 93, 100, 20);
		contentPane.add(lblNombre);

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
						("SELECT count(*) FROM MEDIOSTRANSPORTE WHERE (NOMBRE_MEDIO) LIKE '" + nom + "'");

						while (InicioAG.rset.next())
							num = InicioAG.rset.getInt(1);
						// System.out.println(num);//Compruebo en consola si hay 1 o 0

						if (num > 0) {// Si num es mayor que 0

							JOptionPane.showMessageDialog(null, "El medio existe");
							InicioAG.rset = AccesoDatos.ConsultaBD(
									"SELECT * FROM MEDIOSTRANSPORTE WHERE (NOMBRE_MEDIO) LIKE '" + nom + "'");

							while (InicioAG.rset.next()) {// Cojo el codigo y relleno los textField
								// textPais.setEditable(true);
								textCodigo.setText(InicioAG.rset.getString(1));
								textNombre.setText(InicioAG.rset.getString(2));
							}
						} else {
							JOptionPane.showMessageDialog(null, "El medio no Existe");
							// textCodigo.setText(null);
							textNombre.setText(null);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textNombre.setColumns(10);
		textNombre.setBounds(148, 93, 215, 20);
		contentPane.add(textNombre);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frMoMe.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(251, 212, 135, 25);
		contentPane.add(btnVolver);

		JButton btnAceptarYModificar = new JButton("Aceptar y Modificar");
		btnAceptarYModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = textCodigo.getText();
				if (cod.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else {

					try {// Aviso al usuario si quiere continuar con la inserción
						int msj = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?");
						if (msj == JOptionPane.YES_OPTION) {// Si la opción es SI...
							// Hago el UPDATE en la BDD
							AccesoDatos.update(
									AccesoDatos.ConectarBD("jdbc:oracle:thin:@localhost:1521:XE", "agencia", "agencia"),
									"UPDATE mediostransporte SET COD_MEDIO = '" + textCodigo.getText() + "'" + ","
											+ "nombre_medio= '" + textNombre.getText() + "'" + "WHERE COD_medio LIKE '"
											+ textCodigo.getText() + "'");

							JOptionPane.showMessageDialog(null, "Medio modificado correctamente");
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
	}
}
