/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.OffreTravail;
import Entities.Restaurant;
import Services.OffreTravailService;
import Services.RestaurantService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pijava.PiJAVA;


/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class Acceuil_jobsController implements Initializable {

    @FXML
    private HBox goacc;
    @FXML
    private Button btnrestaurant;
    @FXML
    private Button btnreservation;
    @FXML
    private TextField search;
    @FXML
    private VBox chosenEntreprise;
    @FXML
    private ScrollPane scroll;
    private MyListener myListener;

    @FXML
    private GridPane grid;
    @FXML
    private Button jobs;
    @FXML
    private ImageView currentuserphoto;
    @FXML
    private Text currentusername;
    @FXML
    private Label OffreNom;
    @FXML
    private ImageView OffreImg;
    @FXML
    private Label o_titre;
    @FXML
    private Label o_desc;
    @FXML
    private Button btnshowoffre;

    public interface MyListener {
    public void onClickListener(OffreTravail offre);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentusername.setText(PiJAVA.user.getEmail());
        Image im = new Image(this.getClass().getResourceAsStream("/Images/" + PiJAVA.user.getImage_user()));
        currentuserphoto.setImage(im); 
        OffreTravailService os = new OffreTravailService();
        List<OffreTravail> lo = os.show();
        if (lo.size() > 0) {
           // setChosenEntreprise(lee.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(OffreTravail offre) {
                    try {
                        setChosenOffre(offre);
                    } catch (SQLException ex) {
                        Logger.getLogger(Acceuil_jobsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < lo.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/Offre_Single.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Offre_SingleController itemController = fxmlLoader.getController();
                itemController.setData(lo.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

 private void setChosenOffre(OffreTravail offre) throws SQLException {
        o_titre.setText(offre.getTitre());
        Restaurant res = new RestaurantService().showByID(offre.getId_restaurant()) ;

        o_desc.setText(res.getNom_restaurant());
       Image image = new Image(this.getClass().getResourceAsStream("/Image/restaurant.png"));
        OffreImg.setImage(image);
        //chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
        //        "    -fx-background-radius: 30;");
        PiJAVA.offreshow=offre  ;
    }     

    @FXML
    private void goacc(MouseEvent event) {
    }

    @FXML
    private void gorestaurant(ActionEvent event) {
    }

    @FXML
    private void goreservation(ActionEvent event) {
    }


    @FXML
    private void showJobs(ActionEvent event) {
    }

    @FXML
    private void goshowoffre(ActionEvent event) {
    }
    
}
