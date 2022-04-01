package com.genesiscode.practicefour.views.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PanelMenuBar implements EventHandler<ActionEvent> {

    private static final String UNIFORMITY = "_Pruebas de Uniformidad";
    private static final String AVERAGE = "_Prueba de promedios";
    private static final String FREQUENCY = "_Prueba de las frecuencias";
    private static final String KOLMOGOROV = "_Prueba de las kolmogorov-smirnov";

    private static final String INDEPENDENCE = "_Pruebas de Independencia";
    private static final String SERIES = "_Pruebas de series";
    private static final String POKER = "_Pruebas de poker";
    private static final String GAP = "_Pruebas de huecos";

    private static final int PANEL_TESTS = 1;

    private final MenuItem menuItemAverage, menuItemFrequency, menuItemKolmogorov,
    menuItemSeries, menuItemPoker, menuItemGap;

    private PanelGap panelGap;
    private PanelPoker panelPoker;

    private VBox paneMain;
    private Pane paneBottom;

    public VBox getPaneMain() {
        return paneMain;
    }

    public PanelMenuBar() {
        menuItemAverage = new MenuItem(AVERAGE);
        menuItemAverage.setOnAction(this);

        menuItemFrequency = new MenuItem(FREQUENCY);
        menuItemFrequency.setOnAction(this);

        menuItemKolmogorov = new MenuItem(KOLMOGOROV);
        menuItemKolmogorov.setOnAction(this);

        menuItemSeries = new MenuItem(SERIES);
        menuItemSeries.setOnAction(this);

        menuItemPoker = new MenuItem(POKER);
        menuItemPoker.setOnAction(this);

        menuItemGap = new MenuItem(GAP);
        menuItemGap.setOnAction(this);

        paneBottom = new VBox();
        paneMain = new VBox(10, getMenuBar(), paneBottom);
    }

    private MenuBar getMenuBar() {
        Menu menuUniformity = new Menu(UNIFORMITY);
        menuUniformity.getItems().addAll(menuItemAverage, menuItemFrequency, menuItemKolmogorov);

        Menu menuIndependence = new Menu(INDEPENDENCE);
        menuIndependence.getItems().addAll(menuItemSeries, menuItemPoker, menuItemGap);

        return new MenuBar(menuUniformity, menuIndependence);
    }

    public void setPaneBottom(Pane paneBottom) {
        paneMain.getChildren().remove(PANEL_TESTS);
        paneMain.getChildren().add(paneBottom);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        MenuItem source = (MenuItem) actionEvent.getSource();

        if (source.equals(menuItemAverage)) {
            setPaneBottom(PanelAverage.getInstance().getPaneMain());

        } else if (source.equals(menuItemFrequency)) {
            setPaneBottom(PanelFrequency.getInstance().getPaneMain());

        } else if (source.equals(menuItemKolmogorov)) {
            setPaneBottom(PanelKolmogorov.getInstance().getPaneMain());

        } else if(source.equals(menuItemSeries)) {
            setPaneBottom(PanelSeries.getInstance().getPaneMain());

        } else if (source.equals(menuItemPoker)) {
            setPaneBottom(PanelPoker.getInstance().getPaneMain());

        } else if (source.equals(menuItemGap)) {
            setPaneBottom(PanelGap.getInstance().getPaneMain());
        }
    }
}
