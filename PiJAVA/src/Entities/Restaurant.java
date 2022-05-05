/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SAID EYA
 */
public class Restaurant {
     private int id_restaurant;
    private int id_user;
    private String nom_restaurant;
    private int capacite;
    private String specialite;
    private String desc_restaurant;
    private String adresse;
    private String Num_tel;
    
    
   public Restaurant() {
        this.id_restaurant = id_restaurant;
        this.id_user = id_user;
        this.nom_restaurant = nom_restaurant;
        this.capacite = capacite;
        this.specialite = specialite;
        this.desc_restaurant = desc_restaurant;
        this.adresse = adresse;
        this.Num_tel = Num_tel;
    }
   
   public Restaurant(int id_user, String nom_restaurant, int capacité, String specialité, String desc_restaurant, String adresse, String Num_tel) {
        this.id_restaurant = id_restaurant;
        this.id_user = id_user;
        this.nom_restaurant = nom_restaurant;
        this.capacite = capacité;
        this.specialite = specialité;
        this.desc_restaurant = desc_restaurant;
        this.adresse = adresse;
        this.Num_tel = Num_tel;
    }
   
     public Restaurant(int id_restaurant, int id_user, String nom_restaurant, int capacité, String specialité, String desc_restaurant, String adresse, String Num_tel) {
        this.id_restaurant = id_restaurant;
        this.id_user = id_user;
        this.nom_restaurant = nom_restaurant;
        this.capacite = capacité;
        this.specialite = specialité;
        this.desc_restaurant = desc_restaurant;
        this.adresse = adresse;
        this.Num_tel = Num_tel;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setDesc_restaurant(String desc_restaurant) {
        this.desc_restaurant = desc_restaurant;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId_user() {
        return id_user;
    }

    public String getDesc_restaurant() {
        return desc_restaurant;
    }

    public String getAdresse() {
        return adresse;
    }
  

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public void setNom_restaurant(String nom_restaurant) {
        this.nom_restaurant = nom_restaurant;
    }

    public void setCapacité(int capacité) {
        this.capacite = capacité;
    }

    public void setSpecialité(String specialite) {
        this.specialite = specialite;
    }

    public void setNum_tel(String Num_tel) {
        this.Num_tel = Num_tel;
    }

    

    public Restaurant(String nom_restaurant, int capacité, String specialité, String Num_tel) {
        this.nom_restaurant = nom_restaurant;
        this.capacite = capacité;
        this.specialite = specialite;
        this.Num_tel = Num_tel;
       
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public String getNom_restaurant() {
        return nom_restaurant;
    }

    public int getCapacité() {
        return capacite;
    }

    public String getSpecialité() {
        return specialite;
    }

    public String getNum_tel() {
        return Num_tel;
    }

    @Override
    public String toString() {
        return "Restaurant : " +  nom_restaurant;
    }

    
}
