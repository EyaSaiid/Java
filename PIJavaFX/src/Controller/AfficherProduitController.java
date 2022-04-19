/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Category;
import Entities.Produit;
import Services.CategoryService;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
 * @author Nour Hammami
 */
public class AfficherProduitController implements Initializable {

    
    @FXML
    private TableColumn<Produit, Integer> Col_id;
    @FXML
    private TableColumn<Produit, String> Col_NomPrd;
    @FXML
    private TableColumn<Produit, String> Col_DescPrd;
    @FXML
    private TableColumn<Produit, Double> Col_Prix;
    @FXML
    private TableColumn<Produit, Integer> Col_idCategorie;
    @FXML
    private TableColumn Col_Delete;
    @FXML
    private TableColumn Col_Update;
    @FXML
    private TableColumn<Produit, Integer> Col_qte;
    @FXML
    private TextField NomProduit;
    
    @FXML
    private TextField Prix;
    @FXML
    private ComboBox<Category> Categorie;
    @FXML
    private Button btn_Retour;
    @FXML
    private TextField qte;
    
   
    @FXML
    private TextArea Description;
    @FXML
    private TableView<Produit> Table_Produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showCats();
       ProduitService sa = new ProduitService();
       ObservableList<Produit> db=FXCollections.observableArrayList(sa.afficher1());  
        
       
         
      //afficher le conenu de la tableCategorie
      Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
      Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
      Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
     
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
      Table_Produit.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<Produit,String>, TableCell<Produit,String>> cellFactory =(param) -> {
          final TableCell<Produit,String> cell=new TableCell<Produit,String>(){
          
          
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
                     Produit pdt = getTableView().getItems().get(getIndex());
                    // sa.deleteById(cat.getId_produitplat());
                     sa.DeleteById(pdt);
                     JOptionPane.showMessageDialog(null, "produit supprimé avec succès");
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<Produit> db=FXCollections.observableArrayList(sa.afficher1());  
                   
                        Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                        Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
                        Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
                        Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
                        Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
                        Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
                        Table_Produit.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_Delete.setCellFactory(cellFactory);
        
       
        
//POUR MODIFIER
        
                     Col_NomPrd.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_NomPrd.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomProduit(e.getNewValue());
                     });
                     Table_Produit.setEditable(true);         
          Callback<TableColumn<Produit,String>, TableCell<Produit,String>> cellFactory1 =(param) -> {
          final TableCell<Produit,String> cell=new TableCell<Produit,String>(){
         
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
                    Produit produit = new Produit();
                     produit= Table_Produit.getSelectionModel().getSelectedItem();
                    
                    if (produit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un produit");
            alert.show();
                 } else {

                    System.out.println(produit);
                    NomProduit.setText(produit.getNomProduit());
                    Description.setText(produit.getDescriptionProduit());
                    Prix.setText(String.valueOf(produit.getPrixProduit()));
                    qte.setText(String.valueOf(produit.getQuantiteProduit()));
                    showCats();
                    
                    
                    
                    
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
        Table_Produit.setItems(db);
        
        
   
    }    
    
    private void listeProduit(SortEvent<Produit> event) {
        ProduitService sa = new ProduitService();
        ObservableList<Produit> db=FXCollections.observableArrayList(sa.afficher1());  
            
      Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
      Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
      Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
      Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
      
      Table_Produit.setItems(db);
      
    }

    @FXML
    private void RetourMenuProduit(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduit.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

   

    @FXML
    private void UpdateProduit(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("voulez-vous vraiment modifier cette catégorie?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            Produit produit = new Produit();
            produit = Table_Produit.getSelectionModel().getSelectedItem();
            ProduitService produitService = new ProduitService();
           // Category cat = Categorie.getSelectionModel().getSelectedItem();
//          int id=cat.getId(); 
            produit.setId_categorie(4);
            produit.setNomProduit(NomProduit.getText());
            produit.setQuantiteProduit(Integer.valueOf(qte.getText()));
            produit.setDescriptionProduit(Description.getText());
            produit.setPrixProduit(Double.valueOf(Prix.getText()));
            produitService.update(produit,produit.getId());
            ObservableList<Produit> db=FXCollections.observableArrayList(produitService.afficher1());  
            Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
            Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
            Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
            Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
            Table_Produit.setItems(db);

    }
            
    }
    private void showCats(){
        List<Category> listC = new CategoryService().afficher();
        
        ArrayList<Category> libelles= new ArrayList<>();
        for(Category cat : listC){
            Category Ocat = new Category();
            Ocat.setId(cat.getId());
            Ocat.setLibelle(cat.getLibelle());
            libelles.add(Ocat);
        }
        ObservableList<Category> choices = FXCollections.observableArrayList(libelles);
        Categorie.setItems(choices);
    }
}
