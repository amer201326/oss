/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Citizen;
import Data.CitizenProfile;
import Data.GetDB_Eman;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class CitizenProfileManager implements Serializable {

    Citizen updateProfile;

    int[] allParameters;
    @ManagedProperty(value = "#{msession}")
    Session session;

    public CitizenProfileManager() {
       
    }

    @PostConstruct
    public void init() {
        if (session.citizen != null) {
            updateProfile = session.citizen;
            updateProfile.getAccount().setPassword("");
             allParameters = GetDB_Eman.getAllParametersCitizenDashboard(session.citizen.getId());
        }

    }

    public void updateCitizen() {

        updateProfile.updateCitizen();

    }

    public Citizen getUpdateProfile() {
        return updateProfile;
    }

    public void setUpdateProfile(Citizen updateProfile) {
        this.updateProfile = updateProfile;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int[] getAllParameters() {
        return allParameters;
    }

    public void setAllParameters(int[] allParameters) {
        this.allParameters = allParameters;
    }

}
