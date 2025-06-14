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
    void testGetSound() {
        Cat cat = new Cat(mockFeline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void testGetFood() throws Exception {
        when(mockFeline.eatMeat()).thenReturn(List.of("Мясо", "Рыба"));

        Cat cat = new Cat(mockFeline);
        List<String> actualFood = cat.getFood();

        assertEquals(List.of("Мясо", "Рыба"), actualFood);
        verify(mockFeline, times(1)).eatMeat();
    }
}