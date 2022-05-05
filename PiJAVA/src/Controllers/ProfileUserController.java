/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pijava.PiJAVA;

/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class ProfileUserController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Text nom_prenom;
    @FXML
    private Label rolefield;
    @FXML
    private Label nomfield;
    @FXML
    private Label prenomfield;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Button btnreset;
    
    User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserService us= new UserService();
        u=us.afficher_user(PiJAVA.user.getId());
        nom_prenom.setText(u.getEmail());
        Image im = new Image(this.getClass().getResourceAsStream("/Images/" + u.getImage_user()));
        photo.setImage(im);  
        rolefield.setText(u.getRoles() );
        nomfield.setText(u.getNom() );
        prenomfield.setText(u.getPrenom() );
    }    

    public boolean TestPassword(String Pwd1,String Pwd2)
    {
        return Pwd1.equals(Pwd2);
    }

    
    @FXML
    private void ResetPassword(ActionEvent event) {
    String password11 = password1.getText();
        String password22 = password2.getText();
        UserService us = new UserService();
        if (TestPassword(password11, password22))
        {
            
            us.updatePassword(u.getEmail() ,password11);
            Stage home = new Stage();
            //Parent fxml =FXMLLoader.load(getClass().getResource("/GUI/SignIn.fxml"));
            //  Scene sc = new Scene(fxml);
            //home.setScene(sc);
            //home.show();
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification")
                    .text("Votre mot de passe a été modifié")
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
            
        }
        else {
             Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("Error")
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
        }
    
    }
    
    
}
