package com.pl.bg.javamasproject.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class PopUp {

    private double width = 0;
    private double height = 0;

    /**
     * If window size has to be changed (longer message)
     * @param width
     * @param height
     */
    public PopUp(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public PopUp() {
    }

    private ImageView image_ok = new ImageView();
    private Label label_ok = new Label();
    static Stage stage = new Stage();
    static public Button button = new Button();
    private Image image;
    private File file_ok = new File(System.getProperty("user.home") + "\\IdeaProjects\\demoMASSpring\\src\\main\\resources\\static\\" + "ok-1976099_960_720.png");
    private File file_error = new File(System.getProperty("user.home") + "\\IdeaProjects\\demoMASSpring\\src\\main\\resources\\static\\" + "error.png");
    private Pane pane = new Pane();

    /**
     * showing window ok (img success) with message past as method parameter
     * @param message
     */
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
        pane.setStyle("-fx-background-color: #686767;");
        pane.setStyle("-fx-text-fill: #fff200");
        Scene scene = null;
        if (width != 0 && height != 0) {
            scene = new Scene(pane, width, height);
            button.setLayoutX(pane.getWidth()/2); //button should be in the center of window
        } else {
            scene = new Scene(pane, 190, 130);
        }
        stage.setScene(scene);
        stage.setTitle("SUCCESS");
        stage.show();

    }

    /**
     * showing window ok (img error) with message past as method parameter
     * @param message
     */
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
        pane.setStyle("-fx-background-color: #686767;");
        pane.setStyle("-fx-text-fill: #fff200");
        Scene scene = null;
        if (width != 0 && height != 0) {
            scene = new Scene(pane, width, height);
            button.setLayoutX(pane.getWidth()/2);
        } else {
            scene = new Scene(pane, 190, 130);
        }
        stage.setScene(scene);
        stage.setTitle("ERROR");
        stage.show();

    }

    public void close() {
        PopUp.stage.close();
    }
}
