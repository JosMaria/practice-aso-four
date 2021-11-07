package com.genesiscode.practiceasofour.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class PanelMenuBar implements EventHandler<ActionEvent> {

    private static final String UNIFORMITY = "_Pruebas de Uniformidad";
    private static final String AVERAGE = "_Prueba de promedios";
    private static final String FREQUENCY = "_Prueba de las frecuencias";
    private static final String KOLMOGOROV = "_Prueba de las kolmogorov-smirnov";

    private static final String INDEPENDENCE = "_Pruebas de Independencia";
    private static final String SERIES = "_Pruebas de series";
    private static final String POKER = "_Pruebas de poker";
    private static final String GAP = "_Pruebas de huecos";

    private final MenuItem menuItemAverage, menuItemFrequency, menuItemKolmogorov,
    menuItemSeries, menuItemPoker, menuItemGap;

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
    }

    public MenuBar getMenuBar() {
        Menu menuUniformity = new Menu(UNIFORMITY);
        menuUniformity.getItems().addAll(menuItemAverage, menuItemFrequency, menuItemKolmogorov);

        Menu menuIndependence = new Menu(INDEPENDENCE);
        menuIndependence.getItems().addAll(menuItemSeries, menuItemPoker, menuItemGap);

        return new MenuBar(menuUniformity, menuIndependence);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        MenuItem source = (MenuItem) actionEvent.getSource();
        if (source.equals(menuItemAverage)) {
            System.out.println("This is menu item average");

        } else if (source.equals(menuItemFrequency)) {
            System.out.println("This is menu item frequency");

        } else if (source.equals(menuItemKolmogorov)) {
            System.out.println("This is menu item kolmogorov");

        } else if(source.equals(menuItemSeries)) {
            System.out.println("This is menu item series");

        } else if (source.equals(menuItemPoker)) {
            System.out.println("This is menu item poker");

        } else if (source.equals(menuItemGap)) {
            System.out.println("This is menu item gap");
        }
    }
}
