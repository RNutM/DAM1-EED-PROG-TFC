package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clientes.Clientes;

public class AveriguaNif extends JFrame {
	/**
	 * Agencia de Viajes - Clase AveriguaNif
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textSinletra;
	public static AveriguaNif frame7;
	private JTextField textConletra;

	/**
	 * Create the frame.
	 */
	public AveriguaNif() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAveriguarLetraDe = new JLabel("Averiguar letra de un DNI");
		lblAveriguarLetraDe.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblAveriguarLetraDe.setBounds(122, 11, 220, 25);
		contentPane.add(lblAveriguarLetraDe);

		JLabel lblDniSinLetra = new JLabel("DNI sin letra");
		lblDniSinLetra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDniSinLetra.setBounds(41, 61, 100, 20);
		contentPane.add(lblDniSinLetra);

		textSinletra = new JTextField();
		textSinletra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String dni;
				dni = textSinletra.getText();

				if (dni.isEmpty()) {// Compruebo que el campo esta vacío y aviso
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else if (Clientes.valido2(dni)) {// Accedo al método valido para comprobar el formato

					try {
						String letra = NIF.obtenerletra(Integer.parseInt(dni));
						textConletra.setText(dni + letra);

					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		});
		textSinletra.setToolTipText("Introduzca un DNI sin letra y solo números");
		textSinletra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textSinletra.setColumns(10);
		textSinletra.setBounds(147, 61, 215, 20);
		contentPane.add(textSinletra);

		JButton btnBorrar = new JButton("Borrar campos");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSinletra.setText(null);
				textConletra.setText(null);
			}
		});
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBorrar.setBounds(42, 211, 135, 25);
		contentPane.add(btnBorrar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame7.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(250, 212, 135, 25);
		contentPane.add(btnVolver);

		JLabel lblDniConLetra = new JLabel("DNI con letra");
		lblDniConLetra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDniConLetra.setBounds(41, 92, 100, 20);
		contentPane.add(lblDniConLetra);

		textConletra = new JTextField();
		textConletra.setEditable(false);
		textConletra.setToolTipText("Aqui muestro el dni con su letra correcta");
		textConletra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textConletra.setColumns(10);
		textConletra.setBounds(147, 92, 215, 20);
		contentPane.add(textConletra);
	}
}
