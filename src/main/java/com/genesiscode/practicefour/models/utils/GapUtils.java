package com.genesiscode.practicefour.models.utils;

import java.util.List;
import java.util.function.Predicate;

public class GapUtils {

    public static int[] arrayOfOnesAndZeros(double alpha, double beta, List<Double> numbersGiven) {
        int[] numberOnesAndZeros = new int[numbersGiven.size()];
        int i = 0;
        for (Double number : numbersGiven) {
            numberOnesAndZeros[i] = (alpha <= number && number <= beta) ? 1 : 0;
            i++;
        }
        return numberOnesAndZeros;
    }

    public static int countNumberInList(List<Integer> frequencies, int value) {
        Predicate<Integer> predicate = frequency -> (value >= 5) ? frequency >= value : frequency == value;
        return (int) frequencies.stream()
                .filter(predicate)
                .count();
    }
}
