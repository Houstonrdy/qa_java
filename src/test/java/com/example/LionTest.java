package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline mockFeline;

    @Test
    void testDoesHaveManeForMale() throws Exception {
        Lion lion = new Lion("Самец", mockFeline);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    void testDoesHaveManeForFemale() throws Exception {
        Lion lion = new Lion("Самка", mockFeline);
        assertFalse(lion.doesHaveMane());
    }

    @Test
    void testGetKittens() throws Exception {
        when(mockFeline.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самец", mockFeline);
        assertEquals(1, lion.getKittens());

        verify(mockFeline).getKittens();
    }

    @Test
    void testGetFood() throws Exception {
        when(mockFeline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        Lion lion = new Lion("Самец", mockFeline);
        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());

        verify(mockFeline).getFood("Хищник");
    }

    @Test
    void testInvalidSexThrowsException() throws Exception {
        Exception exception = assertThrows(
                Exception.class,
                () -> new Lion("Неизвестно", mockFeline)
        );
        assertEquals(
                "Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage()
        );
    }
}