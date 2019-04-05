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
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class ServicesInDepartmentManeger implements Serializable{
    
       Department thisDepartment;

    public ServicesInDepartmentManeger() {
          Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
          String param = parameterMap.get("id");
          thisDepartment = GetFromDB.getDepartmentById(param);
    }
    public  List<Service> geServicesInDepartment() {
        return GetFromDB.geServicesInDepartment(thisDepartment.id);
    }

    public Department getThisDepartment() {
        return thisDepartment;
    }

    public void setThisDepartment(Department thisDepartment) {
        this.thisDepartment = thisDepartment;
    }
       
   
}
