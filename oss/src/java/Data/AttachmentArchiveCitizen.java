/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class AttachmentArchiveCitizen implements Serializable {

    int Atta_ArchiveC_ID;
    int Cit_ID;
    int ServiceAttachmentName_ID;
    double si;
    UploadedFile file;
    StreamedContent fileDownload;
    String nameFile;
    String nameAtt;
    String form;
    byte[] outputfinal;
    public AttachmentArchiveCitizen(int Atta_ArchiveC_ID, int Cit_ID, int ServiceAttachmentName_ID, InputStream inputStream, String nameFile, String nameAtt) {
        this.Atta_ArchiveC_ID = Atta_ArchiveC_ID;
        this.Cit_ID = Cit_ID;
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
        this.nameAtt = nameAtt;
        this.nameFile = nameFile;
        if (inputStream != null) {
            try {
                si = inputStream.available();
                byte[] inputByte = new byte[inputStream.available()];
                inputStream.read(inputByte);
                outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop", inputByte);
                InputStream inputForData = new ByteArrayInputStream(outputfinal);

                fileDownload = new DefaultStreamedContent(inputForData, "file", nameFile);
            } catch (IOException ex) {
                Logger.getLogger(AttachmentArchiveCitizen.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            System.out.println("no file no file no file no file ");
        }
    }

    public AttachmentArchiveCitizen(int Atta_ArchiveC_ID, int Cit_ID, int ServiceAttachmentName_ID, UploadedFile file, String nameFile, String nameAtt,String form) {
        this.Atta_ArchiveC_ID = Atta_ArchiveC_ID;
        this.Cit_ID = Cit_ID;
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
        this.file = file;
        this.nameFile = nameFile;
        this.nameAtt = nameAtt;
        this.form = form;
    }

    public void addToDataBase() throws SQLException, ClassNotFoundException {

        String q = "INSERT INTO attachment_archive_citizen (`Atta_ArchiveC_ID`, `Cit_ID`,`ServiceAttachmentName_ID`,`file`,`filename`,`form`) VALUES (?,?,?,?,?,?);";

        System.out.println("data is   > < " + Atta_ArchiveC_ID + "  " + Cit_ID + "  " + ServiceAttachmentName_ID);

            DB data = new DB();
            PreparedStatement s = data.prepareStatement(q);

            s.setInt(1, Atta_ArchiveC_ID);
            s.setInt(2, Cit_ID);
            s.setInt(3, ServiceAttachmentName_ID);

            if (file.getSize() > 0) {
                s.setBinaryStream(4, saveFileInDisk());
            } else {
                s.setBinaryStream(4, null);
            }
            
            s.setString(5,file.getFileName());
            s.setString(6,form);
            System.out.println(q);
            s.executeUpdate();

       

    }

    private InputStream saveFileInDisk() {
        try {
            InputStream inp = file.getInputstream();

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

    public int getAtta_ArchiveC_ID() {
        return Atta_ArchiveC_ID;
    }

    public byte[] getOutputfinal() {
        return outputfinal;
    }

    public void setOutputfinal(byte[] outputfinal) {
        this.outputfinal = outputfinal;
    }

   

    public void setAtta_ArchiveC_ID(int Atta_ArchiveC_ID) {
        this.Atta_ArchiveC_ID = Atta_ArchiveC_ID;
    }

    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public int getServiceAttachmentName_ID() {
        return ServiceAttachmentName_ID;
    }

    public void setServiceAttachmentName_ID(int ServiceAttachmentName_ID) {
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getNameAtt() {
        return nameAtt;
    }

    public void setNameAtt(String nameAtt) {
        this.nameAtt = nameAtt;
    }

    @Override
    public String toString() {
        return "AttachmentArchiveCitizen{" + "Atta_ArchiveC_ID=" + Atta_ArchiveC_ID + ", Cit_ID=" + Cit_ID + ", ServiceAttachmentName_ID=" + ServiceAttachmentName_ID + ", file=" + file + ", fileDownload=" + fileDownload + ", nameFile=" + nameFile + ", nameAtt=" + nameAtt + '}';
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public boolean isImage() {
        if (nameFile != null) {
            String s = nameFile.substring(nameFile.length() - 3, nameFile.length());
            System.out.println("s = " + s + "  " + s.compareTo("jpg"));
            if (s.compareTo("jpg") == 0 || s.compareTo("png") == 0) {
                return true;
            }
        }
        return false;
    }
 
    
    public boolean isPDF() {
        if (nameFile != null) {
            String s = nameFile.substring(nameFile.length() - 3, nameFile.length());
            System.out.println("s = " + s + "  " + s.compareTo("pdf"));
            if (s.compareTo("pdf") == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean canntOpenFile() {
        return !isImage() && !isPDF();
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
}
