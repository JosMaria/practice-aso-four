package com.genesiscode.practiceasofour.models.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DecimalTest {

    @Test
    void getDecimal() {
        //input
        int count = 1;
        double number = 3.429232;

        //WHEN
        double decimalActual = Decimal.getDecimal(count, number);

        //THEN
        assertThat(decimalActual).isEqualTo(3.4);
    }
}