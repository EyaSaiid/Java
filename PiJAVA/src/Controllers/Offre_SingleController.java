/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Acceuil_jobsController.MyListener;
import Entities.OffreTravail;
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
 * @author SAID EYA
 */
public class Offre_SingleController implements Initializable {

    @FXML
    private Label singleOtitre;
    @FXML
    private ImageView singleOimg;

    private OffreTravail offre;
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
              myListener.onClickListener(offre);
    }
    
         public void setData(OffreTravail offre, MyListener myListener) {
        this.offre = offre;
        this.myListener = myListener;
        singleOtitre.setText(offre.getTitre());
        Image image = new Image(this.getClass().getResourceAsStream("/Images/jobs.png"));
        singleOimg.setImage(image);            
        
        
    }
}

  
