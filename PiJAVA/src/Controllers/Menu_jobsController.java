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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import pijava.PiJAVA;
import Services.OffreTravailService;
import Entities.OffreTravail;
import Entities.Restaurant;
import Entities.User;
import Services.RestaurantService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<OffreTravail> tablev;
    @FXML
    private TableColumn<OffreTravail, String> col_id;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnajouter;
    @FXML
    private TableColumn<OffreTravail, String> col_titre;
    @FXML
    private TableColumn<OffreTravail, String> col_description;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label restau;
    
    ObservableList<OffreTravail> oblist=FXCollections.observableArrayList();
    OffreTravail offretravailm = new OffreTravail();
    
    @FXML
    private ComboBox<Restaurant> comRestau;
    @FXML
    private TableColumn<OffreTravail, String> col_restau;
    @FXML
    private TextField mtitre;
    @FXML
    private TextField mdescription;
    String path;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom_prenom.setText(PiJAVA.user.getEmail());
        Image im = new Image(this.getClass().getResourceAsStream("/Images/" + PiJAVA.user.getImage_user()));
        photo.setImage(im); 
        OffreTravailService os = new OffreTravailService();
        List<OffreTravail> lo = os.show();
        System.out.println(lo);
        oblist=FXCollections.observableArrayList(lo);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_restau.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
        tablev.setItems(oblist);
        tablev.setOnMouseClicked(e->{
                mod();
            });
        
        

        
    }

    private void mod(){
        System.out.println("works");
        for (OffreTravail o:tablev.getSelectionModel().getSelectedItems()){
            OffreTravailService os = new OffreTravailService();
            OffreTravail oo;
            oo = os.showByID(o.getId());
            this.offretravailm = oo;
             mtitre.setText(oo.getTitre());
             mdescription.setText(oo.getDescription() );
            
        }
             showRestaurant();

        
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
        
            if (mtitre.getText() == ""){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText("Alert");
                alert.setContentText("Veillez Choisir un Offre de travail avec un simple click");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(" Dialogue de Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("vous êtes sûr de modifier cet offre de travail ?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    this.offretravailm.setTitre(mtitre.getText());
                    this.offretravailm.setDescription(mdescription.getText());
                    Restaurant cat = comRestau.getSelectionModel().getSelectedItem();
                    int id=cat.getId_restaurant();
                    try {
            OffreTravailService os = new OffreTravailService();
            os.update(this.offretravailm, this.offretravailm.getId(),id);
            this.load();
            mtitre.setText("");
            mdescription.setText("");

            
            /*tray notification*/
                TrayNotification tray = new TrayNotification("Avec succès", "Offre de travail  a été modifiée avec succés !", SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(4));
                
        } catch (SQLException ex) {
            Logger.getLogger(Menu_jobsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                }
            }
        
    }

    private void load(){
        oblist.clear();
        //afficher
        OffreTravailService os = new OffreTravailService();
        List<OffreTravail> lo =os.show();
        System.out.println(lo);
        oblist=FXCollections.observableArrayList(lo);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_restau.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
        tablev.setItems(oblist);
        tablev.setOnMouseClicked(e->{
                mod();
            });
        
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
        if (this.offretravailm == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Veillez Choisir un offre de travail avvec un simple click");
            alert.show();
        }
        else{
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer cet offre de travail  ?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                 try {
                     OffreTravailService os = new OffreTravailService();
                     os.deleteById(this.offretravailm.getId());
                     this.load();
                     mtitre.setText("");
                     mdescription.setText("");
                     this.offretravailm=null; 
                    
                 } catch (SQLException ex) {
                     Logger.getLogger(Menu_jobsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        }  
    }

    @FXML
    private void goajouter(ActionEvent event) throws IOException { 
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/add_jobs.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();   
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
        comRestau.setItems(choices);
        
         
    }
}
