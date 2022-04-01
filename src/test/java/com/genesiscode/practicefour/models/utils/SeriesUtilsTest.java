package com.genesiscode.practicefour.models.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeriesUtilsTest {

    @Test
    void cellsTest() {
        //GIVEN
        int k = 4;

        //WHEN
        SeriesCell[][] cellsActual = SeriesUtils.cells(4);

        //THEN
        for (int i = 0; i < k; i++ ) {
            for (int j = 0; j < k; j++) {
                System.out.println(cellsActual[i][j]);
            }
        }
    }

    @Test
    void inverseListTest() {
        //GIVEN
        List<Double> inputList = List.of(0.34, 0.54, 0.1, 0.98);

        //WHEN
        List<Double> outputList = SeriesUtils.inverseList(inputList);

        //THEN
        assertThat(outputList).containsExactly(0.98, 0.1, 0.54, 0.34);
    }

    @Test
    void loadListPairsTest() {
        //GIVEN
        List<Double> inputList = List.of(0.31, 0.06, 0.29, 0.72, 0.86, 0.91, 0.65, 0.47, 0.35);
        int firstPair = 0, ultPair = inputList.size() - 2;

        //WHEN
        List<SeriesPair> outputList = SeriesUtils.loadListPairs(inputList);

        //THEN
        assertAll(
                () -> assertThat(outputList).hasSize(8),
                () -> assertThat(outputList.get(firstPair).getX()).isEqualTo(0.31),
                () -> assertThat(outputList.get(firstPair).getY()).isEqualTo(0.06),
                () -> assertThat(outputList.get(ultPair).getX()).isEqualTo(0.47),
                () -> assertThat(outputList.get(ultPair).getY()).isEqualTo(0.35)
        );
    }
}