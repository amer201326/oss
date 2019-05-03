/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author me
 */
public class ServiceCount implements Serializable {

    int ServicesProvidedID;
    String serviceName;
    int Count;

    public ServiceCount() {
    }

    public ServiceCount(int ServicesProvidedID, String serviceName, int Count) {
        this.ServicesProvidedID = ServicesProvidedID;
        this.serviceName = serviceName;
        this.Count = Count;
    }

    public ServiceCount(int ServicesProvidedID, int Count) {
        this.ServicesProvidedID = ServicesProvidedID;
        this.Count = Count;
    }

    public ServiceCount(String serviceName, int Count) {
        this.serviceName = serviceName;
        this.Count = Count;
    }

    public ServiceCount(int ServicesProvidedID) {
        this.ServicesProvidedID = ServicesProvidedID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public int getServicesProvidedID() {
        return ServicesProvidedID;
    }

    public void setServicesProvidedID(int ServicesProvidedID) {
        this.ServicesProvidedID = ServicesProvidedID;
    }

    public void addToDB() throws SQLException, ClassNotFoundException {

        DB db = new DB();
        String q = "INSERT INTO `oss`.`service_count` (`Services_Provided_ID`, `count`, `Serv_Name`) VALUES (" + ServicesProvidedID + ", " + Count + ", '" + serviceName + "');";
        db.write(q);

    }


    @Override
    public String toString() {
        return "ServiceCount{" + "serviceName=" + serviceName + ", Count=" + Count + '}';
    }

}
