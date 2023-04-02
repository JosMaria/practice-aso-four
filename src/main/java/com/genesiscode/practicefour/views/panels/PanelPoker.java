package com.genesiscode.practicefour.views.panels;

import com.genesiscode.practicefour.models.Poker;
import com.genesiscode.practicefour.models.utils.PokerElement;
import com.genesiscode.practicefour.models.utils.PokerUtils;
import com.genesiscode.practicefour.views.panels.commons.MessageBox;
import com.genesiscode.practicefour.views.panels.rows.RowPoker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.genesiscode.practicefour.models.utils.PokerUtils.numberToAddIsCorrectWithTheCountOfDecimals;

public class PanelPoker extends Panel {

    public static final int THREE_DECIMALS = 3;
    public static final int FOUR_DECIMALS = 4;
    public static final int FIVE_DECIMALS = 5;
    private static PanelPoker panelPoker;
    private final Poker poker;
    private int numberOfCategories;
    private Pane paneMain;
    private final static String MESSAGE = "Se rechaza la hipotesis de que los numeros provienen de una distribucion independiente";
    //Controls by top left panel
    private static Button btnVerify, btnClear;
    private static TextField txtNumberAdd;
    private static TextArea txtAreaNumbersAdded;
    private TextArea txtAreaNumbers;

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

        btnVerify = new Button("Verificar");
        btnVerify.setOnAction(actionEvent -> click_btn_verify());

        btnClear = new Button("Limpiar");
        btnClear.setOnAction(actionEvent -> click_btn_clear());

        txtAreaNumbersAdded = new TextArea();
        txtAreaNumbersAdded.setWrapText(true);
        txtAreaNumbersAdded.setDisable(true);
        txtAreaNumbersAdded.setMaxWidth(350);
        txtAreaNumbersAdded.setMaxHeight(100);

        txtAreaNumbers = new TextArea();
        txtAreaNumbers.setWrapText(true);
        txtAreaNumbers.setMaxWidth(450);
        txtAreaNumbers.setMaxHeight(200);

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
        VBox topLeftPanel = new VBox(10, radioButtonsPanel);
        topLeftPanel.setAlignment(Pos.CENTER);

        HBox topPanel = new HBox(10, topLeftPanel, topRightPanel());
        topPanel.setAlignment(Pos.CENTER);

        HBox bottomPanel = new HBox(10, bottomLeftPanel(), bottomRightPanel());
        bottomPanel.setAlignment(Pos.CENTER);

        VBox paneMain = new VBox(lblHeader, topPanel, bottomPanel);
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

        HBox paneButtons = new HBox(10, btnClear, btnVerify);
        VBox radioButtonsPanel = new VBox(10, new Label("Cantidad de Decimales"), paneRadioButtons, txtAreaNumbers, paneButtons);
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
        txtAreaNumbers.setText("");
    }

    private void click_btn_verify() {
        String input = txtAreaNumbers.getText();
        if (input.isEmpty()) {
            MessageBox.show("Introducir numeros al area de texto.", "POKER");
        } else if (!inputDataIsCorrect()) {
            MessageBox.show("Los numeros no coinciden con la cantidad de decimales seleccionada.", "POKER");
        } else {
            poker.setDegreesOfFreedom(numberOfCategories);
            poker.addPokerElements(buildPokerElements());
            MessageBox.show("Los datos son correctos y fueron agregados", "POKER");
        }
    }

    private boolean inputDataIsCorrect() {
        int numberOfCategories = 0;
        if (rdoThreeDecimals.isSelected()) {
            if (isCorrectCountDecimals(THREE_DECIMALS)) {
                numberOfCategories = 2;
            }
        } else if (rdoFourDecimals.isSelected()) {
            if (isCorrectCountDecimals(FOUR_DECIMALS)) {
                numberOfCategories = 4;
            }
        } else if (rdoFourDecimals.isSelected()) {
            if (isCorrectCountDecimals(FIVE_DECIMALS)) {
                numberOfCategories = 6;
            }
        }
        this.numberOfCategories = numberOfCategories;
        return numberOfCategories != 0;
    }

    private boolean isCorrectCountDecimals(int countDecimals) {
        String[] input = txtAreaNumbers.getText().split(" ");
        return Arrays.stream(input)
                .map(String::trim)
                .allMatch(inputNumber -> numberToAddIsCorrectWithTheCountOfDecimals(inputNumber, countDecimals));
    }

    private Collection<PokerElement> buildPokerElements() {
        String[] input = txtAreaNumbers.getText().split(" ");
        return Arrays.stream(input)
                .map(numberText -> new PokerElement(Double.parseDouble(numberText), numberText.trim()))
                .collect(Collectors.toList());
    }

    private void click_btn_start() {
        click_btn_verify();
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
    }

}
