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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
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
    String requirement;
    String nameFile;
    private StreamedContent fileDownload;

    public ServiceAttachmentName() {
    }

    public ServiceAttachmentName(int id, String name, String notes, String requirement, InputStream inputStream ,String nameFile) {
        try {
            this.id = id;
            this.name = name;
            this.notes = notes;
            this.requirement = requirement;
            this.nameFile = nameFile;
            if (inputStream != null) {
               System.out.println(id);
                byte[] inputByte = new byte[inputStream.available()];
                inputStream.read(inputByte);
                byte[] outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop",inputByte );
                InputStream inputForData = new ByteArrayInputStream(outputfinal);
                System.out.println(nameFile);
                fileDownload = new DefaultStreamedContent(inputForData,"file",nameFile);
                
            }
        }catch (IOException ex) {
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
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
        String q = "UPDATE serviceattachmentname SET ServA_Name = '" + name + "',notes = '" + notes + "' WHERE (ServiceAttachmentName_ID = " + id + ");";
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

        String q = "INSERT INTO oss.serviceattachmentname (`ServiceAttachmentName_ID`, `ServA_Name`, `notes`, `requirement`)  VALUES (null,'"
                + name + "','" + notes + "','" + requirement + "');";
        System.out.println(notes);
        System.out.println(requirement);

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
        System.out.println(System.getProperty("user.dir"));
//        saveFileInDisk();
        String q = "INSERT INTO serviceattachmentname VALUES(null,?,?,?,?);";
        System.out.println(q);

        nameFile = file.getFileName();
        try {
            DB data = new DB();
            PreparedStatement s = data.prepareStatement(q);
            s.setString(1, name);
            s.setBinaryStream(2, saveFileInDisk());
            s.setString(3, notes);
            s.setString(4,nameFile);
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

    
    private InputStream saveFileInDisk() {
        try {
            InputStream inp = file.getInputstream();

            byte[] inputByte = new byte[inp.available()];

            inp.read(inputByte);
            System.out.println("-------------------------------------");
            for (int i = 0; i < inputByte.length; i++) {
                byte b = inputByte[i];
                System.out.print(b);
            }
            System.out.println("");
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

}
