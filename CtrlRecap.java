package lepatio.sae.IHM_Reservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CtrlRecap {

    @FXML
    private Label lbNbPlaceAbonne;

    @FXML
    private Label lbNomClient;

    @FXML
    private Label lbRangee;

    @FXML
    private Label lbZone;

    @FXML
    private Label lbNbPlaceSenior;

    @FXML
    private Label lbDateSpectacle;

    @FXML
    private Button bnAnnuler;

    @FXML
    private Label lbNbPlaceAdulte;

    @FXML
    private Label lbNbPlaceJeune;

    @FXML
    private Label lbPrenomClient;

    @FXML
    private Button bnConfirmer;

    @FXML
    private Label lbNomSpectacle;

    private Reservation reservation;
    private Client client;
    
    @FXML
    void ConfirmerRecap(ActionEvent event) {
        this.client.ajouterReservation(this.reservation);
    	Main.fermerRecap();
    }

    @FXML
    void fermerRecap(ActionEvent event) {
    	Main.fermerRecap();
    }

    public void afficherReservation(Reservation reservation, Client client) {
        this.client = client;
        this.reservation = reservation;
        lbRangee.setText(reservation.getRangee());
        lbNbPlaceAdulte.setText(reservation.getNbPlaceAdulte());
        lbNbPlaceJeune.setText(reservation.getNbPlaceJeune());
        lbPrenomClient.setText(reservation.getPrenomClient());
        lbNomClient.setText(reservation.getNomClient());
        lbNomSpectacle.setText(reservation.getNomSpectacle());
        lbDateSpectacle.setText(reservation.getDateSpectacle().toString());
        lbNbPlaceAbonne.setText(reservation.getNbPlaceAbonne());
        lbNbPlaceSenior.setText(reservation.getNbPlaceSenior());
        lbZone.setText(reservation.getZone());
    }
    
    public void initialize() {

    }

}
