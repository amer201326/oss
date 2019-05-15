/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Beans.Session;
import DB.DB;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amer
 */
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = -6449908958106497977L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("her her herher her he rh ");
        
        Session ms = (Session)((HttpServletRequest)req).getSession().getAttribute("msession");
        // Get last uploaded image
        
            // Image bytes
            String type = req.getParameter("type");
            if(type.compareTo("archsit")==0){
                AttachmentServiceEmployee att = ms.getSelectAtt();
                
                
                
            // Load driver
//            DB d = new DB();
//            ResultSet r = d.read("SELECT file FROM oss.attachment_archive_citizen where Atta_ArchiveC_ID =2;");
//           InputStream in = null;
//            while (r.next()) {
//             in = r.getBinaryStream(1);
//            }
//            
//            byte[] inputByte = new byte[in.available()];
//                in.read(inputByte);
//                byte[] outputfinal = Crypto.dec(Cipher.DECRYPT_MODE, "foreanderDowntop", inputByte);
//                
//            
            
            
            resp.getOutputStream().write(att.outputfinal);
            resp.getOutputStream().close();
            }
            System.out.println("done");
        

    }

}
