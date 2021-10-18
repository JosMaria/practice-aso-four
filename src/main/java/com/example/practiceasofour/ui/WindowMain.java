package com.example.practiceasofour.ui;

import com.example.practiceasofour.ui.panels.PanelAverage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowMain extends Application {

    private static final String UNIFORMITY = "_Pruebas de Uniformidad";
    private static final String AVERAGE = "_Prueba de Promedios";
    private MenuBar menuBar;
    private PanelAverage panelAverage;

    private VBox panel = new VBox();;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        loadPanels();

        Scene scene = new Scene(panel, 600, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void loadPanels() {
        menuBar = getMenuBar();
        panel.getChildren().add(menuBar);

        panelAverage = new PanelAverage();
    }

    private MenuItem menuItemAverage;

    private MenuBar getMenuBar() {
        menuItemAverage = new MenuItem(AVERAGE);
        menuItemAverage.setOnAction(this::menuAction);

        Menu menuUniformity = new Menu(UNIFORMITY);
        menuUniformity.getItems().add(menuItemAverage);

        return new MenuBar(menuUniformity);
    }

    private void menuAction(ActionEvent actionEvent) {
        panel.getChildren().clear();
        if (actionEvent.getSource().equals(menuItemAverage)) {
            panel.getChildren().addAll(menuBar, panelAverage.getPanelMain());
        }
    }
}
