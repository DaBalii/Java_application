/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.services;


import alex.entities.Produit;
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

public class ProduitService {
    private Connection connexion;

    public ProduitService() {
        connexion = Connexion.getConnection();
    }

    public void ajouterProduit(Produit produit) {
    if (produitExiste(produit.getLibelle())) {
        System.out.println("Le produit avec le libelle '" + produit.getLibelle() + "' existe deja !");
        return; 
    }

    String sql = "INSERT INTO produit(libelle, actif) VALUES (?, ?)";
    try (PreparedStatement ps = connexion.prepareStatement(sql)) {
        ps.setString(1, produit.getLibelle());
        ps.setString(2, produit.getActif());
        ps.executeUpdate();
        System.out.println("Produit ajouté avec succès !");
    } catch (SQLException e) {
        System.err.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }


    private boolean produitExiste(String libelle) {
        String sql = "SELECT COUNT(*) FROM produit WHERE libelle = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, libelle);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; 
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la verification de l'existence du produit : " + e.getMessage());
        }
        return false; 
    }


    public void modifierProduit(Produit produit) {
        String sql = "UPDATE produit SET libelle = ?, actif = ? WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, produit.getLibelle());
            ps.setString(2, produit.getActif());
            ps.setInt(3, produit.getId());
            ps.executeUpdate();
            System.out.println("Produit modifie avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du produit : " + e.getMessage());
        }
    }

    public void supprimerProduit(int id) {
        String sql = "DELETE FROM produit WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Produit supprime avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du produit : " + e.getMessage());
        }
    }

    public Produit trouverProduitParId(int id) {
        String sql = "SELECT * FROM produit WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produit(
                            rs.getInt("id"),
                            rs.getString("libelle"),
                            rs.getString("actif")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du produit : " + e.getMessage());
        }
        return null;
    }

    public List<Produit> listerProduits() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try (PreparedStatement ps = connexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                produits.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getString("actif")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recuperation de la liste des produits : " + e.getMessage());
        }
        return produits;
    }
}