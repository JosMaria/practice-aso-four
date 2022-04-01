package com.genesiscode.practicefour.models.utils;

import java.util.HashMap;

import static com.genesiscode.practicefour.models.utils.Category.*;

public class PokerElement {

    private final double number;
    private final String textNumber;
    private Category category;

    public PokerElement(double number, String textNumber) {
        this.number = number;
        this.textNumber = textNumber;
        loadCategoryAndProbability();
    }

    private void loadCategoryAndProbability() {
        String textPartDecimal = PokerUtils.getPartDecimal(textNumber);
        HashMap<Integer, Integer> map = new HashMap<>();
        PokerUtils.textNumberToMap(map, textPartDecimal);

        if (map.containsValue(5)) {
            category = Q;
        } else if (map.containsValue(4)) {
            category = P;
        } else if (map.containsValue(3)) {
            if (map.containsValue(2)) {
                category = TP;
            } else {
                category = T;
            }
        } else if (map.containsValue(2)) {
            if (existsTwoPairs(map)) {
                category = _2P;
            } else {
                category = _1P;
            }
        } else {
            category = TD;
        }
    }

    private boolean existsTwoPairs(HashMap<Integer, Integer> map) {
        int count = 0;
        for (Integer value : map.values()) {
            if (value == 2) {
                count++;
            }
        }
        return count == 2;
    }

    public double getNumber() {
        return number;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("PokerElement {number=%s, textNumber=%s, category=%s}", number, textNumber, category);
    }
}
