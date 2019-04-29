/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eman
 */
@ManagedBean

public class ImagesView implements Serializable{
     
    private List<String> images;
     
    public ImagesView(){
    }
    
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 6; i++) {
            images.add("image" + i + ".jpg");
            System.out.println(images);
        }
    }

    
    public ImagesView(List<String> images) {
        this.images = images;
    }
    
   
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
 
    
}
