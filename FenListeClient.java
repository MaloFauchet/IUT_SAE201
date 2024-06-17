package lepatio.sae.IHM_Reservation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FenListeClient extends Stage {
    CtrlListeClients ctrl;

    public FenListeClient() throws IOException {
        Scene laScene = new Scene(creerSceneGraph());
        this.setScene(laScene);
    }

    private Pane creerSceneGraph() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listeClients.fxml"));
        Pane root = loader.load();
        ctrl = loader.getController();

        return root;
    }

    public void setClientToList(String nom, String ville) {
        ctrl.setClientToList(nom, ville);
    }
}
