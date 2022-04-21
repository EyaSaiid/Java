/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.ProduitPlat;
import Services.CategorieService;
import Services.ProduitPlatService;
import DataBase.MyDB;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherProduitPlatController implements Initializable {

    @FXML
    private TableView<ProduitPlat> Table_ProduitPlat;
    @FXML
    private TableColumn<ProduitPlat, Integer> Col_idPdtPlat;
    @FXML
    private TableColumn<ProduitPlat, String> Col_NomPdtPlat;
    @FXML
    private TableColumn<ProduitPlat, String> Col_DescPdtPlat;
    @FXML
    private TableColumn<ProduitPlat, Float> Col_Prix;
    @FXML
    private TableColumn<ProduitPlat, Integer> Col_idCategorie;
    @FXML
    private TableColumn Col_Delete;
    @FXML
    private TableColumn Col_Update;
    @FXML
    private TextField NomPdtPlat;
    @FXML
    private TextArea Desc;
    @FXML
    private TextField Prix;
    @FXML
    private ComboBox<Categorie> Categorie;
    @FXML
    private Button btn_Retour;
     @FXML
    private Pagination pagination;
    ProduitPlatService cs = new ProduitPlatService();
    ObservableList<ProduitPlat> db=FXCollections.observableArrayList(cs.show());


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCats();
       ProduitPlatService sa = new ProduitPlatService();
        ObservableList<ProduitPlat> db=FXCollections.observableArrayList(sa.show());  
        
       
         
      //afficher le conenu de la tableCategorie
         Col_idPdtPlat.setCellValueFactory(new PropertyValueFactory<>("id_produitplat"));
         Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
         Col_DescPdtPlat.setCellValueFactory(new PropertyValueFactory<>("desc_produitplat"));
         Col_NomPdtPlat.setCellValueFactory(new PropertyValueFactory<>("nom_produitplat"));
         Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            Table_ProduitPlat.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<ProduitPlat,String>, TableCell<ProduitPlat,String>> cellFactory =(param) -> {
          final TableCell<ProduitPlat,String> cell=new TableCell<ProduitPlat,String>(){
          
          
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
                     ProduitPlat pdt = getTableView().getItems().get(getIndex());
                    // sa.deleteById(cat.getId_produitplat());
                     sa.delete(pdt);
                     JOptionPane.showMessageDialog(null, "produitplat supprimée !");
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<ProduitPlat> db=FXCollections.observableArrayList(sa.show());  
                   
                        Col_idPdtPlat.setCellValueFactory(new PropertyValueFactory<>("id_produitplat"));
                        Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
                        Col_DescPdtPlat.setCellValueFactory(new PropertyValueFactory<>("desc_produitplat"));
                        Col_NomPdtPlat.setCellValueFactory(new PropertyValueFactory<>("nom_produitplat"));
                        Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                        Table_ProduitPlat.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_Delete.setCellFactory(cellFactory);
        
       
        
//POUR MODIFIER
        
                     Col_NomPdtPlat.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_NomPdtPlat.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom_produitplat(e.getNewValue());
                     });
                     Table_ProduitPlat.setEditable(true);         
          Callback<TableColumn<ProduitPlat,String>, TableCell<ProduitPlat,String>> cellFactory1 =(param) -> {
          final TableCell<ProduitPlat,String> cell=new TableCell<ProduitPlat,String>(){
         
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
                    ProduitPlat produitplat = new ProduitPlat();
                     produitplat= Table_ProduitPlat.getSelectionModel().getSelectedItem();
                    
                    if (produitplat == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un produit");
            alert.show();
                 } else {

                    System.out.println(produitplat);
                    NomPdtPlat.setText(produitplat.getNom_produitplat());
                    Desc.setText(produitplat.getDesc_produitplat());
                    Prix.setText(String.valueOf(produitplat.getPrix()));
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
        Table_ProduitPlat.setItems(db);
          int paginas = 1;
        if (db.size() % filpag() == 0) {
            paginas = db.size() / filpag();
        } else if (db.size() > filpag()) {
            paginas = db.size() / filpag() + 1;
        }
        pagination.setPageCount(paginas);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::createPage);
        
        
      //  SortedList<Categorie> sortedData=tableViewSearchFilter(db);
       // Table_Categorie.setItems(sortedData);
    }    
    
    @FXML
    private void listeProduitPlat(SortEvent<ProduitPlat> event) {
        ProduitPlatService sa = new ProduitPlatService();
        ObservableList<ProduitPlat> db=FXCollections.observableArrayList(sa.show());  
        //ObservableList<Categorie> ds=FXCollections.observableArrayList(so.afficher());  
     
      Col_idPdtPlat.setCellValueFactory(new PropertyValueFactory<>("id_produitplat"));
      Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
      Col_DescPdtPlat.setCellValueFactory(new PropertyValueFactory<>("desc_produitplat"));
      Col_NomPdtPlat.setCellValueFactory(new PropertyValueFactory<>("nom_produitplat"));
      Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
      
      Table_ProduitPlat.setItems(db);
      
    }

    @FXML
    private void RetourMenuProduitPlat(ActionEvent event) throws IOException {
        Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/MenuProduitPlat.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

   

    @FXML
    private void UpdateProduitPlat(ActionEvent event) {
        boolean t = validatePrix();
         ProduitPlatService produitplatService = new ProduitPlatService();
             if (NomPdtPlat.getText().equals("") )
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ nom produit plat !");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9]*", NomPdtPlat.getText()))){
           JOptionPane.showMessageDialog(null, "Le nom doit etre un texte !");
       }
       else if(Desc.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ description ");
       }
       else if(!( Pattern.matches("[a-zA-Z0-9_ ]*", Desc.getText()))){
           JOptionPane.showMessageDialog(null, "La description doit etre un texte !");
       }
        else if(Prix.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Il faut remplir le champ prix ");
       }
      
      else  if(Categorie.getSelectionModel().isEmpty() ){
       JOptionPane.showMessageDialog(null, "Il faut choisir une categorie ");
      }
       else if(t==true) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de modifier cette catégorie?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            ProduitPlat produitplat = new ProduitPlat();
            produitplat = Table_ProduitPlat.getSelectionModel().getSelectedItem();
           
            Categorie cat = Categorie.getSelectionModel().getSelectedItem();
           int id=cat.getId_categorie(); 
            
            
            System.out.println(produitplat +"gvbt");
            
            
            produitplat.setNom_produitplat(NomPdtPlat.getText());
            produitplat.setDesc_produitplat(Desc.getText());
            produitplat.setPrix(Float.valueOf(Prix.getText()));
            produitplat.setId_categorie(id);
            
            produitplatService.update(produitplat,produitplat.getId_produitplat());
            }
            ObservableList<ProduitPlat> db=FXCollections.observableArrayList(produitplatService.show());  
            Col_idPdtPlat.setCellValueFactory(new PropertyValueFactory<>("id_produitplat"));
            Col_idCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            Col_DescPdtPlat.setCellValueFactory(new PropertyValueFactory<>("desc_produitplat"));
            Col_NomPdtPlat.setCellValueFactory(new PropertyValueFactory<>("nom_produitplat"));
            Col_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            Table_ProduitPlat.setItems(db);
  int paginas = 1;
        if (db.size() % filpag() == 0) {
            paginas = db.size() / filpag();
        } else if (db.size() > filpag()) {
            paginas = db.size() / filpag() + 1;
        }
             pagination.setPageCount(paginas);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::createPage);
    }
            
    }
    private void showCats(){
        List<Categorie> listC = new CategorieService().show();
        
        ArrayList<Categorie> libelles= new ArrayList<>();
        for(Categorie cat : listC){
            Categorie Ocat = new Categorie();
            Ocat.setId_categorie(cat.getId_categorie());
            Ocat.setNom_categorie(cat.getNom_categorie());
            libelles.add(Ocat);
        }
        ObservableList<Categorie> choices = FXCollections.observableArrayList(libelles);
       Categorie.setItems(choices);
    }
     private boolean validatePrix() {
        System.out.println("test");
        Pattern p = Pattern.compile("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
        Matcher m = p.matcher(Prix.getText());
        if(m.find() && m.group().equals(Prix.getText())){
            return true;
        }else{
        JOptionPane.showMessageDialog(null, "prix non valide ");
        return false;            
        }    
    }
      public int filpag() {
        return 3;
    }

    private Node createPage(int pagIndex) {

        int fromIndex = pagIndex * filpag();
        int toIndex = Math.min(fromIndex + filpag(), db.size());
        Table_ProduitPlat.setItems(FXCollections.observableArrayList(db.subList(fromIndex, toIndex)));

        return new BorderPane(Table_ProduitPlat);

    }
    
    @FXML
    private void exportExcel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        Connection con = MyDB.getInstance().getCon();
        String query = "Select * from produit_plat";
         PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Détails ProduitPlat");
            HSSFRow header = sheet.createRow(0);
            
            
            
            header.createCell(0).setCellValue("id_produitplat");
           header.createCell(1).setCellValue("id_categorie");
            header.createCell(2).setCellValue("desc_produitplat");
            header.createCell(3).setCellValue("nom_produitplat");
            header.createCell(4).setCellValue("prix");
           
            
            int index = 1;
            while(rs.next()){
                HSSFRow row = sheet.createRow(index);
                
                row.createCell(0).setCellValue(rs.getInt("id_produitplat"));
                row.createCell(1).setCellValue(rs.getInt("id_categorie"));
                row.createCell(2).setCellValue(rs.getString("desc_produitplat"));
                row.createCell(3).setCellValue(rs.getString("nom_produitplat"));
                row.createCell(4).setCellValue(rs.getFloat("prix"));
                index++;
            }
            
            FileOutputStream file = new FileOutputStream("Détails ProduitPlat.xls");
            wb.write(file);
            file.close();
            JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");
            
            pst.close();
            rs.close();
    
    }
}
