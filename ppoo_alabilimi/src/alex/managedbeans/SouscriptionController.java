/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.managedbeans;


import alex.entities.Souscription;
import alex.services.SouscriptionService;
import java.util.Date;

import java.util.List;
/**
 *
 * @author USER
 */
public class SouscriptionController {
    private SouscriptionService souscriptionService;

    public SouscriptionController() {
        souscriptionService = new SouscriptionService();
    }

    public void ajouterSouscription(int idClient, int idProduit, String actif) {
        try {
            Souscription souscription = new Souscription(null, idClient, idProduit, actif, new Date());
            souscriptionService.ajouterSouscription(souscription);
        } catch (NullPointerException e) {
            System.err.println("Erreur : la date de la souscription est null !");
        }
    }

    public void modifierSouscription(int id, int idClient, int idProduit, String actif) {
        Souscription souscription = new Souscription(id, idClient, idProduit, actif, null);
        souscriptionService.modifierSouscription(souscription);
    }

    public void supprimerSouscription(int id) {
        souscriptionService.supprimerSouscription(id);
    }

    public Souscription trouverSouscriptionParId(int id) {
        return souscriptionService.trouverSouscriptionParId(id);
    }

    public List<Souscription> listerSouscriptions() {
        return souscriptionService.listerSouscriptions();
    }

    
}
