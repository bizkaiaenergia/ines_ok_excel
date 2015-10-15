package ines;
import java.io.File;
import java.io.IOException;

//import org.apache.commons.net.ftp.FTPFile;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;


/**
 **/
public class FileUpload_2
{
 
	public String fichero_subido;
	public String upload(String ftpServer, String user, String password,
			String fileName, File source, Integer port ) {
		try {
		 FTPClient client = new FTPClient();
		 client.setPassive(true);
		 //client.setMLSDPolicy(FTPClient.MLSD_ALWAYS);
		 client.setCharset("UTF8")  ;
		 client.setType(FTPClient.TYPE_BINARY);
		 if (port == 21){
			 client.connect(ftpServer);
		 }else{
			 client.connect(ftpServer, port);
		 }
		 client.login(user, password);
		 client.changeDirectory(fileName);
		 client.upload(source);
		// fichero_subido = source.getName();
		
		 
		 FTPFile[] files;
		 
		 files = client.list();
			  
			   for (int i=0;i<(files.length);i++) {  	
				    fileName = files[i].getName();
				   
				  
				  if (fileName.equals(source.getName())){
					  //System.out.println(fileName);
					  fichero_subido = fileName ;
				  }
				}
			
		 
		 
		 
	   
	   
	   
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FTPIllegalReplyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FTPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FTPDataTransferException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FTPAbortedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FTPListParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   return fichero_subido;
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
}