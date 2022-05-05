/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.JavaMailResetUtil;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
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
public class SendCodeController implements Initializable {

    @FXML
    private Label lcode;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfcode;
    @FXML
    private Button btnsend;
    @FXML
    private Button btnverif;

    int randomCode;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer_email(ActionEvent event) {
    PiJAVA.reset_mail=tfemail.getText();
    
    try {
                    Random rand = new Random();
                    randomCode = rand.nextInt(999999);
                    JavaMailResetUtil.senMail(tfemail.getText(),randomCode);
                    Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("Reset Mail envoyé avec succès")
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

                } catch (Exception ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
    
    
    }

    @FXML
    private void verifier_code(ActionEvent event) throws IOException {
        if (Integer.valueOf(tfcode.getText()) == randomCode) {
            
             Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Reset.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
        Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("Code vérifié")
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
        else{
            
            Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("Code non vérifié")
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
    }
    

    @FXML
    private void closeApplication(MouseEvent event) {
    }
    
}
