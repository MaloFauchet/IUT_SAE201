package lepatio.sae.IHM_Reservation;

import javafx.collections.ObservableList;

public class Abonne extends Client {

    public Abonne(String nom, String prenom, String adresse, String tel, String mail) {
        super(nom, prenom, adresse, tel, mail);
    }

    public Abonne(String nom, String prenom, String adresse, String tel, String mail, ObservableList<Reservation> reservations) {
        super(nom, prenom, adresse, tel, mail, reservations);
    }

    public Abonne(String nom, String prenom, String adresse, String tel, String mail, Reservation reservation) {
        super(nom, prenom, adresse, tel, mail, reservation);
    }
}
