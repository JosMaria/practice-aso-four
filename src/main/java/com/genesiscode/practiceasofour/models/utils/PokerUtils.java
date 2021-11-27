package com.genesiscode.practiceasofour.models.utils;

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
}
