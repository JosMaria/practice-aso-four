package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Panel {

    protected Label lblHeader;

    protected Panel(String titleHeader) {
        lblHeader = new Label(titleHeader);
        lblHeader.setFont(new Font("Gargi", 20));
    }

    public Label getLblHeader() {
        return lblHeader;
    }

    protected void loadControls() {}
}
