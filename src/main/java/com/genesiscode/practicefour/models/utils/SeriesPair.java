package com.genesiscode.practicefour.models.utils;

public class SeriesPair {

    private final double x;
    private final double y;

    public SeriesPair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "SeriesPair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
