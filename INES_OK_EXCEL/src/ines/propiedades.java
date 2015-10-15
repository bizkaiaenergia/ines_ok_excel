package ines;


	import java.io.*;
import java.util.Properties;
public class propiedades {
	
	
	String archivo_properties="INES.properties";
	
	
	String ruta_archivo_salida_temporal;
	String servidor_ftp;
	String servidor_ftp_usuario;
	String servidor_ftp_password;
	String dia_generacion;
	String hora_generacion;
	String ruta_fichero_en_servidor;
	Integer retardo_dias_generacion_diario;
	Integer servidor_ftp_puerto;
	Boolean disable_monthly;
	String ruta_ficheros_ok;
	String ruta_servidor_odbc;
	String nombre_servidor_odbc;
	String ruta_excel;
	String fichero_PI_excel;
	String ruta_instalacion;
	String ruta_fichero_txt_potencia_neta;
	
	Properties prop  = new Properties();
	
	String ruta_archivo_salida_temporal(){
			try{
				prop.load(new FileInputStream(archivo_properties));
				ruta_archivo_salida_temporal = prop.getProperty("ruta_archivo_salida_temporal");
			}catch(IOException e){
			}
			return ruta_archivo_salida_temporal;
			}
	String servidor_ftp(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			servidor_ftp = prop.getProperty("servidor_ftp");
		}catch(IOException e){
		}
		return servidor_ftp;
		}
	
	String dia_generacion(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			dia_generacion = prop.getProperty("dia_generacion");
		}catch(IOException e){
		}
		return dia_generacion;
		}
	String hora_generacion(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			hora_generacion = prop.getProperty("hora_generacion");
		}catch(IOException e){
		}
		return hora_generacion;
		}
	
	String servidor_ftp_usuario(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			servidor_ftp_usuario = prop.getProperty("servidor_ftp_usuario");
		}catch(IOException e){
		}
		return servidor_ftp_usuario;
		}
	
	String servidor_ftp_password(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			servidor_ftp_password = prop.getProperty("servidor_ftp_password");
		}catch(IOException e){
		}
		return servidor_ftp_password;
		}
	
	
	String ruta_fichero_en_servidor(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_fichero_en_servidor = prop.getProperty("ruta_fichero_en_servidor");
		}catch(IOException e){
		}
		return ruta_fichero_en_servidor;
		}
	
	String ruta_ficheros_ok(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_ficheros_ok = prop.getProperty("ruta_ficheros_ok");
		}catch(IOException e){
		}
		return ruta_ficheros_ok;
		}
	
	
	String nombre_servidor_odbc(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			nombre_servidor_odbc = prop.getProperty("nombre_servidor_odbc");
		}catch(IOException e){
		}
		return nombre_servidor_odbc;
		}
	
	String ruta_servidor_odbc(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_servidor_odbc = prop.getProperty("ruta_servidor_odbc");
		}catch(IOException e){
		}
		return ruta_servidor_odbc;
		}
	
	String ruta_instalacion(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_instalacion = prop.getProperty("ruta_instalacion");
		}catch(IOException e){
		}
		return ruta_instalacion;
		}
	
	
	
	
	
	
	
	String fichero_PI_excel(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			fichero_PI_excel = prop.getProperty("fichero_PI_excel");
		}catch(IOException e){
		}
		return fichero_PI_excel;
		}
	
	String ruta_excel(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_excel = prop.getProperty("ruta_excel");
		}catch(IOException e){
		}
		return ruta_excel;
		}
	
	
	
	
	
		
	Integer retardo_dias_generacion_diario(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			retardo_dias_generacion_diario = new Integer(prop.getProperty("retardo_dias_generacion_diario"));
		}catch(IOException e){
		}
		return retardo_dias_generacion_diario;
		}
	Boolean disable_monthly(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			disable_monthly = new Boolean(prop.getProperty("disable_monthly"));
		}catch(IOException e){
		}
		return disable_monthly;
		}
	
	Integer servidor_ftp_puerto(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			servidor_ftp_puerto = new Integer(prop.getProperty("servidor_ftp_puerto"));
		}catch(IOException e){
		}
		return servidor_ftp_puerto;
		}
	
	String ruta_fichero_txt_potencia_neta(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_fichero_txt_potencia_neta = prop.getProperty("ruta_fichero_txt_potencia_neta");
		}catch(IOException e){
		}
		return ruta_fichero_txt_potencia_neta;
		}
	
	
	
	
	
	
}