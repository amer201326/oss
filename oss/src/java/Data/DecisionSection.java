/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baraakali
 */
public class DecisionSection {
    SectionPath section;
    List<StepsAndDecsionsJob> job = new ArrayList<>();

    
    public SectionPath getSection() {
        return section;
    }

    public void setSection(SectionPath section) {
        this.section = section;
    }

    public List<StepsAndDecsionsJob> getJob() {
        return job;
    }

    public void setJob(List<StepsAndDecsionsJob> job) {
        this.job = job;
    }
    
    
    
}
