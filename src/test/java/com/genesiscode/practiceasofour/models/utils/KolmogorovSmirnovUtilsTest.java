package com.genesiscode.practiceasofour.models.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KolmogorovSmirnovUtilsTest {

    @Test
    void inverseListTest() {
        //GIVEN
        List<Double> input = List.of(0.36, 0.82, 0.54, 0.39, 0.76, 0.94, 0.72, 0.65, 0.03, 0.18);

        //WHEN
        List<Double> outputList = KolmogorovSmirnovUtils.inverseList(input);

        //THEN
        assertThat(outputList).containsExactly(0.03, 0.18, 0.36, 0.39, 0.54, 0.65, 0.72, 0.76, 0.82, 0.94);
    }
}