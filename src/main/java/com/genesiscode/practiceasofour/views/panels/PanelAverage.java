package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PanelAverage extends Panel {

    private static PanelAverage panelAverage;

    private PanelAverage() {
        super(new VBox(10), "Prueba de los promedios");
    }

    public synchronized static PanelAverage getInstance() {
        if (panelAverage == null) {
            panelAverage = new PanelAverage();
        }
        return panelAverage;
    }
}
