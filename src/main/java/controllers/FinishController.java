package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import results.DataHandler;
import results.GameResult;

import java.io.IOException;
import java.util.List;

/**
 * Toplistat megjelenito vegso ablak controller osztalya.
 */
@Slf4j
public class FinishController {
    ObservableList list = FXCollections.observableArrayList();

    @FXML
    ListView<String> toplistview;

    @FXML
    private Button backButton;

    /**
     * Inicializalo fuggveny.
     */
    @FXML
    public void initialize() {
        List<GameResult> toptenlist = DataHandler.listResults();
        loadData(toptenlist);
    }

    /**
     * Toplista betolto fuggveny.
     * @param toptenlist top 10 jatek objektumat tartalmazza.
     */
    private void loadData(List<GameResult> toptenlist){
        for(GameResult game : toptenlist){
            String tmp = "";
            tmp = game.getPlayer1() +" vs "+ game.getPlayer2() +" winner: "+game.getWinner() +" won as: "+ game.getWonAs() +" steps: "+ game.getSteps();
            list.add(tmp);
        }
        toplistview.getItems().setAll(list);

    }

    /**
     * Visszalepest biztosito fuggveny.
     * @param actionEvent Kivalto action.
     * @throws IOException
     */
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Hello.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Loading launch scene.");
    }

}
