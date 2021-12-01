package com.genesiscode.practiceasofour.models.utils;

import java.util.List;

public class GapUtils {

    public static int[] nothingName(double alpha, double beta, List<Double> numbersGiven) {
        int[] numberOnesAndZeros = new int[numbersGiven.size()];
        int i = 0;
        for (Double number : numbersGiven) {
            numberOnesAndZeros[i] = (alpha <= number && number <= beta) ? 1 : 0;
            i++;
        }
        return numberOnesAndZeros;
    }
}
