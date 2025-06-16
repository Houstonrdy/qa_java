package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

class AnimalTest {

    @Test
    void testGetFamilyReturnsCorrectValue() {
        Animal animal = new Animal();
        String expectedFamily = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        assertEquals(expectedFamily, animal.getFamily());
    }

    @ParameterizedTest
    @CsvSource({
            "Травоядное, Трава;Различные растения",
            "Хищник, Животные;Птицы;Рыба"
    })
    void testGetFoodForValidKinds(String animalKind, String expectedFoodStr) throws Exception {
        Animal animal = new Animal();
        List<String> actualFood = animal.getFood(animalKind);
        List<String> expectedFood = Arrays.asList(expectedFoodStr.split(";"));
        assertIterableEquals(expectedFood, actualFood);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Птица", "Рыба", "Насекомое"})
    void testGetFoodThrowsExceptionForInvalidKinds(String animalKind) {
        Animal animal = new Animal();
        Exception exception = assertThrows(Exception.class, () -> animal.getFood(animalKind));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }

    @Test
    void testGetFoodForHerbivore() throws Exception {
        Animal animal = new Animal();
        List<String> expectedFood = Arrays.asList("Трава", "Различные растения");
        assertIterableEquals(expectedFood, animal.getFood("Травоядное"));
    }

    @Test
    void testGetFoodForPredator() throws Exception {
        Animal animal = new Animal();
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        assertIterableEquals(expectedFood, animal.getFood("Хищник"));
    }
}