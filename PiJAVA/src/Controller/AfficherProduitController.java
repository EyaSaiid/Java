/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nour Hammami
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<?> Table_Produit;
    @FXML
    private TableColumn<?, ?> Col_id;
    @FXML
    private TableColumn<?, ?> Col_NomPrd;
    @FXML
    private TableColumn<?, ?> Col_DescPrd;
    @FXML
    private TableColumn<?, ?> Col_Prix;
    @FXML
    private TableColumn<?, ?> Col_idCategorie;
    @FXML
    private TableColumn<?, ?> Col_qte;
    @FXML
    private TableColumn<?, ?> Col_Delete;
    @FXML
    private TableColumn<?, ?> Col_Update;
    @FXML
    private TextField NomProduit;
    @FXML
    private TextArea Description;
    @FXML
    private TextField Prix;
    @FXML
    private ComboBox<?> Categorie;
    @FXML
    private Button btn_Retour;
    @FXML
    private TextField qte;
    @FXML
    private TextField searchProduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UpdateProduit(ActionEvent event) {
    }

    @FXML
    private void RetourMenuProduit(ActionEvent event) {
    }

    @FXML
    private void telecharger_pdf(ActionEvent event) {
    }

    @FXML
    private void imprimer(ActionEvent event) {
    }
    
}
