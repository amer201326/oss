/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.GetFromDB;
import Data.Section;
import Data.ServiceErr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author me
 */
@ManagedBean
public class ServiceManager implements Serializable{
    List<ServiceErr> allSrvices;
    
    int orderDepartment;
    int idDepartment;
    static List<Department> departmentsInPath ;

    Department selectDepartment;
    public ServiceManager() {
        allSrvices = new ArrayList<ServiceErr>();
        departmentsInPath = new ArrayList<Department>();
        selectDepartment = new Department();
    }

    public List<ServiceErr> getAllSrvices() {
        return GetFromDB.getAllServices();
    }

    public void setAllSrvices(List<ServiceErr> allSrvices) {
        this.allSrvices = allSrvices;
    }
    
    public void onServiceEdit(RowEditEvent event) {
        System.out.println("Beans.ServiceManager.onServiceEdit()"+((ServiceErr) event.getObject()).getName());
//        ((ServiceErr) event.getObject()).update();

    }
    public void onServiceEditCancel(RowEditEvent event) {

    }

    public int getOrderDepartment() {
        return orderDepartment;
    }

    public void setOrderDepartment(int orderDepartment) {
        this.orderDepartment = orderDepartment;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
    public void addPathDepartment() {
//        Department d = new Department();
//        d.id = idDepartment;
//        d.getNameFromDataBase();
//        d.setOrder(orderDepartment);
//        departmentsInPath.add(d);
    }

    public static List<Department> getDepartmentsInPath() {
        return departmentsInPath;
    }

    public static void setDepartmentsInPath(List<Department> departmentsInPath) {
        ServiceManager.departmentsInPath = departmentsInPath;
    }

    
    public void onRowSelect(SelectEvent event) {
        
    }
 
    public void onRowUnselect(UnselectEvent event) {
        
    }

    public Department getSelectDepartment() {
        return selectDepartment;
    }

    public void setSelectDepartment(Department selectDepartment) {
        this.selectDepartment = selectDepartment;
    }
    
      public List<Section> allPathSectionInDepartment() {
       return null;
    }
}
