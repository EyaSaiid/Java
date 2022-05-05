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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pijava.PiJAVA;

/**
 * FXML Controller class
 *
 * @author SAID EYA
 */
public class ResetController implements Initializable {

    @FXML
    private TextField tfnewpass;
    @FXML
    private TextField tfnewpass1;
    @FXML
    private Button btnreset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
    
    public boolean TestPassword(String Pwd1,String Pwd2)
    {
        return Pwd1.equals(Pwd2);
    }

    @FXML
    private void reset(ActionEvent event) {
        String password1 = tfnewpass.getText();
        String password2 = tfnewpass1.getText();
        UserService us = new UserService();
        if (TestPassword(password1, password2))
        {
            
            us.updatePassword(PiJAVA.reset_mail ,password1);
            Stage home = new Stage();
             try{
            Parent fxml =FXMLLoader.load(getClass().getResource("/GUI/SignIn.fxml"));
                    Scene sc = new Scene(fxml);
                home.setScene(sc);
                home.show();
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
            }catch(IOException ex){ex.printStackTrace();}
            
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

    @FXML
    private void closeApplication(MouseEvent event) {
    }
    
}
