/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IServiceRestaurant;
import Entities.Categorie;
import Entities.ProduitPlat;
import DataBase.MyDB;
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
 * @author dell
 */
public class ProduitPlatService implements IServiceRestaurant<ProduitPlat>{
    Connection con;
    Statement stm;

    public ProduitPlatService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(ProduitPlat t) {
        try{
        String req = "INSERT INTO `produit_plat`(`id_categorie`, `nom_produitplat`, `prix`, `desc_produitplat`) VALUES"
         + " ('"+t.getId_categorie()+"','"+ t.getNom_produitplat()+"','"+ t.getPrix()+"','"+t.getDesc_produitplat()+"') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
          
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    @Override
    public void deleteById(int t)  {
        try{
        String req = "DELETE FROM `produit_plat` WHERE id_produitplat="
               + t;
        stm = con.createStatement();
        stm.executeUpdate(req);
          } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    @Override
    public void delete(ProduitPlat t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         try {
            stm = con.createStatement();
            String requete = "DELETE FROM `produit_plat` WHERE id_produitplat="+t.getId_produitplat();
            PreparedStatement pst = con.prepareStatement(requete);
            //pst.setInt(1, t.getId_produitplat());
             int row= pst.executeUpdate();
             
            if(row>0)
            System.out.println("Plat ou boisson supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void ajouterr(ProduitPlat t)  {
       try{
        String req = "INSERT INTO `produit_plat`(`id_categorie`, `nom_produitplat`, `prix`, `desc_produitplat`) VALUES ('?','?','?','?')";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, t.getId_categorie());
        pstm.setString(2, t.getNom_produitplat());
        pstm.setFloat(3, t.getPrix());
        pstm.setString(5, t.getDesc_produitplat());
        pstm.executeUpdate();
          } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           
    }

    @Override
    public List<ProduitPlat> show()  {
        List<ProduitPlat> categories = new ArrayList<ProduitPlat>();
          String req = "Select * from `produit_plat`";
        try{
      
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        
        while(rst.next()){
           ProduitPlat p = new ProduitPlat(rst.getInt("id_produitplat"),rst.getString("nom_produitplat"),rst.getFloat("prix"),rst.getString("desc_produitplat"),rst.getInt("id_categorie"));
            categories.add(p);
              } 
        
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;
        
    }
     @Override
     public ProduitPlat showByID(int id){
         String req="select * from `produit_plat` where id_produitplat="+id;
         ProduitPlat a=new ProduitPlat();
         
        try { 
             stm = con.createStatement();
            ResultSet rst=stm.executeQuery(req);
           // while(rs.next()){
                rst.next();
                a.setId_produitplat(rst.getInt("id_produitplat"));
                a.setNom_produitplat(rst.getString("nom_produitplat"));
                a.setPrix(rst.getFloat("prix"));
                a.setDesc_produitplat(rst.getString("desc_produitplat"));
                a.setId_categorie(rst.getInt("id_categorie"));
                

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }
     
     @Override
    public void update(ProduitPlat t , int id) {
        try {
            stm = con.createStatement();
            String requete = "UPDATE `produit_plat` SET id_categorie=? , nom_produitplat=?  , prix=? , desc_produitplat=? "
                    + "WHERE id_produitplat="+id;
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, t.getId_categorie());
            pst.setString(2, t.getNom_produitplat());
            pst.setFloat(3, t.getPrix());
           // pst.setInt(4, t.getId_produitplat());
            pst.setString(4, t.getDesc_produitplat());
            pst.executeUpdate();
            System.out.println("plat ou boisson modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    // Afficher ProduitPlat par categorie
    
     public List<ProduitPlat> getProduitPlatByCat(int id)  {
        String req = "Select * from `produit_plat` WHERE id_categorie="+id;
         List<ProduitPlat> categories = new ArrayList<ProduitPlat>();
        try{
            stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
        while(rst.next()){
           ProduitPlat p = new ProduitPlat(rst.getString("nom_produitplat"),rst.getFloat(3),rst.getString("desc_produitplat"),rst.getInt(1));
            categories.add(p);
        }
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;
    
    
}
     
      // Afficher ProduitPlat par restaurant
     public List<ProduitPlat> getProduitPlatByRes(int id) {
             List<ProduitPlat> categories = new ArrayList<ProduitPlat>();
        String req = "SELECT p.nom_produitplat, p.prix, p.desc_produitplat  FROM resto_produitplat rp "
                + "JOIN restaurant r ON rp.id_restaurant = r.id_restaurant "
                + "JOIN produit_plat p ON rp.id_produitplat = p.id_produitplat WHERE r.id_restaurant="+id;
       try{
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
    
        while(rst.next()){
           ProduitPlat p = new ProduitPlat(rst.getString("nom_produitplat"),rst.getFloat("prix"),rst.getString("desc_produitplat"));
            categories.add(p);
        }
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;
    
    
}
      // Afficher ProduitPlat par restaurant et categorie
  /*   public List<ProduitPlat> getProduitPlatByResCat(int idRes, int idCat)  {
        String req = "SELECT p.nom_produitplat, p.prix, p.desc_produitplat  FROM resto_produitplat rp "
                + "JOIN restaurant r ON rp.id_restaurant = r.id_restaurant "
                + "JOIN produit_plat p ON rp.id_produitplat = p.id_produitplat WHERE r.id_restaurant="+idRes+" AND p.id_categorie="+idCat;
        List<ProduitPlat> categories = new ArrayList<ProduitPlat>();
        try{
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
        while(rst.next()){
           ProduitPlat p = new ProduitPlat(rst.getString("nom_produitplat"),rst.getFloat("prix"),rst.getString("desc_produitplat"));
            categories.add(p);
        }
          } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;      
        
}*/
     
          public List<ProduitPlat> getProduitPlatByResCat(int idRes, int idCat)  {
        String req = "SELECT p.nom_produitplat, p.prix, p.desc_produitplat  FROM resto_produitplat rp "
                + "JOIN restaurant r ON rp.id_restaurant = r.id_restaurant "
                + "JOIN produit_plat p ON rp.id_produitplat = p.id_produitplat"
                +" JOIN categorie c ON p.id_categorie=c.id_categorie"
                +" WHERE r.id_restaurant="+idRes+" AND c.id_categorie="+idCat;
        List<ProduitPlat> categories = new ArrayList<ProduitPlat>();
        try{
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
        while(rst.next()){
           ProduitPlat p = new ProduitPlat(rst.getString("nom_produitplat"),rst.getFloat("prix"),rst.getString("desc_produitplat"));
            categories.add(p);
        }
          } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;      
        
}
    
    
    
    
    
}
