package com.genesiscode.practicefour.models.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyUtils {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static List<Double> calculateIntervalValues(int k) {
        List<Double> intervalValues = new ArrayList<>();
        double valueToIncrement = (double) 1 / k;
        for (double value = 0.0; value < 1; value += valueToIncrement) {
            intervalValues.add(Double.parseDouble(decimalFormat.format(value)));
        }
        intervalValues.add(1D);
        return intervalValues;
    }

    public static double getExpectedFrequency(int n, int k) {
        return Double.parseDouble(decimalFormat.format((double) n / k));
    }

    public static Map<Double, List<Double>> getValuesInsideIntervals(List<Double> sequenceNumbers, List<Double> intervalsValues) {
        boolean itFoundItsInterval;
        int i;
        Map<Double, List<Double>> mapWithValues = getMapStart(intervalsValues);

        for (Double number : sequenceNumbers) {
            itFoundItsInterval = false;
            i = 0;
            while (! itFoundItsInterval && i - 1 < intervalsValues.size()) {
                if (intervalsValues.get(i) <= number && number < intervalsValues.get(i+1)) {
                    mapWithValues.get(intervalsValues.get(i)).add(number);
                    itFoundItsInterval = true;
                } else {
                    i++;
                }
            }
        }
        return mapWithValues;
    }

    /*
     * method private
     */
    public static Map<Double, List<Double>> getMapStart(List<Double> intervalsValues) {
        Map<Double, List<Double>> valuesInsideIntervals = new HashMap<>();
        intervalsValues.forEach(key -> valuesInsideIntervals.put(key, new ArrayList<>()));
        return valuesInsideIntervals;
    }

    public static List<Integer> getCountNumbers(List<Double> intervalsValues, Map<Double, List<Double>> map) {
        List<Integer> countNumbers = new ArrayList<>();
        intervalsValues.forEach(value -> countNumbers.add(map.get(value).size()));
        int ultElement = intervalsValues.size() - 1;
        countNumbers.remove(ultElement);
        return countNumbers;
    }

    public static List<Double> getResultByExpectedFrequency(List<Integer> countValuesInIntervals, double expectedFrequency) {
        List<Double> returned = new ArrayList<>();
        countValuesInIntervals.forEach(number -> {
            double result = Double.parseDouble(decimalFormat.format
                    (Math.pow(number - expectedFrequency, 2) / expectedFrequency));
            returned.add(result);
        });
        return returned;
    }

    public static double getTotal(List<Integer> countNumbers, double expectedFrequency) {
        double total = 0;
        for (Integer number : countNumbers) {
            total += Math.pow(number - expectedFrequency, 2) / expectedFrequency;
        }
        return Double.parseDouble(decimalFormat.format(total));
    }
}
