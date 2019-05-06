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
public class StepsAndDecsionsJob {
    public JobPath jobPath;
    public DecisionsJob decisionsJob;
    List<AttachmentServiceEmployee> attachmentServiceEmployee;
    
    public StepsAndDecsionsJob(JobPath jobPath, DecisionsJob decisionsJob) {
        this.jobPath = jobPath;
        this.decisionsJob = decisionsJob;
        attachmentServiceEmployee = new ArrayList<>();
    }

    public JobPath getJobPath() {
        return jobPath;
    }

    public void setJobPath(JobPath jobPath) {
        this.jobPath = jobPath;
    }

    public DecisionsJob getDecisionsJob() {
        return decisionsJob;
    }

    public void setDecisionsJob(DecisionsJob decisionsJob) {
        this.decisionsJob = decisionsJob;
    }

    public List<AttachmentServiceEmployee> getAttachmentServiceEmployee() {
        return attachmentServiceEmployee;
    }

    public void setAttachmentServiceEmployee(List<AttachmentServiceEmployee> attachmentServiceEmployee) {
        this.attachmentServiceEmployee = attachmentServiceEmployee;
    }

    
    
    
}
