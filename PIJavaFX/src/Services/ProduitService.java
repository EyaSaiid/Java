/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Category;
import Entities.Produit;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author macbook
 */
public class ProduitService implements IService<Produit> {

    Connection con;
    Statement stm;

    public ProduitService() {
        con = MyDB.getInstance().getCon();
    }

    public void ajouter(Produit t){
      try {   String req ="INSERT INTO `produit`(`nom_produit`,`description_produit`,`prix`,`quantite_produit`,`categorie_id`,`photo`) VALUES"
                +" ('"+ t.getNomProduit()+ "','"+ t.getDescriptionProduit()+ "','"+ 
                t.getPrixProduit()+"','"
                + t.getQuantiteProduit()+ "','"
               + t.getId_categorie()+ "','"
                + t.getPhoto()+ "')";
       
            stm = con.createStatement();
            stm.executeUpdate(req);
 } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//a corriger:
    @Override
    public void ajouterr(Produit t) throws SQLException {
        String req ="INSERT INTO`produit`(`nom_produit`,'description_produit','prix','quantite_produit','categorie_id','photo')VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getNomProduit());
        pstm.executeUpdate();
           
    }

  
        
        @Override
    public List<Produit> afficher(){
         List<Produit> categorys = new ArrayList<Produit>();
        String req = "SELECT * from `produit`";
        try {
            stm = con.createStatement();
        
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
       while(rst.next()){
            
             Produit p = new Produit(rst.getInt("id"),
                     rst.getString("nom_produit"),
                     rst.getString("description_produit"),
                     rst.getDouble("prix"),
                     rst.getInt("categorie_id"),
                     rst.getInt("quantite_produit"),
                     rst.getString("photo")
                     );
             categorys.add(p);
            
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorys;
        
    }
     
    public List<Produit> afficher1(){
         List<Produit> categorys = new ArrayList<Produit>();
        String req = "SELECT * from `produit`";
        try {
            stm = con.createStatement();
        
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
       while(rst.next()){
            
          Produit p = new Produit(rst.getInt("id"),
                     rst.getString("nom_produit"),
                     rst.getString("description_produit"),
                     rst.getDouble("prix"),
                     rst.getInt("categorie_id"),
                     rst.getInt("quantite_produit")
                     );
             categorys.add(p);
            
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorys;
        
    }
          
   
     public Produit showByID(int id) throws SQLException{
         String req="select * from `produit` where id="+id;
         Produit a=new Produit();
          stm = con.createStatement();
         
        try {
           
                ResultSet rst=stm.executeQuery(req);
                rst.next();
                a.setId(rst.getInt("id"));
                a.setNomProduit(rst.getString("nomProduit"));
 
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }
   public void update(Produit t , int id) {
        try {
            stm = con.createStatement();
           
             String requete1 ="UPDATE `produit` SET `nom_produit`=?,`description_produit`=?,`prix`=?,`quantite_produit`=?,`categorie_id`=?"+ " WHERE id="+id;
            PreparedStatement pst = con.prepareStatement(requete1);
            pst.setInt(5, t.getId_categorie());
            pst.setString(1, t.getNomProduit());
            pst.setDouble(3, t.getPrixProduit());

            pst.setString(2, t.getDescriptionProduit());
            pst.setInt(4, t.getQuantiteProduit());
            pst.executeUpdate();
            System.out.println("produit modifié ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
 
    
        public void DeleteById(Produit t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String requete = "DELETE FROM `produit` WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, t.getId());
             int row= pst.executeUpdate();
           
if(row>0)
            System.out.println("produit supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 public List<Produit> getProduitByCategory(int id)  {
        String req = "Select * from `produit` WHERE categorie_id="+id;
         List<Produit> categories = new ArrayList<Produit>();
        try{
            stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
        while(rst.next()){
           Produit p = new Produit(rst.getInt("id"),
                     rst.getString("nom_produit"),rst.getString("description_produit"),
                     rst.getDouble("prix"),
                     rst.getInt("categorie_id"),
                     rst.getInt("quantite_produit")
                     );
            categories.add(p);
        }
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;
}}

