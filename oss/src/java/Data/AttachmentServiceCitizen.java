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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author baraakali
 */
public class AttachmentServiceCitizen {
    
    int Atta_ArchiveC_ID;
    int Service_Citizen_ID;
    int Services_Provided_ID;
    int Cit_ID;
    UploadedFile file;

    public AttachmentServiceCitizen(int Atta_ArchiveC_ID, int Service_Citizen_ID, int Services_Provided_ID, int Cit_ID, UploadedFile file) {
        this.Atta_ArchiveC_ID = Atta_ArchiveC_ID;
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
        this.file = file;
    }

    
    
    
    public boolean addToDataBase() {
        
     String  q = "INSERT INTO attachment_service_citizen (`Atta_ArchiveC_ID`, `Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`,`file`) VALUES "
               + " (?,?,?,?,?);";

        System.out.println(q);

        
        try {
            DB data = new DB();
            PreparedStatement s = data.prepareStatement(q);
            
            s.setInt(1, Atta_ArchiveC_ID);
            s.setInt(2, Service_Citizen_ID);
            s.setInt(3,Services_Provided_ID);
            s.setInt(4,Cit_ID);
            //s.setBinaryStream(5, saveFileInDisk());
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttachmentServiceCitizen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AttachmentServiceCitizen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
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
            Logger.getLogger(AttachmentServiceCitizen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
}
