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
public class AttachmentArchiveCitizen {

    int Atta_ArchiveC_ID;
    int Cit_ID;
    int ServiceAttachmentName_ID;
    UploadedFile file;

    public AttachmentArchiveCitizen(int Atta_ArchiveC_ID, int Cit_ID, int ServiceAttachmentName_ID, UploadedFile file) {
        this.Atta_ArchiveC_ID = Atta_ArchiveC_ID;
        this.Cit_ID = Cit_ID;
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
        this.file = file;
    }

    public void addToDataBase() {

        String q = "INSERT INTO attachment_archive_citizen (`Atta_ArchiveC_ID`, `Cit_ID`,`ServiceAttachmentName_ID`,`file`) VALUES (?,?,?,?);";

        System.out.println("data is   > < " + Atta_ArchiveC_ID + "  " + Cit_ID + "  " + ServiceAttachmentName_ID);

        try {
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

            System.out.println(q);
            s.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AttachmentArchiveCitizen.class.getName()).log(Level.SEVERE, null, ex);
        }

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

}
