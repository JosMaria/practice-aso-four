package com.genesiscode.practiceasofour.views.panels;

import com.genesiscode.practiceasofour.views.panels.commons.PanelsCommons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PanelPoker extends Panel {

    private static PanelPoker panelPoker;

    //Panel main
    private Pane paneMain;

    //Controls by top left panel
    private static Button btnAdd, btnClear;
    private static TextField txtNumberAdd;
    private static TextArea txtAreaNumbersAdded;
    private static RadioButton rdoThreeDecimals, rdoFourDecimals, rdoFiveDecimals;

    //Controls by top right panel
    private static TextField txtLevelAcceptance;
    private static Button btnStart;

    private PanelPoker() {
        super("Prueba de poker");
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

        //pane bottom left
        //pane bottom right
    }

    private void buildPanel() {
        VBox radioButtonsPanel = new VBox(10, radioButtonsPanel());
        VBox topLeftPanel = new VBox(10, radioButtonsPanel, PanelsCommons.paneTopLeft(txtNumberAdd, btnAdd, btnClear, txtAreaNumbersAdded));
        topLeftPanel.setAlignment(Pos.CENTER);

        HBox topPanel = new HBox(10, topLeftPanel, topRightPanel());
        topPanel.setAlignment(Pos.CENTER);
        this.paneMain = topPanel;
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

    private void click_btn_clear() {
        System.out.println("Click in the button \"clear\"");
    }

    private void click_btn_add() {
        System.out.println("Click in the button \"add\"");
    }

    private void click_btn_start() {
        System.out.println("Click in the button \"start\"");
    }
}
