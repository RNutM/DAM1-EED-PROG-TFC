package reservas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import principal.AccesoDatos;
import principal.InicioAG;

public class BuscarReserva extends JFrame {
	/**
	 * Agencia de Viajes - Clase BuscarReserva
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static BuscarReserva frame5;
	private JTextField textDNI;
	private JTextField textViaje;
	private JTextField textPersonas;
	private JTextField textTotal;
	private JTextField textHotel;

	/**
	 * Create the frame.
	 */
	public BuscarReserva() {
		setTitle("Buscar reservas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 315);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBusquedaDeReservas = new JLabel("Busqueda de reservas");
		lblBusquedaDeReservas.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblBusquedaDeReservas.setBounds(133, 11, 180, 25);
		contentPane.add(lblBusquedaDeReservas);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(42, 61, 100, 20);
		contentPane.add(lblDni);

		textDNI = new JTextField();
		textDNI.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				int num = 0;
				String dni = textDNI.getText();
				String cviaje = textViaje.getText();
				boolean val = true;

				if (dni.isEmpty() || cviaje.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
					val = false;
				} else {
					dni = dni.substring(0, 9).toUpperCase();// Paso el último digito del dni a mayuscula que es la letra
				}

				if (val) {
					
					try {
						InicioAG.rset = AccesoDatos.ConsultaBD// Consulto si el codigo esta o no en la base de datos
						("SELECT count(*) FROM RESERVAS WHERE (DNI) LIKE '" + dni + "' and cod_viaje=" + cviaje);

						while (InicioAG.rset.next())
							num = InicioAG.rset.getInt(1);

						if (num > 0) {// Si num es mayor que 0

							JOptionPane.showMessageDialog(null, "El DNI tiene reserva en nuestra BD y tiene ese viaje");
							InicioAG.rset = AccesoDatos.ConsultaBD(
									"SELECT * FROM RESERVAS WHERE (DNI) LIKE '" + dni + "'and cod_viaje=" + cviaje);

							while (InicioAG.rset.next()) {
								// Relleno los campos
								textDNI.setText(InicioAG.rset.getString(1));
								textViaje.setText(InicioAG.rset.getString(2));
								textPersonas.setText(InicioAG.rset.getString(3));
								textTotal.setText(InicioAG.rset.getString(4));
								textHotel.setText(InicioAG.rset.getString(5));
							}
						} else {
							JOptionPane.showMessageDialog(null, "El DNI no existe en nuestra BD");
							textDNI.setText(null);// Borro el campo
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textDNI.setColumns(10);
		textDNI.setBounds(148, 61, 215, 20);
		contentPane.add(textDNI);

		textViaje = new JTextField();
		textViaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textViaje.setColumns(10);
		textViaje.setBounds(148, 93, 215, 20);
		contentPane.add(textViaje);

		JLabel lblCodigoViaje = new JLabel("C\u00F3digo Viaje");
		lblCodigoViaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoViaje.setBounds(42, 93, 100, 20);
		contentPane.add(lblCodigoViaje);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame5.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(250, 240, 135, 25);
		contentPane.add(btnVolver);

		textPersonas = new JTextField();
		textPersonas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPersonas.setEditable(false);
		textPersonas.setColumns(10);
		textPersonas.setBounds(148, 124, 215, 20);
		contentPane.add(textPersonas);

		JLabel lblNPersonas = new JLabel("N\u00BA Personas");
		lblNPersonas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNPersonas.setBounds(42, 124, 100, 20);
		contentPane.add(lblNPersonas);

		textTotal = new JTextField();
		textTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTotal.setEditable(false);
		textTotal.setColumns(10);
		textTotal.setBounds(148, 155, 215, 20);
		contentPane.add(textTotal);

		JLabel lblPrecioTotal = new JLabel("Precio Total");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioTotal.setBounds(42, 155, 100, 20);
		contentPane.add(lblPrecioTotal);

		textHotel = new JTextField();
		textHotel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textHotel.setEditable(false);
		textHotel.setColumns(10);
		textHotel.setBounds(148, 186, 215, 20);
		contentPane.add(textHotel);

		JLabel lblCdigoHotel = new JLabel("C\u00F3digo Hotel");
		lblCdigoHotel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCdigoHotel.setBounds(42, 186, 100, 20);
		contentPane.add(lblCdigoHotel);

		JButton btnBuscarOtra = new JButton("Buscar otra");
		btnBuscarOtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDNI.setText(null);// Borro el campo
				textViaje.setText(null);
				textPersonas.setText(null);
				textTotal.setText(null);
				textHotel.setText(null);
			}
		});
		btnBuscarOtra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscarOtra.setBounds(42, 240, 135, 25);
		contentPane.add(btnBuscarOtra);
	}
}
