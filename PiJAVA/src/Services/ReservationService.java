/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IServiceRestaurant;
import Entities.Categorie;
import Entities.ProduitPlat;
import java.sql.Date;
import Entities.Reservation;
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
public class ReservationService implements IServiceRestaurant<Reservation>{
     Connection con;
    Statement stm;

    public ReservationService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Reservation t)  {
         try{
        String req ="INSERT INTO `reservation`(`id_restaurant`, `nombre`, `date_reservation`, `user_id`) VALUES"
        + " ('"+t.getId_restaurant()+"','"+ t.getNombre()+"','"+t.getDate_reservation().toString()+"','"+t.getId_user()+"') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void ajouterr(Reservation t) {
       try{
        String req = "INSERT INTO `categorie` (`nom_categorie`) VALUES (?)";
        PreparedStatement pstm = con.prepareStatement(req);
        //pstm.setString(1, t.getNom_categorie());
        pstm.executeUpdate();
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           
    }
    
    @Override
    public void deleteById(int t) {
        try{
        String req = "DELETE FROM `reservation` WHERE id_reservation="
                + t;
        stm = con.createStatement();
        stm.executeUpdate(req);
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    @Override
    public void delete(Reservation t) {
         try {
            stm = con.createStatement();
            String requete = "DELETE FROM reservation WHERE id_reservation=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, t.getId_reservation());
             int row= pst.executeUpdate();
             if(row>0)
            System.out.println("reservation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> show()  {
        String req = "Select * from `reservation`";
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{ stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
      
        while(rst.next()){

            Reservation p = new Reservation(rst.getInt("id_reservation"),rst.getInt("nombre"),rst.getDate("date_reservation"),rst.getInt(6),rst.getInt(1));
            reservations.add(p);
        
        }
        
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reservations;
        
    }
     @Override
     public Reservation showByID(int id){
         String req="select * from `reservation` where id_reservation="+id;
         Reservation a=new Reservation();
         
        try { 
                stm = con.createStatement();
                ResultSet rst=stm.executeQuery(req);
                rst.next();
                a.setId_reservation(rst.getInt("id_reservation"));
                a.setDate_reservation(rst.getDate("date_reservation"));
                a.setId_restaurant(rst.getInt("id_restaurant"));
                a.setNombre(rst.getInt("nombre"));
                a.setId_user(rst.getInt("user_id"));
                
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }
  @Override
    public void update(Reservation t , int id) {
        try {
            stm = con.createStatement();
            String requete = "UPDATE `reservation` SET `id_restaurant`=?,"
                    +"`nombre`=?,"
                    + "`date_reservation`=?,`user_id`=? WHERE id_reservation="+t.getId_reservation();        
            PreparedStatement pst = con.prepareStatement(requete);
           pst.setInt(1, t.getId_restaurant());
           pst.setInt(2, t.getNombre());
           pst.setString(3,t.getDate_reservation().toString());
           pst.setInt(4,t.getId_user());
           
            pst.executeUpdate();
            System.out.println("reservation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
}
