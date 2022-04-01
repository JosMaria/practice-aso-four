package com.genesiscode.practicefour.models.utils;

public class SeriesCell {

    private final double initialX;
    private final double finalX;
    private final double initialY;
    private final double finalY;
    private int value;

    public SeriesCell(double initialX, double finalX, double initialY, double finalY) {
        this.initialX = initialX;
        this.finalX = finalX;
        this.initialY = initialY;
        this.finalY = finalY;
        value = 0;
    }

    public double getInitialX() {
        return initialX;
    }

    public double getFinalX() {
        return finalX;
    }

    public double getInitialY() {
        return initialY;
    }

    public double getFinalY() {
        return finalY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Cell { iniX=%s, finX=%s, iniY=%s, finY=%s , value=%s}",
                                initialX, finalX, initialY, finalY, value);
    }
}
