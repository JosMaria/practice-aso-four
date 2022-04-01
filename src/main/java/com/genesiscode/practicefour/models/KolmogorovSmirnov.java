package com.genesiscode.practiceasofour.models;

import com.genesiscode.practiceasofour.models.formulas.TableKolmogorovSmirnov;
import com.genesiscode.practiceasofour.models.utils.KolmogorovSmirnovUtils;
import com.genesiscode.practiceasofour.views.panels.rows.RowKolmogorov;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KolmogorovSmirnov {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final TableKolmogorovSmirnov tableKolmogorovSmirnov;
    private double valueBiggest;

    //inputs
    private int alpha;
    private final List<Double> numbers;

    public KolmogorovSmirnov() {
        numbers = new ArrayList<>();
        tableKolmogorovSmirnov = TableKolmogorovSmirnov.getInstance();
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

    public int getSizeNumbers() {
        return numbers.size();
    }

    public double getAlphaDecimal() {
        return (double) alpha / 100;
    }

    public double getValueBiggest() {
        return Double.parseDouble(decimalFormat.format(valueBiggest));
    }

    public ObservableList<RowKolmogorov> showTable() {
        ObservableList<RowKolmogorov> rows = FXCollections.observableArrayList();
        valueBiggest = 0;

        List<Double> listFii = KolmogorovSmirnovUtils.sortList(numbers);
        int sizeNumbers = numbers.size();
        for (int i = 0; i < sizeNumbers; i++) {
            String fiString = i+1 + "/" + sizeNumbers;
            double fi = (double) (i + 1) / sizeNumbers;
            double fiValue = Double.parseDouble(decimalFormat.format(fi));
            System.out.println(fiValue);
            double fiiValue = listFii.get(i);
            double result = fiValue - fiiValue;
            System.out.println(result);
            double resultAbsolute = result < 0.0 ? result * -1 : result;
            if (resultAbsolute > valueBiggest) {
                valueBiggest = resultAbsolute;
            }
            rows.add(new RowKolmogorov(fiString, fiiValue, Double.parseDouble(decimalFormat.format(resultAbsolute))));
        }
        return rows;
    }

    public double getValueDAlphaN() {
        return tableKolmogorovSmirnov.getValue((double) alpha/100, numbers.size());
    }
}
