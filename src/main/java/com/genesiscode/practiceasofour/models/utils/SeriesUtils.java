package com.genesiscode.practiceasofour.models.utils;

import java.util.ArrayList;
import java.util.List;

public class SeriesUtils {

    public static List<SeriesPair> loadListPairs(List<Double> numbers) {
        List<SeriesPair> pairs = new ArrayList<>();
        for (int i = 0; i < numbers.size()-1; i++) {
            pairs.add(new SeriesPair(numbers.get(i), numbers.get(i+1)));
        }
        return pairs;
    }

    public static SeriesCell[][] cells(int k) {
        SeriesCell[][] cells = new SeriesCell[k][k];
        List<Double> intervals = FrequencyUtils.calculateIntervalValues(k);
        List<Double> inverseIntervals = SeriesUtils.inverseList(intervals);

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                double initialX = intervals.get(j);
                double finalX = intervals.get(j+1);
                double initialY = inverseIntervals.get(i);
                double finalY = inverseIntervals.get(i + 1);
                cells[i][j] = new SeriesCell(initialX, finalX, initialY, finalY);
            }
        }
        return cells;
    }

    public static List<Double> inverseList(List<Double> inputList) {
        List<Double> outputList = new ArrayList<>();
        for (int i = inputList.size() - 1; i >= 0; i--) {
            outputList.add(inputList.get(i));
        }
        return outputList;
    }
}
