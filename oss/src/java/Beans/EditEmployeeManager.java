/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Employee;
import Data.GetFromDB;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EditEmployeeManager {
    
    Employee employeeEdit;
    
     @ManagedProperty(value = "#{msession}")
    Session session;
	
	
	
	
    public EditEmployeeManager() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String param = parameterMap.get("id");
        
        employeeEdit = GetFromDB.GetEmployeeById(param);
        
        
    }

    public Employee getEmployeeEdit() {
        return employeeEdit;
    }

    public void setEmployeeEdit(Employee employeeEdit) {
        this.employeeEdit = employeeEdit;
    }
    
    
    public void editEmployee() {

        employeeEdit.updateEmployee();
        
    }
    
      public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
     public String urlSideBar() {
        if (session.employee != null) {
            if (session.employee.checkTypeAdmin()) {
                System.out.println("cheackAdmin is  = " + session.employee.checkTypeAdmin());
                return "../pages/sidebar.xhtml";
            }
        }
        if (session.employee != null) {
            if (session.employee.checkTypeEMP()) {

                System.out.println("cheackemp is  = " + session.employee.checkTypeEMP());

                return "../employeePages/sidebar.xhtml";
            }
        }
        return "";
    }
   
    
    
  
    
}
