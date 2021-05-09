package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchController {
    @FXML
    private Label error;

    @FXML
    private Button helpButton;

    @FXML
    private Button startButton;

    @FXML
    private Button menu;

    @FXML
    private TextField userName;

    @FXML
    private Pane howToPlay;

    private void error(){
        error.setText("Please enter a username!");
    }

    @FXML
    private void help(){
        howToPlay.setVisible(true);
    }

    @FXML
    private void backToMenu(){
        howToPlay.setVisible(false);
    }

    public void start(ActionEvent actionEvent) throws IOException {
        if (userName.getText().isEmpty()) {
            error();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().setUsername(userName.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            //log.info("Username is set to {}, loading game scene.", userName.getText());
        }

    }
}
