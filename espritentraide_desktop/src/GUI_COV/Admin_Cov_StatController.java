/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COV;

import Entities.Carpooling;
import Entities.LostObject;
import Services.CarpoolingService;
import Services.LostObjectService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Admin_Cov_StatController implements Initializable {

    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXButton btn_attente;
    @FXML
    private JFXButton btn_publie;
    @FXML
    private JFXButton btn_annule;
    @FXML
    private JFXButton btn_stat;
    
    private ArrayList<Carpooling> annonces;
    private ArrayList<Carpooling> demandes;
    private int nmb_annonces;
    private int nmb_demandes;
    @FXML
    private BarChart<String, Number> Bchart;
    
   
    /**
     * Initializes the controller class.
     */
    /*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarpoolingService cov = new CarpoolingService();
        annonces = (ArrayList) cov.selectAllAnnonce();
        demandes = (ArrayList) cov.selectAllDemande();
        nmb_annonces=annonces.size();
        nmb_demandes=demandes.size();
        String[] pubs = {"annonces","demandes"};
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(pubs));
        NumberAxis yAxis = new NumberAxis("Nombre de publications", 0.0d, 3000.0d, 1000.0d);
        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
            new BarChart.Series("Apples", FXCollections.observableArrayList(
               new BarChart.Data(pubs[0], nmb_annonces),
               new BarChart.Data(pubs[1], nmb_demandes)    
        )));
        Bchart= new BarChart<>(xAxis, yAxis);
        Bchart.getData().addAll(barChartData);
    }    */

    @FXML
    private void All_action(ActionEvent event) {
    }

    @FXML
    private void AllAttente_action(ActionEvent event) {
    }

    @FXML
    private void AllPublie_action(ActionEvent event) {
    }

    @FXML
    private void AllAnnule_action(ActionEvent event) {
    }

    @FXML
    private void Close_action(MouseEvent event) {
    }

    @FXML
    private void Stat_action(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
