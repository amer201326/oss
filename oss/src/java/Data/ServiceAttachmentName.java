/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Amer$_$
 */
public class ServiceAttachmentName {
    int id;
    String name;
    String srcFile;

    public ServiceAttachmentName() {
    }
    
    
    
    public ServiceAttachmentName(int id, String name, String srcFile) {
        this.id = id;
        this.name = name;
        this.srcFile = srcFile;
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

    public String getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(String srcFile) {
        this.srcFile = srcFile;
    }
    
}
