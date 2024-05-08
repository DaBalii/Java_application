/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.entities;

import java.util.Date;

/**
 *
 * @author USER
 */
public class ClientParticulier extends Client {
    
    private Date dateNaissance;
    private String lieuNaissance;

    public ClientParticulier(Integer id, String nom, String prenom, String telephone, Date dateNaissance, String lieuNaissance) {
        super(id, nom, prenom, telephone);
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
    }
    
    
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }
}
