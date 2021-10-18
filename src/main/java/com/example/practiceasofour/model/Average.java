package com.example.practiceasofour.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Average implements Uniformity {

    private double half;
    private double standardDeviation;

    private double halfGiven;
    private int n;
    private double alpha;

    private double valueZo = 0.0;
    private double valueZ_alpha_middle = 0.0;

    private List<Double> numbers;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    private Areas areas;

    public static void main(String[] args) {
        Average average = new Average();
        average.addNumber(0.29);
        average.addNumber(0.81);
        average.addNumber(0.32);
        average.addNumber(0.47);
        average.addNumber(0.62);
        average.addNumber(0.08);
        average.addNumber(0.42);
        average.addNumber(0.71);
        average.addNumber(0.53);
        average.addNumber(0.16);
        average.setAlpha(5);

        average.calculate();
        System.out.println(average);

        System.out.println(average.isCorrect());

    }

    public Average() {
        loadData();
    }

    private void loadData() {
        areas = new Areas();
        numbers = new ArrayList<>();
        half = 0.5;
        standardDeviation = Double.parseDouble(decimalFormat.format(Math.sqrt(variance)));
    }

    public void addNumber(double number) {
        numbers.add(number);
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha / 100;
    }

    public void clear() {
        numbers.clear();
    }

    public void calculate() {
        valueZo = getResultLeft();
        valueZ_alpha_middle = getResultRight();
        numbers.clear();
    }

    private double getResultLeft() {
        n = numbers.size();
        double total = 0.0;
        for (Double number : numbers) {
            total += number;
        }
        halfGiven = total / n;
        return getResult();
    }

    private double getResult() {
        double result = ((half - halfGiven) * Math.sqrt(n)) / standardDeviation;
        double resultCompress = Double.parseDouble(decimalFormat.format(result));
        if (resultCompress < 0.0) {
            return resultCompress * (-1);
        }
        return resultCompress;
    }

    private double getResultRight() {
        double key = alpha / 2;
        return areas.getValue(key);
    }

    public boolean isCorrect() {
        return valueZo < valueZ_alpha_middle;
    }

    public double getHalf() {
        return half;
    }
    public int getN() {
        return n;
    }
    public double getAlpha() {
        return alpha;
    }
    public double getValueZo() {
        return valueZo;
    }
    public double getValueZ_alpha_middle() {
        return valueZ_alpha_middle;
    }

    @Override
    public String toString() {
        return String.format("Average {\thalf=%s, halfGiven=%s, n=%s, standardDeviation=%s, alpha=%s, Zo=%s," +
                        " Z(α/2)=%s\t}", half, halfGiven, n, standardDeviation, alpha, valueZo, valueZ_alpha_middle);
    }
}
