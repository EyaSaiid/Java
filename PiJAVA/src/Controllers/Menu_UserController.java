/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.SignUpController.fc;
import Entities.User;
import Services.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.time.ZoneId;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pijava.PiJAVA;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class Menu_UserController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Text nom_prenom;
    @FXML
    private Button btn_Gestion_Utilisateurs;
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
    private TableView<User> tablev;
    
    @FXML
    private TableColumn<User, String> col_id;
    @FXML
    private TableColumn<User,String> col_nom;
    @FXML
    private TableColumn<User, String> col_prenom;
    @FXML
    private TableColumn<User, Date> col_date;
    @FXML
    private TableColumn<User, String> col_role;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_sexe;
    @FXML
    private TableColumn<User, Integer >col_tel;
    @FXML
    private TableColumn<User, String> col_iv;
    @FXML
    private TableColumn<User, ImageView> col_image;
    
    ObservableList<User> oblist=FXCollections.observableArrayList();
    @FXML
    private ImageView mimage;
    @FXML
    private TextField mnom;
    @FXML
    private TextField mprenom;
    @FXML
    private TextField memail;
    @FXML
    private Button btnimage;
    @FXML
    private ComboBox<String> comsexe;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField mnum;
    @FXML
    private TextField mmdp;
    @FXML
    private ComboBox<String> comverif;
    @FXML
    private ComboBox<String> comrole;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
    
    File selectedfile;
    String path;
    public static FileChooser fc = new FileChooser();
    
    User userm=new User();
    @FXML
    private Button btnajouter;
    @FXML
    private HBox goentjobs;
    @FXML
    private Button btn_Gestion_jobs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         nom_prenom.setText(PiJAVA.user.getEmail());
        Image im = new Image(this.getClass().getResourceAsStream("/Images/" + PiJAVA.user.getImage_user()));
        photo.setImage(im); 
        //affichage
        UserService us = new UserService();
        List<User> lu=us.afficher_user();
            System.out.println(lu);
        oblist=FXCollections.observableArrayList(lu);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("numero_tele"));
        col_iv.setCellValueFactory(new PropertyValueFactory<>("is_verified"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("photo"));

        
        tablev.setItems(oblist);
        tablev.setOnMouseClicked(e->{
                mod();
            });
        ObservableList<String> combo = FXCollections.observableArrayList("Femme", "Homme");
        comsexe.setItems(combo);
        ObservableList<String> combo2 = FXCollections.observableArrayList("ACTIVE", "BLOCKED");
        comverif.setItems(combo2);
        ObservableList<String> combo3 = FXCollections.observableArrayList("ADMIN","RESTAURANT", "USER");
        comrole.setItems(combo3);
    }    

    private void mod() {
    //System.out.println("mrigla");
    for(User u:tablev.getSelectionModel().getSelectedItems() )
    {    
    UserService us=new UserService();

    User uu= us.afficher_user(u.getId());
    this.userm=uu;
    mnom.setText(uu.getNom());
    mprenom.setText(uu.getPrenom() );
    memail.setText(uu.getEmail() );
    System.out.println(u.getImage_user());
    System.out.println(uu.getImage_user());
    Image im = new Image(this.getClass().getResourceAsStream("/Images/" + uu.getImage_user()));
    mimage.setImage(im); 
    mnum.setText(String.valueOf(uu.getNumero_tele()) );
    tfdate.setValue(uu.getDate().toLocalDate() );
    comsexe.setValue(uu.getSexe() );
    if(uu.getIs_verified()==0){
    comverif.setValue("ACTIVE" );        
    }
    else{
    comverif.setValue("BLOCKED" );        
    }
    
    if(uu.getRoles().equals("[\"ROLE_USER\"]")){
    comrole.setValue("USER" );        
    }
    else if (uu.getRoles().equals("[\"ROLE_ADMIN\"]")){
    comrole.setValue("ADMIN" );        
    }else{
    comrole.setValue("RESTAURANT" );        
    }
    
    }
    }
    
    @FXML
    private void Menu_user(ActionEvent event) {
    }

    @FXML
    private void Menu_Rest(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_BackRestaurant.fxml"));
        Scene gestionViewScene = new Scene(gestionView);
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(gestionViewScene);
        window.show();   
    }

    @FXML
    private void Menu_Prod(ActionEvent event) {
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
    private void image_ajout(ActionEvent event) throws MalformedURLException, FileNotFoundException, IOException {
     // l fc declarith mel fou9 9bal l initialize  public static FileChooser fc = new FileChooser();
        // l selectedfile declarith mel fou9 9bal l initialize File selectedfile
        //l path type mteeha string declariha mel fou9 9bal l initialize 
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
     //System.out.println(System.getProperty("user.home"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            
            path = selectedfile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
           Image img=new Image(selectedfile.toURI().toString());
//           img_pub=new ImageView(img);
           mimage.setImage(img);
           mimage.setFitWidth(100);
           mimage.setPreserveRatio(true);
           mimage.setSmooth(true);
           mimage.setCache(true);
           btnimage.setText(path);
           String dd="C:\\Users\\SAID EYA\\Desktop\\DesktopJAVA\\PiJAVA\\src\\Images\\"+selectedfile.getName()  ;
                File dest = new File(dd);
                try {
                    this.copyFile(selectedfile,dest );
                } catch (IOException ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }

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
    
    @FXML
    private void modifier(ActionEvent event) {
if (path == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Veillez Choisir un Compte User avvec un simple click");
            alert.show();
        }
else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" Dialogue de Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de modifier ce compte ?");
            Optional<ButtonType> action = alert.showAndWait();    
            if (action.get() == ButtonType.OK) {
             
            this.userm.setNom(mnom.getText());
            this.userm.setPrenom(mprenom.getText());
            this.userm.setEmail(memail.getText());
            this.userm.setSexe(comsexe.getSelectionModel().getSelectedItem());
            this.userm.setDate( Date.valueOf(tfdate.getValue()) );
            this.userm.setNumero_tele(Integer.parseInt(mnum.getText()));
            this.userm.setPassword(mmdp.getText());
            if(comverif.getSelectionModel().getSelectedItem().equalsIgnoreCase("ACTIVE") )
            {
                          this.userm.setIs_verified(0);
  
            }else{
                           this.userm.setIs_verified(1);
 
            }
            if(comrole.getSelectionModel().getSelectedItem().equalsIgnoreCase("ADMIN")){
                this.userm.setRoles("[\"ROLE_ADMIN\"]");
            }else if(comrole.getSelectionModel().getSelectedItem().equalsIgnoreCase("RESTAURANT")){
                this.userm.setRoles("[\"ROLE_RESTAURANT\"]");

            }else{
                this.userm.setRoles("[\"ROLE_USER\"]");
            }
            this.userm.setImage_user(path);
                
            UserService us= new UserService();
             us.modifier_user(this.userm,this.userm.getId()); 
             this.load();
             mnom.setText("");
             mprenom.setText("");
             memail.setText("");
             mnum.setText("");
             mmdp.setText("");
             memail.setText("");
             comverif.setValue("etat");
             comsexe.setValue("sexe");
             comrole.setValue("role");
             mimage.setImage(null);
             path=null;
             btnimage.setText("Click here Photo");
             Date.valueOf(tfdate.getValue().toString());
             this.userm=null;
/*tray notification*/
TrayNotification tray = new TrayNotification("Avec succès", "Compte User  a été modifiée avec succés !", SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(4));
             
            }        
   }
    
    }
    
    private void load(){
        oblist.clear();
        //affichage
        UserService us = new UserService();
        List<User> lu=us.afficher_user();
            System.out.println(lu);
        oblist=FXCollections.observableArrayList(lu);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("numero_tele"));
        col_iv.setCellValueFactory(new PropertyValueFactory<>("is_verified"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("photo"));

        
        tablev.setItems(oblist);
        tablev.setOnMouseClicked(e->{
                mod();
            });

    }

    @FXML
    private void supprimer(ActionEvent event) {
    if (this.userm == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Veillez Choisir un Compte User avvec un simple click");
            alert.show();
        }
    else{
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer ce Compte User ?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
             
             UserService us= new UserService();
             us.supprimer_user(this.userm.getId());
             this.load();
             mnom.setText("");
             mprenom.setText("");
             memail.setText("");
             mnum.setText("");
             mmdp.setText("");
             memail.setText("");
             comverif.setValue("etat");
             comsexe.setValue("sexe");
             comrole.setValue("role");
             mimage.setImage(null);
             path=null;
             btnimage.setText("Click here Photo");
             Date.valueOf(tfdate.getValue().toString());
             this.userm=null;

            }   
        
    }
    
    }

    @FXML
    private void goajouter(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/AjouterCompte.fxml"));
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
}
