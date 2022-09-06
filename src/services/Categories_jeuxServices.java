/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Game_categoeries;
import services.Iservices;
import utils.mydb;

/**
 *
 * @author HO
 */
public class Categories_jeuxServices implements Iservices<Game_categoeries> {

    Connection cnx = mydb.getInstance().getCnx();
    ObservableList<Game_categoeries> Game_categoeriesList = FXCollections.observableArrayList();

    @Override
    public List<Game_categoeries> afficher() {
        String req = "SELECT * FROM game_categoeries";
        try {
            Game_categoeriesList.clear();

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game_categoeries gameC = new Game_categoeries();
                gameC.setId(rs.getInt("id"));
                gameC.setType(rs.getString("type"));
                Game_categoeriesList.add(gameC);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Categories_jeuxServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Game_categoeriesList;
    }

    @Override
    public void supprimer(Game_categoeries gameC) {

        try {
            String req = "DELETE FROM `game_categoeries` WHERE id = '" + gameC.getId() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Catégorie de jeux supprimer avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(Categories_jeuxServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ajouter(Game_categoeries gameC) {
        String req = "INSERT INTO `game_categoeries`(`type`) VALUES (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, gameC.getType());
            ps.executeUpdate();
            System.out.println("PS : Catégorie de jeux ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(Categories_jeuxServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Game_categoeries gameC) {
        String req = "UPDATE `game_categoeries` SET "
                + "`type`=? WHERE id = '" + gameC.getId() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, gameC.getType());
            ps.executeUpdate();
            System.out.println("PS : Catégorie de jeux Modifié avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(Categories_jeuxServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Game_categoeries> afficherType() {
        String req = "SELECT * FROM game_categoeries";
        List<Game_categoeries> Game_categoeriesList  = new ArrayList<>();
        try {
 

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game_categoeries gameC = new Game_categoeries();
                gameC.setType(rs.getString("type"));
                gameC.setId(rs.getInt("id")) ;
                Game_categoeriesList.add(gameC);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Categories_jeuxServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Game_categoeriesList;
    }

}
