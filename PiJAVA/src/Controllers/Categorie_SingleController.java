/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Front_RestaurantDetailController.MyListener;
import Entities.Categorie;
import Entities.Restaurant;
import pijava.PiJAVA;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Categorie_SingleController implements Initializable {

  
     
    @FXML
    private VBox btn_cat;  
    @FXML
    private Label singleEnom;
    
    private Categorie cat;
     
     private MyListener myListener;

    @FXML
    void click(MouseEvent event) {
      myListener.onClickListener(cat);
   
    }
  /*  @FXML
    void OpenMenu(MouseEvent event ) throws IOException {
       
      
     Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_MenuRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     window.setScene(gestionViewScene);
     window.show();
    }*/
        
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        
    }    
    void setData(Categorie cat ,MyListener myListener) {
        this.cat= cat;
        this.myListener= myListener;
        singleEnom.setText(cat.getNom_categorie());    
        
    }  
  
       
}
