/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IRestaurantService;
import Entities.Restaurant;
import DataBase.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SAID EYA
 */
public class RestaurantService implements IRestaurantService<Restaurant>  {
    Connection con;
    Statement stm;
     List<Restaurant> list=new ArrayList<>();

    public RestaurantService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Restaurant t) throws SQLException {
        String req ="INSERT INTO `restaurant`(`nom_restaurant`, `desc_restaurant`, `capacite`, `num_tel`, `adresse`, `specialite`, `user_id`) VALUES"
                + " ('"+t.getNom_restaurant()+"','"+ t.getDesc_restaurant()+"','"
                + t.getCapacité()+"','"+t.getNum_tel()+"','"+ t.getAdresse()+"','"+ t.getSpecialité()+"','"+ t.getId_user()+"') ";
        
        stm = con.createStatement();
        //int n =stm.executeUpdate(req);

    }
    
      public void add2(Restaurant t, ArrayList<Integer> l) throws SQLException {
        int cu=0;
        String req ="INSERT INTO `restaurant`(`nom_restaurant`, `desc_restaurant`, `capacite`, `num_tel`, `adresse`, `specialite`, `user_id`) VALUES"
                + " ('"+t.getNom_restaurant()+"','"+ t.getDesc_restaurant()+"','"
                + t.getCapacité()+"','"+t.getNum_tel()+"','"+ t.getAdresse()+"','"+ t.getSpecialité()+"','"+ t.getId_user()+"')";
        stm = con.createStatement();
        stm.executeUpdate(req);
     String r="SELECT MAX(id_restaurant) AS cu FROM restaurant";
      stm = con.createStatement();
           ResultSet rs = stm.executeQuery(r);
           while (rs.next()) {
                cu = rs.getInt("cu");   
            }
             System.out.println(cu);
       int length=l.size();
       for(Integer pdt : l){
       String req2="INSERT INTO `resto_produitplat`(`id_produitplat`, `id_restaurant`) VALUES ('"+pdt+"','"+cu+"') ";
    stm.executeUpdate(req2);
               }

    }
    

        
    @Override
    public void deleteById(int t) throws SQLException {
        String req = "DELETE FROM `restaurant` WHERE id_restaurant="
                + t;
        stm = con.createStatement();
        stm.executeUpdate(req);

    }
        @Override
            public void delete(Restaurant t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 try {
                    stm = con.createStatement();
                    String requete = "DELETE FROM restaurant WHERE id_restaurant=?";
                    PreparedStatement pst = con.prepareStatement(requete);
                    pst.setInt(1, t.getId_restaurant());
                     int row= pst.executeUpdate();
                    
        if(row>0)
                    System.out.println("formation supprimée !");

                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            
            
    @Override
   public List<Restaurant> show()  {
         List<Restaurant> restaurants = new ArrayList<Restaurant>();
        try{ 
            String req = "Select * from restaurant";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
       
        while(rst.next()){
            Restaurant p = new Restaurant(rst.getInt("id_restaurant"),rst.getInt("user_id"),rst.getString("nom_restaurant"),rst.getInt("capacite"),rst.getString("specialite"),rst.getString("desc_restaurant"),
                  rst.getString("adresse"),rst.getString("num_tel"));
          restaurants.add(p);
           
        }
         } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
        return restaurants;
        
    }
    
    
    @Override
     public Restaurant showByID(int id)throws SQLException{
         String req="select * from `restaurant` where id_restaurant="+id;
         Restaurant  a=new Restaurant ();
          stm = con.createStatement();
        try { 
            ResultSet rst=stm.executeQuery(req);
                rst.next();
                a.setNom_restaurant(rst.getString("nom_restaurant"));
                a.setCapacité(rst.getInt("capacite"));
                a.setDesc_restaurant(rst.getString("desc_restaurant"));
                a.setNum_tel(rst.getString("num_tel"));
                a.setAdresse(rst.getString("adresse"));
                a.setSpecialité(rst.getString("specialite"));
                a.setId_user(rst.getInt("user_id"));
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }
     

    @Override
    public void ajouterr(Restaurant t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public void update(Restaurant t , int id)throws SQLException {
        try {
            stm = con.createStatement();
            String requete = "UPDATE restaurant SET nom_restaurant=? ,  desc_restaurant=?, capacite=? "
            + ", num_tel=? ,adresse=? , specialite=? , user_id=? WHERE id_restaurant="+id;
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, t.getNom_restaurant());
            pst.setString(2, t.getDesc_restaurant());
            pst.setInt(3, t.getCapacité());
            pst.setString(4, t.getNum_tel());
            pst.setString(5, t.getAdresse());
            pst.setString(6, t.getSpecialité());
            //pst.setInt(7, t.getId_restaurant());
            pst.setInt(7, t.getId_user());
            pst.executeUpdate();
            System.out.println("update modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //rechercher restaurant
      public List<Restaurant> rechercher2(String s)throws SQLException {
        try{
             String requete="select * from restaurant where nom_restaurant LIKE ?  ";
            PreparedStatement pst=con.prepareStatement(requete);
            pst.setString(1,s+'%');
            ResultSet rs=pst.executeQuery();
            while(rs.next()){      
            list.add(new Restaurant(rs.getInt("id_restaurant"),rs.getInt("user_id"),
             rs.getString("nom_restaurant"), rs.getInt("capacite"),
             rs.getString("specialite"), rs.getString("desc_restaurant"),rs.getString("adresse"),rs.getString("num_tel")));            
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
    } 

    
}