package com.genesiscode.practiceasofour.views.panels;

import com.genesiscode.practiceasofour.models.KolmogorovSmirnov;
import com.genesiscode.practiceasofour.views.panels.commons.MessageBox;
import com.genesiscode.practiceasofour.views.panels.commons.PanelsCommons;
import com.genesiscode.practiceasofour.views.panels.rows.RowKolmogorov;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelKolmogorov extends Panel {

    private static PanelKolmogorov panelKolmogorov;

    private Pane paneMain;
    private static Button btnStart, btnAdd, btnClear;
    private static TextField txtAlpha, txtNumberAdd;
    private static TextArea txtAreaNumbersAdded;
    private static TableView<RowKolmogorov> tableData;
    private static Label lblAlpha;
    private static Label lblValueToResult, lblResult;

    private final KolmogorovSmirnov kolmogorovSmirnov;

    private PanelKolmogorov() {
        super("Prueba de kolmogorov-smirnov");
        kolmogorovSmirnov = new KolmogorovSmirnov();
        loadControls();
        buildPane();
    }

    public synchronized static PanelKolmogorov getInstance() {
        if (panelKolmogorov == null) {
            panelKolmogorov = new PanelKolmogorov();
        }
        return panelKolmogorov;
    }

    public Pane getPaneMain() {
        return paneMain;
    }

    private void buildPane() {
        VBox paneTopLeft = PanelsCommons.paneTopLeft(txtNumberAdd, btnAdd, btnClear, txtAreaNumbersAdded);

        HBox paneTop = new HBox(30, paneTopLeft, paneTopRight());
        paneTop.setAlignment(Pos.CENTER);

        HBox paneBottom = new HBox(30, paneBottomLeft(), paneBottomRight());
        paneBottom.setAlignment(Pos.TOP_CENTER);

        VBox paneMain = new VBox(10, lblHeader, paneTop, paneBottom);
        paneMain.setAlignment(Pos.CENTER);
        this.paneMain = paneMain;
    }

    private VBox paneTopRight() {
        VBox pane = new VBox(10, new HBox(10, lblAlpha, txtAlpha), btnStart);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private VBox paneBottomLeft() {
        createTableResult();
        VBox pane = new VBox(tableData);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private VBox paneBottomRight() {
        Label lblResultTitle = new Label("D max < D α,n");
        VBox pane = new VBox(10, lblResultTitle, lblValueToResult, lblResult);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private void createTableResult() {
        TableColumn<RowKolmogorov, String> colFi = new TableColumn<>("F'");
        colFi.setCellValueFactory(new PropertyValueFactory<>("fi"));
        colFi.setPrefWidth(50);

        TableColumn<RowKolmogorov, Double> colFii = new TableColumn<>("F''");
        colFii.setCellValueFactory(new PropertyValueFactory<>("fii"));
        colFii.setPrefWidth(50);

        TableColumn<RowKolmogorov, String> colValueAbsolute = new TableColumn<>("|F' - F''|");
        colValueAbsolute.setCellValueFactory(new PropertyValueFactory<>("valueAbsolute"));
        colValueAbsolute.setPrefWidth(100);

        tableData.getColumns().addAll(List.of(colFi, colFii, colValueAbsolute));
    }

    @Override
    protected void loadControls() {
        //pane top left
        txtNumberAdd = new TextField();
        txtNumberAdd.setPromptText("Numero para agregar");
        txtNumberAdd.setPrefColumnCount(4);

        btnAdd = new Button("Agregar");
        btnAdd.setOnAction(eventAction -> click_btn_add());

        btnClear = new Button("Limpiar");
        btnClear.setOnAction(actionEvent -> click_btn_clear());

        txtAreaNumbersAdded = new TextArea();
        txtAreaNumbersAdded.setDisable(true);
        txtAreaNumbersAdded.setMaxHeight(90);

        //pane top right
        lblAlpha = new Label("alfa (α%)");
        lblValueToResult = new Label();
        lblResult = new Label();

        txtAlpha = new TextField();
        txtAlpha.setPrefColumnCount(4);

        btnStart = new Button("Start");
        btnStart.setOnAction(actionEvent -> click_btn_start());

        //pane bottom left
        tableData = new TableView<>();
        tableData.setMaxWidth(200);
    }

    private void click_btn_add() {
        try {
            kolmogorovSmirnov.addNumber(action_btn_add(txtNumberAdd, txtAreaNumbersAdded));
        } catch (NumberFormatException ex) {
            MessageBox.show("El numero debe ser \ndecimal para agregarlo", "KolmogorovSmirnov");
        }
    }

    private void click_btn_clear() {
        txtAreaNumbersAdded.setText("");
        kolmogorovSmirnov.clear();
        setValueDefaultOfCounterTextArea();
    }

    private void click_btn_start() {
        if (txtAreaNumbersAdded.getLength() > 0 && txtAlpha.getLength() > 0) {
            try {
                kolmogorovSmirnov.setAlpha(Integer.parseInt(txtAlpha.getText()));
            } catch(NumberFormatException e) {
                MessageBox.show("El valor de alfa debe ser entero", "KolmogorovSmirnov");
            }
            tableData.setItems(kolmogorovSmirnov.showTable());
            lblValueToResult.setText(String.format("D max < D %s,%s", kolmogorovSmirnov.getAlphaDecimal(),
                    kolmogorovSmirnov.getSizeNumbers()));
            lblResult.setText(String.format("%s < %s", kolmogorovSmirnov.getValueBiggest(),
                    kolmogorovSmirnov.getValueDAlphaN()));
        } else {
            MessageBox.show("Llenar todos los datos: \nLos numeros y el valor de alfa", "KolmogorovSmirnov");
        }
    }
}
