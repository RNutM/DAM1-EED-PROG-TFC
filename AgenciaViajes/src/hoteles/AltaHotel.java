package hoteles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
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

public class AltaHotel extends JFrame {
	/**
	 * Agencia de Viajes - Clase AltaHotel
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDir;
	private JTextField textPrecio;
	public static AltaHotel frAlHo;

	/**
	 * Create the frame.
	 */
	public AltaHotel() {
		setTitle("Alta de hoteles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(44, 61, 90, 20);
		contentPane.add(lblNombre);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDireccion.setBounds(44, 93, 90, 20);
		contentPane.add(lblDireccion);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecio.setBounds(44, 126, 90, 20);
		contentPane.add(lblPrecio);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCiudad.setBounds(44, 157, 90, 20);
		contentPane.add(lblCiudad);

		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNombre.setBounds(150, 61, 200, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		textDir = new JTextField();
		textDir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textDir.setBounds(150, 93, 200, 20);
		contentPane.add(textDir);
		textDir.setColumns(10);

		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPrecio.setBounds(150, 126, 100, 20);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);

		JComboBox<Object> comboHotel = new JComboBox<Object>();
		comboHotel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboHotel.setBounds(150, 157, 150, 20);
		contentPane.add(comboHotel);

		JButton btnBuscarCiudad = new JButton("Buscar ciudad");
		btnBuscarCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscarCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String hoteles[] = new String[15];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD("select nombre_ciudad from ciudades");

					int cont = 0;

					while (InicioAG.rset.next()) {
						hoteles[cont] = InicioAG.rset.getString(1);
						cont++;
					}
					comboHotel.setEnabled(true);
					comboHotel.setModel(new DefaultComboBoxModel<Object>(hoteles));

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscarCiudad.setBounds(45, 211, 135, 25);
		contentPane.add(btnBuscarCiudad);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String codciudad = "";
				boolean b = true;

				try {

					InicioAG.rset = AccesoDatos.ConsultaBD("select cod_ciudad from ciudades where nombre_ciudad like '"
							+ comboHotel.getSelectedItem() + "'");

					while (InicioAG.rset.next()) {
						codciudad = InicioAG.rset.getString(1);
					}

					if (b) {
						InicioAG.rset = AccesoDatos
								.ConsultaBD("insert into hoteles values ((select MAX(COD_Hotel)+1 FROM hoteles),'"
										+ textNombre.getText() + "','" + textDir.getText() + "'," + textPrecio.getText()
										+ "," + codciudad + ")");

						JOptionPane.showMessageDialog(null, "Hotel dado de alta");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(190, 211, 100, 25);
		contentPane.add(btnAceptar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frAlHo.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setBounds(300, 211, 100, 25);
		contentPane.add(btnVolver);

		JLabel lblFormularioDeAlta = new JLabel("Formulario de alta de hoteles");
		lblFormularioDeAlta.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeAlta.setBounds(102, 11, 240, 25);
		contentPane.add(lblFormularioDeAlta);
	}
}
