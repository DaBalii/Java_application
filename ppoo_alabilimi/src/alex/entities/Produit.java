/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.entities;

/**
 *
 * @author USER
 */
public class Produit {
    private Integer id;
    private String libelle;
    private String actif;
    
    public Produit(Integer id,String libelle,String actif){
        this.id=id;
        this.actif=actif;
        this.libelle=libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getActif() {
        return actif;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }
    
    
}
