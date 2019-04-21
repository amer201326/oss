/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.GetDB_Eman;
import Data.HomePage;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class HomePageManager implements Serializable{
    
    String[] allDetails; 
    int[] allParameters; 
    List<HomePage> serviceDep; 
    
    public HomePageManager() {
        allDetails = GetDB_Eman.getHomePageDetails();
        allParameters = GetDB_Eman.getAllParameters();
        serviceDep = GetDB_Eman.getServicesByDep();
        
    }

    public String[] getAllDetails() {
        return allDetails;
    }

    public void setAllDetails(String[] allDetails) {
        this.allDetails = allDetails;
    }

    public int[] getAllParameters() {
        return allParameters;
    }

    public void setAllParameters(int[] allParameters) {
        this.allParameters = allParameters;
    }

    public List<HomePage> getServiceDep() {
        return serviceDep;
    }

    public void setServiceDep(List<HomePage> serviceDep) {
        this.serviceDep = serviceDep;
    }

    
    
}
