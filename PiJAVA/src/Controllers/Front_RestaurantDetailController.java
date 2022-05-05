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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pijava.PiJAVA;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class Front_RestaurantDetailController implements Initializable {
    
    @FXML
    private Label Desc;

    @FXML
    private Label NomRes;

    @FXML
    private ImageView Img;

    @FXML
    private Label Specialite;
     @FXML
    private Button btnrestaurant;
     @FXML
    private Button btnreservation;


    @FXML
    private Label Numero;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private MyListener myListener;
   
    Scene fxmlFile;
    Parent root;
    Stage window;
     @FXML
    private Button btn_show;
     
     
      @FXML
    void goacc(ActionEvent event)  {

    }
     @FXML
    void gorestaurant(ActionEvent event) throws IOException {
 Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_Restaurants.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }
        @FXML
    void goreservation(ActionEvent event) throws IOException {
 Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AjouterReservation.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

    @FXML
    void show(MouseEvent event) throws IOException {
     Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_MenuRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     window.setScene(gestionViewScene);
     window.show();

    }
   
      public interface MyListener {
    public void onClickListener(Categorie cat);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       NomRes.setText(PiJAVA.resshow.getNom_restaurant());
       Specialite.setText(PiJAVA.resshow.getSpecialité());
       Desc.setText(PiJAVA.resshow.getDesc_restaurant());
       Numero.setText(PiJAVA.resshow.getNum_tel());

        Image image = new Image(this.getClass().getResourceAsStream("/Images/restaurant.png"));
        Img.setImage(image);
        CategorieService c =new CategorieService();
        List<Categorie> lee=c.getCategorieByRes(PiJAVA.resshow.getId_restaurant());
            
        
         if (lee.size() > 0) {
           // setChosenEntreprise(lee.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Categorie cat) {
                    System.out.println(cat.toString());
                    setChosenCategorie(cat);
                }
            };
        }
       int column = 0;
        int row = 1;
        
        try {
            for (int i = 0; i < lee.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/Categorie_Single.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Categorie_SingleController itemController = fxmlLoader.getController();
                
               itemController.setData(lee.get(i), myListener);
              
                if (column == 3) {
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
     private void setChosenCategorie(Categorie cat) {
         System.out.println(cat.toString());
      PiJAVA.resshow2=cat;
        
    } 
    
}
