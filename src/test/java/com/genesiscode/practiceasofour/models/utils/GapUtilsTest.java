package com.genesiscode.practiceasofour.models.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        int[] numbersActual = GapUtils.nothingName(alpha, beta, numbers1);

        //THEN
        assertThat(numbersActual).containsExactly(numbersExpected);
    }
}