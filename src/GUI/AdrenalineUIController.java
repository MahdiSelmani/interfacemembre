/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.tuto.Tutorals_membreController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.Game;
import models.Game_categoeries;
import services.Categories_jeuxServices;
import services.ServiceGame;

/**
 * FXML Controller class
 *
 * @author HO
 */
public class AdrenalineUIController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    private MenuBar menuBar_tuto;
    @FXML
    private Menu menu_tuto;

    List<Game_categoeries> typeCategoriesList = new ArrayList<>();
    List<Game> GameNames = new ArrayList<>();
    ObservableList<MenuItem> MenuItemsList = FXCollections.observableArrayList();
    @FXML
    private Pane pane_tournois;
    @FXML
    private Pane pane_tuto;
    @FXML
    private Pane pane_planifier_tournois;
    @FXML
    private Pane pane_produits;
    @FXML
    private Pane pane_add_produit;
    @FXML
    private Pane pane_home;
    String color = "white";
    int game_id;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label btn_produit_dispo;
    private boolean update = false;
    @FXML
    private FontAwesomeIconView panier_icon;
    @FXML
    private Pane pane_panier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("https://res.cloudinary.com/elife-k/image/upload/v1661599154/oussema_nrdxz4.jpg");
        circle.setFill(new ImagePattern(image));
        try {
            pane_panier.setVisible(false);
            pane_tuto.setVisible(false);
            pane_tournois.setVisible(false);
            pane_planifier_tournois.setVisible(false);
            pane_produits.setVisible(false);
            pane_add_produit.setVisible(false);
            pane_home.setVisible(true);
            Node source = (Node) FXMLLoader.load(getClass().getResource("Home.fxml"));
            pane_home.getChildren().add(source);
            pane_home.toFront();
        } catch (Exception e) {
            System.out.println(e);
        }
        getItems();
        menuBar_tuto = new MenuBar(menu_tuto);
    }

    private Menu create_menu(String type) {
        Menu menu = new Menu(type);
        return menu;
    }

    private MenuItem create_Menuitems(String Game_name) {
        MenuItem menuItem = new MenuItem(Game_name);
        return menuItem;
    }

    private void getItems() {
        Categories_jeuxServices ip = new Categories_jeuxServices();
        ServiceGame ip2 = new ServiceGame();
        typeCategoriesList = ip.afficherType();
        MenuItemsList = menu_tuto.getItems();
        for (Game_categoeries e : typeCategoriesList) {
            Menu m = create_menu(e.getType());
            GameNames = ip2.afficherNamesParCategorie(e.getId());
            for (Game g : GameNames) {
                MenuItem mi = create_Menuitems(g.getGame_name());
                m.getItems().add(mi);
                mi.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tutorals_membre.fxml"));
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AdrenalineUIController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            game_id = g.getId();
                            Tutorals_membreController tutorals_membreController = loader.getController();
                            tutorals_membreController.setGame(game_id);
                            tutorals_membreController.showTutoGames(game_id);
                            tutorals_membreController.showTutoUser(game_id);

//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
//                            setGame_id(g.getId());
//                            System.out.println(getGame_id());
                            pane_panier.setVisible(false);
                            pane_tuto.setVisible(true);
                            pane_tournois.setVisible(false);
                            pane_planifier_tournois.setVisible(false);
                            pane_produits.setVisible(false);
                            pane_add_produit.setVisible(false);
                            pane_home.setVisible(false);
//                          Node source = (Node) FXMLLoader.load(getClass().getResource("Tutorals_membre.fxml"));
                            Parent parent = loader.getRoot();
                            pane_tuto.getChildren().add(parent);
                            pane_tuto.toFront();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });

            }
            menu_tuto.getItems().add(m);
        }

    }

    public void setGame_id(int gameId) {
        this.game_id = gameId;
    }

    public int getGame_id() {
        return this.game_id;
    }

    @FXML
    private void handleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == btn_produit_dispo) {
            pane_panier.setVisible(false);
            pane_tuto.setVisible(false);
            pane_tournois.setVisible(false);
            pane_planifier_tournois.setVisible(false);
            pane_produits.setVisible(true);
            pane_add_produit.setVisible(false);
            pane_home.setVisible(false);
            Node source = (Node) FXMLLoader.load(getClass().getResource("./produits/ProduitsDispo.fxml"));
            pane_produits.getChildren().add(source);
            pane_produits.toFront();
        }

    }

    void setUpdate(boolean b) {
        this.update = b;
    }

    private void showPanePanier() throws IOException {
        if (this.update == true) {
            pane_tuto.setVisible(false);
            pane_tournois.setVisible(false);
            pane_planifier_tournois.setVisible(false);
            pane_produits.setVisible(true);
            pane_add_produit.setVisible(false);
            pane_home.setVisible(false);
            Node source = (Node) FXMLLoader.load(getClass().getResource("./GUI.produits/ProduitsDispo.fxml"));
            pane_produits.getChildren().add(source);
            pane_produits.toFront();

        }
    }

    @FXML
    private void panier_click(MouseEvent event) throws IOException {
        
            pane_panier.setVisible(true);
            pane_tuto.setVisible(false);
            pane_tournois.setVisible(false);
            pane_planifier_tournois.setVisible(false);
            pane_produits.setVisible(false);
            pane_add_produit.setVisible(false);
            pane_home.setVisible(false);
            Node source = (Node) FXMLLoader.load(getClass().getResource("./panier/panier.fxml"));
            pane_panier.getChildren().add(source);
            pane_panier.toFront();

        

    }
}
