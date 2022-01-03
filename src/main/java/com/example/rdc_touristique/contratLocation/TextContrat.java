package com.example.rdc_touristique.contratLocation;


import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.BienVuDTO;
import com.example.rdc_touristique.data_access.entity.Personne;

import java.time.LocalDate;

public class TextContrat {

    private final Personne bailleur;
    private final Personne preneur;
    private final BienVuDTO bienReservee;
    private final LocalDate dateArrivee;
    private final LocalDate dateDepart;

    public TextContrat(Personne bailleur, Personne preneur, BienVuDTO bienReservee, LocalDate dateArrivee, LocalDate dateDepart) {
        this.bailleur = bailleur;
        this.preneur = preneur;
        this.bienReservee = bienReservee;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
    }

    public String EntreBailler(){
        return this.bailleur.getNom() +
                " " + this.bailleur.getPrenom() +
                " né(e) le " + this.bailleur.getDdn() +
                ", domicilié(e) à l'adresse suivante: " +
                this.bailleur.getAdresse().getNumHabitation() + " " +
                this.bailleur.getAdresse().getNomRue() + ", " +
                this.bailleur.getAdresse().getCodePostal() + " " +
                this.bailleur.getAdresse().getPays().getNomFrFr();
    }

    public String EntrePreneur(){
        return this.preneur.getNom() +
                " " + this.preneur.getPrenom() +
                " né(e) le " + this.preneur.getDdn() +
                ", domicilié(e) à l'adresse suivante: " +
                this.preneur.getAdresse().getNumHabitation() + " " +
                this.preneur.getAdresse().getNomRue() + ", " +
                this.preneur.getAdresse().getCodePostal() + " " +
                this.preneur.getAdresse().getPays().getNomFrFr();
    }

    public String objet(){
        return "Le Bailleur met en location au Preneur le Bien suivant:" +
                "\n" +
                this.bienReservee.getType_bien().getNom() + " à l'adresse : " +
                this.bienReservee.getCoordonnee().getNum() + " " + this.bienReservee.getCoordonnee().getRue() + ", dans la ville de " +
                this.bienReservee.getCoordonnee().getVille().getNomVille() + " en " + this.bienReservee.getCoordonnee().getVille().getProvince().getNomprovince() + "." +
                "\n" +
                this.bienReservee.getType_bien().getNom() + " est conçu pour héberger  " + this.bienReservee.getNpmax() + " personne(s) maximum." +
                "\n" +
                "A charge pour le Preneur de payer le loyer, d'apporter les soins d'un bon père de famille au Bien dans le même état à la fin du contrat.";
    }

    public String etatLieu(){
        return "Il sera dressé au début de la location, entre les deux parties, un état des lieux détaillé à frais communs, " +
                "qui sera annexé au présent contrat." +
                "L'état des lieux de fin de bail sera effectué à frais communs après la restitution du Bien au Bailleur.";
    }

    public String loyer(){
        return "Le Bail est consenti en contrepartie d'un loyer de " + this.bienReservee.getPrix() + " euros" +
                " par nuit, payé en une fois." +
                "Le paiement du loyer se fait à la signature du bail par le Preneur sur le site MOBEMBO.cd." +
                "Le loyer est payé, sauf nouvelles instructions du Bailleur, sur le compte en banque suivant:" +
                " " + this.bailleur.getInfoBancaires().getNumCompte();
    }

    public String loyerMobembo(){
        return "Pour chaque réservation faite sur l'application MOBEMBO.cd  pour le Bien cité en Objet, " +
                "le Preneur sera crédité de ((" + this.bienReservee.getPrix() + " x la durée (en nuit) ) - 10%) par le Bailleur." +
                " Le loyer est payé, sauf nouvelles instructions du Bailleur, sur le compte en banque suivant:" +
                " " + this.preneur.getInfoBancaires().getNumCompte() + ".";
    }

    public String dureeClient(){
        return "Le Bail commence le " + this.dateArrivee + " à partir de 12 h (am) et est conclu pour une durée qui s'étend jusqu'au " + this.dateDepart + " à 10 h (am) inclus." +
                "Le Bail peut être résilié moyennant la somme totale d'une nuit (" + this.bienReservee.getPrix() + " /euro)";
    }

    public String dureeMobembo(){
        return "Le Bail commence le " + this.dateArrivee + " à partir de 12 h (am) et est conclu pour une durée qui s'étend jusqu'au " + this.dateDepart + " à 10 h (am) inclus." +
                "Le Bail peut être résilié moyennant la somme totale d'une nuit (" + this.bienReservee.getPrix() + " /euro)";
    }

    public String dARDL(){
        return "Le présent contrat est régi par le droit belge." +
                "Tout différent relatif au présent contrat sera soumis aux tribunaux de l'arrondissement" +
                " judiciaire du domicile du Bailleur." +
                "\n" +
                "Fait sur le site MOBEMBO.cd, le " + LocalDate.now() +
                ", à la disposition des deux parties sur MOBEMBO.cd";
    }
}
