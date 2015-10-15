package ines;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class Propiedades_gui extends JFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_ruta_archivo_salida_temporal;
	private JTextField txt_servidor_ftp;
	private JTextField txt_ruta_fichero_en_servidor;
	private JTextField txtUsuario;
	private JTextField txtContrasea;
	private JTextField txtRetardodiasgeneraciondiario;
	private JTextField txtDiageneracion;
	private JTextField txtHorageneracio;
	public JCheckBox chckbxDesabilitarMensual;
	private JTextField textField_puerto;
	private JTextField textField_ficheros_ok;
	private JTextField textField_nombre_base_de_datos;
	private JTextField textField_ruta_base_datos;
	private JTextField textField_fichero_PI_excel;
	private JTextField textField_ruta_excel;
	private JTextField textField_ruta_instalacion;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Propiedades_gui frame = new Propiedades_gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Propiedades_gui() {
		
		
		
		
		propiedades Archivopropiedades = new propiedades();
		//hora de envío generación de archivos diarios.
		String Hora_generacion = Archivopropiedades.hora_generacion();
		//Día de envío generación de archivos 
		String Dia_generacion = Archivopropiedades.dia_generacion();
		
		String ruta_archivo_salida_temporal = Archivopropiedades.ruta_archivo_salida_temporal();
		
		String ruta_fichero_en_servidor = Archivopropiedades.ruta_fichero_en_servidor();
		
		String servidor_ftp = Archivopropiedades.servidor_ftp();
		
		String servidor_ftp_password = Archivopropiedades.servidor_ftp_password();
		
		String servidor_ftp_usuario = Archivopropiedades.servidor_ftp_usuario();
		
		Integer retardo_dias_generacion_diario = Archivopropiedades.retardo_dias_generacion_diario();
		
		Boolean disable_monthly = Archivopropiedades.disable_monthly();
		
		String ruta_ficheros_ok = Archivopropiedades.ruta_ficheros_ok();

		String ruta_instalacion = Archivopropiedades.ruta_instalacion();

		String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();
		
		String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();
		
		Integer servidor_ftp_puerto = Archivopropiedades.servidor_ftp_puerto();
			
		String ruta_excel = Archivopropiedades.ruta_excel();
		
		String fichero_PI_excel = Archivopropiedades.fichero_PI_excel();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 506, 523);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPreferencias = new JLabel("PREFERENCIAS");
		lblPreferencias.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreferencias.setBounds(10, 11, 134, 14);
		panel.add(lblPreferencias);
		
		txt_ruta_archivo_salida_temporal = new JTextField();
		txt_ruta_archivo_salida_temporal.setBounds(177, 41, 319, 26);
		panel.add(txt_ruta_archivo_salida_temporal);
		txt_ruta_archivo_salida_temporal.setEditable(false);
		txt_ruta_archivo_salida_temporal.setText(ruta_archivo_salida_temporal);
		txt_ruta_archivo_salida_temporal.setColumns(10);
		
		JLabel lblRutaFicheroSalida = new JLabel("Ruta fichero salida temporal:");
		lblRutaFicheroSalida.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRutaFicheroSalida.setBounds(10, 47, 157, 14);
		panel.add(lblRutaFicheroSalida);
		
		JLabel lblServidorftp = new JLabel("Servidor FTP:");
		lblServidorftp.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblServidorftp.setBounds(10, 78, 86, 14);
		panel.add(lblServidorftp);
		
		txt_servidor_ftp = new JTextField();
		txt_servidor_ftp.setBounds(103, 72, 294, 26);
		panel.add(txt_servidor_ftp);
		txt_servidor_ftp.setText(servidor_ftp);
		txt_servidor_ftp.setEditable(false);
		txt_servidor_ftp.setColumns(10);
		
		JLabel lblRutaficheroenservidor = new JLabel("Ruta fichero en servidor:");
		lblRutaficheroenservidor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRutaficheroenservidor.setBounds(10, 113, 134, 14);
		panel.add(lblRutaficheroenservidor);
		
		txt_ruta_fichero_en_servidor = new JTextField();
		txt_ruta_fichero_en_servidor.setBounds(148, 107, 348, 26);
		panel.add(txt_ruta_fichero_en_servidor);
		txt_ruta_fichero_en_servidor.setText(ruta_fichero_en_servidor);
		txt_ruta_fichero_en_servidor.setEditable(false);
		txt_ruta_fichero_en_servidor.setColumns(10);
		
		JButton btnEditarPreferencias = new JButton("Editar preferencias");
		btnEditarPreferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Editar_preferencias_gui frame3 = new Editar_preferencias_gui();
				frame3.setVisible(true);
				
				
			}
		});
		
		btnEditarPreferencias.setBounds(10, 489, 171, 23);
		panel.add(btnEditarPreferencias);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblUsuario.setBounds(10, 177, 48, 14);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setText(servidor_ftp_usuario);
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(58, 171, 92, 26);
		panel.add(txtUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblContrasea.setBounds(158, 177, 60, 14);
		panel.add(lblContrasea);
		
		txtContrasea = new JTextField();
		txtContrasea.setText(servidor_ftp_password);
		txtContrasea.setEditable(false);
		txtContrasea.setColumns(10);
		txtContrasea.setBounds(228, 171, 86, 26);
		panel.add(txtContrasea);
		
		txtRetardodiasgeneraciondiario = new JTextField();
		txtRetardodiasgeneraciondiario.setEditable(false);
		DecimalFormat retardo_dias_generacion_diario_decimal = new DecimalFormat("###"); 
		String retardo_dias_generacion_diario_string = retardo_dias_generacion_diario_decimal.format(retardo_dias_generacion_diario);
		txtRetardodiasgeneraciondiario.setText(retardo_dias_generacion_diario_string);
		txtRetardodiasgeneraciondiario.setBounds(119, 400, 48, 20);
		panel.add(txtRetardodiasgeneraciondiario);
		txtRetardodiasgeneraciondiario.setColumns(10);
		
		JLabel lblRetardoEnDias = new JLabel("Retardo en dias :");
		lblRetardoEnDias.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRetardoEnDias.setToolTipText("Retardo en Dias para generaci\u00F3n de fichero diario");
		lblRetardoEnDias.setBounds(10, 403, 86, 14);
		panel.add(lblRetardoEnDias);
		
		txtDiageneracion = new JTextField();
		txtDiageneracion.setText(Dia_generacion);
		txtDiageneracion.setEditable(false);
		txtDiageneracion.setColumns(10);
		txtDiageneracion.setBounds(282, 400, 36, 20);
		panel.add(txtDiageneracion);
		
		JLabel lblDiaGeneracion = new JLabel("D\u00EDa generacion");
		lblDiaGeneracion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDiaGeneracion.setToolTipText("D\u00EDa del mes para generar el fichero mensual");
		lblDiaGeneracion.setBounds(186, 403, 86, 14);
		panel.add(lblDiaGeneracion);
		
		txtHorageneracio = new JTextField();
		txtHorageneracio.setText(Hora_generacion);
		txtHorageneracio.setEditable(false);
		txtHorageneracio.setColumns(10);
		txtHorageneracio.setBounds(455, 400, 30, 20);
		panel.add(txtHorageneracio);
		
		JLabel lblHoraGeneracin = new JLabel("Hora generaci\u00F3n:");
		lblHoraGeneracin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblHoraGeneracin.setToolTipText("Hora de generaci\u00F3n de fichero de d\u00EDa y mensual.");
		lblHoraGeneracin.setBounds(339, 403, 86, 14);
		panel.add(lblHoraGeneracin);
		
		 chckbxDesabilitarMensual = new JCheckBox("Desabilitar mensual");
		//chckbxDesabilitarMensual.setSelected(true);
		chckbxDesabilitarMensual.setEnabled(false);
		chckbxDesabilitarMensual.setBounds(186, 437, 134, 23);
		panel.add(chckbxDesabilitarMensual);
		chckbxDesabilitarMensual.setSelected(disable_monthly);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(177, 394, 157, 84);
		panel.add(panel_1);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPuerto.setBounds(407, 78, 36, 14);
		panel.add(lblPuerto);
		
		textField_puerto = new JTextField();
		DecimalFormat puerto_format_decimal = new DecimalFormat("###"); 
		String puerto = puerto_format_decimal.format(servidor_ftp_puerto);
			
		textField_puerto.setText(puerto);
		textField_puerto.setEditable(false);
		textField_puerto.setColumns(10);
		textField_puerto.setBounds(455, 75, 41, 20);
		panel.add(textField_puerto);
		
		JLabel lblRutaFicherosOk = new JLabel("Ruta ficheros OK:");
		lblRutaFicherosOk.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRutaFicherosOk.setBounds(10, 143, 134, 14);
		
		panel.add(lblRutaFicherosOk);
		
		textField_ficheros_ok = new JTextField();
		//textField_ficheros_ok.setText((String) null);

		textField_ficheros_ok.setText(ruta_ficheros_ok);
		
		textField_ficheros_ok.setEditable(false);
		textField_ficheros_ok.setColumns(10);
		textField_ficheros_ok.setBounds(148, 140, 348, 26);
		panel.add(textField_ficheros_ok);
		
		JLabel lblServidorBaseDe = new JLabel("Base de datos:");
		lblServidorBaseDe.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblServidorBaseDe.setBounds(10, 205, 86, 14);
		panel.add(lblServidorBaseDe);
		
		textField_nombre_base_de_datos = new JTextField();
		textField_nombre_base_de_datos.setText((String) null);
		textField_nombre_base_de_datos.setText(nombre_servidor_odbc);
		textField_nombre_base_de_datos.setEditable(false);
		textField_nombre_base_de_datos.setColumns(10);
		textField_nombre_base_de_datos.setBounds(95, 199, 348, 26);
		panel.add(textField_nombre_base_de_datos);
		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRuta.setBounds(10, 233, 86, 14);
		panel.add(lblRuta);
		
		textField_ruta_base_datos = new JTextField();
		//textField_ruta_base_datos.setText((String) null);
		textField_ruta_base_datos.setText(ruta_servidor_odbc);
		textField_ruta_base_datos.setEditable(false);
		textField_ruta_base_datos.setColumns(10);
		textField_ruta_base_datos.setBounds(95, 227, 348, 26);
		panel.add(textField_ruta_base_datos);
		
		JLabel lblFicheroExcel = new JLabel("Fichero excel");
		lblFicheroExcel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFicheroExcel.setBounds(10, 270, 86, 14);
		panel.add(lblFicheroExcel);
		
		JLabel lblEjecutableExcel = new JLabel("Ejecutable excel");
		lblEjecutableExcel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEjecutableExcel.setBounds(10, 298, 86, 14);
		panel.add(lblEjecutableExcel);
		
		textField_fichero_PI_excel = new JTextField();
		textField_fichero_PI_excel.setText(fichero_PI_excel);
		textField_fichero_PI_excel.setEditable(false);
		textField_fichero_PI_excel.setColumns(10);
		textField_fichero_PI_excel.setBounds(95, 264, 348, 26);
		panel.add(textField_fichero_PI_excel);
		
		textField_ruta_excel = new JTextField();
		textField_ruta_excel.setText(ruta_excel);
		textField_ruta_excel.setEditable(false);
		textField_ruta_excel.setColumns(10);
		textField_ruta_excel.setBounds(95, 292, 348, 26);
		panel.add(textField_ruta_excel);
		
		JLabel lblRutaInstalacin = new JLabel("Ruta Instalaci\u00F3n:");
		lblRutaInstalacin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRutaInstalacin.setBounds(10, 329, 86, 14);
		panel.add(lblRutaInstalacin);
		
		textField_ruta_instalacion = new JTextField();
		//textField_ruta_instalacion.setText((String) null);
		textField_ruta_instalacion.setText(ruta_instalacion);
		textField_ruta_instalacion.setEditable(false);
		textField_ruta_instalacion.setColumns(10);
		textField_ruta_instalacion.setBounds(95, 323, 348, 26);
		panel.add(textField_ruta_instalacion);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(427, 545, 89, 23);
		contentPane.add(btnSalir);
	
		
		
		
		
		
		
		
		
		
	}
}
