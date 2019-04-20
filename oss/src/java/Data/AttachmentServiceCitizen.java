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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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

    StreamedContent fileDownload;
    String nameFile;
    String nameAtt;

    public AttachmentServiceCitizen(int Atta_ArchiveC_ID, int Service_Citizen_ID, int Services_Provided_ID, int Cit_ID) {
        this.Atta_ArchiveC_ID = Atta_ArchiveC_ID;
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
    }

    public AttachmentServiceCitizen(int Atta_ArchiveC_ID, int Service_Citizen_ID, int Services_Provided_ID, int Cit_ID, InputStream inputStream, String nameFile, String nameAtt) {
        this.Atta_ArchiveC_ID = Atta_ArchiveC_ID;
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
        this.nameAtt = nameAtt;
        this.nameFile = nameFile;
        if (inputStream != null) {
            try {
                byte[] inputByte = new byte[inputStream.available()];
                inputStream.read(inputByte);
                byte[] outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop", inputByte);
                InputStream inputForData = new ByteArrayInputStream(outputfinal);

                fileDownload = new DefaultStreamedContent(inputForData, "file", nameFile);
            } catch (IOException ex) {
                Logger.getLogger(AttachmentArchiveCitizen.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            System.out.println("no file no file no file no file ");
        }
    }

    public boolean addToDataBase() throws SQLException, ClassNotFoundException {

        String q = "INSERT INTO attachment_service_citizen (`Atta_ArchiveC_ID`, `Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`) VALUES "
                + "(" + Atta_ArchiveC_ID + " , " + Service_Citizen_ID + " , " + Services_Provided_ID + " , " + Cit_ID + ");";

        System.out.println(q);
        DB data = new DB();
        data.write(q);

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
