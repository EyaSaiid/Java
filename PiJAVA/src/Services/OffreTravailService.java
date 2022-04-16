/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.OffreTravail;
import java.sql.Connection;
import IServices.IServiceOffreTravail;
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
 * @author SAID EYA
 */
public class OffreTravailService implements IServiceOffreTravail<OffreTravail> {

    Connection con;
    Statement stm;

    public OffreTravailService() {
        con = MyDB.getInstance().getCon();

    }
    
    
    @Override
    public void add(OffreTravail t) throws SQLException {
        //INSERT INTO `offre_travail`(`id`, `user_id`, `id_restaurant`, `titre`, `description`) 
        //VALUES 
        //([value-1],[value-2],[value-3],[value-4],[value-5])
        String req = "INSERT INTO `offre_travail` (`id`,`user_id`,`id_restaurant`,`titre`,`description`) VALUES (?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, t.getId());
        pstm.setInt(2, t.getUser_id());        
        pstm.setInt(3, t.getId_restaurant());
        pstm.setString(4, t.getTitre());
        pstm.setString(5, t.getDescription());
        pstm.executeUpdate();
    }

    @Override
    public void ajouterr(OffreTravail t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Works!!
    @Override
    public List<OffreTravail> show() throws SQLException {
        String req = "Select * from `offre_travail`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<OffreTravail> offreTravails = new ArrayList<OffreTravail>();
        while(rst.next()){
            
            OffreTravail p = new OffreTravail(rst.getInt("id"),rst.getInt("user_id"),rst.getInt("id_restaurant"),rst.getString("titre"),rst.getString("description"));
            offreTravails.add(p);
        }
        return offreTravails;   }
    

    //Works!!
    @Override
    public void update(OffreTravail t, int id) throws SQLException {
        try {
                   String requete = "UPDATE offre_travail SET titre=? WHERE id=?"+id;
                   PreparedStatement pst = con.prepareStatement(requete);
                   pst.setString(1, t.getTitre());
                   pst.setInt(2,t.getId());
                   //pst.setString(2,t.getDateDebut().toString());
                  // pst.setString(3,t.getDateFin().toString());


                  pst.executeUpdate();
                   System.out.println("Offre de travail modifiée !");

               } catch (SQLException ex) {
                   System.err.println(ex.getMessage());
               }    
    }

    //Works!!
    @Override
    public void deleteById(int t) throws SQLException {
        String req = "DELETE FROM `offre_travail` WHERE id="
                       + t;
                stm = con.createStatement();
                stm.executeUpdate(req);
    }

    //Works!!
    @Override
    public OffreTravail showByID(int id) throws SQLException {
        String req="select * from `offre_travail` where id="+id;
             OffreTravail a=new OffreTravail();
              stm = con.createStatement();

            try {

                    ResultSet rst=stm.executeQuery(req);
                    rst.next();
                    a.setId(rst.getInt("id"));
                   
                    a.setTitre(rst.getString("titre"));
                    a.setDescription(rst.getString("description"));

            } catch (SQLException ex) {
                Logger.getLogger(OffreTravailService.class.getName()).log(Level.SEVERE, null, ex);
            }
        return a;    
    }

 
    
    
}
