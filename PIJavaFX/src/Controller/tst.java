
package Controller;

import Entities.Produit;
import Services.ProduitService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class tst implements Initializable {

    @FXML
    private Label inscrire;
    @FXML
    private Button btnimage;
    @FXML
    private ImageView img_v;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private ComboBox<String> comsexe;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfmdp;
    @FXML
    private CheckBox ckmdp;
    @FXML
    private Button btngenerer;
    @FXML
    private Label lmdp;
    @FXML
    private Label LabelMessage;
    @FXML
    private Button btninscrire;

    ProduitService us = new ProduitService();
    File selectedfile;
    String path;
    public static FileChooser fc = new FileChooser();
    @FXML
    private ComboBox<String> comrole;
    @FXML
    private Button btnretour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)     {
        // TODO
      
        
        img_v.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
           // Dropping over surface
         img_v.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
//                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedfile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                         img_v.setImage(new Image("file:" +file.getAbsolutePath()));
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                        btnimage.setText(path);
                        System.out.println(file.getName());
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
       
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

private int Valider_Nb(String Ch)//Compter Nombres des lettres
    {
        int Cpt = 0;
        int i = 0;

        for (i = 0; i < Ch.length(); i++) {
            if (Character.toUpperCase(Ch.charAt(i)) >= 'A' && Character.toUpperCase(Ch.charAt(i)) <= 'Z') {
                Cpt++;
            }
        }
        return Cpt;
    }

public boolean Valider_Email(String Email) {
        Pattern P = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher M = P.matcher(Email);
        if (M.find() && M.group().equals(Email)) {
            return true;
        } else {
            tfemail.setStyle("-fx-border-color:Red;");
        }
        return false;
    }

public boolean Valider_MDP(String Mdp) {
        if ((Compter_NB_MAJUS(Mdp) > 0) && (Compter_NB_MINUS(Mdp) > 0) && (Compter_NB_CHIFFRES(Mdp) > 0)) {
            tfmdp.setStyle("-fx-border-color:Green;");
            lmdp.setStyle("-fx-text-fill: Green;");
            lmdp.setText("✓");
            lmdp.setVisible(true);

            return true;
        }

        return false;
    }
public int Compter_NB_MAJUS(String Mdp) {
        int Cpt = 0, i;

        for (i = 0; i < Mdp.length(); i++) {
            if (Character.isUpperCase(Mdp.charAt(i))) {
                Cpt++;
            }

        }
        return Cpt;
    }
public int Compter_NB_MINUS(String Mdp) {
        int Cpt = 0, i;

        for (i = 0; i < Mdp.length(); i++) {
            if (Character.isLowerCase(Mdp.charAt(i))) {
                Cpt++;
            }

        }
        return Cpt;
    }
public int Compter_NB_CHIFFRES(String Mdp) {
        int Cpt = 0, i;

        for (i = 0; i < Mdp.length(); i++) {
            if (Mdp.charAt(i) >= '0' && Mdp.charAt(i) <= '9') {
                Cpt++;
            }
        }
        return Cpt;
    }

//when i click on the button photo to add a pic
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
           img_v.setImage(img);
           img_v.setFitHeight(150);
           img_v.setFitWidth(250);
           btnimage.setText(path);

        }
    
    }

    @FXML
    private void Show_Password(ActionEvent event) {
        if (ckmdp.isSelected()) {

            tfmdp.setPromptText(tfmdp.getText());
            tfmdp.setText("");
            tfmdp.setDisable(true);
        } else {

            tfmdp.setText(tfmdp.getPromptText());
            tfmdp.setPromptText("Password");
            tfmdp.setDisable(false);
        }
    }

    @FXML
    private void generer(ActionEvent event) {
        String ch = Generer_Chaine();
        LabelMessage.setText("Votre Mot de Passe Generer est : " + ch);
        tfmdp.setText(ch);
    }
    
    private String Generer_Chaine()//GENERER UN MDP
    {
        Random rand = new Random();
        String alphabet = "abcd12350123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int longueur = alphabet.length();
        String ch = "";
        for (int i = 0; i < 8; i++) {
            int k = rand.nextInt(longueur);
            ch += alphabet.charAt(k);
        }

        return ch;
    }

    @FXML
    private void adduser(ActionEvent event) {
    }

    
    private void Connexion(MouseEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/SignIn.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_User.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();   
    }
    
}
