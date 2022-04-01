package com.genesiscode.practicefour.models.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AverageUtilsTest {

    static DecimalFormat decimalFormat;

    @BeforeAll
    static void beforeAll() {
        decimalFormat = new DecimalFormat("#.###");
    }

    @Test
    void getAverageCalculated() {
        //GIVEN
        List<Double> numbers = List.of(0.29, 0.81, 0.32, 0.47, 0.62, 0.08, 0.42, 0.71, 0.53, 0.16);

        //WHEN
        double averageCalculated = AverageUtils.getHalfCalculated(numbers, numbers.size());

        //THEN
        assertThat(averageCalculated).isEqualTo(0.441);

    }

    @Test
    void getZo() {
        //GIVEN
        double halfGiven = 0.5;
        double halfCalculated = 0.441;
        int n = 10;
        double standardDeviation = Double.parseDouble(decimalFormat.format(Math.sqrt(0.083)));

        //WHEN
        double zo = AverageUtils.getZo(halfGiven, halfCalculated, n, standardDeviation);

        //THEN
        assertThat(zo).isEqualTo(0.648);
    }

    @Test
    void getZoAlphaDiv2() {
        //GIVEN
        int alphaPercentage = 5;

        //WHEN
        double zoAlphaDiv2 = AverageUtils.getZoAlphaDiv2(5);

        //THEN
        assertThat(zoAlphaDiv2).isEqualTo(1.96);
    }
}