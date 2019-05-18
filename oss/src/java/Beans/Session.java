/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentArchiveCitizen;
import Data.AttachmentServiceEmployee;
import Data.Citizen;
import Data.Department;
import Data.Employee;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Manager;
import Data.Section;
import Data.SectionPath;
import Data.Service;
import Data.ServiceCitizen;
import Data.ServiceCitizen_1;
import Data.ServiceErr;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Amer$_$
 */
@ManagedBean(name = "msession")
@SessionScoped
public class Session implements Serializable {

    boolean login;
    String typeAccount;

    String username;
    String passWord;

    Manager manager;
    Citizen citizen;
    Employee employee;
    AttachmentServiceEmployee selectAtt;
    ServiceCitizen serviceCitizenShow;
    AttachmentArchiveCitizen attachmentArchiveCitizenSELECTED;
    boolean[] screens;

    public Session() {
        login = false;
        typeAccount = "";

    }

    public void login() {
        System.out.println("login");
        if (username != null) {
            Employee e = GetFromDB.getEmployeeAccount(username, passWord);

            if (e != null) {
                if (e.checkTypeAdmin()) {

                    try {
                        login = true;
                        typeAccount = "manager";
                        employee = e;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("../manager/");
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);

                    }
                } else {
                    try {
                        login = true;
                        typeAccount = "employee";
                        employee = e;
                        screens = GetFromDB.arrBoolScreens(employee.getAccount().getUserName());
                        FacesContext.getCurrentInstance().getExternalContext().redirect("../employeePages/");

                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }

            } else {
                Citizen c = GetFromDB.getCitizenAccount(username, passWord);
                if (c != null) {
                    try {
                        login = true;
                        typeAccount = "citizen";
                        citizen = c;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("../citizenn/");
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);

                    }
                } else {
                    System.out.println("errrorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
                    
                }
            }
        }
    }

    public void logout() {
        System.out.println("out");
        try {
            login = false;
            typeAccount = "";
            citizen = null;
            employee = null;
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ServiceCitizen getServiceCitizenShow() {
        return serviceCitizenShow;
    }

    public AttachmentArchiveCitizen getAttachmentArchiveCitizenSELECTED() {
        return attachmentArchiveCitizenSELECTED;
    }

    public void setAttachmentArchiveCitizenSELECTED(AttachmentArchiveCitizen attachmentArchiveCitizenSELECTED) {
        System.out.println("set select att "+attachmentArchiveCitizenSELECTED.getNameFile());
        this.attachmentArchiveCitizenSELECTED = attachmentArchiveCitizenSELECTED;
    }

    public void setServiceCitizenShow(ServiceCitizen serviceCitizenShow) {
        this.serviceCitizenShow = serviceCitizenShow;
    }

    public boolean[] getScreens() {
        return screens;
    }

    public void setScreens(boolean[] screens) {
        this.screens = screens;
    }

    public AttachmentServiceEmployee getSelectAtt() {
        return selectAtt;
    }

    public void setSelectAtt(AttachmentServiceEmployee selectAtt) {
        this.selectAtt = selectAtt;
    }
    
    public void onServiceSelect(SelectEvent event){
        selectAtt = (AttachmentServiceEmployee) event.getObject();
    }

}
