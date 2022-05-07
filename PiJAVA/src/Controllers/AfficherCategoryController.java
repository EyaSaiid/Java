/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import javafx.scene.layout.HBox;
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
    private TextField searchCategory;
    @FXML
    private Label pont;
    @FXML
    private Button btn_Gestion_Utilisateurs;
    @FXML
    private Button btn_Gestion_jobs;
    @FXML
    private Button btn_Gestion_category;
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
     // pour la recherche 
                            SortedList<Category> sortedData=tableViewSearchFilter(db);
                            Table_Categorie.setItems(sortedData);
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
        
        
        SortedList<Category> sortedData=tableViewSearchFilter(db);
        Table_Categorie.setItems(sortedData);
    }    
    
    private void listeCategorie(SortEvent<Category> event) {
        CategoryService sa = new CategoryService();
        ObservableList<Category> db=FXCollections.observableArrayList(sa.afficher());  
        //ObservableList<Categorie> ds=FXCollections.observableArrayList(so.afficher());  
     
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_NomCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
      
      Table_Categorie.setItems(db);
      
    }

    private void RetourMenuCategorie(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuCategory.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

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
    
  
 //SEARCH
    private SortedList<Category> tableViewSearchFilter(ObservableList<Category> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Category> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
                searchCategory.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(pub -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    return pub.getLibelle().toLowerCase().contains(lowerCaseFilter); // Filter matches first name.
                    // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Category> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(Table_Categorie.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
    }    

    private void AccueilB(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AccueilBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void produitB(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AfficherProduit.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void categorieB(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AfficherCategory.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjouterCategory.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void Menu_jobs(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_jobs.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();   
        
    }

    @FXML
    private void Menu_Prods(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategory.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }

    @FXML
    private void Menu_Rest(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherProduit.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
    }

    @FXML
    private void Menu_Prod(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherProduit.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
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
    private void btn_retourr(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_admin.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }
    
    
}