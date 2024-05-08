/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.entities;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Client implements Serializable{
    
    private Integer id;
    
    private String nom;
    
    private String prenom;
    
    private String telephone;
    
    
    public Client(Integer id,String nom,String prenom,String telephone){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.telephone=telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTel(String telephone) {
        this.telephone = telephone;
    }
    
    
    
}
