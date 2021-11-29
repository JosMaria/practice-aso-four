package com.genesiscode.practiceasofour.models.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PokerUtilsTest {

    @ParameterizedTest(name = "#{index} - Test: decimal={0}, result={1}")
    @MethodSource("getPartDecimalProvider")
    void getPartDecimalTest(String number, String partDecimalExpected) {
        //WHEN
        String partDecimalActual = PokerUtils.getPartDecimal(number);

        //THEN
        assertThat(partDecimalActual).isEqualTo(partDecimalExpected);
    }

    static Stream<Arguments> getPartDecimalProvider() {
        return Stream.of(
                arguments("0.8763", "8763"),
                arguments("0.234", "234"),
                arguments("0.24321", "24321")
        );
    }

    @ParameterizedTest(name = "#{index} - Test: number={0}, result={1}")
    @MethodSource("numberToAddIsCorrectWithTheCountOfDecimalsProvider")
    void numberToAddIsCorrectWithTheCountOfDecimalsTest(String textNumber, int countExpected) {
        //WHEN
        boolean countActual = PokerUtils.numberToAddIsCorrectWithTheCountOfDecimals(textNumber, countExpected);

        //THEN
        assertThat(countActual).isTrue();
    }

    static Stream<Arguments> numberToAddIsCorrectWithTheCountOfDecimalsProvider() {
        return Stream.of(
                arguments("0.8763", 4),
                arguments("0.234", 3),
                arguments("0.24321", 5)
        );
    }

    @ParameterizedTest(name = "#{index} - Test: textNumber={0}, map={1}")
    @MethodSource("textNumberToMapProvider")
    void textNumberToMapTest(String textNumber, Map<Integer, Integer> mapExpected) {
        //GIVEN
        HashMap<Integer, Integer> mapActual = new HashMap<>();

        //WHEN
        PokerUtils.textNumberToMap(mapActual, textNumber);

        //THEN
        assertThat(mapActual).containsExactlyInAnyOrderEntriesOf(mapExpected);
    }

    static Stream<Arguments> textNumberToMapProvider() {
        return Stream.of(
                arguments("777", Map.of(7, 3)),
                arguments("8988", Map.of(8, 3, 9, 1)),
                arguments("98987", Map.of(9, 2, 8, 2, 7, 1 )),
                arguments("654", Map.of(6, 1, 5, 1, 4, 1))
        );
    }

    @ParameterizedTest(name = "#{index} - Test: textNumber={0}, category={1}, probability={2}")
    @MethodSource("getItsProbabilityProvider")
    void getItsProbabilityTest(int countDecimals, Category category, double probabilityExpected) {
        //WHEN
        double probabilityActual = PokerUtils.getItsProbability(category, countDecimals);

        //THEN
        assertThat(probabilityActual).isEqualTo(probabilityExpected);
    }

    static Stream<Arguments> getItsProbabilityProvider() {
        return Stream.of(
                arguments(3, Category._1P, 0.27),
                arguments(3, Category.TD, 0.72),
                arguments(3, Category.T, 0.01),

                arguments(4, Category.TD, 0.5040),
                arguments(4, Category._1P, 0.4320),
                arguments(4, Category._2P, 0.0270),
                arguments(4, Category.T, 0.0360),
                arguments(4, Category.P, 0.0010)
        );
    }
}