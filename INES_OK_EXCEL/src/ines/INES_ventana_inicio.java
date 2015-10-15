package ines;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JScrollPane;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.ComponentOrientation;



public class INES_ventana_inicio extends JFrame {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_ruta_archivo_salida_temporal;
	private JTextField txt_servidor_ftp;
	private JTextField txtRetardodiasgeneraciondiario;
	private JTextField txtDiageneracion;
	private JTextField txtHorageneracio;
	public JTextArea textArea ;
	public JCheckBox chckbxDesabilitarMensual;
	public JTextField textField_ULTIMO_FICHERO;
	public JTextField textField_AMBIETA;
	public JTextField textField_AMBIETA7;
	public JTextField textField_AMBIETA23;
	public JTextField textField_ULTIMO_FICHERO_MENSUAL;
	public JTextField textField_AMBIETA_MES;
	public JTextField textField_AMBIETA_BRUTA_MES;
	public JTextField textField_AMBIETA7_MES;
	public JTextField textField_AMBIETA23_MES;
	public JTextArea textArea_listado_no_ok;
	public JTextField textField_horas_faltan;
	public	JProgressBar progressBar;
	public String ruta_archivo_salida_temporal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					INES_ventana_inicio frame = new INES_ventana_inicio();
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
	public INES_ventana_inicio() {
		setTitle("PROGRAMA ENVIO FICHEROS FTP");
		
		
		
		
		propiedades Archivopropiedades = new propiedades();
		//hora de envío generación de archivos diarios.
		String Hora_generacion = Archivopropiedades.hora_generacion();
		//Día de envío generación de archivos 
		String Dia_generacion = Archivopropiedades.dia_generacion();
		
		 ruta_archivo_salida_temporal = Archivopropiedades.ruta_archivo_salida_temporal();
		
		//String ruta_fichero_en_servidor = Archivopropiedades.ruta_fichero_en_servidor();
		
		String servidor_ftp = Archivopropiedades.servidor_ftp();
		
	//	String servidor_ftp_password = Archivopropiedades.servidor_ftp_password();
		
	//	String servidor_ftp_usuario = Archivopropiedades.servidor_ftp_usuario();
		
		Integer retardo_dias_generacion_diario = Archivopropiedades.retardo_dias_generacion_diario();
		
		Boolean disable_monthly = Archivopropiedades.disable_monthly();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 627);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu ayuda = new JMenu("Ayuda");
		ayuda.setHorizontalTextPosition(SwingConstants.LEFT);
		ayuda.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		ayuda.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(ayuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de ..");
		mntmAcercaDe.setEnabled(false);
		ayuda.add(mntmAcercaDe);
		
		JMenuItem mntmVerLaAyuda = new JMenuItem("Ver la ayuda");
		mntmVerLaAyuda.setEnabled(false);
		ayuda.add(mntmVerLaAyuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 1022, 108);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPreferencias = new JLabel("CONFIGURACION");
		lblPreferencias.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreferencias.setBounds(10, 11, 134, 14);
		panel.add(lblPreferencias);
		
		txt_ruta_archivo_salida_temporal = new JTextField();
		txt_ruta_archivo_salida_temporal.setToolTipText("Ruta donde se guarda una copia de los ficheros generado o enviados ");
		txt_ruta_archivo_salida_temporal.setBounds(177, 41, 285, 26);
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
		txt_servidor_ftp.setToolTipText("Servidor FTP y ruta donde se guardan los ficheros");
		txt_servidor_ftp.setBounds(103, 72, 393, 26);
		panel.add(txt_servidor_ftp);
		txt_servidor_ftp.setText(servidor_ftp);
		txt_servidor_ftp.setEditable(false);
		txt_servidor_ftp.setColumns(10);
		
		JButton btnEditarPreferencias = new JButton("Preferencias");
		btnEditarPreferencias.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEditarPreferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Propiedades_gui frame_prop = new Propiedades_gui();
				frame_prop.setVisible(true);
			}
		});
		btnEditarPreferencias.setBounds(889, 8, 123, 23);
		panel.add(btnEditarPreferencias);
		
		txtRetardodiasgeneraciondiario = new JTextField();
		txtRetardodiasgeneraciondiario.setToolTipText("D\u00EDas hacia atras desde \"hoy\" para generar el fichero diario");
		txtRetardodiasgeneraciondiario.setEditable(false);
		DecimalFormat retardo_dias_generacion_diario_decimal = new DecimalFormat("###"); 
		String retardo_dias_generacion_diario_string = retardo_dias_generacion_diario_decimal.format(retardo_dias_generacion_diario);
		txtRetardodiasgeneraciondiario.setText(retardo_dias_generacion_diario_string);
		txtRetardodiasgeneraciondiario.setBounds(635, 47, 48, 20);
		panel.add(txtRetardodiasgeneraciondiario);
		txtRetardodiasgeneraciondiario.setColumns(10);
		
		JLabel lblRetardoEnDias = new JLabel("Retardo en dias :");
		lblRetardoEnDias.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRetardoEnDias.setToolTipText("Retardo en Dias para generaci\u00F3n de fichero diario");
		lblRetardoEnDias.setBounds(542, 50, 86, 14);
		panel.add(lblRetardoEnDias);
		
		txtDiageneracion = new JTextField();
		txtDiageneracion.setToolTipText("D\u00EDa para generar los fichero mensuales");
		txtDiageneracion.setText(Dia_generacion);
		txtDiageneracion.setEditable(false);
		txtDiageneracion.setColumns(10);
		txtDiageneracion.setBounds(805, 47, 36, 20);
		panel.add(txtDiageneracion);
		
		JLabel lblDiaGeneracion = new JLabel("D\u00EDa generacion");
		lblDiaGeneracion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDiaGeneracion.setToolTipText("D\u00EDa del mes para generar el fichero mensual");
		lblDiaGeneracion.setBounds(709, 50, 86, 14);
		panel.add(lblDiaGeneracion);
		
		txtHorageneracio = new JTextField();
		txtHorageneracio.setToolTipText("Hora para generar los ficheros diarios y mensuales el dia que le corresponda");
		txtHorageneracio.setText(Hora_generacion);
		txtHorageneracio.setEditable(false);
		txtHorageneracio.setColumns(10);
		txtHorageneracio.setBounds(982, 47, 30, 20);
		panel.add(txtHorageneracio);
		
		JLabel lblHoraGeneracin = new JLabel("Hora generaci\u00F3n:");
		lblHoraGeneracin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblHoraGeneracin.setToolTipText("Hora de generaci\u00F3n de fichero de d\u00EDa y mensual.");
		lblHoraGeneracin.setBounds(866, 50, 86, 14);
		panel.add(lblHoraGeneracin);
		
		 chckbxDesabilitarMensual = new JCheckBox("Desabilitar mensual");
		 chckbxDesabilitarMensual.setToolTipText("\u00BFEst\u00E1 desabilitada la generaci\u00F3n de ficheros mensuales?");
		//chckbxDesabilitarMensual.setSelected(true);
		chckbxDesabilitarMensual.setEnabled(false);
		chckbxDesabilitarMensual.setBounds(719, 74, 134, 23);
		panel.add(chckbxDesabilitarMensual);
		chckbxDesabilitarMensual.setSelected(disable_monthly);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.setToolTipText("Abrir carpeta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Desktop desktop = null;
					if (Desktop.isDesktopSupported()) {
						desktop = Desktop.getDesktop();
						desktop.open(new File(ruta_archivo_salida_temporal));
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				/*try{
					
				String	salida_explorer = ruta_archivo_salida_temporal.replace("//", "\");	
				  
				String cmdLine = "explorer " + ruta_archivo_salida_temporal ;
				
				@SuppressWarnings("unused")
				Process p = Runtime.getRuntime().exec(cmdLine);
				//Le damos tiempo a la macro a terminar la generaci�n
				//Thread.sleep(120000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				*/
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(472, 43, 24, 23);
		panel.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
		btnSalir.setBounds(943, 535, 89, 23);
		contentPane.add(btnSalir);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 437, 506, 88);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblSalidaDeMensajes = new JLabel("SALIDA DE MENSAJES");
		lblSalidaDeMensajes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalidaDeMensajes.setBounds(10, 412, 206, 14);
		contentPane.add(lblSalidaDeMensajes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 130, 506, 204);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(526, 130, 506, 204);
		contentPane.add(panel_2);
		
		textField_ULTIMO_FICHERO_MENSUAL = new JTextField();
		textField_ULTIMO_FICHERO_MENSUAL.setEditable(false);
		textField_ULTIMO_FICHERO_MENSUAL.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_ULTIMO_FICHERO_MENSUAL.setColumns(10);
		textField_ULTIMO_FICHERO_MENSUAL.setBounds(10, 35, 440, 20);
		panel_2.add(textField_ULTIMO_FICHERO_MENSUAL);
		
		
		textField_ULTIMO_FICHERO = new JTextField();
		textField_ULTIMO_FICHERO.setEditable(false);
		textField_ULTIMO_FICHERO.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_ULTIMO_FICHERO.setBounds(10, 35, 453, 20);
		panel_1.add(textField_ULTIMO_FICHERO);
		textField_ULTIMO_FICHERO.setColumns(10);
		
		JLabel lblUltimoFicheroDiario = new JLabel("ULTIMO FICHERO DIARIO GENERADO");
		lblUltimoFicheroDiario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUltimoFicheroDiario.setBounds(10, 10, 411, 14);
		panel_1.add(lblUltimoFicheroDiario);
		
		textField_AMBIETA = new JTextField();
		textField_AMBIETA.setEditable(false);
		textField_AMBIETA.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_AMBIETA.setBounds(287, 95, 195, 20);
		panel_1.add(textField_AMBIETA);
		textField_AMBIETA.setColumns(10);
		
		textField_AMBIETA7 = new JTextField();
		textField_AMBIETA7.setEditable(false);
		textField_AMBIETA7.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_AMBIETA7.setColumns(10);
		textField_AMBIETA7.setBounds(287, 127, 195, 20);
		panel_1.add(textField_AMBIETA7);
		
		textField_AMBIETA23 = new JTextField();
		textField_AMBIETA23.setEditable(false);
		textField_AMBIETA23.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_AMBIETA23.setColumns(10);
		textField_AMBIETA23.setBounds(287, 158, 195, 20);
		panel_1.add(textField_AMBIETA23);
		
		JLabel lblAmbieta = new JLabel("AMBIETA - Energia Bruta Diaria (MWh)");
		lblAmbieta.setBounds(10, 98, 267, 14);
		panel_1.add(lblAmbieta);
		
		JLabel lblAmbieta_1 = new JLabel("AMBIETA7 - Consumo de Gas Diario (Nm3)");
		lblAmbieta_1.setBounds(10, 130, 267, 14);
		panel_1.add(lblAmbieta_1);
		
		JLabel lblAmbieta_2 = new JLabel("AMBIETA23 - Consumo de Gas Diario (Termias)");
		lblAmbieta_2.setBounds(10, 161, 267, 14);
		panel_1.add(lblAmbieta_2);
		
		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Desktop desktop = null;
					desktop = Desktop.getDesktop();
					String archivo = textField_ULTIMO_FICHERO.getText();
					desktop.open(new File(ruta_archivo_salida_temporal + "\\"+  archivo));
					
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
					
			}
		});
		btnNewButton_1.setToolTipText("Abrir archivo");
		btnNewButton_1.setBounds(473, 34, 23, 23);
		panel_1.add(btnNewButton_1);
		
	
	
		JLabel lblUltimoFicheroMensual = new JLabel("ULTIMO FICHERO MENSUAL GENERADO");
		lblUltimoFicheroMensual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUltimoFicheroMensual.setBounds(10, 10, 454, 14);
		panel_2.add(lblUltimoFicheroMensual);
		
		textField_AMBIETA_MES = new JTextField();
		textField_AMBIETA_MES.setEditable(false);
		textField_AMBIETA_MES.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_AMBIETA_MES.setColumns(10);
		textField_AMBIETA_MES.setBounds(287, 66, 195, 20);
		panel_2.add(textField_AMBIETA_MES);
		
		textField_AMBIETA_BRUTA_MES = new JTextField();
		textField_AMBIETA_BRUTA_MES.setEditable(false);
		textField_AMBIETA_BRUTA_MES.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_AMBIETA_BRUTA_MES.setColumns(10);
		textField_AMBIETA_BRUTA_MES.setBounds(287, 98, 195, 20);
		panel_2.add(textField_AMBIETA_BRUTA_MES);
		
		textField_AMBIETA7_MES = new JTextField();
		textField_AMBIETA7_MES.setEditable(false);
		textField_AMBIETA7_MES.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_AMBIETA7_MES.setColumns(10);
		textField_AMBIETA7_MES.setBounds(287, 129, 195, 20);
		panel_2.add(textField_AMBIETA7_MES);
		
		JLabel lblAmbietaEnergia = new JLabel("AMBIETA - Energia NETA MES (MWh)");
		lblAmbietaEnergia.setBounds(10, 69, 267, 14);
		panel_2.add(lblAmbietaEnergia);
		
		JLabel lblAmbietaEnergia_1 = new JLabel("AMBIETA - Energia Bruta MES (MWh)");
		lblAmbietaEnergia_1.setBounds(10, 101, 267, 14);
		panel_2.add(lblAmbietaEnergia_1);
		
		JLabel lblAmbietaConsumo = new JLabel("AMBIETA7 - Consumo de Gas MES (Nm3)");
		lblAmbietaConsumo.setBounds(10, 132, 267, 14);
		panel_2.add(lblAmbietaConsumo);
		
		JLabel label = new JLabel("AMBIETA23 - Consumo de Gas MES (Termias)");
		label.setBounds(10, 163, 267, 14);
		panel_2.add(label);
		
		textField_AMBIETA23_MES = new JTextField();
		textField_AMBIETA23_MES.setEditable(false);
		textField_AMBIETA23_MES.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_AMBIETA23_MES.setColumns(10);
		textField_AMBIETA23_MES.setBounds(287, 160, 195, 20);
		panel_2.add(textField_AMBIETA23_MES);
		
		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				
				Desktop desktop = null;
				desktop = Desktop.getDesktop();
				String archivo = textField_ULTIMO_FICHERO_MENSUAL.getText();
				desktop.open(new File(ruta_archivo_salida_temporal + "\\"+  archivo));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
				
			}
		});
		btnNewButton_2.setToolTipText("Abrir fichero mensual");
		btnNewButton_2.setBounds(460, 34, 22, 23);
		panel_2.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(526, 437, 506, 88);
		contentPane.add(scrollPane);
		
		textArea_listado_no_ok = new JTextArea();
		scrollPane.setViewportView(textArea_listado_no_ok);
		
		JLabel lblEstadoUltimosMensajes = new JLabel("MENSAJES NO OK. (A\u00D1O ACTUAL)");
		lblEstadoUltimosMensajes.setToolTipText("Listado de mensajes en el servidor ftp que no se ha importado correctamente");
		lblEstadoUltimosMensajes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstadoUltimosMensajes.setBounds(526, 412, 433, 14);
		contentPane.add(lblEstadoUltimosMensajes);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 342, 506, 65);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_1 = new JLabel("Tiempo para generaci\u00F3n fichero diario");
		label_1.setBounds(10, 3, 313, 14);
		panel_3.add(label_1);
		
		progressBar = new JProgressBar();
		progressBar.setToolTipText("Esperando generacion nuevo fichero");
		progressBar.setStringPainted(true);
		progressBar.setMaximum(24);
		progressBar.setBounds(10, 28, 265, 26);
		panel_3.add(progressBar);
		
		JLabel label_2 = new JLabel("Faltan(Horas)");
		label_2.setBounds(287, 34, 110, 14);
		panel_3.add(label_2);
		
		textField_horas_faltan = new JTextField();
		textField_horas_faltan.setEditable(false);
		textField_horas_faltan.setColumns(10);
		textField_horas_faltan.setBounds(393, 31, 39, 20);
		panel_3.add(textField_horas_faltan);
		
		JButton button = new JButton("Generaci\u00F3n ficheros forzados");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ficheros_forzados  new_ficheros_forzados = new ficheros_forzados();
				
				new_ficheros_forzados.setVisible(true);
			}
		});
		button.setBounds(536, 535, 258, 23);
		contentPane.add(button);
	}
}
