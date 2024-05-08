/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.services;

import alex.entities.Client;
import alex.entities.ClientParticulier;
import alex.utils.Connexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class ClientService {
    private Connection connexion;

    public ClientService() {
        connexion = Connexion.getConnection();
    }

    public void ajouterClient(Client client) {
    
    if (client instanceof ClientParticulier) {
            ajouterClientParticulier((ClientParticulier) client);
        } else {
            ajouterClientStandard(client);
        }
    }
    
    private void ajouterClientStandard(Client client) {
        if (clientExiste(client)) {
            System.out.println("Le client existe deja !");
            return; 
        }
        String sql = "INSERT INTO client(nom, prenom, telephone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getTelephone());
            ps.executeUpdate();
            System.out.println("Client ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du client standard : " + e.getMessage());
        }
    }

    private void ajouterClientParticulier(ClientParticulier clientParticulier) {
        
        String sql = "INSERT INTO clientparticulier(idClient, nom, prenom, telephone, dateNaissance, lieuNaissance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, clientParticulier.getId());
            ps.setString(2, clientParticulier.getNom());
            ps.setString(3, clientParticulier.getPrenom());
            ps.setString(4, clientParticulier.getTelephone());
            ps.setDate(5, new java.sql.Date(clientParticulier.getDateNaissance().getTime()));
            ps.setString(6, clientParticulier.getLieuNaissance());
            ps.executeUpdate();
            System.out.println("Client particulier ajoute avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du client particulier : " + e.getMessage());
        }
    }
    
    


    private boolean clientExiste(Client client) {
        String sql = "SELECT COUNT(*) FROM Client WHERE nom = ? AND prenom = ? AND telephone = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getTelephone());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'existence du client : " + e.getMessage());
        }
        return false; 
    }

    public void modifierClient(Client client) {
        String sql = "UPDATE client SET nom = ?, prenom = ?, telephone = ? WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getTelephone());
            ps.setInt(4, client.getId());
            ps.executeUpdate();
            System.out.println("Client "+client.getNom()+" "+client.getPrenom()+" modifie avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du client : " + e.getMessage());
        }
    }

    public void supprimerClient(int id) {
        if (!clientExiste(id)) {
        System.out.println("Le client avec l'ID " + id + " n'existe pas !");
        return; 
    }
        String sql = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Client id "+id+" supprime avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du client : " + e.getMessage());
        }
    }
    
    private boolean clientExiste(int id) {
    String sql = "SELECT COUNT(*) FROM client WHERE id = ?";
    try (PreparedStatement ps = connexion.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la vérification de l'existence du client : " + e.getMessage());
    }
    return false; 
    }

    public Client trouverClientParId(int id) {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("telephone")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du client : " + e.getMessage());
        }
        return null;
    }

    public List<Client> listerClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (PreparedStatement ps = connexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recuperation de la liste des clients : " + e.getMessage());
        }
        return clients;
    }
}
