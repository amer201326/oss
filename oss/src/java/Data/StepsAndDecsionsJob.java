/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author baraakali
 */
public class StepsAndDecsionsJob {
    public JobPath jobPath;
    public DecisionsJob decisionsJob;
    AttachmentServiceEmployee attachmentServiceEmployee;
    public StepsAndDecsionsJob(JobPath jobPath, DecisionsJob decisionsJob) {
        this.jobPath = jobPath;
        this.decisionsJob = decisionsJob;
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

    public AttachmentServiceEmployee getAttachmentServiceEmployee() {
        return attachmentServiceEmployee;
    }

    public void setAttachmentServiceEmployee(AttachmentServiceEmployee attachmentServiceEmployee) {
        this.attachmentServiceEmployee = attachmentServiceEmployee;
    }
    
    
}
