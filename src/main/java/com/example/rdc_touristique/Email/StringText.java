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
    private static final String sujetEnvoisDemande = "Confirmation de reservation";
    private static final String sujetEnvoisConfMisEnLigne = "Confirmation de mise en ligne.";
    private static final String sujetDemanderecu = "Nouvelle reservation recue";
    private static final String sujetmodifMDP = "Demande de changement de mot de passe";
    private static final String sujetBienNonConforme = "Bien non conforme.";
    private static final String sujetConfirmationReservation = "Demande de Reservation";
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
                "Madame, Monsieur " + entity.getPreneur().getNom() + ",</p>" +
                "<br>" +
                "<br>" +
                "<h3>" +
                "Un processus de cessation de contrat pour le bien nomme ci dessous a ete entamer." +
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
                "Cette action implique une redevence de " + entity.getIdBien().getPrix() + "euro au profit de Mobembo.cd, comme stipule dans le contrat." +
                "</p>" +
                "<p>" +
                "Si vous etes conscient(e) du processus et si vous voulez continue" +
                ", voici votre code de validation : \"" + code + "\"." +
                "</p>" +
                "<br>" +
                "<br>" +
                "<p>Si ce n est pas vous...<br> Conseil:</p>" +
                "<ul>" +
                "<li>" + "Modifier le mode passe de votre Mobembo.cd." +"</li>" +
                "<li>" + "Modifier le mode passe de votre boite E-Mail associer au compte Mobembo.cd." +"</li>" +
                "<li>" + "Contacter le service IT sur : "+ entity.getBailleur().getContactUser().getEmail() +"</li>" +
                "</ul>" +
                "<br>" +
                "<br>" +
                "A bientot.<br><br><br>" +
                "Tshibangu Cedrick<br>" +
                "Developpeur de Mobembo.cd";
    }

    public String creationMessageInscription(String codeCree, String nom){
        return "" +
                "Felicitation " + nom + " !!!!!! <br><br>" +
                "Votre compte a ete cree avec succes :).<br>" +
                "Neanmoins, pour pouvoir profiter de nos services, veuillez retaper le code: \"" + codeCree + "\"<br>" +
                "dans l application Mobembo.cd." +
                "<br><br>" +
                "A bientot.<br><br><br>" +
                "Tshibangu Cedrick<br>" +
                "Developpeur de Mobembo.cd";
    }

    public String confimationEnvoisDemande(
            String province, String ville, String typeBien,
            String adressBien, String nom, int prix,
            LocalDate dateArrive, LocalDate dateFin, DetailsDTO detailsDTO){
        return "Madame, Monsieur "
                + nom + ".<br>votre reservation a ete enregistree avec succes :)- <br><br>" +
                "Details du bien : <br>" +
                "Province : " + province + "<br>" +
                "Ville : " + ville + "<br>" +
                "Type de Bien : " + typeBien + "<br>" +
                "Adresse : " + adressBien + "<br>" +
                "Date d arrivee : " + dateArrive + "<br>" +
                "Date de depart : " + dateFin + "<br>" +
                "<br>" +
                "<h3>Information de paiement</h3>" +
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
                "<th>E mail</th>" +
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
                "A bientot.<br><br><br>" +
                "Tshibangu Cedrick<br>" +
                "Developpeur de Mobembo.cd";
    }

    public String notificationReservation(String province, String ville, String typeBien, String adressBien, String nom, LocalDate dateArrive, LocalDate dateFin){
        return "Madame, Monsieur "
                + nom + "<br>L'un de vos bien a du succes :)- <br><br>" +
                "Details du bien : <br>"  +
                "Province : " + province + "<br>" +
                "Ville : " + ville + "<br>" +
                "Type de bien : " + typeBien + "<br>" +
                "Adresse : " + adressBien + "<br>" +
                "Date d arrive : " + dateArrive + "<br>" +
                "Date de depart : " + dateFin + "<br>" +
                "<br><br>" +
                "A bientot.<br><br><br>" +
                "Tshibangu Cedrick<br>" +
                "Developpeur de Mobembo.cd";
    }

    public String confirmationMisEnLigne(Personne personne, Bien bien,int jours, String code){
        return "Madame, Monsieur " + personne.getNom() + "," +
                "<br>" +
                "<br>" +
                "Nous constatons que vous etes sur le point de mettre votre: <br>" +
                "<ul>" +
                    "<li>" +
                        bien.getType().getNom() +
                    "</li>" +
                    "<li>" +
                        "A " + bien.getPrix() + "/ euro" +
                    "</li>" +
                    "<li>" +
                        "Adresse : " + bien.getCoordonnee().getNum() + " " + bien.getCoordonnee().getRue() + ", " + bien.getCoordonnee().getVille().getNomVille() + " " + bien.getCoordonnee().getVille().getProvince().getNomprovince() +
                    "</li>" +
                "</ul> " +
                "" +
                " En ligne pour une duree de " + jours + " jours." +
                "<br>" +
                "<br>" +
                "Si vous etes d accord avec les informations indiquees ci avant, voici votre code : " +
                "\"" + code + "\"<br>" +
                "En mettant ce code sur MOBEMBO.cd, vous acceptez les termes et conditions lies a la mise en ligne d'un bien sur MOBEMBO.cd." +
                "<br>" +
                "<br>" +
                "Bien a vous," +
                "<br>" +
                "<br>" +
                "Tshibangu Cedrick" +
                "<br>" +
                "Createur et developpeur de MOBEMBO.cd.";
    }

    public String confirmationReservation(Personne personne, Bien bien,long jours, String code){
        return "Madame, Monsieur " + personne.getNom() + "," +
                "<br>" +
                "<br>" +
                "Nous constatons que vous etes sur le point de faire la reservation du bien suivant: <br>" +
                "<ul>" +
                    "<li>" +
                        bien.getType().getNom() +
                    "</li>" +
                    "<li>" +
                        "A " + bien.getPrix() + "/ euro" +
                    "</li>" +
                    "<li>" +
                        "Adresse : " + bien.getCoordonnee().getNum() + " " + bien.getCoordonnee().getRue() + ", " + bien.getCoordonnee().getVille().getNomVille() + " " + bien.getCoordonnee().getVille().getProvince().getNomprovince() +
                    "</li>" +
                "</ul> " +
                "" +
                " Pour une duree de " + jours + " jours." +
                "<br>" +
                "<br>" +
                "Si vous etes d accord avec les informations indiquees ci avant, voici votre code : " +
                "\"" + code + "\"<br>" +
                "En mettant ce code sur MOBEMBO.cd, vous acceptez les termes et conditions lies a la mise en ligne d'un bien sur MOBEMBO.cd." +
                "<br>" +
                "<br>" +
                "Bien a vous," +
                "<br>" +
                "<br>" +
                "Tshibangu Cedrick" +
                "<br>" +
                "Createur et developpeur de MOBEMBO.cd.";
    }

    public String demandeModifMDP( Personne entity, String code){
        return "Madame, Monsieur, " + entity.getNom() + " " + entity.getPrenom() +
                "<br>" +
                "Nous venons de recevoir une demande de modification du mot de passe lie a votre compte. <br>" +
                "Si ce n'est pas vous, veuillez ignorer ce message." +
                "<br>" +
                "<br>" +
                "Sinon, votre code de verification est \"" + code + "\" " +
                "<br>" +
                "<br>" +
                "Bien a vous" +
                "<br>" +
                "<br>" +
                "Tshibangu Cedrick" +
                "<br>" +
                "Createur et developpeur de MOBEMBO.cd.";
    }

    public String bienNonConforme(ContratMisEnLigne contrat){
        return "Madame, Monsieur, " + contrat.getPreneur().getNom() + " " + contrat.getPreneur().getPrenom() +
                "<br>" +
                "<h1>Le contrat pour le bien portant le numero NKDIR_" + contrat.getIdBien() + " a ete annulee par nos soins.</h1>" +
                "<br>" +
                "<h3>Cause :</h3>" +
                "<ul><li>Le bien decrit ci dessous ne repond pas a la qualite attendue sur Mobembo.cd.</li></ul>" +
                "<ul>" +
                "<br>" +
                "Detail du bien :" +
                "<li> Type : " +
                contrat.getIdBien().getType().getNom() +
                "</li>" +
                "<li> Prix : " +
                "A " + contrat.getIdBien().getPrix() + "/ euro" +
                "</li>" +
                "<li> Adresse : " +
                "" + contrat.getIdBien().getCoordonnee().getNum() + " " + contrat.getIdBien().getCoordonnee().getRue() + ", " + contrat.getIdBien().getCoordonnee().getVille().getNomVille() + " " + contrat.getIdBien().getCoordonnee().getVille().getProvince().getNomprovince() +
                "</li>" +
                "</ul> " +
                "L annulation de ce contrat prend fin des aujourd hui " + LocalDate.now() + ". " +
                "Et a pour effet le retrait du bien, cite ci dessus, de la vue de tous." +
                "<br>" +
                "<br>" +
                "Bien a vous" +
                "<br>" +
                "<br>" +
                "Tshibangu Cedrick" +
                "<br>" +
                "Createur et developpeur de MOBEMBO.cd.";
    }
}
