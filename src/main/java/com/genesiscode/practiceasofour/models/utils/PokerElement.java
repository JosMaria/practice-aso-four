package com.genesiscode.practiceasofour.models.utils;

import java.util.HashMap;

import static com.genesiscode.practiceasofour.models.utils.Category.*;

public class PokerElement {

    private final double number;
    private final String textNumber;
    private Category category;
    private double probability;

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
        loadProbability(textPartDecimal);
    }

    private void loadProbability(String textPartDecimal) {
        switch (textPartDecimal.length()) {
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

    public double getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return String.format("PokerElement {number=%s, textNumber=%s, category=%s, probability=%s}",
                number, textNumber, category, probability);
    }
}
