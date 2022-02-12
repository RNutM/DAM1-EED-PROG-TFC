package listados;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import principal.AccesoDatos;
import principal.InicioAG;

public class Listados extends JFrame {
	/**
	 * Agencia de Viajes - Clase Listados
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static Listados frLiHo;
	private JScrollPane scrollPane_1;

	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Create the frame.
	 */
	public Listados() {
		setTitle("Listados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));// Color del fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSeccinDeListados = new JLabel("Secci\u00F3n de listados de la Base de Datos");
		lblSeccinDeListados.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblSeccinDeListados.setBounds(120, 11, 315, 25);
		contentPane.add(lblSeccinDeListados);

		JButton btnListarCiudades = new JButton("Listar Ciudades");
		btnListarCiudades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setColumnCount(0);// Borro columnas del JTable
				model.setRowCount(0);// Borro filas del JTAble

				model.addColumn("Cod. Ciudad");
				model.addColumn("Nombre de la Ciudad");
				model.addColumn("País de la Ciudad");

				table.setModel(model);// Asignamos a la variable "table" el modelo "model"

				String[] ciudades = new String[3];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD("select * from ciudades");

					while (InicioAG.rset.next()) {

						ciudades[0] = InicioAG.rset.getString(1);
						ciudades[1] = InicioAG.rset.getString(2);
						ciudades[2] = InicioAG.rset.getString(3);

						model.addRow(ciudades);
						// Lo muestro por consola
						// System.out.println(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListarCiudades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarCiudades.setBounds(20, 49, 120, 25);
		contentPane.add(btnListarCiudades);

		JButton btnListarClientes = new JButton("Listar Clientes");
		btnListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setColumnCount(0);// Borro columnas del JTable
				model.setRowCount(0);// Borro filas del JTAble

				model.addColumn("DNI");
				model.addColumn("Nombre del Cliente");
				model.addColumn("Apellidos del Cliente");
				model.addColumn("Teléfono del Cliente");

				table.setModel(model);// Asignamos a la variable "table" el modelo "model"

				String[] clientes = new String[4];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD("select * from clientes");

					while (InicioAG.rset.next()) {

						clientes[0] = InicioAG.rset.getString(1);
						clientes[1] = InicioAG.rset.getString(2);
						clientes[2] = InicioAG.rset.getString(3);
						clientes[3] = InicioAG.rset.getString(4);

						model.addRow(clientes);
						// Lo muestro por consola
						// System.out.println(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListarClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarClientes.setBounds(150, 49, 120, 25);
		contentPane.add(btnListarClientes);

		JButton btnListarHoteles = new JButton("Listar Hoteles");
		btnListarHoteles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setColumnCount(0);// Borro columnas del JTable
				model.setRowCount(0);// Borro filas del JTAble

				model.addColumn("Cod. Hotel");
				model.addColumn("Nombre del Hotel");
				model.addColumn("Dirección del Hotel");
				model.addColumn("Precio Hotel");
				model.addColumn("Cod. Ciudad");

				table.setModel(model);// Asignamos a la variable "table" el modelo "model"

				String[] hoteles = new String[5];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD("select * from hoteles");

					while (InicioAG.rset.next()) {

						hoteles[0] = InicioAG.rset.getString(1);
						hoteles[1] = InicioAG.rset.getString(2);
						hoteles[2] = InicioAG.rset.getString(3);
						hoteles[3] = InicioAG.rset.getString(4);
						hoteles[4] = InicioAG.rset.getString(5);

						model.addRow(hoteles);
						// Lo muestro por consola
						// System.out.println(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListarHoteles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarHoteles.setBounds(280, 49, 120, 25);
		contentPane.add(btnListarHoteles);

		JButton btnListarMedios = new JButton("Listar Medios");
		btnListarMedios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setColumnCount(0);// Borro columnas del JTable
				model.setRowCount(0);// Borro filas del JTAble

				model.addColumn("Cod. Medio");
				model.addColumn("Nombre del Medio");

				table.setModel(model);// Asignamos a la variable "table" el modelo "model"

				String[] medios = new String[2];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD("select * from mediostransporte");

					while (InicioAG.rset.next()) {

						medios[0] = InicioAG.rset.getString(1);
						medios[1] = InicioAG.rset.getString(2);

						model.addRow(medios);
						// Lo muestro por consola
						// System.out.println(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListarMedios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarMedios.setBounds(410, 49, 120, 25);
		contentPane.add(btnListarMedios);

		JButton btnListarReservas = new JButton("Listar Reservas");
		btnListarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setColumnCount(0);// Borro columnas del JTable
				model.setRowCount(0);// Borro filas del JTAble

				model.addColumn("DNI");
				model.addColumn("Cod. Viaje");
				model.addColumn("Nº Personas");
				model.addColumn("Precio Total");
				model.addColumn("Cod. Hotel");

				table.setModel(model);// Asignamos a la variable "table" el modelo "model"

				String[] reservas = new String[5];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD("select * from reservas");

					while (InicioAG.rset.next()) {

						reservas[0] = InicioAG.rset.getString(1);
						reservas[1] = InicioAG.rset.getString(2);
						reservas[2] = InicioAG.rset.getString(3);
						reservas[3] = InicioAG.rset.getString(4);
						reservas[4] = InicioAG.rset.getString(5);

						model.addRow(reservas);
						// Lo muestro por consola
						// System.out.println(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListarReservas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarReservas.setBounds(540, 49, 120, 25);
		contentPane.add(btnListarReservas);

		JButton btnListarViajes = new JButton("Listar Viajes");
		btnListarViajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setColumnCount(0);// Borro columnas del JTable
				model.setRowCount(0);// Borro filas del JTAble

				model.addColumn("Cod. Viaje");
				model.addColumn("Ciudad Origen");
				model.addColumn("Ciudad Destino");
				model.addColumn("Cod. Medio");
				model.addColumn("Precio Viaje");

				table.setModel(model);// Asignamos a la variable "table" el modelo "model"

				String[] viajes = new String[5];

				try {
					InicioAG.rset = AccesoDatos.ConsultaBD("select * from viajes");

					while (InicioAG.rset.next()) {

						viajes[0] = InicioAG.rset.getString(1);
						viajes[1] = InicioAG.rset.getString(2);
						viajes[2] = InicioAG.rset.getString(3);
						viajes[3] = InicioAG.rset.getString(4);
						viajes[4] = InicioAG.rset.getString(5);

						model.addRow(viajes);
						// Lo muestro por consola
						// System.out.println(InicioAG.rset.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListarViajes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarViajes.setBounds(670, 49, 120, 25);
		contentPane.add(btnListarViajes);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 120, 796, 290);
		contentPane.add(scrollPane_1);

		table = new JTable();
		table.setEnabled(false);
		scrollPane_1.setViewportView(table);

		JButton btnBorrarJTable = new JButton("Borrar Tabla");
		btnBorrarJTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setColumnCount(0);// Borro columnas del JTable
				model.setRowCount(0);// Borro filas del JTAble
			}
		});
		btnBorrarJTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBorrarJTable.setBounds(20, 427, 105, 23);
		contentPane.add(btnBorrarJTable);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frLiHo.setVisible(false);
				principal.InicioAG.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(711, 421, 105, 23);
		contentPane.add(btnVolver);
	}
}
