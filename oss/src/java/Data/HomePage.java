/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eman
 */
public class HomePage implements Serializable {

    String homepage_ID;
    String images;
    String address;
    String telephone;
    String fax;
    String email;
    String description;
    String servName;
    String depName;
    String munName;
    String munCity;
    String munQuarter;
    String munStreet;
    int serviceCount;
    int citizenCount;
    int employeeCount;
    int doneCount;

    public HomePage(String homepage_ID, String images, String address, String telephone, String fax, String email, String description, String munName, String munCity, String munQuarter, String munStreet) {
        this.homepage_ID = homepage_ID;
        this.images = images;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.description = description;
        
        this.munName = munName;
        this.munCity = munCity;
        this.munQuarter = munQuarter;
        this.munStreet = munStreet;
    }
    

    
    
    
    public HomePage(String homepage_ID, String images, String address, String telephone, String fax, 
            String email, String description, String munName, String munCity) {
        this.homepage_ID = homepage_ID;
        this.images = images;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.description = description;
        this.munName = munName;
        this.munCity = munCity;
    }

    public HomePage(int serviceCount, int citizenCount, int employeeCount, int doneCount) {
        this.serviceCount = serviceCount;
        this.citizenCount = citizenCount;
        this.employeeCount = employeeCount;
        this.doneCount = doneCount;
    }

    public HomePage(String servName, String depName) {
        this.servName = servName;
        this.depName = depName;
    }
    
    

    public HomePage() {
    }

    
    
    public String getHomepage_ID() {
        return homepage_ID;
    }

    public void setHomepage_ID(String homepage_ID) {
        this.homepage_ID = homepage_ID;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(int serviceCount) {
        this.serviceCount = serviceCount;
    }

    public int getCitizenCount() {
        return citizenCount;
    }

    public void setCitizenCount(int citizenCount) {
        this.citizenCount = citizenCount;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getServName() {
        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDoneCount() {
        return doneCount;
    }

    public void setDoneCount(int doneCount) {
        this.doneCount = doneCount;
    }

    public String getMunName() {
        return munName;
    }

    public void setMunName(String munName) {
        this.munName = munName;
    }

    public String getMunCity() {
        return munCity;
    }

    public String getMunQuarter() {
        return munQuarter;
    }

    public void setMunQuarter(String munQuarter) {
        this.munQuarter = munQuarter;
    }

    public void setMunCity(String munCity) {
        this.munCity = munCity;
    }

    public String getMunStreet() {
        return munStreet;
    }

    public void setMunStreet(String munStreet) {
        this.munStreet = munStreet;
    }
    
    
     public void updateHomePage() {
      String q = "UPDATE oss.homepage_data SET address = '" + address + "',telephone = '" + telephone
                + "', fax = '" + fax + "',email = '" + email
                + "', description = '" + description + "',munName = '" + munName
                + "', munCity = '" + munCity + "',munQuarter = '" + munQuarter + "', munStreet = '"
                + munStreet + "' WHERE (homepage_ID = 1);";
      try {
            DB data = new DB();
            data.write(q);
            System.out.println(q);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
