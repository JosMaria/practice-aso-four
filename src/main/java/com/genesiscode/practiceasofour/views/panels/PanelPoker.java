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

    //Controls by pane top left
    private static Button btnAdd, btnClear;
    private static TextField txtNumberAdd;
    private static TextArea txtAreaNumbersAdded;
    private static RadioButton rdoThreeDecimals, rdoFourDecimals, rdoFiveDecimals;

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
        txtAreaNumbersAdded.setMaxWidth(270);

        rdoThreeDecimals = new RadioButton("3 Dec");
        rdoFourDecimals = new RadioButton("4 Dec");
        rdoFiveDecimals = new RadioButton("5 Dec");
        rdoThreeDecimals.setSelected(true);

        //top right panel
        //pane bottom left
        //pane bottom right
    }

    private void buildPanel() {
        VBox radioButtonsPanel = new VBox(10, radioButtonsPanel());
        VBox paneTop = new VBox(10, radioButtonsPanel,
                PanelsCommons.paneTopLeft(txtNumberAdd, btnAdd, btnClear, txtAreaNumbersAdded));
        paneTop.setAlignment(Pos.CENTER);

        this.paneMain = new VBox(10, paneTop);
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

    private void click_btn_clear() {
        System.out.println("Click in the button \"clear\"");
    }

    private void click_btn_add() {
        System.out.println("Click in the button \"add\"");
    }
}
