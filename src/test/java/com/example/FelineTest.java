package com.example;

import org.junit.jupiter.api.Test;
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

    @Test
    void testGetKittensDefault() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    void testGetKittensWithCustomValue() {
        assertEquals(5, feline.getKittens(5));
    }
}