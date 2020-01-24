/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.LostObject;
import Services.LostObjectService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Seif Bejaoui
 */
public class Admin_LO_StatController implements Initializable {

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

    private ArrayList<LostObject> arrayList;

    @FXML
    private JFXButton stat2;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LostObjectService LOservice = new LostObjectService();
        arrayList = (ArrayList) LOservice.admin_selectAll();
        Iterator<LostObject> it = arrayList.iterator();
        int attente = 0;
        int cancel = 0;
        int publish = 0;
        int arrayLength = arrayList.size();
        for (int i = 0; i < arrayLength; i++) {
            if (arrayList.get(i).getEnabled() == false && arrayList.get(i).getCancled() == false) {
                attente = attente + 1;
            } else if (arrayList.get(i).getEnabled() == true && arrayList.get(i).getCancled() == false) {
                publish = publish + 1;
            } else {
                cancel = cancel + 1;
            }

        }

        ObservableList<PieChart.Data> PieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data(cancel + " annonces annulées ", cancel),
                        new PieChart.Data(attente + " annonces en attentes", attente),
                        new PieChart.Data(publish + " annonces publiées", publish)
                );
        PieChart.setData(PieChartData);

    }

    @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAttente_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Attente.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllPublie_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Publish.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAnnule_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Cancel.fxml"));
        btn_all.getScene().setRoot(root);
    }


    @FXML
    private void Stat_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Stat.fxml"));
        btn_stat.getScene().setRoot(root);
    }

    @FXML
    private void StatDetail_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_StatDetail.fxml"));
        stat2.getScene().setRoot(root);
    }

   @FXML
    private void logo_action(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard.fxml"));
        logo.getScene().setRoot(root);
    }
}
