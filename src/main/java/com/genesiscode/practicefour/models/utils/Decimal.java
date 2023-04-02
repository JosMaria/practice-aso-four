package com.genesiscode.practicefour.models.utils;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Decimal {

    public static double getDecimal(int count, double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#." + getHash(count));
        return Double.parseDouble(decimalFormat.format(number));
    }

    private static String getHash(int count) {
        return "#".repeat(Math.max(0, count));
    }

    public static double[] inputNumbersToArray(String inputNumbers) {
        String[] numbersText = inputNumbers.split(" ");
        return Arrays.stream(numbersText)
                .map(String::trim)
                .mapToDouble(Double::parseDouble)
                .toArray();
    }
}
