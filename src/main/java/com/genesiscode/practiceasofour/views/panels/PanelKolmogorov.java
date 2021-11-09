package com.genesiscode.practiceasofour.views.panels;

public class PanelKolmogorov extends Panel {

    private static PanelKolmogorov panelKolmogorov;

    private PanelKolmogorov() {
        super("Prueba de kolmogorov-smirnov");
    }

    public synchronized static PanelKolmogorov getInstance() {
        if (panelKolmogorov == null) {
            panelKolmogorov = new PanelKolmogorov();
        }
        return panelKolmogorov;
    }
}
