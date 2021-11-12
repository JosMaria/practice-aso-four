package com.genesiscode.practiceasofour.views.panels;

public class PanelPoker extends Panel {

    private static PanelPoker panelPoker;

    private PanelPoker() {
        super("Prueba de poker");
    }

    public synchronized static PanelPoker getInstance() {
        if (panelPoker == null) {
            panelPoker = new PanelPoker();
        }
        return panelPoker;
    }
}
