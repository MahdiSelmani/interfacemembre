/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.produits;

import java.io.IOException;
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
import models.Product;
import services.Produits.ProductService;

/**
 * FXML Controller class
 *
 * @author HO
 */
public class ProduitsDispoController implements Initializable {

    @FXML
    private HBox produit_layout;
    @FXML
    private GridPane produit_containers;
    ObservableList<Product> ProductListUser = FXCollections.observableArrayList();
    ObservableList<Product> ProductList = FXCollections.observableArrayList();
    int column = 0;
    int row = 1;
    @FXML
    private Label label_user_name;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        label_user_name.setText("Oussama Nssibi");
        showProductUser();
        showProducts();

    }


    public void showProductUser() {
        ProductService productService = new ProductService();
        ProductListUser = (ObservableList<Product>) productService.afficherProductUser(10);
        try {
            for (Product p: ProductListUser) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("MesProduits.fxml"));
                HBox produit_hbox = fxmlloader.load();
                MesProduitsController produit_hbController = fxmlloader.getController();
                produit_hbController.setData(p);
                produit_layout.getChildren().add(produit_hbox);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void showProducts() {
        ProductService productService = new ProductService();
        ProductList = (ObservableList<Product>) productService.afficher();
        try {
            for (Product p: ProductList) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Produit_hb.fxml"));

                HBox tuto_vbox = fxmlloader.load();
                Produit_hbController produit_hbController = fxmlloader.getController();
                produit_hbController.setData(p);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                produit_containers.add(tuto_vbox, column++, row);
                GridPane.setMargin(tuto_vbox, new Insets(10));
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
