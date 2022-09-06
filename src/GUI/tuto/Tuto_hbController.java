/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.tuto;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Tutorials;

/**
 * FXML Controller class
 *
 * @author HO
 */
public class Tuto_hbController implements Initializable {

    @FXML
    private MediaView video_media;
    @FXML
    private JFXButton play_button;
    @FXML
    private JFXButton pause_button;
    @FXML
    private JFXButton reset_button;
    Media media;
    MediaPlayer mediaPlayer;
    private File file;
    private String video_url = null;
    @FXML
    private HBox box;
    @FXML
    private Label gameNameLabel;
    @FXML
    private Label usernameLabel;
    Tutorials tutorals;
    private String[] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};
    @FXML
    private FontAwesomeIconView icon_tuto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        file = new File(video_url);
//        media = new Media(video_url);
//        mediaPlayer = new MediaPlayer(media);
//        video_media.setMediaPlayer(mediaPlayer);

    }

    @FXML
    private void playMedia(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void pauseMedia(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void resertMedia(ActionEvent event) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }

    public void setData(Tutorials tutorials) {
        video_url = tutorials.getVideo();
        media = new Media(video_url);
        mediaPlayer = new MediaPlayer(media);
        video_media.setMediaPlayer(mediaPlayer);
        gameNameLabel.setText(tutorials.getGame_name());
        usernameLabel.setText(tutorials.getOwner());

        box.setStyle("-fx-background-color: white;"
                + " -fx-background-radius: 15 ;"
                + "-fx-effect: dropShadown(three-pass-box , rgba(0,0,0,0),10,0,0,10);"
        );

    }

    @FXML
    private void play_tuto(MouseEvent event) {
        Stage stage = new Stage();
        WebView webview = new WebView();
        webview.getEngine().load(
                video_url
        );
        webview.setPrefSize(1200, 660);

        stage.setScene(new Scene(webview));
        stage.show();
    }
}
