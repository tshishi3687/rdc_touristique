package com.example.rdc_touristique.Email;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StringText {

        // création
    private static final String sujetCrea = "Confirmer votre compte";
    private static final String sujetEnvoisDemande = "Confirmation de réservation";
    private static final String sujetDemanderecu = "Nouvelle réservation reçue";

    public String getSujetCrea(){
        return sujetCrea;
    }

    public String getSujetEnvoisDemande(){
        return sujetEnvoisDemande;
    }

    public String getSujetDemanderecu(){
        return sujetDemanderecu;
    }

    public String creationMessageInscription(String codeCree, String nom){
        return "" +
                "Félicitation " + nom + " !!!!!! <br><br>" +
                "Votre compte à été créé avec succés :).<br>" +
                "Néanmoins, pour pouvoir profiter de nos services, veuillez retaper le code: \"" + codeCree + "\"<br>" +
                "dans l'application Mobembo.cd." +
                "<br><br>" +
                "A bientôt.<br><br><br>" +
                "Tshibangu Cédrick<br>" +
                "Développeur de Mobembo";
    }

    public String confimationEnvoisDemande(String province, String ville, String typeBien, String adressBien, String nom, int num, String etatDemande, LocalDate dateArrive, LocalDate dateFin){
        return ""
                + nom + " votre réservation a été enregistrée avec succès :)- <br><br>" +
                "La demande portant la référence nkdir:" + num + " a bien été transférée. <br><br>" +
                "Détails du Bien : <br>" +
                "Province : " + province + "<br>" +
                "Ville : " + ville + "<br>" +
                "Type de Bien : " + typeBien + "<br>" +
                "Adresse : " + adressBien + "<br>" +
                "Date d'arrivée : " + dateArrive + "<br>" +
                "Date de départ : " + dateFin + "<br>" +
                "Il vous est possible de lire le contrat de la reservation sur MOBEMBO.cd" +
                "<br><br>" +
                "A bientôt.<br><br><br>" +
                "Tshibangu Cédrick<br>" +
                "Développeur de Mobembo";
    }

    public String notificationDEmande(String province, String ville, String typeBien, String adressBien, String nom, int num, String etatDemande, LocalDate dateArrive, LocalDate dateFin, String faitPart){
        return ""
                + nom + " l'un de vos Bien a tapé dans l'oeil :)- <br><br>" +
                "Le Bien portant la référence nkdir_" + num + " fait l'objet d'une réservation. <br><br>" +
                "Détails du Bien : <br>"  +
                "Province : " + province + "<br>" +
                "Ville : " + ville + "<br>" +
                "Type de Bien : " + typeBien + "<br>" +
                "Adresse : " + adressBien + "<br>" +
                "Date d'arrivé : " + dateArrive + "<br>" +
                "Date de départ : " + dateFin + "<br>" +
                "<br><br>" +
                "A bientôt.<br><br><br>" +
                "Tshibangu Cédrick<br>" +
                "Développeur de Mobembo";
    }
}
