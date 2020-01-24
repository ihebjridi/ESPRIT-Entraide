/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.labs.icalendaragenda.scene.control.agenda.ICalendarAgenda;
import jfxtras.labs.icalendarfx.VCalendar;


/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class EventAgendaController extends Application  {
    @FXML
    private Button b;
   
     
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        VCalendar mainVCalendar = new VCalendar();
        BorderPane root =new BorderPane();
        ICalendarAgenda agenda=new ICalendarAgenda(mainVCalendar);
        root.setCenter(agenda);
        Button importButton=new Button("Import an ics file");
        b=new Button("back");
        b.addEventHandler(ActionEvent.ACTION, 
                    new EventHandler<ActionEvent>() {
                        

            @Override
            public void handle(ActionEvent event) {
                ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
                      Stage stage = new Stage();
                    Scene sc = new Scene(parent);
                    stage.setScene(sc);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(EventAgendaController.class.getName()).log(Level.SEVERE, null, ex);
                }
      
            }
                    });
        HBox bh=new HBox(importButton,b);
        root.setTop(bh);
        FileChooser fileChooser =new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("ics files", "*.ics"));
        importButton.setOnAction(e ->
        {
            File file =fileChooser.showOpenDialog(primaryStage);
            if(file != null && file.toString().lastIndexOf("ics")>0){
                final List<String> log =new ArrayList<>();
                VCalendar ITIPMessage =new VCalendar();
                //ITIPMessage.copyChildrenFrom();
                Thread.setDefaultUncaughtExceptionHandler((thread,exception)->log.add(exception.getMessage()));
                
            }
        
        });
        Scene scene=new Scene(root,1066,668);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    @FXML
    private void back (ActionEvent event) throws Exception {
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
