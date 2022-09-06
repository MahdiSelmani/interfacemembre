/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.tuto;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Tutorials;

import services.Tutorials.ServiceTutorials;

/**
 * FXML Controller class
 *
 * @author HO
 */
public class Tutorals_membreController implements Initializable {

    @FXML
    private HBox tuto_layout;
    ObservableList<Tutorials> TutorialsList = FXCollections.observableArrayList();
    ObservableList<Tutorials> TutorialsListGames = FXCollections.observableArrayList();
    @FXML
    private GridPane tuto_containers;
    int column = 0;
    int row = 1;
    @FXML
    private Label r_ajouté;
    int game_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println(getGame()); 
//        showTutoUser(3);
//        showTutoGames(2);
    }

    public void setGame(int game_idd) {
        this.game_id = game_idd;

    }

    public void displayName(String game_name) {
        r_ajouté.setText(String.valueOf("Recement ajouté" + game_name));

    }

    public void showTutoUser(int game_idd) {
        ServiceTutorials ip = new ServiceTutorials();
        TutorialsList = (ObservableList<Tutorials>) ip.afficherTutoParUser(2, game_idd);
        try {
            for (int i = 0; i < TutorialsList.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("tuto_hb.fxml"));

                HBox tuto_hbox = fxmlloader.load();
                Tuto_hbController tuto_hbController = fxmlloader.getController();
                tuto_hbController.setData(TutorialsList.get(i));
                tuto_layout.getChildren().add(tuto_hbox);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void showTutoGames(int gaem_idd) {
        ServiceTutorials ip = new ServiceTutorials();
        TutorialsListGames = (ObservableList<Tutorials>) ip.afficherTutoPargame(gaem_idd);
        try {
            for (int i = 0; i < TutorialsListGames.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("TutoContainers.fxml"));

                VBox tuto_vbox = fxmlloader.load();
                TutoContainersController tutoContainersController = fxmlloader.getController();
                tutoContainersController.setData(TutorialsListGames.get(i));
                if (column == 4) {
                    column = 0;
                    ++row;
                }
                tuto_containers.add(tuto_vbox, column++, row);
                GridPane.setMargin(tuto_vbox, new Insets(10));
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
