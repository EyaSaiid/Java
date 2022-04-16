/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.User;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IserviceUser<T> {
    public void ajouter_user(T t);
    public void supprimer_user(int t);
    public void modifier_user(T t);
     public void modifier_user(T t,int id);
    public List<T> afficher_user();

}
