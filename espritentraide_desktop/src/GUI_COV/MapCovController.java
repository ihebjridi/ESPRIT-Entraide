/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COV;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.InputMethodEvent;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MapCovController implements Initializable,MapComponentInitializedListener, DirectionsServiceCallback {
    @FXML
    private GoogleMapView mapView;
        @FXML
        private TextArea fromTextField;
        @FXML
        private TextArea toTextField;
        @FXML
        private Button bt_show;
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    
    @FXML
    private void toTextFieldAction(ActionEvent event) {
        mapInitialized();
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }
    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }
     @Override
    public void mapInitialized() {
         System.out.println("mapInitialized");
        MapOptions options = new MapOptions();

        options.center(new LatLong(36, 10))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize");
        //INIT API GMaps
        mapView.addMapInializedListener(this);
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
        //end INIT GMaps
       
    }   
    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXButton btn_annonce;
    @FXML
    private JFXButton btn_demande;
    @FXML
    private JFXButton btn_mesAds;
    @FXML
    private JFXButton btn_mesDmd;
    @FXML
    private JFXButton btn_AddAds;
    @FXML
    private JFXButton btn_AddDmd;
    @FXML
    private JFXTextField search;
    
    

     @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }



    @FXML
    private void MyAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_MyAds.fxml"));
        btn_mesAds.getScene().setRoot(root);
    }

    @FXML
    private void MyDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_MyDmd.fxml"));
        btn_mesDmd.getScene().setRoot(root);
    }

    @FXML
    private void AddAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_AddAds.fxml"));
        btn_AddAds.getScene().setRoot(root);
    }

    @FXML
    private void AddDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_AddDmd.fxml"));
        btn_AddDmd.getScene().setRoot(root);
    }

    @FXML
    private void Close_action(MouseEvent event) {
    }

    @FXML
    private void AllAnnonce_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_Annonce.fxml"));
        btn_annonce.getScene().setRoot(root);
    }

    @FXML
    private void AllDemande_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Cov_Demande.fxml"));
        btn_demande.getScene().setRoot(root);
    }

    @FXML
    private void toTextFieldAction(InputMethodEvent event) {
    }
}
