/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import pijava.PiJAVA;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private GridPane grid;
    
   
    
    private MyListener myListener;
    private List<Produit> fruits = new ArrayList<>();
    private Image image;
    @FXML
    private Label DescLabel;
  
    private TextField search;
    @FXML
    private Label pont;
    @FXML
    private ScrollPane scroll;
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

    private List<Produit> getData() {
        List<Produit> fruits = new ArrayList<>();
        Produit fruit;

        return fruits;
    }

     private void search() {
    ProduitService se=new ProduitService();
    List<Produit> lee=se.rechercher2(search.getText());
   search.setOnKeyReleased(e->{ search(); });
    grid.getChildren().clear();
    if (lee.size() > 0) {
            Produit e3=new Produit();
            e3.setPhoto("");
            e3.setNomProduit("");
            e3.setPrixProduit(0);
            e3.setDescriptionProduit("");
            setChosenProduit(e3);
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit Produit) {
                    setChosenProduit(Produit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < lee.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/test.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Produit_SingleController itemController = fxmlLoader.getController();
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
    

    @FXML
    private void triNom(ActionEvent event) {
        grid.getChildren().clear();
        ProduitService se=new ProduitService();
        List<Produit> lee=se.trierProduitNomdesc();
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
                fxmlLoader.setLocation( pijava.PiJAVA.class.getResource("/GUI/test.fxml"));
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

    @FXML
    private void TriNomC(ActionEvent event) {
        grid.getChildren().clear();
          ProduitService se=new ProduitService();
        List<Produit> lee=se.trierProduitNomAsc();
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
                fxmlLoader.setLocation( pijava.PiJAVA.class.getResource("/GUI/test.fxml"));
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

    @FXML
    private void triPrixD(ActionEvent event) {
        grid.getChildren().clear();
            ProduitService se=new ProduitService();
        List<Produit> lee=se.trierProduitPrixDsc();
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
                fxmlLoader.setLocation( pijava.PiJAVA.class.getResource("/GUI/test.fxml"));
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

    @FXML
    private void TriPrixA(ActionEvent event) {
      grid.getChildren().clear();
         ProduitService se=new ProduitService();
         GridPane.clearConstraints(grid);
        List<Produit> lee=se.trierProduitPrixAsc();
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
                fxmlLoader.setLocation( pijava.PiJAVA.class.getResource("/GUI/test.fxml"));
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


//    private void connecter(ActionEvent event) {
//         try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AccueilBack.fxml"));
//            Parent root = loader.load();
//            pont.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void produitF(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/frontcontroller.fxml"));
//            Parent root = loader.load();
//          pont.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @FXML
//    private void goacc(MouseEvent event) {
//    }
//
//    @FXML
//    private void gorestaurant(ActionEvent event) throws IOException {
//         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_Restaurants.fxml"));
//        Scene gestionViewScene = new Scene(gestionView);
//
//        //les informations du stage
//        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
//
//        window.setScene(gestionViewScene);
//        window.show(); 
//
//    }
//
//    @FXML
//    private void goreservation(ActionEvent event) throws IOException {
//           Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AjouterReservation.fxml"));
//        Scene gestionViewScene = new Scene(gestionView);
//
//        //les informations du stage
//        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
//
//        window.setScene(gestionViewScene);
//        window.show(); 
//    }
//
//    @FXML
//    private void showJobs(ActionEvent event) throws IOException {
//        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/acceuil_jobs.fxml"));
//        Scene gestionViewScene = new Scene(gestionView);
//
//        //les informations du stage
//        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
//
//        window.setScene(gestionViewScene);
//        window.show(); 
//    }
//
//    @FXML
//    private void ShowBoutique(ActionEvent event) throws IOException {
//           Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/frontcontroller.fxml"));
//        Scene gestionViewScene = new Scene(gestionView);
//
//        //les informations du stage
//        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
//
//        window.setScene(gestionViewScene);
//        window.show(); 
//    }
//    
//
//    @FXML
//    private void ShowEvent(ActionEvent event) {
//    }
//
//    @FXML
//    private void goProfile(MouseEvent event) throws IOException {
//          Stage home = new Stage();
//        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/ProfileUser.fxml"));
//                        Scene sc = new Scene(fxml);
//                        home.setScene(sc);
//                        home.show();
//    }
//
//    @FXML
//    private void logout(ActionEvent event) {
//             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Vous allez quitter l'application");
//        alert.setHeaderText("Vous allez quitter l'application");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            System.exit(0);
//        } else {
//            alert.close();
//        }
//    }

    @FXML
    private void goacc(MouseEvent event) {
    }

    @FXML
    private void gorestaurant(ActionEvent event) throws IOException {
          Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Front_Restaurants.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
    }

    @FXML
    private void goreservation(ActionEvent event) throws IOException {
          Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AjouterReservation.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
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
    private void ShowBoutique(ActionEvent event) throws IOException {
            Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/frontcontroller.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
    }

    @FXML
    private void ShowEvent(ActionEvent event) {
    }

    @FXML
    private void goProfile(MouseEvent event) throws IOException {
            Stage home = new Stage();
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/ProfileUser.fxml"));
                        Scene sc = new Scene(fxml);
                        home.setScene(sc);
                        home.show();
    }

    @FXML
    private void logout(ActionEvent event) {
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

   
    
 
public interface MyListener {
    public void onClickListener(Produit Entreprise);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService se=new ProduitService();
        List<Produit> lee=se.afficher();
//        search.setOnKeyReleased(e->{ search(); });
  
        //fruits.addAll(getData());
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
                fxmlLoader.setLocation( pijava.PiJAVA.class.getResource("/GUI/test.fxml"));
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
        fruitPriceLabel.setText( pijava.PiJAVA.CURRENCY +Produit.getPrixProduit());
        DescLabel.setText(Produit.getDescriptionProduit());
         //System.out.println(Produit.getPhoto());
         
        image = new Image(this.getClass().getResourceAsStream("/Images/"+Produit.getPhoto()));
       fruitImg.setImage(image);
     //  fruitImg.setImage(new Image("file:" +this.getClass().getName()));
       
    }
}