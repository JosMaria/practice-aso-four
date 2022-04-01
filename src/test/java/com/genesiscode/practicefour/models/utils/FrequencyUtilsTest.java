package com.genesiscode.practicefour.models.utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrequencyUtilsTest {
    //GIVEN
    //WHEN
    //THEN
    @Test
    void calculateIntervalValuesTest() {
        //GIVEN
        int k = 4;

        //WHEN
        List<Double> intervalValuesActual = FrequencyUtils.calculateIntervalValues(k);

        //THEN
        assertThat(intervalValuesActual).containsExactly(0.0, 0.25, 0.5, 0.75, 1.0);
    }

    @Test
    void getExpectedFrequencyTest() {
        //GIVEN
        int n = 10, k = 4;

        //WHEN
        double expectedFrequency = FrequencyUtils.getExpectedFrequency(n, k);

        //THEN
        assertThat(expectedFrequency).isEqualTo(2.5);
    }

    @Test
    void getValuesInsideIntervalsTest() {
        //GIVEN
        List<Double> numbers = List.of(0.23, 0.82, 0.06, 0.93, 0.68, 0.12, 0.29, 0.76, 0.84, 0.73);
        List<Double> intervals = List.of(0.0, 0.25, 0.5, 0.75, 1.0);

        //WHEN
        Map<Double, List<Double>> mapActual = FrequencyUtils.getValuesInsideIntervals(numbers, intervals);

        //THEN
        System.out.println(mapActual);
        assertAll(
                () -> assertThat(mapActual).containsEntry(0.0, List.of(0.23, 0.06, 0.12)),
                () -> assertThat(mapActual).containsEntry(1.0, List.of())
        );
        //{0.0=[0.23, 0.06, 0.12], 0.25=[0.29], 0.5=[0.68, 0.73], 1.0=[], 0.75=[0.82, 0.93, 0.76, 0.84]}
    }

    @Test
    void getCountNumbersTest() {
        //GIVEN
        List<Double> intervals = List.of(0.0, 0.25, 0.5, 0.75, 1.0);
        Map<Double, List<Double>> map = Map.of(0.0, List.of(0.23, 0.06, 0.12), 0.25, List.of(0.29),
                            0.5, List.of(0.68, 0.73), 0.75, List.of(0.82, 0.76, 0.84), 1.0, List.of());

        //WHEN
        List<Integer> countNumbers = FrequencyUtils.getCountNumbers(intervals, map);

        //THEN
        assertThat(countNumbers).containsExactly(3, 1, 2, 3);
    }
}