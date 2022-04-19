/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Category;
import Services.CategoryService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      @FXML
    private void AjouterCategory(ActionEvent event) throws SQLException {
        CategoryService sa = new CategoryService();
       if (nom_category.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ categorie !");
       }
       else if(!( Pattern.matches("[a-zA-Z]*", nom_category.getText()))){
           JOptionPane.showMessageDialog(null, "Categorie doit etre un texte !");
       }
       else{
       sa.ajouter(new Category(nom_category.getText()));
       JOptionPane.showMessageDialog(null, "Categorie ajoutée avec succes");
       
       }
 
    }
     @FXML
    void RetourMenuCategory(ActionEvent event) throws IOException {
        
     Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuCategory.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     window.setScene(gestionViewScene);
     window.show();
    }
    
}
