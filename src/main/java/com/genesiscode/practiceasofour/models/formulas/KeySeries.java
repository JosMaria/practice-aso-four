package com.genesiscode.practiceasofour.models.formulas;

import java.util.Objects;

public class KeySeries {

    private final double column;
    private final int row;

    public KeySeries(double column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeySeries keySeries = (KeySeries) o;
        return Double.compare(keySeries.column, column) == 0 && row == keySeries.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
