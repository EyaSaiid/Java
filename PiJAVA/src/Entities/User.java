/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author SAID EYA
 */
public class User {
    
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String email;
    private int numero_tele;
    private Date date;
    private String password;
    private String image_user;
    private ImageView photo;
    private int is_verified;
    private String roles;
    
    public User() {
    }

    public User(int id, String nom, String prenom, Date date,String sexe,  int numero_tele, String email , String roles, String password, int is_verified, String image_user) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.sexe = sexe;
        this.numero_tele = numero_tele;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.is_verified = is_verified;
        this.image_user = image_user;
    }


    
    public User( int id,String nom, String prenom, String sexe,Date date,String email,int numero_tele,String password,String is_verified,String image_user) {
      
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date = date;
        this.email = email; 
        this.numero_tele=numero_tele;
        this.password=password;
        this.image_user=image_user;
    }

    public User( int id,String nom, String prenom,Date date, String sexe, int numero_tele, String email,String roles,String password,int is_verified, ImageView photo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.sexe = sexe;
        this.numero_tele = numero_tele;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.is_verified = is_verified;
        this.photo=photo;
    }
    
    public User( int id,String nom, String prenom,Date date, String sexe, int numero_tele, String email,String roles,String password,int is_verified, ImageView photo,String image_user) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.sexe = sexe;
        this.numero_tele = numero_tele;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.is_verified = is_verified;
        this.photo=photo;
        this.image_user=image_user;
    }

    
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        
        this.roles = roles;
//        this.roles = "[\"ROLE_USER\"]";
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero_tele() {
        return numero_tele;
    }

    public void setNumero_tele(int numero_tele) {
        this.numero_tele = numero_tele;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", email=" + email + ", numero_tele=" + numero_tele + ", date=" + date + ", password=" + password + ", image_user=" + image_user + ", photo=" + photo + ", is_verified=" + is_verified + ", roles=" + roles + '}';
    }
    
    
    

   
    
    
    
}
