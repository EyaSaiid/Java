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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ListPdtAjoutController implements Initializable {

    @FXML
    private TableView<ProduitPlat> Table_ProduitPlat;

    @FXML
    private TableColumn<ProduitPlat, Integer> Col_idPdtPlat;

    @FXML
    private TableColumn<ProduitPlat, String> Col_NomPdtPlat;

    @FXML
    private TableColumn<ProduitPlat, String> Col_DescPdtPlat;

    @FXML
    private TableColumn<ProduitPlat, Float> Col_Prix;

    @FXML
    private TableColumn<ProduitPlat, Integer> Col_idCategorie;

    @FXML
    private TableColumn<ProduitPlat, CheckBox> Col_Selected;
    @FXML
    private Button btn_Ajouter;
    @FXML
    private ComboBox<Restaurant> Restaurants;
     @FXML
    private Button btn_Retour;

    

    @FXML
    void RetourMenuRestaurant(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }



    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCats();
        ProduitPlatService sa = new ProduitPlatService();
        ObservableList<ProduitPlat> list=FXCollections.observableArrayList(); 
        List<ProduitPlat> listPDT = new ProduitPlatService().show();
       
        int count=list.size();
        for(ProduitPlat cat : listPDT){
            
        CheckBox c = new CheckBox(""+cat.getId_produitplat());
        list.add(new ProduitPlat(cat.getId_produitplat(),cat.getNom_produitplat(),cat.getPrix(),cat.getDesc_produitplat(),c,cat.getId_categorie()));}
     //ObservableList<ProduitPlat> db=FXCollections.observableArrayList(sa.show());  
     Col_idPdtPlat.setCellValueFactory(new PropertyValueFactory<>("id_produitplat"));
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_DescPdtPlat.setCellValueFactory(new PropertyValueFactory<>("desc_produitplat"));
      Col_NomPdtPlat.setCellValueFactory(new PropertyValueFactory<>("nom_produitplat"));
      Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
      Col_Selected.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
      Table_ProduitPlat.setItems(list);
    
        
     
    }
    
    @FXML
    void Ajouter(ActionEvent event) {
         Restaurant res = Restaurants.getSelectionModel().getSelectedItem();
         RestaurantService r = new RestaurantService();
           int id=res.getId_restaurant();
        ArrayList<Integer> ListInt = new ArrayList<Integer>();
        for( int i=0; i< Table_ProduitPlat.getItems().size(); i++)
        { ProduitPlat pdt = new ProduitPlat();
            if(Table_ProduitPlat.getItems().get(i).getCheckbox().isSelected())
            { pdt = Table_ProduitPlat.getItems().get(i);
            ListInt.add(pdt.getId_produitplat());
            System.out.println(ListInt.toString());}
        
        }
        r.add3(id, ListInt);
        

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
       Restaurants.setItems(choices);
    }
    
}
