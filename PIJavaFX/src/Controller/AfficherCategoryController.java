/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Category;
import Services.CategoryService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
public class AfficherCategoryController implements Initializable {

    @FXML
    private TableView<Category> Table_Categorie;

    @FXML
    private TableColumn<Category, Integer> Col_idCategorie;

    @FXML
    private TableColumn<Category, String> Col_NomCategorie;

    @FXML
    private TableColumn Col_Update;

    @FXML
    private TableColumn Col_delete;
    @FXML
    private TextField NomCategorie;
    @FXML
    private Label l_NomCategorie;
   @FXML
    private Button btn_update;
    @FXML
    private Button btn_Retour;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategoryService sa = new CategoryService();
        ObservableList<Category> db=FXCollections.observableArrayList(sa.afficher());  
        
       
         
      //afficher le conenu de la tableCategorie
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            Table_Categorie.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<Category,String>, TableCell<Category,String>> cellFactory =(param) -> {
          final TableCell<Category,String> cell=new TableCell<Category,String>(){
          
          
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
                     Category cat = getTableView().getItems().get(getIndex());
                     sa.DeleteById(cat);
                     JOptionPane.showMessageDialog(null, "catégorie supprimée !");
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<Category> db=FXCollections.observableArrayList(sa.afficher());  
                   
                    Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
                    Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                    Table_Categorie.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_delete.setCellFactory(cellFactory);
        
       
        
//POUR MODIFIER
        
                     Col_NomCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_NomCategorie.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setLibelle(e.getNewValue());
                     });
                     Table_Categorie.setEditable(true);         
          Callback<TableColumn<Category,String>, TableCell<Category,String>> cellFactory1 =(param) -> {
          final TableCell<Category,String> cell=new TableCell<Category,String>(){
         
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
                     Category categorie = new Category();
                     categorie = Table_Categorie.getSelectionModel().getSelectedItem();
                     
                     
                    // Categorie act = getTableView().getItems().get(getIndex());
                    if (categorie == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une categorie");
            alert.show();
                 } else {

                    System.out.println(categorie);
                     NomCategorie.setText(categorie.getLibelle());}
                     //sa.update(act,act.getId_categorie());
                    // JOptionPane.showMessageDialog(null, "catégorie modifiée !");
                 });
             }
            };
          };  
            return cell;   
        };
        
        Col_Update.setCellFactory(cellFactory1);
        Table_Categorie.setItems(db);
        
        
      //  SortedList<Categorie> sortedData=tableViewSearchFilter(db);
       // Table_Categorie.setItems(sortedData);
    }    
    
    @FXML
    private void listeCategorie(SortEvent<Category> event) {
        CategoryService sa = new CategoryService();
        ObservableList<Category> db=FXCollections.observableArrayList(sa.afficher());  
        //ObservableList<Categorie> ds=FXCollections.observableArrayList(so.afficher());  
     
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
      
      Table_Categorie.setItems(db);
      
    }

    @FXML
    private void RetourMenuCategorie(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuCategory.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

    @FXML
    private void changeUpdate(ActionEvent event) {
        Category categorie = new Category();
        categorie = Table_Categorie.getSelectionModel().getSelectedItem();
        if (categorie == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une categorie");
            alert.show();
        } else {

            System.out.println(categorie);
            NomCategorie.setVisible(true);
            l_NomCategorie.setVisible(true);
            NomCategorie.setText(categorie.getLibelle());
            
        // refraichir la tableview
            
        }
    }

    @FXML
    private void UpdateCategorie(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de modifier cette catégorie?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            Category categorie = new Category();
            categorie = Table_Categorie.getSelectionModel().getSelectedItem();
            CategoryService categorieService = new CategoryService();
            System.out.println(categorie +"gvbt");
            categorie.setLibelle(NomCategorie.getText());
            
            categorieService.update(categorie,categorie.getId());
            
            ObservableList<Category> db=FXCollections.observableArrayList(categorieService.afficher());  
            Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            Table_Categorie.setItems(db);

    }
            
    }
    
  
    
    
    
}
