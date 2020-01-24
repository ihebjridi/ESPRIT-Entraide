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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Seif Bejaoui
 */
public class Admin_LO_StatDetailController implements Initializable {

    @FXML
    private LineChart<?, ?> lineChart;
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

    private ArrayList<LostObject> arrayList;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LostObjectService LOservice = new LostObjectService();
        arrayList = (ArrayList) LOservice.admin_selectAll_trier();
        Iterator<LostObject> it = arrayList.iterator();
        int attente = 0;
        int cancel = 0;
        int publish = 0;
        int arrayLength = arrayList.size();
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        for (int i = 0; i < arrayLength; i++) {
            if (arrayList.get(i).getEnabled() == false && arrayList.get(i).getCancled() == false) {
               series2.getData().add(new XYChart.Data(arrayList.get(i).getCreated_at().toString(),arrayList.get(i).getId()));
            } else if (arrayList.get(i).getEnabled() == true && arrayList.get(i).getCancled() == false) {
                series3.getData().add(new XYChart.Data(arrayList.get(i).getCreated_at().toString(), arrayList.get(i).getId()));
            } else {
               series.getData().add(new XYChart.Data(arrayList.get(i).getCreated_at().toString(), arrayList.get(i).getId()));
            }

        }


        lineChart.getData().addAll(series, series2, series3);

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
    private void logo_action(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard.fxml"));
        logo.getScene().setRoot(root);
    }

}
