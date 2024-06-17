package lepatio.sae.IHM_Reservation;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class CtrlListeClients {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnValider;

    @FXML
    private Button bnVoirReservations;

    @FXML
    private Button bnSupprimer;

    @FXML
    private RadioButton rbTriNomVille;

    @FXML
    private RadioButton rbTriNum;

    @FXML
    private ToggleGroup tri;

    @FXML
    private TableView<Client> tvListeClient;

    private String nomClientToList;
    private String villeClientToList;
    private ObservableList<Client> listeClients;

    private MenuItem optionSelectioner = new MenuItem("Sélectionner");
    private MenuItem optionVoirReservations = new MenuItem("Voir les réservations");
    private MenuItem optionSupprimer = new MenuItem("Supprimer");

    private ContextMenu menu = new ContextMenu(
            optionSelectioner,
            new SeparatorMenuItem(),
            optionVoirReservations,
            new SeparatorMenuItem(),
            optionSupprimer
    );

    @FXML
    void clickAnnuler(ActionEvent event) {
        Main.fermerListeClient();
    }

    @FXML
    void clickRadioButton(ActionEvent event) {
        if (rbTriNum.isSelected()) {
            listeClients = Donnees.getClientTrieNumero();
        } else {
            listeClients = Donnees.getClientTrieNomVille();
        }

        // check si on doit garder que des clients specifique
        if (nomClientToList != null) {
            listeClients = getClientToList();
        }
        tvListeClient.setItems(listeClients);
    }

    @FXML
    void clickValider(ActionEvent event) {
        if (tvListeClient.getSelectionModel().getSelectedIndex() != -1) {
            Client selectedClient = tvListeClient.getSelectionModel().getSelectedItem();

            // check si on doit donner le client selectionner
            if (nomClientToList != null) {
                Main.setReservationClient(selectedClient);
            }

            Main.fermerListeClient();
        }
    }

    @FXML
    void clickVoirReservations(ActionEvent event) throws IOException {
        Client selectedClient = tvListeClient.getSelectionModel().getSelectedItem();
        Main.setClientListeReservations(selectedClient);
        Main.ouvrirListeReservations();
    }

    @FXML
    void clickSupprimerClient(ActionEvent event) {
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Voulez-vous vraiment supprimer ce client ?",
                    ButtonType.YES,
                    ButtonType.NO
            );
            alert.setTitle("Confirmation de suppression");
            Optional<ButtonType> reponse;
            reponse = alert.showAndWait();
            if(reponse.get() == ButtonType.YES) {
                Client selectedClient = tvListeClient.getSelectionModel().getSelectedItem();
                Donnees.supprimerClient(selectedClient);
                this.listeClients = Donnees.getLesClients();
                this.listeClients = getClientToList();
                tvListeClient.setItems(listeClients);
            }
    }

    public void setClientToList(String nom, String ville) {
        nomClientToList = nom;
        villeClientToList = ville;
        clickRadioButton(null);
    }

    private ObservableList<Client> getClientToList() {
        ObservableList<Client> result = FXCollections.observableArrayList();
        for (Client client : listeClients) {
            if (client.getNom().compareToIgnoreCase(nomClientToList) == 0 && client.getAdresse().compareToIgnoreCase(villeClientToList) == 0) {
                result.add(client);
            }
        }
        return result;
    }

    public void initialize() {
        nomClientToList = null;
        villeClientToList = null;

        TableColumn<Client,Integer> colonne1 = new TableColumn<>("Numéro");
        colonne1.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tvListeClient.getColumns().set(0, colonne1);

        TableColumn<Client, String> colonne2 = new TableColumn<>("Nom");
        colonne2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tvListeClient.getColumns().set(1, colonne2);

        TableColumn<Client, String> colonne3 = new TableColumn<>("Prénom");
        colonne3.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tvListeClient.getColumns().set(2, colonne3);

        TableColumn<Client,String> colonne4 = new TableColumn<>("Adresse");
        colonne4.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tvListeClient.getColumns().set(3, colonne4);

        TableColumn<Client, String> colonne5 = new TableColumn<>("Téléphone");
        colonne5.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tvListeClient.getColumns().set(4, colonne5);

        TableColumn<Client, String> colonne6 = new TableColumn<>("Mail");
        colonne6.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tvListeClient.getColumns().set(5, colonne6);

        TableColumn<Client, Integer> colonne7 = new TableColumn<>("Nb Réservation");
        colonne7.setCellValueFactory(new PropertyValueFactory<>("nbReservation"));
        tvListeClient.getColumns().set(6, colonne7);

        TableColumn<Client, String> colonne8 = new TableColumn<>("Type");
        colonne8.setCellValueFactory(new PropertyValueFactory<>("estAbonne"));
        tvListeClient.getColumns().set(7, colonne8);

        clickRadioButton(null);
        tvListeClient.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //griser les boutons Modifier et Supprimer quand aucune s�lection
        BooleanBinding rien = Bindings.equal(tvListeClient.getSelectionModel().selectedIndexProperty(), -1);
        bnValider.disableProperty().bind(rien);
        bnSupprimer.disableProperty().bind(rien);
        bnVoirReservations.disableProperty().bind(rien);

        // ajout du click droit
        tvListeClient.setContextMenu(menu);
        optionSelectioner.setOnAction(e -> clickValider(null));
        optionVoirReservations.setOnAction(e -> {
            try {
                clickVoirReservations(null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        optionSupprimer.setOnAction(e -> clickSupprimerClient(null));

        // setup le double click sur un client
        tvListeClient.setOnMouseClicked(e -> {
            if (e.getClickCount()==2 && e.getButton()== MouseButton.PRIMARY && e.getTarget() instanceof Text) {
                clickValider(null);

            }
        });
    }

}
