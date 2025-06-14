package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private final Animal animal = new Animal();

    @Test
    void testGetFoodForHerbivore() throws Exception {
        List<String> expected = List.of("Трава", "Различные растения");
        assertIterableEquals(expected, animal.getFood("Травоядное"));
    }

    @Test
    void testGetFoodForPredator() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        assertIterableEquals(expected, animal.getFood("Хищник"));
    }

    @Test
    void testGetFoodWithInvalidKindThrowsException() {
        Exception exception = assertThrows(
                Exception.class,
                () -> animal.getFood("Неизвестный")
        );
        assertEquals(
                "Неизвестный вид животного, используйте значение Травоядное или Хищник",
                exception.getMessage()
        );
    }

    @Test
    void testGetFamily() {
        String expected = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        assertEquals(expected, animal.getFamily());
    }
}