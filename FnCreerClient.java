package lepatio.sae.IHM_Reservation;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.layout.*;

public class FnCreerClient extends Stage {
	CtrlCreerClient ctrl = new CtrlCreerClient();

	public FnCreerClient() throws IOException{
		CtrlCreerClient ctrl = new CtrlCreerClient();
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
	}
		
	private Pane creerSceneGraph() throws IOException {
		FXMLLoader loader;
		loader = new FXMLLoader(getClass().getResource("creationClient.fxml"));
        return loader.load();
	}
}