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
public class CitizenService {
    int id;
    Service providServicee;
    String date;
    String status;

    public CitizenService() {
    }

    public CitizenService(int id, Service providServicee, String date) {
        this.id = id;
        this.providServicee = providServicee;
        this.date = date;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getProvidServicee() {
        return providServicee;
    }

    public void setProvidServicee(Service providServicee) {
        this.providServicee = providServicee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
