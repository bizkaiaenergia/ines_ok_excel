package ines;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.apache.commons.io.FileUtils;

public class Editar_preferencias_gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
public String TEXT;
private JTextField textField;
//public  JTextArea txtrDaf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editar_preferencias_gui frame = new Editar_preferencias_gui();
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
	public Editar_preferencias_gui() {
		try {
		FileReader fr;
		fr = new FileReader("INES.properties");
		@SuppressWarnings("resource")
		BufferedReader myInput = new BufferedReader(fr);
		String s;
		StringBuffer b = new StringBuffer();
		while ((s = myInput.readLine()) != null) {
			b.append(s);
			b.append("\n");
		 TEXT = b.toString();
		
		}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 53, 857, 498);
		contentPane.add(scrollPane);
		
		final JTextArea txtrDaf = new JTextArea();
		txtrDaf.setText(TEXT);
		scrollPane.setViewportView(txtrDaf);
		
		
		final JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String fichero_origen = "INES.properties";
					Date date = new Date();
					String DATE_FORMAT = "yyyyMMdd";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String fecha = sdf.format(date);
					File file1 = new File(fichero_origen);
					String fichero_destino = "INES.properties.backup" + fecha;
					File file2 = new File(fichero_destino);
					FileUtils.copyFile(file1, file2);
					
					textField.setText("Se ha hecho una copia de seguridad del archivo \n INES.properties a INES.properties.backup"+fecha +" \n");	
					String s = txtrDaf.getText();
					File f = new File("INES.properties");
					FileWriter fw;
					fw = new FileWriter(f);
					fw.write(s);
					fw.close();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
			}
		});
		btnSalvar.setBounds(146, 19, 91, 23);
		contentPane.add(btnSalvar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(771, 19, 91, 23);
		contentPane.add(btnSalir);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(70, 562, 792, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(5, 565, 46, 14);
		contentPane.add(lblEstado);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalvar.setEnabled(true);
				
			}
		});
		btnEditar.setBounds(5, 19, 91, 23);
		contentPane.add(btnEditar);
	}
}
