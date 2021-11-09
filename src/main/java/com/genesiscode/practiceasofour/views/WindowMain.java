package com.genesiscode.practiceasofour.views;

import com.genesiscode.practiceasofour.views.panels.PanelMenuBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        PanelMenuBar panelMenuBar = new PanelMenuBar();
        Scene scene = new Scene(panelMenuBar.getPaneMain(), 600, 500);
        stage.setTitle("Practica 4");
        stage.setScene(scene);
        stage.show();
    }
}