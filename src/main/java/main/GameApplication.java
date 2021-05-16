package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Jatekmenet betolesere szolgalo class.
 */
public class GameApplication extends Application {

    /**
     * Jatekmenet <code>FXML</code>-t betolto fuggveny.
     * @param primaryStage parameter.
     * @throws Exception kivetel.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Hello.fxml"));
        primaryStage.setTitle("Chair Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
