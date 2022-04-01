package com.genesiscode.practicefour.models.formulas;

import java.util.HashMap;
import java.util.Map;

public class TableSeries {

    private static TableSeries tableSeries;
    private final Map<KeySeries, Double> map;

    private TableSeries() {
        map = new HashMap<>();
        loadData();
    }

    public synchronized static TableSeries getInstance() {
        if (tableSeries == null) {
            tableSeries = new TableSeries();
        }
        return tableSeries;
    }

    private void loadData() {
        //load data to map
        map.put(new KeySeries(0.05, 15), 25.00);
    }

    public double getValue(double column, int row) {
        return map.getOrDefault(new KeySeries(column, row), 0.0);
    }
}
