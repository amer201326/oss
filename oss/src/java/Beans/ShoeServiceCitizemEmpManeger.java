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
import Data.GetFromDB;
import Data.ServiceAttachmentName;
import Data.ServiceCitizen_1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
public class ShoeServiceCitizemEmpManeger {

    ServiceCitizen_1 serviseCitizen;

    @ManagedProperty(value = "#{msession}")
    Session session;

    boolean haveService = false;

    @PostConstruct
    public void init() {
        serviseCitizen = session.serviceCitizen;
        if (serviseCitizen != null) {
            haveService = true;
            filterFormOrNot();
        }

    }

    public void filterFormOrNot() {
        List<ServiceAttachmentName> allAtt = GetFromDB.getAttachmentByserviceCitizen(serviseCitizen.getServices_Provided_ID(), serviseCitizen.getCit_ID());
        List<ServiceAttachmentName> att = new ArrayList<ServiceAttachmentName>();
        List<ServiceAttachmentName> attform = new ArrayList<ServiceAttachmentName>();

        for (ServiceAttachmentName serviceAttachmentName : allAtt) {
            if ("yes".equals(serviceAttachmentName.getForm())) {
                attform.add(serviceAttachmentName);
            } else {
                att.add(serviceAttachmentName);
            }
        }
        
        serviseCitizen.att = att;
        serviseCitizen.attform = attform;
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

    public void submit() {
        
    }

}
