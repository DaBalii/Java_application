/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package alex.services.implementations;

import alex.entities.Client;

/**
 *
 * @author USER
 */
public interface ClientServiceInterface {
    
    public void ajouterClient(Client client);
    
    public void modifierClient(Client client);
    
    public void supprimerClient(int id);
    
    public Client trouverClientParId(int id);
    
}
