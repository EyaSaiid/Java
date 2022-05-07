/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Services.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<Categorie> Table_Categorie;

    @FXML
    private TableColumn<Categorie, Integer> Col_idCategorie;

    @FXML
    private TableColumn<Categorie, String> Col_NomCategorie;

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
    @FXML
    private Pagination pagination;
    CategorieService cs = new CategorieService();
    ObservableList<Categorie> db=FXCollections.observableArrayList(cs.show());
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategorieService sa = new CategorieService();
        ObservableList<Categorie> db=FXCollections.observableArrayList(sa.show());  
         
      //afficher le conenu de la tableCategorie
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
      Table_Categorie.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<Categorie,String>, TableCell<Categorie,String>> cellFactory =(param) -> {
          final TableCell<Categorie,String> cell=new TableCell<Categorie,String>(){
          
          
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
                     Categorie cat = getTableView().getItems().get(getIndex());
                     sa.delete(cat);
                     JOptionPane.showMessageDialog(null, "catégorie supprimée !");
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<Categorie> db=FXCollections.observableArrayList(sa.show());  
                   
                    Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
                    Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
                    Table_Categorie.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_delete.setCellFactory(cellFactory);
        
      
        
        
        
        
       ///////////////////////////////////////////////////////
       
       
       
   
    
    ////////////////////////////////////////////////////////////////////////
        
        
//POUR MODIFIER
        
                     Col_NomCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_NomCategorie.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom_categorie(e.getNewValue());
                     });
                     Table_Categorie.setEditable(true);         
          Callback<TableColumn<Categorie,String>, TableCell<Categorie,String>> cellFactory1 =(param) -> {
          final TableCell<Categorie,String> cell=new TableCell<Categorie,String>(){
         
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
                     Categorie categorie = new Categorie();
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
                     NomCategorie.setText(categorie.getNom_categorie());}
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
       
        int paginas = 1;
        if (db.size() % filpag() == 0) {
            paginas = db.size() / filpag();
        } else if (db.size() > filpag()) {
            paginas = db.size() / filpag() + 1;
        }
        pagination.setPageCount(paginas);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::createPage);
        //Cal.setCellFactory(cellFactory);
       
       
    }    
    
    @FXML
    private void listeCategorie(SortEvent<Categorie> event) {
        CategorieService sa = new CategorieService();
        ObservableList<Categorie> db=FXCollections.observableArrayList(sa.show());  
        //ObservableList<Categorie> ds=FXCollections.observableArrayList(so.afficher());  
     
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
      
      Table_Categorie.setItems(db);
      
    }

    @FXML
    private void RetourMenuCategorie(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuCategorie.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

    @FXML
    private void changeUpdate(ActionEvent event) {
        Categorie categorie = new Categorie();
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
            NomCategorie.setText(categorie.getNom_categorie());
            
        // refraichir la tableview
            
        }
    }

    @FXML
    private void UpdateCategorie(ActionEvent event) {
         CategorieService categorieService = new CategorieService();
             if (NomCategorie.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ categorie !");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", NomCategorie.getText()))){
           JOptionPane.showMessageDialog(null, "Categorie doit etre un texte !");
       }
       else{   
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de modifier cette catégorie?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            Categorie categorie = new Categorie();
            categorie = Table_Categorie.getSelectionModel().getSelectedItem();
           
            System.out.println(categorie +"gvbt");
             
       
            categorie.setNom_categorie(NomCategorie.getText());
            
            categorieService.update(categorie,categorie.getId_categorie());
       }
            
            ObservableList<Categorie> db=FXCollections.observableArrayList(categorieService.show());  
            Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
            Table_Categorie.setItems(db);
             int paginas = 1;
        if (db.size() % filpag() == 0) {
            paginas = db.size() / filpag();
        } else if (db.size() > filpag()) {
            paginas = db.size() / filpag() + 1;
        }
             pagination.setPageCount(paginas);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::createPage);

    }
            
    }
    
    
    
    
     public int filpag() {
        return 3;
    }

    private Node createPage(int pagIndex) {

        int fromIndex = pagIndex * filpag();
        int toIndex = Math.min(fromIndex + filpag(), db.size());
        Table_Categorie.setItems(FXCollections.observableArrayList(db.subList(fromIndex, toIndex)));

        return new BorderPane(Table_Categorie);

    }
  
    
    
    
}
