package lepatio.sae.IHM_Reservation;

import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class CtrlReservation {
    @FXML
    private ChoiceBox<String> cbRepresentation;

    @FXML
    private TextField txtRangee;

    @FXML
    private TextField txtNbPlaceAdulte;

    @FXML
    private Button bnValider;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtVilleClient;

    @FXML
    private RadioButton rbLogesGauches;

    @FXML
    private Label lbClientTrouve;

    @FXML
    private TextField txtNomClient;

    @FXML
    private TextField txtNbPlaceAbonne;

    @FXML
    private Button bnConfirmerClient;

    @FXML
    private RadioButton rbBalcon;

    @FXML
    private TextField txtNumeroPlace;

    @FXML
    private TextField txtNbPlaceJeune;

    @FXML
    private Button bnAnnuler;

    @FXML
    private RadioButton rbLogesDroites;

    @FXML
    private TextField txtNbPlaceSenior;

    @FXML
    private ToggleGroup rbGroupZone;

    @FXML
    private RadioButton rbOrchestre;
    
    private Client client;
    
    private int totalPlaces;


    @FXML
    void ValiderReservation(ActionEvent event) throws IOException {
        if (client == null) {
            errorAlert("Veuillez sélectionner un client", "Erreur : Aucun client sélectionné");
            return;
        }

        // recupere le numero de fauteuil
        try {
            int num = Integer.parseInt(txtNumeroPlace.getText());
            if (num <= 0)
                throw new NumberFormatException("Le numéro de place doit être supérieur à 0");
        } catch(NumberFormatException e) {
            errorAlert("Le numéro de fauteuil doit être un entier superieur à 0", "Numero de fauteuil : format incorrect");
            return;
        }

        // check que la rangee entree n'ait qu'un seul caractère
        if (txtRangee.getText().length() != 1) {
            errorAlert("La rangée doit être une unique lettre", "Rangée : taille incorrecte");
            return;
        }

        // check que la rangee entree soit une lettre
        if (!Main.estLettre(txtRangee.getText())) {
            errorAlert("La rangée doit être une lettre ", "Rangée : format incorrecte");
            return;
        }

        // check que la date soit après aujourd'hui
        LocalDate dateChoisie = datePicker.getValue();
        if (dateChoisie.isBefore(LocalDate.now())) {
            errorAlert("La date choisi ne peut pas être avant aujourd'hui", "Date choisie : date incorrecte");
            return;
        }

        int totalPlaces = 0;

        // recupere le nombre de place reserve
        try {
            int num1 = Integer.parseInt(txtNbPlaceAdulte.getText());
            int num2 = Integer.parseInt(txtNbPlaceAbonne.getText());
            int num3 = Integer.parseInt(txtNbPlaceSenior.getText());
            int num4 = Integer.parseInt(txtNbPlaceJeune.getText());
            if (num1 < 0 || num2 < 0 || num3 < 0 || num4 < 0) {
                throw new NumberFormatException("Le nombre de place doit être un entier positif");
            }
            totalPlaces = num1 + num2 + num3 + num4;
            if (totalPlaces <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            errorAlert("Le nombre total de place doit être un entier positif supérieur à 0", "Numero de place : format incorrect");
            return;
        }

        // Enregistrement des données pour les récupérer dans le récapitulatif
        Reservation reservation = new Reservation(
                client.getNom(),
                client.getPrenom(),
                cbRepresentation.getSelectionModel().getSelectedItem(),
                dateChoisie,
                rbGroupZone.getSelectedToggle().getUserData().toString(),
                txtRangee.getText(),
                txtNumeroPlace.getText(),
                txtNbPlaceAdulte.getText(),
                txtNbPlaceAbonne.getText(),
                txtNbPlaceSenior.getText(),
                txtNbPlaceJeune.getText(),
                totalPlaces
        );
        Main.ouvrirRecap(reservation, client);
    }

    private void errorAlert(String title, String content) {
        Alert erreur = new Alert(AlertType.ERROR, title, ButtonType.OK);
        erreur.setTitle(content);
        erreur.showAndWait();
    }

    @FXML
    void fermerReservation(ActionEvent event) {
    	Main.fermer();
    }
    
    @FXML
    void ChercherClient(ActionEvent event) throws IOException {
        String nomClient = txtNomClient.getText();
        String villeClient = txtVilleClient.getText();

        if (nomClient.isEmpty() || villeClient.isEmpty()) {
            lbClientTrouve.textProperty().setValue("");
            creationClient();
        }

        ObservableList<Client> listeClients = Donnees.existeClient(nomClient, villeClient);
        if (listeClients.isEmpty()) {
            lbClientTrouve.setText("Aucun client trouvé avec ces critères.");
            // Création d'un nouveau client
            creationClient();
            return;
        }

        lbClientTrouve.setText("Client trouvé(e)");
        // si il n'y a qu'un seul client qui correspond
        if (listeClients.size() == 1) {
            client = listeClients.get(0);
            return;
        }

        // si plusieurs clients correspondent
        // TODO : ouverture liste clients correspondantes
        Main.setClientToList(nomClient, villeClient);
        Main.ouvrirListeClients();
    }

    @FXML
    public void creationClient() throws IOException {
        Main.getNouveauClient();
    }

    @FXML
    public void setNouveauClient() {
        if (Donnees.clientCreerParReservation) {
            Client c = Donnees.getLesClients().get(Donnees.getLesClients().size()-1);
            setClientInternally(c);
        }
    }

    @FXML
    private void setClientInternally(Client c) {
        client = c;
        txtNomClient.setText(c.getNom());
        txtVilleClient.setText(c.getAdresse());
        lbClientTrouve.setText("Client trouvé(e)");
    }

    public void setClient(Client c) {
        setClientInternally(c);
    }
    
    public void initialize() {
        client = null;
        Donnees.chargementDonnees();

        // le nombre de place qui vas etre reserve
    	int nbPlaceTotal = 0;

        rbBalcon.setUserData("Balcon");
        rbOrchestre.setUserData("Orchestre");
        rbLogesDroites.setUserData("Loges Droites");
        rbLogesGauches.setUserData("Loges Gauches");


        // liste de spectacles temporaires
    	ObservableList<String> optionsRep = FXCollections.observableArrayList(
                "Les Rolling Stones",
                "Slimane",
                "Manu Verlume",
                "Renan Luce et Christophe Cadero",
                "Guillaume Meurice"
        );
        // initialisation de la liste dans le choicebox
    	cbRepresentation.setItems(optionsRep);

        // active le bouton de reservation que si toute case necessaire est remplie
    	bnValider.disableProperty().bind(
    			Bindings.when(
                        cbRepresentation.valueProperty().isNull()
                        .or(txtVilleClient.textProperty().isEmpty())
                        .or(txtNomClient.textProperty().isEmpty())
    					.or(datePicker.valueProperty().isNull())
    					.or(rbGroupZone.selectedToggleProperty().isNull())
    					.or(txtNbPlaceAdulte.textProperty().isEmpty())
    					.or(txtNbPlaceAbonne.textProperty().isEmpty())
    					.or(txtNbPlaceJeune.textProperty().isEmpty())
    					.or(txtNbPlaceSenior.textProperty().isEmpty())
                ).then(true)
    			.otherwise(false)
        );

    }

}
