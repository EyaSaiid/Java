/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Category;
import Entities.Produit;
import Services.CategoryService;
import Services.ProduitService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nour Hammami
 */
public class AjouterProduitController implements Initializable {

   
    @FXML
    private ChoiceBox<Category> Categorie;
    @FXML
    private Button importerBtn;
    @FXML
    private ImageView image;

    @FXML
    private TextField Prix;
    @FXML
    private Button btn_retour;
    @FXML
    private TextField qte;
    private String path="",imgname="";
    @FXML
    private TextField Nom_Produit;
    @FXML
    private TextArea Desc_Produit;
    @FXML
    private Button AjouterProduit;
  
    @FXML
    void RetourMenuProduit(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduit.fxml"));
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
    void AjouterProduit(ActionEvent event) {
        ProduitService pps = new ProduitService();
         if (Nom_Produit.getText().equals("")||Desc_Produit.getText().equals("")||Prix.getText().equals("")||qte.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir tous les champs  !");
       } 
         else if(Prix.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ prix ");
       }
        else if(qte.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ quantité ");
       }
         
       else  if(Categorie.getSelectionModel().isEmpty() ){
           JOptionPane.showMessageDialog(null, "Il faut choisir une categorie ");
       }
       else if(!( Pattern.matches("[a-zA-Z]*", Nom_Produit.getText()))){
           JOptionPane.showMessageDialog(null, "le nom doit etre un texte !");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", Desc_Produit.getText()))){
           JOptionPane.showMessageDialog(null, "la description doit etre un texte !");
       }
      else if(!( Pattern.matches("[0-9_ ]*", qte.getText()))){
           JOptionPane.showMessageDialog(null, "la quantite doit etre un entier positif !");
       }
       else if (validatePr()){
          Category cat = Categorie.getSelectionModel().getSelectedItem();
          int id=cat.getId(); 
         //pps.ajouter(new Produit(Nom_ProduitPlat.getText(),Prix.getText(), Desc_ProduitPlat.getText(),qte.getText()));
           pps.ajouter(new Produit(Nom_Produit.getText(),Desc_Produit.getText(),Double.parseDouble(Prix.getText()),Integer.parseInt(qte.getText()),id,imgname));
       // pps.add(new Produit(Nom_ProduitPlat.getText(),Double.parseDouble(Prix.getText()),Integer.parseInt(qte.getText()),Desc_ProduitPlat.getText(),id));
           JOptionPane.showMessageDialog(null, "produit ajouté avec succés");
       }

    }
    
  public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        try (
                    FileChannel in = new FileInputStream(sourceFile).getChannel();
                    FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }   
     private void showCats(){
        List<Category> listC = new CategoryService().afficher();
        
        ArrayList<Category> libelles= new ArrayList<>();
        for(Category cat : listC){
            Category Ocat = new Category();
            Ocat.setId(cat.getId());
            Ocat.setLibelle(cat.getLibelle());
            libelles.add(Ocat);
        }
        ObservableList<Category> choices = FXCollections.observableArrayList(libelles);
       Categorie.setItems(choices);
    }

    @FXML
    private void importer_image(ActionEvent event) throws IOException {
      JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(null);
    File f = chooser.getSelectedFile();
    String filename = f.getAbsolutePath();
    path=filename;
    imgname=f.getName();
    Image getAbsolutePath = null;
    
   
    String dd="C:\\Users\\Nour Hammami\\Documents\\NetBeansProjects\\PIJavaFX\\src\\img\\"+f.getName()  ;
    File dest = new File(dd);
    this.copyFile(f,dest);
   
  
   System.out.println(dd);
  
  image.setImage(new Image("file:" +dest.getAbsolutePath()));
 
    }

//      @FXML
//    private void handledragover(DragEvent event) {
//    if(event.getDragboard().hasFiles()){
//        event.acceptTransferModes(TransferMode.COPY);
//    
//    }
//    
//    }
//      @FXML
//    private void handledrop(DragEvent event) throws FileNotFoundException {
//    List<File> files = event.getDragboard().getFiles();
//    Image imgv = new Image(new FileInputStream(files.get(0)));
//    System.out.println(files.get(0).getName());
//    image.setImage(imgv);
//    imgname=files.get(0).getName();
//    
//    }  
     private boolean validatePr() {
 Pattern p = Pattern.compile("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
        Matcher m = p.matcher(Prix.getText());
        if(m.find() && m.group().equals(Prix.getText())){
            return true;
        }else{
                JOptionPane.showMessageDialog(null,"S'il vous plait veuillez saisir un prix valide");
                
               
            
            return false;            
        }    
    }
      private boolean validateimg() {
 Pattern p = Pattern.compile("/\\.(jpe?g|png|gif|bmp)$/i");
        Matcher m = p.matcher(path);
        if(m.find() && m.group().equals(path)){
            return true;
        }else{
                JOptionPane.showMessageDialog(null,"S'il vous plait choisir une img valide");
                
                
            
            return false;            
        }    
}
}