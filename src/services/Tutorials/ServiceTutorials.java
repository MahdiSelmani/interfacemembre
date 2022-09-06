/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.Tutorials;

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
import models.Tutorials;
import services.Iservices;
import utils.mydb;

/**
 *
 * @author HO
 */
public class ServiceTutorials implements Iservices<Tutorials> {

    Connection cnx = mydb.getInstance().getCnx();
    ObservableList<Tutorials> TutorialsList = FXCollections.observableArrayList();
    int game_id;
    String game_name;

    @Override
    public List<Tutorials> afficher() {
        String req = "SELECT * FROM tutorials inner join users on tutorials.user_id = users.id inner join games on tutorials.game_id = games.id;";
        try {
            TutorialsList.clear();

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tutorials tutorials = new Tutorials();
                tutorials.setId(rs.getInt("id"));
                tutorials.setVideo(rs.getString("video"));
                tutorials.setDescription(rs.getString("description"));
                tutorials.setGame_id(rs.getInt("game_id"));
                tutorials.setUser_id(rs.getInt("user_id"));
                tutorials.setOwner(rs.getString("first_name"), rs.getString("last_name"));
                tutorials.setGame_name(rs.getString("game_name"));
                TutorialsList.add(tutorials);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TutorialsList;
    }

    @Override
    public void ajouter(Tutorials tutorials) {
        String req = "INSERT INTO `tutorials`(`video`, `description`, `user_id`, `game_id`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, tutorials.getVideo());
            ps.setString(2, tutorials.getDescription());
            ps.setInt(3, tutorials.getUser_id());
            ps.setInt(4, tutorials.getGame_id());
            ps.executeUpdate();
            System.out.println("PS : Tutorials ajouté avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(Tutorials tutorials) {
        try {
            String req = "DELETE FROM `tutorials` WHERE id = '" + tutorials.getId() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Tutorials supprimer avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Tutorials tutorials) {
        String req = "UPDATE tutorials SET "
                + "`video`=?,"
                + "`description`=?,"
                + "`user_id`=?,"
                + "`game_id`= ? WHERE id = '" + tutorials.getId() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, tutorials.getVideo());
            ps.setString(2, tutorials.getDescription());
            ps.setInt(3, tutorials.getUser_id());
            ps.setInt(4, tutorials.getGame_id());
            ps.executeUpdate();
            System.out.println("PS : Tutorials Modifier avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getGame_id(String game_name) {
        String req = "SELECT * FROM `games` WHERE `game_name`=" + "'" + game_name + "'";

        try {

            Tutorials tutorials = new Tutorials();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tutorials.setGame_id(rs.getInt("id"));
                game_id = tutorials.getGame_id();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return game_id;
    }

    public String getGameName(int game_idd) {
        String req = "SELECT * FROM `games` WHERE `id`=" + "'" + game_idd + "'";

        try {

            Tutorials tutorials = new Tutorials();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tutorials.setGame_name(rs.getString("game_name"));
                game_name = tutorials.getGame_name();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return game_name;
    }

    public List<Tutorials> afficherTutoParUser(int user_id, int game_id) {
        String req = "SELECT * FROM tutorials inner join users on tutorials.user_id = users.id inner join games on tutorials.game_id = games.id WHERE tutorials.user_id =" + "'" + user_id + "'" + "and tutorials.game_id =" + "'" + game_id + "'";
        try {
            TutorialsList.clear();

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tutorials tutorials = new Tutorials();
                tutorials.setId(rs.getInt("id"));
                tutorials.setVideo(rs.getString("video"));
                tutorials.setDescription(rs.getString("description"));
                tutorials.setGame_id(rs.getInt("game_id"));
                tutorials.setUser_id(rs.getInt("user_id"));
                tutorials.setOwner(rs.getString("first_name"), rs.getString("last_name"));
                tutorials.setGame_name(rs.getString("game_name"));
                TutorialsList.add(tutorials);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TutorialsList;
    }

    public List<Tutorials> afficherTutoPargame( int game_id) {
        String req = "SELECT * FROM tutorials inner join users on tutorials.user_id = users.id inner join games on tutorials.game_id = games.id WHERE tutorials.game_id =" + "'" + game_id + "'";
        try {
            TutorialsList.clear();

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tutorials tutorials = new Tutorials();
                tutorials.setId(rs.getInt("id"));
                tutorials.setVideo(rs.getString("video"));
                tutorials.setDescription(rs.getString("description"));
                tutorials.setGame_id(rs.getInt("game_id"));
                tutorials.setUser_id(rs.getInt("user_id"));
                tutorials.setOwner(rs.getString("first_name"), rs.getString("last_name"));
                tutorials.setGame_name(rs.getString("game_name"));
                TutorialsList.add(tutorials);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTutorials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TutorialsList;
    }

}
