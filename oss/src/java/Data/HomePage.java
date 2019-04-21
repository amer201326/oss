/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;

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

    public HomePage(String homepage_ID, String images, String address, String telephone, String fax, String email, String description) {
        this.homepage_ID = homepage_ID;
        this.images = images;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.description = description;
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

  
    
}
