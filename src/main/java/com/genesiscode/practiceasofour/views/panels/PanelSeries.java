package com.genesiscode.practiceasofour.views.panels;

public class PanelSeries extends Panel {

    private static PanelSeries panelSeries;

    private PanelSeries() {
        super("Prueba de series");
    }

    public synchronized static PanelSeries getInstance() {
        if (panelSeries == null) {
            panelSeries = new PanelSeries();
        }
        return panelSeries;
    }
}
