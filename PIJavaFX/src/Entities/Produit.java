/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author Nour Hammami
 */
public class Produit {
    int id,quantiteProduit,id_categorie;
    String nomProduit,descriptionProduit;
    double prixProduit;
    private ImageView image_produit;
    String photo;


    public Produit(int quantiteProduit, int id_categorie, String nomProduit, String descriptionProduit, double prixProduit, String photo) {
        this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.photo = photo;
    }

    public Produit(int id, int quantiteProduit, int id_categorie, String nomProduit, String descriptionProduit, double prixProduit) {
        this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
    }

    public Produit(int quantiteProduit, int id_categorie, String nomProduit, String descriptionProduit, double prixProduit, ImageView image_produit) {
      this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.image_produit = image_produit;
    }

    public Produit(int quantiteProduit, int id_categorie, String nomProduit, String descriptionProduit, double prixProduit) {
       this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
    }

    public Produit(int id, String nomProduit, String descriptionProduit, double prixProduit, int id_categorie, int quantiteProduit) {
        this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
    }

    public Produit(int id, String nomProduit,  String descriptionProduit,double prixProduit,int quantiteProduit, int id_categorie,  String photo) {
        this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.photo = photo;
    }


    public Produit() {
    }

    public Produit(int id, String nomProduit, String descriptionProduit) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
    }

  

    public Produit(String nomProduit, String descriptionProduit, int quantiteProduit, double prix, String photo) {
     this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.photo = photo;
    }
 public Produit(String nomProduit, String descriptionProduit, int quantiteProduit, double prix) {
    this.id = id;
     this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
       
    }
    public Produit(String nomProduit, String descriptionProduit, double prixProduit, int quantiteProduit) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    this.quantiteProduit = quantiteProduit;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        
    }

    public Produit(String nomProduit, String descriptionProduit, double prixProduit, int quantiteProduit, int id_categorie) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     this.quantiteProduit = quantiteProduit;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.id_categorie = id_categorie;
    }

    public Produit(String nomProduit, String descriptionProduit, double prixProduit, int quantiteProduit, int id_categorie, String photo) {
        this.quantiteProduit = quantiteProduit;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.id_categorie = id_categorie; 
        this.photo = photo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

   

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public Produit(int id, int quantiteProduit, int id_categorie, String nomProduit, String descriptionProduit, double prixProduit, ImageView image_produit, String photo) {
        this.id = id;
        this.quantiteProduit = quantiteProduit;
        this.id_categorie = id_categorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.image_produit = image_produit;
        this.photo = photo;
    }

    public ImageView getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(ImageView image_produit) {
        this.image_produit = image_produit;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", quantiteProduit=" + quantiteProduit + ", nomProduit=" + nomProduit + ", descriptionProduit=" + descriptionProduit + ", prixProduit=" + prixProduit + ", photo=" + photo + ", id_categorie=" + id_categorie +'}';
    }
    
    
}
