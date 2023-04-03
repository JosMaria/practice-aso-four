package com.genesiscode.practicefour.views.panels;

import com.genesiscode.practicefour.models.Gap;
import com.genesiscode.practicefour.views.panels.commons.MessageBox;
import com.genesiscode.practicefour.views.panels.commons.PanelsCommons;
import com.genesiscode.practicefour.views.panels.rows.RowGap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PanelGap extends Panel {

    private static PanelGap panelGap;
    private final Gap gap;

    private Pane paneMain;

    //Controls by top left panel
    private static Button btnAdd, btnClear;
    private static TextArea txtAreaNumbersAdded;

    //Controls by top right panel
    private static TextField txtConfidenceLevel, txtAlpha, txtBeta;
    private static Button btnStart;

    //Controls by bottom left panel
    private static TableView<RowGap> tableData;
    private Label lblValueToResult, lblResult, lblStringNumbers;

    private PanelGap() {
        super("Prueba de huecos");
        gap = new Gap();
        loadControls();
        buildPanel();
    }

    public synchronized static PanelGap getInstance() {
        if (panelGap == null) {
            panelGap = new PanelGap();
        }
        return panelGap;
    }

    public Pane getPaneMain() {
        return paneMain;
    }

    private void buildPanel() {
        VBox topLeftPanel = new VBox(10, txtAreaNumbersAdded, new HBox(10, btnAdd, btnClear));
        topLeftPanel.setAlignment(Pos.CENTER);

        HBox topPanel = new HBox(20, topLeftPanel, topRightPanel());
        topPanel.setAlignment(Pos.CENTER);

        HBox bottomPanel = new HBox(20, bottomLeftPanel(), bottomRightPanel());
        bottomPanel.setAlignment(Pos.CENTER);

        HBox centerPane = new HBox(lblStringNumbers);
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setPadding(new Insets(30));

        VBox paneMain = new VBox(lblHeader, topPanel, centerPane, bottomPanel);
        paneMain.setAlignment(Pos.CENTER);

        this.paneMain = paneMain;
    }

    //Instantiate and configure the controls
    @Override
    protected void loadControls() {
        //top left panel
        btnAdd = new Button("Agregar");
        btnAdd.setOnAction(actionEvent -> click_btn_add());

        btnClear = new Button("Limpiar");
        btnClear.setOnAction(actionEvent -> click_btn_clear());

        txtAreaNumbersAdded = new TextArea();
        txtAreaNumbersAdded.setWrapText(true);
        txtAreaNumbersAdded.setMaxWidth(350);
        txtAreaNumbersAdded.setMaxHeight(100);

        //top right panel
        txtConfidenceLevel = new TextField();
        txtConfidenceLevel.setPrefColumnCount(4);
        txtAlpha = new TextField();
        txtAlpha.setPrefColumnCount(4);
        txtBeta = new TextField();
        txtBeta.setPrefColumnCount(4);

        btnStart = new Button("Comenzar");
        btnStart.setOnAction(actionEvent -> click_btn_start());

        //bottom left panel
        tableData = new TableView<>();
        tableData.setMaxHeight(230);

        //bottom right panel
        lblValueToResult = new Label();
        lblResult = new Label();
        lblStringNumbers = new Label();
        lblStringNumbers.setFont(new Font("Gargi", 20));
    }

    private VBox topRightPanel() {
        HBox confidenceLevelPanel = new HBox(10, new Label("Nivel de Confianza"), txtConfidenceLevel);
        confidenceLevelPanel.setAlignment(Pos.CENTER);
        HBox alphaPanel = new HBox(10, new Label("Alfa (α)"), txtAlpha);
        alphaPanel.setAlignment(Pos.CENTER);
        HBox betaPanel = new HBox(10, new Label("Beta (β)"), txtBeta);
        betaPanel.setAlignment(Pos.CENTER);

        VBox topRightPanel = new VBox(10, confidenceLevelPanel, alphaPanel, betaPanel, btnStart);
        topRightPanel.setAlignment(Pos.CENTER);
        return topRightPanel;
    }

    private VBox bottomLeftPanel() {
        createTableData();
        VBox panel = new VBox(tableData);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));
        return panel;
    }

    public void createTableData() {
        TableColumn<RowGap, Integer> colGapSize = new TableColumn<>("Tamaño\ndel hueco");
        colGapSize.setCellValueFactory(new PropertyValueFactory<>("gapSize"));

        TableColumn<RowGap, Integer> colOi = new TableColumn<>("Oi");
        colOi.setCellValueFactory(new PropertyValueFactory<>("oi"));
        colOi.setPrefWidth(50);

        TableColumn<RowGap, String> colEi = new TableColumn<>("Ei");
        colEi.setCellValueFactory(new PropertyValueFactory<>("ei"));
        colEi.setPrefWidth(200);

        TableColumn<RowGap, Double> colResult = new TableColumn<>("(Oi-Ei)²/Ei");
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));

        tableData.getColumns().addAll(List.of(colGapSize, colOi, colEi, colResult));
    }

    private VBox bottomRightPanel() {
        VBox pane = new VBox(10, new Label("RESULTADO FINAL"),
                new Label("X²o < x²α,m-1"), lblValueToResult, lblResult);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private void click_btn_start() {
        if (areEmptyData()) {
            MessageBox.show("Llene todos los datos", "HUECOS");
        } else {
            try {
                gap.setAlpha(Double.parseDouble(txtAlpha.getText()));
                gap.setBeta(Double.parseDouble(txtBeta.getText()));
                gap.setConfidenceLevel(Integer.parseInt(txtConfidenceLevel.getText()));
            } catch (NumberFormatException e) {
                MessageBox.show("Los Datos ingresados tiene que ser decimales", "HUECOS");
            }
            tableData.setItems(gap.getTableResult());
            lblStringNumbers.setText(gap.getStringNumbers());
            lblValueToResult.setText(String.format("X²o < X²%s,%s",
                    gap.getOneMinusConfidenceLevel(), 5));
            lblResult.setText(String.format("%s < %s", gap.getSummation(), gap.getResultAlpha()));
            gap.clear();
        }
    }

    private boolean areEmptyData() {
        return txtAreaNumbersAdded.getLength() == 0 || txtAlpha.getLength() == 0
                || txtBeta.getLength() == 0 || txtConfidenceLevel.getLength() == 0;
    }

    public void click_btn_add() {
        String input = txtAreaNumbersAdded.getText();

        if (input.isEmpty()) {
            MessageBox.show("Introducir numeeros", "HUECOS");

        } else {
            String[] inputSplit = input.trim().split(" ");
            try {
                List<Double> numbers = Arrays.stream(inputSplit)
                        .map(textNumber -> Double.parseDouble(textNumber.trim()))
                        .collect(Collectors.toCollection(ArrayList::new));
                gap.addNumbers(numbers);
                MessageBox.show("Numeros agregados exitosamente", "HUECOS");
            } catch (NumberFormatException e) {
                MessageBox.show("El numero para agregar debe ser un decimal", "HUECOS");
            }
        }
    }

    public void click_btn_clear() {
        txtAreaNumbersAdded.setText("");
        gap.clear();
    }
}
