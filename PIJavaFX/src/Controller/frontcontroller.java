package Controller;

import Entities.Produit;
import Services.ProduitService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class frontcontroller implements Initializable {
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    private MyListener myListener;
    private List<Produit> fruits = new ArrayList<>();
    private Image image;
    @FXML
    private Label DescLabel;
  
   

    private List<Produit> getData() {
        List<Produit> fruits = new ArrayList<>();
        Produit fruit;

//        fruit = new Produit();
//        fruit.setNomProduit("Kiwi");
//        fruit.setPrixProduit(2.99);
//        fruit.setPhoto("/img/kiwi.png");
//        //fruit.setColor("6A7324");
//        fruits.add(fruit);
//        fruit = new Produit();
//        fruit.setNomProduit("Kiwi");
//        fruit.setPrixProduit(2.99);
//        fruit.setPhoto("/img/kiwi.png");
//        //fruit.setColor("6A7324");
//        fruits.add(fruit);
//
//        fruit = new Produit();
//        fruit.setNomProduit("Kiwi");
//        fruit.setPrixProduit(2.99);
//        fruit.setPhoto("/img/kiwi.png");
//        //fruit.setColor("6A7324");
//        fruits.add(fruit);
//
//        fruit = new Produit();
//        fruit.setNomProduit("Kiwi");
//        fruit.setPrixProduit(2.99);
//        fruit.setPhoto("/img/kiwi.png");
//        //fruit.setColor("6A7324");
//        fruits.add(fruit);
//
//         fruit = new Produit();
//        fruit.setNomProduit("Kiwi");
//        fruit.setPrixProduit(2.99);
//        fruit.setPhoto("/img/kiwi.png");
//        //fruit.setColor("6A7324");
//        fruits.add(fruit);
//
//        fruit = new Produit();
//        fruit.setNomProduit("Kiwi");
//        fruit.setPrixProduit(2.99);
//        fruit.setPhoto("/img/kiwi.png");
//        //fruit.setColor("6A7324");
//        fruits.add(fruit);

        return fruits;
    }
 
public interface MyListener {
    public void onClickListener(Produit Entreprise);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService se=new ProduitService();
        List<Produit> lee=se.afficher();
//        search.setOnKeyReleased(e->{ search(); });
  
        fruits.addAll(getData());
        if (lee.size() > 0) {
           // setChosenEntreprise(lee.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosenProduit(produit);
                }
            };
        }
        
        int column = 0;
        int row = 1;
        try {
       
            for (int i = 0; i < lee.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(pijavafx.PIJavaFX.class.getResource("/GUI/test.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Produit_SingleController itemController = fxmlLoader.getController();
                //System.out.println(lee.get(i));
                itemController.setData(lee.get(i), myListener);

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
   
     private void setChosenProduit(Produit Produit) {
        fruitNameLable.setText(Produit.getNomProduit());
        //entreprisePays.setText(Entreprise.getDescriptionProduit());
        fruitPriceLabel.setText(pijavafx.PIJavaFX.CURRENCY +Produit.getPrixProduit());
        DescLabel.setText(Produit.getDescriptionProduit());
         //System.out.println(Produit.getPhoto());
         
        image = new Image(this.getClass().getResourceAsStream("/img/"+Produit.getPhoto()));
       fruitImg.setImage(image);
     //  fruitImg.setImage(new Image("file:" +this.getClass().getName()));
       
    }
}