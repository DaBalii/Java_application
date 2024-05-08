/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp_glsia_poo;

import alex.entities.Client;
import alex.entities.ClientParticulier;
import alex.entities.Sms;
import alex.entities.Souscription;
import alex.managedbeans.ClientController;
import alex.managedbeans.ProduitController;
import alex.managedbeans.SmsController;
import alex.managedbeans.SouscriptionController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class Tp_glsiA_poo {
    
    private static List<Client> list = new ArrayList<>();
    private static List<Sms> listSms = new ArrayList<>();
    private static List<Souscription> listSouscr = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ClientController c = new ClientController();
        
        ProduitController p = new ProduitController();
        SouscriptionController s = new SouscriptionController();
        SmsController Sm = new SmsController();
        System.out.println();
        
        // tous ce qui est Clients
        
        c.ajouterClientStandard("John", "Doe", "0123456789");
         System.out.println();
        
        
        /*list = c.listerClients();
        System.out.println("taille de list: " + list.size()+"\n");
        System.out.println("Liste des clients : ");
        for (Client client : list) {
            System.out.println();
            System.out.println("ID : " + client.getId());
            System.out.println("Nom : " + client.getNom());
            System.out.println("Prenom : " + client.getPrenom());
            System.out.println("Telephone : " + client.getTelephone());
            System.out.println(); 
        }


        c.modifierClient(4, "Jater", "Doie", "9876543230");
        System.out.println();
        
        Client clientTrouve = c.trouverClientParId(1);
        if (clientTrouve != null) {
        System.out.println("Client trouve : ");
        System.out.println(
                "ID : " + clientTrouve.getId()+" Nom : "
                + clientTrouve.getNom()+" Prenom : " 
                + clientTrouve.getPrenom()+" Telephone : " 
                + clientTrouve.getTelephone()+"\n");
        } else {
            System.out.println("Aucun client trouve avec l'ID : " + clientTrouve.getId());
            }
        c.supprimerClient(6);*/
        
        //pour les produit
        
        /*p.ajouterProduit("Sorgoh", "Epargne");
        p.ajouterProduit("riz", "Courant");
        p.supprimerProduit(4);*/
        
        //pour les souscriptions et Sms
        
        s.ajouterSouscription(4, 1, "Epargne");
        System.out.println();
        
        
        
        s.supprimerSouscription(3);
        System.out.println();
        
        Sm.ajouterSMS(2, "vous avew souscrit avec succes au actifs Courant !");
        System.out.println();
        
        listSouscr = s.listerSouscriptions();
        System.out.println("taille SMS  en attentes d envoi: " + listSouscr.size()+"\n");
        System.out.println("Liste des SMS  en attentes d envoi : ");
        for (Souscription scr : listSouscr) {
            System.out.println();
            System.out.println("ID : " + scr.getId());
            System.out.println("id client : " + scr.getIdClient());
            System.out.println("id produit : " + scr.getIdProduit());
            System.out.println("actifs : " + scr.getActif());
            System.out.println(); 
        }
        
        listSms = Sm.listerSMS();
        System.out.println("taille des SMS deja envoyes " + listSms.size()+"\n");
        System.out.println(" SMS deja envoyes : ");
        for (Sms ms : listSms) {
            System.out.println();
            System.out.println("ID : " + ms.getId());
            System.out.println("idClient: " + ms.getIdClient());
            System.out.println("libelle : " + ms.getLibelle());
            System.out.println(); 
        }
        
        Sm.supprimerSMS(2);
        System.out.println();
        
        
        String dateNaissanceStr = "2001-02-12";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateNaissance = sdf.parse(dateNaissanceStr);
            System.out.println("Date de naissance formatee : " + dateNaissance);
            System.out.println();
            c.ajouterClientParticulier(1,"Jordi", "Doenr", "0123456719", dateNaissance, "Lome");
        } catch (ParseException e) {
            System.err.println("Erreur de conversion de la date : " + e.getMessage());
        }
        
        
        
    }
    
}
