/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.ProduitPlat;
import Entities.Reservation;
import Entities.Restaurant;
import Services.CategorieService;
import Services.ProduitPlatService;
import Services.ReservationService;
import Services.RestaurantService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherReservationController implements Initializable {

    @FXML
    private TableView<Reservation> TableReservation;

    @FXML
    private TableColumn<Reservation, Integer> Col_idReservation;

    @FXML
    private TableColumn<Reservation, Integer> Col_IdRestaurant;

    @FXML
    private TableColumn<Reservation, Integer> Col_Nombre;

    @FXML
    private TableColumn<Reservation, Date> Col_Date;

    @FXML
    private TableColumn<Reservation, Integer> Col_idClient;

    @FXML
    private TableColumn Col_Delete;

    @FXML
    private TableColumn Col_Update;

    @FXML
    private TextField Nombre;
    
    @FXML
    private Label NomResto;
     @FXML
    private Button btn_Menu;
        @FXML
    void goMenuBack(ActionEvent event) throws IOException {
           Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_BackRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();


    }


    //@FXML
   // private ComboBox<Restaurant> Restaurant;

    @FXML
    private DatePicker dateRes;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCats();
        ReservationService sa = new ReservationService();
        ObservableList<Reservation> db=FXCollections.observableArrayList(sa.show());  
        
       
         
      //afficher le conenu de la tableCategorie
      Col_idReservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
      Col_IdRestaurant.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
      Col_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      Col_Date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
      Col_idClient.setCellValueFactory(new PropertyValueFactory<>("id_user"));
           TableReservation.setItems(db);
           
           //POUR SUPPRIMER
      Callback<TableColumn<Reservation,String>, TableCell<Reservation,String>> cellFactory =(param) -> {
          final TableCell<Reservation,String> cell=new TableCell<Reservation,String>(){
        
         @Override
         public void updateItem(String item,boolean empty){
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
               
                final Button deleteButton = new Button("supprimer");
              
                
                 setGraphic(deleteButton);
                 setText(null);
                 
                     deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     Reservation res = getTableView().getItems().get(getIndex());
                    // sa.deleteById(cat.getId_produitplat());
                     sa.delete(res);
                     JOptionPane.showMessageDialog(null, "produitplat supprimée !");
                     
                   
                ObservableList<Reservation> db=FXCollections.observableArrayList(sa.show());  
        
      //afficher le conenu de la tableCategorie
      Col_idReservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
      Col_IdRestaurant.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
      Col_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      Col_Date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
      Col_idClient.setCellValueFactory(new PropertyValueFactory<>("id_user"));
           TableReservation.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_Delete.setCellFactory(cellFactory);
        
       
        
//POUR MODIFIER
        
                     
           TableReservation.setEditable(true);         
          Callback<TableColumn<Reservation,String>, TableCell<Reservation,String>> cellFactory1 =(param) -> {
          final TableCell<Reservation,String> cell=new TableCell<Reservation,String>(){
         
              @Override
         public void updateItem(String item,boolean empty){
             
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
               
                final Button modifButton = new Button("modifier");
                 setGraphic(modifButton);
                 setText(null);
                 modifButton.setOnAction(e -> { 
                   
                   Reservation res = new Reservation();
                   res= TableReservation.getSelectionModel().getSelectedItem();
                    
                    if (res == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un produit");
            alert.show();
                 } else {

                    System.out.println(res);
                    RestaurantService r =new RestaurantService ();
                    Restaurant res2;
                    res2 = r.showByID(res.getId_restaurant());
                    Nombre.setText(String.valueOf(res.getNombre()));
                    NomResto.setText(res2.getNom_restaurant());
                    
                    dateRes.setValue(LocalDate.parse(res.getDate_reservation().toString()));   
                    
                    showCats();
                    
                    }
                    
                 });
             }
            };
          };  
            return cell;   
        };
        
        Col_Update.setCellFactory(cellFactory1);
        
        TableReservation.setItems(db);
        
        
      //  SortedList<Categorie> sortedData=tableViewSearchFilter(db);
       // Table_Categorie.setItems(sortedData);
    }    
    
    @FXML
    private void listeProduitPlat(SortEvent<Reservation> event) {
        ReservationService sa = new ReservationService();
        ObservableList<Reservation> db=FXCollections.observableArrayList(sa.show());       
         Col_idReservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
         Col_IdRestaurant.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
         Col_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
         Col_Date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
         Col_idClient.setCellValueFactory(new PropertyValueFactory<>("id_user"));
         TableReservation.setItems(db);
      
    }

    @FXML
    private void RetourMenuProduitPlat(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduitPlat.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     window.setScene(gestionViewScene);
     window.show();
    }

   

    @FXML
    private void UpdateReservation(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de modifier cette reservation?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            Reservation res = new Reservation();
            res = TableReservation.getSelectionModel().getSelectedItem();
            ReservationService sa= new ReservationService(); 
            //Restaurant cat = Restaurant.getSelectionModel().getSelectedItem();
            int id=res.getId_restaurant();
           
            res.setId_restaurant(id);
            res.setId_user(34);
            res.setNombre(Integer.valueOf(Nombre.getText()));
            res.setDate_reservation(Date.valueOf(dateRes.getValue().toString()));
            System.out.println(res.toString()+"jrjj");
            sa.update(res,res.getId_reservation());
     
            ObservableList<Reservation> db=FXCollections.observableArrayList(sa.show());  
            Col_idReservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
            Col_IdRestaurant.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
            Col_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            Col_Date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
            Col_idClient.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            TableReservation.setItems(db);

    }
            
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
       //Restaurant.setItems(choices);
    }
           
           
           
           
        
    }    
    

