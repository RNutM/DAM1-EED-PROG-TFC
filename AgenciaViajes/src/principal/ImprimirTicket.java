package principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ImprimirTicket extends JFrame {
	/**
	 * Agencia de Viajes - Clase ImprimirTicket
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ImprimirTicket frame4;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame4 = new ImprimirTicket();
					frame4.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImprimirTicket() {
		setTitle("Tickets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblImprimir = new JLabel("Impresi\u00F3n de tickets");
		lblImprimir.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblImprimir.setBounds(132, 21, 180, 25);
		contentPane.add(lblImprimir);

		JLabel lblCodigoReserva = new JLabel("Introduzca el c\u00F3digo de reserva:");
		lblCodigoReserva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoReserva.setBounds(57, 67, 180, 14);
		contentPane.add(lblCodigoReserva);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean t = true;
				boolean entrada = true;

				String dni = "";
				String codv = "";
				String pt = "";

				String nomcli = "";
				String apecli = "";
				String telcli = "";
				String numper = "";
				String codh = "";

				String nomhot = "";
				String dirhot = "";

				String nomciuorigen = "";
				String paisorigen = "";

				String nomciudestino = "";
				String paisdestino = "";

				String medio = "";

				if (txtCodigo.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "El campo está en blanco");
					t = false;
				}

				try {
					dni = txtCodigo.getText().substring(0, 9);
					codv = txtCodigo.getText().substring(9);
				} catch (StringIndexOutOfBoundsException corto) {
					t = false;
					JOptionPane.showMessageDialog(null, "Codigo de reserva demasiado corto");
				}

				try {
					Double.parseDouble(codv);

				} catch (NumberFormatException excepcion) {
					t = false;
					JOptionPane.showMessageDialog(null, "Error en el código de viaje");
				}

				if (t) {

					try {
						InicioAG.rset = AccesoDatos.ConsultaBD(
								"select count(*) from reservas where dni like '" + dni + "' and cod_viaje=" + codv);

						while (InicioAG.rset.next()) {
							if (InicioAG.rset.getString(1).equals("0")) {
								JOptionPane.showMessageDialog(null, "Esa reserva no existe");
								entrada = false;
							}
						}

						if (entrada) {

							InicioAG.rset = AccesoDatos
									.ConsultaBD("select * from clientes join reservas using (dni) where dni like '"
											+ dni + "' and cod_viaje=" + codv);
							while (InicioAG.rset.next()) {

								nomcli = InicioAG.rset.getString(2);
								apecli = InicioAG.rset.getString(3);
								telcli = InicioAG.rset.getString(4);
								numper = InicioAG.rset.getString(6);
								pt = InicioAG.rset.getString(7);
								codh = InicioAG.rset.getString(8);
							}

							if (pt.equals("0")) {

								JOptionPane.showMessageDialog(null, "RESERVA PAGADA. Se procede a imprimir el ticket");

								InicioAG.rset = AccesoDatos
										.ConsultaBD("select * from hoteles where  cod_hotel=" + codh);
								while (InicioAG.rset.next()) {
									nomhot = InicioAG.rset.getString(2);
									dirhot = InicioAG.rset.getString(3);
								}

								InicioAG.rset = AccesoDatos.ConsultaBD(
										"select * from ciudades where cod_ciudad=(select ciudad_origen from viajes where cod_viaje="
												+ codv + ")");
								while (InicioAG.rset.next()) {
									nomciuorigen = InicioAG.rset.getString(2);
									paisorigen = InicioAG.rset.getString(3);
								}
								InicioAG.rset = AccesoDatos.ConsultaBD(
										"select * from ciudades, mediostransporte where cod_ciudad=(select ciudad_destino from "
												+ "viajes where cod_viaje=" + codv
												+ ") and cod_medio=(select cod_medio from viajes where cod_viaje="
												+ codv + ")");
								while (InicioAG.rset.next()) {
									nomciudestino = InicioAG.rset.getString(2);
									paisdestino = InicioAG.rset.getString(3);
									medio = InicioAG.rset.getString(5);
								}

								String ticket = "./tickets/Ticket" + nomcli + apecli + nomciuorigen + nomciudestino
										+ ".txt";

								PrintWriter salida = new PrintWriter(ticket);

								salida.println("El cliente " + nomcli + " " + apecli + " con teléfono " + telcli
										+ ", viaja acompañado por " + numper + " personas:" + "\r");
								salida.println("\tOrigen     :  " + nomciuorigen + " - " + paisorigen
										+ "   Destino    : " + nomciudestino + " - " + paisdestino + "\r");
								salida.println("\tMedio de transporte escogido: " + medio + "\r");
								salida.println("\tSe hospedarán en el hotel " + nomhot + " - " + dirhot + "." + "\r");
								salida.println("\tSu codigo de reserva es: " + dni + codv);
								salida.println("\tDisfrute del viaje");
								salida.close();

								txtCodigo.setText("");
							} else {
								JOptionPane.showMessageDialog(null,
										"Aun no ha pagado su reserva, no puede partir. Debe abonar " + pt + " euros");
							}
						}
					} catch (SQLException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnImprimir.setBounds(55, 206, 89, 23);
		contentPane.add(btnImprimir);

		txtCodigo = new JTextField();
		txtCodigo.setToolTipText("Introduzca aqu\u00ED el c\u00F3digo de reserva a imprimir");
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCodigo.setBounds(247, 64, 100, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame4.setVisible(false);
				InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setBounds(303, 206, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblDniletracdigoViaje = new JLabel("DNI + Letra + C\u00F3digo Viaje");
		lblDniletracdigoViaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDniletracdigoViaje.setBounds(57, 92, 180, 14);
		contentPane.add(lblDniletracdigoViaje);
	}
}
