package com.genesiscode.practicefour.models.formulas;

import java.util.HashMap;
import java.util.Map;

public class Areas {

    private static final Map<Double, Double> standardNormalCurve = new HashMap<>();

    public Areas() {
        loadKeysAndValues();
    }

    private void loadKeysAndValues() {
        //values
        standardNormalCurve.put(0.02500, 1.96);
        standardNormalCurve.put(0.97500, 1.96);
        standardNormalCurve.put(0.95000, 1.96);
        standardNormalCurve.put(0.47500, 1.96);

        //more values to insert ...
    }

    public double getValue(double key) throws IllegalArgumentException {

        if (standardNormalCurve.containsKey(key)) {
            return standardNormalCurve.get(key);
        } else {
            throw new IllegalArgumentException("El valor ingresado no exite en el contenido");
        }
    }
}
