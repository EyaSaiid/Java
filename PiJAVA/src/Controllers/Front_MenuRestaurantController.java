/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.ProduitPlat;
import Entities.Restaurant;
import Services.ProduitPlatService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entities.ProduitPlat;
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Front_MenuRestaurantController implements Initializable {


    
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
    private Label PlatNom;

    @FXML
    private ImageView PlatImg;

    @FXML
    private Label l_prix;

    @FXML
    private Label l_desc;

    @FXML
    private Button btnshowentreprise;

    @FXML
    private ScrollPane scroll;
    private MyListener myListener;

    @FXML
    private GridPane grid;
    Scene fxmlFile;
    Parent root;
    Stage window;

    @FXML
    void goacc(MouseEvent event) {

    }
  public interface MyListener {
    public void onClickListener(ProduitPlat pdt);
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


   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieService c = new CategorieService();
       Categorie cat= c.rechercher2(PiJAVA.resshow2.getNom_categorie());
        ProduitPlatService se=new ProduitPlatService();
       // l_prix.setText(String.valueOf(Dma9.entshow3.getId_categorie()));
        List<ProduitPlat> lee=se.getProduitPlatByResCat(PiJAVA.resshow.getId_restaurant(),cat.getId_categorie());
        //List<ProduitPlat> lee=se.getProduitPlatByRes(Dma9.entshow.getId_restaurant());
       // search.setOnKeyReleased(e->{ search(); });
      // System.out.println(Dma9.entshow.getId_restaurant());
        //fruits.addAll(getData());
        if (lee.size() > 0) {
           // setChosenEntreprise(lee.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(ProduitPlat pdt) {
                    setChosenProduit(pdt);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < lee.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/ProduitPlat_Single.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProduitPlat_SingleController itemController = fxmlLoader.getController();
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
 private void setChosenProduit(ProduitPlat pdt) {
        PlatNom.setText(pdt.getNom_produitplat());
        l_prix.setText(String.valueOf(pdt.getPrix()));
       l_desc.setText(pdt.getDesc_produitplat());
       Image image = new Image(this.getClass().getResourceAsStream("/Image/food.png"));
        PlatImg.setImage(image);
        //chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
        //        "    -fx-background-radius: 30;");
      //  Dma9.entshow3=pdt;
    } 
 
   /*  private void search() {
    ProduitPlatService se=new ProduitPlatService();
    List<Restaurant> lee=se.rechercher2(search.getText());
           search.setOnKeyReleased(e->{ search(); });
    grid.getChildren().clear();

    if (lee.size() > 0) {
            Restaurant e3=new Restaurant();
            //e3.setImage_entreprise2("");
            e3.setSpecialité("");
            e3.setNum_tel("");
            
            setChosenEntreprise(e3);
            myListener = new MyListener() {
                @Override
                public void onClickListener(Restaurant Entreprise) {
                    setChosenEntreprise(Entreprise);
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
     
}*/
    
}
