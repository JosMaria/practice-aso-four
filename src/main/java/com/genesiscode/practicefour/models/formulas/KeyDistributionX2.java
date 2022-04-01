package com.genesiscode.practiceasofour.models.formulas;

import java.util.Objects;

public class KeyDistributionX2 {

    private final double xSquared;
    private final int degreesOfFreedom;

    public KeyDistributionX2(double xSquared, int degreesOfFreedom) {
        this.xSquared = xSquared;
        this.degreesOfFreedom = degreesOfFreedom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyDistributionX2 that = (KeyDistributionX2) o;
        return Double.compare(that.xSquared, xSquared) == 0 && degreesOfFreedom == that.degreesOfFreedom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xSquared, degreesOfFreedom);
    }
}
