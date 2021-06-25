package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.service.VisitService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUp {

    private ImageView image_ok = new ImageView();
    private Label label_ok = new Label();
    static Stage stage = new Stage();
    static public Button button = new Button();
    private Image image;
    private File file_ok = new File(System.getProperty("user.home") + "\\IdeaProjects\\demoMASSpring\\src\\main\\resources\\static\\" + "ok-1976099_960_720.png");
    private File file_error = new File(System.getProperty("user.home") + "\\IdeaProjects\\demoMASSpring\\src\\main\\resources\\static\\" + "error.png");
    private Pane pane = new Pane();

    public void start_ok(String message) {

        label_ok.setLayoutX(67.0);
        label_ok.setLayoutY(43.0);

        button.setLayoutX(95.0);
        button.setLayoutY(65.0);
        button.setOnAction(event -> close());

        image_ok.setLayoutX(11.0);
        image_ok.setLayoutY(32.0);
        image_ok.setFitWidth(43.0);
        image_ok.setFitHeight(39.0);

        label_ok.setText(message);
        button.setText("OK");
        image = new Image(file_ok.toURI().toString());
        image_ok.setImage(image);

        pane.getChildren().addAll(image_ok, label_ok, button);
        pane.setStyle("-fx-background-color: #2f3129;");
        pane.setStyle("-fx-text-fill: #fff200");
        var scene = new Scene(pane, 190, 130);
        stage.setScene(scene);
        stage.show();

    }

    public void start_error(String message) {


        label_ok.setLayoutX(67.0);
        label_ok.setLayoutY(43.0);

        button.setLayoutX(95.0);
        button.setLayoutY(65.0);
        button.setOnAction(event -> close());

        image_ok.setLayoutX(11.0);
        image_ok.setLayoutY(32.0);
        image_ok.setFitWidth(43.0);
        image_ok.setFitHeight(39.0);

        label_ok.setText(message);
        button.setText("OK");
        image = new Image(file_error.toURI().toString());
        image_ok.setImage(image);

        pane.getChildren().addAll(image_ok, label_ok, button);
        pane.setStyle("-fx-background-color: #2f3129;");
        pane.setStyle("-fx-text-fill: #fff200");
        var scene = new Scene(pane, 190, 130);
        stage.setScene(scene);
        stage.show();

    }

    public void close() {
        PopUp.stage.close();
    }
}
