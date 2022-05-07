/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IServiceRestaurant;
import Entities.Categorie;
import Entities.ProduitPlat;
import Entities.Restaurant;
import DataBase.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class CategorieService implements IServiceRestaurant<Categorie>  {
    Connection con;
    Statement stm;
    List<Categorie> list=new ArrayList<>();

    public CategorieService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Categorie t)  {
      try{
        String req = "INSERT INTO `categorie` (`nom_categorie`) VALUES ( '"
                + t.getNom_categorie()+"') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
          } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    @Override
    public void deleteById(int t)  {
        try{
        String req = "DELETE FROM `categorie` WHERE id_categorie="
               + t;
        stm = con.createStatement();
        stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
     @Override
    public void delete(Categorie t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         try {
            stm = con.createStatement();
            String requete = "DELETE FROM categorie WHERE id_categorie=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, t.getId_categorie());
             int row= pst.executeUpdate();
             //int i = t.getId();
           // System.out.println(row+""+i);
        //           int row= pst.executeUpdate();
       //            System.out.println(row);
         if(row>0)
            System.out.println("catégorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
// 2eme methode d'ajout ne fonctionne pas
    @Override
    public void ajouterr(Categorie t)  {
        try{
        String req = "INSERT INTO `categorie` (`nom_categorie`) VALUES (?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getNom_categorie());
        pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           
    }
//Afficher toutes les categories
    @Override
    public List<Categorie> show()  {
        List<Categorie> categories = new ArrayList<Categorie>();
        try{
        String req = "Select * from `categorie`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        //List<Categorie> categories = new ArrayList<Categorie>();
        while(rst.next()){
            
            Categorie p = new Categorie(rst.getInt("id_categorie"),rst.getString("nom_categorie"));
            categories.add(p);
            
        
        }
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return categories;
        
    }
    
     @Override
     public Categorie showByID(int id) {
         
         String req="select * from `categorie` where id_categorie="+id;
         Categorie a=new Categorie();
          
        try {   stm = con.createStatement();
           
                ResultSet rst=stm.executeQuery(req);
                rst.next();
                a.setId_categorie(rst.getInt("id_categorie"));
                a.setNom_categorie(rst.getString("nom_categorie"));
 
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }
   @Override
    public void update(Categorie t , int id) {
       try {
            String requete = "UPDATE categorie SET nom_categorie=? WHERE id_categorie="+id;
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, t.getNom_categorie());
           //pst.setInt(2,t.getId_categorie());
           pst.executeUpdate();
            System.out.println("categorie modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    
    
     
       
               

// Afficher Categorie par restaurant 
     public List<Categorie> getCategorieByRes(int idRes)  {
           List<Categorie> categories = new ArrayList<Categorie>();
        String req =  "SELECT c.nom_categorie  FROM resto_produitplat rp"+
               " JOIN restaurant r ON rp.id_restaurant = r.id_restaurant JOIN produit_plat p ON rp.id_produitplat = p.id_produitplat " +
               " JOIN categorie c ON p.id_categorie=c.id_categorie "+
               " WHERE r.id_restaurant= "+idRes+
               " GROUP BY c.id_categorie";
             try{  stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
      
        while(rst.next()){
           Categorie p = new Categorie(rst.getString("nom_categorie"));
            categories.add(p);
        }
             } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;
    
}
     
     
    
    public int CountCategorie()  {
        List<Categorie> categories = new ArrayList<Categorie>();
          int cu = 0;
        try{
            
           String req = "SELECT COUNT(*) as cu FROM `categorie`";
           stm = con.createStatement();
           ResultSet rs = stm.executeQuery(req);
           System.out.println(rs.toString());
           while (rs.next()) {
                cu = rs.getInt("cu");
            }
           
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            
        return cu;}
    
     public int CountProduitPlat()  {
        List<ProduitPlat> produitplats = new ArrayList<ProduitPlat>();
          int cu = 0;
        try{
            
           String req = "SELECT COUNT(*) as cu FROM `produit_plat`";
           stm = con.createStatement();
           ResultSet rs = stm.executeQuery(req);
           System.out.println(rs.toString());
           while (rs.next()) {
                cu = rs.getInt("cu");
            }
           
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            
        return cu;}
    
          public Categorie rechercher2(String s){
              Categorie cat =new Categorie();
        try{
             String requete="select * from categorie where nom_categorie LIKE ?  ";
            PreparedStatement pst=con.prepareStatement(requete);
            pst.setString(1,s+'%');
            ResultSet rs=pst.executeQuery();
            while(rs.next()){      
           cat.setId_categorie(rs.getInt("id_categorie"));
           cat.setNom_categorie(rs.getString("nom_categorie"));
              }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return cat;
    } 
          
           public HashMap<Integer, String> CountPdtByCat()  {
          HashMap<Integer, String> cats = new HashMap<Integer, String>();
 
        try{
            
           String req = "select count(*) as nbr , c.nom_categorie as nom from produit_plat p join categorie c WHERE p.id_categorie=c.id_categorie GROUP By p.id_categorie";
           stm = con.createStatement();
           ResultSet rs = stm.executeQuery(req);
           System.out.println(rs.toString());
           while (rs.next()) {
             cats.put( rs.getInt("nbr"),rs.getString("nom"));
            }
           
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            
        return cats;}
    
}