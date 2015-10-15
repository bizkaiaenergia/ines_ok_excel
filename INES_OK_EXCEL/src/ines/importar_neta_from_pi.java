package ines;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class importar_neta_from_pi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	public  static void importar_ficheros_pi_potencia_neta (String año,String mes){
		propiedades Archivopropiedades = new propiedades();
		String ruta_fichero_txt_potencia_neta_from_pi = Archivopropiedades.ruta_fichero_txt_potencia_neta();
		String filename = ruta_fichero_txt_potencia_neta_from_pi + "AmorebietaPotNetaPlantData_" + año + mes + "_INES.dat";
		
		try {
			//propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			Statement s = conexion.createStatement(); 
			String borrar_mes = "Delete * FROM `Potencia_neta` WHERE `fecha` > {D '"+ año + "-" + mes + "-01" +"'} ";
			 int delete = s.executeUpdate(borrar_mes);
			  if(delete != 0){
			  System.out.println("Se han borrado " + delete + "filas");
			  }
			  else{
			  System.out.println("No hay filas a borrar.");
			  }
			
			
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			
		    String line;
		    String tag;
		    String value="";
		    String value2="";
		    String fecha="";
		    
		    while ((line = br.readLine()) != null) {
		    	if (!line.startsWith("*") & line.startsWith("10CJA00DE100.XQ20,")) {
		    	StringTokenizer st = new StringTokenizer(line, ",");
		    	 tag = st.nextToken( );
		    	 value = st.nextToken( );
		    	// fecha = st.nextToken();
		    	tag = "export";
		    	
		    	}
		    	if (!line.startsWith("*") & line.startsWith("10CJA00DE100.XQ20I,")) {
			    	StringTokenizer st = new StringTokenizer(line, ",");
			    	 tag = st.nextToken( );
			    	 value2 = st.nextToken( );
			    	 fecha = st.nextToken();
			    	tag = "import";
			    	String valuefinal= value + ";" + value2 + ";" + fecha; 
			    	System.out.println(valuefinal);
		    	}
		    	
		   
		    }
			
		    
		    
		    br.close();
		    
		    
		    
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
