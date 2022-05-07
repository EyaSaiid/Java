/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.ProduitPlat;
import Services.CategorieService;
import Services.ProduitPlatService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterProduitPlatController implements Initializable {

      @FXML
    private TextField Nom_ProduitPlat;

    @FXML
    private TextArea Desc_ProduitPlat;

    @FXML
    private TextField Prix;

    @FXML
    private Button AjouterProduitPlat;

    @FXML
    private ChoiceBox<Categorie> Categorie;
      @FXML
    private Button btn_retour;

    

    @FXML
    void RetourMenuProduitPlat(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduitPlat.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showCats();
    }    
     @FXML
    void AjouterProduitPlat(ActionEvent event) {
       boolean t=validatePrix();
        ProduitPlatService pps = new ProduitPlatService();
         if (Nom_ProduitPlat.getText().equals("") )
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ nom produit plat !");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9]*", Nom_ProduitPlat.getText()))){
           JOptionPane.showMessageDialog(null, "Le nom doit etre un texte !");
       }
       else if(Desc_ProduitPlat.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ description ");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", Desc_ProduitPlat.getText()))){
           JOptionPane.showMessageDialog(null, "La description doit etre un texte !");
       }
        else if(Prix.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ prix ");
       }
      
       else  if(Categorie.getSelectionModel().isEmpty() ){
           JOptionPane.showMessageDialog(null, "Il faut choisir une categorie ");
       }
       else if( t==true){
           Categorie cat = Categorie.getSelectionModel().getSelectedItem();
           int id=cat.getId_categorie(); 
       pps.add(new ProduitPlat(Nom_ProduitPlat.getText(),Float.parseFloat(Prix.getText()), Desc_ProduitPlat.getText(),id ));
       JOptionPane.showMessageDialog(null, "Catégorie ajoutée avec succés");
       }

    }
    
     private void showCats(){
        List<Categorie> listC = new CategorieService().show();
        
        ArrayList<Categorie> libelles= new ArrayList<>();
        for(Categorie cat : listC){
            Categorie Ocat = new Categorie();
            Ocat.setId_categorie(cat.getId_categorie());
            Ocat.setNom_categorie(cat.getNom_categorie());
            libelles.add(Ocat);
        }
        ObservableList<Categorie> choices = FXCollections.observableArrayList(libelles);
       Categorie.setItems(choices);
    }
     
    
       private boolean validatePrix() {
        System.out.println("test");
        Pattern p = Pattern.compile("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
        Matcher m = p.matcher(Prix.getText());
        if(m.find() && m.group().equals(Prix.getText())){
            return true;
        }else{
        JOptionPane.showMessageDialog(null, "prix non valide ");
        return false;            
        }    
    }
    
  
}
