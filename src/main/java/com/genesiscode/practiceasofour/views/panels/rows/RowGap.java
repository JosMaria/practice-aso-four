package com.genesiscode.practiceasofour.views.panels.rows;

public class RowGap {

    private final int gapSize;
    private final int oi;
    private final String ei;
    private final double result;

    public RowGap(int gapSize, int oi, String ei, double result) {
        this.gapSize = gapSize;
        this.oi = oi;
        this.ei = ei;
        this.result = result;
    }

    public int getGapSize() {
        return gapSize;
    }

    public int getOi() {
        return oi;
    }

    public String getEi() {
        return ei;
    }

    public double getResult() {
        return result;
    }
}
