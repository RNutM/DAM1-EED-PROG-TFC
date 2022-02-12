package reservas;

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

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class CrearReserva extends JFrame {
	/**
	 * Agencia de Viajes - Clase CrearReserva
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static CrearReserva frame2;
	private JTextField textDni, textAcom, txtPrecioViaje, txtPreciohotel, txtPrecioTotal, txtPpP;
	private JLabel lblOrgen, lblDestino, lblMedioTransporte, lblHotel;
	private JComboBox<Object> comboDestino, comboMedio, comboHotel, comboOrigen;
	private JButton btnAceptarYGuardar;
	private JLabel lblCrearReservas, lblPreciopersona;
	private JButton btnActualizarPrecio;
	private final JSeparator separator_2 = new JSeparator();
	private String codv = "", codh = "";

	/**
	 * Create the frame.
	 */
	public CrearReserva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setToolTipText("Pulse para volver al men\u00FA principal");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame2.setVisible(false);
				InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setBounds(441, 289, 89, 23);
		contentPane.add(btnVolver);

		JLabel lblDNICliente = new JLabel("DNI Cliente");
		lblDNICliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNICliente.setBounds(38, 63, 99, 14);
		contentPane.add(lblDNICliente);

		textDni = new JTextField();
		textDni.setToolTipText("Introduzca un DNI v\u00E1lido");
		textDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDni.setBounds(173, 63, 120, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);

		textAcom = new JTextField();
		textAcom.setToolTipText("Introduzca n\u00FAmero de acompa\u00F1antes");
		textAcom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAcom.setColumns(10);
		textAcom.setBounds(173, 88, 120, 20);
		contentPane.add(textAcom);

		JLabel lblNAcompaantes = new JLabel("N\u00BA Acompa\u00F1antes");
		lblNAcompaantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNAcompaantes.setBounds(38, 88, 125, 14);
		contentPane.add(lblNAcompaantes);

		comboOrigen = new JComboBox<Object>();
		comboOrigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboOrigen.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Madrid", "Barcelona", "Valencia", "Par\u00EDs", "Londres", "Venecia" }));
		comboOrigen.setBounds(173, 113, 120, 20);
		contentPane.add(comboOrigen);

		lblOrgen = new JLabel("Or\u00EDgen");
		lblOrgen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrgen.setBounds(38, 113, 46, 14);
		contentPane.add(lblOrgen);

		lblDestino = new JLabel("Destino");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestino.setBounds(38, 138, 60, 14);
		contentPane.add(lblDestino);

		lblMedioTransporte = new JLabel("Medio Transporte");
		lblMedioTransporte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedioTransporte.setBounds(38, 163, 125, 14);
		contentPane.add(lblMedioTransporte);

		lblHotel = new JLabel("Hotel");
		lblHotel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHotel.setBounds(38, 188, 46, 14);
		contentPane.add(lblHotel);

		comboDestino = new JComboBox<Object>();
		comboDestino.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Madrid", "Barcelona", "Valencia", "Par\u00EDs", "Londres", "Venecia" }));
		comboDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboDestino.setBounds(173, 138, 120, 20);
		contentPane.add(comboDestino);

		comboMedio = new JComboBox<Object>();
		comboMedio.setModel(new DefaultComboBoxModel<Object>(new String[] { "Tren", "Avi\u00F3n", "Barco" }));
		comboMedio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboMedio.setBounds(173, 163, 120, 20);
		contentPane.add(comboMedio);

		comboHotel = new JComboBox<Object>();
		comboHotel.setEnabled(false);
		comboHotel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboHotel.setBounds(173, 188, 120, 20);
		contentPane.add(comboHotel);

		txtPrecioViaje = new JTextField();
		txtPrecioViaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPrecioViaje.setEnabled(false);
		txtPrecioViaje.setBounds(470, 107, 65, 20);
		contentPane.add(txtPrecioViaje);
		txtPrecioViaje.setColumns(10);

		txtPreciohotel = new JTextField();
		txtPreciohotel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPreciohotel.setEnabled(false);
		txtPreciohotel.setBounds(470, 132, 65, 20);
		contentPane.add(txtPreciohotel);
		txtPreciohotel.setColumns(10);

		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPrecioTotal.setEnabled(false);
		txtPrecioTotal.setBounds(470, 187, 65, 20);
		contentPane.add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);

		btnAceptarYGuardar = new JButton("Confirmar");
		btnAceptarYGuardar.setToolTipText("Pulse para confirmar la reserva");
		btnAceptarYGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean num = true;

				if (textDni.getText().isEmpty() || textAcom.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Hay campos en blanco");
					num = false;
				} else {

					try {
						InicioAG.rset = AccesoDatos
								.ConsultaBD("select count(*) from clientes where dni like '" + textDni.getText() + "'");

						while (InicioAG.rset.next()) {
							if (InicioAG.rset.getString(1).equals("0")) {
								JOptionPane.showMessageDialog(null, "Usted no esta en nuestra base de datos");
								num = false;
							}
						}

						InicioAG.rset = AccesoDatos.ConsultaBD(
								"select count(*) from viajes where ciudad_origen like (select cod_ciudad from ciudades where nombre_ciudad like '"
										+ comboOrigen.getSelectedItem() + "')\r\n"
										+ "and ciudad_destino like (select cod_ciudad from ciudades where nombre_ciudad like '"
										+ comboDestino.getSelectedItem() + "')\r\n"
										+ "and cod_medio like (select cod_medio from mediostransporte where nombre_medio like '"
										+ comboMedio.getSelectedItem() + "')");

						while (InicioAG.rset.next()) {
							if (InicioAG.rset.getString(1).equals("0")) {
								JOptionPane.showMessageDialog(null, "Ese viaje es imposible.Lo siento");
								num = false;
							}
						}

						try {
							Double.parseDouble(textAcom.getText());

						} catch (NumberFormatException excepcion) {
							num = false;
							JOptionPane.showMessageDialog(null, "En Acompañantes debe poner un número");
						}

						if (comboHotel.getItemCount() <= 0) {
							num = false;
							JOptionPane.showMessageDialog(null, "No ha elegido hotel aún");
						}

						if (num) {

							String precioviaje = "";

							InicioAG.rset = AccesoDatos.ConsultaBD(
									"select precio_viaje, cod_viaje from viajes where ciudad_origen like (select cod_ciudad from ciudades where nombre_ciudad like '"
											+ comboOrigen.getSelectedItem() + "')\r\n"
											+ "and ciudad_destino like (select cod_ciudad from ciudades where nombre_ciudad like '"
											+ comboDestino.getSelectedItem() + "')\r\n"
											+ "and cod_medio like (select cod_medio from mediostransporte where nombre_medio like '"
											+ comboMedio.getSelectedItem() + "')");

							while (InicioAG.rset.next()) {
								precioviaje = InicioAG.rset.getString(1);
								codv = InicioAG.rset.getString(2);
							}

							String preciohotel = "";

							InicioAG.rset = AccesoDatos
									.ConsultaBD("select precio_hotel, cod_hotel from hoteles where nombre_hotel like '"
											+ comboHotel.getSelectedItem() + "'");

							while (InicioAG.rset.next()) {
								preciohotel = InicioAG.rset.getString(1);
								codh = InicioAG.rset.getString(2);
							}

							double pv = Double.parseDouble(precioviaje);
							double ph = Double.parseDouble(preciohotel);
							boolean q = true;

							InicioAG.rset = AccesoDatos.ConsultaBD("select count(*) from reservas where dni like '"
									+ textDni.getText() + "' and cod_viaje=" + codv);

							while (InicioAG.rset.next()) {
								if (InicioAG.rset.getString(1).equals("1")) {
									JOptionPane.showMessageDialog(null, "Usted ya ha hecho esa reserva");
									q = false;
								}
							}
							/* Aqui hacer la inserción */
							if (q) {
								InicioAG.rset = AccesoDatos.ConsultaBD("insert into reservas values ('"
										+ textDni.getText() + "'," + codv + "," + textAcom.getText() + ", "
										+ Double.parseDouble(textAcom.getText()) * (pv + ph) + "," + codh + ")");

								JOptionPane.showMessageDialog(null,
										"Reserva realizada con éxito. Su ID de reserva es " + textDni.getText() + codv);

								textDni.setText("");
								textAcom.setText("");
								comboHotel.removeAllItems();
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnAceptarYGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptarYGuardar.setBounds(38, 289, 125, 23);
		contentPane.add(btnAceptarYGuardar);

		lblCrearReservas = new JLabel("Crear Reservas");
		lblCrearReservas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCrearReservas.setBounds(88, 12, 146, 28);
		contentPane.add(lblCrearReservas);

		JButton btnBuscarHoteles = new JButton("Buscar Hoteles");
		btnBuscarHoteles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscarHoteles.setToolTipText("Pulse para ver los hoteles disponibles");
		btnBuscarHoteles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String hoteles[] = new String[5];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD(
							"select nombre_hotel from hoteles where cod_ciudad in (select cod_ciudad from ciudades where nombre_ciudad like '"
									+ comboDestino.getSelectedItem() + "')");
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
		btnBuscarHoteles.setBounds(38, 215, 125, 31);
		contentPane.add(btnBuscarHoteles);

		btnActualizarPrecio = new JButton("Actualizar Precio");
		btnActualizarPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnActualizarPrecio.setToolTipText("Pulse para totalizar seg\u00FAn la selecci\u00F3n");
		btnActualizarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean num = true;
				if (textDni.getText().isEmpty() || textAcom.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Hay campos en blanco");
					num = false;
				}

				try {
					InicioAG.rset = AccesoDatos
							.ConsultaBD("select count(*) from clientes where dni like '" + textDni.getText() + "'");

					while (InicioAG.rset.next()) {
						if (InicioAG.rset.getString(1).equals("0")) {
							JOptionPane.showMessageDialog(null, "Usted no esta en nuestra base de datos");
							num = false;
						}
					}
					InicioAG.rset = AccesoDatos.ConsultaBD(
							"select count(*) from viajes where ciudad_origen like (select cod_ciudad from ciudades where nombre_ciudad like '"
									+ comboOrigen.getSelectedItem() + "')\r\n"
									+ "and ciudad_destino like (select cod_ciudad from ciudades where nombre_ciudad like '"
									+ comboDestino.getSelectedItem() + "')\r\n"
									+ "and cod_medio like (select cod_medio from mediostransporte where nombre_medio like '"
									+ comboMedio.getSelectedItem() + "')");

					while (InicioAG.rset.next()) {
						if (InicioAG.rset.getString(1).equals("0")) {
							JOptionPane.showMessageDialog(null, "Ese viaje es imposible.Lo siento");
							num = false;
						}
					}
					try {
						Double.parseDouble(textAcom.getText());

					} catch (NumberFormatException excepcion) {
						num = false;
						JOptionPane.showMessageDialog(null, "En Acompañantes debe poner un número");
					}

					if (comboHotel.getItemCount() <= 0) {
						num = false;
						JOptionPane.showMessageDialog(null, "No ha elegido hotel aún");
					}

					if (num) {
						String precioviaje = "";

						InicioAG.rset = AccesoDatos.ConsultaBD(
								"select precio_viaje, cod_viaje from viajes where ciudad_origen like (select cod_ciudad from ciudades where nombre_ciudad like '"
										+ comboOrigen.getSelectedItem() + "')\r\n"
										+ "and ciudad_destino like (select cod_ciudad from ciudades where nombre_ciudad like '"
										+ comboDestino.getSelectedItem() + "')\r\n"
										+ "and cod_medio like (select cod_medio from mediostransporte where nombre_medio like '"
										+ comboMedio.getSelectedItem() + "')");

						while (InicioAG.rset.next()) {
							precioviaje = InicioAG.rset.getString(1);
							codv = InicioAG.rset.getString(2);
						}

						String preciohotel = "";

						InicioAG.rset = AccesoDatos
								.ConsultaBD("select precio_hotel, cod_hotel from hoteles where nombre_hotel like '"
										+ comboHotel.getSelectedItem() + "'");

						while (InicioAG.rset.next()) {
							preciohotel = InicioAG.rset.getString(1);
							codh = InicioAG.rset.getString(2);
						}

						double pv = Double.parseDouble(precioviaje);
						double ph = Double.parseDouble(preciohotel);

						String ppp = String.valueOf(pv + ph);

						double pt = Double.parseDouble(textAcom.getText()) * (pv + ph);

						String preciototal = String.valueOf(pt);

						txtPrecioViaje.setText(precioviaje);
						txtPreciohotel.setText(preciohotel);
						txtPpP.setText(ppp);
						txtPrecioTotal.setText(preciototal);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnActualizarPrecio.setBounds(375, 219, 155, 23);
		contentPane.add(btnActualizarPrecio);

		JLabel lblImporte = new JLabel("Importe");
		lblImporte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImporte.setBounds(470, 79, 60, 20);
		contentPane.add(lblImporte);

		JLabel lblPrecioViaje = new JLabel("Viaje");
		lblPrecioViaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioViaje.setBounds(375, 107, 72, 14);
		contentPane.add(lblPrecioViaje);

		JLabel lblPrecioHotel = new JLabel("Hotel");
		lblPrecioHotel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioHotel.setBounds(375, 135, 72, 15);
		contentPane.add(lblPrecioHotel);

		JLabel lblPrecioTotal = new JLabel("Total");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioTotal.setBounds(375, 190, 60, 14);
		contentPane.add(lblPrecioTotal);

		lblPreciopersona = new JLabel("Persona");
		lblPreciopersona.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreciopersona.setBounds(375, 160, 76, 14);
		contentPane.add(lblPreciopersona);

		txtPpP = new JTextField();
		txtPpP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPpP.setEnabled(false);
		txtPpP.setBounds(470, 157, 65, 20);
		contentPane.add(txtPpP);
		txtPpP.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		separator.setBounds(470, 100, 70, 10);
		contentPane.add(separator);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio.setBounds(375, 78, 72, 20);
		contentPane.add(lblPrecio);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		separator_1.setBounds(375, 100, 60, 10);
		contentPane.add(separator_1);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		separator_2.setBounds(323, 20, 14, 292);
		contentPane.add(separator_2);

		JLabel lblSimulacin = new JLabel("Simulaci\u00F3n");
		lblSimulacin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSimulacin.setBounds(410, 12, 120, 28);
		contentPane.add(lblSimulacin);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(470, 180, 70, 10);
		contentPane.add(separator_3);
	}
}
