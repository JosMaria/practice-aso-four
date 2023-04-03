package com.genesiscode.practicefour.models;

import com.genesiscode.practicefour.models.formulas.DistributionX2;
import com.genesiscode.practicefour.models.formulas.KeyDistributionX2;
import com.genesiscode.practicefour.models.utils.Decimal;
import com.genesiscode.practicefour.models.utils.GapUtils;
import com.genesiscode.practicefour.views.panels.rows.RowGap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Gap {

    private final List<Double> numbers;
    private double alpha;
    private double beta;
    private int confidenceLevel;
    private final DistributionX2 distributionX2;
    private double summation;
    private String stringNumbers;

    public Gap() {
        numbers = new ArrayList<>();
        distributionX2 = DistributionX2.getInstance();
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
    public void setBeta(double beta) {
        this.beta = beta;
    }
    public void setConfidenceLevel(int confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }
    public double getOneMinusConfidenceLevel() {
        return (double) (100 - confidenceLevel) / 100;
    }
    public double getSummation() {
        return Decimal.getDecimal(4, summation);
    }

    public void addNumbers(Collection<Double> numbers) {
        this.numbers.addAll(numbers);
    }

    public void clear() {
        numbers.clear();
    }

    public List<Integer> getListOfFrequencies(int[] arrayGiven) {
        List<Integer> result = new ArrayList<>();
        int counter = 0;

        for (int i = 0; i <= arrayGiven.length; i++) {
            if (i == arrayGiven.length) {
                if (counter != 0) {
                    result.add(counter);
                }
            } else {
                if (arrayGiven[i] == 1) {
                    if (counter != 0) {
                        result.add(counter);
                        counter = 0;
                    } else {
                        if (result.isEmpty()) {
                            if (i - 1 >= 0) {
                                if (arrayGiven[i-1] == 1) {
                                    result.add(0);
                                }
                            }
                        } else {
                            Integer ultElementAdded = result.get(result.size()-1);
                            if (ultElementAdded != 0) {
                                if (arrayGiven[i-1] == 1) {
                                    result.add(0);
                                }
                            }
                        }
                    }
                } else {
                    counter++;
                }
            }
        }
        return result;
    }

    public ObservableList<RowGap> getTableResult() {
        ObservableList<RowGap> rows = FXCollections.observableArrayList();
        summation = 0;
        int[] onesAndZeros = GapUtils.arrayOfOnesAndZeros(alpha, beta, numbers);
        stringNumbers = Arrays.toString(onesAndZeros);
        List<Integer> listOfFrequencies = getListOfFrequencies(onesAndZeros);
        int h = listOfFrequencies.size();
        for (int i = 0; i < 6; i++) {
            int oi = GapUtils.countNumberInList(listOfFrequencies, i);
            double betaMinusAlpha = Decimal.getDecimal(1, beta - alpha);
            double oneMinusBetaMinusAlpha = 1 - betaMinusAlpha;

            double ei;
            String columnThree;
            if (i != 5) {
                ei = Decimal.getDecimal(4, h * betaMinusAlpha * Math.pow(oneMinusBetaMinusAlpha, i));
                columnThree = String.format("(%s)(%s)(%s)^%s=%s",
                        h, betaMinusAlpha, oneMinusBetaMinusAlpha, i, Decimal.getDecimal(4, ei));
            } else {
                ei = Decimal.getDecimal(4, h * Math.pow(oneMinusBetaMinusAlpha, i));
                columnThree = String.format("%s*(%s)^%s=%s", h, oneMinusBetaMinusAlpha, i, Decimal.getDecimal(4, ei));
            }

            double columnFour = Decimal.getDecimal(4, Math.pow(oi - ei, 2) / ei);
            summation += columnFour;
            rows.add(new RowGap(i, oi, columnThree, columnFour));
        }
        rows.add(new RowGap(0, h, String.valueOf(h), Decimal.getDecimal(4, summation)));
        return rows;
    }

    public double getResultAlpha() {
        return distributionX2.getValue(new KeyDistributionX2(getOneMinusConfidenceLevel(), 5));
    }

    public String getStringNumbers() {
        return stringNumbers;
    }
}
