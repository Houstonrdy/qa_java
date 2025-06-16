package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline mockFeline;

    @Test
    void testGetSoundReturnsCorrectValue() {
        Cat cat = new Cat(mockFeline);
        String actualSound = cat.getSound();
        assertEquals("Мяу", actualSound);
    }

    @Test
    void testGetFoodReturnsCorrectValues() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(mockFeline.eatMeat()).thenReturn(expectedFood);
        Cat cat = new Cat(mockFeline);
        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    void testGetFoodCallsEatMeatOnce() throws Exception {
        List<String> expectedFood = List.of("Птица", "Кость");
        when(mockFeline.eatMeat()).thenReturn(expectedFood);
        Cat cat = new Cat(mockFeline);
        cat.getFood();
        verify(mockFeline, times(1)).eatMeat();
    }

    @Test
    void testGetFoodWithSingleItemReturnsCorrectValue() throws Exception {
        List<String> expectedFood = List.of("Фарш");
        when(mockFeline.eatMeat()).thenReturn(expectedFood);
        Cat cat = new Cat(mockFeline);
        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
    }
}