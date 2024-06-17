package lepatio.sae.IHM_Reservation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javafx.application.Application;
import javafx.scene.control.Toggle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	static private FenReservation 	fReservation;
	static private FenRecap fRecap;
	static private FnCreerClient fClient;
	static private FenListeClient fListeClient;
	static private FenListeReservations fListeReservations;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		fReservation = new FenReservation();
		fReservation.setTitle("Nouvelle réservation");
		fReservation.setResizable(false);

		fRecap = new FenRecap();
		fRecap.initModality(Modality.APPLICATION_MODAL);
		fRecap.setTitle("Récapitulatif de la réservation");
		fRecap.setResizable(false);

		fClient = new FnCreerClient();
		fClient.setTitle("Création de Client");
		fClient.initModality(Modality.APPLICATION_MODAL);
		fClient.setResizable(false);

		fListeClient = new FenListeClient();
		fListeClient.setTitle("Liste des clients");
		fListeClient.setResizable(false);

		fListeReservations = new FenListeReservations();
		fListeReservations.setTitle("Liste des reservations");
		fListeReservations.setResizable(false);

		fReservation.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	// Méthode pour ouvrir des fenetres
	public static void ouvrirRecap(Reservation reservation, Client client) throws IOException {
		fRecap.setReservation(reservation, client);
		fRecap.show();
	}
	
    public static void ouvrirCreationClient() throws IOException {
		fClient.show();
    }

	public static void ouvrirListeClients() throws IOException {
		fListeClient.show();
	}

	public static void ouvrirListeReservations() throws IOException {
		fListeReservations.show();
	}

	public static void getNouveauClient() throws IOException {
		ouvrirCreationClient();
	}

	public static void setNouveauClient() {
		fReservation.setNouveauClient();
	}

	public static void setReservationClient(Client client) {
		fReservation.setClient(client);
	}

	public static void setClientToList(String nom, String ville) {
		fListeClient.setClientToList(nom, ville);
	}

	public static void setClientListeReservations(Client client) {
		fListeReservations.setClient(client);
	}
    
	// Méthodes pour fermer des fenetres
	public static void fermer() {
		fReservation.close();
	}
	
	public static void fermerRecap() {
		fRecap.close();
	}

	public static void fermerCreationClient() {fClient.close();}

	public static void fermerListeClient() {fListeClient.close();}

	public static void fermerListeReservations() {fListeReservations.close();}
	
	public static boolean estEntier(String str) {
		return str.matches("\\d*");
	}
	
	public static boolean estLettre(String str) {
		return str.matches("[A-Za-z]");
	}
}
