package ines;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

public class generacion_ficheros {
	public static String[] datos_fichero_diario = {"","","","",""};
	public static String[] datos_fichero_mes = {"","","","","",""};
	public static String[] generar_ficheros_diarios(Date Fecha,Integer version){
		try{
		///access
			propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			Statement s = conexion.createStatement(); 
			//Generación de fecha inicio y fin			
			GregorianCalendar cal_fecha = new GregorianCalendar ();
			GregorianCalendar cal_fecha2 = new GregorianCalendar ();
			GregorianCalendar cal_fecha3 = new GregorianCalendar ();
			cal_fecha.setTime(Fecha);
			cal_fecha2.setTime(Fecha);
			cal_fecha3.setTime(Fecha);
			cal_fecha2.add(Calendar.DATE, 1);
			cal_fecha3.add(Calendar.DATE,-1);
			Date Fecha_mas_un_dia = cal_fecha2.getTime();
			Date Fecha_menos_un_dia = cal_fecha3.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_string = sdf.format(Fecha);
			String fecha_mas_un_dia_string = sdf.format(Fecha_mas_un_dia);
			String fecha_menos_un_dia_string = sdf.format(Fecha_menos_un_dia);
			//Sacamos año, mes, dia con SimpleDateFormat
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
			String dia = sdf1.format(Fecha);
			String mes = sdf2.format(Fecha);
			String año = sdf3.format(Fecha);
			//Variables con los valores.
			double Gas_AMBIETA_hoy=0;
			double Gas_AMBIETA_hoy_menos1=0;
			//Strings para presentar
			String AMBIETA_diario_string ;
			String AMBIETA23_diario_string;
			String AMBIETA7_diario_string;
			double Gas_AMBIETA23_hoy=0;
			double Gas_AMBIETA23_hoy_menos1=0;
			double Gas_AMBIETA7_hoy=0;
			double Gas_AMBIETA7_hoy_menos1=0;
			String contenido;
		
			///CALCULO DE AMBIETA DIARIO
		
			String query_hoy = "SELECT `Fecha de Turno`, `ContadorTG1`,`ContadorTG2`, `ContadorST` FROM `RegistrosContadoresTurbinas` AS `turb` WHERE `Fecha de Turno` > {D '"+ fecha_string +"'} AND `Fecha de Turno` < {D '"+fecha_mas_un_dia_string+"'}";
			//	System.out.println(query_hoy);
			ResultSet rs_Contador_hoy = s.executeQuery (query_hoy);
			 			
			while (rs_Contador_hoy.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				Gas_AMBIETA_hoy = rs_Contador_hoy.getDouble(2)+ rs_Contador_hoy.getDouble(3)+rs_Contador_hoy.getDouble(4);	
				//System.out.println (Gas_AMBIETA_hoy); 
		
			}
		
			String query_hoy_menos_1 = "SELECT `Fecha de Turno`, `ContadorTG1`,`ContadorTG2`, `ContadorST` FROM `RegistrosContadoresTurbinas` AS `turb` WHERE `Fecha de Turno` > {D '"+ fecha_menos_un_dia_string +"'} AND `Fecha de Turno` < {D '"+fecha_string+"'}";
			//System.out.println(query_hoy_menos_1);
			ResultSet rs_Contador_hoy_menos_1 = s.executeQuery (query_hoy_menos_1);
			while (rs_Contador_hoy_menos_1.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				Gas_AMBIETA_hoy_menos1 = rs_Contador_hoy_menos_1.getDouble(2)+ rs_Contador_hoy_menos_1.getDouble(3)+rs_Contador_hoy_menos_1.getDouble(4);	
				//System.out.println (Gas_AMBIETA_hoy_menos1); 
			}
		
			DecimalFormat dec = new DecimalFormat("##0.00"); 
			double AMBIETA_diario = Gas_AMBIETA_hoy - Gas_AMBIETA_hoy_menos1;
			AMBIETA_diario_string = dec.format(AMBIETA_diario/1000);
			System.out.println("AMBIETA = " +AMBIETA_diario_string);
		
			// HASTA AQUI AMBIETA DIARIO.
			//AHORA AMBIETA 23
			
			query_hoy = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAE`,`CorrectorLineaBE`, `CorrectorLineaDE`,`CorrectorLineaEE` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ fecha_string +"'} AND `Fecha de Turno` < {D '"+fecha_mas_un_dia_string+"'} AND `Turno` = 'N'";
			// System.out.println(query_hoy);
		
			rs_Contador_hoy = s.executeQuery (query_hoy);
			while (rs_Contador_hoy.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				Gas_AMBIETA23_hoy = rs_Contador_hoy.getDouble(3)+ rs_Contador_hoy.getDouble(4)+rs_Contador_hoy.getDouble(5)+rs_Contador_hoy.getDouble(6);	
				//System.out.println (Gas_AMBIETA23_hoy); 
				//long salida = rs_ContadorTG1.getLong(2);
				//System.out.println(salida  1);
			}
			query_hoy_menos_1 = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAE`,`CorrectorLineaBE`, `CorrectorLineaDE`,`CorrectorLineaEE` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ fecha_menos_un_dia_string +"' } AND `Fecha de Turno` < {D '"+fecha_string+"' } and `Turno` = 'N'";
			//		 System.out.println(query_hoy_menos_1);
			rs_Contador_hoy_menos_1 = s.executeQuery (query_hoy_menos_1);
			while (rs_Contador_hoy_menos_1.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				Gas_AMBIETA23_hoy_menos1 = rs_Contador_hoy_menos_1.getDouble(3)+ rs_Contador_hoy_menos_1.getDouble(4)+rs_Contador_hoy_menos_1.getDouble(5)+rs_Contador_hoy_menos_1.getDouble(6);	
				//System.out.println (Gas_AMBIETA23_hoy_menos1); 
				//long salida = rs_ContadorTG1.getLong(2);
				//System.out.println(salida  1);
			}
			DecimalFormat dec2 = new DecimalFormat("##0.00"); 
			double AMBIETA23_diario = Gas_AMBIETA23_hoy - Gas_AMBIETA23_hoy_menos1;
			AMBIETA23_diario_string = dec2.format(AMBIETA23_diario*860);
			System.out.println("AMBIETA23 = " +  AMBIETA23_diario_string);
			 
			////AMBIETA 7 Consumo de Gas Diario (Nm3)
			query_hoy = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAVn`,`CorrectorLineaBVn`, `CorrectorLineaDVn`,`CorrectorLineaEVn` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ fecha_string +"' } AND `Fecha de Turno` < {D '"+fecha_mas_un_dia_string+"' } AND `Turno` = 'N'";
			// System.out.println(query_hoy);
			rs_Contador_hoy = s.executeQuery (query_hoy);
			while (rs_Contador_hoy.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				Gas_AMBIETA7_hoy = rs_Contador_hoy.getDouble(3)+ rs_Contador_hoy.getDouble(4)+rs_Contador_hoy.getDouble(5)+rs_Contador_hoy.getDouble(6);	
				//System.out.println (Gas_AMBIETA7_hoy); 
			}
			query_hoy_menos_1 = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAVn`,`CorrectorLineaBVn`, `CorrectorLineaDVn`,`CorrectorLineaEVn` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ fecha_menos_un_dia_string +"' } AND `Fecha de Turno` < {D '"+fecha_string+"' } and `Turno` = 'N'";
			//		 System.out.println(query_hoy_menos_1);
			rs_Contador_hoy_menos_1 = s.executeQuery (query_hoy_menos_1);
			while (rs_Contador_hoy_menos_1.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				Gas_AMBIETA7_hoy_menos1 = rs_Contador_hoy_menos_1.getDouble(3)+ rs_Contador_hoy_menos_1.getDouble(4)+rs_Contador_hoy_menos_1.getDouble(5)+rs_Contador_hoy_menos_1.getDouble(6);	
			//System.out.println (Gas_AMBIETA7_hoy_menos1); 
			}
			DecimalFormat dec3 = new DecimalFormat("##0.00"); 
			double AMBIETA7_diario = Gas_AMBIETA7_hoy - Gas_AMBIETA7_hoy_menos1;
			AMBIETA7_diario_string = dec3.format(AMBIETA7_diario*100);
			System.out.println("AMBIETA7 = " +  AMBIETA7_diario_string);
			
		//// GENERACIÓN DE FICHERO DIARIO.
		
			Archivopropiedades = new propiedades();
			//ruta del fichero salida.
			String ruta_archivo_salida_temporal = Archivopropiedades.ruta_archivo_salida_temporal();		
			String  nombre_fichero_salida="INES_EMPD_1623_" + dia + mes + año + "." + version; 
					
			String contenido_fichero1 =  año + ";" + mes + ";" + dia + ";" + "2" + ";" + "AMBIETA" + ";" + AMBIETA_diario_string + ";";
			String contenido_fichero2 = año + ";" + mes + ";" + dia + ";" + "23" + ";" + "AMBIETA7" + ";" + AMBIETA7_diario_string + ";";
			String contenido_fichero3 = año + ";" + mes + ";" + dia + ";" + "23" + ";" + "AMBIETA23" + ";" + AMBIETA23_diario_string + ";";
			
			contenido = contenido_fichero1 + "\r\n" + contenido_fichero2 +"\r\n" + contenido_fichero3 + "\r\n*";
			
			contenido = contenido.replace(",", ".");	
			///ahora generamos el fichero.
		
			Writer output = null;
			File file = new File(ruta_archivo_salida_temporal + nombre_fichero_salida);
			output = new BufferedWriter(new FileWriter(file));
			output.write(contenido);
			output.close();
			//Envío de fichero ftp. Clase java
			Integer servidor_ftp_puerto = Archivopropiedades.servidor_ftp_puerto();
			String servidor_ftp = Archivopropiedades.servidor_ftp();	
			//Aquí sobra algoo.......
			String ruta_completa_servidor_ftp;
			ruta_completa_servidor_ftp = servidor_ftp ;
			String ruta_fichero_en_servidor = Archivopropiedades.ruta_fichero_en_servidor();		
			String servidor_ftp_usuario = Archivopropiedades.servidor_ftp_usuario();
			String servidor_ftp_password= Archivopropiedades.servidor_ftp_password();
		
			FileUpload_2 NewFileUpload = new FileUpload_2();
			String fichero_subido =NewFileUpload.upload(ruta_completa_servidor_ftp,servidor_ftp_usuario,servidor_ftp_password,ruta_fichero_en_servidor,file,servidor_ftp_puerto);
			//Valores a devolver. 
			datos_fichero_diario[0] = nombre_fichero_salida;
			datos_fichero_diario[1] = AMBIETA_diario_string;
			datos_fichero_diario[2] = AMBIETA7_diario_string;
			datos_fichero_diario[3] = AMBIETA23_diario_string;
			datos_fichero_diario[4] = 	fichero_subido;
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return datos_fichero_diario;		
	
	}
	
	public static String[] generar_ficheros_mensuales(Date Fecha,Integer version){
		try{
		//Conexión con la base de datos
			
			propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			Statement s = conexion.createStatement(); 
			//Generación de fecha inicio y fin			
	
			
			GregorianCalendar cal_fecha = new GregorianCalendar ();
			GregorianCalendar cal_fecha2 = new GregorianCalendar ();
			GregorianCalendar cal_fecha3 = new GregorianCalendar ();
			GregorianCalendar cal_fecha4 = new GregorianCalendar ();
			cal_fecha.setTime(Fecha);
			cal_fecha2.setTime(Fecha);
			cal_fecha3.setTime(Fecha);
			cal_fecha4.setTime(Fecha);
			
			cal_fecha2.add(Calendar.DATE, -1);
			cal_fecha3.add(Calendar.MONTH,-1);
			cal_fecha4.add(Calendar.MONTH, -1);
			cal_fecha4.add(Calendar.DATE, -1);
			
			Date Fecha_menos_un_dia = cal_fecha2.getTime();
			Date Fecha_menos_un_mes = cal_fecha3.getTime();
			Date Fecha_menos_un_mes_y_un_dia = cal_fecha4.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_string = sdf.format(Fecha);
			String Fecha_menos_un_dia_string = sdf.format(Fecha_menos_un_dia);
			String Fecha_menos_un_mes_string = sdf.format(Fecha_menos_un_mes);
			String Fecha_menos_un_mes_y_un_dia_string = sdf.format(Fecha_menos_un_mes_y_un_dia);
			
			//Sacamos año, mes, dia con SimpleDateFormat
			//SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
			String mes = sdf2.format(Fecha_menos_un_mes);
			String año = sdf3.format(Fecha_menos_un_mes);
			
			//Variables para almacenar los datos (double)
			double AMBIETA_NETA_export_mes=0;
			double AMBIETA_NETA_import_mes=0;
			String AMBIETA_NETA_mes_string;
			//Energia Bruta MES (MWh)
			double Gas_AMBIETA_BRUTA_fin_de_mes=0;
			double Gas_AMBIETA_BRUTA_principio_de_mes_=0;
			String AMBIETA_BRUTA_mensual_string ;
			String AMBIETA23_mensual_string;
			String AMBIETA7_mensual_string;
			double Gas_AMBIETA23_fin_de_mes=0;
			double Gas_AMBIETA23_principio_de_mes=0;
			double Gas_AMBIETA7_fin_de_mes=0;
			double Gas_AMBIETA7_principio_de_mes=0;
			//PAra rellenar el archivo
			String contenido;
			
			
			///CALCULO DE AMBIETA NETA MENSUAL
			
			String export = "SELECT Sum(export)  FROM Potencia_neta having (Fecha > {D '"+Fecha_menos_un_mes_y_un_dia_string+"' } and Fecha < {D '"+fecha_string+"' } )";
			
			ResultSet rs = s.executeQuery (export);
				 			
			while (rs.next()) 
			{ 
				AMBIETA_NETA_export_mes = rs.getDouble(1);
				//System.out.println (AMBIETA_NETA_export_mes); 
			
			}
			
			String importada = "SELECT Sum(import)  FROM Potencia_neta having (Fecha > {D '"+Fecha_menos_un_mes_y_un_dia_string+"' } and Fecha < {D '"+fecha_string+"' } )";
			
			ResultSet rs_importada = s.executeQuery (importada);
				 			
			while (rs_importada.next()) 
			{ 
				AMBIETA_NETA_import_mes = rs_importada.getDouble(1);
				//System.out.println (AMBIETA_NETA_import_mes); 
			
			}
			
			DecimalFormat dec = new DecimalFormat("##0.00"); 
			double AMBIETA_NETA_MENSUAL = AMBIETA_NETA_export_mes - AMBIETA_NETA_import_mes;
			AMBIETA_NETA_mes_string = dec.format(AMBIETA_NETA_MENSUAL);
			System.out.println("AMBIETA_NETA_MENSUAL = " +AMBIETA_NETA_mes_string);
				
			
			
			
			
			///CALCULO DE AMBIETA BRUTAL MENSUAL
			String query_fin_de_mes = "SELECT `Fecha de Turno`, `ContadorTG1`,`ContadorTG2`, `ContadorST` FROM `RegistrosContadoresTurbinas` AS `turb` WHERE `Fecha de Turno` > {D '"+ Fecha_menos_un_dia_string +"' } AND `Fecha de Turno` < {D '"+fecha_string+"' }";
			//System.out.println(query_fin_de_mes);
			ResultSet rs_fin_de_mes = s.executeQuery (query_fin_de_mes);
				 			
			while (rs_fin_de_mes.next()) 
			{ 
				Gas_AMBIETA_BRUTA_fin_de_mes = rs_fin_de_mes.getDouble(2)+ rs_fin_de_mes.getDouble(3)+rs_fin_de_mes.getDouble(4);	
				//System.out.println (Gas_AMBIETA_BRUTA_fin_de_mes); 
			
			}
			String query_principio_de_mes = "SELECT `Fecha de Turno`, `ContadorTG1`,`ContadorTG2`, `ContadorST` FROM `RegistrosContadoresTurbinas` AS `turb` WHERE `Fecha de Turno` > {D '"+ Fecha_menos_un_mes_y_un_dia_string +"' } AND `Fecha de Turno` < {D '"+Fecha_menos_un_mes_string+"' }";
			//	System.out.println(query_principio_de_mes);
			ResultSet rs_principio_de_mes = s.executeQuery (query_principio_de_mes);
		 	while (rs_principio_de_mes.next()) 
		 	{ 
		 		Gas_AMBIETA_BRUTA_principio_de_mes_ = rs_principio_de_mes.getDouble(2)+ rs_principio_de_mes.getDouble(3)+rs_principio_de_mes.getDouble(4);	
		 		//System.out.println (Gas_AMBIETA_BRUTA_principio_de_mes_); 
			}
			dec = new DecimalFormat("##0.00"); 
			double AMBIETA_BRUTA_MENSUAL = Gas_AMBIETA_BRUTA_fin_de_mes - Gas_AMBIETA_BRUTA_principio_de_mes_;
			AMBIETA_BRUTA_mensual_string = dec.format(AMBIETA_BRUTA_MENSUAL/1000);
			System.out.println("AMBIETA_BRUTA_MENSUAL = " +AMBIETA_BRUTA_mensual_string);
			
			
			// HASTA AQUI AMBIETA bruta_mensual.
			//AHORA AMBIETA 23 MENSUAL Consumo de Gas MES (Termias)

			
			query_fin_de_mes = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAE`,`CorrectorLineaBE`, `CorrectorLineaDE`,`CorrectorLineaEE` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ Fecha_menos_un_dia_string +"' } AND `Fecha de Turno` < {D '"+fecha_string+"' } AND `Turno` = 'N'";
			// System.out.println(query_fin_de_mes);
			rs_fin_de_mes = s.executeQuery (query_fin_de_mes);
			while (rs_fin_de_mes.next()) 
			{ 
				Gas_AMBIETA23_fin_de_mes = rs_fin_de_mes.getDouble(3)+ rs_fin_de_mes.getDouble(4)+rs_fin_de_mes.getDouble(5)+rs_fin_de_mes.getDouble(6);	
			//System.out.println (Gas_AMBIETA23_fin_de_mes); 
			}
			query_principio_de_mes = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAE`,`CorrectorLineaBE`, `CorrectorLineaDE`,`CorrectorLineaEE` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ Fecha_menos_un_mes_y_un_dia_string +"' } AND `Fecha de Turno` < {D '"+Fecha_menos_un_mes_string+"' } and `Turno` = 'N'";
			// System.out.println(query_principio_de_mes);
			rs_principio_de_mes = s.executeQuery (query_principio_de_mes);
		 	 while (rs_principio_de_mes.next()) 
			{ 
				Gas_AMBIETA23_principio_de_mes = rs_principio_de_mes.getDouble(3)+ rs_principio_de_mes.getDouble(4)+rs_principio_de_mes.getDouble(5)+rs_principio_de_mes.getDouble(6);	
				//System.out.println (Gas_AMBIETA23_principio_de_mes); 
			
			}
		 	DecimalFormat dec2 = new DecimalFormat("##0.00"); 
			double AMBIETA23_mensual = Gas_AMBIETA23_fin_de_mes - Gas_AMBIETA23_principio_de_mes;
			AMBIETA23_mensual_string = dec2.format(AMBIETA23_mensual*860);
			System.out.println("AMBIETA23 MENSUAL = " +  AMBIETA23_mensual_string);
				 
			 ////AMBIETA 7 Consumo de Gas mensual (Nm3)
			query_fin_de_mes = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAVn`,`CorrectorLineaBVn`, `CorrectorLineaDVn`,`CorrectorLineaEVn` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ Fecha_menos_un_dia_string +"' } AND `Fecha de Turno` < {D '"+fecha_string+"' } AND `Turno` = 'N'";
			// System.out.println(query_fin_de_mes);
			rs_fin_de_mes = s.executeQuery (query_fin_de_mes);
			while (rs_fin_de_mes.next()) 
			{ 
				Gas_AMBIETA7_fin_de_mes = rs_fin_de_mes.getDouble(3)+ rs_fin_de_mes.getDouble(4)+rs_fin_de_mes.getDouble(5)+rs_fin_de_mes.getDouble(6);	
				//System.out.println (Gas_AMBIETA7_fin_de_mes); 
			}
			query_principio_de_mes = "SELECT `Fecha de Turno`,`Turno`, `CorrectorLineaAVn`,`CorrectorLineaBVn`, `CorrectorLineaDVn`,`CorrectorLineaEVn` FROM `RegistrosContadoresGas` AS `gas` WHERE `Fecha de Turno` > {D '"+ Fecha_menos_un_mes_y_un_dia_string +"' } AND `Fecha de Turno` < {D '"+Fecha_menos_un_mes_string+"' } and `Turno` = 'N'";
			//	 System.out.println(query_principio_de_mes);
			rs_principio_de_mes = s.executeQuery (query_principio_de_mes);
			while (rs_principio_de_mes.next()) 
			{ 
				Gas_AMBIETA7_principio_de_mes = rs_principio_de_mes.getDouble(3)+ rs_principio_de_mes.getDouble(4)+rs_principio_de_mes.getDouble(5)+rs_principio_de_mes.getDouble(6);	
				//System.out.println (Gas_AMBIETA7_principio_de_mes); 
			}
			DecimalFormat dec3 = new DecimalFormat("##0.00"); 
			double AMBIETA7_mensual = Gas_AMBIETA7_fin_de_mes - Gas_AMBIETA7_principio_de_mes;
			AMBIETA7_mensual_string = dec3.format(AMBIETA7_mensual*100);
			System.out.println("AMBIETA7 MENSUAL = " +  AMBIETA7_mensual_string);
			
			//// GENERACIÃ“N DE FICHERO mensual.
			
			//ruta del fichero salida.
			String ruta_archivo_salida_temporal = Archivopropiedades.ruta_archivo_salida_temporal();		
			String nombre_fichero_salida="INES_EMPM_1623_" + mes + año + "." + version ; 
					
			String contenido_fichero1 =  año + ";" + mes + ";" + "2" + ";" + "AMBIETA" + ";" + AMBIETA_BRUTA_mensual_string + ";";
			String contenido_fichero2 =  año + ";" + mes + ";" + "22" + ";" + "AMBIETA" + ";" +  AMBIETA_NETA_mes_string + ";";
			String contenido_fichero3 = año + ";" + mes +  ";" + "23" + ";" + "AMBIETA7" + ";" + AMBIETA7_mensual_string + ";";
			String contenido_fichero4 = año + ";" + mes +  ";" + "23" + ";" + "AMBIETA23" + ";" + AMBIETA23_mensual_string + ";";
			
			contenido = contenido_fichero1 + "\r\n" + contenido_fichero2 +"\r\n" + contenido_fichero3 +"\r\n" + contenido_fichero4 + "\r\n*";
			
				
				contenido = contenido.replace(",", ".");	
			///ahora generamos el fichero.
		
			
			
			 
			Writer output = null;
			File file = new File(ruta_archivo_salida_temporal + nombre_fichero_salida);
			output = new BufferedWriter(new FileWriter(file));
			output.write(contenido);
			output.close();
			
			//Envío FTP
			Integer servidor_ftp_puerto = Archivopropiedades.servidor_ftp_puerto();
			String servidor_ftp = Archivopropiedades.servidor_ftp();	
			
			String ruta_completa_servidor_ftp = servidor_ftp ;
			String ruta_fichero_en_servidor = Archivopropiedades.ruta_fichero_en_servidor();		
			String servidor_ftp_usuario = Archivopropiedades.servidor_ftp_usuario();
			String servidor_ftp_password= Archivopropiedades.servidor_ftp_password();
		
			FileUpload_2 NewFileUpload = new FileUpload_2();
			String fichero_subido = NewFileUpload.upload(ruta_completa_servidor_ftp,servidor_ftp_usuario,servidor_ftp_password,ruta_fichero_en_servidor,file,servidor_ftp_puerto);
			//Se rellenan los datos para devolverlos	
			datos_fichero_mes[0] = nombre_fichero_salida;
			datos_fichero_mes[1] = AMBIETA_NETA_mes_string;
			datos_fichero_mes[2] = AMBIETA_BRUTA_mensual_string;
			datos_fichero_mes[3] = AMBIETA7_mensual_string;
			datos_fichero_mes[4] = AMBIETA23_mensual_string;
			datos_fichero_mes[5] = fichero_subido;
			
				
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Array a devolver
		return datos_fichero_mes;
	
	
	}
	
	
	
	
	
}
