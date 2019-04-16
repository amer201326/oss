/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentArchiveCitizen;
import Data.GetFromDB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Amer
 */
@ManagedBean
@ViewScoped
public class ArchiveCitizen implements Serializable {

    List<AttachmentArchiveCitizen> archivesCitizens = new ArrayList<>();

    public ArchiveCitizen() {

    }

    @ManagedProperty(value = "#{msession}")
    Session session;

    @PostConstruct
    public void init() {
        if (session.citizen != null) {
            archivesCitizens = GetFromDB.getAttachmantsArchive(session.citizen.getId());
        }
    }

}
