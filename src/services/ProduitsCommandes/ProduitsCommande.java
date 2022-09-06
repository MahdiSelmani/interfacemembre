/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.ProduitsCommandes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Product;
import services.Iservices;
import utils.mydb;

/**
 *
 * @author Mahdi
 */
public class ProduitsCommande implements Iservices<Product> {
    private Connection cnx = mydb.getInstance().getCnx();
    @Override
    public List<Product> afficher() {
        return null;
        //where orde id = 
    }

    public void ajouter(Product t,int id) {
       //we will replace 10 with current user id later 
        String req = "INSERT INTO `orders_products`(`order_id`, `product_id`) VALUES ('"+id+"','"+t.getId()+"')";
        try {

            PreparedStatement statement = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            

        } catch (SQLException ex) {
            Logger.getLogger(ProduitsCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void supprimer(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
