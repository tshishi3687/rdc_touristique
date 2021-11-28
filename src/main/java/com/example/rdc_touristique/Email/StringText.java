package com.example.rdc_touristique.Email;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StringText {

        // création
    private static final String sujetCrea = "Confirmer votre compte";
    private static final String sujetEnvoisDemande = "Confirmer d'envois de la demanande n°";
    private static final String sujetDemanderecu = "Nouvelle demande reçu";

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
                + nom + " votre demande a été envoyé avec succès :)- <br><br>" +
                "La demande portant la reference nkdir:" + num + " a bien été transférer. <br><br>" +
                "Détail du bien : <br>" +
                "Province : " + province + "<br>" +
                "Vielle : " + ville + "<br>" +
                "Type de bien : " + typeBien + "<br>" +
                "Adresse : " + adressBien + "<br>" +
                "Date d'arrivé : " + dateArrive + "<br>" +
                "Date de départ : " + dateFin + "<br>" +
                "Etat de la demande : " + etatDemande + "<br>" +
                "Il vous est possible de suivre l'état de toutes vos demandes sur MOBEMBO.cd" +
                "<br><br>" +
                "A bientôt.<br><br><br>" +
                "Tshibangu Cédrick<br>" +
                "Développeur de Mobembo";
    }

    public String notificationDEmande(String province, String ville, String typeBien, String adressBien, String nom, int num, String etatDemande, LocalDate dateArrive, LocalDate dateFin, String faitPart){
        return ""
                + nom + " l'une de vos bien a tapé dans l'oeil :)- <br><br>" +
                "La demande portant la reference nkdir_" + num + " fait l'objet d'une demande. <br><br>" +
                "Détail du bien : <br>" +
                "Fait part : " + faitPart + "<br>" +
                "Province : " + province + "<br>" +
                "Vielle : " + ville + "<br>" +
                "Type de bien : " + typeBien + "<br>" +
                "Adresse : " + adressBien + "<br>" +
                "Date d'arrivé : " + dateArrive + "<br>" +
                "Date de départ : " + dateFin + "<br>" +
                "Etat de la demande : " + etatDemande + "<br>" +
                "Il vous est possible de suivre l'état de toutes vos demandes reçu sur MOBEMBO.cd" +
                "<br><br>" +
                "A bientôt.<br><br><br>" +
                "Tshibangu Cédrick<br>" +
                "Développeur de Mobembo";
    }
}
