/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.User;
import IServices.IserviceUser;
import static com.mysql.jdbc.Messages.getString;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pijava.PiJAVA;

/**
 *
 * @author hp
 */
public class UserService implements IserviceUser<User> {

    Connection cnx = MyDB.getInstance().getCon();
    private Statement ste;
   
    

    @Override
    public void ajouter_user(User u) {
             try {
            String req = "INSERT INTO user(nom,prenom,date,sexe,numero_tele,email,roles,password,is_verified,image) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(4, u.getSexe());
            pst.setString(3, u.getDate().toString());
            pst.setString(6, u.getEmail());
            pst.setInt(5, u.getNumero_tele());
         //  String s=encryptThisString(p.getPassword());
         //   System.out.println(p.getPassword()+"|"+s);
         //role
            pst.setString(7,"[\"ROLE_USER\"]" );

            pst.setString(8,encryptThisString(u.getPassword() ));
            //isverified
            pst.setInt(9,0);
            pst.setString(10, u.getImage_user());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ajouter_user2(User u) {
             try {
            String req = "INSERT INTO user(nom,prenom,date,sexe,numero_tele,email,roles,password,is_verified,image) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(4, u.getSexe());
            pst.setString(3, u.getDate().toString());
            pst.setString(6, u.getEmail());
            pst.setInt(5, u.getNumero_tele());
         //  String s=encryptThisString(p.getPassword());
         //   System.out.println(p.getPassword()+"|"+s);
         //role
            pst.setString(7,u.getRoles() );
            
            pst.setString(8,encryptThisString(u.getPassword() ));
            //isverified
            pst.setInt(9,0);
            pst.setString(10, u.getImage_user());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer_user(int t) {
        try { 
             String req = "Delete from user Where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t);
            System.out.println("suppression entreprise avec succes ");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifier_user(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_user(User u, int id) {
        try {
            String requete = "UPDATE  User SET nom=?,prenom=?,date=?,sexe=?,numero_tele=?,email=?,roles=?,password=?,is_verified=?,image=?"
                    + " WHERE id=?";
            Statement st = cnx.createStatement();
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(4, u.getSexe());
            ps.setString(3, u.getDate().toString());
            ps.setString(6, u.getEmail());
            ps.setInt(5, u.getNumero_tele());
           ps.setString(7,u.getRoles() );
            ps.setString(8,encryptThisString(u.getPassword() ));
            //isverified
            ps.setInt(9,u.getIs_verified());
            ps.setString(10, u.getImage_user());
            ps.setInt(11, u.getId());
            ps.executeUpdate();

            System.out.println(u);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<User> afficher_user() {

        List<User> list = new ArrayList<>();
        try {
            String requete = "SELECT id,nom,prenom,date,sexe,numero_tele,email,roles,password,is_verified,image FROM User";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User p = new User();
                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream("/Images/"+rs.getString(11))));     
               //ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream("/Images/eya.jpg")));     

                photo.setFitWidth(100);
                photo.setPreserveRatio(true);
                photo.setSmooth(true);
                photo.setCache(true);
                list.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), 
                        rs.getDate(4), rs.getString(5), rs.getInt(6),rs.getString(7), rs.getString(8)
                        , rs.getString(9),rs.getInt(10),photo ));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;


    }
    
   
    public User afficher_user(int id) {

        User u=null;
        try {
            String requete = "SELECT id,nom,prenom,date,sexe,numero_tele,email,roles,password,is_verified,image FROM User where id= ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream("/Images/"+rs.getString(11))));     
                photo.setFitWidth(100);
                photo.setPreserveRatio(true);
                photo.setSmooth(true);
                photo.setCache(true);
                User uq = new User(rs.getInt(1),rs.getString(2), rs.getString(3), 
                        rs.getDate(4), rs.getString(5), rs.getInt(6),rs.getString(7), rs.getString(8)
                        , rs.getString(9),rs.getInt(10),photo, rs.getString(11) );
                u=uq;

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return u;


    }
    
    public String encryptThisString(String input) 
    { 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        } 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
    public boolean Verifer_Access(String Email, String Password) {
        boolean valide = false;
        try {
            String requete = "SELECT email,password,roles,image,id FROM user WHERE email=? and password=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, Email);
            pst.setString(2, encryptThisString(Password));

            ResultSet Res = pst.executeQuery();
            if (Res.next()) {
                User u = new User();
                u.setEmail(Res.getString(1));
                u.setImage_user(Res.getString(4));
                u.setId(Res.getInt(5));

                String roles = Res.getString(3);
                if (roles == null) {
                    roles = "user";
                }
                u.setRoles(roles);
                PiJAVA.user = u;
                
                valide = true;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return valide;
    }

    public void updatePassword(String reset_mail, String password) {
 
    try {
            String requete = "UPDATE  user SET password=?"
                    + " WHERE email=?";
            Statement st = cnx.createStatement();
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,encryptThisString(password));
            ps.setString(2, reset_mail);
            
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

}
