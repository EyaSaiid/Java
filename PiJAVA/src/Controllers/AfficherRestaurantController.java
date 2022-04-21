/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.ProduitPlat;
import Entities.Restaurant;
import Services.CategorieService;
import Services.ProduitPlatService;
import Services.RestaurantService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class AfficherRestaurantController implements Initializable {

     @FXML
    private TableView<Restaurant> TableRestaurant;
    @FXML
    private TableColumn<Restaurant, Integer> Col_IdRes;

    @FXML
    private TableColumn<Restaurant, String> Col_NomRes;

    @FXML
    private TableColumn<Restaurant, String> Col_Desc;

    @FXML
    private TableColumn<Restaurant, Integer> Col_Capacite;

    @FXML
    private TableColumn<Restaurant, Integer> Col_Numero;

    @FXML
    private TableColumn<Restaurant, String> Col_Specialite;

    @FXML
    private TableColumn<Restaurant, String> Col_Adresse;

    @FXML
    private TableColumn<Restaurant, Integer> Co_idUser;

    @FXML
    private TableColumn Col_Delete;

    @FXML
    private TableColumn Col_Update;

    @FXML
    private TextField NomRes;

    @FXML
    private TextField Numero;

    @FXML
    private TextArea Desc;

    @FXML
    private TextField Adresse;

    @FXML
    private TextField Capacite;

    @FXML
    private ComboBox<String> Specialite;

    @FXML
    private Button btn_Modifier;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         RestaurantService sa = new RestaurantService();
        ObservableList<Restaurant> db=FXCollections.observableArrayList(sa.show());  
      //afficher le conenu de la tableCategorie
      Col_IdRes.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
      Col_NomRes.setCellValueFactory(new PropertyValueFactory<>("nom_restaurant"));
      Col_Desc.setCellValueFactory(new PropertyValueFactory<>("desc_restaurant"));
      Col_Capacite.setCellValueFactory(new PropertyValueFactory<>("capacité"));
      Col_Numero.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
      Col_Specialite.setCellValueFactory(new PropertyValueFactory<>("specialité"));
      Col_Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      Co_idUser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
      TableRestaurant.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<Restaurant,String>, TableCell<Restaurant,String>> cellFactory =(param) -> {
          final TableCell<Restaurant,String> cell=new TableCell<Restaurant,String>(){
         
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
                     Restaurant pdt = getTableView().getItems().get(getIndex());
                    // sa.deleteById(cat.getId_produitplat());
                     sa.delete(pdt);
                     JOptionPane.showMessageDialog(null, "produitplat supprimée !");
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<Restaurant> db=FXCollections.observableArrayList(sa.show());  
                   
                        Col_IdRes.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
      Col_NomRes.setCellValueFactory(new PropertyValueFactory<>("nom_restaurant"));
      Col_Desc.setCellValueFactory(new PropertyValueFactory<>("desc_restaurant"));
      Col_Capacite.setCellValueFactory(new PropertyValueFactory<>("capacité"));
      Col_Numero.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
      Col_Specialite.setCellValueFactory(new PropertyValueFactory<>("specialité"));
      Col_Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      Co_idUser.setCellValueFactory(new PropertyValueFactory<>("id_user"));

      

            TableRestaurant.setItems(db);
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_Delete.setCellFactory(cellFactory);
        
       
        
//POUR MODIFIER
        
                     Col_NomRes.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_NomRes.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom_restaurant(e.getNewValue());
                     });
                     TableRestaurant.setEditable(true);         
          Callback<TableColumn<Restaurant,String>, TableCell<Restaurant,String>> cellFactory1 =(param) -> {
          final TableCell<Restaurant,String> cell=new TableCell<Restaurant,String>(){
         
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
                  
                     //extraire les infos de la ligne selectionnée
                    Restaurant produitplat = new Restaurant();
                     produitplat= TableRestaurant.getSelectionModel().getSelectedItem();
                    
                    if (produitplat == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un produit");
            alert.show();
                 } else {

                    System.out.println(produitplat);
                    Desc.setText(produitplat.getDesc_restaurant());
                    NomRes.setText(produitplat.getNom_restaurant());
                    Capacite.setText(String.valueOf(produitplat.getCapacité()));
                    Numero.setText(produitplat.getNum_tel());
                   Adresse.setText(produitplat.getAdresse());
                    Specialite.getItems().add("Tunisien");
                    Specialite.getItems().add("Mexicain");
                    Specialite.getItems().add("Chinois");
                    Specialite.getItems().add("Thailandais");
                    Specialite.getItems().add("Italien");

                    
                    
                    }
                     //sa.update(act,act.getId_categorie());
                    // JOptionPane.showMessageDialog(null, "catégorie modifiée !");
                 });
             }
            };
          };  
            return cell;   
        };
        
        Col_Update.setCellFactory(cellFactory1);
        TableRestaurant.setItems(db);
        
        
      //  SortedList<Categorie> sortedData=tableViewSearchFilter(db);
       // Table_Categorie.setItems(sortedData);
    }    
    
    @FXML
    private void listeProduitPlat(SortEvent<Restaurant> event) {
        RestaurantService sa = new RestaurantService();
        ObservableList<Restaurant> db=FXCollections.observableArrayList(sa.show());  
      Col_IdRes.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
      Col_NomRes.setCellValueFactory(new PropertyValueFactory<>("nom_restaurant"));
      Col_Desc.setCellValueFactory(new PropertyValueFactory<>("desc_restaurant"));
      Col_Capacite.setCellValueFactory(new PropertyValueFactory<>("capacité"));
      Col_Numero.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
      Col_Specialite.setCellValueFactory(new PropertyValueFactory<>("specialité"));
      Col_Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      Co_idUser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
      TableRestaurant.setItems(db);
      
    }

    @FXML
    private void RetourMenuRestaurant(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

   

    @FXML
    private void UpdateRestaurant(ActionEvent event) {
            RestaurantService produitplatService = new RestaurantService();
            boolean t = validateCapacite();
          
             if (NomRes.getText().equals("") )
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ nom du restaurant !");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", NomRes.getText()))){
           JOptionPane.showMessageDialog(null, "Le nom du restaurant doit etre un texte !");}
       else if(Desc.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ description du restaurant ");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", Desc.getText()))){
           JOptionPane.showMessageDialog(null, "La description du restaurant doit etre un texte !");
       }
        else if(Adresse.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ Adresse ");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", Adresse.getText()))){
           JOptionPane.showMessageDialog(null, "L'adresse du restaurant doit etre un texte !");
       }
        else if(Capacite.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ numero ");
       }
       
        else if(Numero.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ numero ");
       }
       else if((!( Pattern.matches("[0-9]*", Numero.getText())))&&(Numero.getText().length()==8)){ // number 
           JOptionPane.showMessageDialog(null, "Le doit etre un nombreet doit etre d'une longueur de 8 caractere!");
       }
        else  if(Specialite.getSelectionModel().isEmpty() ){
           JOptionPane.showMessageDialog(null, "Il faut choissir une specialité ");
       }
        else if(t==true){    
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de modifier cette catégorie?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            Restaurant produitplat = new Restaurant();
            produitplat = TableRestaurant.getSelectionModel().getSelectedItem();
           
            String spe = Specialite.getSelectionModel().getSelectedItem();
          
            System.out.println(produitplat +"gvbt");
            produitplat.setAdresse(Adresse.getText());
            produitplat.setDesc_restaurant(Desc.getText());
            produitplat.setNum_tel(Numero.getText());
            produitplat.setNom_restaurant(NomRes.getText());
            produitplat.setCapacité(Integer.valueOf(Capacite.getText()));
            produitplat.setSpecialité(spe);
           
            produitplatService.update(produitplat,produitplat.getId_restaurant());
            }
            ObservableList<Restaurant> db=FXCollections.observableArrayList(produitplatService.show());  
            Col_IdRes.setCellValueFactory(new PropertyValueFactory<>("id_restaurant"));
      Col_NomRes.setCellValueFactory(new PropertyValueFactory<>("nom_restaurant"));
      Col_Desc.setCellValueFactory(new PropertyValueFactory<>("desc_restaurant"));
      Col_Capacite.setCellValueFactory(new PropertyValueFactory<>("capacité"));
      Col_Numero.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
      Col_Specialite.setCellValueFactory(new PropertyValueFactory<>("specialité"));
      Col_Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      Co_idUser.setCellValueFactory(new PropertyValueFactory<>("id_user"));

      

            TableRestaurant.setItems(db);

    }
            
    }
    
    
                 private boolean validateCapacite() {
        System.out.println("test");
        Pattern p = Pattern.compile("^[1-9]$|^[1-9][0-9]$|^(100)$");
        Matcher m = p.matcher(Capacite.getText());
        if(m.find() && m.group().equals(Capacite.getText())){
            return true;
        }else{
        JOptionPane.showMessageDialog(null, "capacité doit etre entre  1 et 100 ");
        return false;            
        }    
    }
   
     
    
}
