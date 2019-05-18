/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentArchiveCitizen;
import Data.GetFromDB;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Amer
 */
@ManagedBean(name = "archiveCitizen")
@ViewScoped
public class ArchiveCitizen implements Serializable {

    AttachmentArchiveCitizen selected;
    List<AttachmentArchiveCitizen> archivesCitizens = new ArrayList<>();
    DefaultStreamedContent image;
    public ArchiveCitizen() {
        image = new DefaultStreamedContent();
    }

    @ManagedProperty(value = "#{msession}")
    Session session;

    @PostConstruct
    public void init() {
        if (session.citizen != null) {
            archivesCitizens = GetFromDB.getAttachmantsArchiveJustFile(session.citizen.getId());
            
        }
    }

    public List<AttachmentArchiveCitizen> getArchivesCitizens() {
        return archivesCitizens;
    }

    public void setArchivesCitizens(List<AttachmentArchiveCitizen> archivesCitizens) {
        this.archivesCitizens = archivesCitizens;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<AttachmentArchiveCitizen> filterAtta() {
        List<AttachmentArchiveCitizen> a = new ArrayList<>();
        int id = 0;
        if (!archivesCitizens.isEmpty()) {
            AttachmentArchiveCitizen ar = archivesCitizens.get(0);
            id = ar.getServiceAttachmentName_ID();
            a.add(ar);
            for (AttachmentArchiveCitizen archivesCitizen : archivesCitizens) {
                if (archivesCitizen.getServiceAttachmentName_ID() != id) {
                    System.out.println("id is  ?>>> " + id);
                    a.add(archivesCitizen);
                    id = archivesCitizen.getServiceAttachmentName_ID();
                }
            }
        }

        return a;
    }

    public List<AttachmentArchiveCitizen> filterAtta(int id) {
        List<AttachmentArchiveCitizen> a = new ArrayList<>();

        for (AttachmentArchiveCitizen archivesCitizen : archivesCitizens) {
            System.out.println(archivesCitizen.getNameFile());
            
            if (archivesCitizen.getServiceAttachmentName_ID() == id) {
                a.add(archivesCitizen);
                
            }
        }

        return a;
    }

    public AttachmentArchiveCitizen getSelected() {
        return selected;
    }

    public void setSelected(AttachmentArchiveCitizen selected) {
        image = new DefaultStreamedContent(selected.getFileDownload().getStream());
        this.selected = selected;
        System.out.println(selected.getNameFile() + "11111111111111111111111111111111111111111111111111111");
    }

    

    public DefaultStreamedContent getImage() {
        return image;
    }

    public void setImage(DefaultStreamedContent image) {
        this.image = image;
    }
    
}
