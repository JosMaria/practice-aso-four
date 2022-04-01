package com.genesiscode.practiceasofour.models.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GapUtilsTest {

    @Test
    void nothingName() {
        //GIVEN
        double alpha = 0.8, beta = 1.0;
        Double[] numbersArray = {   0.872, 0.950, 0.343, 0.058, 0.384,
                                    0.219, 0.041, 0.036, 0.213, 0.946,
                                    0.570, 0.842, 0.706, 0.809, 0.300,
                                    0.618, 0.512, 0.462, 0.005, 0.203,
                                    0.291, 0.151, 0.596, 0.443, 0.868,
                                    0.913, 0.511, 0.586, 0.608, 0.879 };
        List<Double> numbers1 = List.of(numbersArray);
        int[] numbersExpected = {   1, 1, 0, 0, 0,
                                    0, 0, 0, 0, 1,
                                    0, 1, 0, 1, 0,
                                    0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 1,
                                    1, 0, 0, 0, 1 };
        //WHEN
        int[] numbersActual = GapUtils.arrayOfOnesAndZeros(alpha, beta, numbers1);

        //THEN
        assertThat(numbersActual).containsExactly(numbersExpected);
    }

    @ParameterizedTest(name = "#{index} - Test: frequencies={0}, value={1}, frequency={2}")
    @MethodSource("countNumberInListProvider")
    void countNumberInListTest(List<Integer> frequencies, int value, int frequency) {
        //WHEN
        int frequencyActual = GapUtils.countNumberInList(frequencies, value);

        //THEN
        assertThat(frequencyActual).isEqualTo(frequency);
    }

    static Stream<Arguments> countNumberInListProvider() {
        //GIVEN
        List<Integer> frequencies = List.of(0, 7, 1, 1, 10, 0, 3);
        return Stream.of(
                arguments(frequencies, 0, 2),
                arguments(frequencies, 1, 2),
                arguments(frequencies, 2, 0),
                arguments(frequencies, 3, 1),
                arguments(frequencies, 4, 0),
                arguments(frequencies, 5, 2)
        );
    }
}