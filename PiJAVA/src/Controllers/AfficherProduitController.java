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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static jdk.nashorn.tools.Shell.SUCCESS;

/**
 * FXML Controller class
 *
 * @author Nour Hammami
 */
public class AfficherProduitController implements Initializable {

    
    @FXML
    private TableColumn<Produit, Integer> Col_id;
    @FXML
    private TableColumn<Produit, String> Col_NomPrd;
    @FXML
    private TableColumn<Produit, String> Col_DescPrd;
    @FXML
    private TableColumn<Produit, Double> Col_Prix;
    @FXML
    private TableColumn<Produit, Integer> Col_idCategorie;
    @FXML
    private TableColumn Col_Delete;
    @FXML
    private TableColumn Col_Update;
    @FXML
    private TableColumn<Produit, Integer> Col_qte;
    @FXML
    private TextField NomProduit;
    
    @FXML
    private TextField Prix;
    @FXML
    private ComboBox<Category> Categorie;
    @FXML
    private TextField qte;
    
   
    @FXML
    private TextArea Description;
    @FXML
    private TableView<Produit> Table_Produit;
    @FXML
    private TextField searchProduit;
    @FXML
    private Label pont;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showCats();
       ProduitService sa = new ProduitService();
       ObservableList<Produit> db=FXCollections.observableArrayList(sa.afficher1());  
        
       
         
      //afficher le conenu de la tableCategorie
      Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
      Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
      Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
     
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
      Table_Produit.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<Produit,String>, TableCell<Produit,String>> cellFactory =(param) -> {
          final TableCell<Produit,String> cell=new TableCell<Produit,String>(){
          
          
         @Override
         public void updateItem(String item,boolean empty){
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
               
                final Button deleteButton = new Button("supprimer");
              
                
                 setGraphic(deleteButton);
                 setText(null);
                 
                     deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     Produit pdt = getTableView().getItems().get(getIndex());
                    // sa.deleteById(cat.getId_produitplat());
                     sa.DeleteById(pdt);
                     JOptionPane.showMessageDialog(null, "produit supprimé avec succès");
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<Produit> db=FXCollections.observableArrayList(sa.afficher1());  
                   
                        Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                        Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
                        Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
                        Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
                        Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
                        Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
                        Table_Produit.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_Delete.setCellFactory(cellFactory);
        
       
        
//POUR MODIFIER
        
                     Col_NomPrd.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_NomPrd.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomProduit(e.getNewValue());
                     });
                     Table_Produit.setEditable(true);         
          Callback<TableColumn<Produit,String>, TableCell<Produit,String>> cellFactory1 =(param) -> {
          final TableCell<Produit,String> cell=new TableCell<Produit,String>(){
         
              @Override
         public void updateItem(String item,boolean empty){
             
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
               
                final Button modifButton = new Button("modifier");
                 setGraphic(modifButton);
                 setText(null);
                 modifButton.setOnAction(e -> { 
                  
                     //extraire les infos de la ligne selectionnée
                    Produit produit = new Produit();
                     produit= Table_Produit.getSelectionModel().getSelectedItem();
                    
                    if (produit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un produit");
            alert.show();
                 } else {

                    System.out.println(produit);
                    NomProduit.setText(produit.getNomProduit());
                    Description.setText(produit.getDescriptionProduit());
                    Prix.setText(String.valueOf(produit.getPrixProduit()));
                    qte.setText(String.valueOf(produit.getQuantiteProduit()));
                    showCats();
                    
                    
                    
                    
                    }
                     //sa.update(act,act.getId_categorie());
                    // JOptionPane.showMessageDialog(null, "catégorie modifiée !");
                 });
             }
            };
          };  
            return cell;   
        };
        
        Col_Update.setCellFactory(cellFactory1);
        Table_Produit.setItems(db);
        //recherche
          SortedList<Produit> sortedData=tableViewSearchFilter(db);
        Table_Produit.setItems(sortedData); 
        
   
    }    
    
    private void listeProduit(SortEvent<Produit> event) {
        ProduitService sa = new ProduitService();
        ObservableList<Produit> db=FXCollections.observableArrayList(sa.afficher1());  
            
      Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
      Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
      Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
      Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
      
      Table_Produit.setItems(db);
      
    }

    private void RetourMenuProduit(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduit.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

   

    @FXML
    private void UpdateProduit(ActionEvent event) throws WriterException, IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("voulez-vous vraiment modifier ce produit?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            Produit produit = new Produit();
            produit = Table_Produit.getSelectionModel().getSelectedItem();
            ProduitService produitService = new ProduitService();
           // Category cat = Categorie.getSelectionModel().getSelectedItem();
//          int id=cat.getId(); 
            produit.setId_categorie(4);
            produit.setNomProduit(NomProduit.getText());
            produit.setQuantiteProduit(Integer.valueOf(qte.getText()));
            produit.setDescriptionProduit(Description.getText());
            produit.setPrixProduit(Double.valueOf(Prix.getText()));
            produitService.update(produit,produit.getId());
            produitService.qr(produit);
            ObservableList<Produit> db=FXCollections.observableArrayList(produitService.afficher1());  
            Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            Col_DescPrd.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
            Col_NomPrd.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
            Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
            Col_qte.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
            Table_Produit.setItems(db);

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

//    @FXML
//    private void telecharger_pdf(ActionEvent event) {
//   
//    }

    @FXML
    private void telecharger_pdf(ActionEvent event) {
         Produit l=new Produit();
   l.pdf();
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("pdf ajouté avec succès");
    }

    @FXML
    private void imprimer(ActionEvent event) {
        // Création du job d'impression.
            final PrinterJob printerJob = PrinterJob.createPrinterJob();
           
            // Affichage de la boite de dialog de configation de l'impression.    
            if (printerJob.showPrintDialog(Table_Produit.getScene().getWindow())) {
                final JobSettings settings = printerJob.getJobSettings();
                final PageLayout pageLayout = settings.getPageLayout();
                final double pageWidth = pageLayout.getPrintableWidth();
                final double pageHeight = pageLayout.getPrintableHeight();
                System.out.println(pageWidth);
                System.out.println(Printer.getAllPrinters());
                // Mise en page, si nécessaire.
                // Lancement de l'impression.
                if (printerJob.printPage(pageLayout, Table_Produit)) {
                    // Fin de l'impression.
                    printerJob.endJob();
                }
            }        
    }
 //SEARCH
    private SortedList<Produit> tableViewSearchFilter(ObservableList<Produit> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Produit> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
                searchProduit.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(pub -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    return pub.getNomProduit().toLowerCase().contains(lowerCaseFilter); // Filter matches first name.
                    // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Produit> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(Table_Produit.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
    }       

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
    private void ajouterProduit(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjouterProduit.fxml"));
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
    private void btn_retourr(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Acceuil_admin.fxml"));
        Scene gestionViewScene = new Scene(gestionView);

        //les informations du stage
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

        window.setScene(gestionViewScene);
        window.show();
    }

    
    
}
