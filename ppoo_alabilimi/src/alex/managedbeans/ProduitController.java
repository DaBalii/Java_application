/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.managedbeans;

import alex.entities.Produit;
import alex.services.ProduitService;

import java.util.List;


/**
 *
 * @author USER
 */
public class ProduitController {
    private ProduitService produitService;

    public ProduitController() {
        produitService = new ProduitService();
    }

    public void ajouterProduit(String libelle, String actif) {
        Produit produit = new Produit(0, libelle, actif); 
        produitService.ajouterProduit(produit);
    }

    public void modifierProduit(int id, String libelle, String actif) {
        Produit produit = new Produit(id, libelle, actif);
        produitService.modifierProduit(produit);
    }

    public void supprimerProduit(int id) {
        produitService.supprimerProduit(id);
    }

    public Produit trouverProduitParId(int id) {
        return produitService.trouverProduitParId(id);
    }

    public List<Produit> listerProduits() {
        return produitService.listerProduits();
    }
}
