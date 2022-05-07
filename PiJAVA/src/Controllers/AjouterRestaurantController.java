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
import Services.RestaurantService;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterRestaurantController implements Initializable {

    @FXML
    private TextField NomRestaurant;

    @FXML
    private TextArea DescRestaurant;

    @FXML
    private TextField Adresse;

    @FXML
    private TextField Capacite;

    @FXML
    private ComboBox<String> Specialite;

    @FXML
    private TextField Numero;

    @FXML
    private Button btn_Ajouter;
    @FXML
    private ListView<String> listBox;
    
@FXML
    void RetourMenuRestaurant(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }
    
    @FXML
    void AjouterRestaurant(ActionEvent event) {
        boolean t =validateCapacite();
         RestaurantService s = new RestaurantService();
         if (NomRestaurant.getText().equals("") )
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ nom du restaurant !");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", NomRestaurant.getText()))){
           JOptionPane.showMessageDialog(null, "Le nom du restaurant doit etre un texte !");}
       else if(DescRestaurant.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ description du restaurant ");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", DescRestaurant.getText()))){
           JOptionPane.showMessageDialog(null, "La description du restaurant doit etre un texte !");
       }
        else if(Adresse.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ Adresse ");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", Adresse.getText()))){
           JOptionPane.showMessageDialog(null, "L'adresse du restaurant doit etre un texte !");
       }
        else if(Capacite.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ numero ");
       }
       
        else if(Numero.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ numero ");
       }
       else if((!( Pattern.matches("[0-9]*", Numero.getText())))&&(Numero.getText().length()==8)){ // number 
           JOptionPane.showMessageDialog(null, "Le doit etre un nombreet doit etre d'une longueur de 8 caractere!");
       }
        else  if(Specialite.getSelectionModel().isEmpty() ){
           JOptionPane.showMessageDialog(null, "Il faut choissir une specialité ");
       }
       
        //} else if ((Valider_Nb(Numero.getText()) != 0) || (Numero.getText().length() != 8)) {
          //      JOptionPane.showMessageDialog(null, "Description doit etre un texte !");}
        else if (t==true){
     String cat = Specialite.getSelectionModel().getSelectedItem();
       s.add(new Restaurant(35,NomRestaurant.getText() , Integer.valueOf(Capacite.getText()),cat , DescRestaurant.getText(),Adresse.getText(),Numero.getText() ));
       JOptionPane.showMessageDialog(null, "Restaurant ajouté avec succés");
       }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Specialite.getItems().add("Tunisien");
        Specialite.getItems().add("Mexicain");
        Specialite.getItems().add("Chinois");
        Specialite.getItems().add("Thailandais");
        Specialite.getItems().add("Italien");
         
        List<ProduitPlat> list = new ProduitPlatService().show();
        
        ArrayList<ProduitPlat> pdtList= new ArrayList<>();
        for(ProduitPlat pdt : list){
            ProduitPlat Ocat = new ProduitPlat();
            Ocat.setId_produitplat(pdt.getId_produitplat());
            Ocat.setNom_produitplat(pdt.getNom_produitplat());
           CheckBox cb1 = new CheckBox();
           cb1.setText(Ocat.getNom_produitplat());
           cb1.setIndeterminate(true);
          
          
          
        }
        
        
        
        //listBox.setCellFactory(value);
        //ObservableList<ProduitPlat> choices = FXCollections.observableArrayList(pdtList);
       //Categorie.setItems(choices);
    
       
       
    }
    
    
             private boolean validateCapacite() {
        System.out.println("test");
        Pattern p = Pattern.compile("^[1-9]$|^[1-9][0-9]$|^(100)$");
        Matcher m = p.matcher(Capacite.getText());
        if(m.find() && m.group().equals(Capacite.getText())){
            return true;
        }else{
        JOptionPane.showMessageDialog(null, "capacité doit etre entre  1 et 100 ");
        return false;            
        }    
    }
    
}
