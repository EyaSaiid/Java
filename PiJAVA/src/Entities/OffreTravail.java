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
public class OffreTravail {
    private int id_restaurant;
    private int user_id;
    private int id;
    private String titre;
    private String description;
    private String restaurant;

    public OffreTravail() {
        this.user_id=user_id;
        this.id_restaurant = id_restaurant;
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    

    
    public OffreTravail(int id,int user_id,int id_restaurant, String titre, String description) {
        this.user_id=user_id;
        this.id_restaurant = id_restaurant;
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    
    public OffreTravail(String titre, String description, int id_restaurant) {
        this.titre = titre;
        this.description = description;
        this.id_restaurant=id_restaurant;
    }
    public int getId_restaurant() {
            return id_restaurant;
        }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "OffreTravail{" + "id=" + id + ", titre=" + titre + ", description=" + description + '}';
    }
    
    
}
