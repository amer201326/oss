/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Data.AttachmentArchiveCitizen;
import Data.Crypto;
import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
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
    String Atta_Attachment_src;
    UploadedFile file;
    String filename;
    StreamedContent fileDownload;
    boolean haveFile;

    public AttachmentServiceEmployee(int Attachment_Service_Employee_ID, int Emp_ID, int Cit_ID, int Service_Citizen_ID, int Services_Provided_ID, String Atta_Attachment_src, InputStream inputStream, String filename) {
        this.Attachment_Service_Employee_ID = Attachment_Service_Employee_ID;
        this.Emp_ID = Emp_ID;
        this.Cit_ID = Cit_ID;
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Atta_Attachment_src = Atta_Attachment_src;
        this.filename = filename;
        if (inputStream != null) {
            try {
                byte[] inputByte = new byte[inputStream.available()];
                inputStream.read(inputByte);
                byte[] outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop", inputByte);
                InputStream inputForData = new ByteArrayInputStream(outputfinal);

                fileDownload = new DefaultStreamedContent(inputForData, "file", filename);
                haveFile = true;
            } catch (IOException ex) {
                Logger.getLogger(AttachmentArchiveCitizen.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            haveFile = false;
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

    public String getAtta_Attachment_src() {
        return Atta_Attachment_src;
    }

    public void setAtta_Attachment_src(String Atta_Attachment_src) {
        this.Atta_Attachment_src = Atta_Attachment_src;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public boolean isHaveFile() {
        return haveFile;
    }

    public void setHaveFile(boolean haveFile) {
        this.haveFile = haveFile;
    }

}
