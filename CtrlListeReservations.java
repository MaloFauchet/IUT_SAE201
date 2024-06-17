package lepatio.sae.IHM_Reservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CtrlListeReservations {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnValider;

    @FXML
    private TableView<Reservation> tvListeReservations;

    private Client client;
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    @FXML
    void clickAnnuler(ActionEvent event) {
        Main.fermerListeReservations();
    }

    @FXML
    void clickValider(ActionEvent event) {
        Main.fermerListeReservations();
    }

    void setClient(Client client) {
        this.client = client;
        reservations = client.getReservations();
        tvListeReservations.setItems(reservations);
    }

    public void initialize() {
        client = Donnees.getLesClients().get(0);
        reservations = client.getReservations();

        TableColumn<Reservation, String> colonne1 = new TableColumn<>("Nom");
        colonne1.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        tvListeReservations.getColumns().set(0, colonne1);

        TableColumn<Reservation, String> colonne2 = new TableColumn<>("Prénom");
        colonne2.setCellValueFactory(new PropertyValueFactory<>("prenomClient"));
        tvListeReservations.getColumns().set(1, colonne2);

        TableColumn<Reservation,String> colonne3 = new TableColumn<>("Spectacle");
        colonne3.setCellValueFactory(new PropertyValueFactory<>("nomSpectacle"));
        tvListeReservations.getColumns().set(2, colonne3);

        TableColumn<Reservation, String> colonne4 = new TableColumn<>("Date");
        colonne4.setCellValueFactory(new PropertyValueFactory<>("dateSpectacle"));
        tvListeReservations.getColumns().set(3, colonne4);

        TableColumn<Reservation, String> colonne5 = new TableColumn<>("Zone");
        colonne5.setCellValueFactory(new PropertyValueFactory<>("zone"));
        tvListeReservations.getColumns().set(4, colonne5);

        TableColumn<Reservation, String> colonne6 = new TableColumn<>("Rangée");
        colonne6.setCellValueFactory(new PropertyValueFactory<>("rangee"));
        tvListeReservations.getColumns().set(5, colonne6);

        TableColumn<Reservation, String> colonne7 = new TableColumn<>("Num Place");
        colonne7.setCellValueFactory(new PropertyValueFactory<>("numeroPlace"));
        tvListeReservations.getColumns().set(6, colonne7);

        TableColumn<Reservation, String> colonne8 = new TableColumn<>("Adulte");
        colonne8.setCellValueFactory(new PropertyValueFactory<>("nbPlaceAdulte"));
        tvListeReservations.getColumns().set(7, colonne8);

        TableColumn<Reservation, String> colonne9 = new TableColumn<>("Abonné");
        colonne9.setCellValueFactory(new PropertyValueFactory<>("nbPlaceAbonne"));
        tvListeReservations.getColumns().set(8, colonne9);

        TableColumn<Reservation, String> colonne10 = new TableColumn<>("Senior");
        colonne10.setCellValueFactory(new PropertyValueFactory<>("nbPlaceSenior"));
        tvListeReservations.getColumns().set(9, colonne10);

        TableColumn<Reservation, String> colonne11 = new TableColumn<>("Jeune");
        colonne11.setCellValueFactory(new PropertyValueFactory<>("nbPlaceJeune"));
        tvListeReservations.getColumns().set(10, colonne11);

        TableColumn<Reservation, Integer> colonne12 = new TableColumn<>("Total");
        colonne12.setCellValueFactory(new PropertyValueFactory<>("nbPlaceTotal"));
        tvListeReservations.getColumns().set(11, colonne12);

        tvListeReservations.setItems(reservations);
        tvListeReservations.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
