/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijava;

import Entities.Categorie;
import Entities.OffreTravail;
import Entities.ProduitPlat;
import Entities.Restaurant;
import Entities.Produit;
import Entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SAID EYA
 */
public class PiJAVA extends Application {
    public static User user;
    public static OffreTravail offreshow ;  
    public static String reset_mail;
    public static Restaurant resshow ;  
     public static Categorie resshow2 ; 
     public static ProduitPlat resshow3 ;
     public static final String CURRENCY = "TND";
    public static Produit entshow;

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
