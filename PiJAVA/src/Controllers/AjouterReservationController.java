/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reservation;
import Entities.Restaurant;
import Services.ReservationService;
import Services.RestaurantService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pijava.PiJAVA;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterReservationController implements Initializable {
  
 @FXML
    private DatePicker Datep;

    @FXML
    private TextField Nombre;

    @FXML
    private Button btn_Ajouter;

    @FXML
    private HBox goacc;

    @FXML
    private HBox rest1;

    @FXML
    private Button rest;

    @FXML
    private Button btnreservation;

    @FXML
    private HBox jobs1;

    @FXML
    private Button jobs;

    @FXML
    private HBox prod1;

    @FXML
    private Button prod;

    @FXML
    private HBox event1;

    @FXML
    private Button event;

    @FXML
    private ImageView photo;

    @FXML
    private Text nom_prenom;

    @FXML
    private Button Logout;

    @FXML
    private ImageView ImgRes;
  
   @FXML
    private ComboBox<Restaurant> Restaurant;

   
     @FXML
    private Button btnrestaurant;

   
   
    @FXML
    void goacc(ActionEvent event){

    }

   
    @FXML
    void ShowBoutique(ActionEvent event) {

    }

    @FXML
    void ShowEvent(ActionEvent event) {

    }

    @FXML
    void goProfile(MouseEvent event)  throws IOException {
         Stage home = new Stage();
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/ProfileUser.fxml"));
                        Scene sc = new Scene(fxml);
                        home.setScene(sc);
                        home.show();

    }

    @FXML
    void goacc(MouseEvent event) throws IOException {
         Stage home = new Stage();
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/Accueil_user2.fxml"));
                        Scene sc = new Scene(fxml);
                        home.setScene(sc);
                        home.show();

    }

    
    @FXML
    void goreservation(ActionEvent event) throws IOException {
        
          Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AjouterReservation.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 

    }

    @FXML
    void gorestaurant(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_Restaurants.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 

    }

    @FXML
    void logout(ActionEvent event) {
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
    private void showJobs(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/acceuil_jobs.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
        
    }


    @FXML
    void AjouterReservation(ActionEvent event) {
        int id=0;
        LocalDateTime now = LocalDateTime.now();  
        Date todayDate = new Date();
         ReservationService pps = new ReservationService();
         boolean t = validateCapacite();
        
         if(Restaurant.getSelectionModel().isEmpty() ){
           JOptionPane.showMessageDialog(null, "Il faut choisir un restaurant ");
       }
         
         if (Nombre.getText().equals("") )
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ Nombre !");
       }
       else if(t!=true){
           JOptionPane.showMessageDialog(null, "le nombre doit etre entre 1 et 100 !");
       }
      
       else{
           Restaurant cat = Restaurant.getSelectionModel().getSelectedItem();
        id=cat.getId_restaurant();

       pps.add(new Reservation(Integer.parseInt(Nombre.getText()),java.sql.Date.valueOf(Datep.getValue().toString()), 34, id ));
       JOptionPane.showMessageDialog(null, "Reservation ajoutée avec succés");
       send();
       }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           nom_prenom.setText(PiJAVA.user.getEmail());
        Image im = new Image(this.getClass().getResourceAsStream("/Images/" + PiJAVA.user.getImage_user()));
        photo.setImage(im);  
        showCats();
         Image image = new Image(this.getClass().getResourceAsStream("/Images/book1.jpg"));
        ImgRes.setImage(image); 
         
        
    }    
       
    
    private void showCats(){
        List<Restaurant> listC = new RestaurantService().show();
        ArrayList<Restaurant> libelles= new ArrayList<>();
        for(Restaurant cat : listC){
            Restaurant Ocat = new Restaurant();
            Ocat.setId_restaurant(cat.getId_restaurant());
            Ocat.setNom_restaurant(cat.getNom_restaurant());
            libelles.add(Ocat);
        }
        ObservableList<Restaurant> choices = FXCollections.observableArrayList(libelles);
       Restaurant.setItems(choices);
    }
    
    
             private boolean validateCapacite() {
        System.out.println("test");
        Pattern p = Pattern.compile("^[1-9]$|^[1-9][0-9]$|^(100)$");
        Matcher m = p.matcher(Nombre.getText());
        if(m.find() && m.group().equals(Nombre.getText())){
            return true;
        }else{
        JOptionPane.showMessageDialog(null, "capacité doit etre entre  1 et 100 ");
        return false;            
        }  
}
             
          
             
             
    private void send() {
        SMS smsc= new SMS();
        smsc.sms("ineshaba", "Ines@hb99", "+21625513910", "reservation ajoutée avec succée");
        JOptionPane.showMessageDialog(null, "SMS envoyé avec succés !");
    }
}