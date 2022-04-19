/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class MenuPrincipal implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
      @FXML
    private Button btn_Ajouter;

    @FXML
    private Button btn_Afficher;

    @FXML
    void Afficher(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuCategory.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();


    }

    @FXML
    void Ajouter(ActionEvent event) throws IOException {
     Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduit.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     window.setScene(gestionViewScene);
     window.show();


    }
    
}
