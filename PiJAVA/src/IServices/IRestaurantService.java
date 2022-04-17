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
public interface IRestaurantService<T> {
    void add(T t) throws SQLException;
    void ajouterr(T t)throws SQLException; //2eme methode
    List<T> show() throws SQLException;
    void update(T t, int id) throws SQLException;
    void deleteById(int t)throws SQLException;
    void delete(T t)throws SQLException;
    T showByID(int t)throws SQLException;
}
