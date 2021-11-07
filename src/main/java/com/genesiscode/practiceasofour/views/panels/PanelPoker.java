package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.layout.VBox;

public class PanelPoker extends Panel {

    private static PanelPoker panelPoker;

    private PanelPoker() {
        super(new VBox(10), "Prueba de poker");
    }

    public synchronized static PanelPoker getInstance() {
        if (panelPoker == null) {
            panelPoker = new PanelPoker();
        }
        return panelPoker;
    }
}
