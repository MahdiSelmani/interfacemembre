/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.panier;

import GUI.commander.CommanderController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Product;

/**
 * FXML Controller class
 *
 * @author HO
 */
public class PanierController implements Initializable {

    @FXML
    private GridPane produit_containers;
    int column = 0;
    int row = 1;
    @FXML
    private Label total;
    private double price = 0;
    @FXML
    private Button cmd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showProducts();
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showProducts() throws IOException {
        if (Panier.getProducts().isEmpty()){
            cmd.setDisable(true);
        }
        for (Product p : Panier.getProducts()) {
            price += p.getPrice();
            total.setText(price + " TND");
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ProduitPanier.fxml"));
            HBox tuto_vbox = fxmlloader.load();
            ProduitPanierController produitController = fxmlloader.getController();
            produitController.setData(p);
            if (column == 3) {
                column = 0;
                ++row;

            }
            produit_containers.add(tuto_vbox, column++, row);
            GridPane.setMargin(tuto_vbox, new Insets(10));

        }
    }

    @FXML
    public void reload() throws IOException {
        column = 0;
        price = 0;
        total.setText(null);
        produit_containers.getChildren().clear();
        showProducts();
        System.out.println(Panier.getProducts());
    }

    @FXML
    private void Commander(MouseEvent event) throws IOException {

        reload();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../commander/Commander.fxml"));
        loader.load();
        CommanderController cc = loader.getController();
        cc.setTextField(price, Panier.getProducts());
        Parent parent = loader.getRoot();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}
