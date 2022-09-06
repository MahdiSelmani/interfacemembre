/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.commandes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Order;
import services.Iservices;
import utils.mydb;

/**
 *
 * @author Mahdi
 */
public class ServiceCommandes implements Iservices<Order> {

    private Connection cnx = mydb.getInstance().getCnx();
    ObservableList<Order> orders = FXCollections.observableArrayList();

    @Override
    public List<Order> afficher() {
        String req = "SELECT orders.id, orders.delivered, orders.price, orders.created_at, users.first_name, users.last_name, products.name, products.image from `orders` inner join users inner join products on orders.user_id = users.id and orders.product_id = products.id";
        try {
            orders.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order p = new Order();
                p.setId(rs.getInt("id"));
                //     p.setProduct(rs.getString("name"));
                //     p.setOwner(rs.getString("first_name") + " " + rs.getString("last_name"));
                p.setDelivered(rs.getBoolean("delivered"));
                p.setImage(rs.getString("image"));
                p.setDate(rs.getDate("created_at"));
                p.setPrice(rs.getDouble("price"));

                orders.add(p);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return orders;
    }

    public int ajouterCommande(Order t) {
        int id=-1;
        String req = "INSERT INTO `orders` ( `price`, `user_id`, `adresse`) VALUES ('" + t.getPrice() + "','" + t.getOwner() + "', '" + t.getAdresse() + "');";
        try {
//            statement.setString(1, user.getName());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getEmail());
            // ...
            PreparedStatement statement = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommandes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    @Override
    public void supprimer(Order t) {
        try {
            String req = "DELETE `orders` WHERE id = '" + t.getId() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande supprimée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommandes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifier(Order t) {
        String req = " UPDATE `orders` SET `delivered`=1 WHERE id =" + t.getId() + "";
        try {

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("PS : Commande modifiée à Delivrée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Order> rechercher(String query) {
        String req = "SELECT orders.id, orders.delivered, orders.price, orders.created_at, users.first_name, users.last_name, products.name, products.image from `orders` inner join users inner join products on orders.user_id = users.id and orders.product_id = products.id where concat(users.first_name,\" \",users.last_name) like '%" + query + "%' or concat(users.last_name,\" \",users.first_name) like '%" + query + "%' or products.name like '%" + query + "%' ";
        try {
            orders.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order p = new Order();
                p.setId(rs.getInt("id"));
                //      p.setProduct(rs.getString("name"));
                //    p.setOwner(rs.getString("first_name") + " " + rs.getString("last_name"));
                p.setDelivered(rs.getBoolean("delivered"));
                p.setImage(rs.getString("image"));
                p.setDate(rs.getDate("created_at"));
                p.setPrice(rs.getDouble("price"));

                orders.add(p);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return orders;
    }

    @Override
    public void ajouter(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
