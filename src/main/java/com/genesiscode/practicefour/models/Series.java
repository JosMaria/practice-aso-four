package com.genesiscode.practicefour.models;

import com.genesiscode.practicefour.models.formulas.TableSeries;
import com.genesiscode.practicefour.models.utils.Decimal;
import com.genesiscode.practicefour.models.utils.SeriesCell;
import com.genesiscode.practicefour.models.utils.SeriesPair;
import com.genesiscode.practicefour.models.utils.SeriesUtils;
import com.genesiscode.practicefour.views.panels.rows.RowSeries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Series {

    private SeriesCell[][] cells;
    private List<SeriesPair> pairs;
    private double totalResult;
    private final TableSeries tableSeries;

    //inputs
    private int alpha;
    private int k;
    private final List<Double> numbers;

    private String[][] matrixString;

    public Series() {
        numbers = new ArrayList<>();
        tableSeries = TableSeries.getInstance();
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

    public double getTotalResult() {
        return Decimal.getDecimal(2, totalResult);
    }

    public void addNumbers(Collection<Double> numbers) {
        this.numbers.addAll(numbers);
    }

    public void clear() {
        numbers.clear();
    }
    public double getAlphaDecimal() {
        return (double) alpha / 100;
    }

    public double getFrequencyExpected() {
        return Decimal.getDecimal(2, (numbers.size() - 1)/ Math.pow(k,2));
    }

    private SeriesCell[][] loadValuesInCells() {
        pairs = SeriesUtils.loadListPairs(numbers);
        cells = SeriesUtils.cells(k);

        for (SeriesPair pair : pairs) {
            foundCell(pair);
        }
        return cells;
    }

    private void foundCell(SeriesPair pair) {
        boolean found = false;
        for (int i = 0; i < k && ! found; i++) {
            for (int j = 0; j < k && ! found; j++) {
                SeriesCell cell = cells[i][j];
                if (cell.getInitialX() <= pair.getX() && pair.getX() < cell.getFinalX()
                        && cell.getInitialY() > pair.getY() && pair.getY() >= cell.getFinalY()) {
                    cell.setValue(cell.getValue() + 1);
                    found = true;
                }
            }
        }
    }

    public ObservableList<RowSeries> showTableResult() {
        SeriesCell[][] seriesCells = loadValuesInCells();
        ObservableList<RowSeries> rows = FXCollections.observableArrayList();
        totalResult = 0.0;
        String[][] matrixStringTemp = new String[k][k];
        double fe = getFrequencyExpected();
        int counterRow = 1;

        for (int i = 0; i < k; i++) {
            for (int j = k - 1; j >= 0; j--, counterRow++) {
                int fo = seriesCells[j][i].getValue();
                matrixStringTemp[j][i] = String.valueOf(fo);
                double result = Math.pow(fo - fe, 2) / fe;
                result = Decimal.getDecimal(2, result);
                if (result < 0.0) {
                    result = result * -1;
                }
                rows.add(new RowSeries(counterRow, fo, fe, result));
                totalResult += result;
            }
        }
        this.matrixString = matrixStringTemp;
        return rows;
    }

    public double getValueAlphaK() {
        return tableSeries.getValue(getAlphaDecimal(), k * k - 1);
    }

    public String[][] getMatrixString() {
        return matrixString;
    }
}
