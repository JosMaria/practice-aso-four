package com.genesiscode.practiceasofour.views.panels;

public class PanelGap extends Panel {

    private static PanelGap panelGap;

    private PanelGap() {
        super("Prueba de huecos");
    }

    public synchronized static PanelGap getInstance() {
        if (panelGap == null) {
            panelGap = new PanelGap();
        }
        return panelGap;
    }
}
