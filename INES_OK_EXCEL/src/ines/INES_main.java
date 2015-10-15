package ines;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class INES_main {

	
	public static Integer version ;

	//Energia Bruta Diaria (MWh)
	public static String diario_AMBIETA;
	//Consumo de Gas Diario (Termias)
	public static String diario_AMBIETA23;
	//Consumo de Gas Diario (Nm3)
	public static String diario_AMBIETA7;
	//	Energia NETA MES (MWh)
	public static String mes_AMBIETA_neta;
	//Energia Bruta MES (MWh)
	public static String mes_AMBIETA_bruta;
	//Consumo de Gas MES (Termias)
	public static String mes_AMBIETA23;
	//Consumo de Gas MES (Nm3)
	public static String mes_AMBIETA7;

	public static Integer generar_ahora_mensual;
	
	public static Integer generar_ahora_diario;
	public static Integer valor_progressbar;
	public static String hora_actual;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Ventana inicio
		INES_ventana_inicio frame = new INES_ventana_inicio();
		//Hacerla visible
		frame.setVisible(true);
		propiedades Archivopropiedades = new propiedades();
		//hora de envío generación de archivos diarios.
		String Hora_generacion = Archivopropiedades.hora_generacion();
		//Día de envóo generación de archivos mensuales 
		String Dia_generacion = Archivopropiedades.dia_generacion();
		//año_actual  mes y día actual para no volver a generar hasta el día siguiente
		String año_actual;
		String mes_actual;
		String dia_actual;
		//Retardo en días para dar tiempo a que los datos esten disponibles en la BD
		Integer	retardo_dias_generacion_diario = Archivopropiedades.retardo_dias_generacion_diario();
		//Booleano para habilitar o deshabilitar la generacion de ficheros 
		Boolean disable_monthly = Archivopropiedades.disable_monthly();		
				
				
		//ULTIMO FICHERO GENERADO
		//Se inicializa la fecha del ultimo fichero generado
		Integer Ultimo_fichero_diario_generado= 20010101;
		Integer Ultimo_fichero_mensual_generado= 200101;
		String nuevo_fichero_diario_a_generar;
		String nuevo_fichero_mensual_a_generar;
		//Integer para que no se salga del bucle. 
		Integer bucle = 1;
		//Inicializamos el campo de texto con un mensaje
		frame.textArea.append("Esperando nueva fecha y hora de generación..\n");
		//Bucle propiamente dicho
		while (bucle == 1){	
		
			try {
				
				do {
				//Espera un tiempo 
				Thread.sleep(900000);
				//Thread.sleep(10000);
				frame.textArea.append(".");
				//Cual es la fecha de hoy?
				Date now = new Date();
				//Sacamos la hora, dia mes y año
				Format formatter = new SimpleDateFormat("HH");
				hora_actual= formatter.format(now);
				formatter = new SimpleDateFormat("dd");
				 dia_actual= formatter.format(now);
				formatter = new SimpleDateFormat("yyyy");
				año_actual= formatter.format(now);
				formatter = new SimpleDateFormat("MM");
				mes_actual= formatter.format(now);
				//Cual va a ser el la fecha del nuevo fichero a generar
				nuevo_fichero_diario_a_generar = año_actual + mes_actual+ dia_actual;
				nuevo_fichero_mensual_a_generar = año_actual + mes_actual ;
				//Variables para generar la barra de progreso.
				Integer hasta_24= 24 - Integer.parseInt(Hora_generacion); 
				Integer faltan = 24-hasta_24-Integer.parseInt(hora_actual);
					if (faltan>=0){
						valor_progressbar = 24-faltan;
					}else{
						valor_progressbar = (faltan*(-1)); 	
					}
				frame.progressBar.setValue(valor_progressbar);
				//Campo para indicar las horas en numerico
				DecimalFormat valor_progressbar_decimal = new DecimalFormat("###"); 
				String valor_progressbar_string = valor_progressbar_decimal.format(24-valor_progressbar);
				frame.textField_horas_faltan.setText(valor_progressbar_string);
				//Bucle if para comprobar si hay que generar el fichero mensual o si ya se ha generado 
				if ( (Integer.parseInt(hora_actual)-Integer.parseInt(Hora_generacion)) == 0 && ((Integer.parseInt(dia_actual)-Integer.parseInt(Dia_generacion)==0)) && Ultimo_fichero_mensual_generado != Integer.parseInt(nuevo_fichero_mensual_a_generar)){
		 	    	 generar_ahora_mensual= 1;
		 	    }else{ 
		    		generar_ahora_mensual = 0;
		    	}
				//Bucle if para comprobar si hay que generar el fichero diario o si ya se ha generado 
	
				if ( (Integer.parseInt(hora_actual)-Integer.parseInt(Hora_generacion)) == 0  && Ultimo_fichero_diario_generado != Integer.parseInt(nuevo_fichero_diario_a_generar)){
			 	    	 generar_ahora_diario= 1;
			 	    	//frame.progressBar.setValue(100);
			    }else{ 
			    			generar_ahora_diario = 0;
			    }
					
				//Si no se cumplen las condiciones se sigue dentro del bucle DO, si no se cumple se sale.
				}while (generar_ahora_mensual != 1 && generar_ahora_diario != 1);
				//frame.textArea.append("]\n");

				//Si se cumple generar ahora diario.....
				if (generar_ahora_diario == 1){
					//formato fecha a enviar a la clase de generacion
					String DATE_FORMAT = "yyyy-MM-dd";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String Fecha_string = año_actual + "-" + mes_actual + "-" + dia_actual;
					//Versión por defecto....1
					Integer version = 1;
					Date Fecha;
					Fecha = sdf.parse(Fecha_string);	
					GregorianCalendar cal_fecha = new GregorianCalendar ();
					cal_fecha.setTime(Fecha);
					//Se importa el parametro de retardo de dias de generación
					retardo_dias_generacion_diario = Archivopropiedades.retardo_dias_generacion_diario();		
					//SE quitan los días indicados en la variable.
					cal_fecha.add(Calendar.DATE, retardo_dias_generacion_diario);
					//Se define la fecha del fichero a generar
					Date Fecha_del_fichero_a_generar = cal_fecha.getTime();
					//Clase java para generar el fichero
					//generacion_ficheros Nuevo_generar_ficheros = new generacion_ficheros();
					//Para generar los diarios, se devuelve el array datos_fichero_diario
					String[] datos_fichero_diario =generacion_ficheros.generar_ficheros_diarios(Fecha_del_fichero_a_generar, version);
					//SE extrae cada dato devuelto
					String Nombre_fichero_generado = datos_fichero_diario[0];
					Ultimo_fichero_diario_generado = Integer.parseInt(año_actual + mes_actual+ dia_actual);
					frame.textField_ULTIMO_FICHERO.setText(Nombre_fichero_generado);
					frame.textField_AMBIETA.setText(datos_fichero_diario[1]);
					frame.textField_AMBIETA7.setText(datos_fichero_diario[2]);
					frame.textField_AMBIETA23.setText(datos_fichero_diario[3]);
					frame.textArea.append("\nSe ha subido con exito " + datos_fichero_diario[4] + "\n");
				
				}
				//Si se cumple generar ahora y está habilitado

				if (generar_ahora_mensual == 1 && disable_monthly==false){
				try {
					
					//Datos para ejecutar el fichero Excel que importa datos de PI (osisoft)
					//Ruta de el ejecutable Excel.
					String ruta_excel= Archivopropiedades.ruta_excel();
					//Cual es el fichero excel?
					String fichero_PI_excel = Archivopropiedades.fichero_PI_excel();
					//Importación de datos de PI con una machro en Excell.
					String cmdLine = ruta_excel + " " + fichero_PI_excel ;
					@SuppressWarnings("unused")
					Process p = Runtime.getRuntime().exec(cmdLine);
					//Le damos tiempo a la macro a terminar la generación
					Thread.sleep(120000);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					//Formato de fecha para enviar a la clase java
					String DATE_FORMAT = "yyyy-MM-dd";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					//Fecha para enviar a la clase java, se quitará un mes.!!!!!!!!!
					String Fecha_string = año_actual + "-" + mes_actual + "-" + 01;
					//Versión por defecto 1
					Integer version = 1;
					//Definimos Fecha
					Date Fecha;
					//Se define fecha del string
					Fecha = sdf.parse(Fecha_string);	
					//Clase java para generación de ficheros
					//generacion_ficheros Nuevo_generar_ficheros = new generacion_ficheros();
					//Se pasan los datos a la clase java y se recoge la información en el Array 
					String[] datos_fichero_diario = generacion_ficheros.generar_ficheros_mensuales(Fecha, version);
					//Se recoge la información
					String Nombre_fichero_generado = datos_fichero_diario[0];
					Ultimo_fichero_mensual_generado = Integer.parseInt(año_actual + mes_actual);
					frame.textField_ULTIMO_FICHERO_MENSUAL.setText(Nombre_fichero_generado);	
					frame.textField_AMBIETA_MES.setText(datos_fichero_diario[1]);
					frame.textField_AMBIETA_BRUTA_MES.setText(datos_fichero_diario[2]);
					frame.textField_AMBIETA7_MES.setText(datos_fichero_diario[3]);
					frame.textField_AMBIETA23_MES.setText(datos_fichero_diario[4]);
					frame.textArea.append("\nSe ha subido con éxito " + datos_fichero_diario[5] +"\n");
				}
				//Se ha terminado de enviar los ficheros y se tiene que comprobar cuales no están noOK.
				//solamente del último año
				FileListFiltered ficheros_no_ok = new FileListFiltered();
				String servidor_ftp = Archivopropiedades.servidor_ftp();	
				Integer servidor_ftp_puerto = Archivopropiedades.servidor_ftp_puerto();
				String ruta_ficheros_ok = Archivopropiedades.ruta_ficheros_ok();		
				String servidor_ftp_usuario = Archivopropiedades.servidor_ftp_usuario();
				String servidor_ftp_password= Archivopropiedades.servidor_ftp_password();
				//Devuelve un listado
				String listado_ficheros_no_ok = ficheros_no_ok.list_ftp_files(servidor_ftp, servidor_ftp_usuario,
							servidor_ftp_password, ruta_ficheros_ok, año_actual , ".noOK",servidor_ftp_puerto );
				frame.textArea_listado_no_ok.setText(listado_ficheros_no_ok);
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		
		}//bucle
		
		
	}
	
	
}

