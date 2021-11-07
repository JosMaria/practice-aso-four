package com.genesiscode.practiceasofour.views.panels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Panel {

    protected VBox paneMain;
    protected Label header;

    public Panel(VBox paneMain, String titleHeader) {
        this.paneMain = paneMain;
        header = new Label(titleHeader);
        header.setFont(new Font("Gargi", 20));
        paneMain.getChildren().add(header);
        paneMain.setAlignment(Pos.TOP_CENTER);
    }

    protected Pane getPaneMain() {
        return paneMain;
    }
}
