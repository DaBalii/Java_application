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
public class Souscription {
    private Integer id;
    private Integer idClient;
    private Integer idProduit;
    private String actif;
    private Date dateHeureSous;
    
    public Souscription(Integer id,Integer idClient,Integer idProduit,String actif,Date dateHeureSous){
        this.actif=actif;
        this.dateHeureSous=dateHeureSous;
        this.id=id;
        this.idClient=idClient;
        this.idProduit=idProduit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getActif() {
        return actif;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }

    public Date getDateHeureSous() {
        return dateHeureSous;
    }

    public void setDateHeureSous(Date dateHeureSous) {
        this.dateHeureSous = dateHeureSous;
    }
    
    
    
}
