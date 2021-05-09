package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Chair;
import model.GameModel;
import model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameController {
    private GameModel gameState;

    @FXML
    private Button chair1;
    @FXML
    private Button chair2;
    @FXML
    private Button chair3;
    @FXML
    private Button chair4;
    @FXML
    private Button chair5;
    @FXML
    private Button chair6;
    @FXML
    private Button chair7;
    @FXML
    private Button chair8;
    @FXML
    private Button chair9;
    @FXML
    private Button chair10;
    @FXML
    private Button chair11;
    @FXML
    private Button chair12;
    @FXML
    private Button chair13;
    @FXML
    private Button chair14;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label playerLabel;
    @FXML
    private Pane floor;
    @FXML
    private Button doneButton;
    @FXML
    private Label resultLabel;

    private String username;
    public void setUsername(String username){
        this.username = username;
        usernameLabel.setText(username);
    }

    @FXML
    public void initialize() {
        gameState = new GameModel();
        floor.getChildren().setAll(chair1,chair2,chair3,chair4,chair5,chair6,chair7,chair8,chair9,chair10,chair11,chair12,chair13,chair14);
        setColor();
    }

    private void setColor() {
        playerLabel.setText("Player "+GameModel.playerTurn+"'s turn");
        playerLabel.setStyle("-fx-border-width: 3");

        switch(GameModel.playerTurn) {
            case ONE -> playerLabel.setStyle("-fx-text-fill: red;-fx-border-color: red;");
            case TWO -> playerLabel.setStyle("-fx-text-fill: blue;-fx-border-color: blue;");
        }

        for(int i = 0; i < GameModel.CHAIRS_LENGTH; i++) {
            switch (GameModel.getChair(i)) {
                case NONE -> floor.getChildren().get(i).setStyle("-fx-background-color: white; -fx-background-radius:40; -fx-border-color:black; -fx-border-radius:40;");
                case GIRL -> floor.getChildren().get(i).setStyle("-fx-background-color: #ff0000; -fx-background-radius:40; -fx-border-color:black; -fx-border-radius:40;");
                case BOY -> floor.getChildren().get(i).setStyle("-fx-background-color: #1E90FF; -fx-background-radius:40; -fx-border-color:black; -fx-border-radius:40;");
            }
        }
    }

    @FXML
    public void chairClick(MouseEvent mouseEvent){
        String clickedChair = mouseEvent.getPickResult().getIntersectedNode().getId();
        int chairIndex = Integer.parseInt(clickedChair.replaceAll("[^0-9]", ""));
        if(!gameState.canPlace(chairIndex-1)){
            System.out.println("Nem lehetseges a leultetes!");
            return;
        }
        if(gameState.canPlace(chairIndex-1) && !gameState.isFinished()) {
            System.out.println(chairIndex);
            gameState.place(chairIndex - 1);
            setColor();
            if(gameState.isFinished()){
                doneButton.setText("Finish");
                if(GameModel.playerTurn.equals(Player.ONE)){
                    resultLabel.setText("Player TWO wins!");
                    resultLabel.setStyle("-fx-text-fill: red;");
                    System.out.println("Player TWO wins!");
                }
                else {
                    resultLabel.setText("Player ONE wins!");
                    resultLabel.setStyle("-fx-text-fill: blue;");
                    System.out.println("Player ONE wins!");
                }
            }
        }
    }

    @FXML
    public void finishGame(ActionEvent actionEvent) throws IOException {
        if(!gameState.isFinished()){
            //TODO
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Finished.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
