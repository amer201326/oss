/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class AddManManager implements Serializable{
    
      public void gotToEdit(String id){
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("editManager.xhtml?id="+id);
        } catch (IOException ex) {
            Logger.getLogger(AddManManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}