package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.layout.VBox;

public class PanelFrequency extends Panel {

    private static PanelFrequency panelFrequency;

    private PanelFrequency() {
        super(new VBox(10), "Prueba de frecuencias");
    }

    public synchronized static PanelFrequency getInstance() {
        if (panelFrequency == null) {
            panelFrequency = new PanelFrequency();
        }
        return panelFrequency;
    }
}
