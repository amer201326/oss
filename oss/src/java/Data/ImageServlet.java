/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Beans.ArchiveCitizen;
import Beans.Session;
import DB.DB;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
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

    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("her her herher her he rh ");

        Session ms = (Session) ((HttpServletRequest) req).getSession().getAttribute("msession");
        // Get last uploaded image

        // Image bytes
        String type = req.getParameter("type");
        if (type.compareTo("archsit") == 0) {
            System.out.println("ImageServlet   send  AttachmentServiceEmployee");
            AttachmentServiceEmployee att = ms.getSelectAtt();

         
            if (att != null) {
                if (att.outputfinal != null) {
                    resp.getOutputStream().write(att.outputfinal);
                    resp.getOutputStream().close();
                }
            }
        }
        if (type.compareTo("asit") == 0) {

            System.out.println("asit");

            AttachmentArchiveCitizen att = ms.getAttachmentArchiveCitizenSELECTED();
            if (att != null) {
                if (att.outputfinal != null) {
                    System.out.println("from imgserv = " + att.nameFile);
                    resp.getOutputStream().write(att.outputfinal);
                    resp.getOutputStream().close();
                }
            }

        }
        if (type.compareTo("serviceAttachmentName") == 0) {
            System.out.println("serviceAttachmentName");

            ServiceAttachmentName att = ms.getServiceAttachmentName();
            if (att != null) {
                if (att.outputfinal != null) {
                    System.out.println("from imgserv = " + att.nameFile);
                    resp.getOutputStream().write(att.outputfinal);
                    resp.getOutputStream().close();
                }
            }
        }

        System.out.println("done");

    }

}
