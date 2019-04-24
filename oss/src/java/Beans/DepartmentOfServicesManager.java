/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.GetFromDB;
import Data.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class DepartmentOfServicesManager implements Serializable{
    
     

    public DepartmentOfServicesManager() {
    }
    
    public  List<Department> getDepartmentsWithNService() {
        List<Department> department = GetFromDB.getDepartmentsWithNService();
        
        List<Department> departments = new ArrayList<>() ;
        
        for (Department d : department) {
            if(d.getNumberService() > 0){
                departments.add(d);
            }
        }
        return departments;
    }

 

    

}
