package com.genesiscode.practiceasofour.views.panels;

import com.genesiscode.practiceasofour.models.Poker;
import com.genesiscode.practiceasofour.models.utils.PokerElement;
import com.genesiscode.practiceasofour.models.utils.PokerUtils;
import com.genesiscode.practiceasofour.views.panels.commons.MessageBox;
import com.genesiscode.practiceasofour.views.panels.commons.PanelsCommons;
import com.genesiscode.practiceasofour.views.panels.rows.RowPoker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelPoker extends Panel {

    private static PanelPoker panelPoker;
    private final Poker poker;
    private int numberOfCategories;
    private Pane paneMain;
    private final static String MESSAGE = "Se rechaza la hipotesis de que los numeros provienen de una distribucion independiente";
    //Controls by top left panel
    private static Button btnAdd, btnClear;
    private static TextField txtNumberAdd;
    private static TextArea txtAreaNumbersAdded;
    private static RadioButton rdoThreeDecimals, rdoFourDecimals, rdoFiveDecimals;

    //Controls by top right panel
    private static TextField txtLevelAcceptance;
    private static Button btnStart;

    //Controls by bottom left panel
    private static TableView<RowPoker> tableData;

    //Controls by bottom right panel
    private static Label lblValueToResult, lblResult;


    private PanelPoker() {
        super("Prueba de poker");
        poker = new Poker();
        loadControls();
        buildPanel();
    }

    public synchronized static PanelPoker getInstance() {
        if (panelPoker == null) {
            panelPoker = new PanelPoker();
        }
        return panelPoker;
    }

    public Pane getPaneMain() {
        return paneMain;
    }

    //Instantiate and configure the controls
    @Override
    protected void loadControls() {
        //top left panel
        txtNumberAdd = new TextField();
        txtNumberAdd.setPrefColumnCount(6);

        btnAdd = new Button("Agregar");
        btnAdd.setOnAction(actionEvent -> click_btn_add());

        btnClear = new Button("Limpiar");
        btnClear.setOnAction(actionEvent -> click_btn_clear());

        txtAreaNumbersAdded = new TextArea();
        txtAreaNumbersAdded.setWrapText(true);
        txtAreaNumbersAdded.setDisable(true);
        txtAreaNumbersAdded.setMaxWidth(350);
        txtAreaNumbersAdded.setMaxHeight(100);

        rdoThreeDecimals = new RadioButton("3 Dec");
        rdoFourDecimals = new RadioButton("4 Dec");
        rdoFiveDecimals = new RadioButton("5 Dec");
        rdoThreeDecimals.setSelected(true);

        //top right panel
        txtLevelAcceptance = new TextField();
        txtLevelAcceptance.setPrefColumnCount(4);

        btnStart = new Button("Comenzar");
        btnStart.setOnAction(actionEvent -> click_btn_start());

        //bottom left panel
        tableData = new TableView<>();
        tableData.setMaxHeight(200);

        //pane bottom right
        lblValueToResult = new Label();
        lblResult = new Label();
    }

    private void buildPanel() {
        VBox radioButtonsPanel = new VBox(10, radioButtonsPanel());
        VBox topLeftPanel = new VBox(10, radioButtonsPanel, PanelsCommons.paneTopLeft(txtNumberAdd, btnAdd, btnClear, txtAreaNumbersAdded));
        topLeftPanel.setAlignment(Pos.CENTER);

        HBox topPanel = new HBox(10, topLeftPanel, topRightPanel());
        topPanel.setAlignment(Pos.CENTER);

        HBox bottomPanel = new HBox(10, bottomLeftPanel(), bottomRightPanel());
        bottomPanel.setAlignment(Pos.CENTER);

        VBox paneMain = new VBox(topPanel, bottomPanel);
        paneMain.setAlignment(Pos.CENTER);

        this.paneMain = paneMain;
    }

    private Pane radioButtonsPanel() {
        ToggleGroup toggleGroup = new ToggleGroup();
        rdoThreeDecimals.setToggleGroup(toggleGroup);
        rdoFourDecimals.setToggleGroup(toggleGroup);
        rdoFiveDecimals.setToggleGroup(toggleGroup);

        HBox paneRadioButtons = new HBox(10, rdoThreeDecimals, rdoFourDecimals, rdoFiveDecimals);
        paneRadioButtons.setAlignment(Pos.CENTER);

        VBox radioButtonsPanel = new VBox(10, new Label("Cantidad de Decimales"), paneRadioButtons);
        radioButtonsPanel.setAlignment(Pos.CENTER);
        radioButtonsPanel.setPadding(new Insets(10));

        return radioButtonsPanel;
    }

    private VBox topRightPanel() {
        HBox paneAcceptance = new HBox(10, new Label("Nivel de aceptación"), txtLevelAcceptance);
        paneAcceptance.setAlignment(Pos.CENTER);
        VBox pane = new VBox(10, paneAcceptance, btnStart);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private VBox bottomLeftPanel() {
        createTableData();
        VBox panel = new VBox(tableData);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));
        return panel;
    }

    private void createTableData() {
        TableColumn<RowPoker, String> colCategory = new TableColumn<>("Categoria");
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCategory.setPrefWidth(150);

        TableColumn<RowPoker, Double> colProbability = new TableColumn<>("Probabilidad");
        colProbability.setCellValueFactory(new PropertyValueFactory<>("probability"));
        colProbability.setPrefWidth(100);

        TableColumn<RowPoker, Integer> colOi = new TableColumn<>("Oi");
        colOi.setCellValueFactory(new PropertyValueFactory<>("oi"));
        colOi.setPrefWidth(50);

        TableColumn<RowPoker, String> colEi = new TableColumn<>("Ei");
        colEi.setCellValueFactory(new PropertyValueFactory<>("ei"));
        colEi.setPrefWidth(150);

        TableColumn<RowPoker, Double> colResult = new TableColumn<>("(FEi-FOi)²/FEi");
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        colResult.setPrefWidth(120);

        tableData.getColumns().addAll(List.of(colCategory, colProbability, colOi, colEi, colResult));
    }

    private VBox bottomRightPanel() {
        Label lblTitleByFinalResult = new Label("RESULTADO FINAL");
        Label lblTitle = new Label("X²o < x²α,m-1");
        VBox pane = new VBox(10, lblTitleByFinalResult, lblTitle, lblValueToResult, lblResult);
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private void click_btn_clear() {
        poker.clear();
        txtAreaNumbersAdded.setText("");
    }

    private void click_btn_add() {
        String textNumber = txtNumberAdd.getText();
        txtNumberAdd.setText("");
        if (isCorrectTheSelectionGivenTheDecimal(textNumber)) {
            if (isEqualToFirstElementIfExists(textNumber)) {
                action_btn_add(txtAreaNumbersAdded, textNumber);
                try {
                    poker.setDegreesOfFreedom(numberOfCategories);
                    poker.addPokerElement(new PokerElement(Double.parseDouble(textNumber), textNumber));
                } catch (NumberFormatException e) {
                    MessageBox.show("El numero que se quiere agregar\ndebe tener formato decimal", "POKER");
                }
            } else {
                MessageBox.show("El número para agregar no tiene la misma\ncantidad de decimales que los demas numeros", "POKER");
            }
        } else {
            MessageBox.show(String.format("No se puede agregar este numero %s,\n ya que incumple las restricciones", textNumber), "POKER");
        }
    }

    private boolean isCorrectTheSelectionGivenTheDecimal(String textNumberToAdd) {
        boolean isCorrect;
        if (rdoThreeDecimals.isSelected()) {
            isCorrect = PokerUtils.numberToAddIsCorrectWithTheCountOfDecimals(textNumberToAdd, 3);
            if (isCorrect) {
                numberOfCategories = 2;
            }
        } else if (rdoFourDecimals.isSelected()) {
            isCorrect = PokerUtils.numberToAddIsCorrectWithTheCountOfDecimals(textNumberToAdd, 4);
            if (isCorrect) {
                numberOfCategories = 4;
            }
        } else {
            isCorrect = PokerUtils.numberToAddIsCorrectWithTheCountOfDecimals(textNumberToAdd, 5);
            if (isCorrect) {
                numberOfCategories = 6;
            }
        }
        return isCorrect;
    }

    private boolean isEqualToFirstElementIfExists(String textNumber) {
        boolean isEquals = false;
        try {
            PokerElement firstElement = poker.getFirstElement();
            String partDecimalFirstElement = PokerUtils.getPartDecimal(firstElement.getTextNumber());
            String partDecimalNumberToAdd = PokerUtils.getPartDecimal(textNumber);
            if (partDecimalFirstElement.length() == partDecimalNumberToAdd.length()) {
                isEquals = true;
            }
        } catch (IndexOutOfBoundsException e) {
            isEquals = true;
        }
        return isEquals;
    }

    private void click_btn_start() {
        if (txtAreaNumbersAdded.getLength() != 0 && txtLevelAcceptance.getLength() != 0) {
            try {
                poker.setLevelAcceptance(Integer.parseInt(txtLevelAcceptance.getText()));
            } catch (NumberFormatException e) {
                MessageBox.show("El nivel de aceptación debe ser un numero entero", "POKER");
            }
            tableData.setItems(poker.getTableResult());
            lblValueToResult.setText(String.format("X²o < X²%s,%s",
                    poker.getLevelAcceptanceInDecimals(), poker.getDegreesOfFreedom()));
            double resultFinal = poker.getResultFinal();
            double resultAlpha = poker.getResultAlpha();
            lblResult.setText(String.format("%s < %s", resultFinal, resultAlpha));
            if (resultFinal < resultAlpha) {
                MessageBox.show(MESSAGE, "POKER");
            }
        } else {
            MessageBox.show("Se debe agregar todos los datos necesarios", "POKER");
        }
    }
}
