/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.services;

import alex.entities.Souscription;
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
public class SouscriptionService {
    private Connection connexion;

    public SouscriptionService() {
        connexion = Connexion.getConnection();
    }

    public void ajouterSouscription(Souscription souscription) {
        try {
            // Vérification de l'existence de la souscription
            if (souscriptionExisteDeja(souscription)) {
                System.out.println("La souscription deja effectuee !");
                return; 
            }

            String sql = "INSERT INTO souscription(dateHeureSous, actif, idClient, idProduit) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connexion.prepareStatement(sql)) {
                ps.setDate(1, new java.sql.Date(souscription.getDateHeureSous().getTime()));
                ps.setString(2, souscription.getActif());
                ps.setInt(3, souscription.getIdClient());
                ps.setInt(4, souscription.getIdProduit());
                ps.executeUpdate();
                System.out.println("Souscription effectuee avec succes !");
            }
        } catch (NullPointerException e) {
            System.err.println("Erreur : la date de la souscription est null !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la souscription : " + e.getMessage());
        }
    }

    private boolean souscriptionExisteDeja(Souscription souscription) throws SQLException {
        String sql = "SELECT COUNT(*) FROM souscription WHERE dateHeureSous = ? AND actif = ? AND idClient = ? AND idProduit = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(souscription.getDateHeureSous().getTime()));
            ps.setString(2, souscription.getActif());
            ps.setInt(3, souscription.getIdClient());
            ps.setInt(4, souscription.getIdProduit());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; 
                }
            }
        }
        return false; 
    }


    public void modifierSouscription(Souscription souscription) {
        String sql = "UPDATE souscription SET dateHeureSous = ?, actif = ?, idClient = ?, idProduit = ? WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(souscription.getDateHeureSous().getTime()));
            ps.setString(2, souscription.getActif());
            ps.setInt(3, souscription.getIdClient());
            ps.setInt(4, souscription.getIdProduit());
            ps.setInt(5, souscription.getId());
            ps.executeUpdate();
            System.out.println("Souscription modifiee avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la souscription : " + e.getMessage());
        }
    }

    public void supprimerSouscription(int id) {
        String sql = "DELETE FROM souscription WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Souscription supprimee avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la souscription : " + e.getMessage());
        }
    }

    public Souscription trouverSouscriptionParId(int id) {
    String sql = "SELECT * FROM souscription WHERE id = ?";
    try (PreparedStatement ps = connexion.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Souscription(
                        rs.getInt("id"),
                        rs.getInt("idClient"),
                        rs.getInt("idProduit"),
                        rs.getString("actif"),
                        rs.getDate("dateHeureSous") // Utilisation directe de rs.getDate("dateHeureSous")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la recherche de la souscription : " + e.getMessage());
    }
    return null;
}


    public List<Souscription> listerSouscriptions() {
    List<Souscription> souscriptions = new ArrayList<>();
    String sql = "SELECT * FROM souscription";
    try (PreparedStatement ps = connexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            souscriptions.add(new Souscription(
                    rs.getInt("id"),
                    rs.getInt("idClient"),
                    rs.getInt("idProduit"),
                    rs.getString("actif"),
                    rs.getDate("dateHeureSous")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la recuperation de la liste des souscriptions : " + e.getMessage());
    }
    return souscriptions;
}
}
