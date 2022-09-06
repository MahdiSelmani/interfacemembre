/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.Produits;

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
import models.Product;
import services.Iservices;
import utils.mydb;

/**
 *
 * @author Mahdi
 */
public class ProductService implements Iservices<Product> {

    private Connection cnx = mydb.getInstance().getCnx();
    ObservableList<Product> products = FXCollections.observableArrayList();

    @Override
    public List<Product> afficher() {
        String req = "SELECT * FROM products inner join users on products.user_id = users.id ;";
        try {
            products.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUser_id(rs.getInt("user_id"));
                p.setOwner(rs.getString("first_name"), rs.getString("last_name"));
                products.add(p);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());

        }

        return products;
    }

    @Override
    public void ajouter(Product p) {
        String req = "INSERT INTO `products`(`name`, `description`, `image`, `user_id`, `price`, `quantity`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getImage());
            ps.setInt(4, p.getUser_id());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getQuantity());
            ps.executeUpdate();
            System.out.println("PS : Produit ajouté avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(Product t) {
        try {
            String req = "DELETE FROM `products` WHERE id = '" + t.getId() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit supprimé avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Product t) {
        String req = " UPDATE `products` SET `name`=?,`description`=?,`image`=?,`price`=?,`quantity`=? WHERE id =" + t.getId() + "";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getImage());
            ps.setDouble(4, t.getPrice());
            ps.setInt(5, t.getQuantity());
            ps.executeUpdate();
            System.out.println("PS : Produit Modifié avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Product> rechercher(String query) {
        String req = "SELECT * FROM products inner join users on products.user_id = users.id where name like '%" + query + "%'";
        try {
            products.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUser_id(rs.getInt("user_id"));
                p.setOwner(rs.getString("first_name"), rs.getString("last_name"));
                products.add(p);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return products;
    }

    public List<Product> afficherProductUser(int user_id) {
        String req = "SELECT * FROM products inner join users on products.user_id = users.id WHERE products.user_id = " + "'" + user_id + "'";
        try {
            products.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUser_id(rs.getInt("user_id"));
                p.setOwner(rs.getString("first_name"), rs.getString("last_name"));
                products.add(p);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return products;
    }

}
