package com.genesiscode.practicefour.views.panels.rows;

public class RowFrequencyInterval {

    private String interval;
    private String values;

    public RowFrequencyInterval(String interval, String values) {
        this.interval = interval;
        this.values = values;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
