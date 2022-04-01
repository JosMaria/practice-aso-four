package com.genesiscode.practiceasofour.views.panels.commons;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelsCommons {

    public static VBox paneTopLeft(TextField txtNumberAdd, Button btnAdd, Button  btnClear,
                                    TextArea txtAreaNumbersAdded) {
        HBox pane = new HBox(10, txtNumberAdd, btnAdd, btnClear);
        pane.setAlignment(Pos.CENTER);
        VBox paneBottom = new VBox(10, pane, txtAreaNumbersAdded);
        paneBottom.setPadding(new Insets(0, 20, 20, 20));
        paneBottom.setAlignment(Pos.CENTER);
        return paneBottom;
    }
}
