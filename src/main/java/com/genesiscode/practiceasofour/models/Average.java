package com.genesiscode.practiceasofour.models;

import com.genesiscode.practiceasofour.models.utils.AverageUtils;
import com.genesiscode.practiceasofour.views.panels.rows.RowAverage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Average {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    //inputs of the user
    private int alpha;
    private List<Double> numbers;

    // values defined
    private double halfGiven;
    private double standardDeviation;

    public Average() {
        loadData();
    }

    public void loadData() {
        numbers = new ArrayList<>();
        halfGiven = 0.5;
        standardDeviation = Double.parseDouble(decimalFormat.format(Math.sqrt(0.083)));
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void addNumber(double number) {
        numbers.add(number);
    }

    public void clear() {
        numbers.clear();
    }

    public ObservableList<RowAverage> getAllRows() {
        ObservableList<RowAverage> rows = FXCollections.observableArrayList();
        rows.clear();
        int n = numbers.size();
        double halfCalculated = AverageUtils.getHalfCalculated(numbers, n);
        double alphaDecimal = AverageUtils.getAlphaDecimal(alpha);
        double zo = AverageUtils.getZo(halfGiven, halfCalculated, n, standardDeviation);
        double zoAlphaDiv2 = AverageUtils.getZoAlphaDiv2(alpha);
        String messageResult = AverageUtils.getMessageResult(zo, zoAlphaDiv2);

        RowAverage rowOne = new RowAverage("# elementos", String.valueOf(n));
        RowAverage rowTwo = new RowAverage("Media calculada (X)", String.valueOf(halfCalculated));
        RowAverage rowThree = new RowAverage("alfa (α)", String.valueOf(alphaDecimal));
        RowAverage rowFour = new RowAverage("|Zo|", String.valueOf(zo));
        RowAverage rowFive = new RowAverage("Z(α/2)", String.valueOf(zoAlphaDiv2));
        RowAverage rowSix = new RowAverage("MENSAJE", messageResult);

        rows.addAll(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix);
        return rows;
    }

}
