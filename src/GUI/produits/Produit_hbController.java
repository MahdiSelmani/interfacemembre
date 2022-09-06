/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.produits;

import GUI.panier.Panier;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.Product;

/**
 * FXML Controller class
 *
 * @author HO
 */
public class Produit_hbController implements Initializable {

    private Product produit;

    @FXML
    private ImageView bookImage;
    @FXML
    private Text description_label;
    @FXML
    private Label nom_p;
    @FXML
    private Label prix_p;
    @FXML
    private Label quantite_p;
    @FXML
    private JFXButton panier_button;
    private String[] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};
    @FXML
    private HBox box_produit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setData(Product product) {

        produit = product;
        bookImage.setImage(new Image(product.getImage()));
        
        description_label.setText(product.getDescription());
        nom_p.setText(product.getName());
        prix_p.setText(String.valueOf(product.getPrice()));
        box_produit.setStyle("-fx-background-color: WHITE;"
                + " -fx-background-radius: 15 ;"
                + "-fx-effect: dropShadown(three-pass-box , rgba(0,0,0,0),10,0,0,10);"
        );

    }

    @FXML
    private void AddPanier(MouseEvent event) {

        Panier.addProduct(produit);

    }

}
