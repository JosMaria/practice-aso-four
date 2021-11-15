package com.genesiscode.practiceasofour.views.panels;

import com.genesiscode.practiceasofour.models.Series;
import com.genesiscode.practiceasofour.views.panels.commons.MessageBox;
import com.genesiscode.practiceasofour.views.panels.commons.PanelsCommons;
import com.genesiscode.practiceasofour.views.panels.rows.RowSeries;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelSeries extends Panel {

    private static PanelSeries panelSeries;

    private Pane paneMain;
    private static Button btnAdd, btnClear, btnStart;
    private static TextField txtNumberAdd, txtAlpha, txtK;
    private static TextArea txtAreaNumbersAdded;
    private TableView<RowSeries> tableData;
    private Label lblValueAlpha, lblResult;

    private final Series series;

    private PanelSeries() {
        super("Prueba de series");
        series = new Series();
        loadControls();
        buildPaneMain();
    }

    public synchronized static PanelSeries getInstance() {
        if (panelSeries == null) {
            panelSeries = new PanelSeries();
        }
        return panelSeries;
    }

    public Pane getPaneMain() {
        return paneMain;
    }

    private void buildPaneMain() {
        VBox paneTopLeft = PanelsCommons.paneTopLeft(txtNumberAdd, btnAdd, btnClear, txtAreaNumbersAdded);
        HBox paneTop = new HBox(20, paneTopLeft, paneTopRight());
        paneTop.setAlignment(Pos.CENTER);
        HBox paneBottom = new HBox(20, paneBottomLeft(), paneBottomRight());
        paneBottom.setAlignment(Pos.CENTER);
        VBox paneMain = new VBox(10, lblHeader, paneTop, paneBottom);
        paneMain.setAlignment(Pos.CENTER);
        this.paneMain = paneMain;
    }

    private VBox paneTopRight() {
        Label lblAlpha = new Label("alfa (α)");
        HBox paneAlpha = new HBox(10, lblAlpha, txtAlpha);
        paneAlpha.setAlignment(Pos.CENTER);

        Label lblK = new Label("k");
        HBox paneK = new HBox(10, lblK, txtK);
        paneK.setAlignment(Pos.CENTER);

        VBox paneTopRight = new VBox(10, paneAlpha, paneK, btnStart);
        paneTopRight.setAlignment(Pos.CENTER);
        return paneTopRight;
    }

    private VBox paneBottomLeft() {
        createTableData();
        VBox pane = new VBox(tableData);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private VBox paneBottomRight() {
        Label lblTitleResult = new Label("Xo² < X² α,k²-1");
        VBox paneBottomLeft = new VBox(10, lblTitleResult, lblValueAlpha, lblResult);
        paneBottomLeft.setAlignment(Pos.CENTER);
        return paneBottomLeft;
    }

    private void createTableData() {
        TableColumn<RowSeries, Integer> colValue = new TableColumn<>("Celda");
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colValue.setPrefWidth(50);

        TableColumn<RowSeries, Integer> colFo = new TableColumn<>("FO");
        colFo.setCellValueFactory(new PropertyValueFactory<>("fo"));
        colFo.setPrefWidth(50);

        TableColumn<RowSeries, Double> colFe = new TableColumn<>("FE");
        colFe.setCellValueFactory(new PropertyValueFactory<>("fe"));
        colFe.setPrefWidth(50);

        TableColumn<RowSeries, Double> colX2 = new TableColumn<>("Xo²");
        colX2.setCellValueFactory(new PropertyValueFactory<>("x2"));
        colX2.setPrefWidth(50);

        tableData.getColumns().addAll(List.of(colValue, colFo, colFe, colX2));
    }

    @Override
    protected void loadControls() {
        //pane top left
        btnAdd = new Button("Agregar");
        btnAdd.setOnAction(actionEvent -> click_btn_add());
        btnClear = new Button("Limpiar");
        btnClear.setOnAction(actionEvent -> click_btn_clear());

        txtNumberAdd = new TextField();
        txtNumberAdd.setPromptText("Numero para agregar");
        txtNumberAdd.setPrefColumnCount(4);

        txtAreaNumbersAdded = new TextArea();
        txtAreaNumbersAdded.setDisable(true);
        txtAreaNumbersAdded.setMaxHeight(180);
        txtAreaNumbersAdded.setMaxWidth(240);

        //pane top right
        txtAlpha = new TextField();
        txtAlpha.setPrefColumnCount(4);
        btnStart = new Button("Comenzar");
        btnStart.setOnAction(actionEvent -> click_btn_start());

        txtK = new TextField();
        txtK.setPrefColumnCount(4);

        //pane bottom left
        tableData = new TableView<>();

        //pane bottom right
        lblValueAlpha = new Label();
        lblResult = new Label();
    }

    private void click_btn_add() {
        try {
            series.addNumber(action_btn_add(txtNumberAdd, txtAreaNumbersAdded));
        } catch (NumberFormatException e) {
            MessageBox.show("El numero a agregar debe ser decimal\n y sin espacios", "Series");
        }
    }

    private void click_btn_clear() {
        txtAreaNumbersAdded.setText("");
        series.clear();
        setValueDefaultOfCounterTextArea();
    }

    private void click_btn_start() {
        if (txtAreaNumbersAdded.getLength() > 0 && txtAlpha.getLength() > 0 && txtK.getLength() > 0) {
            try {
                series.setAlpha(Integer.parseInt(txtAlpha.getText()));
                series.setK(Integer.parseInt(txtK.getText()));
            } catch(NumberFormatException e) {
                MessageBox.show("El valor de alfa y k deben ser enteros", "Series");
            }
            tableData.setItems(series.showTableResult());
            lblValueAlpha.setText(String.format("X²o < X² %s,%s",
                    series.getAlphaDecimal(), Math.pow(series.getK(), 2)-1));
            lblResult.setText(String.format("%s < %s", series.getTotalResult(), series.getValueAlphaK()));

        } else {
            MessageBox.show("Llenar todos los datos: \nLos numeros, el valor de alfa y k", "Series");
        }
    }
}
