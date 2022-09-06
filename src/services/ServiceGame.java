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
import models.Game;
import models.Game_categoeries;

import services.Iservices;

import utils.mydb;

/**
 *
 * @author HO
 */
public class ServiceGame implements Iservices<Game> {

    Connection cnx = mydb.getInstance().getCnx();
    ObservableList<Game> GameList = FXCollections.observableArrayList();
    String Categorie_name;
    int categoerie_id;

    @Override
    public List<Game> afficher() {

        String req = "SELECT g.id , g.game_name , g.description ,g.categorie_id , gc.type FROM games g inner join game_categoeries gc on g.categorie_id = gc.id;";
        try {
            GameList.clear();

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setGame_name(rs.getString("game_name"));
                game.setDescription(rs.getString("description"));
                game.setCategorie_id(rs.getInt("categorie_id"));
                game.setType(rs.getString("type"));
                GameList.add(game);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return GameList;
    }

    @Override
    public void ajouter(Game game) {
        String req = "INSERT INTO `games`(`game_name`, `description`, `categorie_id`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, game.getGame_name());
            ps.setString(2, game.getDescription());
            ps.setInt(3, game.getCategorie_id());
            ps.executeUpdate();
            System.out.println("PS : Game ajouté avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(Game game) {
        try {
            String req = "DELETE FROM `games` WHERE id = '" + game.getId() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Game supprimer avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Game game) {
        String req = "UPDATE games SET "
                + "`game_name`=?,"
                + "`description`=?,"
                + "`categorie_id`= ? WHERE id = '" + game.getId() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, game.getGame_name());
            ps.setString(2, game.getDescription());
            ps.setInt(3, game.getCategorie_id());
            ps.executeUpdate();
            System.out.println("PS : Game Modifié avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getCategorieType(int Categorie_id) {
        String req = "SELECT * FROM `game_categoeries` WHERE `id`=" + "'" + Categorie_id + "'";

        try {

            Game game = new Game();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                game.setType(rs.getString("type"));
                Categorie_name = game.getType();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Categorie_name;
    }

    public int getCategorie_id(String Categorie_name) {
        String req = "SELECT * FROM `game_categoeries` WHERE `type`=" + "'" + Categorie_name + "'";

        try {

            Game game = new Game();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                game.setCategorie_id(rs.getInt("id"));
                categoerie_id = game.getCategorie_id();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoerie_id;
    }

    public List<Game> afficherNamesParCategorie(int categories_id) {
        String req = "SELECT * FROM `games` WHERE `categorie_id`='" + categories_id + "'";
        List<Game> Game_NamesList = new ArrayList<>();
        try {

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game gameC = new Game();
                gameC.setGame_name(rs.getString("game_name"));
                gameC.setId(rs.getInt("id"));
                Game_NamesList.add(gameC);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Categories_jeuxServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Game_NamesList;
    }

}
