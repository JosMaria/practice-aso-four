package com.genesiscode.practiceasofour.views.panels;

import com.genesiscode.practiceasofour.models.Frequency;
import com.genesiscode.practiceasofour.views.panels.commons.PanelsCommons;
import com.genesiscode.practiceasofour.views.panels.rows.RowFrequency;
import com.genesiscode.practiceasofour.views.panels.rows.RowFrequencyInterval;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelFrequency extends Panel {

    private static PanelFrequency panelFrequency;

    private Pane paneMain;
    //controls
    private static Button btnStart, btnAdd, btnClear;
    private static TextField txtAlpha, txtK, txtNumberAdd;
    private static TextArea txtAreaNumbersAdded;
    private static TableView<RowFrequency> tableResult;
    private static TableView<RowFrequencyInterval> tableIntervals;
    private static Label lblResult;

    private final Frequency frequency;

    private PanelFrequency() {
        super("Prueba de frecuencias");
        frequency = new Frequency();
        loadControls();
        loadEvents();
        buildPane();
    }

    public synchronized static PanelFrequency getInstance() {
        if (panelFrequency == null) {
            panelFrequency = new PanelFrequency();
        }
        return panelFrequency;
    }

    public Pane getPaneMain() {
        return paneMain;
    }

    private void buildPane() {
        VBox paneTopLeft = PanelsCommons.paneTopLeft(txtNumberAdd, btnAdd, btnClear, txtAreaNumbersAdded);
        HBox paneTop = new HBox(40, paneTopLeft, paneTopRight());
        paneTop.setAlignment(Pos.CENTER);
        VBox paneMain = new VBox(10, lblHeader, paneTop, paneBottom());
        paneMain.setAlignment(Pos.CENTER);
        this.paneMain = paneMain;
    }

    private VBox paneTopRight() {
        Label lblAlpha = new Label("alfa (α%)");
        Label lblK = new Label("k ");

        HBox paneAlpha = new HBox(10, lblAlpha, txtAlpha);
        paneAlpha.setAlignment(Pos.CENTER);
        HBox paneK = new HBox(10, lblK, txtK);
        paneK.setAlignment(Pos.CENTER);
        VBox paneTopRight = new VBox(10, paneAlpha, paneK, btnStart);
        paneTopRight.setAlignment(Pos.TOP_CENTER);

        return paneTopRight;
    }

    private VBox paneBottom() {
        createTableResult();
        createTableIntervals();

        VBox paneTables = new VBox(10, tableIntervals, tableResult, lblResult);
        paneTables.setPadding(new Insets(10));
        paneTables.setAlignment(Pos.CENTER);
        return paneTables;
    }

    private void createTableResult() {
        TableColumn<RowFrequency, String> colInterval = new TableColumn<>("Intervalos");
        colInterval.setPrefWidth(100);
        colInterval.setCellValueFactory(new PropertyValueFactory<>("interval"));

        TableColumn<RowFrequency, Integer> colFrequencyObserved = new TableColumn<>("FO");
        colFrequencyObserved.setPrefWidth(50);
        colFrequencyObserved.setCellValueFactory(new PropertyValueFactory<>("frequencyObserved"));

        TableColumn<RowFrequency, Double> colFrequencyExpected = new TableColumn<>("FE");
        colFrequencyExpected.setPrefWidth(50);
        colFrequencyExpected.setCellValueFactory(new PropertyValueFactory<>("frequencyExpected"));

        TableColumn<RowFrequency, String> colResult = new TableColumn<>("Resultado");
        colResult.setPrefWidth(250);
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));

        tableResult.getColumns().addAll(List.of(colInterval, colFrequencyObserved, colFrequencyExpected, colResult));
    }

    private void createTableIntervals() {
        TableColumn<RowFrequencyInterval, String> colInterval = new TableColumn<>("Intervalos");
        colInterval.setPrefWidth(100);
        colInterval.setCellValueFactory(new PropertyValueFactory<>("interval"));

        TableColumn<RowFrequencyInterval, String> colValues = new TableColumn<>("Valores");
        colValues.setPrefWidth(300);
        colValues.setCellValueFactory(new PropertyValueFactory<>("values"));

        tableIntervals.getColumns().addAll(List.of(colInterval, colValues));
    }

    @Override
    public void loadControls() {
        //pane top tight
        btnStart = new Button("Comenzar");
        txtAlpha = new TextField();
        txtAlpha.setPrefColumnCount(4);
        txtK = new TextField();
        txtK.setPrefColumnCount(4);

        //pane top left
        btnAdd = new Button("Agregar");
        btnClear = new Button("Limpiar");
        txtAreaNumbersAdded = new TextArea();
        txtAreaNumbersAdded.setDisable(true);
        txtAreaNumbersAdded.setMaxWidth(225);
        txtAreaNumbersAdded.setMinHeight(70);
        txtNumberAdd = new TextField();
        txtNumberAdd.setPrefColumnCount(4);

        //pane bottom
        tableResult = new TableView<>();
        tableResult.setMaxWidth(450);
        tableIntervals = new TableView<>();
        tableIntervals.setMaxWidth(400);
        lblResult = new Label();
    }

    private void loadEvents() {
        btnClear.setOnAction(actionEvent -> click_btn_clear());
        btnAdd.setOnAction(actionEvent -> click_btn_add());
        btnStart.setOnAction(actionEvent -> click_btn_start());
    }

    private void click_btn_clear() {
        txtAreaNumbersAdded.setText("");
        frequency.clear();
    }

    private void click_btn_add() {
        try {
            frequency.addNumber(action_btn_add(txtNumberAdd, txtAreaNumbersAdded));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void click_btn_start() {
        if (txtAreaNumbersAdded.getLength() > 0 && txtAlpha.getLength() > 0 && txtK.getLength() > 0) {
            try {
                frequency.setAlpha(Integer.parseInt(txtAlpha.getText()));
                frequency.setK(Integer.parseInt(txtK.getText()));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            tableIntervals.setItems(frequency.showTableIntervalsAndValues());
            tableResult.setItems(frequency.showTableResult());
            lblResult.setText(String.format("%s < %s", frequency.getResultTotal(), frequency.getValueZAlphaK()));
        } else {
            System.out.println("Llenar los datos");
        }
    }
}
