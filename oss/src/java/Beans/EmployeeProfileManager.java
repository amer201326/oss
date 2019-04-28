/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Employee;
import Data.GetDB_Eman;
import Data.GetFromDB;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class EmployeeProfileManager implements Serializable{
    
    Employee updateProfile;
    @ManagedProperty(value = "#{msession}")
    Session session;

     public EmployeeProfileManager() {
       
    }

    @PostConstruct
    public void init() {
         
        if (session.employee != null) {
            
            updateProfile = GetDB_Eman.GetEmployeeData(session.employee.getEmp_id());
            
            
            updateProfile.getAccount().setPassword("");
           
           
        }

    }
    
      public void updateEmployee() {

        updateProfile.updateEmployee();

    }

    
    public Employee getUpdateProfile() {
        return updateProfile;
    }

    public void setUpdateProfile(Employee updateProfile) {
        this.updateProfile = updateProfile;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
    
}
