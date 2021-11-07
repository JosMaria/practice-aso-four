package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.layout.VBox;

public class PanelSeries extends Panel {

    private static PanelSeries panelSeries;

    private PanelSeries() {
        super(new VBox(10), "Prueba de series");
    }

    public synchronized static PanelSeries getInstance() {
        if (panelSeries == null) {
            panelSeries = new PanelSeries();
        }
        return panelSeries;
    }
}
