/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import Data.AttachmentArchiveCitizen;
import Data.Crypto;
import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author baraakali
 */
public class AttachmentServiceEmployee implements Serializable {

    int Attachment_Service_Employee_ID;
    int Emp_ID, Cit_ID;
    int Service_Citizen_ID;
    int Services_Provided_ID;

    InputStream inputStreamFile;
    String filename;
    StreamedContent fileDownload;
    long sel;
    int si;
    public AttachmentServiceEmployee() {
    }

    public AttachmentServiceEmployee(int Emp_ID, int Cit_ID, int Service_Citizen_ID, int Services_Provided_ID, InputStream file, String filename) {
        try {
            this.Emp_ID = Emp_ID;
            this.Cit_ID = Cit_ID;
            this.Service_Citizen_ID = Service_Citizen_ID;
            this.Services_Provided_ID = Services_Provided_ID;
            this.inputStreamFile = file;
            this.filename = filename;
            si = file.available();
//           File a = File.createTempFile(filename.substring(0, filename.length()-3), filename.substring(filename.length()-3, filename.length())); 
//    		
//    		System.out.println("Temp file : " + a.getAbsolutePath());
//            //File a = new File( "/"+filename);

            String relativeWebPath = "/files/"+filename;
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            File a = new File(absoluteDiskPath);
            System.out.println(a.getAbsolutePath());
            byte[] buffer = new byte[file.available()];
            file.read(buffer);

            OutputStream outStream = new FileOutputStream(a);
            outStream.write(buffer);
            outStream.close();
        } catch (IOException ex) {
            System.out.println("error out put steem");
            Logger.getLogger(AttachmentServiceEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AttachmentServiceEmployee(int Attachment_Service_Employee_ID, int Emp_ID, int Cit_ID,
            int Service_Citizen_ID, int Services_Provided_ID, InputStream inputStream, String filename) {
        this.Attachment_Service_Employee_ID = Attachment_Service_Employee_ID;
        this.Emp_ID = Emp_ID;
        this.Cit_ID = Cit_ID;
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;

        this.filename = filename;
        if (inputStream != null) {
            try {
                byte[] inputByte = new byte[inputStream.available()];
                inputStream.read(inputByte);
                byte[] outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop", inputByte);
                InputStream inputForData = new ByteArrayInputStream(outputfinal);

                fileDownload = new DefaultStreamedContent(inputForData, "file", filename);

            } catch (IOException ex) {
                Logger.getLogger(AttachmentArchiveCitizen.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            System.out.println("no file no file no file no file ");
        }

    }

    public int getAttachment_Service_Employee_ID() {
        return Attachment_Service_Employee_ID;
    }

    public void setAttachment_Service_Employee_ID(int Attachment_Service_Employee_ID) {
        this.Attachment_Service_Employee_ID = Attachment_Service_Employee_ID;
    }

    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public int getService_Citizen_ID() {
        return Service_Citizen_ID;
    }

    public void setService_Citizen_ID(int Service_Citizen_ID) {
        this.Service_Citizen_ID = Service_Citizen_ID;
    }

    public int getServices_Provided_ID() {
        return Services_Provided_ID;
    }

    public void setServices_Provided_ID(int Services_Provided_ID) {
        this.Services_Provided_ID = Services_Provided_ID;
    }

    public InputStream getInputStreamFile() {
        return inputStreamFile;
    }

    public void setInputStreamFile(InputStream inputStreamFile) {
        this.inputStreamFile = inputStreamFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public StreamedContent getFileDownload() {
        System.out.println("get file downloafd");
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public void addToDataBase() throws SQLException, ClassNotFoundException {

        String q = "INSERT INTO attachment_service_employee (`Attachment_Service_Employee_ID`, `Emp_ID`,`Cit_ID`,`Service_Citizen_ID`,`Services_Provided_ID`,`file`, `filename`)"
                + "VALUES (null,?,?,?,?,?,?);";

        DB data = new DB();
        PreparedStatement s = data.prepareStatement(q);

        s.setInt(1, Emp_ID);
        s.setInt(2, Cit_ID);
        s.setInt(3, Service_Citizen_ID);
        s.setInt(4, Services_Provided_ID);

        try {

            if (inputStreamFile.available() > 0) {
                System.out.println("file size > 0");
                s.setBinaryStream(5, saveFileInDisk());

            } else {
                System.out.println("file size = 0 filenulll");
                s.setBinaryStream(5, null);

            }

        } catch (IOException ex) {
            s.setBinaryStream(5, null);
            Logger.getLogger(AttachmentServiceEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        s.setString(6, filename);

        System.out.println(q);
        s.executeUpdate();

    }

    private InputStream saveFileInDisk() {
        try {
            InputStream inp = inputStreamFile;

            byte[] inputByte = new byte[inp.available()];

            inp.read(inputByte);

            byte[] outputCipher = Crypto.dec(Cipher.ENCRYPT_MODE, "foreanderDowntop", inputByte);

            InputStream inputForData = new ByteArrayInputStream(outputCipher);

            return inputForData;

        } catch (IOException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String sizeFile() {
        
            double s = si;
            s /= 1000;
            if (s > 1000) {
                s /= 1000;
                return s + " MB";
            } else {
                return s + " KB";
            }
       
        
    }

    public long getSel() {
        return sel;
    }

    public void setSel(long sel) {
        this.sel = sel;
    }
    
    public String typ(){
        if(filename !=null)
            if(filename.length() >3)
        return filename.substring(filename.length()-3, filename.length());
        return "";
    }

}
