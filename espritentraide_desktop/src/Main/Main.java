/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Seif Bejaoui
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Esprit Entr'aide");
        primaryStage.setMaximized(false);
        primaryStage.setMaxHeight(520);
        primaryStage.setMinHeight(500);
        primaryStage.setMaxWidth(900);
        primaryStage.setMinWidth(900);
        Scene scene = new Scene(root,900,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
