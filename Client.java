package lepatio.sae.IHM_Reservation;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class Client {
    private static int cls_num = 0;

    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty adresse;
    private StringProperty tel;
    private StringProperty mail;
    private StringProperty numero;
    private IntegerProperty nbReservation;
    private StringProperty estAbonne;

    ObservableList<Reservation> reservations;

    public Client(String nom, String prenom, String adresse, String tel, String mail, ObservableList<Reservation> reservations) {
        this.nom = new SimpleStringProperty();
        this.setNom(nom);
        this.prenom = new SimpleStringProperty();
        this.setPrenom(prenom);
        this.adresse = new SimpleStringProperty();
        this.setAdresse(adresse);
        this.tel = new SimpleStringProperty();
        this.setTel(tel);
        this.mail = new SimpleStringProperty();
        this.setMail(mail);
        this.numero = new SimpleStringProperty();
        this.setNumero(String.valueOf(++cls_num));
        this.reservations = reservations;
        this.nbReservation = new SimpleIntegerProperty();
        this.nbReservation.set(this.reservations.size());
        this.estAbonne = new SimpleStringProperty();
        this.getEstAbonne();
    }

    public Client(String nom, String prenom, String adresse, String tel, String mail, Reservation reservation) {
        this.nom = new SimpleStringProperty();
        this.setNom(nom);
        this.prenom = new SimpleStringProperty();
        this.setPrenom(prenom);
        this.adresse = new SimpleStringProperty();
        this.setAdresse(adresse);
        this.tel = new SimpleStringProperty();
        this.setTel(tel);
        this.mail = new SimpleStringProperty();
        this.setMail(mail);
        this.numero = new SimpleStringProperty();
        this.setNumero(String.valueOf(++cls_num));
        this.reservations = FXCollections.observableArrayList();
        this.reservations.add(reservation);
        this.nbReservation = new SimpleIntegerProperty();
        this.nbReservation.set(1);
        this.estAbonne = new SimpleStringProperty();
        this.setEstAbonne();
    }

    public Client(String nom, String prenom, String adresse, String tel, String mail) {
        this.nom = new SimpleStringProperty();
        this.setNom(nom);
        this.prenom = new SimpleStringProperty();
        this.setPrenom(prenom);
        this.adresse = new SimpleStringProperty();
        this.setAdresse(adresse);
        this.tel = new SimpleStringProperty();
        this.setTel(tel);
        this.mail = new SimpleStringProperty();
        this.setMail(mail);
        this.numero = new SimpleStringProperty();
        this.setNumero(String.valueOf(++cls_num));
        this.reservations = FXCollections.observableArrayList();
        this.nbReservation = new SimpleIntegerProperty();
        this.nbReservation.set(0);
        this.estAbonne = new SimpleStringProperty();
        this.getEstAbonne();
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getTel() {
        return tel.get();
    }

    public void setTel(String tel) {
        assert tel.length() == 10;  // taille reglementaire de 10 chiffre
        assert tel.matches("\\d*");  // Seulement des chiffres
        this.tel.set(tel);
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(String mail) {
        assert mail.matches("\\w+@\\w+\\.\\w+");  // check bon format
        this.mail.set(mail);
    }

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        assert numero.matches("\\d*");
        this.numero.set(numero);
    }

    public ObservableList<Reservation> getReservations() {
        return this.reservations;
    }

    public void ajouterReservation(Reservation reservation) {
        this.nbReservation.set(this.nbReservation.get() + 1);
        this.reservations.add(reservation);
    }

    public void retirerReservation(Reservation reservation) {
        this.nbReservation.set(this.nbReservation.get() - 1);
        this.reservations.remove(reservation);
    }

    public void modifierReservation(Reservation reservation) {
        boolean trouve = false;
        int i=0;
        while (!trouve && i<this.reservations.size()) {
            if (this.reservations.get(i).getNumero() == reservation.getNumero()){
                this.reservations.set(i, reservation);
                trouve = true;
            }
            i++;
        }
    }

    public int getNbReservation() {
        return this.nbReservation.get();
    }

    public String getEstAbonne() {
        return this.estAbonne.get();
    }

    protected void setEstAbonne() {
        if (this instanceof Abonne) {
            this.estAbonne.set("Abonné");
        } else {
            this.estAbonne.set("Client");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(numero, client.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    public int compareNumero(Client other) {
        Integer num1 = Integer.parseInt(this.numero.get());
        Integer num2 = Integer.parseInt(other.getNumero());
        return num1.compareTo(num2);
    }

    public int compareNom(Client other) {
        return this.nom.get().compareTo(other.getNom());
    }

    public int comparePrenom(Client other) {
        return this.prenom.get().compareTo(other.getPrenom());
    }

    public int compareAdresse(Client other) {
        return this.adresse.get().compareTo(other.getAdresse());
    }

    public int compareTel(Client other) {
        return this.tel.get().compareTo(other.getTel());
    }

    public int compareMail(Client other) {
        return this.mail.get().compareTo(other.getMail());
    }

    @Override
    public String toString() {
        String res = "Nom : " + this.nom.get() + "; Prenom : " + this.prenom.get() + "; Adresse : " + this.adresse.get() + "; Tel : " + this.tel.get() + "; Mail : " + this.mail.get() + "; Numero : " + this.numero.get() + "; Nb reservation : " + this.reservations.size();
        return res;
    }
}
