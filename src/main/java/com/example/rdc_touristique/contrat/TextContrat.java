package com.example.rdc_touristique.contrat;


import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;

import java.time.LocalDate;

public class TextContrat {

    private final Personne bailleur;
    private final Personne preneur;
    private final Bien bienReservee;
    private final LocalDate dateArrivee;
    private final LocalDate dateDepart;

    public TextContrat(Personne bailleur, Personne preneur, Bien bienReservee, LocalDate dateArrivee, LocalDate dateDepart) {
        this.bailleur = bailleur;
        this.preneur = preneur;
        this.bienReservee = bienReservee;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
    }

    public String EntreBailler(){
        return this.bailleur.getNom() +
                " " + this.bailleur.getPrenom() +
                " nee le " + this.bailleur.getDdn() +
                ", domiciliee a ladresse suivante: " +
                this.bailleur.getAdresse().getNumHabitation() + " " +
                this.bailleur.getAdresse().getNomRue() + ", " +
                this.bailleur.getAdresse().getCodePostal() + " " +
                this.bailleur.getAdresse().getPays().getNomFrFr();
    }

    public String EntrePreneur(){
        return this.preneur.getNom() +
                " " + this.preneur.getPrenom() +
                " nee le " + this.preneur.getDdn() +
                ", domiciliee a l adresse suivante : " +
                this.preneur.getAdresse().getNumHabitation() + " " +
                this.preneur.getAdresse().getNomRue() + ", " +
                this.preneur.getAdresse().getCodePostal() + " " +
                this.preneur.getAdresse().getPays().getNomFrFr();
    }

    public String objet(){
        return "Le Bailleur met en location au Preneur le bien suivant:" +
                "\n" +
                this.bienReservee.getType().getNom() + " a l adresse : " +
                this.bienReservee.getCoordonnee().getNum() + " " + this.bienReservee.getCoordonnee().getRue() + ", dans la ville de " +
                this.bienReservee.getCoordonnee().getVille().getNomVille() + " en " + this.bienReservee.getCoordonnee().getVille().getProvince().getNomprovince() + "." +
                "\n" +
                this.bienReservee.getType().getNom() + " est concu pour heberger  " + this.bienReservee.getNpmax() + " personnes maximum." +
                "\n" +
                "A charge pour le Preneur de payer le loyer, d apporter les soins d un bon pere de famille au bien dans le meme etat a la fin du contrat.";
    }

    public String etatLieu(){
        return "Il sera dresse au debut de la location, entre les deux parties, un etat des lieux detaille a frais communs, " +
                "qui sera annexe au present contrat." +
                "L etat des lieux de fin de bail sera effectue a frais communs apres la restitution du bien au Bailleur.";
    }

    public String loyer(){
        return "Le Bail est consenti en contrepartie d un loyer de " + this.bienReservee.getPrix() + " euros" +
                " par nuit, paye en une fois." +
                "Le paiement du loyer se fait a la signature du bail par le Preneur sur le site MOBEMBO.cd." +
                "Le loyer est paye, sauf nouvelles instructions du Bailleur, sur le compte en banque suivant:" +
                " " + this.bailleur.getInfoBancaires().getNumCompte();
    }

    public String loyerMobembo(){
        return "Pour chaque reservation faite sur l application MOBEMBO.cd  pour le bien cite en objet, " +
                "le Preneur sera credite de ((" + this.bienReservee.getPrix() + " x la duree (en nuit) ) - 10%) par le Bailleur." +
                " Le loyer est paye, sauf nouvelles instructions du Bailleur, sur le compte en banque suivant:" +
                " " + this.preneur.getInfoBancaires().getNumCompte() + ".";
    }

    public String dureeClient(){
        return "Le Bail commence le " + this.dateArrivee + " a partir de 12 h (am) et est conclu pour une duree qui s etend jusqu au " + this.dateDepart + " a 10 h (am) inclus." +
                "Le Bail peut etre resilie moyennant la somme totale d'une nuit (" + this.bienReservee.getPrix() + " /euro)";
    }

    public String dureeMobembo(){
        return "Le Bail commence le " + this.dateArrivee + " a partir de 12 h (am) et est conclu pour une duree qui s etend jusqu au " + this.dateDepart + " a 10 h (am) inclus." +
                "Le Bail peut etre resilie moyennant la somme totale d une nuit (" + this.bienReservee.getPrix() + " /euro)";
    }

    public String dARDL(){
        return "Le present contrat est regi par le droit belge." +
                "Tout different relatif au present contrat sera soumis aux tribunaux de l arrondissement" +
                " judiciaire du domicile du Bailleur." +
                "\n" +
                "Fait sur le site MOBEMBO.cd, le " + LocalDate.now() +
                ", a la disposition des deux parties sur MOBEMBO.cd";
    }
}
