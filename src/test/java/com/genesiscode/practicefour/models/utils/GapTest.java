package com.genesiscode.practiceasofour.models.utils;

import com.genesiscode.practiceasofour.models.Gap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GapTest {

    private Gap gap;

    @BeforeEach
    void setUp() {
        gap = new Gap();
    }

    @Test
    void getListTest() {
        //GIVEN
        int[] arrayGiven = {1,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1};

        //WHEN
        List<Integer> listActual = gap.getListOfFrequencies(arrayGiven);

        //WHEN
        assertThat(listActual).containsExactly(0, 7, 1, 1, 10, 0, 3);
    }

    @Test
    void getListOneTest() {
        //GIVEN
        int[] arrayGiven = {0,0,0,1,1};

        //WHEN
        List<Integer> listActual = gap.getListOfFrequencies(arrayGiven);

        //THEN
        assertThat(listActual).containsExactly(3, 0);
    }

    @Test
    void getListTwoTest() {
        //GIVEN
        int[] arrayGiven = {1,1,1,1,0};

        //WHEN
        List<Integer> listActual = gap.getListOfFrequencies(arrayGiven);

        //THEN
        assertThat(listActual).containsExactly(0,1);
    }

    @Test
    void getListThreeTest() {
        //GIVEN
        int[] arrayGiven = {1,0,0,0};

        //WHEN
        List<Integer> listActual = gap.getListOfFrequencies(arrayGiven);

        //THEN
        assertThat(listActual).containsExactly(3);
    }
}
