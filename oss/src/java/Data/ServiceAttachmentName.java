/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Amer$_$
 */
public class ServiceAttachmentName implements Serializable {

    int id;
    String name;
    String notes;
    UploadedFile file;

    String nameFile;
    StreamedContent fileDownload;
    String haveFile;

    public ServiceAttachmentName() {
    }

    public ServiceAttachmentName(int id, String name, String notes, InputStream inputStream, String nameFile, String haveFile) {
        try {
            this.id = id;
            this.name = name;
            this.notes = notes;
            this.haveFile = haveFile;
            this.nameFile = nameFile;
            if (inputStream != null) {
                System.out.println(id);
                byte[] inputByte = new byte[inputStream.available()];
                inputStream.read(inputByte);
                byte[] outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop", inputByte);
                InputStream inputForData = new ByteArrayInputStream(outputfinal);
                System.out.println(nameFile);
                fileDownload = new DefaultStreamedContent(inputForData, "file", nameFile);

            } else {
                System.out.println(name);

                System.out.println("no file no file no file no file ");
            }
        } catch (IOException ex) {
            System.out.println("null null ServiceAttachmentName ");
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServiceAttachmentName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public void update() {
        String q = "UPDATE serviceattachmentname SET ServA_Name = '" + name + "',notes = '" + notes + "',`haveFile` = '" + haveFile + "' WHERE (ServiceAttachmentName_ID = " + id + ");";
        System.out.println(q);
        try {
            DB data = new DB();
            data.write(q);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatewithFile() {

        if (file.getSize() != 0) {
            String q = "UPDATE `oss`.`serviceattachmentname` SET `ServA_Name` = ?,`File` = ?,`notes` = ?,`typeFile` = ?,`haveFile` = ? WHERE `ServiceAttachmentName_ID` = " + id + " ;";

            System.out.println(q);
            try {
                DB data = new DB();
                PreparedStatement p = data.prepareStatement(q);
                p.setString(1, name);
                p.setBinaryStream(2, saveFileInDisk());
                p.setString(3, notes);
                p.setString(4, nameFile);
                p.setString(5, haveFile);
                p.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String q = "UPDATE `oss`.`serviceattachmentname` SET `ServA_Name` = ?,`notes` = ?,`typeFile` = ?,`haveFile` = ? WHERE `ServiceAttachmentName_ID` = " + id + " ;";

            System.out.println(q);
            try {
                DB data = new DB();
                PreparedStatement p = data.prepareStatement(q);
                p.setString(1, name);
                //p.setBinaryStream(1, saveFileInDisk());
                p.setString(2, notes);
                p.setString(3, nameFile);
                p.setString(4, haveFile);
                p.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void deleteFromDB() {

        String q = "DELETE FROM serviceattachmentname WHERE (ServiceAttachmentName_ID =" + id + ");";
        System.out.println(q);
        try {
            DB data = new DB();
            data.write(q);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addAttachToDBWitoutFile() {

        String q = "INSERT INTO oss.serviceattachmentname (`ServiceAttachmentName_ID`, `ServA_Name`, `notes`, `haveFile`)  VALUES (null,'"
                + name + "','" + notes + "','" + haveFile + "');";

        try {
            DB data = new DB();
            data.write(q);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addAttachToDBwithFile() {

        String q = "INSERT INTO serviceattachmentname VALUES(null,?,?,?,?,?);";
        System.out.println(q);

        nameFile = file.getFileName();
        try {
            DB data = new DB();
            PreparedStatement s = data.prepareStatement(q);
            s.setString(1, name);
            s.setBinaryStream(2, saveFileInDisk());
            s.setString(3, notes);

            s.setString(4, nameFile);

            s.setString(5, haveFile);
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addUploadFile(FileUploadEvent file) {
        this.file = file.getFile();
        if (this.file != null) {
            System.out.println(this.file.getFileName());

        }

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>in set>>>>>>>>>>>>>>>>>>>>>>" + file.getFileName());
        this.file = file;

    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getHaveFile() {
        return haveFile;
    }

    public void setHaveFile(String haveFile) {
        this.haveFile = haveFile;
    }

    private InputStream saveFileInDisk() {
        try {
            InputStream inp = file.getInputstream();

            byte[] inputByte = new byte[inp.available()];

            inp.read(inputByte);

            byte[] outputCipher = Crypto.dec(Cipher.ENCRYPT_MODE, "foreanderDowntop", inputByte);
//            byte[] outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop", outputCipher);
//            System.out.println("-------------------------------------");
//            for (int i = 0; i < outputfinal.length; i++) {
//                byte b = outputfinal[i];
//                System.out.print(b);
//            }
//            System.out.println("");
            InputStream inputForData = new ByteArrayInputStream(outputCipher);
//            String q = "UPDATE serviceattachmentname SET File = ? WHERE (ServiceAttachmentName_ID = 11);";
//            try {
//                DB d = new DB();
//                if(outputCipher!=null)
//                d.writeFile(q, inputForData);
//                else
//                    System.out.println("output is null");
//                
//                
//                
//                
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
//            }
            return inputForData;

        } catch (IOException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//           InputStream initialStream = file.getInputstream();
//            byte[] buffer = new byte[initialStream.available()];
//            initialStream.read(buffer);
//            File test = new File(System.getProperty("user.dir")+"/oss");
//            if(!test.isDirectory()){
//                test.mkdir();
//            }
//            File targetFile = new File(System.getProperty("user.dir")+"/oss/tmep.txt");
//            
//            OutputStream outStream = new FileOutputStream(targetFile);
//            outStream.write(buffer);
//            
//            File f = new File(System.getProperty("user.dir")+"/" + file.getFileName());
//            f.createNewFile();
//            Crypto.fileProcessor(Cipher.ENCRYPT_MODE, "foreanderDowntop", targetFile, f);
////             File test = new File("E:/oss/decription" + file.getFileName());
////            test.createNewFile();
////            Crypto.fileProcessor(Cipher.DECRYPT_MODE, "foreanderDowntop", f, test);
//
//           
//            outStream.close();
//            targetFile.delete();
//
//
//
//            System.out.println("uploaded pdf");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        return null;
    }

    @Override
    public String toString() {
        return "ServiceAttachmentName{" + "id=" + id + ", name=" + name + ", notes=" + notes + ", file=" + file + ", nameFile=" + nameFile + ", fileDownload=" + fileDownload + ", haveFile=" + haveFile + '}';
    }

    public boolean haveFileToupload() {
        if (haveFile != null) {
            return haveFile.compareTo("yes") == 0;
        }
        return false;
    }

    public String color() {
        if (haveFileToupload()) {
            return "danger";
        } else {
            return "primary";
        }
    }

    public String colorButton() {
        if (haveFileToupload()) {
            return "#e74a3b";
        } else {
            return "#4e73df";
        }
    }

    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Upload All the Files needed"));

      //  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please upload files");
      //  PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void updatewithoutFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
