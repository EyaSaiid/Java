/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Front_RestaurantsController.MyListener;
import Entities.Restaurant;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Restaurant_SingleController implements Initializable {

    @FXML
    private Label singleEnom;

    @FXML
     private ImageView singleEimg;
     private Restaurant resto;
     private MyListener myListener;

    @FXML
    void click(MouseEvent event) {
      myListener.onClickListener(resto);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
     public void setData(Restaurant resto, MyListener myListener) {
        this.resto = resto;
        this.myListener = myListener;
        singleEnom.setText(resto.getNom_restaurant());
        Image image = new Image(this.getClass().getResourceAsStream("/Images/restaurant.png"));
        singleEimg.setImage(image);            
        
        
    }
    
}
