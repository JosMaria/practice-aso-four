package com.genesiscode.practicefour.models.utils;

import com.genesiscode.practicefour.models.formulas.Areas;

import java.text.DecimalFormat;
import java.util.List;

public class AverageUtils {

    private static final String MESSAGE_REFUSE = "se puede rechazar la hipotesis\n" +
                                                    "de que los numeros pseudoaletorios\n" +
                                                    "provienen de un universo uniforme.";
    private static final String MESSAGE_NOT_REFUSE = "NO " + MESSAGE_REFUSE;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.###");
    private static final Areas areas = new Areas();

    public static double getHalfCalculated(List<Double> numbers, int n) {
        double halfCalculated = 0.0;
        for (Double number : numbers) {
            halfCalculated += number;
        }
        return Double.parseDouble(decimalFormat.format(halfCalculated / n));
    }

    public static double getZo(double halfGiven, double halfCalculated, int n, double standardDeviation) {
        double result = ((halfGiven - halfCalculated) * Math.sqrt(n)) / standardDeviation;
        double zo = Double.parseDouble(decimalFormat.format(result));
        if (zo < 0.0) {
            return zo * (-1);
        }
        return zo;
    }

    public static double getZoAlphaDiv2(int alphaPercentage) {
        double alphaDecimal = getAlphaDecimal(alphaPercentage);
        double key = alphaDecimal / 2;
        return areas.getValue(key);
    }

    public static double getAlphaDecimal(int alphaPercentage) {
        return (double) alphaPercentage / 100;
    }

    public static String getMessageResult(double zo, double zAlphaDiv2) {
        return zo < zAlphaDiv2 ? MESSAGE_NOT_REFUSE : MESSAGE_REFUSE;
    }
}
