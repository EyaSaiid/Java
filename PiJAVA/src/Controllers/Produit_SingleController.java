/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.frontcontroller.MyListener;
import Entities.Produit;
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
 * @author unknown
 */
public class Produit_SingleController implements Initializable {

    @FXML
    private Label singleEnom;
    @FXML
    private ImageView singleEimg;
@FXML
    private Label priceLable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produit);
    }
        private Produit produit;
        private MyListener myListener;
    
    public void setData(Produit produit, MyListener myListener) {
        this.produit = produit;
        this.myListener = myListener;
        singleEnom.setText(produit.getNomProduit());
//        priceLable.setText(produit.getDescriptionProduit());
       Image image = new Image(this.getClass().getResourceAsStream("/Images/"+produit.getPhoto()));
        
       singleEimg.setImage(image);
       
        //singleEimg.setImage(new Image("file:" +this.getClass().getName()));
        
    }
    
}
