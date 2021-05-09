package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Chair;
import model.GameModel;

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
        System.out.println(chairIndex);
        gameState.place(chairIndex-1);
        setColor();
    }
}
