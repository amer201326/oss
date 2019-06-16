/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.Employee;
import Data.GetFromDB;
import java.io.IOException;
import java.util.List;
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
    List<Department> allDepartments;

    
     @ManagedProperty(value = "#{msession}")
    Session session;
	
	  String Type = "";
	
	
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
    
    public void editEmployeePassword() {

        employeeEdit.updateEmployeePassword();
        System.out.println("amerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        
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
   
        public void chagneType() {
        System.out.println(Type);
    }
        
     public boolean checkTypeEMP() {
        if (Type.compareTo("3") == 0) {
            return true;
        } else {
            return false;
        }
    }
     
       public boolean checkTypeHed() {
        if (Type.compareTo("1") == 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Department> getAllDepartments() {
        return allDepartments;
    }

    public void setAllDepartments(List<Department> allDepartments) {
        this.allDepartments = allDepartments;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    
    public String returnEmployeeType() {
        if (employeeEdit.getType().compareTo("e") == 0) {
            return "موظف";
        } else if (employeeEdit.getType().compareTo("h") == 0) {
            return "مسؤول";
        } else{
            return "رئيس دائرة";
        }
    }
    
     public String returnEmployeeGender() {
        if (employeeEdit.getEmp_gender().compareTo("M") == 0) {
            return "ذكر";
        }else{
            return "انثى";
        }
    }
  
    
}
