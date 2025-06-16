package com.example;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline mockFeline;

    @ParameterizedTest
    @CsvSource({"Самец, true", "Самка, false"})
    void testDoesHaveMane(String sex, boolean expected) throws Exception {
        Lion lion = new Lion(sex, mockFeline);
        assertEquals(expected, lion.doesHaveMane());
    }

    static Stream<Integer> kittenCounts() {
        return Stream.of(0, 1, 3, 5);
    }

    @ParameterizedTest
    @MethodSource("kittenCounts")
    void testGetKittens(int count) throws Exception {
        when(mockFeline.getKittens()).thenReturn(count);

        Lion lion = new Lion("Самец", mockFeline);
        assertEquals(count, lion.getKittens());

        verify(mockFeline).getKittens();
    }

    static Stream<Arguments> foodProvider() {
        return Stream.of(
                Arguments.of(List.of("Животные", "Птицы", "Рыба")),
                Arguments.of(List.of("Мясо")),
                Arguments.of(List.of("Корм для кошек", "Мышки"))
        );
    }

    @ParameterizedTest
    @MethodSource("foodProvider")
    void testGetFood(List<String> foodList) throws Exception {
        when(mockFeline.getFood("Хищник")).thenReturn(foodList);

        Lion lion = new Lion("Самец", mockFeline);
        assertIterableEquals(foodList, lion.getFood());

        verify(mockFeline).getFood("Хищник");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Неизвестно", "Средний пол", "Другое"})
    void testInvalidSexThrowsException(String invalidSex) {
        Exception exception = assertThrows(
                Exception.class,
                () -> new Lion(invalidSex, mockFeline)
        );
        assertEquals(
                "Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage()
        );
    }
}