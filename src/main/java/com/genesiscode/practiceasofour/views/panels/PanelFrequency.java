package com.genesiscode.practiceasofour.views.panels;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelFrequency extends Panel {

    private static PanelFrequency panelFrequency;

    private static Button btnStart;
    private static TextField txtAlpha, txtK;

    private PanelFrequency() {
        super("Prueba de frecuencias");
    }

    public synchronized static PanelFrequency getInstance() {
        if (panelFrequency == null) {
            panelFrequency = new PanelFrequency();
        }
        return panelFrequency;
    }

    private VBox paneTopRight() {
        Label lblAlpha = new Label("alfa (α%)");
        Label lblK = new Label("k ");

        HBox paneAlpha = new HBox(10, lblAlpha, txtAlpha);
        paneAlpha.setAlignment(Pos.CENTER);
        HBox paneK = new HBox(10, lblK, txtK);
        paneK.setAlignment(Pos.CENTER);
        VBox paneTopRight = new VBox(10, paneAlpha, paneK, btnStart);
        paneTopRight.setAlignment(Pos.CENTER);

        return paneTopRight;
    }

    @Override
    public void loadControls() {
        //pane top tight
        btnStart = new Button("Comenzar");
        txtAlpha = new TextField();
        txtK = new TextField();
    }
}
