/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.DepartmentPaths;
import Data.GetFromDBaraa;
import Data.JobPath;
import Data.SectionPath;
import Data.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author me
 */
@ManagedBean
@ViewScoped
public class ServiceManager implements Serializable {

    List<Service> allSrvices;

    int orderDepartment;
    int idDepartment;
    List<DepartmentPaths> departmentsInPath = new ArrayList<DepartmentPaths>();

    int orderSection;
    int idSection;
    List<SectionPath> sectionsInPath = new ArrayList<SectionPath>();

    int orderJob;
    int idJob;
    List<JobPath> jobInPath = new ArrayList<JobPath>();

    DepartmentPaths selectDepartment;
    SectionPath selectSection;

    Service newServie = new Service();

    public ServiceManager() {
        allSrvices = new ArrayList<Service>();
        newServie = new Service();

        selectDepartment = new DepartmentPaths();
        selectSection = new SectionPath();

    }

//    public List<Service> getAllSrvices() {
//        return GetFromDBaraa.getAllServices();
//    }

    public void setAllSrvices(List<Service> allSrvices) {
        this.allSrvices = allSrvices;
    }

    public void onServiceEdit(RowEditEvent event) {
        System.out.println("Beans.ServiceManager.onServiceEdit()" + ((Service) event.getObject()).getName());
        ((Service) event.getObject()).update();

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
        DepartmentPaths d = new DepartmentPaths();
        d.id = idDepartment;
        d.getNameFromDataBase();
        d.setOrder(orderDepartment);
        departmentsInPath.add(d);
        System.out.println("Beans.ServiceManager.addPathDepartment()" + departmentsInPath.size());

    }

    public List<DepartmentPaths> getDepartmentsInPath() {
        return departmentsInPath;
    }

    public void setDepartmentsInPath(List<DepartmentPaths> departmentsInPath) {
        this.departmentsInPath = departmentsInPath;
    }

    public List<SectionPath> getSectionsInPath() {
        return selectDepartment.getSections();
    }

    public void setSectionsInPath(List<SectionPath> SectionsInPath) {
        this.sectionsInPath = SectionsInPath;
    }

    public int getOrderSection() {
        return orderSection;
    }

    public void setOrderSection(int orderSection) {
        this.orderSection = orderSection;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public void addPathSection() {
        SectionPath s = new SectionPath();
        s.setId(idSection);
        s.getNameFromDataBase();
        s.setOrder(orderSection);
        selectDepartment.sections.add(s);

    }

    public void addPathJob() {
        JobPath j = new JobPath();
        j.setId(idJob);
        j.setOrder(orderJob);
        j.getNameFromDataBase();
        selectSection.jobs.add(j);

    }

    public List<JobPath> getJobInPath() {
        return selectSection.jobs;
    }

    public void setJobInPath(List<JobPath> jobInPath) {
        this.jobInPath = jobInPath;
    }

    public void onRowSelect(SelectEvent event) {

    }

    public void onRowUnselect(UnselectEvent event) {

    }

    public DepartmentPaths getSelectDepartment() {
        return selectDepartment;
    }

    public void setSelectDepartment(DepartmentPaths selectDepartment) {
        this.selectDepartment = selectDepartment;
    }

    public SectionPath getSelectSection() {
        return selectSection;
    }

    public void setSelectSection(SectionPath selectSection) {
        this.selectSection = selectSection;
    }

    public int getOrderJob() {
        return orderJob;
    }

    public void setOrderJob(int orderJob) {
        this.orderJob = orderJob;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public void addService() {
        //GetFromDBaraa.addNewService(departmentsInPath);
        System.out.println("kkkkkk"+newServie.toString());
        newServie.setId((int)System.currentTimeMillis());
        newServie.addServiceToDB();
        
    }

    public Service getNewServie() {
        return newServie;
    }

    public void setNewServie(Service newServie) {
        this.newServie = newServie;
    }

}
