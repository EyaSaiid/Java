/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.OffreTravail;
import Entities.Restaurant;
import Services.OffreTravailService;
import Services.RestaurantService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class Add_jobsController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private Button Annuler;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField titre;
    @FXML
    private Button Retour;
    @FXML
    private Label Nullable_warn;
    @FXML
    private Label img;
    @FXML
    private ComboBox<Restaurant> restaurant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showRestaurant();
    }    



    @FXML
    private void Retour(ActionEvent event) throws IOException {
       Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_jobs.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();   
    }

    @FXML
    private void AnnulerOffre(ActionEvent event) {
        // get a handle to the stage
    Stage stage = (Stage) Annuler.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    @FXML
    private void AjouterOffre(ActionEvent event) {
        if (titre.getText().isEmpty() || description.getText().isEmpty() ||restaurant.getSelectionModel().isEmpty()  )
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.initOwner(Annuler.getScene().getWindow());
            alert.showAndWait();  
        }
        else  
        {
            try {
                Restaurant cat = restaurant.getSelectionModel().getSelectedItem();
                int id=cat.getId_restaurant();
                
                OffreTravail o = new OffreTravail(titre.getText(),description.getText(),id);
                OffreTravailService os = new OffreTravailService();
                Restaurant r = new RestaurantService().showByID(id);
                System.out.println(o);
                System.out.println(o.getId_restaurant());
                System.out.println(r.getId_user());

                os.add(o,r.getId_user());
                 /*tray notification*/
                TrayNotification tray = new TrayNotification("Avec succès", "Offre de travail  a été ajouté avec succés !", SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(4));
            } catch (SQLException ex) {
                Logger.getLogger(Add_jobsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
        private void showRestaurant(){
            
            List<Restaurant> listC;
            listC = new RestaurantService().show();
            ArrayList<Restaurant> libelles= new ArrayList<>();
            for(Restaurant cat : listC){
                Restaurant Ocat = new Restaurant();
                Ocat.setId_restaurant(cat.getId_restaurant());
                Ocat.setNom_restaurant(cat.getNom_restaurant());
                libelles.add(Ocat);
            }
            ObservableList<Restaurant> choices = FXCollections.observableArrayList(libelles);
            restaurant.setItems(choices);
        }
        
    

    
}
