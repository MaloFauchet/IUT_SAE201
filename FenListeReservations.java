package lepatio.sae.IHM_Reservation;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenListeReservations extends Stage{
    CtrlListeReservations ctrl;

    public FenListeReservations() throws IOException {
        Scene laScene = new Scene(creerSceneGraph());
        this.setScene(laScene);
    }

    private Pane creerSceneGraph() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listeReservations.fxml"));
        Pane root = loader.load();
        ctrl = loader.getController();

        return root;
    }

    public void setClient(Client client) {
        ctrl.setClient(client);
    }
}