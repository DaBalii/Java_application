/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alex.managedbeans;


import alex.entities.Sms;
import alex.services.SMSService;

import java.util.List;

/**
 *
 * @author USER
 */
public class SmsController {
    private SMSService smsService;

    public SmsController() {
        smsService = new SMSService();
    }

   public void ajouterSMS(Integer idClient, String libelle) {
    Sms sms = new Sms(null, idClient, libelle);
    smsService.ajouterSMS(sms);
    }


    public void modifierSMS(int id, int idClient, String libelle) {
        Sms sms = new Sms(id, idClient, libelle);
        smsService.modifierSMS(sms);
    }

    public void supprimerSMS(int id) {
        smsService.supprimerSMS(id);
    }

    public Sms trouverSMSParId(int id) {
        return smsService.trouverSMSParId(id);
    }

    public List<Sms> listerSMS() {
        return smsService.listerSMS();
    }

}
