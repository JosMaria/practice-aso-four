package com.example.practiceasofour.ui.panels;

import com.example.practiceasofour.model.Average;
import com.example.practiceasofour.ui.MessageBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PanelAverage {

    private static final String MESSAGE_NOT_VALID = "se puede rechazar la hipotesis\n" +
                                                    "de que los numeros pseudoaletorios\n" +
                                                    "provienen de un universo uniforme.";

    private static final String MESSAGE_VALID = "NO " + MESSAGE_NOT_VALID;

    private static final String AVERAGE_CALCULATED = "Media Calculada (X) = ";
    private static final String TOTAL_NUMBERS = "n = ";
    private static final String Z_O = "|Zo| =";
    private static final String Z_ALPHA_MIDDLE = "Z (α/2) = ";
    private static final String ALPHA_PERCENTAGE = "α = ";

    private HBox panelTop, panelBottom;

    private TextField txtNumberAdd;
    private Button btnAdd, btnClear;
    private TextArea txtAreaNumbersAdded;

    private Label lblAlpha, lblAverageGiven, lblStandardDeviation;
    private TextField txtAlpha;
    private Button btnStart;

    private Label lblAverageCalculated, lblTotalNumbers, lbl_Zo, lbl_Z_alpha_middle, lblConditional, lblAlphaPercentage;

    private final Average average;
    private int limit = 25;

    private final VBox panelMain;

    public VBox getPanelMain() {
        return panelMain;
    }

    public PanelAverage() {
        panelMain = new VBox(20);
        average = new Average();
        createDataPanelTop();
        sortDataPanelTop();

        createDataPanelBottom();
        sortDataPanelBottom();

        panelMain.getChildren().addAll(panelTop, panelBottom);
    }

    private void createDataPanelTop() {
        panelTop = new HBox();
        panelTop.setAlignment(Pos.CENTER);

        txtNumberAdd = new TextField();
        btnAdd = new Button("Agregar");
        btnAdd.setOnAction(actionEvent -> click_btn_add());

        btnClear = new Button("Clear");
        btnClear.setOnAction(actionEvent -> click_btn_clear_txtAreaNumbers());

        txtAreaNumbersAdded = new TextArea();

        lblAlpha = new Label("alfa (α%)");
        lblAverageGiven = new Label("Media (μ) = ½");
        lblStandardDeviation = new Label("Desviacion Estandar\n\t(σ) = √(1/12)");
        txtAlpha = new TextField();
        btnStart = new Button("Comenzar");
        btnStart.setOnAction(actionEvent -> click_btn_start());
    }

    private void sortDataPanelTop() {
        Insets insets = new Insets(30);
        panelTop.getChildren().addAll(getPaneLeft(insets), getPaneRight(insets));
    }

    private void click_btn_clear_txtAreaNumbers() {
        txtAreaNumbersAdded.setText("");
        limit = 25;
        average.clear();
    }

    private void click_btn_add() {
        String inputNumberText = txtNumberAdd.getText().trim();
        if (inputNumberText.length() > 0) {
            try {
                average.addNumber(Double.parseDouble(inputNumberText));
            } catch (NumberFormatException e) {
                MessageBox.show("El numero que esta agregando\nno es compatible con un decimal", "Dato Invalido");
            }
        } else {
            MessageBox.show("Ingrese el numero para añadir","Dato Inexistente");
        }


        String textArea = txtAreaNumbersAdded.getText();
        if (textArea.equals("")) {
            txtAreaNumbersAdded.setText(inputNumberText);
        } else if (textArea.length() < limit) {
            txtAreaNumbersAdded.setText(textArea  + "  " + inputNumberText);
        } else {
            txtAreaNumbersAdded.setText(textArea  + "\n" + inputNumberText);
            limit += limit;
        }
        txtNumberAdd.setText("");
    }

    private void click_btn_start() {
        String inputAlphaText = txtAlpha.getText().trim();
        if (inputAlphaText.length() > 0 && txtAreaNumbersAdded.getLength() > 0) {
            try {
                average.setAlpha(Integer.parseInt(inputAlphaText));
                average.calculate();
                resultsShows();
                String result = "\t\t" + average.getValueZo() + " < " + average.getValueZ_alpha_middle() + "\n\n";
                if (average.isCorrect()) {
                    MessageBox.show(result + MESSAGE_VALID, "Calculo realizado con exito");
                } else {
                    MessageBox.show(result + MESSAGE_NOT_VALID, "Calculo realizado con exito");
                }
                click_btn_clear_txtAreaNumbers();
            } catch (NumberFormatException e) {
                MessageBox.show("El valor de alfa debe ser un entero", "Dato Invalido");
            }
        } else {
            MessageBox.show("Ingrese el valor de alfa o agregue numeros..","Dato Inexistente");
        }
    }

    private Pane getPaneLeft(Insets insets) {
        double sizeTextField = 50;
        txtNumberAdd.setPrefWidth(sizeTextField);
        txtAreaNumbersAdded.setPrefColumnCount(15);
        txtAreaNumbersAdded.setPrefRowCount(3);
        txtAreaNumbersAdded.setDisable(true);
        HBox panelDataNumbers = new HBox(20, txtNumberAdd, btnAdd, btnClear);
        panelDataNumbers.setAlignment(Pos.CENTER);

        VBox paneLeft = new VBox(20, panelDataNumbers, txtAreaNumbersAdded);
        paneLeft.setPadding(insets);
        return paneLeft;
    }

    private Pane getPaneRight(Insets insets) {
        double sizeTextField = 100;
        txtAlpha.setPrefWidth(sizeTextField);
        txtAlpha.setPromptText("α%");

        HBox panel1 = new HBox(15, lblAlpha, txtAlpha);
        panel1.setAlignment(Pos.CENTER);

        VBox panelRight = new VBox(20, lblAverageGiven, lblStandardDeviation, panel1, btnStart);
        panelRight.setPadding(insets);
        panelRight.setAlignment(Pos.CENTER);
        return panelRight;
    }

    private void createDataPanelBottom() {
        panelBottom = new HBox();
        panelBottom.setAlignment(Pos.CENTER);

        lblAverageCalculated = new Label(AVERAGE_CALCULATED);
        lblTotalNumbers = new Label(TOTAL_NUMBERS);
        lblConditional = new Label("|Zo| < Z (α/2)");
        lbl_Zo = new Label(Z_O);
        lbl_Z_alpha_middle = new Label(Z_ALPHA_MIDDLE);
        lblAlphaPercentage = new Label(ALPHA_PERCENTAGE);
    }

    private void resultsShows() {
        lblAverageCalculated.setText(AVERAGE_CALCULATED + average.getHalf());
        lblTotalNumbers.setText(TOTAL_NUMBERS + average.getN());
        lbl_Zo.setText(Z_O + average.getValueZo());
        lbl_Z_alpha_middle.setText(Z_ALPHA_MIDDLE + average.getValueZ_alpha_middle());
        lblAlphaPercentage.setText(ALPHA_PERCENTAGE + average.getAlpha());
    }

    private void sortDataPanelBottom() {
        Insets insets = new Insets(20);
        VBox paneLeftBottom = new VBox(20, lblAverageCalculated, lblTotalNumbers, lblAlphaPercentage);
        paneLeftBottom.setPadding(insets);
        paneLeftBottom.setAlignment(Pos.CENTER_LEFT);

        VBox paneRightBottom = new VBox(20, lblConditional, lbl_Zo, lbl_Z_alpha_middle);
        paneRightBottom.setPadding(insets);
        paneRightBottom.setAlignment(Pos.CENTER_RIGHT);

        HBox paneMainBottom = new HBox(30, paneLeftBottom, paneRightBottom);

        panelBottom.getChildren().add(paneMainBottom);
    }
}
