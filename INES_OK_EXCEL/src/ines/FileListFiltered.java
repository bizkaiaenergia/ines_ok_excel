package ines;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

import java.io.IOException;




//import org.apache.commons.io.filefilter.WildcardFileFilter;
//import org.apache.commons.net.ftp.*;


 
/**
 * This class is used to upload a file to a FTP server.
 * SACADO DE http://www.javabeat.net/2007/10/file-upload-and-download-using-java/
 * @author Muthu
 */
public class FileListFiltered
{
 
  
	public String filelist_filtered_string = "";
	public String fileName;
   
	public String  list_ftp_files(String ftpServer, String user, String password,
	         String filedir,String año_actual, String finalizacon,Integer puerto){   
	  
   try{
	   
	   
	   FTPClient client = new FTPClient();
	   client.setPassive(true);
	// client.setMLSDPolicy(FTPClient.MLSD_ALWAYS);
	 client.setCharset("UTF8")  ;
	 client.setType(FTPClient.TYPE_BINARY);
	 if (puerto == 21){
		 
			client.connect(ftpServer);
		
	 }else{
	 client.connect(ftpServer, puerto);
	 }
	  // client.connect("ftp.host.com", port);
	   client.login(user, password);
	   client.changeDirectory(filedir);
	   
	   FTPFile[] files;
	
	files = client.list();
	  
	   for (int i=0;i<(files.length);i++) {  	
		    fileName = files[i].getName();
		   
		  
		  if (fileName.startsWith("INES_EMP") && fileName.contains(año_actual) && fileName.endsWith(finalizacon)){
			  //System.out.println(fileName);
			  filelist_filtered_string = fileName + "\n" + filelist_filtered_string; 
		  }
		}
		 
	  
   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (FTPDataTransferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPAbortedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPListParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	
	  
   
	   
	   
	   return filelist_filtered_string;
	   
	  
		 }  
   
   
   
   
   
   {
	   
	   
	   
	   
	   
	   
	   
	   
}
}
