package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class ModifyPart {
    private boolean inHouseSelected = true;

    @FXML private Label machineCompanyLabel;
    @FXML private TextField modifyPartIDTextField;
    @FXML private TextField modifyPartNameTextField;
    @FXML private TextField modifyPartInvTextField;
    @FXML private TextField modifyPartPriceTextField;
    @FXML private TextField modifyPartMaxTextField;
    @FXML private TextField modifyPartMachineCompanyTextField;

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void inHouseClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Machine-ID");
        inHouseSelected = true;
    }

    public void outsourcedClicked(ActionEvent actionEvent) {
        machineCompanyLabel.setText("Company Name");
        inHouseSelected = false;
    }

    public void saveClicked(ActionEvent actionEvent) {
    }
}
