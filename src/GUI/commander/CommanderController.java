/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.commander;

import GUI.panier.Panier;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Order;
import models.Product;
import services.ProduitsCommandes.ProduitsCommande;
import services.commandes.ServiceCommandes;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class CommanderController implements Initializable {

    @FXML
    private Button go;
    @FXML
    private Button cancel;
    @FXML
    private TextField adresseDeLivraison;
    @FXML
    private Label total;
    @FXML
    private Label nbrProduits;
    private List<Product> ProductList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTextField(double total, List<Product> ProductList) {
        this.ProductList = ProductList;
        this.total.setText(String.valueOf(total));
        this.nbrProduits.setText(String.valueOf(ProductList.size()));

    }

    @FXML
    private void go(MouseEvent event) {
        if (adresseDeLivraison.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Adresse manquante");
            alert.setContentText("Veuillez indiquer l'adresse de livraison s'il vous plait");
            alert.showAndWait();
            return;
        }
        Order order = new Order();
        order.setAdresse(this.adresseDeLivraison.getText());
        //change 10 with current user id 
        order.setOwner(10);
        order.setPrice(Double.valueOf(total.getText()));
        ServiceCommandes sc = new ServiceCommandes();
        int id = sc.ajouterCommande(order);
        if (id != -1) {
            //Apr??s l'avoir ajout?? a la commande, le produit doit etre supprim?? du panier
            ProductList.stream().map((p) -> {
                ProduitsCommande pc = new ProduitsCommande();
                pc.ajouter(p, id);
                return p;
            }).forEachOrdered((p) -> {
                System.out.println(p.toString() + "ajout?? a la commande avc succ??s et supprim?? du panier");
            });

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Commande ajout??e avec succ??s");
            alert.setContentText("Merci pour avoir pass?? votre commande");
       

        }
        Panier.clearPanier();

    }

    @FXML
    private void cancel(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
    }

}
