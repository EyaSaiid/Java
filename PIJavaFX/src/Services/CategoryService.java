/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Category;
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
public class CategoryService implements IService<Category> {

    Connection con;
    Statement stm;

    public CategoryService() {
        con = MyDB.getInstance().getCon();
    }

    public void ajouter(Category t)  {
       try { String req = "INSERT INTO`category`(`libelle`)VALUES ('"+ t.getLibelle()+ "' )";
        
            stm = con.createStatement();
        
        stm.executeUpdate(req);
} catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterr(Category t) throws SQLException {
        String req = "INSERT INTO`category`(`libelle`) VALUES (?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getLibelle());
        pstm.executeUpdate();
           
    }

    @Override
    public List<Category> afficher(){
         List<Category> categorys = new ArrayList<Category>();
        String req = "Select * from `category`";
        try {
            stm = con.createStatement();
        
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
        while(rst.next()){
            
            Category p = new Category(rst.getInt(1),rst.getString("libelle"),rst.getString(2));
            categorys.add(p);
            
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorys;
        
    }
       
        public void DeleteById(Category t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String requete = "DELETE FROM `category` WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, t.getId());
             int row= pst.executeUpdate();
           
if(row>0)
            System.out.println("catégorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public Category showByID(int id) throws SQLException{
         String req="select * from `category` where id="+id;
         Category a=new Category();
          stm = con.createStatement();
         
        try {
           
                ResultSet rst=stm.executeQuery(req);
                rst.next();
                a.setId(rst.getInt("id"));
                a.setLibelle(rst.getString("libelle"));
 
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }
    public void update(Category t , int id){
       try {
            String requete = "UPDATE `category` SET libelle=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, t.getLibelle());
            pst.setInt(2,t.getId());
            pst.executeUpdate();
            System.out.println("categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
