package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.layout.VBox;

public class PanelGap extends Panel {

    private static PanelGap panelGap;

    private PanelGap() {
        super(new VBox(10), "Prueba de huecos");
    }

    public synchronized static PanelGap getInstance() {
        if (panelGap == null) {
            panelGap = new PanelGap();
        }
        return panelGap;
    }
}
