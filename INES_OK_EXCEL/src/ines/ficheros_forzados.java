package ines;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ficheros_forzados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel contentPane;
	
	
	public JFormattedTextField textField_2;
	public JFormattedTextField textField_3;
	public JTextArea textArea; 
	public  JFormattedTextField textField;
	public JFormattedTextField textField_1;
	public String[] datos_fichero_diario;
	public String[] datos_fichero_mensual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ficheros_forzados frame = new ficheros_forzados();
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
	public ficheros_forzados() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(25, 46, 609, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDiarios = new JLabel("Diarios");
		lblDiarios.setBounds(10, 11, 113, 14);
		panel.add(lblDiarios);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(20, 36, 46, 14);
		panel.add(lblFecha);
		
		/*
		textField = new JTextField();
		textField.setBounds(76, 33, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		*/
		
		JLabel lblVersin = new JLabel("Versi\u00F3n");
		lblVersin.setBounds(211, 36, 46, 14);
		panel.add(lblVersin);
		
		/*		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(267, 33, 22, 20);
		panel.add(textField_1);
		*/
		NumberFormat version_format;
		version_format = NumberFormat.getNumberInstance();
		version_format.setMaximumIntegerDigits(2);
		textField_1 =  new JFormattedTextField(version_format);
		textField_1.setColumns(10);
		textField_1.setBounds(267, 33, 22, 20);
		panel.add(textField_1);
		
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String DATE_FORMAT = "dd/MM/yyyy";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String Fecha_string =textField.getText();
					//String Fecha_string = año_actual + "-" + mes_actual + "-" + dia_actual;
					Integer version =  Integer.parseInt(textField_1.getText());
					//Integer version = 1;
					Date Fecha;
					Fecha = sdf.parse(Fecha_string);
					
				/*	Format formatter = new SimpleDateFormat("dd");
					String dia= formatter.format(Fecha);
					formatter = new SimpleDateFormat("yyyy");
					String año= formatter.format(Fecha);
					formatter = new SimpleDateFormat("MMM");
					String mes= formatter.format(Fecha);
					*/
					
					Date Fecha_del_fichero_a_generar = Fecha;
					
			//	generacion_ficheros Nuevo_generar_ficheros = new generacion_ficheros();
				datos_fichero_diario = generacion_ficheros.generar_ficheros_diarios(Fecha_del_fichero_a_generar, version);
				//StyledDocument doc = textPane.getStyledDocument();
				
				textArea.append(" Se ha enviado ... " + datos_fichero_diario[4].toString()+ "\n");
				
				//textArea.append("Se ha generado fichero de la fecha " + dia + " de " + mes + " del " + año + " y versión " + version +"\n");
					}catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnGenerar.setBounds(485, 32, 89, 23);
		panel.add(btnGenerar);
		
		textField =  new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		textField.setBounds(76, 36, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(25, 134, 609, 77);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMensuales = new JLabel("Mensuales");
		lblMensuales.setBounds(10, 11, 112, 14);
		panel_1.add(lblMensuales);
		
		JLabel label = new JLabel("Fecha");
		label.setBounds(20, 39, 46, 14);
		panel_1.add(label);
		
		/*
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(76, 36, 86, 20);
		panel_1.add(textField_2);
		*/
		
		
		JLabel label_1 = new JLabel("Versi\u00F3n");
		label_1.setBounds(211, 39, 46, 14);
		panel_1.add(label_1);
		
		textField_3 = new JFormattedTextField(version_format);
		textField_3.setColumns(10);
		textField_3.setBounds(267, 36, 22, 20);
		panel_1.add(textField_3);
		
		JButton button = new JButton("Generar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				String DATE_FORMAT = "dd/MM/yyyy";
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				String Fecha_string = textField_2.getText();
				Integer version =  Integer.parseInt(textField_3.getText());
				Date Fecha;
				Fecha = sdf.parse(Fecha_string);
						
				String dia = "01";
				Format formatter = new SimpleDateFormat("yyyy");
				String año= formatter.format(Fecha);
				//formatter = new SimpleDateFormat("MMM");
				//String mes_largo= formatter.format(Fecha);
				formatter = new SimpleDateFormat("MM");
				String mes= formatter.format(Fecha);
				
				Date Fecha2 = sdf.parse(dia + "/" + mes + "/" + año);
				
				GregorianCalendar cal_fecha = new GregorianCalendar ();
				cal_fecha.setTime(Fecha2);
				cal_fecha.add(Calendar.MONTH,-1);
			/*	Date Fecha_menos_un_mes = cal_fecha.getTime();
				formatter = new SimpleDateFormat("yyyy");
				String año_menos_un_mes = formatter.format(Fecha_menos_un_mes);
				formatter = new SimpleDateFormat("MM");
				String mes_menos_un_mes= formatter.format(Fecha_menos_un_mes);
			*/	
			//	generacion_ficheros Nuevo_generar_ficheros = new generacion_ficheros();
				
				  datos_fichero_mensual = generacion_ficheros.generar_ficheros_mensuales(Fecha2, version);
				
			//	textArea.append("Se ha generado fichero MENSUAL del MES" + mes_menos_un_mes + " del " + año_menos_un_mes + " y versión " + version +"\n");
					textArea.append(" Se ha enviado ... " + datos_fichero_mensual[5].toString()+ "\n");

				}catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
				
				
			}
		});
		button.setBounds(488, 35, 89, 23);
		panel_1.add(button);
		
		textField_2 =  new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		textField_2.setBounds(76, 36, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel titulo = new JLabel("GENERACI\u00D3N DE FICHEROS FORZADOS");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titulo.setBounds(25, 11, 330, 14);
		contentPane.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 257, 609, 151);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(545, 484, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblMensajes = new JLabel("Mensajes");
		lblMensajes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensajes.setBounds(25, 231, 330, 14);
		contentPane.add(lblMensajes);
	}
}
