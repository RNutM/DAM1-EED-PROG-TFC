package principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ciudades.AltaCiudad;
import ciudades.BajaCiudad;
import ciudades.ModCiudad;
import clientes.AltaCliente;
import clientes.BajaCliente;
import clientes.ModCliente;
import hoteles.AltaHotel;
import hoteles.BajaHotel;
import hoteles.ModHotel;
import listados.Listados;
import medios.AltaMedio;
import medios.BajaMedio;
import medios.ModMedio;
import reservas.AnularReserva;
import reservas.BuscarReserva;
import reservas.CrearReserva;
import reservas.PagarReserva;

public class InicioAG extends JFrame {
	/**
	 * Agencia de Viajes - Clase para iniciar el programa (Main)
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static InicioAG frame1;
	public static ResultSet rset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 = new InicioAG();
					frame1.setVisible(true);
					CrearReserva.frame2 = new CrearReserva();
					CrearReserva.frame2.setVisible(false);
					PagarReserva.frame3 = new PagarReserva();
					PagarReserva.frame3.setVisible(false);
					ImprimirTicket.frame4 = new ImprimirTicket();
					ImprimirTicket.frame4.setVisible(false);
					BuscarReserva.frame5 = new BuscarReserva();
					BuscarReserva.frame5.setVisible(false);
					AnularReserva.frame6 = new AnularReserva();
					AnularReserva.frame6.setVisible(false);
					AveriguaNif.frame7 = new AveriguaNif();
					AveriguaNif.frame7.setVisible(false);
					AltaCliente.frAlCl = new AltaCliente();
					AltaCliente.frAlCl.setVisible(false);
					BajaCliente.frBaCl = new BajaCliente();
					BajaCliente.frBaCl.setVisible(false);
					ModCliente.frMoCl = new ModCliente();
					ModCliente.frMoCl.setVisible(false);
					AltaCiudad.frAlCi = new AltaCiudad();
					AltaCiudad.frAlCi.setVisible(false);
					BajaCiudad.frBaCi = new BajaCiudad();
					BajaCiudad.frBaCi.setVisible(false);
					ModCiudad.frMoCiu = new ModCiudad();
					ModCiudad.frMoCiu.setVisible(false);
					AltaMedio.frAlMe = new AltaMedio();
					AltaMedio.frAlMe.setVisible(false);
					BajaMedio.frBaMe = new BajaMedio();
					BajaMedio.frBaMe.setVisible(false);
					ModMedio.frMoMe = new ModMedio();
					ModMedio.frMoMe.setVisible(false);
					AltaHotel.frAlHo = new AltaHotel();
					AltaHotel.frAlHo.setVisible(false);
					BajaHotel.frBaHo = new BajaHotel();
					BajaHotel.frBaHo.setVisible(false);
					ModHotel.frMoHo = new ModHotel();
					ModHotel.frMoHo.setVisible(false);
					Listados.frLiHo = new Listados();
					Listados.frLiHo.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioAG() {
		setTitle("Gesti\u00F3n de reservas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 340);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(224, 255, 255));
		setJMenuBar(menuBar);

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		JMenuItem mntmAltaCliente = new JMenuItem("Alta Cliente");
		mntmAltaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame1.setVisible(false);
				AltaCliente.frAlCl.setVisible(true);
			}
		});
		mnClientes.add(mntmAltaCliente);

		JMenuItem mntmBajaCliente = new JMenuItem("Baja Cliente");
		mntmBajaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				BajaCliente.frBaCl.setVisible(true);
			}
		});
		mnClientes.add(mntmBajaCliente);

		JMenuItem mntmModificarCliente = new JMenuItem("Modificar Cliente");
		mntmModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				ModCliente.frMoCl.setVisible(true);
			}
		});
		mnClientes.add(mntmModificarCliente);

		JMenu mnCiudades = new JMenu("Ciudades");
		menuBar.add(mnCiudades);

		JMenuItem mntmAltaCiudad = new JMenuItem("Alta Ciudad");
		mntmAltaCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				AltaCiudad.frAlCi.setVisible(true);
			}
		});
		mnCiudades.add(mntmAltaCiudad);

		JMenuItem mntmBajaCiudad = new JMenuItem("Baja Ciudad");
		mntmBajaCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				BajaCiudad.frBaCi.setVisible(true);
			}
		});
		mnCiudades.add(mntmBajaCiudad);

		JMenuItem mntmModificarCiudad = new JMenuItem("Modificar Ciudad");
		mntmModificarCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				ModCiudad.frMoCiu.setVisible(true);
			}
		});
		mnCiudades.add(mntmModificarCiudad);

		JMenu mnHoteles = new JMenu("Hoteles");
		menuBar.add(mnHoteles);

		JMenuItem mntmAltaHotel = new JMenuItem("Alta Hotel");
		mntmAltaHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				AltaHotel.frAlHo.setVisible(true);
			}
		});
		mnHoteles.add(mntmAltaHotel);

		JMenuItem mntmBajaHotel = new JMenuItem("Baja Hotel");
		mntmBajaHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				BajaHotel.frBaHo.setVisible(true);
			}
		});
		mnHoteles.add(mntmBajaHotel);

		JMenuItem mntmModificarHotel = new JMenuItem("Modificar Hotel");
		mntmModificarHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				ModHotel.frMoHo.setVisible(true);
			}
		});
		mnHoteles.add(mntmModificarHotel);

		JMenu mnMediosTransporte = new JMenu("Medios Transporte");
		menuBar.add(mnMediosTransporte);

		JMenuItem mntmAltaMedios = new JMenuItem("Alta Medios");
		mntmAltaMedios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				AltaMedio.frAlMe.setVisible(true);
			}
		});
		mnMediosTransporte.add(mntmAltaMedios);

		JMenuItem mntmBajaMedios = new JMenuItem("Baja Medios");
		mntmBajaMedios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				BajaMedio.frBaMe.setVisible(true);
			}
		});
		mnMediosTransporte.add(mntmBajaMedios);

		JMenuItem mntmModificarMedios = new JMenuItem("Modificar Medios");
		mntmModificarMedios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				ModMedio.frMoMe.setVisible(true);
			}
		});
		mnMediosTransporte.add(mntmModificarMedios);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCrearReserva = new JButton("Crear Reserva");
		btnCrearReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrearReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				CrearReserva.frame2.setVisible(true);
			}
		});
		btnCrearReserva.setBounds(41, 54, 130, 23);
		contentPane.add(btnCrearReserva);

		JButton btnAnularReserva = new JButton("Anular Reserva");
		btnAnularReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				AnularReserva.frame6.setVisible(true);
			}
		});
		btnAnularReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnularReserva.setBounds(208, 54, 130, 23);
		contentPane.add(btnAnularReserva);

		JButton btnPagarReserva = new JButton("Pagar Reserva");
		btnPagarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				PagarReserva.frame3.setVisible(true);
			}
		});
		btnPagarReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPagarReserva.setBounds(41, 111, 130, 23);
		contentPane.add(btnPagarReserva);

		JButton btnImprimirTicket = new JButton("Imprimir ticket");
		btnImprimirTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				ImprimirTicket.frame4.setVisible(true);
			}
		});
		btnImprimirTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnImprimirTicket.setBounds(208, 111, 130, 23);
		contentPane.add(btnImprimirTicket);

		JButton btnBuscarReserva = new JButton("Buscar Reserva");
		btnBuscarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				BuscarReserva.frame5.setVisible(true);
			}
		});
		btnBuscarReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarReserva.setBounds(41, 169, 130, 23);
		contentPane.add(btnBuscarReserva);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(315, 247, 89, 23);
		contentPane.add(btnSalir);

		JLabel lblAgenciaDeViajes = new JLabel("Agencia de Viajes - V2.0");
		lblAgenciaDeViajes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAgenciaDeViajes.setBounds(88, 11, 232, 32);
		contentPane.add(lblAgenciaDeViajes);

		JButton btnAveriguarLetraDni = new JButton("Averiguar letra DNI");
		btnAveriguarLetraDni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				AveriguaNif.frame7.setVisible(true);
			}
		});
		btnAveriguarLetraDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAveriguarLetraDni.setBounds(208, 169, 160, 23);
		contentPane.add(btnAveriguarLetraDni);

		JButton btnListados = new JButton("Listados");
		btnListados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				Listados.frLiHo.setVisible(true);
			}
		});
		btnListados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListados.setBounds(41, 224, 130, 23);
		contentPane.add(btnListados);
	}
}
