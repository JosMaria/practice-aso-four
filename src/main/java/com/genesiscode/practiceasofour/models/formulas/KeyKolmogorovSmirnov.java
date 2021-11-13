package com.genesiscode.practiceasofour.models.formulas;

import java.util.Objects;

public class KeyKolmogorovSmirnov {

    private final double column;
    private final int row;

    public KeyKolmogorovSmirnov(double column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyKolmogorovSmirnov that = (KeyKolmogorovSmirnov) o;
        return Double.compare(that.column, column) == 0 && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
