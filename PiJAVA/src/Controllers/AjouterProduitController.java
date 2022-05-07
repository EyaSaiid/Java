/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Category;
import Entities.Produit;
import Services.CategoryService;
import Services.ProduitService;
import com.google.zxing.WriterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
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
  
    @FXML
    void RetourMenuProduit(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AfficherProduit.fxml"));
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
    void AjouterProduit(ActionEvent event) throws WriterException, IOException {
        ProduitService pps = new ProduitService();
         if (!checkbox_recaptcha.isSelected()||Nom_Produit.getText().equals("")||Desc_Produit.getText().equals("")||Prix.getText().equals("")||qte.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir tous les champs  !");
           checkbox_recaptcha.setStyle("-fx-border-color:red");
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
       else if(!( Pattern.matches("[a-zA-Z_ ]*", Desc_Produit.getText()))){
           JOptionPane.showMessageDialog(null, "la description doit etre un texte !");
           
       }
      else if(!( Pattern.matches("[0-9_ ]*", qte.getText()))){
           JOptionPane.showMessageDialog(null, "la quantite doit etre un entier positif !");
           
       }
       else if (validatePr()){
           checkbox_recaptcha.setStyle("-fx-border-color:transparent");
          Category cat = Categorie.getSelectionModel().getSelectedItem();
          int id=cat.getId();
           pps.ajouter(new Produit(Nom_Produit.getText(),Desc_Produit.getText(),Double.parseDouble(Prix.getText()),Integer.parseInt(qte.getText()),id,imgname));
           pps.qr(new Produit(Nom_Produit.getText(),Desc_Produit.getText(),Double.parseDouble(Prix.getText()),Integer.parseInt(qte.getText()),id,imgname));
           JOptionPane.showMessageDialog(null, "produit ajouté avec succés");
       }

    }
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