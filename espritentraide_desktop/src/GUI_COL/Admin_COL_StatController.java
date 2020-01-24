/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COL;

import Entities.Flatshare;
import Services.FlatshareService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS PC
 */
public class Admin_COL_StatController implements Initializable {

    private int annonces;
    private int demandes;

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
    @FXML
    private PieChart PieChart;
    @FXML
    private BarChart<String, Number> BarCh;
    @FXML
    CategoryAxis xAxis;
    @FXML
    NumberAxis yAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FlatshareService FS = new FlatshareService();

        annonces = FS.selectAllAnnonce().size();
        demandes = FS.selectAllDemande().size();

        xAxis.setLabel("Type de Publication");
        xAxis.setTickLabelRotation(90);

        yAxis.setLabel("Nombre d'annonce");

        XYChart.Series<String, Number> series1 = new XYChart.Series();
        series1.getData().addAll(new XYChart.Data("Annonces", annonces), new XYChart.Data("Demandes", demandes));
        BarCh.getData().add(series1);
        // TODO
    }

    @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAttente_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Attente.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllPublie_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Published.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAnnule_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Canceled.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void Close_action(MouseEvent event) {
    }

    @FXML
    private void Stat_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Stat.fxml"));
        btn_stat.getScene().setRoot(root);
    }

}
