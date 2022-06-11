package com.example.rdc_touristique.Email;

import com.example.rdc_touristique.business.dto.DetailsDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.ContratMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Personne;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StringText {

        // création
    private static final String sujetCrea = "Confirmer votre compte";
    private static final String sujetEnvoisDemande = "Confirmation de réservation";
    private static final String sujetEnvoisConfMisEnLigne = "Confirmation de mise en ligne.";
    private static final String sujetDemanderecu = "Nouvelle réservation reçue";
    private static final String sujetmodifMDP = "Demande de changement de mot-de-passe";
    private static final String sujetBienNonConforme = "Bien non conforme.";
    private static final String sujetConfirmationReservation = "Demande de Réservation";
    private static final String sujetStopContrat = "***ATTENTION: TENTATIVE DE CESSATION DE CONTRAT";


    public String getSujetEnvoisConfMisEnLigne(){ return sujetEnvoisConfMisEnLigne; }
    public String getSujetCrea(){
        return sujetCrea;
    }
    public String getSujetEnvoisDemande(){
        return sujetEnvoisDemande;
    }
    public String sujetDemanderecu(){
        return sujetDemanderecu;
    }
    public String getSujetmodifMDP(){
        return sujetmodifMDP;
    }
    public String getSujetBienNonConforme(){return sujetBienNonConforme;}
    public String getSujetConfirmationReservation(){return sujetConfirmationReservation;}
    public String getSujetStopContrat(){return sujetStopContrat;}

    public String alertStopContrat(ContratMisEnLigne entity, String code){
        return "<p>" +
                "Bonjour Madame, Monsieur " + entity.getPreneur().getNom() + ",</p>" +
                "<br>" +
                "<br>" +
                "<h3>" +
                "Un processus de cessation de contrat pour le bien nommé ci-dessous a été entamer." +
                "</h3>" +
                "<tableau>" +
                "<tr>" +
                "<th>" + "Type de Bien" + "</th>" +
                "<th>" + "Adresse" + "</th>" +
                "<th>" + "Prix" + "</th>" +
                "</tr>" +
                "<tr>" +
                "<td>" + entity.getIdBien().getType().getNom() + "</td>" +
                "<td>" + entity.getIdBien().getCoordonnee().getNum() + " "+ entity.getIdBien().getCoordonnee().getRue()+ ", " + entity.getIdBien().getCoordonnee().getVille().getNomVille() + " / " + entity.getIdBien().getCoordonnee().getVille().getProvince().getNomprovince() + "</td>" +
                "<td>" + entity.getIdBien().getPrix() + "</td>" +
                "</tr>" +
                "</tableau>" +
                "<br>" +
                "<p>" +
                "Cette action implique une redevence de " + entity.getIdBien().getPrix() + "€ au profit de Mobembo.cd, comme stipulé dans le contrat." +
                "</p>" +
                "<p>" +
                "Si vous êtes conscient(e) du processus et si vous voulez continué" +
                ", voici votre code de validation : \"" + code + "\"." +
                "</p>" +
                "<br>" +
                "<br>" +
                "<p>Ce n'est pas vous qui a entamée ce processus?<br> Conseil:</p>" +
                "<ul>" +
                "<li>" + "Modifier le mode passe de votre Bobembo.cd." +"</li>" +
                "<li>" + "Modifier le mode passe de votre voite E-Mail associer au compte Mobembo.cd." +"</li>" +
                "<li>" + "Contacter le service IT sur : "+ entity.getBailleur().getContactUser().getEmail() +"</li>" +
                "</ul>" +
                "<br>" +
                "<br>" +
                "A bientôt.<br><br><br>" +
                "Tshibangu Cédrick<br>" +
                "Développeur de Mobembo";
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

    public String confimationEnvoisDemande(
            String province, String ville, String typeBien,
            String adressBien, String nom, int prix,
            LocalDate dateArrive, LocalDate dateFin, DetailsDTO detailsDTO){
        return "Bonjour Madame, Monsieur "
                + nom + ".<br>votre réservation a été enregistrée avec succès :)- <br><br>" +
                "Détails du Bien : <br>" +
                "Province : " + province + "<br>" +
                "Ville : " + ville + "<br>" +
                "Type de Bien : " + typeBien + "<br>" +
                "Adresse : " + adressBien + "<br>" +
                "Date d'arrivée : " + dateArrive + "<br>" +
                "Date de départ : " + dateFin + "<br>" +
                "<br>" +
                "<h3>Information de payement</h3>" +
                "<table>" +
                "<thead>" +
                "<tr>" +
                "<th>Id</th>" +
                "<th>Payer ID</th>" +
                "<th>Create Time</th>" +
                "<th>Update Time</th>" +
                "<th>Intent</th>" +
                "<th>status</th>" +
                "<th>Prix</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "<th>" + detailsDTO.getId() + "</th>" +
                "<th>" + detailsDTO.getPayerId() + "</th>" +
                "<th>" + detailsDTO.getCreateTime() + "</th>" +
                "<th>" + detailsDTO.getUpdateTime() + "</th>" +
                "<th>" + detailsDTO.getIntent() + "</th>" +
                "<th>" + detailsDTO.getStatus() + "</th>" +
                "<th>" + prix + "</th>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "<table>" +
                "<thead>" +
                "<tr>" +
                "<th>Nom</th>" +
                "<th>Prenom</th>" +
                "<th>E-mail</th>" +
                "<th>Adresse Line</th>" +
                "<th>Admin Area 1</th>" +
                "<th>Admin Area 2</th>" +
                "<th>Country Code</th>" +
                "<th>Postal Code</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "<th>" + detailsDTO.getNom() + "<th>" +
                "<th>" + detailsDTO.getPrenom() + "<th>" +
                "<th>" + detailsDTO.getEmail() + "<th>" +
                "<th>" + detailsDTO.getAdressLine1() + "<th>" +
                "<th>" + detailsDTO.getAdminArea1() + "<th>" +
                "<th>" + detailsDTO.getAdminArea2() + "<th>" +
                "<th>" + detailsDTO.getCountryCode() + "<th>" +
                "<th>" + detailsDTO.getPostalcode() + "<th>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "<br>" +
                "<br>" +
                "Il vous est possible de lire le contrat de la reservation sur MOBEMBO.cd" +
                "<br><br>" +
                "A bientôt.<br><br><br>" +
                "Tshibangu Cédrick<br>" +
                "Développeur de Mobembo";
    }

    public String notificationReservation(String province, String ville, String typeBien, String adressBien, String nom, LocalDate dateArrive, LocalDate dateFin){
        return "Bonjour Madame, Monsieur "
                + nom + "<br>l'un de vos Bien a tapé dans l'oeil :)- <br><br>" +
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

    public String confirmationMisEnLigne(Personne personne, Bien bien,int jours, String code){
        return "Bonjour Madame, Monsieur " + personne.getNom() + "," +
                "<br>" +
                "<br>" +
                "Nous constatons que vous êtes sur le point de mettre votre: <br>" +
                "<ul>" +
                    "<li>" +
                        bien.getType().getNom() +
                    "</li>" +
                    "<li>" +
                        "A " + bien.getPrix() + "/ €" +
                    "</li>" +
                    "<li>" +
                        "Adresse : " + bien.getCoordonnee().getNum() + " " + bien.getCoordonnee().getRue() + ", " + bien.getCoordonnee().getVille().getNomVille() + " " + bien.getCoordonnee().getVille().getProvince().getNomprovince() +
                    "</li>" +
                "</ul> " +
                "" +
                " En ligne pour une durée de " + jours + " jours." +
                "<br>" +
                "<br>" +
                "Si vous êtes d'accord avec les informations indiquées ci-avant, voici votre code : " +
                "\"" + code + "\"<br>" +
                "En mettant ce code sur MOBEMBO.cd, vous acceptez les termes et conditions liés à la mise en ligne d'un Bien sur MOBEMBO.cd." +
                "<br>" +
                "<br>" +
                "Bien à vous," +
                "<br>" +
                "<br>" +
                "Tshibangu Cédrcik" +
                "<br>" +
                "Créateur et developpeur de MOBEMBO.cd.";
    }

    public String confirmationReservation(Personne personne, Bien bien,long jours, String code){
        return "Bonjour Madame, Monsieur " + personne.getNom() + "," +
                "<br>" +
                "<br>" +
                "Nous constatons que vous êtes sur le point de faire la réservation du Bien suivant: <br>" +
                "<ul>" +
                    "<li>" +
                        bien.getType().getNom() +
                    "</li>" +
                    "<li>" +
                        "A " + bien.getPrix() + "/ €" +
                    "</li>" +
                    "<li>" +
                        "Adresse : " + bien.getCoordonnee().getNum() + " " + bien.getCoordonnee().getRue() + ", " + bien.getCoordonnee().getVille().getNomVille() + " " + bien.getCoordonnee().getVille().getProvince().getNomprovince() +
                    "</li>" +
                "</ul> " +
                "" +
                " Pour une durée de " + jours + " jours." +
                "<br>" +
                "<br>" +
                "Si vous êtes d'accord avec les informations indiquées ci-avant, voici votre code : " +
                "\"" + code + "\"<br>" +
                "En mettant ce code sur MOBEMBO.cd, vous acceptez les termes et conditions liés à la mise en ligne d'un Bien sur MOBEMBO.cd." +
                "<br>" +
                "<br>" +
                "Bien à vous," +
                "<br>" +
                "<br>" +
                "Tshibangu Cédrcik" +
                "<br>" +
                "Créateur et developpeur de MOBEMBO.cd.";
    }

    public String demandeModifMDP( Personne entity, String code){
        return "Bonjour " + entity.getNom() + " " + entity.getPrenom() +
                "<br>" +
                "Nous venons de recevoir une demande de modification du mot de passe lié à votre compte. <br>" +
                "Si ce n'est pas vous, veuillez ignorer ce message." +
                "<br>" +
                "<br>" +
                "Sinon, votre code de vérification est " + code +
                "<br>" +
                "<br>" +
                "Bien à vous" +
                "<br>" +
                "<br>" +
                "Tshibangu Cédrcik" +
                "<br>" +
                "Créateur et developpeur de MOBEMBO.cd.";
    }

    public String bienNonConforme(ContratMisEnLigne contrat){
        return "Bonjour " + contrat.getPreneur().getNom() + " " + contrat.getPreneur().getPrenom() +
                "<br>" +
                "<h1>Le contrat pour le bien portant le numéro NKDIR_" + contrat.getIdBien() + " a été annunée par nos soin.</h1>" +
                "<br>" +
                "<h3>Cause :</h3>" +
                "<ul><li>Le Bien décrit ci-dessous de répond pas à la qualité attendu sur Mobembo.cd.</li></ul>" +
                "<ul>" +
                "<br>" +
                "Détail du bien :" +
                "<li> Type : " +
                contrat.getIdBien().getType().getNom() +
                "</li>" +
                "<li> Prix : " +
                "A " + contrat.getIdBien().getPrix() + "/ €" +
                "</li>" +
                "<li> Adresse : " +
                "Adresse : " + contrat.getIdBien().getCoordonnee().getNum() + " " + contrat.getIdBien().getCoordonnee().getRue() + ", " + contrat.getIdBien().getCoordonnee().getVille().getNomVille() + " " + contrat.getIdBien().getCoordonnee().getVille().getProvince().getNomprovince() +
                "</li>" +
                "</ul> " +
                "L'annulation de se contrat prend fin dès aujourd'hui " + LocalDate.now() + ". " +
                "Et a pour effet le retrait du Bien cité ci-deçu de la vue de tous." +
                "<br>" +
                "<br>" +
                "Bien à vous" +
                "<br>" +
                "<br>" +
                "Tshibangu Cédrcik" +
                "<br>" +
                "Créateur et developpeur de MOBEMBO.cd.";
    }
}
