/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentArchiveCitizen;
import Data.Citizen;
import Data.Employee;
import Data.GetFromDB;
import Data.Service;
import Data.ServiceCitizen;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
    import javax.annotation.PostConstruct;
    import javax.faces.bean.ManagedBean;
    import javax.faces.bean.ManagedProperty;
    import javax.faces.bean.ViewScoped;
    import javax.faces.context.FacesContext;
    import org.primefaces.model.DefaultStreamedContent;

    /**
     *
     * @author Amer
     */
    @ManagedBean
    @ViewScoped
    public class HeaderUser implements Serializable {

        Citizen citizen;
        List<ServiceCitizen> serviceCitizens = new ArrayList<>();
        int numberOfNot;

        public HeaderUser() {

        }

        @ManagedProperty(value = "#{msession}")
        Session session;

        @PostConstruct
        public void init() {
            if (session.citizen != null) {
                citizen = session.citizen;
                updateNotification();

            }
        }

        public List<ServiceCitizen> getServiceCitizens() {
            return serviceCitizens;
        }

        public void setServiceCitizens(List<ServiceCitizen> serviceCitizens) {
            this.serviceCitizens = serviceCitizens;
        }

        public int getNumberOfNot() {
            return numberOfNot;
        }

        public void setNumberOfNot(int numberOfNot) {
            this.numberOfNot = numberOfNot;
        } 

        public void updateNotification() {
            serviceCitizens = GetFromDB.serviceNotificationCitizen(citizen);

            numberOfNot = serviceCitizens.size();
            System.out.println("size notification is = "+numberOfNot);

    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void showServise() {

        System.out.println("show ssssssssssssssssssssssssssssssssss - -  -");
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("ShowMyService.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
