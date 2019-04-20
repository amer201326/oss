/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Amer
 */
import Data.ServiceCitizen_1;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ShoeServiceCitizemEmpManeger {

    ServiceCitizen_1 serviseCitizen ;
            
    @ManagedProperty(value = "#{msession}")
    Session session;
    
    boolean haveService = false;
    @PostConstruct
    public void init() {
         serviseCitizen = session.serviceCitizen;
         if(serviseCitizen!=null){
             haveService = true;
         }
        
    }

    public ServiceCitizen_1 getServiseCitizen() {
        return serviseCitizen;
    }

    public void setServiseCitizen(ServiceCitizen_1 serviseCitizen) {
        this.serviseCitizen = serviseCitizen;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isHaveService() {
        return haveService;
    }

    public void setHaveService(boolean haveService) {
        this.haveService = haveService;
    }
    
    
    

}
