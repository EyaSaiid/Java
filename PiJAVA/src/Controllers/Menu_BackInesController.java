/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Menu_BackInesController implements Initializable {

 @FXML
    private Button btn_cat;

    @FXML
    private Button btn_pdt;

    @FXML
    private Button btnres;

    @FXML
    private Button btnreservation;
    
 @FXML
    void goStat(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/StatCatProduitPlat.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }
    @FXML
    void gocat(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuCategorie.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }

    @FXML
    void gopdt(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduitPlat.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }

    @FXML
    void gores(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }

    @FXML
    void goreservation(ActionEvent event) throws IOException {
 Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherReservation.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
