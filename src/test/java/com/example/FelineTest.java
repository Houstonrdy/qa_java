package com.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    private final Feline feline = new Feline();

    @Test
    void testEatMeat() throws Exception {
        assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
    }

    @Test
    void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @ParameterizedTest
    @CsvSource({"0", "1", "5"})
    void testGetKittensWithCustomValue(int kittensCount) {
        int result = feline.getKittens(kittensCount);
        assertEquals(Math.max(0, kittensCount), result);
    }

    @Test
    void testGetKittensDefault() {
        assertEquals(1, feline.getKittens());
    }
}