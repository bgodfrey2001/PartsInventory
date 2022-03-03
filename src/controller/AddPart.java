package controller;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

/**This is the Add Part class.  This class is the controller for the Add Part screen.*/
public class AddPart {

    public void cancelClicked(ActionEvent actionEvent) throws IOException {
        Scene currentScene;
        Stage stage = (Stage) ((javafx.scene.control.Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }
}
