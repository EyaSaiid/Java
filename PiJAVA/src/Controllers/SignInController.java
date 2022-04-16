/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pijava.PiJAVA;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class SignInController implements Initializable {

    
    @FXML
    private AnchorPane anchorRoot;
    
    
    
    @FXML
    private ImageView img;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfmdp;
    @FXML
    private CheckBox ckmdp;
    @FXML
    private Button btnlogin;
    @FXML
    private Label btngoforgot;
    @FXML
    private Label btngosignup;
    @FXML
    private Label LabelMessage;

    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btnlogin.setOnAction((ActionEvent e) -> {

            String email = tfemail.getText();
            String Pwd = tfmdp.getText();
            Test_Vide(tfemail.getText(), tfmdp.getText());
            System.out.println(email+Pwd);
            System.out.println(us.Verifer_Access(email, Pwd));
            if (us.Verifer_Access(email, Pwd) == true) {
                System.out.println(PiJAVA.user);
                System.out.println(PiJAVA.user.getRoles());
                if (PiJAVA.user.getRoles().equals("[\"ROLE_USER\"]")||PiJAVA.user.getRoles().equals("[\"ROLE_RESTAURANT\"]")) {
                                      System.out.println("gggggggg");
                    Stage home = new Stage();
                    try {
                        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_user.fxml"));
                        Scene sc = new Scene(fxml);
                        home.setScene(sc);
                        home.show();
                      /* Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_user.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) ( btnlogin.getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();*/
                        Notifications notificationBuilder = Notifications.create()
                                .title("Notification")
                                .text("Bienvenue dans notre application")
                                .graphic(null)
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT)
                                .onAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                    }
                                });
                        notificationBuilder.darkStyle();
                        notificationBuilder.showConfirm();
                    } catch (IOException ex) {

                    }

                } else if (PiJAVA.user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                  System.out.println("hhhhhh");
                    Stage home = new Stage();
                    try {
                       /* Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_admin.fxml"));
                        Scene sc = new Scene(fxml);
                        home.setScene(sc);
                        home.show();*/
                         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_admin.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) ( btnlogin.getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
        
                        Notifications notificationBuilder = Notifications.create()
                                .title("Notification")
                                .text("Bienvenue dans notre application")
                                .graphic(null)
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT)
                                .onAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                    }
                                });
                        notificationBuilder.darkStyle();
                        notificationBuilder.showConfirm();
                    } catch (IOException ex) {

                    }
                }

            } else {
                Notifications notificationBuilder = Notifications.create()
                        .title("Notification")
                        .text("Vérifiez vos informations")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            }
                        });
                notificationBuilder.darkStyle();
                notificationBuilder.showError();
                Test_Vide(tfemail.getText(), tfmdp.getText());
            }

        });
    }  
                


    
    public void Test_Vide(String Login, String Pwd) {
        String Vide = "";
        if (Login.isEmpty() == false && Pwd.isEmpty() == false) {
            Vide = "Verification de vos infromations ";
        } else if (Login.isEmpty() == true && Pwd.isEmpty() == false) {
            Vide = "Vous devez Taper votre Propre Email !";
        } else if (Login.isEmpty() == false && Pwd.isEmpty() == true) {
            Vide = "Vous devez Taper votre Mot de Passe !";
        } else if (Login.isEmpty() == true && Pwd.isEmpty() == true) {
            Vide = "Vous devez saisir vos infromations !";
        }

        LabelMessage.setText(Vide);

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
    private void Forgot_Password(MouseEvent event) {
    }


    @FXML
    private void Inscription(MouseEvent event) throws IOException {
       Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/SignUp.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }

    @FXML
    private void closeApplication(MouseEvent event) {
    }

    @FXML
    private void Login(ActionEvent event) {
      /*  try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_admin.fxml"));
           Scene scene = btnlogin.getScene();
        root.translateYProperty().set(scene.getHeight());

        anchorRoot.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(2), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            anchorRoot.getChildren().remove(anchorRoot);
        });
        timeline.play();
            
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    
}
