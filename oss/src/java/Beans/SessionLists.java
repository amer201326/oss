/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.GetFromDB;
import Data.Section;
import Data.SectionPath;
import Data.Service;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Amer$_$
 */
@ManagedBean(name = "sessionLists")
@SessionScoped
public class SessionLists implements Serializable{
    Department departmentSelected;
    public SessionLists() {
        departmentSelected = new Department();
    }

    public Department getDepartmentSelected() {
        return departmentSelected;
    }

    public void setDepartmentSelected(Department departmentSelected) {
        this.departmentSelected = departmentSelected;
    }
    
    

   

    
 
}
