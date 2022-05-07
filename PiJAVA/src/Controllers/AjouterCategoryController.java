/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Category;
import Services.CategoryService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterCategoryController implements Initializable {
 
     @FXML
    private Button AddCat;

    @FXML
    private TextField nom_category;

    @FXML
    private Label label_nomCat;
    @FXML
    private Button btn_Retour;
    @FXML
    private Label pont;
    @FXML
    private Rectangle rectangle_recaptcha1;
    @FXML
    private CheckBox checkbox_recaptcha;
    @FXML
    private Button btn_Gestion_Utilisateurs;
    @FXML
    private Button btn_Gestion_jobs;
    @FXML
    private Button btn_Gestion_category;
    @FXML
    private HBox goent;
    @FXML
    private Button btn_Gestion_Restarants;
    @FXML
    private HBox gos;
    @FXML
    private Button btn_Gestion_Produit;
    @FXML
    private HBox goevent;
    @FXML
    private Button btn_Gestion_Event;
    @FXML
    private HBox logouttttt;
    @FXML
    private Button btn_Logout;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      @FXML
    private void AjouterCategory(ActionEvent event) throws SQLException {
        CategoryService sa = new CategoryService();
       if (!checkbox_recaptcha.isSelected()||nom_category.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ categorie !");
           checkbox_recaptcha.setStyle("-fx-border-color:red");
       }
       else if(!( Pattern.matches("[a-zA-Z]*", nom_category.getText()))){
           JOptionPane.showMessageDialog(null, "Categorie doit etre un texte !");
       }
       else{
       sa.ajouter(new Category(nom_category.getText()));
       JOptionPane.showMessageDialog(null, "Categorie ajoutée avec succes");
       checkbox_recaptcha.setStyle("-fx-border-color:transparent");
       
       }
 
    }
     @FXML
    void RetourMenuCategory(ActionEvent event) throws IOException {
        
     Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategory.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     window.setScene(gestionViewScene);
     window.show();
    }

  
    private void AccueilB(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AccueilBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void produitB(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AfficherProduit.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void categorieB(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AfficherCategory.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          //  Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Menu_user(ActionEvent event) throws IOException {
             Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_User.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();    
    }

    @FXML
    private void Menu_jobs(ActionEvent event) throws IOException {
           Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_jobs.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();  
    }

    @FXML
    private void Menu_Prods(ActionEvent event) throws IOException {
          Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategory.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }

    @FXML
    private void Menu_Rest(ActionEvent event) {
    }

    @FXML
    private void Menu_Prod(ActionEvent event) throws IOException {
           Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherProduit.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show(); 
    
    }

    @FXML
    private void Logout(ActionEvent event) {
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
    private void btn_retourr(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_admin.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }

    
}