package reservas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class PagarReserva extends JFrame {
	/**
	 * Agencia de Viajes - Clase PagarReserva
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static PagarReserva frame3;
	private JTextField txtBusca, txtApagar, textPago;
	private String dni = "", codv = "", prtl = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame3 = new PagarReserva();
					frame3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PagarReserva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPagarReserva = new JLabel("Pagar Reserva");
		lblPagarReserva.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblPagarReserva.setBounds(143, 25, 115, 20);
		contentPane.add(lblPagarReserva);

		JLabel lblCodigoReserva = new JLabel("Codigo de Reserva");
		lblCodigoReserva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoReserva.setBounds(56, 72, 115, 14);
		contentPane.add(lblCodigoReserva);

		txtBusca = new JTextField();
		txtBusca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBusca.setBounds(181, 70, 100, 20);
		contentPane.add(txtBusca);
		txtBusca.setColumns(10);

		JLabel lblPrecioAPagar = new JLabel("Precio a pagar");
		lblPrecioAPagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioAPagar.setBounds(56, 119, 91, 14);
		contentPane.add(lblPrecioAPagar);

		txtApagar = new JTextField();
		txtApagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtApagar.setEnabled(false);
		txtApagar.setBounds(181, 117, 100, 20);
		contentPane.add(txtApagar);
		txtApagar.setColumns(10);

		JLabel lblPago = new JLabel("Pago");
		lblPago.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPago.setBounds(56, 166, 115, 14);
		contentPane.add(lblPago);

		textPago = new JTextField();
		textPago.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPago.setBounds(181, 164, 100, 20);
		contentPane.add(textPago);
		textPago.setColumns(10);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean num = true;;

				if (txtBusca.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "El campo está en blanco");
					num = false;
				}

				try {
					dni = txtBusca.getText().substring(0, 9);
					codv = txtBusca.getText().substring(9);
				} catch (StringIndexOutOfBoundsException corto) {
					num = false;
					JOptionPane.showMessageDialog(null, "Codigo de reserva demasiado corto");
				}

				if (txtApagar.getText().isEmpty() || textPago.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Hay campos en blanco");
					num = false;
				} else {

					try {
						Double.parseDouble(txtApagar.getText());
						Double.parseDouble(textPago.getText());
					} catch (NumberFormatException excepcion) {
						num = false;
						JOptionPane.showMessageDialog(null, "Deben ser numéros, por favor");
					}
				}
				if (num) {

					double total = Double.parseDouble(txtApagar.getText());
					double pago = Double.parseDouble(textPago.getText());
					double fin = total - pago;

					if (fin <= 0) {
						fin = 0;
					}
					String fn = String.valueOf(fin);

					txtApagar.setText(fn);
					textPago.setText("");

					try {
						InicioAG.rset = AccesoDatos.ConsultaBD("update reservas set precio_total=" + fn
								+ "  where dni like '" + dni + "' and cod_viaje=" + codv);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (txtApagar.getText().equals("0.0")) {
						JOptionPane.showMessageDialog(null, "Reserva pagada");
					}
				}
			}
		});
		btnPagar.setBounds(181, 213, 89, 23);
		contentPane.add(btnPagar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3.setVisible(false);
				InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setBounds(306, 213, 89, 23);
		contentPane.add(btnVolver);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean t = true;
				boolean entrada = true;

				

				if (txtBusca.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "El campo está en blanco");
					t = false;
				}

				try {
					dni = txtBusca.getText().substring(0, 9);
					codv = txtBusca.getText().substring(9);
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
							InicioAG.rset = AccesoDatos.ConsultaBD("select precio_total from reservas where dni like '"
									+ dni + "' and cod_viaje=" + codv);
							while (InicioAG.rset.next()) {

								prtl = InicioAG.rset.getString(1);
							}
							txtApagar.setText(prtl);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnBuscar.setBounds(58, 213, 89, 23);
		contentPane.add(btnBuscar);
	}
}
