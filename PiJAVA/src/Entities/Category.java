/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SNour
 */
public class Category {
    private int id;
    private String libelle;

    public Category() {
    }

    public Category(String libelle) {
        this.libelle = libelle;
       
    }

    
    public Category(int id, String nom, String libelle) {
        this.id = id;
        this.libelle = libelle;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle =libelle;
    }

    @Override
    public String toString() {
       // return "Category{" + "id=" + id + ", libelle=" + libelle + '}';
    return libelle;
    }
    
}
