/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.Employee;
import Data.GetFromDB;
import Data.JobOfSection;
import Data.JobTitel;
import Data.Section;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
@ViewScoped
public class DepartmentManager implements Serializable{

    List<Department> departments;
    List<Section> sections;
    List<JobOfSection> jobsOfSections;
    List<JobOfSection> filterJobsOfSections;
    Department thisDepartment;
    List<JobTitel> jobTitels;
    Section sectionSelected;
    Section newSection;
    List<Employee> employees;
    JobOfSection jobSelected;
    JobOfSection newJobOfsection;

    ///////////////
    private OrganigramNode rootNode;
    private OrganigramNode selection;

    private boolean zoom = false;
    private String style = "width: 800px";
    private int leafNodeConnectorHeight = 0;
    private boolean autoScrollToSelection = false;

    /////////////
    public DepartmentManager() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String param = parameterMap.get("id");

        thisDepartment = GetFromDB.getDepartmentById(param);

        jobTitels = GetFromDB.getJobTittle();
        sections = GetFromDB.getFsection(Integer.parseInt(param));
        jobsOfSections = GetFromDB.getJobOfSectio(param);
        employees = GetFromDB.GetEmployeeinDepartment(param);
        jobSelected = new JobOfSection();
        newSection = new Section();
        newJobOfsection = new JobOfSection();
        sectionSelected = new Section();
        ////////////////
        creatOrganic();

        //////////////
    }
 

    public void onSectionEdit(RowEditEvent event) {
        Section s = ((Section) event.getObject());
        s.update();
        creatOrganic();
        jobsOfSections = GetFromDB.getJobOfSectio(thisDepartment.id+"");
        

    }
     public void addJobForSec() {
        
        if(!newJobOfsection.addToDB()){
           
            FacesContext.getCurrentInstance().addMessage("formJob:tableJob", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "لا يمكن اضافة نفس الوظيفة مرتين"));
        }else{
            creatOrganic();
            newJobOfsection.setName(getJobName(newJobOfsection.getIdJob()));
            newJobOfsection.setSEctionName(getSecName(newJobOfsection.getIdSEction()));
             jobsOfSections.add(newJobOfsection);
            newJobOfsection = new JobOfSection();
        }
    }

   public String getJobName(int id){
       for (JobTitel jobTitel : jobTitels) {
           if(jobTitel.getId().compareTo(id+"")==0){
               return jobTitel.getName();
           }
       }
       return "refreash";
   }

    public void onSectionSelected(SelectEvent event) {
        System.out.println("form al ajax " + ((Section) event.getObject()).getId());
        sectionSelected = ((Section) event.getObject());
    }

    public void onJobSelected(SelectEvent event) {
       
        jobSelected = (JobOfSection) event.getObject();
    }

    public void deleteSection() {
        sectionSelected.deleteFromDB();
        sections.remove(sectionSelected);
        creatOrganic();
    }

    public void addSection() {
        newSection.setDepartmentId(thisDepartment.id+"");
        newSection.addToDB();
        
        sections = GetFromDB.getFsection(thisDepartment.id);
        creatOrganic();
        newSection = new Section();

    }

    public void deleteJopTitle() {
        jobSelected.delete();
        creatOrganic();
        jobsOfSections.remove(jobSelected);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<JobOfSection> getJobsOfSections() {
        return jobsOfSections;
    }

    public void setJobsOfSections(List<JobOfSection> jobsOfSections) {
        this.jobsOfSections = jobsOfSections;
    }

    public Department getThisDepartment() {
        return thisDepartment;
    }

    public void setThisDepartment(Department thisDepartment) {
        this.thisDepartment = thisDepartment;
    }

    public List<JobOfSection> getFilterJobsOfSections() {
        return filterJobsOfSections;
    }

    public void setFilterJobsOfSections(List<JobOfSection> filterJobsOfSections) {
        this.filterJobsOfSections = filterJobsOfSections;
    }

    public List<JobTitel> getJobTitels() {
        return jobTitels;
    }

    public void setJobTitels(List<JobTitel> jobTitels) {
        this.jobTitels = jobTitels;
    }

    public Section getSectionSelected() {
        return sectionSelected;
    }

    public void setSectionSelected(Section sectionSelected) {
        this.sectionSelected = sectionSelected;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public OrganigramNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(OrganigramNode rootNode) {
        this.rootNode = rootNode;
    }

    public OrganigramNode getSelection() {
        return selection;
    }

    public void setSelection(OrganigramNode selection) {
        this.selection = selection;
    }

    public boolean isZoom() {
        return zoom;
    }

    public void setZoom(boolean zoom) {
        this.zoom = zoom;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getLeafNodeConnectorHeight() {
        return leafNodeConnectorHeight;
    }

    public void setLeafNodeConnectorHeight(int leafNodeConnectorHeight) {
        this.leafNodeConnectorHeight = leafNodeConnectorHeight;
    }

    public boolean isAutoScrollToSelection() {
        return autoScrollToSelection;
    }

    public void setAutoScrollToSelection(boolean autoScrollToSelection) {
        this.autoScrollToSelection = autoScrollToSelection;
    }

    public Section getNewSection() {
        return newSection;
    }

    public void setNewSection(Section newSection) {
        this.newSection = newSection;
    }

    public JobOfSection getJobSelected() {
        return jobSelected;
    }

    public void setJobSelected(JobOfSection jobSelected) {
        this.jobSelected = jobSelected;
    }

    public JobOfSection getNewJobOfsection() {
        return newJobOfsection;
    }

    public void setNewJobOfsection(JobOfSection newJobOfsection) {
        this.newJobOfsection = newJobOfsection;
    }

    private void creatOrganic() {
        rootNode = new DefaultOrganigramNode("department", "دائرة : " + thisDepartment.nameA, null);
        rootNode.setCollapsible(false);
        rootNode.setDroppable(false);
        rootNode.setSelectable(false);

        for (int i = 0; i < sections.size(); i++) {
            Section s = sections.get(i);

            OrganigramNode rootNodee2 = new DefaultOrganigramNode("section", "قسم : " + s.getName(), null);

            rootNode.getChildren().add(rootNodee2);

            try {

                for (int j = 0; j < jobsOfSections.size(); j++) {
                    JobOfSection get = jobsOfSections.get(j);
                    if (get.getIdSEction() == Integer.parseInt(s.getId())) {
                        List<Employee> emp = GetFromDB.GetEmployeeForJobID(get.getIdJob() + "");
                        String[] empName = new String[emp.size()];
                        OrganigramNode divisionNode = new DefaultOrganigramNode("job", get.getName(), rootNodee2);

                        System.out.println(divisionNode.getType());
                        for (int k = 0; k < emp.size(); k++) {
                            Employee get1 = emp.get(k);
                            empName[k] = get1.getEmp_name();
                            OrganigramNode employeeNode = new DefaultOrganigramNode("employee", empName[k], divisionNode);

                        }
                    }

                }

            } catch (Exception e) {
                break;
            }

        }
    }

    private String getSecName(int idSEction) {
        for (Section section : sections) {
            if(section.getId().compareTo(idSEction+"")==0){
                return section.getName();
            }
        }
        return "refresh";
    }
    

}
