/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import Services.UserService;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import pijava.PiJAVA;
import org.controlsfx.control.Notifications;
import javafx.scene.control.Alert;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class Acceuil_adminController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Text nom_prenom;
    @FXML
    private Button btn_Gestion_Utilisateurs;
    @FXML
    private HBox goent;
    @FXML
    private HBox gos;
    @FXML
    private HBox goevent;
    @FXML
    private HBox logouttttt;
    @FXML
    private Button btn_Gestion_Restarants;
    @FXML
    private Button btn_Gestion_Produit;
    @FXML
    private Button btn_Gestion_Event;
    @FXML
    private Button btn_Logout;
    @FXML
    private Button btn_Gestion_jobs;
    @FXML
    private Button btn_Gestion_category;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom_prenom.setText(PiJAVA.user.getEmail());
        Image im = new Image(this.getClass().getResourceAsStream("/Images/" + PiJAVA.user.getImage_user()));
        photo.setImage(im);    
    }    

    @FXML
    private void Menu_user(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_User.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();    
    }
    
    @FXML
    private void Menu_jobs(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_jobs.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();   
        
    }
    
    @FXML
    private void Menu_Rest(ActionEvent event) {
    }

    @FXML
    private void Menu_Prod(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherProduit.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
    }

    @FXML
    private void Logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'application");
        alert.setHeaderText("Vous allez quitter l'application");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            alert.close();
        }
    }

    @FXML
    private void Menu_Prods(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategory.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }

    
    
}
