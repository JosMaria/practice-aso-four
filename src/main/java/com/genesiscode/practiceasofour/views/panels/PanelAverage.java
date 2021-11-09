package com.genesiscode.practiceasofour.views.panels;

import com.genesiscode.practiceasofour.models.Average;
import com.genesiscode.practiceasofour.views.panels.rows.RowAverage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelAverage extends Panel {

    private static PanelAverage panelAverage;

    private Pane paneMain;
    //controls
    private static TextField txtNumberAdd;
    private static Button btnAdd, btnClear, btnStart;
    private static TextArea txtAreaNumbersAdded;
    private static TextField txtAlpha;
    private static TableView<RowAverage> tableResult;

    private int counterTextArea = 25;
    private final Average average;

    private PanelAverage() {
        super("Prueba de los promedios");
        average = new Average();
        loadControls();
        loadEvents();
        buildPane();
    }

    public synchronized static PanelAverage getInstance() {
        if (panelAverage == null) {
            panelAverage = new PanelAverage();
        }
        return panelAverage;
    }

    public Pane getPaneMain() {
        return paneMain;
    }

    private void buildPane() {
        VBox pane = new VBox(10, lblHeader, new HBox(30, paneTopLeft(), paneTopRight()), paneBottom());
        pane.setAlignment(Pos.CENTER);
        paneMain = pane;
    }

    private VBox paneTopLeft() {
        VBox paneBottom = new VBox(10, new HBox(10, txtNumberAdd, btnAdd, btnClear), txtAreaNumbersAdded);
        paneBottom.setPadding(new Insets(0, 20, 20, 20));
        return new VBox(10, paneBottom);
    }

    private VBox paneTopRight() {
        Label lblAverageGiven = new Label("Media (μ) = ½");
        Label lblStandardDeviation = new Label("Desviacion Estandar (σ) = √(1/12)");
        HBox paneAlpha = new HBox(10, new Label("alfa (α%)"), txtAlpha);
        paneAlpha.setAlignment(Pos.CENTER);
        VBox pane = new VBox(10, lblAverageGiven, lblStandardDeviation, paneAlpha, btnStart);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private VBox paneBottom() {
        TableColumn<RowAverage, String> colVariable = new TableColumn<>("Variable");
        colVariable.setMinWidth(200);
        colVariable.setCellValueFactory(new PropertyValueFactory<>("variable"));

        TableColumn<RowAverage, String> colValue = new TableColumn<>("Valor");
        colValue.setMinWidth(250);
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableResult.getColumns().addAll(List.of(colVariable, colValue));

        VBox pane = new VBox(tableResult);
        pane.setMaxWidth(500);
        pane.setMaxHeight(500);
        pane.setPadding(new Insets(20));
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    @Override
    public void loadControls() {
        //pane top left
        txtNumberAdd = new TextField();
        txtNumberAdd.setPrefColumnCount(4);
        btnAdd = new Button("Agregar");
        btnClear = new Button("Clear");
        txtAreaNumbersAdded = new TextArea();
        txtAreaNumbersAdded.setDisable(true);
        txtAreaNumbersAdded.setMaxWidth(210);
        txtAreaNumbersAdded.setPrefRowCount(5);

        //pane top right
        txtAlpha = new TextField();
        txtAlpha.setPrefColumnCount(4);
        btnStart = new Button("Comenzar");

        //pane bottom
        tableResult = new TableView<>();
    }

    private void loadEvents() {
        btnAdd.setOnAction(actionEvent -> click_btn_add());
        btnClear.setOnAction(actionEvent -> click_btn_clear());
        btnStart.setOnAction(actionEvent -> click_btn_start());
    }

    private void click_btn_add() {
        try {
            String textToAdd = txtNumberAdd.getText();
            txtNumberAdd.setText("");
            double numberAdded = Double.parseDouble(textToAdd);
            String oldText = txtAreaNumbersAdded.getText();

            if (oldText.length() == 0) {
                txtAreaNumbersAdded.setText(textToAdd);
            } else if (oldText.length() + textToAdd.length() > counterTextArea) {
                txtAreaNumbersAdded.setText(oldText + "\n" + numberAdded);
                counterTextArea += 25;
            } else {
                txtAreaNumbersAdded.setText(oldText + "  "  + numberAdded);
            }
            average.addNumber(numberAdded);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void click_btn_clear() {
        txtAreaNumbersAdded.setText("");
        average.clear();
    }

    private void click_btn_start() {
        if (txtAreaNumbersAdded.getLength() > 0 && txtAlpha.getLength() > 0) {
            try {
                int alphaPercentage = Integer.parseInt(txtAlpha.getText());
                average.setAlpha(alphaPercentage);
                tableResult.setItems(average.getAllRows());
            } catch (NumberFormatException ex) {
                System.out.println("Valor de alfa no numerico");
            }
        } else {
            System.out.println("Llenar los datos");
        }
    }

}
