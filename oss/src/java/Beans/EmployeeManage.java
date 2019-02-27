/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.Employee;
import Data.GetDB_Eman;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Screen;
import Data.Section;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
public class EmployeeManage implements Serializable {

    List<Employee> employeeList;
    Employee newEmployee;
    
    List<String> emp;
    String[] selectedEmployees;
    List<Screen> screen;

    public EmployeeManage() {

        newEmployee = new Employee();
        screen = GetDB_Eman.getScreens();

    }

    public EmployeeManage(List<Employee> employeeList, Employee newEmployee, String[] selectedEmployees) {
        this.employeeList = employeeList;
        this.newEmployee = newEmployee;
        this.selectedEmployees = selectedEmployees;
    }

    
    public List<Screen> getScreen() {
        return screen;
    }

    public void setScreen(List<Screen> screen) {
        this.screen = screen;
    }
}
