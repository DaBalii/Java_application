/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.managedbeans;

import alex.entities.Client;
import alex.entities.ClientParticulier;
import alex.services.ClientService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class ClientController {
    private ClientService clientService;

    public ClientController() {
        clientService = new ClientService();
    }

    public void ajouterClientStandard(String nom, String prenom, String tel) {
        Client client = new Client(0, nom, prenom, tel); 
        clientService.ajouterClient(client);
    }

    public void ajouterClientParticulier(Integer id, String nom, String prenom, String telephone, Date dateNaissance, String lieuNaissance) {
        ClientParticulier clientParticulier = new ClientParticulier(id, nom, prenom, telephone, dateNaissance, lieuNaissance); 
        clientService.ajouterClient(clientParticulier);
    }

    public void modifierClient(int id, String nom, String prenom, String tel) {
        Client client = new Client(id, nom, prenom, tel);
        clientService.modifierClient(client);
    }

    public void supprimerClient(int id) {
        clientService.supprimerClient(id);
    }

    public Client trouverClientParId(int id) {
        return clientService.trouverClientParId(id);
    }

    public List<Client> listerClients() {
        return clientService.listerClients();
    }

    
}
