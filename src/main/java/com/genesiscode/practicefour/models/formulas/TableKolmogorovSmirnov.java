package com.genesiscode.practiceasofour.models.formulas;

import java.util.HashMap;
import java.util.Map;

public class TableKolmogorovSmirnov {

    private static TableKolmogorovSmirnov tableKolmogorovSmirnov;
    private final Map<KeyKolmogorovSmirnov, Double> map;

    private TableKolmogorovSmirnov() {
        map = new HashMap<>();
        loadData();
    }

    public synchronized static TableKolmogorovSmirnov getInstance() {
        if (tableKolmogorovSmirnov == null) {
            tableKolmogorovSmirnov = new TableKolmogorovSmirnov();
        }
        return tableKolmogorovSmirnov;
    }

    private void loadData() {
        //load data to map
        map.put(new KeyKolmogorovSmirnov(0.1, 1), 0.950);
        map.put(new KeyKolmogorovSmirnov(0.1, 2), 0.776);
        map.put(new KeyKolmogorovSmirnov(0.1, 3), 0.642);
        map.put(new KeyKolmogorovSmirnov(0.1, 4), 0.564);
        map.put(new KeyKolmogorovSmirnov(0.1, 5), 0.510);
        map.put(new KeyKolmogorovSmirnov(0.1, 6), 0.470);
        map.put(new KeyKolmogorovSmirnov(0.1, 7), 0.438);
        map.put(new KeyKolmogorovSmirnov(0.1, 8), 0.411);
        map.put(new KeyKolmogorovSmirnov(0.1, 9), 0.388);
        map.put(new KeyKolmogorovSmirnov(0.1, 10), 0.368);

        map.put(new KeyKolmogorovSmirnov(0.05, 1), 0.975);
        map.put(new KeyKolmogorovSmirnov(0.05, 2), 0.842);
        map.put(new KeyKolmogorovSmirnov(0.05, 3), 0.708);
        map.put(new KeyKolmogorovSmirnov(0.05, 4), 0.624);
        map.put(new KeyKolmogorovSmirnov(0.05, 5), 0.565);
        map.put(new KeyKolmogorovSmirnov(0.05, 6), 0.521);
        map.put(new KeyKolmogorovSmirnov(0.05, 7), 0.486);
        map.put(new KeyKolmogorovSmirnov(0.05, 8), 0.457);
        map.put(new KeyKolmogorovSmirnov(0.05, 9), 0.432);
        map.put(new KeyKolmogorovSmirnov(0.05, 10), 0.410);

        map.put(new KeyKolmogorovSmirnov(0.01, 1), 0.995);
        map.put(new KeyKolmogorovSmirnov(0.01, 2), 0.929);
        map.put(new KeyKolmogorovSmirnov(0.01, 3), 0.828);
        map.put(new KeyKolmogorovSmirnov(0.01, 4), 0.733);
        map.put(new KeyKolmogorovSmirnov(0.01, 5), 0.669);
        map.put(new KeyKolmogorovSmirnov(0.01, 6), 0.618);
        map.put(new KeyKolmogorovSmirnov(0.01, 7), 0.577);
        map.put(new KeyKolmogorovSmirnov(0.01, 8), 0.543);
        map.put(new KeyKolmogorovSmirnov(0.01, 9), 0.514);
        map.put(new KeyKolmogorovSmirnov(0.01, 10), 0.490);
    }

    public double getValue(double column, int row) {
        return map.getOrDefault(new KeyKolmogorovSmirnov(column, row), 0.0);
    }

}
