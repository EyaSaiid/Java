/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class Menu_jobsController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Text nom_prenom;
    @FXML
    private Button btn_Gestion_Utilisateurs;
    @FXML
    private HBox goentjobs;
    @FXML
    private Button btn_Gestion_jobs;
    @FXML
    private HBox goent;
    @FXML
    private Button btn_Gestion_Restarants;
    @FXML
    private HBox gos;
    @FXML
    private Button btn_Gestion_Produit;
    @FXML
    private HBox goevent;
    @FXML
    private Button btn_Gestion_Event;
    @FXML
    private HBox logouttttt;
    @FXML
    private Button btn_Logout;
    @FXML
    private TableView<?> tablev;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TextField mnom;
    @FXML
    private TextField mprenom;
    @FXML
    private ComboBox<?> comsexe;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnajouter;
    @FXML
    private TableColumn<?, ?> col_titre;
    @FXML
    private TableColumn<?, ?> col_description;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label restau;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void Menu_user(ActionEvent event) {
    }

    @FXML
    private void Menu_jobs(ActionEvent event) {
    }

    @FXML
    private void Menu_Rest(ActionEvent event) {
    }

    @FXML
    private void Menu_Prod(ActionEvent event) {
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
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void goajouter(ActionEvent event) {
    }
    
}
