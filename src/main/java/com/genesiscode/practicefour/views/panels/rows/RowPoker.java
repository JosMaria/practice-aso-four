package com.genesiscode.practicefour.views.panels.rows;

public class RowPoker {

    private final String category;
    private final double probability;
    private final int oi;
    private final String ei;
    private final double result;

    public RowPoker(String category, double probability, int oi, String ei, double result) {
        this.category = category;
        this.probability = probability;
        this.oi = oi;
        this.ei = ei;
        this.result = result;
    }

    public String getCategory() {
        return category;
    }

    public double getProbability() {
        return probability;
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
