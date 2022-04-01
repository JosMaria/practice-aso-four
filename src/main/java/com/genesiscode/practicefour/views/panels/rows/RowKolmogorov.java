package com.genesiscode.practiceasofour.views.panels.rows;

public class RowKolmogorov {

    private String fi;
    private double fii;
    private double valueAbsolute;

    public RowKolmogorov(String fi, double fii, double valueAbsolute) {
        this.fi = fi;
        this.fii = fii;
        this.valueAbsolute = valueAbsolute;
    }

    public String getFi() {
        return fi;
    }

    public void setFi(String fi) {
        this.fi = fi;
    }

    public double getFii() {
        return fii;
    }

    public void setFii(double fii) {
        this.fii = fii;
    }

    public double getValueAbsolute() {
        return valueAbsolute;
    }

    public void setValueAbsolute(double valueAbsolute) {
        this.valueAbsolute = valueAbsolute;
    }
}
