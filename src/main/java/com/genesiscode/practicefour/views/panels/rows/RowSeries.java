package com.genesiscode.practicefour.views.panels.rows;

public class RowSeries {

    private int value;
    private int fo;
    private double fe;
    private double x2;

    public RowSeries(int value, int fo, double fe, double x2) {
        this.value = value;
        this.fo = fo;
        this.fe = fe;
        this.x2 = x2;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFo() {
        return fo;
    }

    public void setFo(int fo) {
        this.fo = fo;
    }

    public double getFe() {
        return fe;
    }

    public void setFe(double fe) {
        this.fe = fe;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }
}
