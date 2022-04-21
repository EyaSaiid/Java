/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.CheckBox;


/**
 *
 * @author dell
 */
public class ProduitPlat {
    private int id_produitplat;
      private String nom_produitplat;
      private float prix;
      private String desc_produitplat;
      CheckBox checkbox;

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public int getId_categorie() {
        return id_categorie;
    }
      private int id_categorie;

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public ProduitPlat(int id_produitplat, String nom_produitplat, float prix, String desc_produitplat, CheckBox checkbox, int id_categorie) {
        this.id_produitplat = id_produitplat;
        this.nom_produitplat = nom_produitplat;
        this.prix = prix;
        this.desc_produitplat = desc_produitplat;
        this.checkbox = checkbox;
        this.id_categorie = id_categorie;
    }

    public ProduitPlat(String nom_produitplat, float prix, String desc_produitplat, int id_categorie) {
        //this.id_produitplat = id_produitplat;
        this.nom_produitplat = nom_produitplat;
        this.prix = prix;
        this.desc_produitplat = desc_produitplat;
        this.id_categorie = id_categorie;
    }
    public ProduitPlat(int id_produitplat,String nom_produitplat, float prix, String desc_produitplat, int id_categorie) {
        this.id_produitplat = id_produitplat;
        this.nom_produitplat = nom_produitplat;
        this.prix = prix;
        this.desc_produitplat = desc_produitplat;
        this.id_categorie = id_categorie;
    }
    
    
     public ProduitPlat(String nom_produitplat, float prix, String desc_produitplat) {
        this.id_produitplat = id_produitplat;
        this.nom_produitplat = nom_produitplat;
        this.prix = prix;
        this.desc_produitplat = desc_produitplat;
        this.id_categorie = id_categorie;
    }
    
      public ProduitPlat() {
        this.id_produitplat = id_produitplat;
        this.nom_produitplat = nom_produitplat;
        this.prix = prix;
        this.desc_produitplat = desc_produitplat;
        this.id_categorie = id_categorie;
    }


   

    public int getId_produitplat() {
        return id_produitplat;
    }

    public String getNom_produitplat() {
        return nom_produitplat;
    }

    public float getPrix() {
        return prix;
    }

    public String getDesc_produitplat() {
        return desc_produitplat;
    }

    public void setId_produitplat(int id_produitplat) {
        this.id_produitplat = id_produitplat;
    }

    public void setNom_produitplat(String nom_produitplat) {
        this.nom_produitplat = nom_produitplat;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDesc_produitplat(String desc_produitplat) {
        this.desc_produitplat = desc_produitplat;
    }

    @Override
    public String toString() {
        return "ProduitPlat{" + "id_produitplat=" + id_produitplat + ", nom_produitplat=" + nom_produitplat + ", prix=" + prix + ", desc_produitplat=" + desc_produitplat + ", id_categorie=" + id_categorie + '}';
    }
    
    
    
      
}
