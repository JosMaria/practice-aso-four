package com.genesiscode.practicefour.models;

import com.genesiscode.practicefour.models.formulas.DistributionX2;
import com.genesiscode.practicefour.models.formulas.KeyDistributionX2;
import com.genesiscode.practicefour.models.utils.FrequencyUtils;
import com.genesiscode.practicefour.views.panels.rows.RowFrequency;
import com.genesiscode.practicefour.views.panels.rows.RowFrequencyInterval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Frequency {

    //inputs
    private int alpha;
    private int k;
    private final List<Double> numbers;

    private final DistributionX2 distributionX2;
    private double resultTotal;

    public Frequency() {
        numbers = new ArrayList<>();
        distributionX2 = DistributionX2.getInstance();
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void clear() {
        numbers.clear();
    }

    public void addNumber(double number) {
        numbers.add(number);
    }

    public double getResultTotal() {
        return resultTotal;
    }

    public ObservableList<RowFrequencyInterval> showTableIntervalsAndValues() {
        List<Double> intervals = FrequencyUtils.calculateIntervalValues(k);
        Map<Double, List<Double>> map = FrequencyUtils.getValuesInsideIntervals(numbers, intervals);
        return showIntervalsAndValues(intervals, map);
    }
    
    private ObservableList<RowFrequencyInterval> showIntervalsAndValues(List<Double> intervals, Map<Double, List<Double>> map) {
        ObservableList<RowFrequencyInterval> rows = FXCollections.observableArrayList();
        for (int i = 0; i < intervals.size() - 1; i++) {
            String interval = String.format("[ %s - %s ]", intervals.get(i), intervals.get(i+1));
            String values = map.get(intervals.get(i)).toString();
            rows.add(new RowFrequencyInterval(interval, values));
        }
        return rows;
    }

    public ObservableList<RowFrequency> showTableResult() {
        List<Double> intervals = FrequencyUtils.calculateIntervalValues(k);
        Map<Double, List<Double>> map = FrequencyUtils.getValuesInsideIntervals(numbers, intervals);        return showResult(intervals, map);
    }

    private ObservableList<RowFrequency> showResult(List<Double> intervals, Map<Double, List<Double>> map) {
        ObservableList<RowFrequency> rows = FXCollections.observableArrayList();
        List<Integer> countValuesInInterval = FrequencyUtils.getCountNumbers(intervals, map);
        double fe = FrequencyUtils.getExpectedFrequency(numbers.size(), k);
        List<Double> results = FrequencyUtils.getResultByExpectedFrequency(countValuesInInterval, fe);

        for (int i = 0; i < intervals.size() - 1; i++) {
            String interval = String.format("[ %s - %s ]", intervals.get(i), intervals.get(i+1));
            Integer fo = countValuesInInterval.get(i);
            String result = String.format("(%s - %s)² / %s = %s", fo, fe, fe, results.get(i));
            rows.add(new RowFrequency(interval, fo, fe, result));
        }

        resultTotal = FrequencyUtils.getTotal(countValuesInInterval, fe);
        rows.add(new RowFrequency("TOTAL", 0, 0.0, String.valueOf(resultTotal)));
        return rows;
    }

    public double getValueZAlphaK() {
        return distributionX2.getValue(new KeyDistributionX2((double) alpha / 100, k - 1));
    }
}
