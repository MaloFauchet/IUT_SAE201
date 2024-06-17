package lepatio.sae.IHM_Reservation;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class CtrlCreerClient {

    @FXML private Button bnAnnuler;
    @FXML private Button bnValider;
    @FXML private CheckBox cbAbonnee;
    @FXML private Label lblErreurAdr;
    @FXML private Label lblErreurEmail;
    @FXML private Label lblErreurNom;
    @FXML private Label lblErreurPrenom;
    @FXML private Label lblErreurTel;
    @FXML private TextField txtAdr1;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNom;
    @FXML private TextField txtPrenom;
    @FXML private TextField txtTel;

    @FXML void clicAnnuler(ActionEvent event) {
		Donnees.clientCreerParReservation = false;
    	Main.fermerCreationClient();
    }

    //vérification pour validation
    @FXML void clicValider(ActionEvent event) {

    	if(lblErreurNom.isVisible()) {
			errorAlert("Le nom doit avoir au moins un caractère et ne doit pas dépasser 15 caracères.", "Nom : saisie incorrect");
			return;
    	}

    	if(lblErreurPrenom.isVisible()) {
			errorAlert("Le prénom doit avoir au moins un caractère et ne doit pas dépasser 15 caractères.", "Prénom : saisie incorrect");
			return;
    	}

    	if(lblErreurAdr.isVisible()) {
			errorAlert("L'addresse doit avoir au moins un caractère et ne doit pas dépasser 35 caractères.", "Adresse : saisie incorrect");
			return;
    	}

    	if(lblErreurTel.isVisible()) {
    		if(lblErreurTel.textProperty().getValue().equals("Erreur : Maximum 10 caractères")) {
				errorAlert("Le numéro de téléphone ne doit pas dépasser 10 caractères.", "Téléphone : saisie incorrect");
            }
			else {
				errorAlert("Le numéro de téléphone ne doit contenir que des chiffres.", "Téléphone : format incorrect");
            }
            return;
        }

    	if (!estMail(txtEmail.getText().strip())){
			errorAlert("L'email est incorrect", "Email : format incorrect");
    		lblErreurEmail.setVisible(true);
			return;
    	}

		// si tous les checks sont passés :
		if (cbAbonnee.isSelected()) {
			Abonne a = new Abonne(txtNom.textProperty().getValue().strip(),
					txtPrenom.textProperty().getValue().strip(),
					txtAdr1.textProperty().getValue().strip(),
					txtTel.textProperty().getValue().strip(),
					txtEmail.textProperty().getValue().strip());
			Donnees.ajouterClient(a);
		} else {
			Client c = new Client(txtNom.textProperty().getValue().strip(),
					txtPrenom.textProperty().getValue().strip(),
					txtAdr1.textProperty().getValue().strip(),
					txtTel.textProperty().getValue().strip(),
					txtEmail.textProperty().getValue().strip());
			Donnees.ajouterClient(c);
    	}
		Donnees.clientCreerParReservation = true;

		Main.setNouveauClient();
		Main.fermerCreationClient();
    }
    
    @FXML void gererNbCaract(KeyEvent event) {
        lblErreurNom.setVisible(txtNom.getText().strip().length() >= 16 || txtNom.getText().isBlank());

        lblErreurPrenom.setVisible(txtPrenom.getText().strip().length() >= 16 || txtPrenom.getText().isBlank());

        lblErreurAdr.setVisible(txtAdr1.getText().strip().length() >= 36 || txtAdr1.getText().isBlank());

		lblErreurEmail.setVisible(txtEmail.getText().strip().length() >= 36 || txtEmail.getText().isBlank());
    	
    	if (estValide(txtTel.getText().strip())){
    		lblErreurTel.setVisible(false);
    		if (txtTel.getText().length() >= 11){
    			lblErreurTel.textProperty().setValue("Erreur : Maximum 10 caractères");
    			lblErreurTel.setVisible(true);
    		}else if (txtTel.getText().length() < 10){
				lblErreurTel.textProperty().setValue("Erreur : 10 caractères minimum");
    			lblErreurTel.setVisible(true);
    		} else {
				lblErreurTel.setVisible(false);
			}
    	} else {
    		lblErreurTel.textProperty().setValue("Erreur : Ne doit contenir que des chiffres");
			lblErreurTel.setVisible(true);
    	}
    	
    	if(estMail(txtEmail.getText().strip())) {
    		lblErreurEmail.setVisible(false);
    	}
    }

	private void errorAlert(String content, String title) {
		Alert erreur = new Alert(AlertType.ERROR, content, ButtonType.OK);
		erreur.setTitle(title);
		erreur.showAndWait();
	}

	private boolean estMail(String mail) {
		return mail.matches("(\\w+\\.?)+@(\\w+\\.?)*\\w\\.\\w+");
	}
    
    private boolean estValide(String str) {
       	return str.matches("\\d*");
    }

	public void clearValues() {
		txtNom.clear();
		txtTel.clear();
		txtPrenom.clear();
		txtAdr1.clear();
		txtEmail.clear();
		cbAbonnee.setSelected(false);
	}
    
    public void initialize() {
    	txtNom.setTooltip(new Tooltip("Maximum 15 caractères"));
    	txtTel.setTooltip(new Tooltip("Maximum 10 caractères"));
    	txtPrenom.setTooltip(new Tooltip("Maximum 15 caractères"));
    	txtAdr1.setTooltip(new Tooltip("Maximum 35 caractères"));
    	txtEmail.setTooltip(new Tooltip("Ex:jean@gmail.com"));
		
		// griser le bouton OK tant que le nom n'est pas saisi. Les autres donn�es obligatoires
		// ont une valeur et ne peuvent pas �tre effac�es par l'utilisateur.
    	BooleanBinding manque = Bindings.or(txtNom.textProperty().isEmpty(), txtPrenom.textProperty().isEmpty()).
				or(txtAdr1.textProperty().isEmpty().
				or(txtTel.textProperty().isEmpty().
				or(txtEmail.textProperty().isEmpty())));
    	bnValider.disableProperty().bind(Bindings.when(manque).then(true).otherwise(false));
    }
}