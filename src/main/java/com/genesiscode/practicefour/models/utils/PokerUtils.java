package com.genesiscode.practicefour.models.utils;

import java.util.Map;

public class PokerUtils {

    public static boolean numberToAddIsCorrectWithTheCountOfDecimals(String numberText, int countDecimals)   {
        String decimalNumber = getPartDecimal(numberText);
        return decimalNumber.length() == countDecimals;
    }

    public static String getPartDecimal(String textDecimal) {
        return textDecimal.substring(textDecimal.indexOf(".") + 1);
    }

    public static void textNumberToMap(Map<Integer, Integer> map, String textNumber) {
        map.clear();
        char[] charsOfTextNumber = textNumber.toCharArray();
        for (char digit : charsOfTextNumber) {
            int numericValue = Character.getNumericValue(digit);
            if (map.containsKey(numericValue)) {
                Integer oldValue = map.get(numericValue);
                map.put(numericValue, oldValue + 1);
            } else {
                map.put(numericValue, 1);
            }
        }
    }

    public static double getItsProbability(Category category, int countDecimals) {
        double probability = 0.0;
        switch (countDecimals) {
            case 3:
                switch (category) {
                    case TD:
                        probability = 0.72;
                        break;
                    case _1P:
                        probability = 0.27;
                        break;
                    case T:
                        probability = 0.01;
                        break;
                }
                break;
            case 4:
                switch (category) {
                    case TD:
                        probability = 0.5040;
                        break;
                    case _1P:
                        probability = 0.4320;
                        break;
                    case _2P:
                        probability = 0.0270;
                        break;
                    case T:
                        probability = 0.0360;
                        break;
                    case P:
                        probability = 0.0010;
                        break;
                }
                break;
            case 5:
                switch(category) {
                    case TD:
                        probability = 0.3024;
                        break;
                    case _1P:
                        probability = 0.5040;
                        break;
                    case _2P:
                        probability = 0.1080;
                        break;
                    case TP:
                        probability = 0.0090;
                        break;
                    case T:
                        probability = 0.0720;
                        break;
                    case P:
                        probability = 0.0045;
                        break;
                    case Q:
                        probability = 0.0001;
                        break;
                }
                break;
        }
        return probability;
    }
}
