/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Amer$_$
 */
public class ServiceAttachmentName implements Serializable {

    int id;
    String name;
    String srcFile;
    String notes;
    UploadedFile file;
    String requirement;

    public ServiceAttachmentName() {
    }

    public ServiceAttachmentName(int id, String name, String srcFile, String notes) {
        this.id = id;
        this.name = name;
        this.srcFile = srcFile;
        this.notes = notes;
        file = new DefaultUploadedFile();
    }

    public ServiceAttachmentName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ServiceAttachmentName(int id, String name, String srcFile) {
        this.id = id;
        this.name = name;
        this.srcFile = srcFile;
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

    public String getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(String srcFile) {
        this.srcFile = srcFile;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        saveFileInDisk();
        String q = "INSERT INTO serviceattachmentname (`ServiceAttachmentName_ID`, `ServA_Name`, `File_src`, `notes`) VALUES(null,'" + name + "','" + srcFile + "','" + notes + "');";
        System.out.println(q);
        
        System.out.println(file.getFileName());
        try {
            DB data = new DB();
            //data.write(q);

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
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>in set>>>>>>>>>>>>>>>>>>>>>>"+file.getFileName());
        this.file = file;

    }

    private void saveFileInDisk() {
        
        try {
            System.out.println(file.getSize());
            InputStream in = file.getInputstream();
            
            File f = new File("E:/oss/"+file.getFileName());
            
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            srcFile = "files/" + file.getFileName();
            out.close();
            in.close();

            

            System.out.println("uploaded pdf");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void uploadFile(){
        try {
            System.out.println(file.getSize());
            InputStream in = file.getInputstream();
            
            File f = new File("E:/oss/"+file.getFileName());
            
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            srcFile = "files/" + file.getFileName();
            out.close();
            in.close();

            

            System.out.println("uploaded pdf");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
