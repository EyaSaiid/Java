/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author SAID EYA
 */
public interface IServiceOffreTravail<T> {
    
    void add(T t, int iduser) throws SQLException;
    void ajouterr(T t)throws SQLException; //2eme methode
    List<T> show() throws SQLException;
     void update(T t, int id, int idres) throws SQLException;
    void deleteById(int t)throws SQLException;
    T showByID(int t)throws SQLException;
    
}
