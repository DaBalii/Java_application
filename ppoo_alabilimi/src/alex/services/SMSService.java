/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.services;

import alex.entities.Sms;
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
public class SMSService {
    private Connection connexion;

    public SMSService() {
        connexion = Connexion.getConnection();
    }

    public void ajouterSMS(Sms sms) {
        if (sms == null || sms.getIdClient() == null || sms.getLibelle() == null || sms.getLibelle().isEmpty()) {
            System.err.println("Erreur : l'objet SMS ou son libelle est invalide !");
            return; 
        }
        if (sms.getLibelle().length() > 255) {
            System.err.println("Erreur : le libelle du SMS depasse la limite de caracteres !");
            return; 
        }

        String sqlCheckDoublon = "SELECT COUNT(*) FROM sms WHERE idClient = ? AND libelle = ?";
        try (PreparedStatement psCheckDoublon = connexion.prepareStatement(sqlCheckDoublon)) {
            psCheckDoublon.setInt(1, sms.getIdClient());
            psCheckDoublon.setString(2, sms.getLibelle());
            try (ResultSet rs = psCheckDoublon.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.err.println("Erreur : ce SMS existe deja dans la base de donnees !");
                    return;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la verification du doublon : " + e.getMessage());
            return; 
        }

        String sqlInsertSMS = "INSERT INTO sms(idClient, libelle) VALUES (?, ?)";
        try (PreparedStatement ps = connexion.prepareStatement(sqlInsertSMS)) {
            ps.setInt(1, sms.getIdClient());
            ps.setString(2, sms.getLibelle());
            ps.executeUpdate();
            System.out.println("SMS envye avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du SMS : " + e.getMessage());
        }
    }


    public void modifierSMS(Sms sms) {
        String sql = "UPDATE sms SET idClient = ?, libelle = ? WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, sms.getIdClient());
            ps.setString(2, sms.getLibelle());
            ps.setInt(3, sms.getId());
            ps.executeUpdate();
            System.out.println("SMS modifie avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du SMS : " + e.getMessage());
        }
    }

    public void supprimerSMS(int id) {
        String sql = "DELETE FROM sms WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("SMS supprime avec succes !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du SMS : " + e.getMessage());
        }
    }

    public Sms trouverSMSParId(int id) {
        String sql = "SELECT * FROM sms WHERE id = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Sms(
                            rs.getInt("id"),
                            rs.getInt("idClient"),
                            rs.getString("libelle")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du SMS : " + e.getMessage());
        }
        return null;
    }

    public List<Sms> listerSMS() {
        List<Sms> smsList = new ArrayList<>();
        String sql = "SELECT * FROM sms";
        try (PreparedStatement ps = connexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Sms sms = new Sms(
                        rs.getInt("id"),
                        rs.getInt("idClient"),
                        rs.getString("libelle")
                );
                smsList.add(sms);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la liste des SMS : " + e.getMessage());
        }
        return smsList;
    }
}
