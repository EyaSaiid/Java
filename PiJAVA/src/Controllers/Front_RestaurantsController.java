/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Restaurant;
import Services.RestaurantService;
import pijava.PiJAVA;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Front_RestaurantsController implements Initializable {
@FXML
    private HBox goacc;

    @FXML
    private HBox rest1;

    @FXML
    private Button rest;

    @FXML
    private Button btnreservation;

    @FXML
    private HBox jobs1;

    @FXML
    private Button jobs;

    @FXML
    private HBox prod1;

    @FXML
    private Button prod;

    @FXML
    private HBox event1;

    @FXML
    private Button event;

    @FXML
    private ImageView photo;

    @FXML
    private Text nom_prenom;

    @FXML
    private Button Logout;

    @FXML
    private TextField search;

    @FXML
    private VBox chosenEntreprise;

    @FXML
    private Label RestaurantNom;

    @FXML
    private ImageView RestaurantImg;

    @FXML
    private Label l_specialite;

    @FXML
    private Label l_numero;

    @FXML
    private Button btnshowentreprise;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;


  
    private MyListener myListener;

   

    @FXML
    void goacc(MouseEvent event) throws IOException {
         Stage home = new Stage();
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/Accueil_user2.fxml"));
                        Scene sc = new Scene(fxml);
                        home.setScene(sc);
                        home.show();

    }
  public interface MyListener {
    public void onClickListener(Restaurant Entreprise);
    }
   
    @FXML
    void ShowBoutique(ActionEvent event) {

    }

    @FXML
    void ShowEvent(ActionEvent event) {

    }

    @FXML
    void goProfile(MouseEvent event)  throws IOException {
         Stage home = new Stage();
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/ProfileUser.fxml"));
                        Scene sc = new Scene(fxml);
                        home.setScene(sc);
                        home.show();

    }

   

    @FXML
    void goreservation(ActionEvent event) throws IOException {
        
          Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AjouterReservation.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 

    }

    @FXML
    void gorestaurant(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_Restaurants.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 

    }

    @FXML
    void logout(ActionEvent event) {
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
    private void showJobs(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/acceuil_jobs.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
        
    }

    @FXML
    void goshowentreprise(ActionEvent event) throws IOException {
     Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_RestaurantDetail.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     window.setScene(gestionViewScene);
     window.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
         nom_prenom.setText(PiJAVA.user.getEmail());
        Image im = new Image(this.getClass().getResourceAsStream("/Images/" + PiJAVA.user.getImage_user()));
        photo.setImage(im);  
        RestaurantService se=new RestaurantService();
        List<Restaurant> lee=se.show();
        search.setOnKeyReleased(e->{ search(); });
  
        //fruits.addAll(getData());
        if (lee.size() > 0) {
           // setChosenEntreprise(lee.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Restaurant resto) {
                    setChosenRestaurant(resto);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < lee.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/Restaurant_Single.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Restaurant_SingleController itemController = fxmlLoader.getController();
                itemController.setData(lee.get(i),myListener);

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
 private void setChosenRestaurant(Restaurant Res) {
        RestaurantNom.setText(Res.getNom_restaurant());
        l_specialite.setText(Res.getSpecialité());
       l_numero.setText(Res.getNum_tel());
       Image image = new Image(this.getClass().getResourceAsStream("/Images/restaurant.png"));
        RestaurantImg.setImage(image);
        //chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
        //        "    -fx-background-radius: 30;");
       PiJAVA.resshow=Res  ;
    } 
 
     private void search() {
    RestaurantService se=new RestaurantService();
    List<Restaurant> lee=se.rechercher2(search.getText());
           search.setOnKeyReleased(e->{ search(); });
    grid.getChildren().clear();

    if (lee.size() > 0) {
            Restaurant e3=new Restaurant();
            //e3.setImage_entreprise2("");
            e3.setSpecialité("");
            e3.setNum_tel("");
            
            setChosenRestaurant(e3);
            myListener = new MyListener() {
                @Override
                public void onClickListener(Restaurant Entreprise) {
                    setChosenRestaurant(Entreprise);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < lee.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/Restaurant_Single.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Restaurant_SingleController itemController = fxmlLoader.getController();
                //System.out.println(lee.get(i));
                itemController.setData(lee.get(i),myListener);

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
    
}
