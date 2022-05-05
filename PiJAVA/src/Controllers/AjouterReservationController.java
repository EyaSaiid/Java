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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterReservationController implements Initializable {
  

   

  
   @FXML
    private ComboBox<Restaurant> Restaurant;

    @FXML
    private DatePicker Datep;

    @FXML
    private TextField Nombre;

    @FXML
    private Button btn_Ajouter;
     @FXML
    private Button btnrestaurant;

    @FXML
    private Button btnreservation;
    @FXML
    private ImageView ImgRes;
   
    @FXML
    void goacc(ActionEvent event){

    }

   
    @FXML
    void gorestaurant(ActionEvent event) throws IOException {
 Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_Restaurants.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }
        @FXML
    void goreservation(ActionEvent event) throws IOException {
 Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AjouterReservation.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
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
        showCats();
         Image image = new Image(this.getClass().getResourceAsStream("/Image/book1.jpg"));
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