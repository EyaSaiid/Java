/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dell
 */
public class Reservation {
      private int id_reservation;
      private int nombre;
      private Date date_reservation;
      private int id_user;
      private int id_restaurant;

    public Reservation(int nombre, Date date_reservation, int id_user, int id_restaurant) {
        this.id_reservation = id_reservation;
        this.nombre = nombre;
        this.date_reservation = date_reservation;
        this.id_user = id_user;
        this.id_restaurant = id_restaurant;
    }
    
     public Reservation(int id_reservation ,int nombre, Date date_reservation, int id_user, int id_restaurant) {
        
        this.id_reservation = id_reservation;
        this.nombre = nombre;
        this.date_reservation = date_reservation;
        this.id_user = id_user;
        this.id_restaurant = id_restaurant;
    }
      public Reservation(int id_reservation ,int nombre, int id_user, int id_restaurant) {
        
        this.id_reservation = id_reservation;
        this.nombre = nombre;
        this.date_reservation = date_reservation;
        this.id_user = id_user;
        this.id_restaurant = id_restaurant;
    }
    
    public Reservation() {
        this.id_reservation = id_reservation;
        this.nombre = nombre;
        this.date_reservation = date_reservation;
        this.id_user = id_user;
        this.id_restaurant = id_restaurant;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public int getNombre() {
        return nombre;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", nombre=" + nombre + ", date_reservation=" + date_reservation + ", id_user=" + id_user + ", id_restaurant=" + id_restaurant + '}';
    }

   
   
    

  
}
