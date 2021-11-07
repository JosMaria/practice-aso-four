package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.layout.VBox;

public class PanelKolmogorov extends Panel {

    private static PanelKolmogorov panelKolmogorov;

    private PanelKolmogorov() {
        super(new VBox(10), "Prueba de kolmogorov-smirnov");
    }

    public synchronized static PanelKolmogorov getInstance() {
        if (panelKolmogorov == null) {
            panelKolmogorov = new PanelKolmogorov();
        }
        return panelKolmogorov;
    }
}
