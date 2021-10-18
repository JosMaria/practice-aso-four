package com.example.practiceasofour.model;

import java.util.HashMap;
import java.util.Map;

public class Areas {

    private final Map<Double, Double> standardNormalCurve;

    public Areas() {
        standardNormalCurve = new HashMap<>();
        loadKeysAndValues();
    }

    private void loadKeysAndValues() {
        standardNormalCurve.put(0.02500, 1.96);
        standardNormalCurve.put(0.97500, 1.96);
        standardNormalCurve.put(0.95000, 1.96);
        standardNormalCurve.put(0.47500, 1.96);
        //more values inserting
    }

    public Double getValue(Double key) throws IllegalArgumentException {

        if (standardNormalCurve.containsKey(key)) {
            return standardNormalCurve.get(key);
        } else {
            throw new IllegalArgumentException("El valor ingresado no exite en el contenido");
        }
    }
}
