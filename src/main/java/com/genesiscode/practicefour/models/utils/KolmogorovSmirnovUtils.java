package com.genesiscode.practiceasofour.models.utils;

import java.util.ArrayList;
import java.util.List;

public class KolmogorovSmirnovUtils {

    public static List<Double> sortList(List<Double> inputList) {
        List<Double> outputList = new ArrayList<>();
        for (Double number : inputList) {
            if (outputList.size() == 0) {
                outputList.add(number);
            } else {
                boolean sideFound = false;
                int i = 0;
                while (! sideFound && i <= outputList.size()) {
                    if (i == outputList.size()) {
                        outputList.add(number);
                        sideFound = true;
                    } else if (number < outputList.get(i)) {
                        outputList.add(i, number);
                        sideFound = true;
                    } else {
                        i++;
                    }
                }
            }
        }
        return outputList;
    }
}
