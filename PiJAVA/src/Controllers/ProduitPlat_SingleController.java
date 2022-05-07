/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Front_MenuRestaurantController.MyListener;
import Entities.ProduitPlat;
import pijava.PiJAVA;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
public class ProduitPlat_SingleController implements Initializable {

    /**
     * Initializes the controller class.
     */ 
    @FXML
    private Label singleEnom;

    @FXML
     private ImageView singleEimg;
     private ProduitPlat pdt;
     private MyListener myListener;

    @FXML
    void click(MouseEvent event) {
      myListener.onClickListener(pdt);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void setData(ProduitPlat pdt, MyListener myListener) {
        this.pdt = pdt;
        this.myListener = myListener;
       // singleEnom.setText(Dma9.entshow3.getNom_produitplat());
          singleEnom.setText(pdt.getNom_produitplat());
        Image image = new Image(this.getClass().getResourceAsStream("/Images/food.png"));
        singleEimg.setImage(image);            
        
        
    }
    }    
    

