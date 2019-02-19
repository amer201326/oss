package Data;

import java.io.Serializable;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author me
 */
public class SectionPath implements Serializable{
    int id;
    String name;
    List<JobTitel> jobs;

    public SectionPath() {
    }

    public SectionPath(int id, String name, List<JobTitel> jobs) {
        this.id = id;
        this.name = name;
        this.jobs = jobs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JobTitel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobTitel> jobs) {
        this.jobs = jobs;
    }
    
}
