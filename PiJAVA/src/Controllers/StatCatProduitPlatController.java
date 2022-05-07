/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class StatCatProduitPlatController implements Initializable {
   @FXML
    private Label l_Tcategorie;

    @FXML
    private Label l_TProduit;
      @FXML
    private Button btn_retour;

    @FXML
    void Retour_MenuBack(ActionEvent event) throws IOException {
     Parent gestionView = FXMLLoader.load(getClass().getResource("/GUI/Menu_BackRestaurant.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();

    }

  
    @FXML
    private PieChart categChart;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<PieChart.Data> pieChartData = null; 
         CategorieService sa = new CategorieService();
       HashMap l = sa.CountPdtByCat();
       Iterator it = l.entrySet().iterator();
         int c1 = sa.CountCategorie();
         int c2 = sa.CountProduitPlat();
         l_Tcategorie.setText(String.valueOf(c1));
         l_TProduit.setText(String.valueOf(c2));
        // statistics for pie charts ----->>>> NEXTT >>>>>>---------
        //----PIE CHART
      
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = (Map.Entry)it.next();
            pieChartData = FXCollections.observableArrayList( 
            new PieChart.Data(entry.getValue(), entry.getKey())
            );

       categChart.getData().addAll(pieChartData);}
        // categChart.setData(pieChartData);
        categChart.setTitle("nombre de produit par categorie");
        categChart.setClockwise(true);
        categChart.setLabelLineLength(50);
        categChart.setLabelsVisible(true);
        categChart.setStartAngle(180);
        
        
        
    }    
    
}
