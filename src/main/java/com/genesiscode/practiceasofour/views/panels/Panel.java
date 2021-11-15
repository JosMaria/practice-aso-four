package com.genesiscode.practiceasofour.views.panels;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class Panel {

    protected static final int COUNT_TEXT_SPACE = 25;
    protected int counterTextArea = COUNT_TEXT_SPACE;
    protected Label lblHeader;

    protected Panel(String titleHeader) {
        lblHeader = new Label(titleHeader);
        lblHeader.setFont(new Font("Gargi", 20));
    }

    protected Label getLblHeader() {
        return lblHeader;
    }

    protected void loadControls() {}

    protected void setValueDefaultOfCounterTextArea() {
        counterTextArea = COUNT_TEXT_SPACE;
    }

    protected double action_btn_add(TextField txtNumberAdd, TextArea txtAreaNumbersAdded) throws NumberFormatException {
        String textToAdd = txtNumberAdd.getText();
        double numberAdded = Double.parseDouble(textToAdd);
        txtNumberAdd.setText("");
        String oldText = txtAreaNumbersAdded.getText();

        if (oldText.length() == 0) {
            txtAreaNumbersAdded.setText(String.valueOf(numberAdded));
        } else if (oldText.length() + textToAdd.length() > counterTextArea) {
            txtAreaNumbersAdded.setText(oldText + "\n" + numberAdded);
            counterTextArea += COUNT_TEXT_SPACE;
        } else {
            txtAreaNumbersAdded.setText(oldText + "  "  + numberAdded);
        }
        return numberAdded;
    }
}
