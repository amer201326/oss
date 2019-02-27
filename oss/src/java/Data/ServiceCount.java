/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;

/**
 *
 * @author me
 */
public class ServiceCount implements Serializable{
    String serviceName;
    int Count;

    public ServiceCount(String serviceName, int Count) {
        this.serviceName = serviceName;
        this.Count = Count;
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

    @Override
    public String toString() {
        return "ServiceCount{" + "serviceName=" + serviceName + ", Count=" + Count + '}';
    }
    
    
    
}
