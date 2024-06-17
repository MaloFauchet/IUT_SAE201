package lepatio.sae.IHM_Reservation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Toggle;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private StringProperty numero;
    private Date date;
    private Date dateEnvoiConf;
    
    private SimpleStringProperty nomClient;
    private SimpleStringProperty prenomClient;
    private SimpleStringProperty nomSpectacle;
    private LocalDate dateSpectacle;
    private SimpleStringProperty zone;
    private SimpleStringProperty rangee;
    private SimpleStringProperty numeroPlace;
    private SimpleStringProperty nbPlaceAdulte;
    private SimpleStringProperty nbPlaceAbonne;
    private SimpleStringProperty nbPlaceSenior;
    private SimpleStringProperty nbPlaceJeune;
    private SimpleIntegerProperty nbPlaceTotal;

    public Reservation(String numero, Date date, Date dateEnvoiConf) {
        this.numero = new SimpleStringProperty(numero);
        this.date = date;
        this.dateEnvoiConf = dateEnvoiConf;
    }

    public Reservation(
						String nomClient,
                        String prenomClient,
                        String nomSpectacle,
						LocalDate dateSpectacle,
                        String zone,
                        String rangee,
                        String numeroPlace,
                        String nbPlaceAdulte,
                        String nbPlaceAbonne,
                        String nbPlaceSenior,
                        String nbPlaceJeune,
						int nbPlaces
		    		) {
    	this.nomClient = new SimpleStringProperty(nomClient);
    	this.prenomClient = new SimpleStringProperty(prenomClient);
    	this.nomSpectacle = new SimpleStringProperty(nomSpectacle);
    	this.dateSpectacle = dateSpectacle;
    	this.zone = new SimpleStringProperty(zone);
    	this.rangee = new SimpleStringProperty(rangee);
    	this.numeroPlace = new SimpleStringProperty(numeroPlace);
    	this.nbPlaceAdulte = new SimpleStringProperty(nbPlaceAdulte);
    	this.nbPlaceAbonne = new SimpleStringProperty(nbPlaceAbonne);
    	this.nbPlaceSenior = new SimpleStringProperty(nbPlaceSenior);
    	this.nbPlaceJeune = new SimpleStringProperty(nbPlaceJeune);
    	this.nbPlaceTotal = new SimpleIntegerProperty(nbPlaces);
    }
    
    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateEnvoiConf() {
        return dateEnvoiConf;
    }

    public void setDateEnvoiConf(Date dateEnvoiConf) {
        this.dateEnvoiConf = dateEnvoiConf;
    }
    
    public String getNomClient() {
        return nomClient.get();
    }

    public void setNomClient(String nomClient) {
        this.nomClient.set(nomClient);
    }

    public String getPrenomClient() {
        return prenomClient.get();
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient.set(prenomClient);
    }

    public String getNomSpectacle() {
        return nomSpectacle.get();
    }

    public void setNomSpectacle(String nomSpectacle) {
        this.nomSpectacle.set(nomSpectacle);
    }

    public LocalDate getDateSpectacle() {
        return dateSpectacle;
    }

    public void setDateSpectacle(LocalDate dateSpectacle) {
        this.dateSpectacle = dateSpectacle;
    }

    public String getZone() {
        return zone.get();
    }

    public void setZone(String zone) {
        this.zone.set(zone);
    }

    public String getRangee() {
        return rangee.get();
    }

    public void setRangee(String rangee) {
        this.rangee.set(rangee);
    }

    public String getNumeroPlace() {
        return numeroPlace.get();
    }

    public void setNumeroPlace(String numeroPlace) {
        this.numeroPlace.set(numeroPlace);
    }

    public String getNbPlaceAdulte() {
        return nbPlaceAdulte.get();
    }

    public void setNbPlaceAdulte(String nbPlaceAdulte) {
        this.nbPlaceAdulte.set(nbPlaceAdulte);
    }

    public String getNbPlaceAbonne() {
        return nbPlaceAbonne.get();
    }

    public void setNbPlaceAbonne(String nbPlaceAbonne) {
        this.nbPlaceAbonne.set(nbPlaceAbonne);
    }

    public String getNbPlaceSenior() {
        return nbPlaceSenior.get();
    }

    public void setNbPlaceSenior(String nbPlaceSenior) {
        this.nbPlaceSenior.set(nbPlaceSenior);
    }

    public String getNbPlaceJeune() {
        return nbPlaceJeune.get();
    }

    public void setNbPlaceJeune(String nbPlaceJeune) {
        this.nbPlaceJeune.set(nbPlaceJeune);
    }
    
    public int getNbPlaceTotal() {
        return nbPlaceTotal.get();
    }

    public void setNbPlaceTotal(int nbPlaces) {
        this.nbPlaceTotal.set(nbPlaces);
    }
    
}
