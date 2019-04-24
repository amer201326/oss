/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.DecisionSection;
import Data.DecisionsDepartment;
import Data.DecisionsJob;
import Data.DepartmentPaths;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.JobPath;
import Data.StepsAndDecsions;
import Data.SectionPath;
import Data.Service;
import Data.StepsAndDecsionsJob;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ShowMyServicesDoneManager implements Serializable {

    Service thisService = new Service();
    List<DecisionsDepartment> MyServicedepartmentPath;

    List<StepsAndDecsions> path;

    public ShowMyServicesDoneManager() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String param = parameterMap.get("id");
        thisService = GetFromDB.getServiceByID2(param);
        thisService.setPath(servicePath(thisService.getId()));
        //MyServicedepartmentPath = MyServicedepartmentPath(thisService.getId(), 1, 1);

        path = paths(1, 1);
    }

    public Service getThisService() {
        return thisService;
    }

    public void setThisService(Service thisService) {
        this.thisService = thisService;
    }

    private List<DepartmentPaths> servicePath(int idSer) {
        List<DepartmentPaths> departments = GetFromDBaraa.departmentPath(idSer);
        List<SectionPath> sections = GetFromDBaraa.sectionPath(idSer);
        List<JobPath> jobs = GetFromDBaraa.jobPath(idSer);
        for (DepartmentPaths department : departments) {
            for (SectionPath section : sections) {
                if (department.id == section.getDepartmentId() && department.order == section.getOrderDepartment()) {
                    System.out.println("ggggg" + department.toString());
                    department.sections.add(section);

                    for (JobPath job : jobs) {
                        if (section.getId() == job.getSectionID() && section.getOrder() == job.getsOrder()) {
                            section.jobs.add(job);
                        }

                    }
                }
            }

        }

        return departments;
    }

//    public List<DecisionsDepartment> MyServicedepartmentPath(int idservice, int idcitizen, int idSerCit) {
//        List<DecisionsDepartment> dd = GetFromDBaraa.MyServicedepartmentPath(idservice, idcitizen, idSerCit);
//        List<DecisionsJob> dj = GetFromDBaraa.MyServiceJobPath(idservice, idcitizen, idSerCit);
//        for (DecisionsDepartment d : dd) {
//
//            if (d.getDepId() == j.getJob().getDepId() && d.getDepOrder() == j.getJob().getdOrder()) {
//                d.getJobs().add(j);
//            }
//
//        }
//        return dd;
//    }
    public List<StepsAndDecsions> paths(int idcitizen, int idSerCit) {
        List<StepsAndDecsions> pathD = GetFromDBaraa.stepAndDecDep(idcitizen, idSerCit);
        List<DecisionSection> pathS = GetFromDBaraa.sectionsteps(thisService.getId());
        List<StepsAndDecsionsJob> pathJ = GetFromDBaraa.stepAndDecJop(idcitizen, idSerCit);
        System.out.println("lllll" + pathJ.size());
        for (StepsAndDecsions d : pathD) {
            for (DecisionSection s : pathS) {
                if (d.getDepartmentPaths().id == s.getSection().getDepartmentId() && d.getDepartmentPaths().order == s.getSection().getOrderDepartment()) {
                    d.getSections().add(s);
                    for (StepsAndDecsionsJob j : pathJ) {
                        if (s.getSection().getId() == j.getJobPath().getSectionID() && s.getSection().getOrder() == j.getJobPath().getsOrder()) {
                            s.getJob().add(j);
                        }
                    }
                }
            }
        }
        return pathD;
    }

    public List<DecisionsDepartment> getMyServicedepartmentPath() {
        return MyServicedepartmentPath;
    }

    public void setMyServicedepartmentPath(List<DecisionsDepartment> MyServicedepartmentPath) {
        this.MyServicedepartmentPath = MyServicedepartmentPath;
    }

    public List<StepsAndDecsions> getPath() {
        return path;
    }

    public void setPath(List<StepsAndDecsions> path) {
        this.path = path;
    }

}
