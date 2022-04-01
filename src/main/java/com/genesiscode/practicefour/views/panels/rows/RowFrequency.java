package com.genesiscode.practicefour.views.panels.rows;

public class RowFrequency {

    private String interval;
    private int frequencyObserved;
    private double frequencyExpected;
    private String result;

    public RowFrequency(String interval, int frequencyObserved, double frequencyExpected, String result) {
        this.interval = interval;
        this.frequencyObserved = frequencyObserved;
        this.frequencyExpected = frequencyExpected;
        this.result = result;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public int getFrequencyObserved() {
        return frequencyObserved;
    }

    public void setFrequencyObserved(int frequencyObserved) {
        this.frequencyObserved = frequencyObserved;
    }

    public double getFrequencyExpected() {
        return frequencyExpected;
    }

    public void setFrequencyExpected(double frequencyExpected) {
        this.frequencyExpected = frequencyExpected;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
